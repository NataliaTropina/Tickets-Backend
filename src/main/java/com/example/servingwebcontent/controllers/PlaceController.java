package com.example.servingwebcontent.controllers;

import com.example.servingwebcontent.services.PlaceService;
import com.example.servingwebcontent.dto.ArtistDTO;
import com.example.servingwebcontent.services.ArtistService;
import com.example.servingwebcontent.dto.EventDTO;
import com.example.servingwebcontent.services.EventService;
import com.example.servingwebcontent.dto.NewPlaceDTO;
import com.example.servingwebcontent.dto.PlaceDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("places")
public class PlaceController {

    private PlaceService placeService;

    @Autowired
    public void setEventService(EventService eventService) {
        this.eventService = eventService;
    }

    private EventService eventService;
    @Autowired
    public void setArtistService(ArtistService artistService) {
        this.artistService = artistService;
    }

    private ArtistService artistService;




    @Autowired
    public void setPlaceService(PlaceService placeService) {
        this.placeService = placeService;
    }

    @PostMapping
    public int createPlace(@RequestBody NewPlaceDTO newPlaceDTO)
    {
        return placeService.createPlace(newPlaceDTO);
    }

    @GetMapping(value = "/{id}")
    public PlaceDTO getPlace (@PathVariable int id ){
        return placeService.getPlace(id);
    }

    @GetMapping(value = "/{placeId}/events")
    public List<EventDTO> getEventsByPlace(@PathVariable int placeId){
        return eventService.getEventsByPlace(placeId);
    }

    @GetMapping(value = "/{placeId}/artists")
    public List<ArtistDTO> getArtistByPlaceId (@PathVariable int placeId){

       return artistService.getArtistByPlaceId(placeId);
    }

    @GetMapping(value = "/by-city/{city}")
    public List<PlaceDTO> getPlacesByCity (@PathVariable String city) {
        return placeService.getPlacesByCityName(city);
    }

    @GetMapping(value = "/by-city/{city}/address{address}")
    public List<PlaceDTO> getPlacesByCityWithAddress (@PathVariable String city, String address){
        return placeService.getPlacesByCityWithAddress(city, address);
    }

}
