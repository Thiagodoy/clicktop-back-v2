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
@Table(name = "categories")
@Data
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "is_tour")
    private Long isTour;

    @Column(name = "createdAt")
    private LocalDateTime createdAt;

    @PrePersist
    public void generateDate() {
        this.createdAt = LocalDateTime.now();
    }

    public void update(Category request) {

        if (Optional.ofNullable(request.getName()).isPresent() && !request.getName().equals(name)) {
            this.name = request.getName();
        }
        
        if (Optional.ofNullable(request.getIsTour()).isPresent() && !request.getIsTour().equals(isTour)) {
            this.isTour = request.getIsTour();
        }

    }

}
