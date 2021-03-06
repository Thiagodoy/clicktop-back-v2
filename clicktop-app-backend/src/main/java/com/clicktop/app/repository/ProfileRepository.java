/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.clicktop.app.repository;

import com.clicktop.app.model.Profile;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author thiag
 */
@Repository
public interface ProfileRepository extends JpaRepository<Profile, Long> {
    
    
    List<Profile> findByNameContaining(String name); 
    
}
