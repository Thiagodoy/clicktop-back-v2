/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.clicktop.app.model;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.Data;

/**
 *
 * @author thiag
 */
@Entity
@Table(name = "company")
@Data
public class Company {
    
    
    public enum CompanyType{FULL,SHORT}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    
    @Column(name = "name")
    private String name;
    
    @Column(name = "email")
    private String email;
    
    @Column(name = "latitude")
    private String latitude;
    
    @Column(name = "longitude")    
    private String longitude;
    
    @Column(name = "description")
    private String description;
    
    @Column(name = "address")
    private String address;
    
    @Column(name = "address_neighborhood")
    private String addressNeighborhood;
    
    @Column(name = "address_complement")
    private String addressComplement;
    
    @Column(name = "address_number")
    private Long addressNumber;
    
    @Column(name = "zip_code")
    private String zipCode;
    
    @Column(name = "website")
    private String website;
    
    @Column(name = "facebook")
    private String facebook;
    
    @Column(name = "instagran")
    private String instagran;
    
    @Column(name = "status")
    private String status;
    
    @Column(name = "main_products")
    private String mainProducts;
    
    @Column(name = "opening_hours")
    private String openingHours;
    
    @Column(name = "closing_hours")
    private String closingHours;
    
    @Column(name = "link_whats")
    private String linkWhats;
    
    @Column(name = "company_keys")
    private String keys;
    
    @Column(name = "point_text")
    private String point_text;
    
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_plan")
    private Plan plan;
    
    @OneToOne
    @JoinColumn(name = "id_city")
    private City city;
    
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)    
    @JoinColumn(name = "id_company")
    private List<Telephone> telephones;
    
    @Column(name = "type")
    private CompanyType type;    
    
    @Column(name = "observation")
    private String observation;
    
    @Column(name = "photo_profile")
    private String photoProfile;
    
    @Column(name = "photo_cover")
    private String photoCover;

}
