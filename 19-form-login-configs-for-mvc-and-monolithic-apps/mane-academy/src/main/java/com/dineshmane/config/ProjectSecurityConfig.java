package com.dineshmane.config;

import com.dineshmane.handler.CustomAuthenticationFailureHandler;
import com.dineshmane.handler.CustomAuthenticationSuccessHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.password.CompromisedPasswordChecker;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.password.HaveIBeenPwnedRestApiPasswordChecker;

@Configuration
@RequiredArgsConstructor
public class ProjectSecurityConfig {

    private final CustomAuthenticationSuccessHandler authenticationSuccessHandler;
    private final CustomAuthenticationFailureHandler authenticationFailureHandler;

    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {

        http.csrf((csrf) -> csrf.disable());
        http.authorizeHttpRequests((requests) -> requests.requestMatchers("/dashboard").authenticated());
        http.authorizeHttpRequests((requests) -> requests
                .requestMatchers("/", "/home", "/holidays/**", "/contact", "/saveMsg", "/courses", "/about", "/assets/**", "/login/**").permitAll());
        http.formLogin(flc->flc.loginPage("/login").defaultSuccessUrl("/dashboard").failureUrl("/login?error=true"));
        http.formLogin(flc->flc.successHandler(authenticationSuccessHandler).failureHandler(authenticationFailureHandler));
        http.logout(loc->loc.logoutSuccessUrl("/login?logout=true").invalidateHttpSession(true).clearAuthentication(true).deleteCookies("JSESSIONID"));
        http.httpBasic(Customizer.withDefaults());

        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails user1 = User.withUsername("dinesh").password("{noop}SpringUser@1234").authorities("admin").build();
        UserDetails user2 = User.withUsername("mukesh").password("{noop}SpringUser@2345").authorities("user").build();
        UserDetails user3 = User.withUsername("vaibhav").password("{noop}SpringUser@3456").authorities("admin").build();
        UserDetails user4 = User.withUsername("shreyas").password("{bcrypt}$2a$12$AT6srF6n03ybsIcghdJTqeQIVjpaGURHJTG8shG/ND52masW0PEaO").authorities("user").build();

        return new InMemoryUserDetailsManager(user1, user2, user3, user4);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    public CompromisedPasswordChecker compromisedPasswordChecker() {
        return new HaveIBeenPwnedRestApiPasswordChecker();
    }


}
