package by.itacademy.justshooter.shoppingmall.web.controller.admin;

import by.itacademy.justshooter.shoppingmall.service.api.AdminService;
import by.itacademy.justshooter.shoppingmall.service.dtos.AddressDto;
import by.itacademy.justshooter.shoppingmall.service.dtos.ShopOwnerDto;
import lombok.RequiredArgsConstructor;
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

import static by.itacademy.justshooter.shoppingmall.web.controller.MockConstants.TITLE;

@Controller
@RequiredArgsConstructor
public class AdminShopOwnersController {

    private final AdminService adminService;

    @RequestMapping("/admin/shop-owners")
    public String viewAllShopOwners(Model model) {
        model.addAttribute(TITLE, "title.admin.shop_owners");
        List<ShopOwnerDto> allShopOwners = adminService.getAllShopOwners();
        model.addAttribute("allShopOwners", allShopOwners);
        return "/admin/shop.owner/shopOwnerList";
    }

    @RequestMapping("/admin/shop-owners/new")
    public String showNewShopOwnerPage(Model model) {
        model.addAttribute(TITLE, "title.admin.shop_owners.add");
        ShopOwnerDto shopOwner = new ShopOwnerDto();
        model.addAttribute("shopOwner", shopOwner);
        prepareAttributes(model);
        return "admin/shop.owner/shopOwner";
    }

    @PostMapping("/admin/shop-owners/new")
    public String createShopOwner(@Valid @ModelAttribute("shopOwner") ShopOwnerDto shopOwner,
                                  Errors errors,
                                  Model model) {
        prepareAttributes(model);
        model.addAttribute(TITLE, "title.admin.shop_owners.add" + "common.menu.error");
        if (errors.hasErrors()) {
            return "admin/shop.owner/shopOwner";
        }
        adminService.saveShopOwner(shopOwner);
        return "redirect:/admin/shop-owners";
    }

    @RequestMapping("/admin/shop-owners/edit/{id}")
    public String editShopOwner(Model model, @PathVariable Integer id) {
        model.addAttribute(TITLE, "title.admin.shop_owners.edit");
        ShopOwnerDto shopOwner = adminService.getShopOwnerById(id);
        model.addAttribute("shopOwner", shopOwner);
        prepareAttributes(model);
        return "/admin/shop.owner/shopOwner";
    }

    @PostMapping("/admin/shop-owners/edit/{id}")
    public String saveShopOwner(@Valid @ModelAttribute("shopOwner") ShopOwnerDto shopOwner,
                                Errors errors,
                                @PathVariable Integer id,
                                Model model) {
        prepareAttributes(model);
        model.addAttribute(TITLE, "title.admin.shop_owners.edit" + " " + "common.menu.error");
        if (errors.hasErrors()) {
            return "admin/shop.owner/shopOwner";
        } else if (!id.equals(shopOwner.getId())) {
            errors.rejectValue("id", "validation.error.id.mismatch");
            shopOwner.setId(id);
            return "admin/shop.owner/shopOwner";
        } else {
            adminService.saveShopOwner(shopOwner);
            return "redirect:/admin/shop-owners";
        }
    }

    private void prepareAttributes(Model model) {
        List<AddressDto> allAddresses = adminService.getAllAddresses();
        AddressDto address = new AddressDto();
        model.addAttribute("address", address);
        model.addAttribute("allAddresses", allAddresses);
    }

}
