/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.clicktop.app.model;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
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

    public enum CompanyType {
        FULL, SHORT
    }

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

    @Column(name = "formatted_address")
    private String formatedAddress;

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

    @OneToMany(cascade = {CascadeType.PERSIST,CascadeType.REMOVE}, orphanRemoval = true)
    @JoinColumn(name = "id_company")
    private List<Telephone> telephones;

    @Column(name = "type")
    private CompanyType type;

    @Column(name = "observation")
    private String observation;

    @Column(name = "photo_profile", columnDefinition = "LONGTEXT")
    private String photoProfile;

    @Column(name = "photo_cover", columnDefinition = "LONGTEXT")
    private String photoCover;

    @Column(name = "spotlight")
    private String spotlight;

    @Column(name = "consultant")
    private User consultant;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_category")
    private Category category;

    @Column(name = "created_at")
    private LocalDateTime createAt;

    @PrePersist
    public void generateDate() {
        this.createAt = LocalDateTime.now();
    }

    public void update(Company entity) {

        if (Optional.ofNullable(entity.getName()).isPresent() && !entity.getName().equals(this.name)) {
            this.name = entity.getName();
        }

        if (Optional.ofNullable(entity.getEmail()).isPresent() && !entity.getEmail().equals(this.email)) {
            this.email = entity.getEmail();
        }

        if (Optional.ofNullable(entity.getLatitude()).isPresent() && !entity.getLatitude().equals(this.latitude)) {
            this.latitude = entity.getLatitude();
        }

        if (Optional.ofNullable(entity.getLongitude()).isPresent() && !entity.getLongitude().equals(this.longitude)) {
            this.longitude = entity.getLongitude();
        }

        if (Optional.ofNullable(entity.getDescription()).isPresent() && !entity.getDescription().equals(this.description)) {
            this.description = entity.getDescription();
        }

        if (Optional.ofNullable(entity.getAddress()).isPresent() && !entity.getAddress().equals(this.address)) {
            this.address = entity.getAddress();
        }

        if (Optional.ofNullable(entity.getAddressNeighborhood()).isPresent() && !entity.getAddressNeighborhood().equals(this.addressNeighborhood)) {
            this.addressNeighborhood = entity.getAddressNeighborhood();
        }

        if (Optional.ofNullable(entity.getAddressComplement()).isPresent() && !entity.getAddressComplement().equals(this.addressComplement)) {
            this.addressComplement = entity.getAddressComplement();
        }

        if (Optional.ofNullable(entity.getAddressNumber()).isPresent() && !entity.getAddressNumber().equals(this.addressNumber)) {
            this.addressNumber = entity.getAddressNumber();
        }

        if (Optional.ofNullable(entity.getAddressNumber()).isPresent() && !entity.getAddressNumber().equals(this.addressNumber)) {
            this.addressNumber = entity.getAddressNumber();
        }

        if (Optional.ofNullable(entity.getZipCode()).isPresent() && !entity.getZipCode().equals(this.zipCode)) {
            this.zipCode = entity.getZipCode();
        }

        if (Optional.ofNullable(entity.getWebsite()).isPresent() && !entity.getWebsite().equals(this.website)) {
            this.website = entity.getWebsite();
        }

        if (Optional.ofNullable(entity.getFacebook()).isPresent() && !entity.getFacebook().equals(this.facebook)) {
            this.facebook = entity.getFacebook();
        }

        if (Optional.ofNullable(entity.getInstagran()).isPresent() && !entity.getInstagran().equals(this.instagran)) {
            this.instagran = entity.getInstagran();
        }

        if (Optional.ofNullable(entity.getStatus()).isPresent() && !entity.getStatus().equals(this.status)) {
            this.status = entity.getStatus();
        }

        if (Optional.ofNullable(entity.getMainProducts()).isPresent() && !entity.getMainProducts().equals(this.mainProducts)) {
            this.mainProducts = entity.getMainProducts();
        }

        if (Optional.ofNullable(entity.getOpeningHours()).isPresent() && !entity.getOpeningHours().equals(this.openingHours)) {
            this.openingHours = entity.getOpeningHours();
        }

        if (Optional.ofNullable(entity.getClosingHours()).isPresent() && !entity.getClosingHours().equals(this.closingHours)) {
            this.closingHours = entity.getClosingHours();
        }

        if (Optional.ofNullable(entity.getLinkWhats()).isPresent() && !entity.getLinkWhats().equals(this.linkWhats)) {
            this.linkWhats = entity.getLinkWhats();
        }

        if (Optional.ofNullable(entity.getKeys()).isPresent() && !entity.getKeys().equals(this.keys)) {
            this.keys = entity.getKeys();
        }

        if (Optional.ofNullable(entity.getPoint_text()).isPresent() && !entity.getPoint_text().equals(this.point_text)) {
            this.point_text = entity.getPoint_text();
        }

        if (Optional.ofNullable(entity.getPlan()).isPresent() && !entity.getPlan().equals(this.plan)) {
            this.plan = entity.getPlan();
        }

        if (Optional.ofNullable(entity.getType()).isPresent() && !entity.getType().equals(this.type)) {
            this.type = entity.getType();
        }

        if (Optional.ofNullable(entity.getSpotlight()).isPresent() && !entity.getSpotlight().equals(this.spotlight)) {
            this.spotlight = entity.getSpotlight();
        }
        
        if(Optional.ofNullable(entity.getLongitude()).isPresent() && !entity.getLongitude().equals(this.longitude)) {
            this.longitude = entity.getLongitude();
        }
        
        if(Optional.ofNullable(entity.getLatitude()).isPresent() && !entity.getLatitude().equals(this.latitude)) {
            this.latitude = entity.getLongitude();
        }

    }

}
