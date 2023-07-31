package com.example.servingwebcontent.services.impl;

import com.example.servingwebcontent.dto.NewUserDto;
import com.example.servingwebcontent.dto.UserDto;
import com.example.servingwebcontent.models.User;
import com.example.servingwebcontent.repositories.UsersRepository;
import com.example.servingwebcontent.services.SignUpService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Service
public class SignUpServiceImpl implements SignUpService {
    
    private final UsersRepository userRepository;

    @Override
    public UserDto signUp(NewUserDto newUserDto) {
        
        User user = User.builder()
                .userName(newUserDto.getUserName())
                .hashPassword(newUserDto.getPassword())
                .role(User.Role.USER)
                .createdDate(LocalDateTime.now())
                .build();

        userRepository.save(user);


        return UserDto.from(user);
    }
}
