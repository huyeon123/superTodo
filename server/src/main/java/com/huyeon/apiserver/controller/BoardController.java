package com.huyeon.apiserver.controller;

import com.huyeon.apiserver.model.UserDetailsImpl;
import com.huyeon.apiserver.model.dto.ResMessage;
import com.huyeon.apiserver.model.entity.Board;
import com.huyeon.apiserver.service.BoardService;
import com.huyeon.apiserver.support.JsonParse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/board")
@RequiredArgsConstructor
public class BoardController {
    private final BoardService boardService;

    @GetMapping("/{id}")
    public String getBoard(@PathVariable Long id) {
        Optional<Board> board = boardService.getBoard(id);
        if (board.isPresent()) {
            return JsonParse.writeJson(board.get());
        }
        return "게시글을 찾을 수 없습니다.";
    }

    @PostMapping
    public ResponseEntity<ResMessage> writeBoard(
            @AuthenticationPrincipal UserDetailsImpl userDetails,
            @RequestBody Board newBoard) {
        newBoard.setUserEmail(userDetails.getUsername());
        ResMessage response = new ResMessage();
        if (boardService.writeBoard(newBoard)) {
            response.setMessage("게시글이 생성되었습니다.");
            response.setSuccess(true);
        } else response.setMessage("게시글을 작성할 수 없습니다.");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public String post(@PathVariable Long id, @RequestBody String editBoard) {
        if (boardService.editBoard(id, editBoard)) return "게시글을 수정했습니다.";
        return "게시글을 수정할 수 없습니다.";
    }

    @DeleteMapping("/{id}")
    public String removeBoard(@PathVariable Long id) {
        if (boardService.removeBoard(id)) return "게시글을 삭제했습니다.";
        return "게시글을 삭제할 수 없습니다.";
    }
}
