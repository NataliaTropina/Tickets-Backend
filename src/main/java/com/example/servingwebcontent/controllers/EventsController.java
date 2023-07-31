package com.example.servingwebcontent.controllers;

import com.example.servingwebcontent.services.EventService;
import com.example.servingwebcontent.dto.EventDTO;
import com.example.servingwebcontent.dto.NewEventDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("events") // = @Controller + @ResponseBody for every method
@Tag(name = "Events", description = "Working with events")
public class EventsController {

    private EventService service;

    @Autowired
    public void setService(EventService service) {
        this.service = service;
    }

    @Operation(summary = "Get all events", description = "Get all events filtered by city")
    @GetMapping
    public List<EventDTO> listEvents(@RequestParam(name = "city", required = false, defaultValue = "all") String city)
    {
       return service.getEvents(city);
    }

    @Operation(summary = "Get event", description = "Get event filtered by id")
    @GetMapping(value = "/{eventId}")


    public EventDTO getEvent(@PathVariable int eventId)
    {
        return service.getEvent(eventId);
    }

    @Operation(summary = "delete event", description = "Delete event by ID")
    @DeleteMapping(value = "/{eventId}")
    public void deleteEvent(@PathVariable int eventId)
    {
         service.deleteEvent(eventId);
    }

    // Update
    @Operation(summary = "update event", description = "update event by ID")
    @PutMapping(value = "/{eventId}")

    public void updateEvent(@PathVariable int eventId, @RequestBody NewEventDTO eventDTO)
    {
        service.updateEvent(eventId, eventDTO);
    }
    @Operation(summary = "create event", description = "save new event in DB")
    @PostMapping(value = "")
    public int createEvent(@RequestBody NewEventDTO newEventDTO)
    {
        return service.createEvent(newEventDTO);
    }

}