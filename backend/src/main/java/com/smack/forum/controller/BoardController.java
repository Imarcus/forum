package com.smack.forum.controller;

import com.smack.forum.exception.ResourceNotFoundException;
import com.smack.forum.model.Board;
import com.smack.forum.repository.BoardRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController()
@RequestMapping("/api")
public class BoardController {

    private static final Logger LOG = LoggerFactory.getLogger(BoardController.class);

    @Autowired
    private BoardRepository boardRepository;

    @GetMapping(path = "/boards")
    public @ResponseBody ArrayList<Board> getBoards() {
        LOG.info("GET called on /boards all");
        ArrayList<Board> boardList = new ArrayList<>();
        boardRepository.findAll().iterator().forEachRemaining(boardList::add);
        return  boardList;
    }

    @GetMapping(path = "/boards/{id}")
    public @ResponseBody Board getBoardById(@PathVariable(value = "id") Long id) {
        LOG.info("GET called on /boards by id");
        return boardRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Board", "id", id));
    }

    @PostMapping(path = "/boards")
    public @ResponseBody Board createBoard(@RequestBody Board board) {
        return boardRepository.save(board);
    }

    @PostMapping(path = "/boards/ids")
    public @ResponseBody List<Board> getBoardsByIds(@RequestBody List<Long> ids) {
        ArrayList<Board> boardList = new ArrayList<>();
        boardRepository.findAllById(ids).iterator().forEachRemaining(boardList::add);
        return boardList;
    }
}