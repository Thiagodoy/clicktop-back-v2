/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.clicktop.app.model;

import com.clicktop.app.request.UserRequest;
import com.clicktop.app.utils.Utils;
import java.time.LocalDateTime;
import java.util.Collection;
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
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 *
 * @author thiag
 */
@Entity
@Table(name = "user")
@Data
@NoArgsConstructor
public class User implements UserDetails {

   

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
    
    @Column(name = "cellphone")
    private String cellPhone;
    
    @Column(name = "tellphone")
    private String tellPhone;
    
    @Column(name = "id_company")
    private Long company;
        

    @PrePersist
    public void generateDate() {
        this.createdAt = LocalDateTime.now();
    }
    
    public User(UserRequest request){
        
        this.firstName = request.getFirstName();
        this.lastName = request.getLastName();
        this.email = request.getEmail();
        this.password = Utils.encodePassword(request.getPassword()); 
        this.cellPhone = request.getCellPhone();
        this.tellPhone = request.getTellPhone();
        
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
        
        if(Optional.ofNullable(request.getTellPhone()).isPresent() && !request.getTellPhone().equals(tellPhone)){
            this.tellPhone = request.getTellPhone();
        }  
        
        if(Optional.ofNullable(request.getPassword()).isPresent() && !request.getPassword().equals(password)){
            this.password = Utils.encodePassword(request.getPassword());
        } 
    }
    
    
    public static User createUserDefault(){
        User user = new User();
        user.setStatus(UserStatus.ACTIVE);
        user.setPassword(Utils.encodePassword("Clicktop2020"));
        user.setLastName("");
        return user;
    }
    
    
     @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
    

}
