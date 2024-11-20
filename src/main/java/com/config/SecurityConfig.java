package com.config;

import com.services.impl.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Autowired
    private CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler;

    @Autowired
    private CustomUserDetailsService customUserDetailService;


 	@Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring()
                .antMatchers("/favicon.ico","/images/**","/stylesheet/**", "/script/**"); // Ignore JSP and static resources
    }

 	@Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
         http
                 .csrf().disable()
                 .authorizeRequests(requests -> requests
						 .antMatchers("/admin/**").hasRole("ADMIN")
						 .antMatchers("/user/**").hasAnyRole("ADMIN", "NORMAL")
                         .antMatchers("/api/**").permitAll()//.hasAnyRole("ADMIN", "USER")
						 .antMatchers("/home","/login", "/register").permitAll() // Allow public access to login and static resources
						 .anyRequest().authenticated())
                 //.formLogin(Customizer.withDefaults())
                 .formLogin(form -> form
                         .loginPage("/login")          // Custom login page
                         //.loginProcessingUrl("/login")   // URL for processing login (providing default login processing)
                         .failureUrl("/login?error=true") // Redirect to login page with an error parameter
                         .successHandler(customAuthenticationSuccessHandler)  // Use custom success handler
                         .permitAll())
                 .logout(logout -> logout
                         .logoutUrl("/logout")
                         .permitAll())
                .sessionManagement(session -> session
                .maximumSessions(1) // Allow only one session per user
                .expiredUrl("/login?expired=true")); // Redirect when session expires

        return http.build();
    }

    @Bean
    public DaoAuthenticationProvider authProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(customUserDetailService);
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

 	 // In-memory authentication (for testing)
    /*@Bean
    public UserDetailsService userDetailsService() {
        UserDetails normalUser = User.builder()
                .username("Rahul")
                .password(passwordEncoder().encode("User123"))
                .roles("USER")
                .build();

        UserDetails adminUser = User.builder()
                .username("admin")
                .password(passwordEncoder().encode("Admin123"))
                .roles("ADMIN")
                .build();

        return new InMemoryUserDetailsManager(normalUser, adminUser);
    }*/

}
