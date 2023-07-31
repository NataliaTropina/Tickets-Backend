package com.example.servingwebcontent.models;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull
    private String name;

    @NotNull
    @ManyToOne
    private Place place;

    @NotNull
    @ManyToOne
    // @JoinColumn(name = "artist_ref_id", referencedColumnName = "id", table = "artists")
    private Artist artist;
}
