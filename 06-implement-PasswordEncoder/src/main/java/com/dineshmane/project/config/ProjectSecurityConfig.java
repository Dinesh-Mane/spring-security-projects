package com.dineshmane.project.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class ProjectSecurityConfig {

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http.authorizeHttpRequests(request-> request
                .requestMatchers("/welcome", "/contact", "/notices", "/error").permitAll()
                .requestMatchers("/myAccount", "/myLoans", "/myBalance", "/myCards").authenticated()
        );

        http.formLogin(Customizer.withDefaults());
        http.httpBasic(Customizer.withDefaults());

        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService(){

        UserDetails user1 = User.withUsername("dinesh").password("{noop}1234").authorities("user").build();
        UserDetails user2 = User.withUsername("mukesh").password("{bcrypt}$2a$12$6kjwsTfs0ENr2Md6hLJXa.18jioMjlxWjGt6QNmrOpxgJGIKPwTwO").authorities("admin").build();
        UserDetails user3 = User.withUsername("vaibhav").password("{noop}pass").authorities("user").build();
        UserDetails user4 = User.withUsername("shreyas").password("{bcrypt}$2a$12$cNOJtggGzA0cCyzQ61fWjeI8YmpimXMWNDUIOmksehvWcgQV3iFqO").authorities("admin").build();

        return new InMemoryUserDetailsManager(user1, user2, user3, user4);
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

}
