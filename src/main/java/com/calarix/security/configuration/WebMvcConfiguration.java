package com.calarix.security.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfiguration implements WebMvcConfigurer {

    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/home").setViewName("home");

        registry.addViewController("/users").setViewName("users");
        registry.addViewController("/users/new").setViewName("newUser");
        registry.addViewController("/users/show").setViewName("showUser");

        registry.addViewController("/login").setViewName("login");

        registry.addViewController("/").setViewName("home");
    }

}
