package com.example.servingwebcontent.models;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "artists")
public class Artist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int Id;

    @NotNull
    private String name;

    @NotNull
    @ManyToOne
    private Genre genre;

    @OneToMany(mappedBy = "artist")
    private List<Event> events;


    @ManyToMany
    @JoinTable(
            name = "event",
            joinColumns = @JoinColumn(name = "artist_id"),
            inverseJoinColumns = @JoinColumn(name = "place_id")
    )
    private List<Place>places;

}
