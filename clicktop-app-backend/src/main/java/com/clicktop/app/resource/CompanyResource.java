/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.clicktop.app.resource;

import com.clicktop.app.model.Company;
import com.clicktop.app.model.User;
import com.clicktop.app.service.CompanyService;
import com.clicktop.app.utils.Url;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author thiag
 */
@RestController
@RequestMapping(value = Url.URL_COMPANY)
public class CompanyResource {

    @Autowired
    private CompanyService service;

    @RequestMapping(method = RequestMethod.GET)
    @ApiOperation(response = Company.class, responseContainer = "List", value = "")
    public ResponseEntity get(@RequestParam(required = false, name = "id") Long id,
            @RequestParam(required = false, name = "name") String name,
            @RequestParam(required = false, name = "email") String email,
            @RequestParam(required = false, name = "planId") Long planId,
            @RequestParam(required = false, name = "spotlight") Long spotlight,
            @RequestParam(required = false, name = "category") Long category,
            @RequestParam(required = false, name = "type") String type,
            @RequestParam(required = false, name = "postType") String postType,
            @RequestParam(required = false, name = "page", defaultValue = "0") int page,
            @RequestParam(required = false, name = "size", defaultValue = "10") int size,
            UsernamePasswordAuthenticationToken principal) {
        try {

            if (Optional.ofNullable(principal).isPresent()) {

                User user = (User) principal.getPrincipal();

                
                if (user.getProfile().getId().equals(3L)) {         //Profile Company
                    Company response = this.service.findById(user.getCompany());
                    return ResponseEntity.ok(response);
                }else if (user.getProfile().getId().equals(2L)) {  //Profile Consult
                    Page<Company> response = this.service.findByConsult(user, PageRequest.of(page, size));
                    return ResponseEntity.ok(response);
                }

            }

            if (id != null) {
                Company response = this.service.findById(id);
                return ResponseEntity.ok(response);
            } else if(postType != null){
                List<Company> response = this.service.findBysStatusPost(postType);
                return ResponseEntity.ok(response);
            }else {
                Page<Company> response = this.service.list(name, email, spotlight, planId, type, category,null, PageRequest.of(page, size));
                return ResponseEntity.ok(response);
            }

        } catch (Exception e) {
            Logger.getLogger(CompanyResource.class.getName()).log(Level.SEVERE, "[get]", e);
            return ResponseEntity.status(HttpStatus.resolve(500)).body(e.getMessage());

        }
    }

    @RequestMapping(value = "/last-companys", method = RequestMethod.GET)
    @ApiOperation(response = Company.class, responseContainer = "List", value = "")
    public ResponseEntity getLastCompanys(
            @RequestParam(required = false, name = "page", defaultValue = "0") int page,
            @RequestParam(required = false, name = "size", defaultValue = "10") int size) {
        try {
            Page response = this.service.listLastCompanys(PageRequest.of(page, size, Sort.by("createAt").descending()));
            return ResponseEntity.ok(response);

        } catch (Exception e) {
            Logger.getLogger(CompanyResource.class.getName()).log(Level.SEVERE, "[get]", e);
            return ResponseEntity.status(HttpStatus.resolve(500)).body(e.getMessage());

        }
    }

    @RequestMapping(value = "/spotlight-companys", method = RequestMethod.GET)
    @ApiOperation(response = Company.class, responseContainer = "List", value = "")
    public ResponseEntity getSpotlightCompanys(
            @RequestParam(required = false, name = "page", defaultValue = "0") int page,
            @RequestParam(required = false, name = "size", defaultValue = "10") int size) {
        try {
            Page response = this.service.listSpotLightCompanys(PageRequest.of(page, size, Sort.by("createAt").descending()));
            return ResponseEntity.ok(response);

        } catch (Exception e) {
            Logger.getLogger(CompanyResource.class.getName()).log(Level.SEVERE, "[get]", e);
            return ResponseEntity.status(HttpStatus.resolve(500)).body(e.getMessage());

        }
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity post(@RequestBody Company request) {
        try {
            this.service.save(request);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            Logger.getLogger(CompanyResource.class.getName()).log(Level.SEVERE, "[post]", e);
            return ResponseEntity.status(HttpStatus.resolve(500)).body(e.getMessage());
        }
    }

    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity put(@RequestBody Company request) {
        try {
            this.service.update(request);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            Logger.getLogger(CompanyResource.class.getName()).log(Level.SEVERE, "[post]", e);
            return ResponseEntity.status(HttpStatus.resolve(500)).body(e.getMessage());
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity delete(@PathVariable(name = "id") Long id) {
        try {
            this.service.delete(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            Logger.getLogger(CompanyResource.class.getName()).log(Level.SEVERE, "[delete]", e);
            return ResponseEntity.status(HttpStatus.resolve(500)).body(e.getMessage());
        }
    }

}
