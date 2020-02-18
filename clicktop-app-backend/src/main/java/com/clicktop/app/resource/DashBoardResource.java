/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.clicktop.app.resource;

import com.clicktop.app.dto.PlanStatusDTO;
import com.clicktop.app.service.DashBoardService;
import com.clicktop.app.utils.Url;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author thiag
 */
@RestController
@RequestMapping(value = Url.URL_DASHBOARD)
public class DashBoardResource {

    @Autowired
    private DashBoardService service;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity getInformationAboutPlan() {
        try {
            List<PlanStatusDTO> response = this.service.listStatusPlan();
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Logger.getLogger(DashBoardResource.class.getName()).log(Level.SEVERE, "[]", e);
            return ResponseEntity.status(HttpStatus.resolve(500)).body(e.getMessage());
        }
    }

}
