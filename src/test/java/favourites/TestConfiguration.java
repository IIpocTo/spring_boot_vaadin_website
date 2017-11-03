package favourites;

import favourites.mail.MailService;
import favourites.mail.impl.MailProperties;
import favourites.mail.impl.MailServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

@Configuration
@PropertySource(value = {"classpath:application.properties"})
public class TestConfiguration {

    @Bean
    public MailProperties mailProperties() {
        return new MailProperties();
    }

    @Bean
    public MailService mailService() {
        return new MailServiceImpl(mailProperties());
    }

}

