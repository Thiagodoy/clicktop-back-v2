package com.clicktop.app;

import com.clicktop.app.model.City;
import com.clicktop.app.model.Company;
import com.clicktop.app.model.State;
import com.clicktop.app.service.GoogleAddressApiService;
import javax.mail.MessagingException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class AppApplicationTests {
    
    
        @Autowired
        private GoogleAddressApiService service;

	
	void contextLoads() {
	}
        
        @Test
        void googleAddress() throws MessagingException{
            
            Company company = new Company();
            
            State state = new State(); 
            state.setInitials("SP");
            state.setName("São Paulo");
            City city = new City();
            city.setName("Araraquara");
            city.setState(state);
            
            company.setAddress("Avenida Uschoa Jd América");
            company.setAddressNumber(393L);
            company.setCity(city);
            
            
            this.service.getLocation(company);
            
        }

}
