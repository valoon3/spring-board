package com.project.springboard.api.note.controller;

import com.project.springboard.api.note.service.NoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class NoteController {

    private final NoteService noteService;

    @GetMapping("/hello")
    public String hello() {
        noteService.hello();
//        System.out.println("hello world");
        return "hello world";
    }
}
