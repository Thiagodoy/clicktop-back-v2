/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.clicktop.app.service;

import com.clicktop.app.dto.PlanStatusDTO;
import com.clicktop.app.repository.PlanRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author thiag
 */
@Service
public class DashBoardService {
    
    
    @Autowired
    private PlanRepository planRepository;
    
    
    public List<PlanStatusDTO> listStatusPlan(){
        return this.planRepository.listStatus();
    }
    
    
}
