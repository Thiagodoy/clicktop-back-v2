/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.clicktop.app.google.response;

import java.util.List;
import lombok.Data;

/**
 *
 * @author thiag
 */
@Data
public class GoogleAddressListResponse {
    
    
    private List<GoogleAddressResponse> results;
    
    
}


