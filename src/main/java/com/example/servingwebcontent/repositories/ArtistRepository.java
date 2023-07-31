package com.example.servingwebcontent.repositories;

import com.example.servingwebcontent.models.Artist;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArtistRepository extends CrudRepository<Artist, Integer> {

    @Query("SELECT a FROM Artist a INNER JOIN a.genre g WHERE g.name = :name" )
    List<Artist> findFilterByGenre (@Param("name") String name);
}
