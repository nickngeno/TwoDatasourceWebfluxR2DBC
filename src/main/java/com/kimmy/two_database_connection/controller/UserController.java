package com.kimmy.two_database_connection.controller;

import com.kimmy.two_database_connection.model.user.User;
import com.kimmy.two_database_connection.service.UserService;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("api")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/adduser")
    public Mono<User> addUser(@RequestBody User user) {
        return userService.addUser(user);
    }

    @GetMapping("/users/{id}")
    public Mono<User> findUser(@PathVariable int id) {
        return userService.findUser(id);
    }

    @GetMapping("/users")
    public Flux<User> findAll() {
        return userService.findAll();
    }

    @PostMapping("/updateuser")
    public Mono<User> updateUser(@RequestBody User user) {
        return userService.updateUser(user);
    }

    @PostMapping("/deleteuser/{id}")
    public Mono<Void> deleteUser(@PathVariable int id) {
        return userService.deleteUser(id);
    }
}
