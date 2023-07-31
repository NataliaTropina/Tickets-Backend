package com.example.servingwebcontent.controllers;

import com.example.servingwebcontent.services.ArtistService;
import com.example.servingwebcontent.dto.EventDTO;
import com.example.servingwebcontent.services.EventService;
import com.example.servingwebcontent.dto.PlaceDTO;
import com.example.servingwebcontent.services.PlaceService;
import com.example.servingwebcontent.dto.ArtistDTO;
import com.example.servingwebcontent.dto.NewArtistDTO;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("artists")
public class ArtistsController {

    private ArtistService service;

    private EventService eventService;

    @Autowired
    public void setPlaceService(PlaceService placeService) {
        this.placeService = placeService;
    }

    private PlaceService placeService;



    @Autowired
    public void setEventService(EventService eventService) {
        this.eventService = eventService;
    }

    @Autowired
    public void setService(ArtistService service) {
        this.service = service;
    }

    @Operation(summary = "get artists", description = "get artist by genre")
    @GetMapping(value = "")
    public List<ArtistDTO> listArtists() {
        return service.getArtistByGenre();
    }

    @Operation(summary = "get artist", description = "get artist by id")
    @GetMapping(value = "/{id}")
    public ArtistDTO getArtist(@PathVariable int id) {
        return service.getArtist(id);
    }

    @Operation(summary = "delete artist", description = "delete artist by ID")
    @DeleteMapping(value = "/{id}")
    public void deleteArtist(@PathVariable int id) {

         service.deleteArtist(id);

    }

    @Operation(summary = "update artist", description = "update artist by ID")
    @PutMapping(value = "/{artistId}")
    public void updateArtist(@PathVariable int artistId, @RequestBody ArtistDTO newArtistDTO) {

        service.updateArtist(artistId, newArtistDTO);

    }

    @PostMapping
    @Operation(summary = "create artist", description = "create new artist in DB")
    public int createArtist(@RequestBody NewArtistDTO newArtistDTO) {

       return service.createArtist(newArtistDTO);
    }

    @GetMapping(value = "/{artistId}/events")
    public List<EventDTO> getEventsForArtist(@PathVariable int artistId){

        return eventService.getEventsForArtist(artistId);
    }

    @GetMapping(value = "/{artistId}/places")
    public List<PlaceDTO> getPlacesByArtistId(@PathVariable int artistId) {

        return placeService.getPlacesByArtistId(artistId);
    }

    @GetMapping(value = "/by-genre/{genre}")
    public List<ArtistDTO> getArtistsByGenre(@RequestParam(name = "name", required = false, defaultValue = "all") String name) {
        return service.getArtistByGenre(name);
    }


}




