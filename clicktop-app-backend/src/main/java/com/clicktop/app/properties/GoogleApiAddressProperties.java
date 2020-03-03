/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.clicktop.app.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 *
 * @author thiag
 */
@ConfigurationProperties(prefix = "google", ignoreUnknownFields = true)
@Data
public class GoogleApiAddressProperties {

    private String url;
    private String key;

}
