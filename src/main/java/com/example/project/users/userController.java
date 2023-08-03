package com.example.project.users;

import com.example.project.users.Services.userService;
import com.example.project.users.data.DTO.userDTO;
import com.example.project.users.data.DTO.userLoginResponse;
import com.example.project.users.data.userLogin;
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

    @GetMapping("/getById/{userId}")
    public ResponseEntity<userDTO> getUserById(@RequestParam("userId") Long userId){
        try {
            return ResponseEntity.ok(modelMapper.map(userService.getUserById(userId), userDTO.class));
        }
        catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/login")
    public ResponseEntity<userLoginResponse> login(@RequestBody userLogin userLogin){

    }

}
