/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.clicktop.app.service;

import com.clicktop.app.model.Profile;
import com.clicktop.app.repository.ProfileRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author thiag
 */
@Service
public class ProfileService {
    
    @Autowired
    private ProfileRepository repository;
    
    @Transactional(readOnly = true)
    public Profile getById(Long id) throws Exception {
        return this.repository.findById(id).orElseThrow(() -> new Exception("Não foi possivel localizar o perfil!"));
    }
    
    @Transactional
    public void save(Profile profile){
        
        if(profile.getId() != null){
            this.repository.deleteById(profile.getId());
        }
        
        this.repository.save(profile);
    }
    
    @Transactional
    public void delete(Long id){
        this.repository.deleteById(id);
    }
    
    @Transactional(readOnly = true)
    public List<Profile>listByName(String name){
        return this.repository.findByNameContaining(name);
    }
    
    
    @Transactional(readOnly = true)
    public List<Profile>listAll(){
        return this.repository.findAll();
    }
    
}
