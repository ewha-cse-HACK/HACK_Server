package com.hack.hack_server.Entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

//@DynamicInsert
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class User extends BaseTimeEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column(name = "nickname")
    private String nickname;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "roles")
    private String roles;

    @Column(name = "profile_image")
    private String profileImage;

    public void updateProfile(String profileImage){
        this.profileImage = profileImage;
    }

    @PrePersist
    public void prePersist(){
        this.roles = this.roles == null? "ROLE_USER" : this.roles;
    }

    public List<String> getRolesList(){
        if(this.roles.length() > 0){
            return Arrays.asList(this.roles.split(","));
        }
        return new ArrayList<>();
    }

    @Builder
    public User(String nickname, String email, String password){
        this.nickname = nickname;
        this.email = email;
        this.password = password;
    }

}
