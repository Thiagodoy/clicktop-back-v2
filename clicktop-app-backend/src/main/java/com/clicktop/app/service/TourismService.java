/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.clicktop.app.service;

import com.clicktop.app.model.Tourism;
import com.clicktop.app.repository.TourismRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author thiag
 */
@Service
public class TourismService {

    @Autowired
    private TourismRepository repository;

    @Transactional(readOnly = true)
    public List<Tourism> findAll() {
        return this.repository.findAll();
    }

    @Transactional
    public void save(Tourism tour) {
        this.repository.save(tour);
    }

    @Transactional
    public void delete(Long id) {
        this.repository.deleteById(id);
    }

    @Transactional
    public void update(Tourism request) throws Exception {

        Tourism tour = this.repository.findById(request.getId()).orElseThrow(() -> new Exception("Nenhum Turismo localizado!"));
        tour.update(request);
        this.repository.save(tour);
    }

}
