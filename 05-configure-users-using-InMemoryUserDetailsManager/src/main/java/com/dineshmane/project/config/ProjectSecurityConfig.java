package com.dineshmane.project.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
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

        UserDetails user1 = User.withUsername("dinesh").password("{noop}1234").authorities("admin").build();
        UserDetails user2 = User.withUsername("mukesh").password("{noop}root").authorities("user").build();
        UserDetails user3 = User.withUsername("vaibhav").password("{noop}pass").authorities("admin").build();
        UserDetails user4 = User.withUsername("shreyas").password("{noop}5678").authorities("user").build();

        return new InMemoryUserDetailsManager(user1, user2, user3, user4);
    }
}
