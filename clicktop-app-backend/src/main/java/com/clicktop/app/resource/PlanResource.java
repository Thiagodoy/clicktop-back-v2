/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.clicktop.app.resource;

import com.clicktop.app.model.Plan;
import com.clicktop.app.service.PlanService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
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
import static com.clicktop.app.utils.Url.*;
import io.swagger.annotations.ApiResponses;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author thiag
 */

@RequestMapping(value = URL_PLAN)
@ApiOperation(value = "PlanResource")
@RestController
public class PlanResource {

    @Autowired
    private PlanService service;

    @RequestMapping(method = RequestMethod.GET)
    @ApiOperation(value = "List", notes = "List all plan with a company can have")
    @ApiResponses(value = {
        @ApiResponse(response = Plan.class, code = 200, message = "Ok", responseContainer = "List"),
        @ApiResponse(code = 500, message = "Error on Server")
    })
    public ResponseEntity get() {

        try {

            List<Plan> response = this.service.findAll();
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Logger.getLogger(PlanResource.class.getName()).log(Level.SEVERE, "[get]", e);
            return ResponseEntity.status(HttpStatus.resolve(500)).body(e.getMessage());
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ApiOperation(value = "Delete", notes = "Delete a plan for id")
    @ApiResponses(value = {
        @ApiResponse(response = Void.class, code = 200, message = "Ok"),
        @ApiResponse(code = 500, message = "Error on Server")
    })
    public ResponseEntity delete(@PathVariable(name = "id") Long id) {
        try {
            this.service.delete(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            Logger.getLogger(PlanResource.class.getName()).log(Level.SEVERE, "[delete]", e);
            return ResponseEntity.status(HttpStatus.resolve(500)).body(e.getMessage());
        }
    }

    @RequestMapping(method = RequestMethod.POST)
    @ApiOperation(value = "Save", notes = "Create a new plan for application")
    @ApiResponses(value = {
        @ApiResponse(response = Void.class, code = 200, message = "Ok"),
        @ApiResponse(code = 500, message = "Error on Server")
    })
    public ResponseEntity post(@RequestBody Plan request) {
        try {
            this.service.save(request);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            Logger.getLogger(PlanResource.class.getName()).log(Level.SEVERE, "[post]", e);
            return ResponseEntity.status(HttpStatus.resolve(500)).body(e.getMessage());
        }
    }

    @RequestMapping(method = RequestMethod.PUT)
    @ApiOperation(value = "Update", notes = "Update a plan")
    @ApiResponses(value = {
        @ApiResponse(response = Void.class, code = 200, message = "Ok"),
        @ApiResponse(code = 500, message = "Error on Server")
    })
    public ResponseEntity put(@RequestBody Plan request) {
        try {
            this.service.update(request);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            Logger.getLogger(PlanResource.class.getName()).log(Level.SEVERE, "[put]", e);
            return ResponseEntity.status(HttpStatus.resolve(500)).body(e.getMessage());
        }
    }

}
