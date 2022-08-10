package by.itacademy.justshooter.shoppingmall.web.controller.admin;

import by.itacademy.justshooter.shoppingmall.entity.enums.StreetType;
import by.itacademy.justshooter.shoppingmall.service.api.AdminService;
import by.itacademy.justshooter.shoppingmall.service.dtos.AddressDto;
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

import static by.itacademy.justshooter.shoppingmall.web.controller.MockConstants.STREET_TYPES;
import static by.itacademy.justshooter.shoppingmall.web.controller.MockConstants.TITLE;

@Controller
@RequiredArgsConstructor
public class AdminAddressesController {

    private final AdminService adminService;

    @RequestMapping("/admin/addresses")
    public String viewAllAddresses(Model model) {
        model.addAttribute(TITLE, "title.admin.addresses");
        List<AddressDto> allAddresses = adminService.getAllAddresses();
        model.addAttribute("allAddresses", allAddresses);
        return "admin/address/addressList";
    }

    @RequestMapping("/admin/addresses/new")
    public String addAddress(@ModelAttribute("address") AddressDto address, Model model) {
        model.addAttribute(STREET_TYPES, StreetType.values());
        model.addAttribute(TITLE, "title.admin.addresses.add");
        return "admin/address/address";
    }

    @PostMapping("/admin/addresses/new")
    public String createAddress(@Valid @ModelAttribute("address") AddressDto address,
                                Errors errors,
                                Model model) {
        model.addAttribute(TITLE, "title.admin.addresses.add" + "common.menu.error");
        if (errors.hasErrors()) {
            model.addAttribute(STREET_TYPES, StreetType.values());
            return "admin/address/address";
        }
        adminService.saveAddress(address);
        return "redirect:/admin/addresses";
    }

    @RequestMapping("/admin/addresses/edit/{id}")
    public String editAddress(Model model, @PathVariable Integer id) {
        AddressDto address = adminService.getAddressById(id);
        model.addAttribute("address", address);
        model.addAttribute(STREET_TYPES, StreetType.values());
        model.addAttribute(TITLE, "title.admin.addresses.edit");
        return "admin/address/address";
    }

    @PostMapping("/admin/addresses/edit/{id}")
    public String updateAddress(@Valid @ModelAttribute("address") AddressDto address,
                                Errors errors,
                                @PathVariable Integer id,
                                Model model) {
        model.addAttribute(TITLE, "title.admin.addresses.edit" + "common.menu.error");
        model.addAttribute(STREET_TYPES, StreetType.values());
        if (errors.hasErrors()) {
            return "admin/address/address";
        } else if (!id.equals(address.getId())) {
            errors.rejectValue("id", "validation.error.id.mismatch");
            address.setId(id);
            return "admin/address/address";
        } else {
            adminService.saveAddress(address);
            return "redirect:/admin/addresses";
        }
    }

    @RequestMapping("/admin/addresses/delete/{id}")
    public String deleteAddress(@PathVariable Integer id) {
        adminService.deleteAddress(id);
        return "redirect:/admin/addresses";
    }

}
