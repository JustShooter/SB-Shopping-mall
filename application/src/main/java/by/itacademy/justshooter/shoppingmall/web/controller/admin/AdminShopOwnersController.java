package by.itacademy.justshooter.shoppingmall.web.controller.admin;

import by.itacademy.justshooter.shoppingmall.entity.enums.StreetType;
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

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

import static by.itacademy.justshooter.shoppingmall.web.controller.MockConstants.RETURN_URL;
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
        shopOwner.setAddress(new AddressDto());
        prepareAttributes(model, shopOwner);
        return "admin/shop.owner/shopOwner";
    }

    @PostMapping("/admin/shop-owners/new")
    public String createShopOwner(@Valid @ModelAttribute("shopOwner") ShopOwnerDto shopOwner,
                                  Errors errors,
                                  @ModelAttribute AddressDto address,
                                  Model model) {
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
        prepareAttributes(model, shopOwner);
        return "/admin/shop.owner/shopOwner";
    }

    @PostMapping("/admin/shop-owners/edit/{id}")
    public String saveShopOwner(@Valid @ModelAttribute("shopOwner") ShopOwnerDto shopOwner,
                                Errors errors,
                                @PathVariable Integer id,
                                Model model) {
        model.addAttribute(TITLE, "title.admin.shop_owners.edit" + " " + "common.menu.error");
        if (errors.hasErrors()) {
            return "admin/shop.owner/shopOwner";
        }
        if (!id.equals(shopOwner.getId())) {
            errors.rejectValue("id", "validation.error.id.mismatch");
            shopOwner.setId(id);
            return "admin/shop.owner/shopOwner";
        }
        adminService.saveShopOwner(shopOwner);
        return "redirect:/admin/shop-owners";
    }

    @PostMapping("/admin/shop-owners/address/new")
    public String createAddress(@Valid @ModelAttribute("address") AddressDto address,
                                Errors errors,
                                HttpServletRequest request,
                                @Valid @ModelAttribute("shopOwner") ShopOwnerDto shopOwner,
                                Model model) {
        if (request.getSession().getAttribute(RETURN_URL) == null) {
            request.getSession().setAttribute(RETURN_URL, request
                    .getHeader("referer")
                    .substring(request.getHeader("origin").length()));
        }
        if (errors.hasErrors()) {
            model.addAttribute(TITLE, "title.admin.shop_owners.add"
                    + "title.admin.addresses.add"
                    + "common menu.error");
            List<AddressDto> allAddresses1 = adminService.getAllAddresses();
            StreetType[] streetTypes = StreetType.values();
            model.addAttribute("allAddresses", allAddresses1);
            model.addAttribute("streetTypes", streetTypes);
            shopOwner.setAddress(address);
            return "admin/shop.owner/shopOwner";
        }
        String returnUrl = (String) request.getSession().getAttribute(RETURN_URL);
        request.getSession().removeAttribute(RETURN_URL);
        adminService.saveAddress(address);
        return "redirect:" + returnUrl;
    }

    private void prepareAttributes(Model model, ShopOwnerDto shopOwner) {
        List<AddressDto> allAddresses = adminService.getAllAddresses();
        StreetType[] streetTypes = StreetType.values();
        AddressDto address = new AddressDto();
        model.addAttribute("address", address);
        model.addAttribute("allAddresses", allAddresses);
        model.addAttribute("streetTypes", streetTypes);
        model.addAttribute("shopOwner", shopOwner);
    }

}
