package com.techzen.techlearn.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Where;

import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Where(clause = "is_deleted = false")
@Table(name = "tbl_mentor")
public class MentorEntity {
    @Id
    private UUID id;

    @Column(nullable = false)
    private String name;

    @Column(length = 7)
    private String color;

    @Column(columnDefinition = "TEXT")
    private String avatar;

    @Column(name = "email")
    private String email;

    @Column(name = "is_deleted", nullable = false)
    private Boolean isDeleted;

    @ManyToMany
    @JoinTable(
            name = "tbl_chapter_mentor",
            joinColumns = @JoinColumn(name = "mentor_id"),
            inverseJoinColumns = @JoinColumn(name = "chapter_id")
    )
    private List<ChapterEntity> chapterEntities;

}
