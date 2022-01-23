package ru.job4j.forum.services;

import org.springframework.stereotype.Service;
import ru.job4j.forum.Security;
import ru.job4j.forum.models.User;
import ru.job4j.forum.models.net.Authentication;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class UserService {

	private final Map<String, User> users = new HashMap<>();
	private final AtomicInteger generator = new AtomicInteger(0);

	public boolean addUser(User value) {
		value.setId(generator.incrementAndGet());
		return users.putIfAbsent(value.getEmail(), value) == null;
	}

	public User login(Authentication auth) {
		User result = users.get(auth.getLogin());
		return
			result != null
			&& result.getPassword().equals(Security.getSHA1(auth.getPassword()))
			? result : null;
	}
}
