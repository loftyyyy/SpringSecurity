package com.rho.srpingsecurity.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
public class WebAuthorizationConfig {
    private final StaticAuthenticationFilter staticAuthenticationFilter;
    private final AuthenticationProvider authenticationProvider;

    public WebAuthorizationConfig(StaticAuthenticationFilter staticAuthenticationFilter, AuthenticationProvider authenticationProvider){
        this.staticAuthenticationFilter = staticAuthenticationFilter;
        this.authenticationProvider = authenticationProvider;
    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        http.addFilterBefore(new RequestValidationFilter(), BasicAuthenticationFilter.class);
        http.addFilterAfter(new AuthenticationLoggingFilter(), BasicAuthenticationFilter.class);
        http.addFilterAt(staticAuthenticationFilter, BasicAuthenticationFilter.class);
        http.httpBasic(Customizer.withDefaults());
        http.authenticationProvider(authenticationProvider);
        http.authorizeHttpRequests(c -> c.anyRequest().authenticated());
        return http.build();
    }

}
