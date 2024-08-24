package com.BJJ.BJJSite.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Controller responsible for handling requests to the home page.
 */
@Controller
public class HomePageController {

    /**
     * Handles GET requests to the root URL ("/").
     * 
     * This method returns the `index.html` file, which is the main entry point for the Angular application.
     * 
     * @return The name of the HTML file to be served.
     */
    @GetMapping("/")
    public String home() {
        return "index.html";
    }
}
