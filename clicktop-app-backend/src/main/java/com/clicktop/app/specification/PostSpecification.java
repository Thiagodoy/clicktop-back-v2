/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.clicktop.app.specification;

import com.clicktop.app.model.Post;
import com.clicktop.app.model.Post_;
import org.springframework.data.jpa.domain.Specification;

/**
 *
 * @author thiag
 */
public class PostSpecification {

    public static Specification<Post> company(Long company) {
        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.equal(root.get(Post_.company), company);
    }

    public static Specification<Post> status(Post.PostStatus status) {
        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.equal(root.get(Post_.status), status);
    }
    
    public static Specification<Post> companyName(String companyName) {
        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.like(root.get(Post_.companyName), "%" + companyName + "%");
    }
    
    public static Specification<Post> key(String key) {
        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.like(root.get(Post_.key), "%" + key + "%");
    }
    
     public static Specification<Post> category(Long category) {
        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.equal(root.get(Post_.category), category);
    }

}
