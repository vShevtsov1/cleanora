package com.example.project.users.Services;

import com.example.project.users.data.User;
import org.springframework.stereotype.Service;

@Service
public class userService {
    private final userRepo userRepo;

    public userService(userRepo userRepo) {
        this.userRepo = userRepo;
    }
    public User getUserById(Long userId){
        return userRepo.findById(userId).get();
    }
    public Iterable<User> getAll() {
        return userRepo.findAll();
    }
}
