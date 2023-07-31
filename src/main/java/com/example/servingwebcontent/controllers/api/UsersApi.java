package com.example.servingwebcontent.controllers.api;

import com.example.servingwebcontent.config.details.AuthenticatedUser;
import com.example.servingwebcontent.dto.ProfileDto;
import com.example.servingwebcontent.dto.UserDto;
import com.example.servingwebcontent.dto.UsersPage;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.tags.Tags;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;


@Tags(value = {
        @Tag(name = "Users")
})
@RequestMapping("/api/users")
public interface UsersApi {

    @Operation(summary = "Получение пользователей")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Список пользователей",
            content = {
                    @Content(mediaType = "application/json",
                        schema = @Schema(implementation = UsersPage.class))
            })
    })

    @GetMapping
    ResponseEntity<UsersPage> getAll();

    @Operation(summary = "Получение своего профиля")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Информацию о профиле",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ProfileDto.class))
                    }
            )
    })
    @GetMapping("/my/profile")
    ResponseEntity<ProfileDto> getProfile(@Parameter(hidden = true) @AuthenticationPrincipal AuthenticatedUser currentUser);

    @GetMapping("/{user-id}")
    ResponseEntity<UserDto> getUser(@PathVariable("user-id")Long userId);
}

