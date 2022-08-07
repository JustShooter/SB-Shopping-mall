package by.itacademy.justshooter.shoppingmall;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        PasswordEncoder passwordEncoder =  new BCryptPasswordEncoder();
        String password = passwordEncoder.encode("user");
        System.out.println(password);
        System.out.println(passwordEncoder.matches("user", password));
    }
}