/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.clicktop.app.repository;

import com.clicktop.app.model.Prospect;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author thiag
 */
@Repository
public interface ProspectRepository extends JpaRepository<Prospect, Long> {

    Page<Prospect> findByStatus(Prospect.ProspectStatus status, Pageable page);
}
