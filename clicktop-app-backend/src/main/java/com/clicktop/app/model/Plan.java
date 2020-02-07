/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.clicktop.app.model;

import java.util.Optional;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

/**
 *
 * @author thiag
 */
@Entity
@Table(name = "plan")
@Data
public class Plan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    public void update(Plan plan) {
        if (Optional.ofNullable(plan.getName()).isPresent() && !plan.getName().equals(this.name)) {
            this.name = plan.getName();
        }

        if (Optional.ofNullable(plan.getDescription()).isPresent() && !plan.getDescription().equals(this.description)) {
            this.description = plan.getDescription();
        }
    }

}
