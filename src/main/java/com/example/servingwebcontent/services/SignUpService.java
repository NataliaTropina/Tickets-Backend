package com.example.servingwebcontent.services;

import com.example.servingwebcontent.dto.NewUserDto;
import com.example.servingwebcontent.dto.UserDto;

public interface SignUpService {

    UserDto signUp(NewUserDto newUserDto);

}
