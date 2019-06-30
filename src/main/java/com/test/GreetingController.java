package com.test;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;

import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
public class GreetingController {

    private static final String TEMPLATE = "Hello, %s!";

    @RequestMapping("/greeting/{dept}")
    public HttpEntity<Greeting> greeting(
            @RequestParam(value = "name", required = false, defaultValue = "World") String name,@PathVariable(name="dept") String dept) {

        Greeting greeting = new Greeting(String.format(TEMPLATE, name));

        System.out.println("I have added links :  " + greeting.hasLinks());

        greeting.add(linkTo(methodOn(GreetingController.class).greeting(name,dept)).withSelfRel());
        greeting.add(linkTo(methodOn(GreetingController.class).greeting(name,dept)).withRel("dept"));

        System.out.println("I have added links :  " +greeting.hasLinks());


        return new ResponseEntity<>(greeting, HttpStatus.OK);
    }
}