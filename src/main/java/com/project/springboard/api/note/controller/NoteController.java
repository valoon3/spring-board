package com.project.springboard.api.note.controller;

import com.project.springboard.api.note.entities.NoteEntity;
import com.project.springboard.api.note.service.NoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/note")
public class NoteController {

    private final NoteService noteService;

    // 글쓰기 (게시물 생성) 기능
    @PostMapping
    public ResponseEntity<NoteEntity> createNote(@RequestBody NoteEntity noteEntity) {
        NoteEntity createdNote = noteService.createOrUpdateNote(noteEntity);
        return ResponseEntity.ok(createdNote);
    }

    @GetMapping
    public ResponseEntity<List<NoteEntity>> findMany() {
        List<NoteEntity> notes = noteService.findMany();
        return ResponseEntity.ok(notes);
    }
}
