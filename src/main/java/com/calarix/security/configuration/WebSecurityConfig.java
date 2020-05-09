package com.calarix.security.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.thymeleaf.extras.springsecurity5.dialect.SpringSecurityDialect;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    public final static String ROL_ADMIN = "ADMIN";
    public final static String ROL_INVITADO = "INVITADO";

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                    /*.antMatchers("/", "/home").permitAll()*/
                    .antMatchers("/", "/home", "/users", "users/show").hasAnyRole("ADMIN", "INVITADO")
                    .antMatchers("/users/new", "users/remove").hasAnyRole("ADMIN")
                    .anyRequest().authenticated()
                .and()
                .formLogin()
                /*.loginPage("/login") -> usnado el login por defecto de spring secruty*/
                    .permitAll()
                .and()
                .logout()
                    .permitAll();
    }

    /*
    @Bean
    @Override
    public UserDetailsService userDetailsService() {
        UserDetails user =
                User.withDefaultPasswordEncoder()
                        .username("admin")
                        .password("admin")
                        .roles("ADMIN")
                        .build();
        UserDetails user2 =
                User.withDefaultPasswordEncoder()
                        .username("invitado")
                        .password("invitado")
                        .roles("INVITADO")
                        .build();

        return new InMemoryUserDetailsManager(user, user2);
    }*/

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("admin")
                    .password(passwordEncoder().encode("admin"))
                    .roles(ROL_ADMIN)
                .and()
                .withUser("invitado")
                    .password(passwordEncoder().encode("invitado"))
                    .roles(ROL_INVITADO);
    }
    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    /**
     * Dialect for SPring Secutiry tag in html page
     * @return
     */
    @Bean
    public SpringSecurityDialect springSecurityDialect(){
        return new SpringSecurityDialect();
    }

}
