package com.example.servingwebcontent.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(description = "message about exceptions")
public class ExceptionDto {
    @Schema(description = "Text of exception")
    private String message;
    @Schema(description = "HTTP status of exception")
    private int status;
}
