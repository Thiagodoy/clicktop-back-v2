/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.clicktop.app.service;

import com.clicktop.app.model.Plan;
import com.clicktop.app.repository.PlanRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author thiag
 */
@Service
public class PlanService {

    @Autowired
    private PlanRepository repository;

    @Transactional(readOnly = true)
    public List<Plan> findAll() {
        return this.repository.findAll();
    }

    @Transactional
    public void save(Plan plan) {
        this.repository.save(plan);
    }

    @Transactional
    public void delete(Long plan) {
        this.repository.deleteById(plan);
    }

    @Transactional
    public void update(Plan request) throws Exception {

        Plan plan = this.repository.findById(request.getId()).orElseThrow(() -> new Exception("Nenhum plano localizado!"));
        plan.update(request);
        this.repository.save(plan);
    }

}
