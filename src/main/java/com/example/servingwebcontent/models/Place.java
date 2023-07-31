package com.example.servingwebcontent.models;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Place {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;


    private String name;


    private String address;


    private String city;

    @OneToMany(mappedBy = "place")
    private List<Event> events;

    @ManyToMany(mappedBy = "places")
    private List<Artist> artists;

}
