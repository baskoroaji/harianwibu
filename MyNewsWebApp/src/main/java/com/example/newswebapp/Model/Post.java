package com.example.newswebapp.Model;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Data
@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long PostId;
    private String PostName;
    @Builder.Default
    private Integer likeCounts = 0;
    @Lob
    private String image;
    @Lob
    private String content;
    @CreatedDate
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdDate;
    @Column(insertable = false)
    private LocalDateTime updatedDate;
    @ManyToOne
    @JoinColumn(name = "owner_id")
    private User owner;
    @OneToMany(mappedBy = "post", fetch = FetchType.EAGER)
    private List<Likes> likes;
    @OneToMany(mappedBy = "post", fetch = FetchType.LAZY)
    private List<Comment> comments;


}
