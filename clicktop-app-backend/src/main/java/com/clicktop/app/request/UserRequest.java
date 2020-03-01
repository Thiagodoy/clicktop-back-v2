/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.clicktop.app.request;

import lombok.Data;

/**
 *
 * @author thiag
 */
@Data
public class UserRequest {

    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String photo;
    private Long profile;
    private String tellPhone;
    private String cellPhone;
    private Long receiveEmail;

}
