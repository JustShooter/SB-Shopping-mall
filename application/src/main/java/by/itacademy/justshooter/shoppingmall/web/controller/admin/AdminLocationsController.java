package by.itacademy.justshooter.shoppingmall.web.controller.admin;

import by.itacademy.justshooter.shoppingmall.service.api.AdminService;
import by.itacademy.justshooter.shoppingmall.service.dtos.LocationDto;
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

import static by.itacademy.justshooter.shoppingmall.web.controller.MockConstants.ID;
import static by.itacademy.justshooter.shoppingmall.web.controller.MockConstants.TITLE;

@Controller
@RequiredArgsConstructor
public class AdminLocationsController {

    private final AdminService adminService;

    @RequestMapping("/admin/locations")
    public String viewAllLocations(Model model) {
        model.addAttribute(TITLE, "title.admin.locations");
        List<LocationDto> allLocations = adminService.getAllLocations();
        model.addAttribute("allLocations", allLocations);
        return "/admin/location/locationList";
    }

    @RequestMapping("/admin/locations/new")
    public String showNewLocationPage(Model model) {
        model.addAttribute(TITLE, "title.admin.locations.new");
        LocationDto location = new LocationDto();
        model.addAttribute("location", location);
        return "admin/location/location";
    }

    @PostMapping("/admin/locations/new")
    public String createLocation(@Valid @ModelAttribute("location") LocationDto location,
                                 Errors errors,
                                 Model model) {
        model.addAttribute(TITLE, "title.admin.locations.new" + " " + "common.menu.error");
        if (errors.hasErrors()) {
            return "admin/location/location";
        }
        adminService.saveLocation(location);
        return "redirect:/admin/locations";
    }

    @RequestMapping("/admin/locations/edit/{id}")
    public String showEditLocationPage(Model model, @PathVariable Integer id) {
        model.addAttribute(TITLE, "title.admin.locations.edit");
        LocationDto location = adminService.getLocationById(id);
        model.addAttribute("location", location);
        return "admin/location/location";
    }

    @PostMapping("/admin/locations/edit/{id}")
    public String saveLocation(@Valid @ModelAttribute("location") LocationDto location,
                               Errors errors,
                               @PathVariable Integer id,
                               Model model) {
        model.addAttribute(TITLE, "title.admin.locations.edit" + " " + "common.menu.error");
        if (errors.hasErrors()) {
            return "admin/location/location";
        }
        if (!id.equals(location.getId())) {
            errors.rejectValue(ID, "validation.error.id.mismatch");
            location.setId(id);
            return "admin/location/location";
        }
        adminService.saveLocation(location);
        return "redirect:/admin/locations";
    }

    @RequestMapping("/admin/locations/delete/{id}")
    public String deleteLocation(@PathVariable Integer id) {
        adminService.deleteLocation(id);
        return "redirect:/admin/locations";
    }
}
