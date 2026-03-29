package com.demo.first.app;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserService {
    private Map<Integer,User> userdb = new HashMap<>();

    public User createUser(User user) {
        userdb.putIfAbsent(user.getId(),user);
//        return ResponseEntity.status(HttpStatus.CREATED)
//                .body(user);
        return user;
    }

    public User updateUser(User user) {
        if(!userdb.containsKey(user.getId()))
            throw new IllegalArgumentException("User with ID "+ user.getId()+ " does not exist");
        userdb.put(user.getId(),user);
        return user;
    }

    public boolean deleteUser(int id) {
        if(!userdb.containsKey(id))
            return false ;
        userdb.remove(id);
        return true;
    }

    public List<User> getAllUsers() {
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
