/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.clicktop.app.specification;

import com.clicktop.app.model.Category;
import com.clicktop.app.model.Company;
import com.clicktop.app.model.Company_;
import com.clicktop.app.model.Plan;
import org.springframework.data.jpa.domain.Specification;

/**
 *
 * @author thiag
 */
public class CompanySpecification {

    public static Specification<Company> name(String name) {
        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.like(criteriaBuilder.upper(root.get(Company_.name)), name.toUpperCase() + "%");
    }

    public static Specification<Company> email(String email) {
        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.like(criteriaBuilder.upper(root.get(Company_.email)), email.toUpperCase() + "%");
    }

    public static Specification<Company> plan(Plan plan) {
        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.equal(root.get(Company_.plan), plan);
    }

    public static Specification<Company> spotLight(Long spotlight) {
        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.equal(root.get(Company_.spotlight), spotlight);
    }

    public static Specification<Company> type(Company.CompanyType type) {
        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.equal(root.get(Company_.type), type);
    }

    public static Specification<Company> category(Category category) {
        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.equal(root.get(Company_.category), category);
    }
}
