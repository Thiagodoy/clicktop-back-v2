/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.clicktop.app.service;

import com.clicktop.app.model.City;
import com.clicktop.app.model.Company;
import com.clicktop.app.model.Plan;
import com.clicktop.app.model.Profile;
import com.clicktop.app.model.User;
import com.clicktop.app.repository.CompanyRepository;
import com.clicktop.app.specification.CompanySpecification;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author thiag
 */
@Service
public class CompanyService {

    @Autowired
    private CompanyRepository repository;

    @Autowired
    private UserService service;

    @Autowired
    private ProfileService profileService;

    @Autowired
    private PlanService planService;

    @Autowired
    private CityService cityService;

    @Transactional
    public void save(Company company) throws Exception {

        Plan plan = planService.findAll().stream().filter(p -> p.getId().equals(company.getPlan().getId())).findFirst().orElseThrow(() -> new Exception("Plano não encontrado!"));
        City city = cityService.findById(company.getCity().getId());

        company.setCity(city);
        company.setPlan(plan);

        Company company1 = this.repository.save(company);
        User user = User.createUserDefault();
        user.setEmail(company.getEmail());
        user.setFirstName(company.getName());
        user.setCompany(company1.getId());

        Profile profile = profileService
                .listAll()
                .stream()
                .filter(p -> p.getId().equals(3L))
                .findFirst()
                .orElseThrow(() -> new Exception("Não foi encontrado nenhum perfil!"));

        user.setProfile(profile);

        service.save(user);
    }

    @Transactional
    public void update(Company request) throws Exception {

        Company entity = this.repository.findById(request.getId()).orElseThrow(() -> new Exception("Não foi encontrado nenhuma empresa!"));       

        
        
        
        
        entity.update(request);
        this.repository.save(entity);

        
        
        if (Optional.ofNullable(request.getEmail()).isPresent() && request.getEmail().equals(entity.getEmail())) {
            service.delete(entity.getEmail());
            User user = User.createUserDefault();
            user.setEmail(request.getEmail());
            user.setFirstName(request.getName());
            user.setCompany(request.getId());
            service.save(user);
        }
    }

    @Transactional
    public void delete(Long id) throws Exception {

        Company company = this.findById(id);

        this.service.delete(company.getEmail());

        this.repository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public Page<Company> list(String name, String email, Long spotlight, Long planId, Pageable page) throws Exception {

        List<Specification<Company>> predicatives = new ArrayList<>();

        if (Optional.ofNullable(name).isPresent()) {
            predicatives.add(CompanySpecification.name(name));
        }

        if (Optional.ofNullable(email).isPresent()) {
            predicatives.add(CompanySpecification.email(email));
        }

        if (Optional.ofNullable(spotlight).isPresent()) {
            predicatives.add(CompanySpecification.spotLight(spotlight));
        }

        if (Optional.ofNullable(planId).isPresent()) {
            Plan plan = planService.findAll().stream().filter(p -> p.getId().equals(planId)).findFirst().orElseThrow(() -> new Exception("Plano não encontrado!"));
            predicatives.add(CompanySpecification.plan(plan));
        }

        Specification<Company> specification = predicatives.stream().reduce(Specification::and).orElse(null);

        return this.repository.findAll(specification, page);

    }

    @Transactional(readOnly = true)
    public Company findById(Long id) throws Exception {
        return this.repository.findById(id).orElseThrow(() -> new Exception("Nenhuma empresa foi encontrada!"));
    }

}