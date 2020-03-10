/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.clicktop.app.service;

import com.clicktop.app.model.Profile;
import com.clicktop.app.model.User;
import com.clicktop.app.repository.UserRepository;
import com.clicktop.app.request.UserPhoto;
import com.clicktop.app.request.UserRequest;
import com.clicktop.app.specification.UserSpecification;

import static com.clicktop.app.utils.Constants.*;
import java.text.MessageFormat;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
public class UserService {

    @Autowired(required = true)
    private UserRepository repository;

    @Autowired
    private ProfileService profileService;
    
    @Autowired
    private EmailService emailService;

    @Transactional
    public void save(User request) {
        this.repository.save(request);
    }

    @Transactional
    public void save(UserRequest request) throws Exception {
        
        
        
        boolean exists = this.repository.findByEmail(request.getEmail()).isPresent();
        
        if(exists){
            throw new Exception("Email : " + request.getEmail() + " já esta cadastrado em nossa base!");
        }
        
        Map<String,String>paramenters = new HashMap<>();
        paramenters.put(EMAIL_SUBJECT_KEY, "Primeiro Acesso");
        paramenters.put(EMAIL_MESSAGE_KEY, MessageFormat.format(EMAIL_FIRST_ACCESS_CONTENT, request.getFirstName(),request.getEmail(),request.getPassword()));
        
        
        
        
        User user = new User(request);

        Profile profile = profileService
                .listAll()
                .stream()
                .filter(p -> p.getId().equals(request.getProfile()))
                .findFirst()
                .orElseThrow(() -> new Exception("Não foi encontrado nenhum perfil!"));

        user.setProfile(profile);

        this.repository.save(user);
        
        if(user.getReceiveEmail().equals(1L)){
            this.emailService.sendEmail(request.getEmail(), paramenters);
        }
        
        
    }

    
    
    @Transactional
    public void update(UserRequest request) throws Exception {

        User user = this.repository
                .findById(request.getId())
                .orElseThrow(() -> new Exception("Não foi possivel localizar o usuário!"));

        user.update(request);

        if (!request.getProfile().equals(user.getProfile().getId())) {
            Profile profile = profileService.getById(request.getProfile());
            user.setProfile(profile);
        }

        this.repository.save(user);
    }
    
    @Transactional
    public void updatePhoto(UserPhoto request) throws Exception {

        User user = this.repository
                .findById(request.getId())
                .orElseThrow(() -> new Exception("Não foi possivel localizar o usuário!"));

        

        if (!request.getPhoto().equals(user.getPhoto())) {
            user.setPhoto(request.getPhoto());
        }

        this.repository.save(user);
    }

    @Transactional
    public void delete(Long id) throws Exception {        
        this.repository.deleteById(id);
    }

    @Transactional
    public void delete(String email) throws Exception {

        User user = this.repository
                .findByEmail(email)
                .orElseThrow(() -> new Exception("Não foi possivel localizar o usuário!"));
        this.repository.delete(user);

    }
    
    public boolean checkEmail(String email){
        return this.repository.findByEmail(email).isPresent();
    }

    @Transactional(readOnly = true)
    public Page<User> listBy(Long id, String email, String name, String profile, Pageable page) {

        List<Specification<User>> predicates = new ArrayList<>();

        if (Optional.ofNullable(id).isPresent()) {
            predicates.add(UserSpecification.id(id));
        }

        if (Optional.ofNullable(email).isPresent()) {
            predicates.add(UserSpecification.email(email));
        }

        if (Optional.ofNullable(name).isPresent()) {
            predicates.add(UserSpecification.firstName(name));
        }

        if (Optional.ofNullable(profile).isPresent()) {

            List<Profile> profiles = profileService.listByName(profile);

            if (!profiles.isEmpty()) {
                predicates.add(UserSpecification.profile(profiles.get(0)));
            }

        }

        Specification<User> specification = predicates.stream().reduce(Specification::and).orElse(null);

        return this.repository.findAll(specification, page);

    }
}
