package com.smack.forum.repository;

import com.smack.forum.model.Thread;
import org.springframework.data.repository.CrudRepository;

public interface ThreadRepository extends CrudRepository<Thread, Long> {
}
