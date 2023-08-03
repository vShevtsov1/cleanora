package com.example.project.users.Services;

import com.example.project.token.TokenServices;
import com.example.project.users.data.DTO.*;
import com.example.project.users.data.User;
import com.example.project.users.data.help.loginStatus;
import com.example.project.users.data.help.registerStatus;
import com.example.project.users.data.help.resetPassRequestStatus;
import com.example.project.users.data.help.userRole;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class userService {
    private final userRepo userRepo;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;
    private final TokenServices tokenServices;

    public userService(com.example.project.users.Services.userRepo userRepo, ModelMapper modelMapper, PasswordEncoder passwordEncoder, TokenServices tokenServices) {
        this.userRepo = userRepo;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
        this.tokenServices = tokenServices;
    }

    public User getUserById(Long userId){

        return userRepo.findById(userId).get();
    }
    public Iterable<User> getAll() {
        return userRepo.findAll();
    }
    public loginResponseDTO login(userLogin loginDTO){
        User loginUser = userRepo.findByUserEmail(loginDTO.getUserEmail());
        if(loginUser==null){
            return new loginResponseDTO(loginStatus.MISSING_USER,"",null);
        }
        else {
            if(passwordEncoder.matches(loginDTO.getUserPassword(),loginUser.getUserPassword())){
                return new loginResponseDTO(loginStatus.OK,tokenServices.generateTokenUser(loginUser),modelMapper.map(loginUser, userDTO.class));
            }
            else {
                return new loginResponseDTO(loginStatus.WRONG_PASSWORD,"",null);
            }
        }
    }

    public userRegisterStatusDTO register(userRegisterDTO newUser){
        User registerUser = new User(newUser.getUserName(), newUser.getUserEmail(), newUser.getUserPhoneNumber(), userRole.USER,passwordEncoder.encode(newUser.getUserPassword()));
        try {
            userRepo.save(registerUser);
            return new userRegisterStatusDTO(modelMapper.map(registerUser, userDTO.class), registerStatus.OK);
        } catch (Exception e) {
            return new userRegisterStatusDTO(null,registerStatus.USER_EXISTS);
        }
    }
    public resetPassRequestStatus requestResetPassword(String email){
        User requestResetUser = userRepo.findByUserEmail(email);
        if(requestResetUser==null){
            return resetPassRequestStatus.MISSING_USER;
        }
        else {
            return resetPassRequestStatus.SEND;
        }
    }
}
