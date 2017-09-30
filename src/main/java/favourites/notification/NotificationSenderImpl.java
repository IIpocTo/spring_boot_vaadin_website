package favourites.notification;

import favourites.dao.DomainOperations;
import favourites.domain.EntityType;
import favourites.domain.User;
import favourites.mail.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class NotificationSenderImpl implements NotificationSender {

    private final MailService mailService;
    private final DomainOperations domainOperations;

    @Autowired
    public NotificationSenderImpl(MailService mailService, DomainOperations domainOperations) {
        this.mailService = mailService;
        this.domainOperations = domainOperations;
    }

    @Override
    public void successRegistrationNotificationSend(String email, String username, String password) {
        mailService.send(email, NotificationPatterns.SUCCESS_REG_SUBJECT,
                String.format(NotificationPatterns.SUCCESS_REG_TEXT, username, password));
    }

    @Override
    public void rememberPasswordSend(String email, String username) {
        User user = domainOperations.findById(EntityType.USER, username);
        if (user == null){
            throw new UsernameNotFoundException("User with this username don't register in the application!");
        }
        mailService.send(email, NotificationPatterns.FORGOTTEN_PASSWORD_SUBJECT,
                String.format(NotificationPatterns.FORGOTTEN_PASSWORD_TEXT, username, user.getPassword()));
    }

    @Override
    @Scheduled(cron = NotificationPatterns.DAILY_CRON_EXPRESSION)
    public void dailyNotificationSend() {
        //TODO
    }

    static class NotificationPatterns {
        static final String SUCCESS_REG_SUBJECT = "Success registration in the application";
        static final String SUCCESS_REG_TEXT = "You're successfully registered in the application! /n Your login is '%s', password is '%s'.";
        static final String FORGOTTEN_PASSWORD_SUBJECT = "Forgotten password";
        static final String FORGOTTEN_PASSWORD_TEXT = "Dear %s! \n Password for your account is '%s'. \n Best regards, administration.";
        static final String DAILY_CRON_EXPRESSION = "0 0 12 1/1 * ?";
        static final String DAILY_SUBJECT = "You for a long time don't visit our application.";
        static final String DAILY_TEXT = "We miss you, %s!";
    }

}
