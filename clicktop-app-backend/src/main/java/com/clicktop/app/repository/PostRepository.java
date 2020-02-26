/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.clicktop.app.repository;

import com.clicktop.app.dto.PostStatusDTO;
import com.clicktop.app.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author thiag
 */
@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    @Query(nativeQuery = true)
    PostStatusDTO status();
}
