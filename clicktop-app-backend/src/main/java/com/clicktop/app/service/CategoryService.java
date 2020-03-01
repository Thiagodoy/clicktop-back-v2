/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.clicktop.app.service;

import com.clicktop.app.model.Category;
import com.clicktop.app.repository.CategoryRepository;
import java.util.List;
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
public class CategoryService {

    @Autowired
    private CategoryRepository repository;

    @Transactional(readOnly = true)
    public Page<Category> findAll(Pageable page) {
        return this.repository.findAll(page);
    }

    @Transactional(readOnly = true)
    public List<Category> findAll() {
        return this.repository.findAll();
    }

    @Transactional
    public void save(Category plan) {
        this.repository.save(plan);
    }

    @Transactional
    public void delete(Long plan) {
        this.repository.deleteById(plan);
    }

    @Transactional
    public void update(Category request) throws Exception {

        Category category = this.repository.findById(request.getId()).orElseThrow(() -> new Exception("Nenhuma categoria localizada!"));
        category.update(request);
        this.repository.save(category);
    }

}
