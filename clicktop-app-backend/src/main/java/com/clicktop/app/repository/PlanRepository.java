/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.clicktop.app.repository;

import com.clicktop.app.dto.PlanStatusDTO;
import com.clicktop.app.model.Plan;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author thiag
 */
@Repository
public interface PlanRepository extends JpaRepository<Plan, Long>{
    
    
    @Query(nativeQuery = true)
    List<PlanStatusDTO>listStatus();
}
