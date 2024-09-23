package com.BJJ.BJJSite.Config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Configuration class for setting up web-related configurations such as CORS, view controllers, and resource handlers.
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    /**
     * Configures view controllers to redirect requests to the home page, allowing Angular to handle routing.
     * 
     * This method forwards requests to the root URL ("/") to `index.html`, which is served by Angular.
     * 
     * @param registry The view controller registry.
     */
    @SuppressWarnings("null")
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        // Forward to home page so Angular handles routing
        registry.addViewController("/").setViewName("forward:/index.html");
    }

    /**
     * Configures resource handlers for serving static resources.
     * 
     * This method maps requests to `/static/**` to the `classpath:/static/` directory, allowing the application 
     * to serve static content such as images, CSS, and JavaScript files.
     * 
     * @param registry The resource handler registry.
     */
    @SuppressWarnings("null")
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**")
                .addResourceLocations("classpath:/static/");
    }
}
