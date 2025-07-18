package com.javayh.omni.ai.chat.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/login", "/css/**", "/js/**", "/logo.jpeg").permitAll()
                .requestMatchers("/chat", "/chat2.html", "/chat/**").authenticated()
                .anyRequest().permitAll()
            )
            .formLogin(form -> form
                .loginPage("/login")
                .defaultSuccessUrl("/chat", true)
                .permitAll()
            )
            .logout(logout -> logout
                .logoutSuccessUrl("/login?logout")
                .permitAll()
            )
            .csrf(AbstractHttpConfigurer::disable); // ⛔️ 关闭 CSRF 试试看是否就是这个问题引起的

        return http.build();
    }
    @Bean
    public UserDetailsService userDetailsService() {
        // 示例：内存用户（你可以换成数据库用户）
        UserDetails user = User.withUsername("Haiji")
            .password("{noop}123456") // {noop}表示不加密（只用于开发）
            .roles("USER")
            .build();
        return new InMemoryUserDetailsManager(user);
    }
}
