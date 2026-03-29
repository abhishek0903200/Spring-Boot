package com.demo.first.app.service;

import com.demo.first.app.controller.UserController;
import com.demo.first.app.exceptions.UserNotFoundException;
import com.demo.first.app.model.User;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserService {
    private Map<Integer, User> userdb = new HashMap<>();
    private final Logger logger = LoggerFactory.getLogger(UserService.class);

    public User createUser(User user) {
        logger.info("Creating user .....INFO");
        logger.debug("Creating user .....DEBUG");
        logger.trace("Creating user .....TRACE");
        logger.warn("Creating user .....WARN");
        logger.error("Creating user .....ERROR");
        System.out.println(user.getEmail());
        userdb.putIfAbsent(user.getId(),user);
//        return ResponseEntity.status(HttpStatus.CREATED)
//                .body(user);
        return user;
    }

    public User updateUser(User user) {
        if(!userdb.containsKey(user.getId()))
            throw new UserNotFoundException("User with ID "+ user.getId()+ " does not exist");
        userdb.put(user.getId(),user);
        return user;
    }

    public boolean deleteUser(int id) {
        if(!userdb.containsKey(id))
            throw new UserNotFoundException("User with ID "+ id+ " does not exist");
        userdb.remove(id);
        return true;
    }

    public List<User> getAllUsers() {
        if(!userdb.isEmpty())
            throw new NullPointerException("No users found in database");
        return new ArrayList<>(userdb.values());
    }

    public User getUserById(int id) {
        return userdb.get(id);
    }

    public List<User> searchUser(String name, String email) {
        return userdb.values().stream()
                .filter(a -> a.getName().equalsIgnoreCase(name))
                .filter(a -> a.getEmail().equalsIgnoreCase(email))
                .toList();
    }
}
