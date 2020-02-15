/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.clicktop.app.service;

import com.clicktop.app.model.Profile;
import com.clicktop.app.model.User;
import com.clicktop.app.repository.UserRepository;
import com.clicktop.app.request.UserRequest;
import com.clicktop.app.response.UserResponse;
import com.clicktop.app.specification.UserSpecification;

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
public class UserService {

    @Autowired(required = true)
    private UserRepository repository;

    @Autowired
    private ProfileService profileService;

    @Transactional
    public void save(UserRequest request) {
        User user = new User(request);
        this.repository.save(user);
    }

    @Transactional
    public void update(UserRequest request) throws Exception {

        User user = this.repository
                .findById(request.getId())
                .orElseThrow(() -> new Exception("Não foi possivel localizar o usuário!"));

        user.update(request);

        if (!request.getProfile().equals(user.getProfile().getId())) {
            Profile profile = profileService.getById(request.getId());
            user.setProfile(profile);
        }

        this.repository.save(user);
    }

    @Transactional
    public void delete(Long id) throws Exception {

        User user = this.repository
                .findById(id)
                .orElseThrow(() -> new Exception("Não foi possivel localizar o usuário!"));

        user.setStatus(User.UserStatus.CANCELED);
        this.repository.save(user);

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
