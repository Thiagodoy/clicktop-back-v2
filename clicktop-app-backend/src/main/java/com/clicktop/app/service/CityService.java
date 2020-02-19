/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.clicktop.app.service;

import com.clicktop.app.model.Category;
import com.clicktop.app.model.City;
import com.clicktop.app.repository.CategoryRepository;
import com.clicktop.app.repository.CityRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
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
    public List<City> findAll() {
        return this.repository.findAll();
    }
    
    @Transactional(readOnly = true)
    public City findById(Long id) throws Exception {
        return this.repository.findById(id).orElseThrow(() -> new Exception("Nehuma cidade foi encontrada!"));
    }

}
