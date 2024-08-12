package com.example.conveting.service;

import com.example.conveting.dto.BoardDTO;
import com.example.conveting.entity.BoardEntity;
import com.example.conveting.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;

    public Long save(BoardDTO boardDTO, MultipartFile file) throws IOException {
        String filePath = null;
        if (!file.isEmpty()) {
            // 파일 저장 로직
            String originalFilename = file.getOriginalFilename();
            String storedFileName = UUID.randomUUID().toString() + "_" + originalFilename;
            String savePath = "E:/conveting_img/" + storedFileName; // 서버 디렉토리 경로
            file.transferTo(new File(savePath));
            filePath = savePath;
        }

        // BoardEntity 생성 및 저장
        BoardEntity boardEntity = BoardEntity.toSaveEntity(boardDTO, filePath);
        return boardRepository.save(boardEntity).getId_post();
    }

    public List<BoardDTO> findAll() {
        List<BoardEntity> boardEntityList = boardRepository.findAll();
        return boardEntityList.stream()
                .map(BoardDTO::toBoardDTO)
                .collect(Collectors.toList());
    }

    public Page<BoardDTO> getBoardList(Pageable pageable) {
        Page<BoardEntity> boardEntities = boardRepository.findAll(pageable);
        return boardEntities.map(BoardDTO::toBoardDTO);
    }

    public BoardDTO findbyId(Long id_post) {
        Optional<BoardEntity> optionalBoardEntity = boardRepository.findById(id_post);
        if(optionalBoardEntity.isPresent()) {
            BoardEntity boardEntity = optionalBoardEntity.get();
            BoardDTO boardDTO = BoardDTO.toBoardDTO(boardEntity);
            return boardDTO;
        } else {
            return null;
        }
    }

    public BoardDTO update(BoardDTO boardDTO, MultipartFile file) throws IOException {
        Optional<BoardEntity> optionalBoardEntity = boardRepository.findById(boardDTO.getId_post());

        if (optionalBoardEntity.isPresent()) {
            BoardEntity boardEntity = optionalBoardEntity.get();

            String filePath = boardEntity.getPhoto_post(); // 기존 파일 경로 유지

            if (!file.isEmpty()) {
                // 기존 파일 삭제
                if (filePath != null) {
                    File oldFile = new File(filePath);
                    if (oldFile.exists()) {
                        oldFile.delete();
                    }
                }

                // 새 파일 저장 로직
                String originalFilename = file.getOriginalFilename();
                String storedFileName = UUID.randomUUID().toString() + "_" + originalFilename;
                String savePath = "E:/conveting_img/" + storedFileName; // 서버 디렉토리 경로
                file.transferTo(new File(savePath));
                filePath = savePath;
            }

            // BoardEntity 업데이트
            BoardEntity updatedEntity = BoardEntity.toUpdateEntity(boardDTO, filePath);
            boardRepository.save(updatedEntity);
            return BoardDTO.toBoardDTO(updatedEntity);
        } else {
            return null;
        }
    }

    public void delete(Long id_post) {
        Optional<BoardEntity> optionalBoardEntity = boardRepository.findById(id_post);
        if (optionalBoardEntity.isPresent()) {
            BoardEntity boardEntity = optionalBoardEntity.get();
            String filePath = boardEntity.getPhoto_post();

            // 파일 삭제
            if (filePath != null) {
                File file = new File(filePath);
                if (file.exists()) {
                    file.delete();
                }
            }

            // 엔티티 삭제
            boardRepository.deleteById(id_post);
        }
    }
    public Page<BoardDTO> paging(Pageable pageable) {
        int page = pageable.getPageNumber() -1;
        int pageLimit = 20;  // 한 페이지에 보여줄 글 갯수
        Page<BoardEntity> boardEntities =
                boardRepository.findAll(PageRequest.of(page, pageLimit, Sort.by(Sort.Direction.DESC, "id")));

        Page<BoardDTO> boardDTOS = boardEntities.map(board-> new BoardDTO(board.getId_post(), board.getW_time(), board.getTitle_post(), board.getId_user()));
        return boardDTOS;
    }
}

