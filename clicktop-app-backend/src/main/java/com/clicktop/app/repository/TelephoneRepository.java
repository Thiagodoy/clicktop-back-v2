/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.clicktop.app.repository;

import com.clicktop.app.model.Telephone;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author thiag
 */
public interface TelephoneRepository extends JpaRepository<Telephone, Long> {
    
}
