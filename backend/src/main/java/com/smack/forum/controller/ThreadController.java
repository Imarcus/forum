package com.smack.forum.controller;

import com.smack.forum.exception.ResourceNotFoundException;
import com.smack.forum.model.Board;
import com.smack.forum.repository.BoardRepository;
import com.smack.forum.repository.ThreadRepository;
import com.smack.forum.model.Post;
import com.smack.forum.model.Thread;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController()
@RequestMapping("/api")
public class ThreadController {

    private static final Logger LOG = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private ThreadRepository threadRepository;
    @Autowired
    private BoardRepository boardRepository;

    @GetMapping(path = "/threads")
    public @ResponseBody ArrayList<Thread> getThreads() {
        LOG.info("GET called on /threads all");
        ArrayList<Thread> forumThreadList = new ArrayList<>();
        threadRepository.findAll().iterator().forEachRemaining(forumThreadList::add);
        return forumThreadList;
    }

    @GetMapping(path = "/threads/{id}")
    public @ResponseBody Thread getThreadById(@PathVariable(value = "id") Long id) {
        LOG.info("GET called on /threads by id");
        return threadRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Thread", "id", id));
    }

    @PostMapping(path = "/threads")
    public @ResponseBody Thread createThread(@RequestBody Thread thread, @RequestParam Long boardId) {
        Board board = boardRepository.findById(boardId)
                .orElseThrow(() -> new ResourceNotFoundException("Board", "id", boardId));
        thread = threadRepository.save(thread);
        board.addThreadId(thread.getId());
        boardRepository.save(board);
        return thread;
    }

    @PatchMapping(path = "threads/{id}")
    public @ResponseBody Thread putAnswer(@PathVariable(value = "id") Long id, @RequestBody Post answer) {
        Thread thread = threadRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", id));
        thread.addAnswer(answer);
        threadRepository.save(thread);
        return thread;
    }

    @PostMapping(path = "/threads/ids")
    public @ResponseBody List<Thread> getThreadsByIds(@RequestBody  List<Long> ids) {
        ArrayList<Thread> forumThreadList = new ArrayList<>();
        threadRepository.findAllById(ids).iterator().forEachRemaining(forumThreadList::add);
        return forumThreadList;
    }

    @GetMapping(path = "/threads/boardId/{id}")
    public @ResponseBody List<Thread> getThreadsByBoardId(@PathVariable(value ="id") Long boardId) {
        Board board = boardRepository.findById(boardId).orElseThrow(() -> new ResourceNotFoundException("Board", "id", boardId));
        ArrayList<Thread> forumThreadList = new ArrayList<>();
        threadRepository.findAllById(board.getThreadIds()).iterator().forEachRemaining(forumThreadList::add);
        return forumThreadList;
    }
}
