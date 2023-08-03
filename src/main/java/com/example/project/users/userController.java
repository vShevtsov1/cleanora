package com.example.project.users;

import com.example.project.users.Services.userService;
import com.example.project.users.data.DTO.*;
import com.example.project.users.data.help.resetPassRequestStatus;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/user")
public class userController {
    private final userService userService;
    private final ModelMapper modelMapper;


    public userController(ModelMapper modelMapper,userService userService) {
        this.modelMapper = modelMapper;
        this.userService = userService;
    }

    @GetMapping("/getById")
    public ResponseEntity<userDTO> getUserById(@RequestParam("userId") Long userId){
        try {
            return ResponseEntity.ok(modelMapper.map(userService.getUserById(userId), userDTO.class));
        }
        catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }
    @PostMapping("/register")
    private ResponseEntity<userRegisterStatusDTO> registerNewUser(@RequestBody userRegisterDTO userRegisterDTO){
        try {
            return ResponseEntity.ok(userService.register(userRegisterDTO));
        }
        catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }
    @PostMapping("/login")
    public ResponseEntity<loginResponseDTO> login(@RequestBody userLogin userLogin){
        try {
            return ResponseEntity.ok(userService.login(userLogin));
        }
        catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }
    @GetMapping("/password-reset/request")
    public ResponseEntity<resetPassRequestStatus> passwordResetRequest(@RequestParam("userEmail")String userEmail){
        try {
            return ResponseEntity.ok(userService.requestResetPassword(userEmail));
        }
        catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }
}
