package com.example.maawa.service;


import com.example.maawa.model.MyUser;
import com.example.maawa.repository.myUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MyUserDetailsService implements UserDetailsService {

    private final myUserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        MyUser myUser= userRepository.findUserByUsername(username);
        if (myUser==null){
            throw new UsernameNotFoundException("Wrong username or password");
        }
        return myUser;
    }
}