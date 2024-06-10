package com.example.newswebapp.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/posts")
public class PostContoller {

    @GetMapping
    public String viewAllPost() {
        return "Hello There";
    }
    @GetMapping("/{id}")
    public String viewAllPost(@RequestBody String id) {
        return "Hello There";
    }
    
    

}
