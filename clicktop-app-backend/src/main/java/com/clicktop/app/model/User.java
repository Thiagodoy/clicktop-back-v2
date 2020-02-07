/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.clicktop.app.model;

import com.clicktop.app.request.UserRequest;
import java.time.LocalDateTime;
import java.util.Optional;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import lombok.Data;

/**
 *
 * @author thiag
 */
@Entity
@Table(name = "user")
@Data
public class User {

    public enum UserStatus {
        ACTIVE, CANCELED
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "photo")
    private String photo;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private UserStatus status;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "profile")
    private Profile profile;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @PrePersist
    public void generateDate() {
        this.createdAt = LocalDateTime.now();
    }
    
    public User(UserRequest request){
        
    }
    
    public void update(UserRequest request){
        
        if(Optional.ofNullable(request.getEmail()).isPresent() && !request.getEmail().equals(email)){
            this.email = request.getEmail();
        }
        
        if(Optional.ofNullable(request.getFirstName()).isPresent() && !request.getFirstName().equals(firstName)){
            this.firstName = request.getFirstName();
        }
        
        if(Optional.ofNullable(request.getLastName()).isPresent() && !request.getLastName().equals(lastName)){
            this.lastName = request.getLastName();
        }
        
        if(Optional.ofNullable(request.getPhoto()).isPresent() && !request.getPhoto().equals(photo)){
            this.photo = request.getPhoto();
        }       
        
    }

}
