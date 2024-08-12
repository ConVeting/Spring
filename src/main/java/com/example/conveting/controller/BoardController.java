package com.example.conveting.controller;

import com.example.conveting.dto.BoardDTO;
import com.example.conveting.dto.CommentDTO;
import com.example.conveting.service.BoardService;
import com.example.conveting.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardController {
    private final BoardService boardService;
    private final CommentService commentService;


    @GetMapping("/save")
    public String saveForm() {
        return "save";
    }

    @PostMapping
    public String save(@ModelAttribute BoardDTO boardDTO, @RequestParam("file") MultipartFile file) throws IOException {
        boardService.save(boardDTO, file);
        return "redirect:/board/";
    }

    @GetMapping("/")
    public String findAll(Model model, @RequestParam(defaultValue = "0") int page) {
        Pageable pageable = PageRequest.of(page, 10);
        Page<BoardDTO> boardDTOPage = boardService.getBoardList(pageable);
        model.addAttribute("boardPage", boardDTOPage);
        return "list";
    }

    @GetMapping("/{id_post}")
    public String findbyId(@PathVariable Long id_post, Model model, @PageableDefault(page = 1) Pageable pageable) {
        BoardDTO boardDTO = boardService.findbyId(id_post);
        List<CommentDTO> commentDTOList = commentService.findAll(id_post);
        commentService.findAll(id_post);
        model.addAttribute("commentList", commentDTOList);
        model.addAttribute("board", boardDTO);
        model.addAttribute("page", pageable.getPageNumber());
        return "detail";
    }

    @GetMapping("/update/{id}")
    public String updateForm(@PathVariable Long id_post, Model model) {
        BoardDTO boardDTO = boardService.findbyId(id_post);
        model.addAttribute("boardUpdate", boardDTO);
        return "update";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute BoardDTO boardDTO, @RequestParam("file") MultipartFile file, Model model) throws IOException {
        BoardDTO updatedBoard = boardService.update(boardDTO, file);
        model.addAttribute("board", updatedBoard);
        return "detail";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id_post) {
        boardService.delete(id_post);
        return "redirect:/board/";
    }

    @GetMapping("/paging")
    public String paging(@PageableDefault(page = 0, size = 10) Pageable pageable, Model model) {
        Page<BoardDTO> boardList = boardService.paging(pageable);

        int blockLimit = 7;
        int currentPage = pageable.getPageNumber() + 1;  // 페이지는 0부터 시작하므로 1을 더함
        int startPage = ((currentPage - 1) / blockLimit) * blockLimit + 1; // 블록 내 시작 페이지
        int endPage = Math.min(startPage + blockLimit - 1, boardList.getTotalPages());

        model.addAttribute("boardList", boardList);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        model.addAttribute("currentPage", currentPage);

        return "paging";
    }
}
