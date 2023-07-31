package com.example.servingwebcontent.config.details;

import com.example.servingwebcontent.models.User;
import com.example.servingwebcontent.repositories.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@RequiredArgsConstructor
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    public final UsersRepository usersRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
       User user = usersRepository.findByUserName(username).orElseThrow(
                () -> new UsernameNotFoundException("user not found"));

        return new AuthenticatedUser(user);

    }
}
