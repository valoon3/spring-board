//package com.project.springboard.api.user.entities;
//
//import jakarta.persistence.*;
//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//
//@Entity
//@Table(name = "user")
//@Data
//@NoArgsConstructor
//@AllArgsConstructor
//public class UserEntity {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    @Column(nullable = false, unique = true)
//    private String email;
//
//    @Column(nullable = false)
//    private String password;
//
//    @Column(nullable = false)  // 유일한 값으로 설정 (사용자 이름 중복 불가)
//    private String username;
//
//    @Column(nullable = false, unique = true)
//    private String nickname;
//
//    private boolean isLocked = false;
//}
