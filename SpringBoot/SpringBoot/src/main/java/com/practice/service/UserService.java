package com.practice.service;

import com.practice.dto.UserRequest;
import com.practice.exception.UserNotFoundException;
import com.practice.entity.UserEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserService {
    private final Map<Integer, UserEntity> userMap = new HashMap<>();
    private int nextId = 1;

    public UserEntity createUser(UserRequest req) {
        UserEntity user = new UserEntity(nextId++, req.getName(), req.getEmail());
        userMap.put(user.getId(), user);
        return user;
    }

    public UserEntity getUser(int id) {
        if (!userMap.containsKey(id)) {
            throw new UserNotFoundException("Không tìm thấy người dùng với ID: " + id);
        }
        return userMap.get(id);
    }
}

