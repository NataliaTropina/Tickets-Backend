package com.example.servingwebcontent.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public
class EventDTO {

        @Schema(description = "Name of Event")
        private String name;

        @Schema(description = "Place of Event")
        private PlaceDTO place;
        private ArtistDTO artist;

    }

