/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.clicktop.app.resource;

import com.clicktop.app.model.User;
import com.clicktop.app.request.UserRequest;
import com.clicktop.app.service.UserService;
import com.clicktop.app.utils.Url;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
@RequestMapping(value = Url.URL_USER)
public class UserResource {

    @Autowired
    private UserService service;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity get(
            @RequestParam(name = "id", required = false) Long id,
            @RequestParam(name = "email", required = false) String email,
            @RequestParam(name = "name", required = false) String name,
            @RequestParam(name = "profile", required = false) String profile,
            @RequestParam(name = "page", required = false, defaultValue = "0") int page,
            @RequestParam(name = "size", required = false, defaultValue = "10") int size) {

        try {

            Page<User> response = this.service.listBy(id, email, name, profile, PageRequest.of(page, size, Sort.by("firstName")));
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Logger.getLogger(UserResource.class.getName()).log(Level.SEVERE, "[get]", e);
            return ResponseEntity.status(HttpStatus.resolve(500)).body(e.getMessage());
        }
    }
    
    
    @RequestMapping(value = "/checkEmail", method = RequestMethod.GET)
    public ResponseEntity checkEmail(            
            @RequestParam(name = "email", required = false) String email            
            ) {

        try {

            boolean response = this.service.checkEmail(email);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Logger.getLogger(UserResource.class.getName()).log(Level.SEVERE, "[get]", e);
            return ResponseEntity.status(HttpStatus.resolve(500)).body(e.getMessage());
        }
    }
    

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity delete(@PathVariable(name = "id")Long id) {
        try {
            this.service.delete(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            Logger.getLogger(UserResource.class.getName()).log(Level.SEVERE, "[delete]", e);
            return ResponseEntity.status(HttpStatus.resolve(500)).body(e.getMessage());
        }
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity post(@RequestBody UserRequest request) {
        try {
            this.service.save(request);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            Logger.getLogger(UserResource.class.getName()).log(Level.SEVERE, "[post]", e);
            return ResponseEntity.status(HttpStatus.resolve(500)).body(e.getMessage());
        }
    }

    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity put(@RequestBody UserRequest request) {
        try {
            this.service.update(request);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            Logger.getLogger(UserResource.class.getName()).log(Level.SEVERE, "[put]", e);
            return ResponseEntity.status(HttpStatus.resolve(500)).body(e.getMessage());
        }
    }

}
