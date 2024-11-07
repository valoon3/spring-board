package com.project.springboard.api.note.entities;

import com.project.springboard.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(
        name = "note_comment"
//        uniqueConstraints = {
//                @UniqueConstraint(
//                        name = "UNIQUE_COMMENT_TITLE",
//                        columnNames = {"title"}
//                )
//        }
)
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class NoteComment extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commentId;

    @Column(nullable = false, length = 256)
    private String title;

    @Column(nullable = false) // columnDefinition = "varchar(100) default 'default value'"
    private String content;

}
