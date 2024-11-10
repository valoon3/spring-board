package com.project.springboard.api.note.service;

import com.project.springboard.api.note.entities.Note;
import com.project.springboard.api.note.repository.NoteRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
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
    public Note createOrUpdateNote(Note note) {
        if (note.getId() == null) {
            // 새로운 게시물 작성 시 createdAt, updatedAt 설정
            note.setCreatedAt(LocalDateTime.now());
            note.setUpdatedAt(LocalDateTime.now());
        } else {
            // 기존 게시물 수정 시 updatedAt만 갱신
            note.setUpdatedAt(LocalDateTime.now());
        }
        return noteRepository.save(note);
    }

    // 전체 글 조회
    public List<Note> findMany() {
        return noteRepository.findAll();
    }

    public void test() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("com.project.springboard.api.note.entities");
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();
    }
}
