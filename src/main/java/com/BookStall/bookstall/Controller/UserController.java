package com.BookStall.bookstall.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.BookStall.bookstall.Exceptions.NoObjectFoundException;
import com.BookStall.bookstall.Model.User;
import com.BookStall.bookstall.Service.UserService;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/adduser")
    private ResponseEntity<User> saveUser(@RequestBody User user)
    {
        try {

            return ResponseEntity.ok().body(this.userService.createUser(user));
        }
        catch (Exception e) {
            throw new NoObjectFoundException("Check inputs! user already exists for these fields"+e.getStackTrace());
        }
    }
    @GetMapping("/allusers")
    private ResponseEntity<List<User>> getAllUsers()
    {
        return ResponseEntity.ok().body(this.userService.getUsers());
    }
    @PutMapping("/users/{userId}")
    private ResponseEntity<User> updateUser(@PathVariable int userId, @RequestBody User user)
    {
        user.setUserId(userId);
        return ResponseEntity.ok().body(this.userService.updateUser(user));
    }

    @PutMapping("/users/suspend/{userId}")
    private ResponseEntity<User> suspendUser(@PathVariable int userId)
    {
        return ResponseEntity.ok().body(this.userService.suspendUserById(userId));
    }

}
