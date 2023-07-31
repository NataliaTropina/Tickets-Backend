package com.example.servingwebcontent.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class NewEventDTO {

    @Schema(description = "Name of event")
    private String name;

    @Schema(description = "Place id of event")
    private int placeId;

    @Schema(description = "Artist id of Event")
    private int artistId;
}
