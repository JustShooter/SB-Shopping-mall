package by.itacademy.justshooter.shoppingmall.service.api;

import by.itacademy.justshooter.shoppingmall.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Optional;

public interface UserService extends UserDetailsService {

    Optional<User> findUserByUsername(String username);

}
