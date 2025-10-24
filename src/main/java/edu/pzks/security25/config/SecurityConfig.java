package edu.pzks.security25.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

@Configuration
@EnableMethodSecurity(prePostEnabled = true)
public class SecurityConfig {

    @Bean
    public UserDetailsService userDetailsService() {
        var admin = User.withDefaultPasswordEncoder()
                .username("admin").password("admin123")
                .roles("ADMIN","MANAGER","USER")
                .authorities("ITEM_READ","ITEM_WRITE","AUDIT_READ","FINANCE_READ")
                .build();
        var manager = User.withDefaultPasswordEncoder()
                .username("manager").password("manager123")
                .roles("MANAGER","USER")
                .authorities("ITEM_READ","ITEM_WRITE")
                .build();
        var auditor = User.withDefaultPasswordEncoder()
                .username("auditor").password("auditor123")
                .roles("AUDITOR")
                .authorities("ITEM_READ","AUDIT_READ")
                .build();
        var user = User.withDefaultPasswordEncoder()
                .username("user").password("user123")
                .roles("USER")
                .authorities("ITEM_READ")
                .build();
        return new InMemoryUserDetailsManager(admin, manager, auditor, user);
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()))
            .authorizeHttpRequests(auth -> auth.anyRequest().authenticated())
            .httpBasic(Customizer.withDefaults());
        return http.build();
    }
}
