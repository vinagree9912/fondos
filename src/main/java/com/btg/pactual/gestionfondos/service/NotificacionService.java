package com.btg.pactual.gestionfondos.service;

import com.btg.pactual.gestionfondos.model.Fondo;
import jakarta.mail.MessagingException;

public interface NotificacionService {
    void sendEmail(String to, String subject, String text) throws MessagingException;
    void sendSms(String phoneNumber, String message);
}
