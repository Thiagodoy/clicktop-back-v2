/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.clicktop.app.resource;

import com.clicktop.app.model.City;
import com.clicktop.app.model.Plan;
import com.clicktop.app.service.CityService;
import com.clicktop.app.service.PlanService;
import static com.clicktop.app.utils.Url.URL_CITY;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
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
@RequestMapping(value = URL_CITY)
public class CityResource {
    
    
     @Autowired
    private CityService service;

    @RequestMapping(method = RequestMethod.GET)
    @ApiOperation(value = "List", notes = "List all cities")
    @ApiResponses(value = {
        @ApiResponse(response = City.class, code = 200, message = "Ok", responseContainer = "List"),
        @ApiResponse(code = 500, message = "Error on Server")
    })
    public ResponseEntity get() {

        try {

            List<City> response = this.service.findAll();
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Logger.getLogger(CityResource.class.getName()).log(Level.SEVERE, "[get]", e);
            return ResponseEntity.status(HttpStatus.resolve(500)).body(e.getMessage());
        }
    }
    
}
