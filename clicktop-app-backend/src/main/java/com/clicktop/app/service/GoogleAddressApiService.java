/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.clicktop.app.service;

import com.clicktop.app.google.response.GoogleAddressListResponse;
import com.clicktop.app.google.response.GoogleAddressResponse;
import com.clicktop.app.google.response.GoogleLocation;
import com.clicktop.app.model.Company;
import com.clicktop.app.properties.GoogleApiAddressProperties;
import com.clicktop.app.repository.CompanyRepository;
import static com.clicktop.app.utils.Constants.EMAIL_ERROR_GEOLOCATION;
import static com.clicktop.app.utils.Constants.EMAIL_MESSAGE_KEY;
import static com.clicktop.app.utils.Constants.EMAIL_SUBJECT_KEY;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author thiag
 */
@Service
public class GoogleAddressApiService {

    @Autowired
    private GoogleApiAddressProperties properties;

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private EmailService emailService;

    @Value("${admin}")
    private String adminEmail;

    private String generateUrl(Company company) {

        String address = MessageFormat.format("{0} {1} {2} {3} {4}", company.getAddress(), company.getAddressNeighborhood(),  company.getAddressNumber(), company.getCity().getName(), company.getCity().getState().getName());
        return MessageFormat.format("{0}&key={1}&address={2}", this.properties.getUrl(), this.properties.getKey(), address);

    }

    public void getLocation(Company company) throws MessagingException {

        RestTemplate restTemplate = new RestTemplate();

        try {
            GoogleAddressListResponse addressListResponse = restTemplate.getForObject(this.generateUrl(company), GoogleAddressListResponse.class);

            if (Optional.ofNullable(addressListResponse).isPresent() && addressListResponse.getResults().size() > 0) {
                GoogleAddressResponse addressResponse = addressListResponse.getResults().get(0);
                GoogleLocation googleLocation = addressResponse.getGeometry().getLocaltion();

                company.setFormatedAddress(addressResponse.getFormattedAddress());
                company.setLongitude(String.valueOf(googleLocation.getLongitude()));
                company.setLatitude(String.valueOf(googleLocation.getLatitude()));
                companyRepository.save(company);
            } else {

                Map<String, String> parameters = new HashMap<>();
                parameters.put(EMAIL_SUBJECT_KEY, "Erro no endereço");
                parameters.put(EMAIL_MESSAGE_KEY, MessageFormat.format(EMAIL_ERROR_GEOLOCATION, company.getName() + " id => " + company.getId()));
                emailService.sendEmail(adminEmail, parameters);

            }

        } catch (Exception e) {
            Logger.getLogger(GoogleAddressApiService.class.getName()).log(Level.SEVERE, "[getLocation]", e);
            Map<String, String> parameterss = new HashMap<>();
            parameterss.put(EMAIL_SUBJECT_KEY, "Erro no endereço");
            parameterss.put(EMAIL_MESSAGE_KEY, MessageFormat.format(EMAIL_ERROR_GEOLOCATION, company.getName() + " id => " + company.getId()));
            emailService.sendEmail(adminEmail, parameterss);
        }

    }

}
