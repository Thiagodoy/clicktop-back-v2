/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.clicktop.app.resource;

import com.clicktop.app.model.Tourism;
import com.clicktop.app.service.TourismService;
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
import static com.clicktop.app.utils.Url.*;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author thiag
 */
@RestController
@RequestMapping(value = URL_TOUR)
public class TourismResource {

    @Autowired
    private TourismService service;

    @RequestMapping(method = RequestMethod.GET)
    @ApiOperation(value = "List", notes = "List all tourism")
    @ApiResponses(value = {
        @ApiResponse(response = Tourism.class, code = 200, message = "Ok", responseContainer = "List")
        ,
        @ApiResponse(code = 500, message = "Error on Server")
    })
    public ResponseEntity get(@RequestParam(name="category",required = false)Long category) {

        try {
            List<Tourism> response = this.service.findAll(category);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Logger.getLogger(TourismResource.class.getName()).log(Level.SEVERE, "[get]", e);
            return ResponseEntity.status(HttpStatus.resolve(500)).body(e.getMessage());
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ApiOperation(value = "Delete", notes = "Delete a tourism for id")
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
            Logger.getLogger(TourismResource.class.getName()).log(Level.SEVERE, "[delete]", e);
            return ResponseEntity.status(HttpStatus.resolve(500)).body(e.getMessage());
        }
    }

    @RequestMapping(method = RequestMethod.POST)
    @ApiOperation(value = "Save", notes = "Create a new tourism for application")
    @ApiResponses(value = {
        @ApiResponse(response = Void.class, code = 200, message = "Ok")
        ,
        @ApiResponse(code = 500, message = "Error on Server")
    })
    public ResponseEntity post(@RequestBody Tourism request) {
        try {
            this.service.save(request);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            Logger.getLogger(TourismResource.class.getName()).log(Level.SEVERE, "[post]", e);
            return ResponseEntity.status(HttpStatus.resolve(500)).body(e.getMessage());
        }
    }

    @RequestMapping(method = RequestMethod.PUT)
    @ApiOperation(value = "Update", notes = "Update a tourism")
    @ApiResponses(value = {
        @ApiResponse(response = Void.class, code = 200, message = "Ok")
        ,
        @ApiResponse(code = 500, message = "Error on Server")
    })
    public ResponseEntity put(@RequestBody Tourism request) {
        try {
            this.service.save(request);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            Logger.getLogger(TourismResource.class.getName()).log(Level.SEVERE, "[put]", e);
            return ResponseEntity.status(HttpStatus.resolve(500)).body(e.getMessage());
        }
    }
}
