package com.project.springboard.api.note.controller;

import com.project.springboard.api.note.entities.Note;
import com.project.springboard.api.note.service.NoteService;
import com.project.springboard.common.BaseResponse;
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
    public ResponseEntity<Note> createNote(@RequestBody Note note) {
        Note createdNote = noteService.createOrUpdateNote(note);
        return ResponseEntity.ok(createdNote);
    }

    @GetMapping
    public BaseResponse<List<Note>> findMany() {
        List<Note> notes = noteService.findMany();
        return BaseResponse.success(notes);
    }
}
