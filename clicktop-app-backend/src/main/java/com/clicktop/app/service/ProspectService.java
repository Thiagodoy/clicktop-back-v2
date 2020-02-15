/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.clicktop.app.service;

import com.clicktop.app.model.Prospect;
import com.clicktop.app.repository.ProspectRepository;
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
public class ProspectService {
    
    @Autowired
    private ProspectRepository repository;

    @Transactional(readOnly = true)
    public Page<Prospect> findAll(Pageable page) {
        return this.repository.findAll(page);
    }

    @Transactional
    public void save(Prospect prospect) {
        this.repository.save(prospect);
    }

    @Transactional
    public void delete(Long prospect) {
        this.repository.deleteById(prospect);
    }

    @Transactional
    public void update(Prospect request) throws Exception {

        Prospect prospect = this.repository.findById(request.getId()).orElseThrow(() -> new Exception("Nenhum prosprect foi localizado!"));
        prospect.update(request);
        this.repository.save(prospect);
    }
    
    
}
