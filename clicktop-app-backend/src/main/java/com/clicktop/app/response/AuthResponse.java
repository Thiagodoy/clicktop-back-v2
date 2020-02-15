/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.clicktop.app.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.security.core.userdetails.UserDetails;

/**
 *
 * @author thiag
 */
@Data
@AllArgsConstructor
public class AuthResponse {
    
    private String token;
    private UserDetails user;
    
}
