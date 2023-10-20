package com.inkua.user.controller;

import com.inkua.user.model.User;
import com.inkua.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    @GetMapping
    public List<User> getUsers() {
        return this.userService.findAllUsers();
    }

    @GetMapping(path = "/{email}")
    public Optional<User> getByEmail(@PathVariable("email") String email) {
        return this.userService.findByEmail(email);
    }

    @PostMapping(path = ("/create"))
    public User saveUser(@RequestBody User user) {
        return this.userService.saveUser(user);
    }

    @PutMapping(path = "/{id}")
    public User updateUserById(@RequestBody User request,@PathVariable("id") Long id) {
        return this.userService.updateById(request, id);
    }

    @DeleteMapping(path = "/{id}")
    public String deleteById(@PathVariable("id") Long id) {
        boolean ok = this.userService.deleteUser(id);
        if (ok) {
            return "User with id " + id + "deleted";
        }else {
            return "Error";
        }
    }
}
