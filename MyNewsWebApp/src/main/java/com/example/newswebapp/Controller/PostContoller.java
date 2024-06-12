package com.example.newswebapp.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/posts")
public class PostContoller {

    @GetMapping("/all")
    public String viewAllPost(HttpSession session) {
        session.setAttribute("key", "value");
        return "Hello There";
    }
    @GetMapping("/{id}")
    public String viewAllPost(@RequestBody String id) {
        return "Hello There";
    }
    
    

}
