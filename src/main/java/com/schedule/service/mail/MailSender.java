package com.schedule.service.mail;

import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Component;

@Component
public interface MailSender {
    void send(SimpleMailMessage simpleMailMessage) throws MailException;
    void send(SimpleMailMessage[] simpleMailMessages) throws MailException;
}
