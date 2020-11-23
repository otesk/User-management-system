package com.otesk.ums.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Controller for processing actions of all users.
 */
@Controller
public class MainController {

    /**
     * Displays the start page.
     *
     * @return starting page
     */
    @GetMapping
    public String getStartPage(){
        return "start";
    }

}
