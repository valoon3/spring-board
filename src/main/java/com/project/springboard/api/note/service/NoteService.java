package com.project.springboard.api.note.service;

import com.project.springboard.api.note.repository.NoteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NoteService {
    private final NoteRepository noteRepository;

    public void hello() {
        System.out.println("hello world");
    }
}
