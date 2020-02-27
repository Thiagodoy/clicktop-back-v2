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

}
