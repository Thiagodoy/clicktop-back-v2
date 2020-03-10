/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.clicktop.app.repository;

import com.clicktop.app.model.Tourism;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author thiag
 */
public interface TourismRepository extends JpaRepository<Tourism, Long>{
    
    List<Tourism> findByCategory(Long category);
    
}
