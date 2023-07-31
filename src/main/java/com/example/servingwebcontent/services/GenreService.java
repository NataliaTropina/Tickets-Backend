package com.example.servingwebcontent.services;

import com.example.servingwebcontent.dto.GenreDTO;
import com.example.servingwebcontent.dto.NewGenreDTO;
import com.example.servingwebcontent.models.Genre;
import com.example.servingwebcontent.repositories.GenreRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GenreService {

    private String name;
    private int id;
    private GenreRepository genreRepository;

    private final static ModelMapper modelMapper = new ModelMapper();

    @Autowired
    public void SetGenreRepository (GenreRepository genreRepository){
        this.genreRepository = genreRepository;
    }

    public GenreDTO getGenre (int id){
        Genre genre = genreRepository.findById(id).get();

        GenreDTO result = modelMapper.map(genre, GenreDTO.class);
        return result;
    }

    public int createGenre (NewGenreDTO newGenreDTO){

        Genre genre = modelMapper.map(newGenreDTO, Genre.class);

        Genre result = genreRepository.save(genre);

        return result.getId();
    }

    public List<GenreDTO> getGenresByName (String name){

       List<Genre> genresByName = genreRepository.findByName(name);

       return modelMapper.map(genresByName,  new TypeToken<List<GenreDTO>>(){}.getType());
    }

}
