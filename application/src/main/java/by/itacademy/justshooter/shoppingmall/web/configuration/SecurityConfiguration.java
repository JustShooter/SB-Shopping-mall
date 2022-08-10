package by.itacademy.justshooter.shoppingmall.web.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;

@Configuration
public class SecurityConfiguration {
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .authorizeRequests()
                .antMatchers("/admin/locations/**","/admin/**" )
                .access("hasRole('ADMIN')")
                .antMatchers("/shop-owner/**")
                .access("hasAnyRole('ADMIN', 'SHOP_OWNER')")
                .antMatchers("/search/**")
                .access("hasAnyRole('ADMIN', 'SHOP_OWNER', 'USER')")
                .antMatchers("/", "/**")
                .access("permitAll()")
                .and()
                .csrf()
                .csrfTokenRepository(new HttpSessionCsrfTokenRepository())
                .and()
                .formLogin()
                .loginPage("/login")
                .and()
                .logout()
                .logoutSuccessUrl("/")
                .and()
                .build();
    }
}
