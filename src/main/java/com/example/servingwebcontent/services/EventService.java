package com.example.servingwebcontent.services;

import com.example.servingwebcontent.dto.EventDTO;
import com.example.servingwebcontent.dto.NewEventDTO;
import com.example.servingwebcontent.models.Artist;
import com.example.servingwebcontent.repositories.ArtistRepository;
import com.example.servingwebcontent.models.Place;
import com.example.servingwebcontent.repositories.PlaceRepository;
import com.example.servingwebcontent.models.Event;
import com.example.servingwebcontent.repositories.EventRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EventService {

    private static final  ModelMapper modelMapper = new ModelMapper();
    private PlaceRepository placeRepository;

    private EventRepository eventRepository;

    private ArtistRepository artistRepository;

    @Autowired
    public void setArtistRepository(ArtistRepository artistRepository) {
        this.artistRepository = artistRepository;
    }

    @Autowired
    public void setPlaceRepository(PlaceRepository placeRepository) {
        this.placeRepository = placeRepository;
    }

    @Autowired
    public void setEventRepository(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }
    public List<EventDTO> getEvents(String cityFilter)
        {
            List<Event> allEvents;
            if (cityFilter.equals("all")){
                allEvents = eventRepository.findAll();
            } else {
                allEvents = eventRepository.findFilteredByCity(cityFilter);
            }
            List<EventDTO> result = modelMapper.map(allEvents, new TypeToken<List<EventDTO>>(){}.getType());
            return result;
        }


    public EventDTO getEvent(int eventId)
    {
        // Optional 2 state:
        // 1. I'm empty. I have no value
        // 2. Has value;

        Optional<Event> eventOptional = eventRepository.findById(eventId);
        Event event = eventOptional.get(); // Exception if data not found
        // Copy data to DTO

        return modelMapper.map(event, EventDTO.class);



        /**
         if (eventOptional.isPresent())
         {
         // We have row in database. Optional has value
         // Now get value from optional
         Event event = eventOptional.get();
         // Copy data to DTO
         EventDTO eventDTO = new EventDTO(event.getName(), event.getCity());
         return eventDTO;
         } else {
         // No value
         return new EventDTO();
         }
         **/
    }

    public void deleteEvent(int eventId)
    {
        eventRepository.deleteById(eventId);
    }
    public void updateEvent(int eventId, NewEventDTO newEventDTO)
    {

        Event event = eventRepository.findById(eventId).get();
        event.setName(newEventDTO.getName());

        Place place = placeRepository.findById(newEventDTO.getPlaceId()).get();

        Artist artist = artistRepository.findById(newEventDTO.getArtistId()).get();

        event.setPlace(place);
        event.setArtist(artist);

        eventRepository.save(event);
        artistRepository.save(artist);
    }

    public int createEvent(NewEventDTO newEventDTO)
    {
        int placeId = newEventDTO.getPlaceId();

        int artistId = newEventDTO.getArtistId();

        Place place = placeRepository.findById(placeId).get();
        Artist artist = artistRepository.findById(artistId).get();

        Event event = new Event();
        event.setName(newEventDTO.getName());

        event.setPlace(place);
        event.setArtist(artist);

        return eventRepository.save(event).getId();
    }

    public List<EventDTO> getEventsByPlace(int placeId)
    {
        Place place = placeRepository.findById(placeId).get();

        List<Event> eventsForPlace = place.getEvents();

        List<EventDTO> result = modelMapper.map(eventsForPlace, new TypeToken<List<EventDTO>>(){}.getType());

        return result;
    }

    public List<EventDTO> getEventsForArtist(int artistId){

       Artist artist = artistRepository.findById(artistId).get();
       List<Event> eventsForArtist = artist.getEvents();

       List<EventDTO> result = modelMapper.map(eventsForArtist, new TypeToken<List<EventDTO>>(){}.getType());

       return result;
    }

}
