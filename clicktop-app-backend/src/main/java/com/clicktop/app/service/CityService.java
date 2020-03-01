/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.clicktop.app.service;

import com.clicktop.app.model.City;
import com.clicktop.app.repository.CityRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author thiag
 */
@Service
public class CityService {

    @Autowired
    private CityRepository repository;

    @Transactional(readOnly = true)
    public Page<City> findAll(String name, Pageable page) {

        if (Optional.ofNullable(name).isPresent()) {
            return this.repository.findByNameContaining(name, page);
        } else {
            return this.repository.findAll(page);
        }

    }

    @Transactional(readOnly = true)
    public City findById(Long id) throws Exception {
        return this.repository.findById(id).orElseThrow(() -> new Exception("Nehuma cidade foi encontrada!"));
    }

}
