package com.clicktop.app.service;

import com.clicktop.app.properties.EmailServiceProperties;
import static com.clicktop.app.utils.Constants.*;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

/**
 *
 * @author Thiago H. Godoy <thiagodoy@hotmail.com>
 */
@Service
public class EmailService {

    @Autowired
    private JavaMailSender sender;

    @Autowired
    private EmailServiceProperties properties;

    public void sendEmail(String email, Map<String, String> parameters) throws MessagingException {

        Thread sendEmail = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    MimeMessage message = sender.createMimeMessage();
                    MimeMessageHelper helper = new MimeMessageHelper(message, true);
                    helper.setFrom(properties.getUsername());
                    helper.setTo(email);
                    helper.setSubject(parameters.get(EMAIL_SUBJECT_KEY));
                    helper.setText(parameters.get(EMAIL_MESSAGE_KEY));
                    sender.send(message);
                } catch (Exception e) {
                    Logger.getLogger(EmailService.class.getName()).log(Level.SEVERE, "[sendEmail]",e);
                }
            }
        });
        
        sendEmail.start();

    }

//    public void sendEmailByUser(EmailLayoutEnum layout, String subject, UserActiviti userActiviti){
//    
//    
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm:ss");
//        Map<String, String> parameter = new HashMap<String, String>();
//        parameter.put(":name", Utils.replaceAccentToEntityHtml(userActiviti.getFirstName()));
//        parameter.put(":email", userActiviti.getEmail());        
//        parameter.put(":data", formatter.format(LocalDateTime.now()));
//
//        Runnable runnable = () -> {
//            try {
//                this.send(layout, subject , parameter, userActiviti.getEmail());
//            } catch (MessagingException ex) {
//                Logger.getLogger(UserActivitiService.class.getName()).log(Level.SEVERE, "EMAIL", ex);
//            } catch (IOException ex) {
//                Logger.getLogger(UserActivitiService.class.getName()).log(Level.SEVERE, "EMAIL", ex);
//            }
//        };
//
//        new Thread(runnable).start();
//    
//    
//    }
//    public void send(Notificacao notificacao) throws MessagingException, IOException{
//        
//        
//        MimeMessage message = sender.createMimeMessage();        
//        MimeMessageHelper helper = new MimeMessageHelper(message, true);
//        
////        if(parameters != null && parameters.size() > 0){            
////            for (String key : parameters.keySet()) {
////                content = content.replace(key, parameters.get(key));
////            }
////        }   
//        
//        
//        
//    }
//    public void send(EmailLayoutEnum layout,String subject,Map<String,String>parameters, String... emails) throws MessagingException, IOException {
//
//        MimeMessage message = sender.createMimeMessage();        
//        MimeMessageHelper helper = new MimeMessageHelper(message, true);        
//        String content = Utils.getContenFromLayout(layout);      
//        
//        helper.setTo(emails);
//        helper.setSubject(subject);
//        
//        
//        
//        if(parameters != null && parameters.size() > 0){            
//            for (String key : parameters.keySet()) {
//                content = content.replace(key, parameters.get(key));
//            }
//        }        
//
//        
//        helper.setText(content, true);        
//        
//        File logo = Utils.loadLogo(Constantes.IMAGE_CHANFRO);
//        FileSystemResource res = new FileSystemResource(logo);
//        helper.addInline("identifier1", res);
//        
//        File logo1 = Utils.loadLogo(Constantes.IMAGE_FUNDO);
//        FileSystemResource res1 = new FileSystemResource(logo1);
//        helper.addInline("identifier2", res1);
//        
//        File logo2 = Utils.loadLogo(Constantes.IMAGE_LOGO_001);
//        FileSystemResource res2 = new FileSystemResource(logo2);
//        helper.addInline("identifier3", res2);
//        
//        File logo3 = Utils.loadLogo(Constantes.IMAGE_LOGO_002);
//        FileSystemResource res3 = new FileSystemResource(logo3);
//        helper.addInline("identifier4", res3);
//        
//        File logo4 = Utils.loadLogo(Constantes.IMAGE_LOGO_LATAM);
//        FileSystemResource res4 = new FileSystemResource(logo4);
//        helper.addInline("identifier5", res4);
//        
//        File logo5 = Utils.loadLogo(Constantes.IMAGE_LOGO_ONE);
//        FileSystemResource res5 = new FileSystemResource(logo5);
//        helper.addInline("identifier6", res5);
//        
//    
//        sender.send(message);
//        logo.delete();
//        logo1.delete();
//        logo2.delete();
//        logo3.delete();
//        logo4.delete();
//        logo5.delete();
//    }
}
