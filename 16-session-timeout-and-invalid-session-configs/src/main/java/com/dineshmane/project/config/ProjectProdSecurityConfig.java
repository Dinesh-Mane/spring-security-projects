package com.dineshmane.project.config;

import com.dineshmane.project.exception.CustomAccessDeniedHandler;
import com.dineshmane.project.exception.CustomBasicAuthenticationEntryPoint;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.authentication.password.CompromisedPasswordChecker;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.password.HaveIBeenPwnedRestApiPasswordChecker;

@Configuration
@Profile("prod")
public class ProjectProdSecurityConfig {

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.sessionManagement(smc->smc.invalidSessionUrl("/invalidSession"));
        http.redirectToHttps(Customizer.withDefaults()); // only HTTPS
        http.csrf((csrfConfig)-> csrfConfig.disable());
        http.authorizeHttpRequests(request-> request
                .requestMatchers("/welcome", "/contact", "/notices", "/register", "/invalidSession").permitAll()
                .requestMatchers("/myAccount", "/myLoans", "/myBalance", "/myCards").authenticated()
        );

        http.formLogin(Customizer.withDefaults());
        http.httpBasic(hbc->hbc.authenticationEntryPoint(new CustomBasicAuthenticationEntryPoint()));
        http.exceptionHandling(ehc->ehc.accessDeniedHandler(new CustomAccessDeniedHandler()));

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    public CompromisedPasswordChecker compromisedPasswordChecker(){
        return new HaveIBeenPwnedRestApiPasswordChecker();
    }

}
