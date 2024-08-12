package com.example.conveting.service;

import com.example.conveting.dto.CommentDTO;
import com.example.conveting.entity.BoardEntity;
import com.example.conveting.entity.CommentEntity;
import com.example.conveting.entity.CommentId;
import com.example.conveting.repository.BoardRepository;
import com.example.conveting.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final BoardRepository boardRepository;
    public CommentId save(CommentDTO commentDTO) {
        Optional<BoardEntity> optionalBoardEntity = boardRepository.findById(commentDTO.getId_post());
        if(optionalBoardEntity.isPresent()){
            BoardEntity boardEntity = optionalBoardEntity.get();
            CommentEntity commentEntity = CommentEntity.toSaveEntity(commentDTO,boardEntity);
            CommentEntity savedEntity = commentRepository.save(commentEntity);
            return new CommentId(savedEntity.getNumComment(), savedEntity.getId_post());
        }else {
            return null;
        }

    }

    public List<CommentDTO> findAll(Long idPost) {
        BoardEntity boardEntity = boardRepository.findById(idPost).get();
        List<CommentEntity> commentEntityList = commentRepository.findAllByBoardEntityOrderByNumCommentDesc(boardEntity);
        /* EntityList -> DTOList */
        List<CommentDTO> commentDTOList = new ArrayList<>();
        for (CommentEntity commentEntity: commentEntityList) {
            CommentDTO commentDTO = CommentDTO.toCommentDTO(commentEntity, idPost);
            commentDTOList.add(commentDTO);
        }
        return commentDTOList;
    }
}
