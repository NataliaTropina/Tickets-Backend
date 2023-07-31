package com.example.servingwebcontent.repositories;

import com.example.servingwebcontent.models.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GenreRepository extends JpaRepository<Genre, Integer> {

    List<Genre> findByName (String name);
}
