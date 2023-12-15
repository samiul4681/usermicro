package com.brainstation.usermicro.controller;


import com.brainstation.usermicro.entity.User;
import com.brainstation.usermicro.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api")
public class UserController {



    @Autowired
    private UserService userService;



    @Cacheable(value = "users", key = "#userId")
    @RequestMapping(value = "user/{userId}", method = RequestMethod.GET)
    public ResponseEntity<User> getUser(@PathVariable Long userId) {


        Optional<User> userData = userService.getUserById(userId);

        if (userData.isPresent()) {
            return new ResponseEntity<>(userData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }


    @CachePut(value = "users", key = "#user.id")
    @PutMapping("user/update")
    public ResponseEntity<User> updatePersonByID(@RequestBody User user) {
       User userData=userService.updateUser(user);
       return new ResponseEntity<>(userData, HttpStatus.OK);
    }

    @CacheEvict(value = "users", allEntries=true)
    @DeleteMapping("user/{id}")
    public ResponseEntity<User>  deleteUserByID(@PathVariable Long id) {

        userService.deleteUser(id);

        return new ResponseEntity<>(HttpStatus.OK);

    }


    @CachePut(value = "users", key = "#user.id")
    @PostMapping("user/create")
    public ResponseEntity<User> createUser(@RequestBody User user)
    {

        User userData=userService.createUSer(user);

        return new ResponseEntity<>(userData, HttpStatus.OK);
    }


}
