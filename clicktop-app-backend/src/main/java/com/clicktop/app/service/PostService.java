/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.clicktop.app.service;

import com.clicktop.app.dto.PostStatusDTO;
import com.clicktop.app.model.Post;
import com.clicktop.app.repository.PostRepository;
import com.clicktop.app.specification.PostSpecification;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
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
public class PostService {

    @Autowired
    private PostRepository repository;

    @Transactional
    public void save(Post post) {
        this.repository.save(post);
    }

    @Transactional
    public void delete(Long id) {
        this.repository.deleteById(id);
    }

    @Transactional
    public void update(Post request) throws Exception {

        Post post = this.repository.findById(request.getId()).orElseThrow(() -> new Exception("Nenhum post foi encontrado!"));
        post.update(request);

        this.repository.save(post);
    }

    @Transactional(readOnly = true)
    public PostStatusDTO status() {
        return this.repository.status();
    }

    @Transactional(readOnly = true)
    public PostStatusDTO statusByCompany(Long company) {
        return this.repository.statusByCompany(company);
    }

    @Transactional(readOnly = true)
    public Page list(Long company, String status, String search,Long category, Pageable page) {

        
         List<Specification<Post>> predicatives = new ArrayList<>();
         
         if (Optional.ofNullable(category).isPresent()) {
            predicatives.add(PostSpecification.category(category));
        }

        if (Optional.ofNullable(company).isPresent()) {
            predicatives.add(PostSpecification.company(company));
        }

        if (Optional.ofNullable(status).isPresent()) {
            Post.PostStatus ps = Post.PostStatus.valueOf(status);
            predicatives.add(PostSpecification.status(ps));
        } 
        
        
        
         if (Optional.ofNullable(search).isPresent()) {
            
             PostSpecification.key(search);
             PostSpecification.companyName(search);
             Specification<Post> spec = Arrays
                     .asList(PostSpecification.key(search),PostSpecification.companyName(search))
                     .stream()
                     .reduce(Specification::or)
                     .orElse(null);
             
            predicatives.add(spec);
        }
        
        

        Specification<Post> specification = predicatives.stream().reduce(Specification::and).orElse(null);
        
        return this.repository.findAll(specification, page);
    }
    
    @Transactional(readOnly = true)
    public List<Post> getPostScheduled(){
        return this.repository.listPostScheduled(LocalDate.now());
    }

}
