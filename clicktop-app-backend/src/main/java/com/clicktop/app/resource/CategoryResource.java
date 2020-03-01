/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.clicktop.app.resource;

import com.clicktop.app.model.Category;
import com.clicktop.app.service.CategoryService;
import static com.clicktop.app.utils.Url.URL_CATEGORY;
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
@RequestMapping(value = URL_CATEGORY)
public class CategoryResource {
 
    
     @Autowired
    private CategoryService service;

    @RequestMapping(method = RequestMethod.GET)
    @ApiOperation(value = "List", notes = "List all category with a company can have")
    @ApiResponses(value = {
        @ApiResponse(response = Category.class, code = 200, message = "Ok", responseContainer = "List"),
        @ApiResponse(code = 500, message = "Error on Server")
    })
    public ResponseEntity get(@RequestParam(name = "page", defaultValue = "0",required = true)int page,
            @RequestParam(name = "size", defaultValue = "10",required = true)int size) {

        try {

            Page<Category> response = this.service.findAll(PageRequest.of(page, size, Sort.by("name")));
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Logger.getLogger(CategoryResource.class.getName()).log(Level.SEVERE, "[get]", e);
            return ResponseEntity.status(HttpStatus.resolve(500)).body(e.getMessage());
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ApiOperation(value = "Delete", notes = "Delete a category for id")
    @ApiResponses(value = {
        @ApiResponse(response = Void.class, code = 200, message = "Ok"),
        @ApiResponse(code = 500, message = "Error on Server")
    })
    public ResponseEntity delete(@PathVariable(name = "id") Long id) {
        try {
            this.service.delete(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            Logger.getLogger(CategoryResource.class.getName()).log(Level.SEVERE, "[delete]", e);
            return ResponseEntity.status(HttpStatus.resolve(500)).body(e.getMessage());
        }
    }

    @RequestMapping(method = RequestMethod.POST)
    @ApiOperation(value = "Save", notes = "Create a new category for application")
    @ApiResponses(value = {
        @ApiResponse(response = Void.class, code = 200, message = "Ok"),
        @ApiResponse(code = 500, message = "Error on Server")
    })
    public ResponseEntity post(@RequestBody Category request) {
        try {
            this.service.save(request);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            Logger.getLogger(CategoryResource.class.getName()).log(Level.SEVERE, "[post]", e);
            return ResponseEntity.status(HttpStatus.resolve(500)).body(e.getMessage());
        }
    }

    @RequestMapping(method = RequestMethod.PUT)
    @ApiOperation(value = "Update", notes = "Update a category")
    @ApiResponses(value = {
        @ApiResponse(response = Void.class, code = 200, message = "Ok"),
        @ApiResponse(code = 500, message = "Error on Server")
    })
    public ResponseEntity put(@RequestBody Category request) {
        try {
            this.service.save(request);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            Logger.getLogger(CategoryResource.class.getName()).log(Level.SEVERE, "[put]", e);
            return ResponseEntity.status(HttpStatus.resolve(500)).body(e.getMessage());
        }
    }

    
    
    
    
}
