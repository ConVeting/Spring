package com.example.conveting.entity;

import java.io.Serializable;
import java.util.Objects;

public class CommentId implements Serializable {

    private Long numComment;
    private Long id_post;


    // 기본 생성자
    public CommentId() {}

    public Long getNumComment() {
        return numComment;
    }

    public void setNumComment(Long num_comment) {
        this.numComment = num_comment;
    }

    public Long getId_post() {
        return id_post;
    }

    public void setId_post(Long id_post) {
        this.id_post = id_post;
    }

    // 생성자
    public CommentId(Long numComment, Long id_post) {
        this.numComment = numComment;
        this.id_post = id_post;
    }

    // equals와 hashCode 구현 (필수)
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CommentId commentId = (CommentId) o;
        return Objects.equals(numComment, commentId.numComment) &&
                Objects.equals(id_post, commentId.id_post);
    }

    @Override
    public int hashCode() {
        return Objects.hash(numComment, id_post);
    }
}
