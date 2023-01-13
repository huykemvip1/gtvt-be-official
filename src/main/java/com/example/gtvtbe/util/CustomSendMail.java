package com.example.gtvtbe.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.InputStreamSource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMailMessage;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

@Component
@Slf4j
public class CustomSendMail {
    private final JavaMailSender mailSender;
    private final TemplateEngine templateEngine;

    public CustomSendMail(JavaMailSender mailSender, TemplateEngine templateEngine) {
        this.mailSender = mailSender;
        this.templateEngine = templateEngine;
    }

    public void sendMail(String recipient , String subject, String link) throws MessagingException, IOException {
        try {
            MimeMessage mimeMessage = mailSender.createMimeMessage();

            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
//            helper.setFrom(Constant.SENDER);
            helper.setFrom("huyjavacode@gmail.com");
            helper.setTo(recipient);
            helper.setSubject(subject);
            // set Image
            String logo = "logo";
            String contentType = "image/png";
            // Set variable for
            Context context = new Context();
            context.setVariable("link",link);
            context.setVariable("linkImage",logo);
            mimeMessage.setContent(templateEngine.process("template-mail.html",context),"text/html; charset=UTF-8");
            // set Image inpust stream
            Path source = Paths.get(System.getProperty("user.dir"))
                    .resolve("src/main/resources/static")
                    .resolve("logo-dai-hoc-giao-thong-van-tai-inkythuatso-01.png");
            final InputStreamSource imageSource = new ByteArrayResource(new FileInputStream(source.toString()).readAllBytes());
            helper.addInline(logo,imageSource,contentType);
            mailSender.send(mimeMessage);
        }catch (Exception e){
            log.error(e.getMessage());
            throw e;
        }
    }

    public void resetPassword(String recipient , String subject, String passwordReset) throws MessagingException, IOException {
        try {
            MimeMessage mimeMessage = mailSender.createMimeMessage();

            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
//            helper.setFrom(Constant.SENDER);
            helper.setFrom("huyjavacode@gmail.com");
            helper.setTo(recipient);
            helper.setSubject(subject);
            // set Image
            String logo = "logo";
            String contentType = "image/png";
            // Set variable for
            Context context = new Context();
            context.setVariable("passwordReset",passwordReset);
            context.setVariable("linkImage",logo);
            mimeMessage.setContent(templateEngine.process("reset-password.html",context),"text/html; charset=UTF-8");
            // set Image inpust stream
            Path source = Paths.get(System.getProperty("user.dir"))
                    .resolve("src/main/resources/static")
                    .resolve("logo-dai-hoc-giao-thong-van-tai-inkythuatso-01.png");
            final InputStreamSource imageSource = new ByteArrayResource(new FileInputStream(source.toString()).readAllBytes());
            helper.addInline(logo,imageSource,contentType);
            mailSender.send(mimeMessage);
        }catch (Exception e){
            log.error(e.getMessage());
            throw e;
        }
    }
}
