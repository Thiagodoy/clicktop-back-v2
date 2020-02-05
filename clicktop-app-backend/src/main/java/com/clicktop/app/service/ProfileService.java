/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.clicktop.app.service;

import com.clicktop.app.model.Profile;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author thiag
 */
@Service
public class ProfileService {
    
    
    
    
    @Transactional(readOnly = true)
    public Profile getById(Long id){
        return null;
    }
    
    
}
