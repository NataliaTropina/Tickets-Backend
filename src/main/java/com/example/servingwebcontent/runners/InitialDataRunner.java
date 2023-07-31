package com.example.servingwebcontent.runners;

import com.example.servingwebcontent.models.User;
import com.example.servingwebcontent.repositories.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@RequiredArgsConstructor
//@Component
public class InitialDataRunner implements CommandLineRunner {

    private final UsersRepository usersRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run (String... args) throws Exception{
        User admin = User.builder()
                .userName("admin")
                .role(User.Role.ADMIN)
                .hashPassword(passwordEncoder.encode("admin"))
                .build();

        User alex = User.builder()
                .userName("alex")
                .hashPassword(passwordEncoder.encode("qwerty008"))
                .role(User.Role.USER)
                .build();
        User marsel = User.builder()
                .userName("marsel")
                .hashPassword(passwordEncoder.encode("qwerty007"))
                .role(User.Role.USER)
                .build();

        usersRepository.saveAll(Arrays.asList(admin, marsel, alex));
    }

}
