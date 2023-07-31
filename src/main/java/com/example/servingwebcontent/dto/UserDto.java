package com.example.servingwebcontent.dto;

import com.example.servingwebcontent.models.User;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(description = "Зарегистрированный пользователь")
public class UserDto {

    @Schema(description = "идентификатор пользователя", example = "1")
    private Long id;

    @Schema(description = "имя пользователя", example = "username")
    private String userName;

    private String createdDateTime;

    public static UserDto from(User user){
        return UserDto.builder()
                .id(user.getId())
                .userName(user.getUserName())
                .createdDateTime(user.getCreatedDate().toString())
                .build();
    }

    public static List<UserDto> from(List<User> users) {
       return users.stream().map(UserDto::from).collect(Collectors.toList());
    }
}
