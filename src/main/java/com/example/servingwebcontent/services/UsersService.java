package com.example.servingwebcontent.services;

import com.example.servingwebcontent.dto.ProfileDto;
import com.example.servingwebcontent.dto.UserDto;
import com.example.servingwebcontent.dto.UsersPage;

public interface UsersService {


    UsersPage getAll();

    ProfileDto getProfile(Long currentUserId);

    UserDto getUser(Long userId);
}
