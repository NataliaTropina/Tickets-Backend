package com.example.servingwebcontent.models;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Table(name = "account")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class User {


    public enum Role {

        ADMIN, USER
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    private String lastName;

    private String userName;
    private String hashPassword;

    @Enumerated(value = EnumType.STRING)
    private Role role;

    private LocalDateTime createdDate;

}
