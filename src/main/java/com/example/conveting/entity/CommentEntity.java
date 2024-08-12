package com.example.conveting.entity;

import com.example.conveting.dto.CommentDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@IdClass(CommentId.class)
@Getter
@Setter
@Table(name = "Comment")
public class CommentEntity {
    @Id
    @Column(name = "num_comment")
    private Long numComment;

    @Id
    private Long id_post;

    @Column(nullable = false)
    private String content_comment;

    private int likes;

    @Column(nullable = false)
    private String idUser;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id")
    private BoardEntity boardEntity;

    public static CommentEntity toSaveEntity(CommentDTO commentDTO, BoardEntity boardEntity) {
        CommentEntity commentEntity = new CommentEntity();
        commentEntity.setNumComment(commentDTO.getNumComment()); // 수동으로 설정
        commentEntity.setId_post(commentDTO.getId_post());
        commentEntity.setContent_comment(commentDTO.getContent_comment());
        commentEntity.setLikes(0);
        commentEntity.setIdUser(commentDTO.getIdUser());
        commentEntity.setBoardEntity(boardEntity);
        return commentEntity;
    }
}
