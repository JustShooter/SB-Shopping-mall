package by.itacademy.justshooter.shoppingmall.web.controller.admin;

import by.itacademy.justshooter.shoppingmall.service.api.AdminService;
import by.itacademy.justshooter.shoppingmall.service.dtos.ItemDto;
import by.itacademy.justshooter.shoppingmall.web.controller.MockConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static by.itacademy.justshooter.shoppingmall.web.controller.MockConstants.*;

@Controller
@RequiredArgsConstructor
public class AdminItemsController {

    private final AdminService adminService;

    @RequestMapping("/admin/items")
    public String showItems(Model model,
                            @RequestParam("page") Optional<Integer> page,
                            @RequestParam("size") Optional<Integer> size) {
        int currentPage = page.orElse(1);
        int pageSize = size.orElse(10);
        Sort sort = Sort.by("id").ascending();
        Page<ItemDto> items = adminService.findPaginatedItems(PageRequest.of(currentPage - 1, pageSize, sort));
        model.addAttribute("items", items);
        int totalPages = items.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }

        model.addAttribute(TITLE, "title.admin.items");
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("currentPage", currentPage);
        return "admin/item/itemList";
    }

    @RequestMapping("/admin/items/new")
    public String addItem(@ModelAttribute("item") ItemDto item, Model model) {
        model.addAttribute(TITLE, "title.admin.items.add");
        return "admin/item/item";
    }

    @PostMapping("/admin/items/new")
    public String createItem(@Valid @ModelAttribute("item") ItemDto item,
                             Errors errors,
                             Model model) {
        model.addAttribute(TITLE, "title.admin.items.add" + "common.menu.error");
        if (errors.hasErrors()) {
            return "admin/item/item";
        }
        adminService.saveItem(item);
        return "redirect:/admin/items";
    }

    @RequestMapping("/admin/items/edit/{id}")
    public String editItem(Model model, @PathVariable Integer id) {
        model.addAttribute(TITLE, "title.admin.items.edit");
        model.addAttribute("item", adminService.getItemById(id));
        return "admin/item/item";
    }

    @PostMapping("/admin/items/edit/{id}")
    public String updateItem(@Valid @ModelAttribute("item") ItemDto item,
                             Errors errors,
                             @PathVariable Integer id,
                             Model model) {
        model.addAttribute(TITLE, "title.admin.items.edit" + "common.menu.error");
        if (errors.hasErrors()) {
            return "admin/item/item";
        } else if (!id.equals(item.getId())) {
            errors.rejectValue("id", "validation.error.id.mismatch");
            item.setId(id);
            return "admin/item/item";
        } else {
            adminService.saveItem(item);
            return "redirect:/admin/items";
        }
    }

    @RequestMapping("/admin/items/delete/{id}")
    public String deleteItem(@PathVariable Integer id) {
        adminService.deleteItem(id);
        return "redirect:/admin/items";
    }

}
