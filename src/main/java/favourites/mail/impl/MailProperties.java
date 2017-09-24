package favourites.mail.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Properties;

@Component
public class MailProperties extends Properties {

    private static final long serialVersionUID = -32153645373541L;

    private static final String MAIL_SMTP_HOST = "mail.smtp.host";
    private static final String MAIL_SMTP_SF_PORT = "mail.smtp.socketFactory.port";
    private static final String MAIL_SMTP_SF_CLASS = "mail.smtp.socketFactory.class";
    private static final String MAIL_SMTP_AUTH = "mail.smtp.auth";
    private static final String MAIL_SMTP_PORT = "mail.smtp.port";

    @Value("${spring.mail.host}")
    private String mailHost;

    @Value("${spring.mail.protocol}")
    private String mailProtocol;

    @Value("${spring.mail.smtp.socketFactory.class}")
    private String sslClass;

    @Value("${spring.mail.port}")
    private int mailPort;

    @Value("${spring.mail.smtp.socketFactory.port}")
    private int sslPort;

    @Value("${spring.mail.smtp.auth}")
    private boolean mailAuth;

    @PostConstruct
    public void init() {
        this.put(MAIL_SMTP_HOST, mailProtocol.concat(".").concat(mailHost));
        this.put(MAIL_SMTP_SF_PORT, sslPort);
        this.put(MAIL_SMTP_SF_CLASS, sslClass);
        this.put(MAIL_SMTP_AUTH, mailAuth);
        this.put(MAIL_SMTP_PORT, mailPort);
    }

}
