/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.clicktop.app.google.response;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.Data;

/**
 *
 * @author thiag
 */
@Data
public class GoogleGeometry {   
    
    @JsonAlias(value = "location")
    private GoogleLocation localtion;
    
}
