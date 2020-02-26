/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.clicktop.app.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 *
 * @author thiag
 */
@Data
@AllArgsConstructor
public class PostStatusDTO {

    private Long scheduled;
    private Long pending;
    private Long approved;
    private Long disapproved;
    private Long published;

}
