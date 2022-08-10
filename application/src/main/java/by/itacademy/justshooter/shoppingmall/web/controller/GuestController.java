package by.itacademy.justshooter.shoppingmall.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
@RequiredArgsConstructor
public class GuestController {

    @RequestMapping
    public String getMainPage(Model model) {
        model.addAttribute(MockConstants.TITLE, "title.main");
        return "index";
    }

    @RequestMapping("/login")
    public String getLoginPage(Model model) {
        model.addAttribute(MockConstants.TITLE, "title.login");
        return "login";
    }
}
