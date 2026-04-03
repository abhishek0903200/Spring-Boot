package com.demo.first.app.controller;

import com.demo.first.app.model.User;
import com.demo.first.app.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    private UserService userService ;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<User> createdUser(@RequestBody User user){
        User createdUser = userService.createUser(user);
        return new ResponseEntity<>(createdUser,HttpStatus.CREATED);
    }

    @PutMapping
     public ResponseEntity<User> updatedUser(@RequestBody User user){
        User updated = userService.updateUser(user);
        if(updated == null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        return ResponseEntity.status(HttpStatus.OK).body(updated) ;
        //return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletedUser(@PathVariable int id){
        boolean isDeleted = userService.deleteUser(id);
        if(!isDeleted)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build() ;
        return ResponseEntity.ok("User Deleted");
    }

    @GetMapping
    public List<User> getUser(){
        return userService.getAllUsers() ;
    }
    // /user/1
    @GetMapping("/{userid}")
    public ResponseEntity<User> getUser(
            @PathVariable(value = "userid", required = false) int id){
        User user = userService.getUserById(id);
        if(user == null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build() ;
        return ResponseEntity.ok(user);
    }

    @GetMapping("/{userid}/order/{orderid}")
    public ResponseEntity<User> getUserOrder(@PathVariable("userid") int id,
                                             @PathVariable int orderid){
        User user = userService.getUserById(id);
        if(user == null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build() ;
        return ResponseEntity.ok(user);
    }

    // search ?name=abhi
    @GetMapping("/search")
    public ResponseEntity<List<User>> searchUser(
            @RequestParam(required = false, defaultValue = "achal") String name,
            @RequestParam(required = false, defaultValue = "email") String email
            ){
        return ResponseEntity.ok(userService.searchUsers(name,email));
    }

    @GetMapping("/info{id}")
    public String getInfo(
            @PathVariable int id,
            @RequestParam String name,
            @RequestHeader("User-Agent") String userAgent){
        return "User Agent: "+ userAgent
                +" : "+ id
                +" : "+ name;
    }


}
