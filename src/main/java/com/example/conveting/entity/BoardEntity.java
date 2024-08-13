package com.example.conveting.entity;

import com.example.conveting.dto.BoardDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "post")
public class BoardEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_post;

    @Column(nullable = false)
    private LocalDateTime w_time;

    @Column(length = 255)
    private String photo_post;

    @Column(length = 255, nullable = false)
    private String title_post;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content_post;

    @Column(length = 30, nullable = false)
    private String id_user;

    @Column
    private int fileAttached;

    @OneToMany(mappedBy = "boardEntity", cascade = CascadeType.REMOVE, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<CommentEntity> commentEntityList = new ArrayList<>();
    public static BoardEntity toSaveEntity(BoardDTO boardDTO, String filePath) {
        BoardEntity boardEntity = new BoardEntity();
        boardEntity.setW_time(boardDTO.getW_time());
        boardEntity.setPhoto_post(filePath);
        boardEntity.setTitle_post(boardDTO.getTitle_post());
        boardEntity.setContent_post(boardDTO.getContent_post());
        boardEntity.setId_user(boardDTO.getId_user());
        boardEntity.setFileAttached(filePath != null ? 1 : 0); //첨부파일 없음.
        return boardEntity;
    }

    public static BoardEntity toUpdateEntity(BoardDTO boardDTO, String filePath) {
        BoardEntity boardEntity = new BoardEntity();
        boardEntity.setId_post(boardDTO.getId_post());
        boardEntity.setW_time(boardDTO.getW_time());
        boardEntity.setPhoto_post(filePath); // 파일 경로 저장
        boardEntity.setTitle_post(boardDTO.getTitle_post());
        boardEntity.setContent_post(boardDTO.getContent_post());
        boardEntity.setId_user(boardDTO.getId_user());
        boardEntity.setFileAttached(filePath != null ? 1 : 0); // 파일이 있으면 1, 없으면 0
        return boardEntity;
    }
}
