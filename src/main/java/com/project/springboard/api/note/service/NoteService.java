package com.project.springboard.api.note.service;

import com.project.springboard.api.note.entities.NoteEntity;
import com.project.springboard.api.note.repository.NoteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class NoteService {
    private final NoteRepository noteRepository;

    public void hello() {
        System.out.println("hello world");
    }

    // 글쓰기 (게시물 생성) 기능
    public NoteEntity createOrUpdateNote(NoteEntity noteEntity) {
        if (noteEntity.getId() == null) {
            // 새로운 게시물 작성 시 createdAt, updatedAt 설정
            noteEntity.setCreatedAt(LocalDateTime.now());
            noteEntity.setUpdatedAt(LocalDateTime.now());
        } else {
            // 기존 게시물 수정 시 updatedAt만 갱신
            noteEntity.setUpdatedAt(LocalDateTime.now());
        }
        return noteRepository.save(noteEntity);
    }

    // 전체 글 조회
    public List<NoteEntity> findMany() {
        return noteRepository.findAll();
    }
}
