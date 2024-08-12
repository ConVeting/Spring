package com.example.conveting.dto;

import com.example.conveting.entity.BoardEntity;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class BoardDTO {
    private Long id_post;
    private LocalDateTime w_time;
    private String photo_post;
    private String title_post;
    private String content_post;
    private String id_user;
    private int fileAttached; // 파일 첨부 여부(첨부 : 1 , 미첨부 0)

    public BoardDTO(Long id_post, LocalDateTime w_time, String title_post, String id_user) {
        this.id_post = id_post;
        this.w_time = w_time;
        this.title_post = title_post;
        this.id_user = id_user;
    }

    public static BoardDTO toBoardDTO(BoardEntity boardEntity) {
        BoardDTO boardDTO = new BoardDTO();
        boardDTO.setId_post(boardEntity.getId_post());
        boardDTO.setW_time(boardEntity.getW_time());
        boardDTO.setPhoto_post(boardEntity.getPhoto_post());
        boardDTO.setTitle_post(boardEntity.getTitle_post());
        boardDTO.setContent_post(boardEntity.getContent_post());
        boardDTO.setId_user(boardEntity.getId_user());
        boardEntity.setFileAttached(boardEntity.getFileAttached());
        return boardDTO;
    }
}
