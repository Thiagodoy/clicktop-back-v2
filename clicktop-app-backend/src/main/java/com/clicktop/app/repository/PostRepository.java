/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.clicktop.app.repository;

import com.clicktop.app.dto.PostStatusDTO;
import com.clicktop.app.model.Post;
import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author thiag
 */
@Repository
public interface PostRepository extends JpaRepository<Post, Long>, JpaSpecificationExecutor<Post> {

    @Query(nativeQuery = true)
    PostStatusDTO status();
    @Query(nativeQuery = true)
    PostStatusDTO statusByCompany(@Param("company")Long company);
    
    List<Post> findByStatus(Post.PostStatus status);
    
    @Modifying
    void deleteByCompany(Long company);
    
    
    @Query(value = "select * from post where status = 'SCHEDULED' and scheduled_time <= :date", nativeQuery = true)
    List<Post>listPostScheduled(@Param("date")LocalDate date);
}
