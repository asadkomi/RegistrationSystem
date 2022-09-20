package com.socialmedia.services;


import com.google.api.services.gmail.Gmail;
import com.socialmedia.exceptions.EmailFailedToSendException;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.api.services.gmail.model.Message;

import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.ByteArrayOutputStream;
import java.util.Properties;

@Service
public class MailService {
    private final Gmail gmail;

    @Autowired
    public MailService(Gmail gmail) {
        this.gmail = gmail;
    }

    public void sendEmail(String to, String subject, String content) throws Exception {
        Properties properties = new Properties();
        Session session = Session.getInstance(properties, null);
        MimeMessage mimeMessage = new MimeMessage(session);

        try {
            mimeMessage.setFrom(new InternetAddress("asadkomi@outlook.com"));
            mimeMessage.addRecipient(javax.mail.Message.RecipientType.TO, new InternetAddress(to));
            mimeMessage.setSubject(subject);
            mimeMessage.setText(content);

            ByteArrayOutputStream buffer = new ByteArrayOutputStream();
            mimeMessage.writeTo(buffer);
            byte[] rawMessageBytes = buffer.toByteArray();
            String encodedEmail = Base64.encodeBase64URLSafeString(rawMessageBytes);

            Message message = new Message();
            message.setRaw(encodedEmail);
            message = gmail.users().messages().send("me", message).execute();

        } catch (Exception e) {
            throw new EmailFailedToSendException();
        }
    }
}
