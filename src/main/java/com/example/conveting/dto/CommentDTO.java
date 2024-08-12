package com.example.conveting.dto;

import com.example.conveting.entity.CommentEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class CommentDTO {
    private Long numComment;
    private Long id_post;

    private String content_comment;

    private int likes;

    private String idUser;



    public static CommentDTO toCommentDTO(CommentEntity commentEntity, Long idPost) {
        CommentDTO commentDTO = new CommentDTO();
        commentDTO.setNumComment(commentEntity.getNumComment());
        commentDTO.setId_post(commentEntity.getId_post());
        commentDTO.setContent_comment(commentEntity.getContent_comment());
        commentDTO.setLikes(commentEntity.getLikes());
        commentDTO.setIdUser(commentEntity.getIdUser());
        return  commentDTO;
    }
}
