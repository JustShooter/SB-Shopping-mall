package by.itacademy.justshooter.shoppingmall.web.controller.admin;

import by.itacademy.justshooter.shoppingmall.service.api.AdminService;
import by.itacademy.justshooter.shoppingmall.service.dtos.CategoryDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.List;

import static by.itacademy.justshooter.shoppingmall.web.controller.MockConstants.*;

@Controller
@RequiredArgsConstructor
public class AdminCategoriesController {

    private final AdminService adminService;

    @RequestMapping(ADMIN_CATEGORIES)
    public String viewAllCategories(Model model) {
        model.addAttribute(TITLE, "title.admin.categories");
        List<CategoryDto> allCategories = adminService.getAllCategories();
        model.addAttribute(ALL_CATEGORIES, allCategories);
        return ADMIN_CATEGORY_CATEGORY_LIST;
    }

    @RequestMapping(ADMIN_CATEGORIES_NEW)
    public String showNewCategoryPage(Model model) {
        model.addAttribute(TITLE, "title.admin.categories.add");
        CategoryDto category = new CategoryDto();
        model.addAttribute(CATEGORY, category);
        return ADMIN_CATEGORY_CATEGORY;
    }

    @PostMapping(ADMIN_CATEGORIES_NEW)
    public String createCategory(@Valid @ModelAttribute(CATEGORY) CategoryDto category,
                                 Errors errors,
                                 Model model) {
        model.addAttribute(TITLE, "title.admin.categories.add" + " " + "common.menu.error");
        if (errors.hasErrors()) {
            return ADMIN_CATEGORY_CATEGORY;
        }
        adminService.saveCategory(category);
        return REDIRECT + ADMIN_CATEGORIES;
    }

    @RequestMapping(ADMIN_CATEGORIES_EDIT_ID)
    public String showEditCategoryPage(Model model, @PathVariable Integer id) {
        model.addAttribute(TITLE, "title.admin.categories.edit");
        CategoryDto category = adminService.getCategoryById(id);
        model.addAttribute(CATEGORY, category);
        return ADMIN_CATEGORY_CATEGORY;
    }

    @PostMapping(ADMIN_CATEGORIES_EDIT_ID)
    public String saveCategory(@Valid @ModelAttribute(CATEGORY) CategoryDto category,
                               Errors errors,
                               @PathVariable Integer id,
                               Model model) {
        model.addAttribute(TITLE, "title.admin.categories.edit" + " " + "common.menu.error");
        if (errors.hasErrors()) {
            return ADMIN_CATEGORY_CATEGORY;
        }
        if (!id.equals(category.getId())) {
            errors.rejectValue(ID, "validation.error.id.mismatch");
            category.setId(id);
            return ADMIN_CATEGORY_CATEGORY;
        }
        adminService.saveCategory(category);
        return REDIRECT + ADMIN_CATEGORIES;
    }

    @RequestMapping(ADMIN_CATEGORIES_DELETE_ID)
    public String deleteCategory(@PathVariable Integer id) {
        adminService.deleteCategory(id);
        return REDIRECT + ADMIN_CATEGORIES;
    }
}
