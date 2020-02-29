/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.clicktop.app.jobs;

import com.clicktop.app.model.Post;
import com.clicktop.app.service.PostService;
import java.util.logging.Level;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 *
 * @author thiag
 */
@Component
public class PublisherJob {

    private final long SECONDS = 1000;
    private final long MINUTES = SECONDS * 60;

    @Autowired
    private PostService postService;

    @Scheduled(fixedDelay  = MINUTES * 5)
    public void publish() {

        try {
            this.postService.getPostScheduled().stream().forEach(post -> {
                post.setStatus(Post.PostStatus.PUBLISHED);
                try {
                    this.postService.update(post);
                } catch (Exception ex) {
                    java.util.logging.Logger.getLogger(PublisherJob.class.getName()).log(Level.SEVERE, "[publish]", ex);
                }
            });

        } catch (Exception e) {
            java.util.logging.Logger.getLogger(PublisherJob.class.getName()).log(Level.SEVERE, "[publish]", e);
        }

    }

}
