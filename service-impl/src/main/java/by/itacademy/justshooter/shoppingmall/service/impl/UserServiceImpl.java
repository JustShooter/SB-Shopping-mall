package by.itacademy.justshooter.shoppingmall.service.impl;

import by.itacademy.justshooter.shoppingmall.entity.User;
import by.itacademy.justshooter.shoppingmall.repository.UserRepository;
import by.itacademy.justshooter.shoppingmall.service.api.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public Optional<User> findUserByUsername(String username) {
        return userRepository.findByUserName(username);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByUserName(username);
        if (user.isEmpty()) {
            throw new UsernameNotFoundException("User not found");
        }
        return user.get();

    }
}
