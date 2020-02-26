/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.clicktop.app.service;

import com.clicktop.app.dto.PostStatusDTO;
import com.clicktop.app.model.Post;
import com.clicktop.app.repository.PostRepository;
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
public class PostService {

    @Autowired
    private PostRepository repository;

    @Transactional
    public void save(Post post) {
        this.repository.save(post);
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
        return this.repository.status();
    }
    
    @Transactional(readOnly = true)
    public Page list(Long company,String status,Pageable page){
        
        
        return null;
        
        
        
    }

}
