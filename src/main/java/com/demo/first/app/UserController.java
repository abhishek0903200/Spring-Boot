package com.demo.first.app;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {
    private Map<Integer,User> userdb = new HashMap<>();

    @PostMapping
    public ResponseEntity<User> createdUser(@RequestBody User user){
        userdb.putIfAbsent(user.getId(),user);
//        return ResponseEntity.status(HttpStatus.CREATED)
//                .body(user);
        return new ResponseEntity<>(user,HttpStatus.CREATED);
    }

    @PutMapping
     public ResponseEntity<User> updatedUser(@RequestBody User user){
        if(!userdb.containsKey(user.getId()))
        //    return ResponseEntity.notFound().build();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build() ;
            userdb.put(user.getId(),user);
        return ResponseEntity.status(HttpStatus.OK).body(user) ;
        //return ResponseEntity.ok(user);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletedUser(@PathVariable int id){
        if(!userdb.containsKey(id))
            //    return ResponseEntity.notFound().build();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build() ;
        userdb.remove(id);
        return ResponseEntity.ok("User Deleted");
    }

    @GetMapping
    public List<User> getUser(){
        return new ArrayList<>(userdb.values());
    }
    // /user/1
    @GetMapping("/{userid}")
    public ResponseEntity<User> getUser(
            @PathVariable(value = "userid", required = false) int id){
        if(!userdb.containsKey(id))
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build() ;
        return ResponseEntity.ok(userdb.get(id));
    }

    @GetMapping("/{userid}/order/{orderid}")
    public ResponseEntity<User> getUserOrder(@PathVariable("userid") int id,
                                             @PathVariable int orderid){
        System.out.println("ORDER ID: "+ orderid);
        if(!userdb.containsKey(orderid))
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build() ;
        return ResponseEntity.ok(userdb.get(orderid));
    }

    // search ?name=abhi
    @GetMapping("/search")
    public ResponseEntity<List<User>> searchUser(
            @RequestParam(required = false, defaultValue = "achal") String name,
            @RequestParam(required = false, defaultValue = "email") String email
        ){
        System.out.println(name);
        List<User> users = userdb.values().stream()
                .filter(a -> a.getName().equalsIgnoreCase(name))
                .filter(a -> a.getName().equalsIgnoreCase(email))
                .toList();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/info")
    public String getInfo(@RequestHeader("User-Agent") String userAgent){
        return "User Agent: "+ userAgent;
    }
}
