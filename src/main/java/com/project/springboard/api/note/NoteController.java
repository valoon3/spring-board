package com.project.springboard.api.note;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NoteController {

    @GetMapping("/hello")
    public String hello() {
        System.out.println("hello world");
        return "hello world";
    }
}
