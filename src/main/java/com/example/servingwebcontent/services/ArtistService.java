package com.example.servingwebcontent.services;

import com.example.servingwebcontent.dto.ArtistDTO;
import com.example.servingwebcontent.dto.NewArtistDTO;
import com.example.servingwebcontent.models.Artist;
import com.example.servingwebcontent.models.Genre;
import com.example.servingwebcontent.repositories.GenreRepository;
import com.example.servingwebcontent.models.Place;
import com.example.servingwebcontent.repositories.PlaceRepository;
import com.example.servingwebcontent.repositories.ArtistRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class ArtistService {

    private ArtistRepository artistRepository;

    @Autowired
    public void setGenreRepository(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }

    private GenreRepository genreRepository;

    private PlaceRepository placeRepository;

    @Autowired
    public void setPlaceRepository(PlaceRepository placeRepository) {
        this.placeRepository = placeRepository;
    }

    private static final ModelMapper mapper = new ModelMapper();

    @Autowired
    public void setArtistRepository(ArtistRepository artistRepository){
        this.artistRepository = artistRepository;
    }

    public List<ArtistDTO> getArtistByGenre() {

        Iterable<Artist> allArtists = artistRepository.findAll();
        List<ArtistDTO> result = new ArrayList<ArtistDTO>();

        for (Artist artist: allArtists ) {

         //   ArtistDTO artistDTO = new ArtistDTO(artist.getName(), artist.getGenre());
          //  result.add(artistDTO);
        }
       return result;

    }

    public ArtistDTO getArtist(int id)
    {
        // Get from database

        // Equal for
        // Optional<Artist> artistOpt = artistRepository.findById(id);
        // Artist artist = artistOpt.get();

        Artist artist = artistRepository.findById(id).get(); // TODO: handle not found

        ArtistDTO result = mapper.map(artist, ArtistDTO.class );
        // Copy data from Entity(db) to ArtistDTO(presentation layer)
        //ArtistDTO result = new ArtistDTO();
        //result.setName(artist.getName());
        //result.setGenre(artist.getGenre());

        return result;
    }



    public void deleteArtist(int artistId){

       artistRepository.deleteById(artistId);

    }

    public void updateArtist(int artistId, ArtistDTO newArtistDTO){

        Artist artist = artistRepository.findById(artistId).get();

        artist.setName(newArtistDTO.getName());
     //   artist.setGenre(newArtistDTO.getGenre());

        artistRepository.save(artist);

    }

    public int createArtist(NewArtistDTO newArtistDTO){

        int genreID = newArtistDTO.getGenreId();
        Genre genre = genreRepository.findById(genreID).get();

        Artist artist = new Artist();

        artist.setName(newArtistDTO.getName());

        artist.setGenre(genre);

        return artistRepository.save(artist).getId();

    }

    public List<ArtistDTO> getArtistByPlaceId (int placeId){

        Place place = placeRepository.findById(placeId).get();

        List<Artist> artistsForPlace = place.getArtists();

        List<ArtistDTO> result = mapper.map(artistsForPlace, new TypeToken<List<ArtistDTO>>(){}.getType());

        return result;
    }

    public List<ArtistDTO> getArtistByGenre (String name){

        List<Artist> allArtists;
        if(name.equals("all")){
            allArtists = (List<Artist>) artistRepository.findAll();

        } else {
            allArtists = artistRepository.findFilterByGenre(name);
        }

        return mapper.map(allArtists, new TypeToken<List<ArtistDTO>>(){}.getType());
    }

}
