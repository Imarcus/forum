package com.smack.forum;

import com.smack.forum.model.*;
import com.smack.forum.model.Thread;
import com.smack.forum.repository.BoardRepository;
import com.smack.forum.repository.ThreadRepository;
import com.smack.forum.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.Date;

@SpringBootApplication
@EnableJpaAuditing
public class ForumApplication implements CommandLineRunner {

	private Logger LOG = LoggerFactory.getLogger(ForumApplication.class);

	private final BoardRepository boardRepository;
	private final ThreadRepository threadRepository;
	private final UserRepository userRepository;

	@Autowired
	public ForumApplication(BoardRepository boardRepository,
							ThreadRepository threadRepository,
							UserRepository userRepository) {
		this.boardRepository = boardRepository;
		this.threadRepository = threadRepository;
		this.userRepository = userRepository;
	}

	public static void main(String[] args) {
		SpringApplication.run(ForumApplication.class, args);
	}


	@Override
	public void run(String... args) throws Exception {
		boardRepository.save(new Board("Test board"));

		userRepository.save(new ForumUser("Majvor", "mavjor"));
		userRepository.save(new ForumUser("Elva", "elva"));
		userRepository.save(new ForumUser("Tolv", "tolv"));

		MainPost mainPost1 = new MainPost("Hej, detta är ett test", 2L, new Date(), "Jag behöver hjälp");
		Thread thread1 = new Thread(mainPost1);
		Post post1 = new Post("läget?", 3L, new Date());
		thread1.getAnswers().add(post1);
		threadRepository.save(thread1);

		LOG.info("User count in DB: {}", userRepository.count());
		LOG.info("User with ID 1: {}", userRepository.findById(1L));
		LOG.info("User with ID 2: {}", userRepository.findById(2L));
		LOG.info("User with ID 3: {}", userRepository.findById(3L));
	}
}
