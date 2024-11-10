package com.project.springboard.api.note.entities;

import com.project.springboard.api.member.entities.Member;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "note")
@Data // @Getter, @Setter, @ToString, @EqualsAndHashCode, @RequiredArgsConstructor를 포함
@NoArgsConstructor // 기본 생성자 생성
@AllArgsConstructor // 모든 필드를 매개변수로 받는 생성자 생성
public class NoteEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(nullable = false)
    private LocalDateTime updatedAt;

    @ManyToOne
    private Member author;

    public NoteEntity(String title, String content, Member author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }

    public static NoteEntity createNote(String title, String content, Member author) {
        return new NoteEntity(
                title,
                content,
                author
        );
    }

    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }

}
