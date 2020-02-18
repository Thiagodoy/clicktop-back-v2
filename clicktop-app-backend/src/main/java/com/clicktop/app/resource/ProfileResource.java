/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.clicktop.app.resource;

import com.clicktop.app.model.Profile;
import com.clicktop.app.service.ProfileService;
import com.clicktop.app.utils.Url;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author thiag
 */
@RestController
@RequestMapping( value = Url.URL_PROFILE)
public class ProfileResource {

    @Autowired
    private ProfileService service;

    @RequestMapping(method = RequestMethod.GET)
    @ApiOperation(value = "List", notes = "List all Profile")
    @ApiResponses(value = {
        @ApiResponse(response = Profile.class, code = 200, message = "Ok", responseContainer = "List")
        ,
        @ApiResponse(code = 500, message = "Error on Server")
    })
    public ResponseEntity get() {

        try {
            List<Profile> response = this.service.listAll();
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Logger.getLogger(ProfileResource.class.getName()).log(Level.SEVERE, "[get]", e);
            return ResponseEntity.status(HttpStatus.resolve(500)).body(e.getMessage());
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ApiOperation(value = "Delete", notes = "Delete a Profile for id")
    @ApiResponses(value = {
        @ApiResponse(response = Void.class, code = 200, message = "Ok")
        ,
        @ApiResponse(code = 500, message = "Error on Server")
    })
    public ResponseEntity delete(@PathVariable(name = "id") Long id) {
        try {
            this.service.delete(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            Logger.getLogger(ProfileResource.class.getName()).log(Level.SEVERE, "[delete]", e);
            return ResponseEntity.status(HttpStatus.resolve(500)).body(e.getMessage());
        }
    }

    @RequestMapping(method = RequestMethod.POST)
    @ApiOperation(value = "Save", notes = "Create a new Profile")
    @ApiResponses(value = {
        @ApiResponse(response = Void.class, code = 200, message = "Ok")
        ,
        @ApiResponse(code = 500, message = "Error on Server")
    })
    public ResponseEntity post(@RequestBody Profile request) {
        try {
            this.service.save(request);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            Logger.getLogger(ProfileResource.class.getName()).log(Level.SEVERE, "[post]", e);
            return ResponseEntity.status(HttpStatus.resolve(500)).body(e.getMessage());
        }
    }

    @RequestMapping(method = RequestMethod.PUT)
    @ApiOperation(value = "Update", notes = "Update status of a c")
    @ApiResponses(value = {
        @ApiResponse(response = Void.class, code = 200, message = "Ok")
        ,
        @ApiResponse(code = 500, message = "Error on Server")
    })
    public ResponseEntity put(@RequestBody Profile request) {
        try {
            this.service.save(request);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            Logger.getLogger(ProfileResource.class.getName()).log(Level.SEVERE, "[put]", e);
            return ResponseEntity.status(HttpStatus.resolve(500)).body(e.getMessage());
        }
    }
}
