package spring.rest.test.demo.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import spring.rest.test.demo.models.Greeting;

@RestController
public class GreetingController {

    @GetMapping("/greeting")
    public Greeting Greet(){
        return new Greeting("Hello World");
    }

}
