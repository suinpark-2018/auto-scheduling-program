package com.schedule.service.mail;

import com.schedule.dto.StaffDto;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import java.io.UnsupportedEncodingException;

@Service
public class MailService {
    // 인증메일에 포함되는 내용들 상수로 관리
    private final String subject = "[널스케쥴링] 본인인증 메일입니다.";
    private final String title = "<h1>널스케쥴링 인증 메일</h1>";
    private final String content = "<br>널스케쥴링을 이용해주셔서 감사합니다." + "<br>인증번호: ";
    private final String senderName = "Nurscheduling";
    private final String senderEmail = "parksuuuun@gmail.com";

    private final JavaMailSender mailSender;

    // MailSender 테스트용 메서드
    public MailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void sendTestEmail(StaffDto staffDto) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(staffDto.getEmail());
        mailMessage.setFrom("nurscheduling@co.kr");
        mailMessage.setSubject("[테스트] 인증 메일 제목");
        mailMessage.setText("[테스트] 인증 메일 내용: " + makeRandomMailKey());

        this.mailSender.send(mailMessage);
    }

    // 인증번호 생성 메서드 makeRandomMailKey(): 6자리 랜덤키 생성
    public String makeRandomMailKey() {
        return new TempKey().getKey(6, false);
    }

    // 메일 전송 메서드 send()
    // parameter: 사용자가 입력한 이메일 및 인증번호
    public boolean send(String recipient, String mailKey) {
        boolean successToSend = true;

        try {
            MailHandler sendMail = new MailHandler(mailSender);
            sendMail.setSubject(subject);
            sendMail.setText(title + content + mailKey);
            sendMail.setFrom(senderEmail, senderName);
            sendMail.setTo(recipient);
            sendMail.send();
        } catch (AddressException e) {
            successToSend = false;
            throw new RuntimeException(e);
        } catch (MessagingException e) {
            successToSend = false;
            throw new RuntimeException(e);
        } catch (UnsupportedEncodingException e) {
            successToSend = false;
            throw new RuntimeException(e);
        }

        return successToSend;
    }
}
