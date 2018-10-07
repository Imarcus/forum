package com.smack.forum.repository;

import com.smack.forum.model.ForumUser;
import org.springframework.data.repository.CrudRepository;


public interface UserRepository extends CrudRepository<ForumUser, Long> {
}
