package com.rho.srpingsecurity.config;


import com.rho.srpingsecurity.config.filter.AuthenticationLoggingFilter;
import com.rho.srpingsecurity.config.filter.RequestValidationFilter;
import com.rho.srpingsecurity.config.filter.StaticAuthenticationFilter;
import com.rho.srpingsecurity.config.handler.CustomAuthenticationFailureHandler;
import com.rho.srpingsecurity.config.handler.CustomAuthenticationSuccessHandler;
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

    public WebAuthorizationConfig( AuthenticationProvider authenticationProvider, StaticAuthenticationFilter staticAuthenticationFilter){
        this.staticAuthenticationFilter = staticAuthenticationFilter;
        this.authenticationProvider = authenticationProvider;
    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        http.addFilterBefore(new RequestValidationFilter(), BasicAuthenticationFilter.class);
        http.addFilterAfter(new AuthenticationLoggingFilter(), BasicAuthenticationFilter.class);
        http.addFilterAt(staticAuthenticationFilter, BasicAuthenticationFilter.class);
        http.httpBasic(c -> {
            c.realmName("OTHER");
            c.authenticationEntryPoint(new CustomEntryPoint());
        });

//        http.formLogin(c -> {
//            c.loginPage("/login");
//            c.defaultSuccessUrl("/home", true);
//            c.successHandler(new CustomAuthenticationSuccessHandler());
//            c.failureHandler(new CustomAuthenticationFailureHandler());
//        });
        http.authenticationProvider(authenticationProvider);
//        http.authorizeHttpRequests(c -> c.anyRequest().authenticated());
//        http.authorizeHttpRequests(c -> c.anyRequest().hasAnyAuthority("write"));
        http.authorizeHttpRequests(c -> c.anyRequest().hasAnyRole("ADMIN", "STAFF"));
        http.authorizeHttpRequests(c -> c.requestMatchers("/hello").hasRole("ADMIN"));


        return http.build();
    }


}
