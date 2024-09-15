//package com.project.springboard.api.user.service;
//
//import com.project.springboard.api.user.repository.UserRepository;
//import lombok.RequiredArgsConstructor;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.stereotype.Service;
//
//@Service
//@RequiredArgsConstructor
//public class UserDetailService implements UserDetailsService {
//
//    private final UserRepository userRepository;
//
//    @Override
//    public UserDetails loadUserByUsername(String email) {
//        return userRepository.findByEmail(email)
//                .orElseThrow(() -> new IllegalArgumentException((email)));
//    }
//}
