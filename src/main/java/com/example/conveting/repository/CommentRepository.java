package com.example.conveting.repository;

import com.example.conveting.entity.BoardEntity;
import com.example.conveting.entity.CommentEntity;
import com.example.conveting.entity.CommentId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<CommentEntity, CommentId> {
    List<CommentEntity> findAllByBoardEntityOrderByNumCommentDesc(BoardEntity boardEntity);
}
