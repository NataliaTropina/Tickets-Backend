package com.example.servingwebcontent.services.impl;

import com.example.servingwebcontent.dto.ProfileDto;
import com.example.servingwebcontent.dto.UserDto;
import com.example.servingwebcontent.dto.UsersPage;
import com.example.servingwebcontent.exceptions.NotFoundException;
import com.example.servingwebcontent.models.User;
import com.example.servingwebcontent.repositories.UsersRepository;
import com.example.servingwebcontent.services.UsersService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.servingwebcontent.dto.UserDto.from;

@Service
@RequiredArgsConstructor
public class UsersServiceImpl implements UsersService {

    private final UsersRepository usersRepository;
    @Override
    public UsersPage getAll(){
        List<User> users = usersRepository.findAll();
        return UsersPage.builder()
                .data(from(users))
                .build();
    }

    @Override
    public ProfileDto getProfile(Long currentUserId) {
        User user = usersRepository.findById(currentUserId)
                .orElseThrow(IllegalArgumentException::new);
        return ProfileDto.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .username(user.getUserName())
                .role(user.getRole().name())
                .build();
    }

    @Override
    public UserDto getUser(Long userId) {
        User user = usersRepository.findById(userId)
                .orElseThrow(()->
                        new NotFoundException("user with id<" + userId + "> not found"));
        return from(user);
    }
}
