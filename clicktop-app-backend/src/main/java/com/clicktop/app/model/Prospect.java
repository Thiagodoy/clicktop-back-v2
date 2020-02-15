/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.clicktop.app.model;

import java.time.LocalDateTime;
import java.util.Optional;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import lombok.Data;

/**
 *
 * @author thiag
 */
@Entity
@Table(name = "prospect")
@Data
public class Prospect {
    
    
    public enum ProspectStatus {PENDING, SUCCESS }
    
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    
    @Column(name = "firstName")
    private String firstName;
    
    @Column(name = "lastName")
    private String lastName;
    
    @Column(name = "companyName")
    private String companyName;
    
    @Column(name = "jobRole")
    private String jobRole;
    
    @Column(name = "email")
    private String email;
    
    @Column(name = "phone")
    private String phone;
    
    @Column(name = "description")
    private String description;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private ProspectStatus status;
    
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    
    
    @PrePersist
    public void generatedDate(){
        this.createdAt = LocalDateTime.now();
    }   
    
    
    public void update(Prospect prospect){
        if(Optional.of(prospect.getStatus()).isPresent() && !prospect.getStatus().equals(this.status)){
            this.status = prospect.getStatus();
        }
    }
    
}
