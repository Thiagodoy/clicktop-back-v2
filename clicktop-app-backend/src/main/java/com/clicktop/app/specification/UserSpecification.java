/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.clicktop.app.specification;

import com.clicktop.app.model.User;
import com.clicktop.app.model.User_;
import org.springframework.data.jpa.domain.Specification;

/**
 *
 * @author thiag
 */
public class UserSpecification {

    public static Specification<User> firstName(String firstName) {
        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.like(criteriaBuilder.upper(root.get(User_.firstName)), firstName.toUpperCase() + "%");
    }

    public static Specification<User> email(String email) {
        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.like(criteriaBuilder.upper(root.get(User_.email)), email.toUpperCase() + "%");
    }
    
    public static Specification<User> id(Long id) {
        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.equal(root.get(User_.id), id);
    }
    
    public static Specification<User> status(User.UserStatus status) {
        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.equal(root.get(User_.status), status);
    }

}
