/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.clicktop.app.model;

import com.clicktop.app.dto.PostStatusDTO;
import java.time.LocalDateTime;
import java.util.Optional;
import javax.persistence.Column;
import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedNativeQuery;
import javax.persistence.PrePersist;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.Table;
import lombok.Data;

/**
 *
 * @author thiag
 */


@SqlResultSetMapping(name = "PostStatus", classes = @ConstructorResult(
        targetClass = PostStatusDTO.class,
        columns = {                              
            @ColumnResult(name = "SCHEDULED", type = Long.class),
            @ColumnResult(name = "PENDING", type = Long.class),
            @ColumnResult(name = "APPROVED", type = Long.class),
            @ColumnResult(name = "DISAPPROVED", type = Long.class),
            @ColumnResult(name = "PUBLISHED", type = Long.class)
        }))


@NamedNativeQuery(query = "select \n"
        + "(select count(1) from post p where p.status = 'SCHEDULED') as SCHEDULED,\n"
        + "(select count(1) from post p where p.status = 'PENDING') as PENDING,\n"
        + "(select count(1) from post p where p.status = 'APPROVED') as APPROVED,\n"
        + "(select count(1)  from post p where p.status = 'DISAPPROVED') as DISAPPROVED,\n"
        + "(select count(1)  from post p where p.status = 'PUBLISHED') as PUBLISHED", 
        resultSetMapping = "PostStatus", name = "Post.status")

@Entity
@Table(name = "post")
@Data
public class Post {

    public enum PostStatus {

        SCHEDULED,
        PENDING,
        APPROVED,
        DISAPPROVED,
        PUBLISHED

    }

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private PostStatus status;

    @Column(name = "scheduled_time")
    private LocalDateTime scheduleTime;

    @Column(name = "published_time")
    private LocalDateTime publishedTime;

    @Column(name = "created_time")
    private LocalDateTime createdTime;

    @Column(name = "company", nullable = false)
    private Long company;

    @Column(name = "photo")
    private String photo;

    @Column(name = "description")
    private String description;

    @Column(name = "feedback")
    private String feedback;

    @Column(name = "title")
    private String title;

    @Column(name = "key_post")
    private String key;

    @PrePersist
    public void generateDate() {
        this.createdTime = LocalDateTime.now();
    }

    public void update(Post post) {

        if (Optional.ofNullable(post.getStatus()).isPresent() && !post.getStatus().equals(status)) {
            this.status = post.getStatus();
        }

        if (Optional.ofNullable(post.getScheduleTime()).isPresent() && !post.getScheduleTime().equals(scheduleTime)) {
            this.scheduleTime = post.getScheduleTime();
        }

        if (Optional.ofNullable(post.getPublishedTime()).isPresent() && !post.getPublishedTime().equals(publishedTime)) {
            this.publishedTime = post.getPublishedTime();
        }

        if (Optional.ofNullable(post.getCompany()).isPresent() && !post.getCompany().equals(company)) {
            this.company = post.getCompany();
        }

        if (Optional.ofNullable(post.getPhoto()).isPresent() && !post.getPhoto().equals(photo)) {
            this.photo = post.getPhoto();
        }

        if (Optional.ofNullable(post.getDescription()).isPresent() && !post.getDescription().equals(description)) {
            this.description = post.getDescription();
        }

        if (Optional.ofNullable(post.getFeedback()).isPresent() && !post.getFeedback().equals(feedback)) {
            this.feedback = post.getFeedback();
        }

        if (Optional.ofNullable(post.getTitle()).isPresent() && !post.getTitle().equals(title)) {
            this.title = post.getTitle();
        }

        if (Optional.ofNullable(post.getKey()).isPresent() && !post.getKey().equals(key)) {
            this.key = post.getKey();
        }

    }

}
