package com.example.servingwebcontent.controllers;

import com.example.servingwebcontent.controllers.api.SignUpApi;
import com.example.servingwebcontent.dto.NewUserDto;
import com.example.servingwebcontent.dto.UserDto;
import com.example.servingwebcontent.services.SignUpService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.security.PermitAll;

@RequiredArgsConstructor
@RestController
public class SignUpController implements SignUpApi {

    private final SignUpService signUpService;

    @PermitAll
    @Override
    public ResponseEntity<UserDto> signUp(NewUserDto newUserDto) {
        return ResponseEntity
                .status(201)
                .body(signUpService.signUp(newUserDto));

    }

}
