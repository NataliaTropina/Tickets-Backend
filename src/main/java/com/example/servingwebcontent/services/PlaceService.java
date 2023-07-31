package com.example.servingwebcontent.services;

import com.example.servingwebcontent.dto.NewPlaceDTO;
import com.example.servingwebcontent.dto.PlaceDTO;
import com.example.servingwebcontent.models.Artist;
import com.example.servingwebcontent.repositories.ArtistRepository;
import com.example.servingwebcontent.models.Place;
import com.example.servingwebcontent.repositories.PlaceRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlaceService {

    private PlaceRepository placeRepository;

    @Autowired
    public void setArtistRepository(ArtistRepository artistRepository) {
        this.artistRepository = artistRepository;
    }

    private ArtistRepository artistRepository;



    private static final ModelMapper modelMapper = new ModelMapper();

    @Autowired
    public void setPlaceRepository(PlaceRepository placeRepository) {
        this.placeRepository = placeRepository;
    }

    public int createPlace(NewPlaceDTO newPlaceDTO)
    {
        Place place = modelMapper.map(newPlaceDTO, Place.class);
        return placeRepository.save(place).getId();
    }

    public PlaceDTO getPlace (int id)
    {
        Place place = placeRepository.findById(id).get();

        PlaceDTO result = modelMapper.map(place, PlaceDTO.class);
        return result;
    }

    public List<PlaceDTO> getPlacesByArtistId(int artistId) {

        Artist artist = artistRepository.findById(artistId).get();
        List<Place> placesForArtist = artist.getPlaces();

        return modelMapper.map(placesForArtist, new TypeToken<List<PlaceDTO>>(){}.getType());
    }

    public List<PlaceDTO> getPlacesByCityName(String city){
        List<Place> places = placeRepository.findByCity(city);
        return modelMapper.map(places, new TypeToken<List<PlaceDTO>>(){}.getType());
    }

    public List<PlaceDTO> getPlacesByCityWithAddress (String city, String address){

        List<Place> places = placeRepository.findByCityOrAddress(city, address);

        return modelMapper.map(places,new TypeToken<List<PlaceDTO>>(){}.getType());
    }

}
