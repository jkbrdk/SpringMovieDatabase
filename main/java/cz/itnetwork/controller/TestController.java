package cz.itnetwork.controller;

import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Profile("test")
public class TestController {

    @GetMapping
    public String getHello(){
        return "Hello test!";
    }

}
