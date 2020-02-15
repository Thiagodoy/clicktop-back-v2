/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.clicktop.app.resource;

import com.clicktop.app.model.Prospect;
import com.clicktop.app.service.ProspectService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
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
@RequestMapping(value = "/prospect")
public class ProspectResource {

    @Autowired
    private ProspectService service;

    @RequestMapping(method = RequestMethod.GET)
    @ApiOperation(value = "List", notes = "List all prospect")
    @ApiResponses(value = {
        @ApiResponse(response = Page.class, code = 200, message = "Ok"),
        @ApiResponse(code = 500, message = "Error on Server")
    })
    public ResponseEntity get(
            @RequestParam(name = "page", defaultValue = "0",required = true)int page,
            @RequestParam(name = "size", defaultValue = "10",required = true)int size) {

        try {
            
            PageRequest pageRequest = PageRequest.of(page, size, Sort.by("createdAt").descending()); 
            Page<Prospect> response = this.service.findAll(pageRequest);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Logger.getLogger(ProspectResource.class.getName()).log(Level.SEVERE, "[get]", e);
            return ResponseEntity.status(HttpStatus.resolve(500)).body(e.getMessage());
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ApiOperation(value = "Delete", notes = "Delete a prospect for id")
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
            Logger.getLogger(ProspectResource.class.getName()).log(Level.SEVERE, "[delete]", e);
            return ResponseEntity.status(HttpStatus.resolve(500)).body(e.getMessage());
        }
    }

    @RequestMapping(method = RequestMethod.POST)
    @ApiOperation(value = "Save", notes = "Create a new prospect")
    @ApiResponses(value = {
        @ApiResponse(response = Void.class, code = 200, message = "Ok")
        ,
        @ApiResponse(code = 500, message = "Error on Server")
    })
    public ResponseEntity post(@RequestBody Prospect request) {
        try {
            this.service.save(request);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            Logger.getLogger(ProspectResource.class.getName()).log(Level.SEVERE, "[post]", e);
            return ResponseEntity.status(HttpStatus.resolve(500)).body(e.getMessage());
        }
    }

    @RequestMapping(method = RequestMethod.PUT)
    @ApiOperation(value = "Update", notes = "Update status of a prospect")
    @ApiResponses(value = {
        @ApiResponse(response = Void.class, code = 200, message = "Ok")
        ,
        @ApiResponse(code = 500, message = "Error on Server")
    })
    public ResponseEntity put(@RequestBody Prospect request) {
        try {
            this.service.save(request);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            Logger.getLogger(ProspectResource.class.getName()).log(Level.SEVERE, "[put]", e);
            return ResponseEntity.status(HttpStatus.resolve(500)).body(e.getMessage());
        }
    }

}
