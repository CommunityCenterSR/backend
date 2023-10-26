package com.inkua.communitycenter.controller;

import com.inkua.communitycenter.entity.User;
import com.inkua.communitycenter.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/v1/users")
public class UserController {

    @Autowired
    private UserServiceImpl userService;

    @GetMapping
    public ResponseEntity<List<User>> getUsers() {
        List<User> list = this.userService.findAllUsers();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping(path = "/{email}")
    public ResponseEntity<Optional<User>> getByEmail(@PathVariable("email") String email) {
        Optional<User> user = this.userService.findByEmail(email);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PostMapping(path = ("/create"))
    public ResponseEntity<User> saveUser(@RequestBody User user) {
        return new ResponseEntity<>( this.userService.saveUser(user), HttpStatus.CREATED);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<User> updateUserById(@RequestBody User request,@PathVariable("id") Long id) {
        return new ResponseEntity<>(this.userService.updateById(request, id), HttpStatus.OK);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<String> deleteById(@PathVariable("id") Long id) {
        boolean ok = this.userService.deleteUser(id);
        if (ok) {
            return new ResponseEntity<>("User with id " + id + "deleted", HttpStatus.OK);
        }else {
            return new ResponseEntity<>("Error", HttpStatus.NOT_FOUND);
        }
    }
}
