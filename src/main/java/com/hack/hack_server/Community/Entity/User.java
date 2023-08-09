package com.hack.hack_server.Community.Entity;

import com.hack.hack_server.Authentication.Dto.JoinRequestDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@DynamicInsert
@NoArgsConstructor
@AllArgsConstructor
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
    @ColumnDefault("USER")
    private String roles; //USER, ADMIN

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
