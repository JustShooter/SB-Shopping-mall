package by.itacademy.justshooter.shoppingmall.web.controller;

import by.itacademy.justshooter.shoppingmall.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.Set;
import java.util.stream.Collectors;

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
