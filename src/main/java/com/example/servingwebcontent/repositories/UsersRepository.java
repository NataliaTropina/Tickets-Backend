package com.example.servingwebcontent.repositories;

import com.example.servingwebcontent.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsersRepository extends JpaRepository<User, Long> {

    Optional<User> findByUserName(String username);
}
