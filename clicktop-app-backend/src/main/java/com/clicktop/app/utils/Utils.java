/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.clicktop.app.utils;


import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

/**
 *
 * @author thiag
 */
@Component
public class Utils {

    private static BCryptPasswordEncoder bcpe;
    private static SimpleDateFormat sdfHour;
    
    
    static {
        bcpe = new BCryptPasswordEncoder();
        sdfHour = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    }

    
    
    
    public static String encodePassword(String in) {
        return bcpe.encode(in);
    }
    
    public synchronized static String dateTimeNowFormated(){
        return LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME);
    }
    
    public synchronized static String dateToString(Date date){
        return sdfHour.format(date);
    }

}
