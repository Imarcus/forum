package com.smack.forum.controller;

import com.smack.forum.exception.ResourceNotFoundException;
import com.smack.forum.model.ForumUser;
import com.smack.forum.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.StreamSupport;

@RestController()
@RequestMapping("/api")
public class UserController {

    private static final Logger LOG = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserRepository userRepository;

    @GetMapping(path = "/users")
    public @ResponseBody ArrayList<ForumUser> getUsers() {
        LOG.info("GET called on /users all");
        ArrayList<ForumUser> forumUserList = new ArrayList<>();
        userRepository.findAll().iterator().forEachRemaining(forumUserList::add);
        return forumUserList;
    }

    @GetMapping(path = "/users/{id}")
    public @ResponseBody ForumUser getUserById(@PathVariable(value = "id") Long id) {
        LOG.info("GET called on /users by id");
        return userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User", "id", id));
    }

    @PostMapping(path = "/users")
    public @ResponseBody ForumUser createUser(@RequestBody ForumUser forumUser) {
        return userRepository.save(forumUser);
    }

    @PostMapping(path = "/users/id")
    public @ResponseBody List<ForumUser> getUsersByIds(@RequestBody List<Long> ids) {
        ArrayList<ForumUser> userList = new ArrayList<>();
        userRepository.findAllById(ids).iterator().forEachRemaining(userList::add);
        return userList;
    }

    @GetMapping(path = "/login")
    public @ResponseBody ForumUser login(@RequestParam(value = "username") String username,
                                       @RequestParam(value = "password") String password) {
        return StreamSupport.stream(userRepository.findAll().spliterator(), false)
                .filter(forumUser -> forumUser.getName().equals(username) && forumUser.getPassword().equals(password))
                .findFirst().orElseThrow(() -> new ResourceNotFoundException("User", "name", username));
    }
}
