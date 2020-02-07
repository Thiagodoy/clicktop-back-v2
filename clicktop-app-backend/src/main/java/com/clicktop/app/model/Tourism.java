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
@Table(name = "tourism")
@Data
public class Tourism {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "telephone_1")
    private String telephone1;

    @Column(name = "telephone_2")
    private String telephone2;

    @Column(name = "telephone_3")
    private String telephone3;

    @Column(name = "address")
    private String address;

    @Column(name = "address_complement")
    private String addressComplement;

    @Column(name = "address_zip_code")
    private String addressZipCode;

    @Column(name = "city")
    private Long city;

    @Column(name = "open_hours")
    private String openHours;

    @Column(name = "closing_hours")
    private String closingHours;

    @Column(name = "email")
    private String email;

    @Column(name = "image")
    private String image;

    @Column(name = "website")
    private String website;

    @Column(name = "facebook")
    private String facebook;

    @Column(name = "instagran")
    private String instagran;

    @Column(name = "history")
    private String history;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @PrePersist
    public void generateDate() {
        this.createdAt = LocalDateTime.now();
    }

    public void update(Tourism request) {
        if (Optional.ofNullable(request.getName()).isPresent() && !request.getName().equals(name)) {
            this.name = request.getName();
        }

        if (Optional.ofNullable(request.getTelephone1()).isPresent() && !request.getTelephone1().equals(telephone1)) {
            this.telephone1 = request.getTelephone1();
        }

        if (Optional.ofNullable(request.getTelephone2()).isPresent() && !request.getTelephone2().equals(telephone2)) {
            this.telephone2 = request.getTelephone2();
        }

        if (Optional.ofNullable(request.getTelephone3()).isPresent() && !request.getTelephone3().equals(telephone3)) {
            this.telephone3 = request.getTelephone3();
        }

        if (Optional.ofNullable(request.getAddress()).isPresent() && !request.getAddress().equals(address)) {
            this.address = request.getAddress();
        }
        if (Optional.ofNullable(request.getAddressComplement()).isPresent() && !request.getAddressComplement().equals(addressComplement)) {
            this.addressComplement = request.getAddressComplement();
        }
        if (Optional.ofNullable(request.getAddressZipCode()).isPresent() && !request.getAddressZipCode().equals(addressZipCode)) {
            this.addressZipCode = request.getAddressZipCode();
        }
        if (Optional.ofNullable(request.getCity()).isPresent() && !request.getCity().equals(city)) {
            this.city = request.getCity();
        }
        if (Optional.ofNullable(request.getOpenHours()).isPresent() && !request.getOpenHours().equals(openHours)) {
            this.openHours = request.getOpenHours();
        }
        if (Optional.ofNullable(request.getClosingHours()).isPresent() && !request.getClosingHours().equals(closingHours)) {
            this.closingHours = request.getClosingHours();
        }
        if (Optional.ofNullable(request.getEmail()).isPresent() && !request.getEmail().equals(email)) {
            this.email = request.getEmail();
        }
        if (Optional.ofNullable(request.getImage()).isPresent() && !request.getImage().equals(image)) {
            this.image = request.getImage();
        }
        if (Optional.ofNullable(request.getWebsite()).isPresent() && !request.getWebsite().equals(website)) {
            this.website = request.getWebsite();
        }
        if (Optional.ofNullable(request.getFacebook()).isPresent() && !request.getFacebook().equals(facebook)) {
            this.facebook = request.getFacebook();
        }
        if (Optional.ofNullable(request.getInstagran()).isPresent() && !request.getInstagran().equals(instagran)) {
            this.instagran = request.getInstagran();
        }
        if (Optional.ofNullable(request.getHistory()).isPresent() && !request.getHistory().equals(history)) {
            this.history = request.getHistory();
        }
    }

}
