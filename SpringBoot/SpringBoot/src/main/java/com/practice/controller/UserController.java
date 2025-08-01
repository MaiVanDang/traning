package com.practice.controller;

import com.practice.dto.UserRequest;
import com.practice.entity.UserEntity;
import com.practice.service.UserService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @PostMapping
    public UserEntity create(@Valid @RequestBody UserRequest req) {
        return service.createUser(req);
    }

    @GetMapping("/{id}")
    public UserEntity getById(@PathVariable int id) {
        return service.getUser(id);
    }

    // Có thể thêm endpoint test lỗi nếu muốn
}

