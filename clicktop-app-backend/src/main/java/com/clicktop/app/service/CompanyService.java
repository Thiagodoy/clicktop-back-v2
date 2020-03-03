/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.clicktop.app.service;

import com.clicktop.app.model.Category;
import com.clicktop.app.model.City;
import com.clicktop.app.model.Company;
import com.clicktop.app.model.Plan;
import com.clicktop.app.model.Profile;
import com.clicktop.app.model.User;
import com.clicktop.app.repository.CompanyRepository;
import com.clicktop.app.specification.CompanySpecification;
import static com.clicktop.app.utils.Constants.EMAIL_FIRST_ACCESS_CONTENT;
import static com.clicktop.app.utils.Constants.EMAIL_MESSAGE_KEY;
import static com.clicktop.app.utils.Constants.EMAIL_SUBJECT_KEY;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.MessagingException;
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
    
    @Autowired
    private EmailService emailService;
    
    
    @Autowired
    private GoogleAddressApiService gaas;

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
        
        
        Map<String,String>paramenters = new HashMap<>();
        paramenters.put(EMAIL_SUBJECT_KEY, "Primeiro Acesso");
        paramenters.put(EMAIL_MESSAGE_KEY, MessageFormat.format(EMAIL_FIRST_ACCESS_CONTENT, company.getName(),company.getEmail(),"Clicktop2020"));
        
        this.emailService.sendEmail(company.getEmail(), paramenters);
        service.save(user);
        
        Thread t = new Thread(()->{
            
            try {
                gaas.getLocation(company1);
            } catch (MessagingException ex) {
                Logger.getLogger(CompanyService.class.getName()).log(Level.SEVERE, "[google]", ex);
            }
        });
        
        t.start();
        
        
        
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
    public Page<Company> list(String name, String email, Long spotlight, Long planId, String type, Long category, Pageable page) throws Exception {

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

        if (Optional.ofNullable(type).isPresent()) {
            Company.CompanyType tt = Company.CompanyType.valueOf(type);
            predicatives.add(CompanySpecification.type(tt));
        }

        if (Optional.ofNullable(category).isPresent()) {
            Category c = new Category();
            c.setId(category);
            predicatives.add(CompanySpecification.category(c));
        }

        if (Optional.ofNullable(planId).isPresent()) {
            Plan plan = planService.findAll().stream().filter(p -> p.getId().equals(planId)).findFirst().orElseThrow(() -> new Exception("Plano não encontrado!"));
            predicatives.add(CompanySpecification.plan(plan));
        }

        Specification<Company> specification = predicatives.stream().reduce(Specification::and).orElse(null);

        return this.repository.findAll(specification, page);

    }

    @Transactional(readOnly = true)
    public Page<Company> listLastCompanys(Pageable page) throws Exception {
        return this.repository.findAll(page);
    }

    @Transactional(readOnly = true)
    public Page<Company> listSpotLightCompanys(Pageable page) throws Exception {

        List<Specification<Company>> predicatives = new ArrayList<>();

        predicatives.add(CompanySpecification.spotLight(1L));

        Specification<Company> specification = predicatives.stream().reduce(Specification::and).orElse(null);

        return this.repository.findAll(specification, page);

    }

    @Transactional(readOnly = true)
    public Company findById(Long id) throws Exception {
        return this.repository.findById(id).orElseThrow(() -> new Exception("Nenhuma empresa foi encontrada!"));
    }
    
    
     @Transactional(readOnly = true)
    public Page<Company> findByConsult(User user, Pageable page) throws Exception {
        return this.repository.findByConsultant(user, page);
    }
    

}
