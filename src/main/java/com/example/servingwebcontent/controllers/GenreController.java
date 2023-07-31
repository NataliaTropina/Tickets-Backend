package com.example.servingwebcontent.controllers;

import com.example.servingwebcontent.services.GenreService;
import com.example.servingwebcontent.dto.GenreDTO;
import com.example.servingwebcontent.dto.NewGenreDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("genres")
public class GenreController {

    private GenreService genreService;

    @Autowired
    public void setGenreService(GenreService genreService) {
        this.genreService = genreService;
    }

    @GetMapping(value = "/{id}")
    public GenreDTO getGenre (@PathVariable int id){
        return genreService.getGenre(id);
    }

    @PostMapping(value = "")
    public int createGenre (@RequestBody NewGenreDTO newGenreDTO){
        return genreService.createGenre(newGenreDTO);
    }

    @GetMapping(value = "/by-name/{name}")
    public List<GenreDTO> getGenresByName (@PathVariable String name){
       return genreService.getGenresByName(name);
    }
}
