package by.itacademy.justshooter.shoppingmall.web.configuration;

import by.itacademy.justshooter.shoppingmall.entity.User;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice
public class UserControllerAdvice {

    @ModelAttribute("currentUser")
    public User getCurrentUser(Authentication authentication) {
        User user = null;
        if (authentication != null) {
            user = (User) authentication.getPrincipal();
        }
        return user;
    }
}
