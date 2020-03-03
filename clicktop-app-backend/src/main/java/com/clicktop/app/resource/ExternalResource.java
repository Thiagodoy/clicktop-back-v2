/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.clicktop.app.resource;

import com.clicktop.app.model.Category;
import com.clicktop.app.model.City;
import com.clicktop.app.model.Company;
import com.clicktop.app.model.Post;
import com.clicktop.app.model.Prospect;
import com.clicktop.app.model.Tourism;
import com.clicktop.app.service.CategoryService;
import com.clicktop.app.service.CityService;
import com.clicktop.app.service.CompanyService;
import com.clicktop.app.service.PostService;
import com.clicktop.app.service.ProspectService;
import com.clicktop.app.service.TourismService;
import static com.clicktop.app.utils.Url.URL_EXTERNAL;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
@RequestMapping(value = URL_EXTERNAL)
public class ExternalResource {

    @Autowired
    private PostService postService;

    @Autowired
    private CompanyService companyService;

    @Autowired
    private ProspectService prospectService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private TourismService tourismService;
    
    @Autowired
    private CityService cityService;

    @RequestMapping(value = "/post", method = RequestMethod.GET)
    @ApiOperation(value = "List", notes = "List all post with a company can have")
    @ApiResponses(value = {
        @ApiResponse(response = Post.class, code = 200, message = "Ok", responseContainer = "List")
        ,
        @ApiResponse(code = 500, message = "Error on Server")
    })
    public ResponseEntity getPost(@RequestParam(name = "company", required = false) Long company,
            @RequestParam(name = "search", required = false) String search,
            @RequestParam(name = "category", required = false) Long category,
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "10") int size) {

        try {
            Page<Post> response = this.postService.list(company, "PUBLISHED", search,category, PageRequest.of(page, size, Sort.by("publishedTime").descending()));
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Logger.getLogger(ExternalResource.class.getName()).log(Level.SEVERE, "[getPost]", e);
            return ResponseEntity.status(HttpStatus.resolve(500)).body(e.getMessage());
        }
    }

    @RequestMapping(value = "/company", method = RequestMethod.GET)
    @ApiOperation(value = "List", notes = "List all company")
    @ApiResponses(value = {
        @ApiResponse(response = Post.class, code = 200, message = "Ok", responseContainer = "List")
        ,
        @ApiResponse(code = 500, message = "Error on Server")
    })
    public ResponseEntity getCompany(@RequestParam(required = false, name = "id") Long id,
            @RequestParam(required = false, name = "name") String name,
            @RequestParam(required = false, name = "email") String email,
            @RequestParam(required = false, name = "planId") Long planId,
            @RequestParam(required = false, name = "spotlight") Long spotlight,
            @RequestParam(required = false, name = "category") Long category,
            @RequestParam(required = false, name = "type") String type,
            @RequestParam(required = false, name = "page", defaultValue = "0") int page,
            @RequestParam(required = false, name = "size", defaultValue = "10") int size) {

        try {
            if (id != null) {
                Company response = this.companyService.findById(id);
                return ResponseEntity.ok(response);
            } else {
                Page<Company> response = this.companyService.list(name, email, spotlight, planId, type, category, PageRequest.of(page, size));
                return ResponseEntity.ok(response);
            }
        } catch (Exception e) {
            Logger.getLogger(ExternalResource.class.getName()).log(Level.SEVERE, "[getCompany]", e);
            return ResponseEntity.status(HttpStatus.resolve(500)).body(e.getMessage());
        }
    }

    @RequestMapping(value = "/last-companys", method = RequestMethod.GET)
    @ApiOperation(response = Company.class, responseContainer = "List", value = "")
    public ResponseEntity getLastCompanys(
            @RequestParam(required = false, name = "page", defaultValue = "0") int page,
            @RequestParam(required = false, name = "size", defaultValue = "10") int size) {
        try {
            Page response = this.companyService.listLastCompanys(PageRequest.of(page, size, Sort.by("createAt").descending()));
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
            Page response = this.companyService.listSpotLightCompanys(PageRequest.of(page, size, Sort.by("createAt").descending()));
            return ResponseEntity.ok(response);

        } catch (Exception e) {
            Logger.getLogger(CompanyResource.class.getName()).log(Level.SEVERE, "[get]", e);
            return ResponseEntity.status(HttpStatus.resolve(500)).body(e.getMessage());

        }
    }

    @RequestMapping(value = "/prospect", method = RequestMethod.POST)
    @ApiOperation(value = "Save", notes = "Create a new prospect")
    @ApiResponses(value = {
        @ApiResponse(response = Void.class, code = 200, message = "Ok")
        ,
        @ApiResponse(code = 500, message = "Error on Server")
    })
    public ResponseEntity createProspect(@RequestBody Prospect request) {
        try {
            this.prospectService.save(request);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            Logger.getLogger(ProspectResource.class.getName()).log(Level.SEVERE, "[post]", e);
            return ResponseEntity.status(HttpStatus.resolve(500)).body(e.getMessage());
        }
    }

    @RequestMapping(value = "/category", method = RequestMethod.GET)
    @ApiOperation(value = "List", notes = "List all category with a company can have")
    @ApiResponses(value = {
        @ApiResponse(response = Category.class, code = 200, message = "Ok", responseContainer = "List")
        ,
        @ApiResponse(code = 500, message = "Error on Server")
    })
    public ResponseEntity getCategory() {

        try {
            List<Category> response = this.categoryService.findAll();
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Logger.getLogger(CategoryResource.class.getName()).log(Level.SEVERE, "[getCategory]", e);
            return ResponseEntity.status(HttpStatus.resolve(500)).body(e.getMessage());
        }
    }

    @RequestMapping(value = "/tourism", method = RequestMethod.GET)
    @ApiOperation(value = "List", notes = "List all tourism")
    @ApiResponses(value = {
        @ApiResponse(response = Tourism.class, code = 200, message = "Ok", responseContainer = "List")
        ,
        @ApiResponse(code = 500, message = "Error on Server")
    })
    public ResponseEntity getTourism() {

        try {

            List<Tourism> response = this.tourismService.findAll();
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Logger.getLogger(TourismResource.class.getName()).log(Level.SEVERE, "[getTourism]", e);
            return ResponseEntity.status(HttpStatus.resolve(500)).body(e.getMessage());
        }
    }
    
    
    @RequestMapping(value = "/city", method = RequestMethod.GET)
    @ApiOperation(value = "List", notes = "List all tourism")
    @ApiResponses(value = {
        @ApiResponse(response = Tourism.class, code = 200, message = "Ok", responseContainer = "List")
        ,
        @ApiResponse(code = 500, message = "Error on Server")
    })
    public ResponseEntity getCity() {

        try {

            List<City> response = Arrays.asList(this.cityService.findById(1905L));
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Logger.getLogger(TourismResource.class.getName()).log(Level.SEVERE, "[getCity]", e);
            return ResponseEntity.status(HttpStatus.resolve(500)).body(e.getMessage());
        }
    }

}
