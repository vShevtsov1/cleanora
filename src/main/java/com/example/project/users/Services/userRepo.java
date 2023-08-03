package com.example.project.users.Services;

import com.example.project.users.data.User;
import org.springframework.data.repository.CrudRepository;

public interface userRepo extends CrudRepository<User,Long> {
    User findByUserEmail(String email);
}
