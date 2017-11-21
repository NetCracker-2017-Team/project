package com.github.habiteria.web.controller;

import com.github.habiteria.integration.controller.RootController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

/**
 * @author Alex Ivchenko
 */
@Controller
public class WelcomeController {
    @RequestMapping("/")
    public String index() {
        return "index";
    }

    @ModelAttribute("api")
    public String api() {
        return linkTo(methodOn(RootController.class).api()).toUri().toASCIIString();
    }
}
