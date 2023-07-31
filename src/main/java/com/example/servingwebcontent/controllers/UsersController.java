package com.example.servingwebcontent.controllers;


import com.example.servingwebcontent.config.details.AuthenticatedUser;
import com.example.servingwebcontent.controllers.api.UsersApi;
import com.example.servingwebcontent.dto.ProfileDto;
import com.example.servingwebcontent.dto.UserDto;
import com.example.servingwebcontent.dto.UsersPage;
import com.example.servingwebcontent.services.UsersService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UsersController implements UsersApi {

    private final UsersService usersService;

    @PreAuthorize("hasAuthority('ADMIN')")
    @Override
    public ResponseEntity<UsersPage> getAll() {
        return ResponseEntity
                .ok(usersService.getAll());
    }

    @PreAuthorize("isAuthenticated")
    @Override
    public ResponseEntity<ProfileDto> getProfile(AuthenticatedUser currentUser) {
        Long currentUserId = currentUser.getUser().getId();
        ProfileDto profileDto = usersService.getProfile(currentUserId);

        return ResponseEntity.ok(profileDto);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @Override
    public ResponseEntity<UserDto> getUser(Long userId) {
        return ResponseEntity.ok(usersService.getUser(userId));
    }


}
