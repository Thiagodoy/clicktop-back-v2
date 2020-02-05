/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.clicktop.app.request;

import com.clicktop.app.model.Profile;
import com.clicktop.app.model.User;
import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
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

}
