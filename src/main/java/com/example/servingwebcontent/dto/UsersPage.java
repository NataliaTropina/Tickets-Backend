package com.example.servingwebcontent.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.example.servingwebcontent.dto.UserDto;

import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Schema(description = "Страница с пользователями")
public class UsersPage {

    @Schema(description = "Пользователи")
    private List<UserDto> data;
}
