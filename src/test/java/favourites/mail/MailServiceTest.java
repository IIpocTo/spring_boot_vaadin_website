package favourites.mail;

import favourites.TestConfiguration;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestConfiguration.class)
public class MailServiceTest {

    @Autowired
    private MailService mailService;

    @Test
    public void testSendMail() {
        mailService.send("allxf95@gmail.com", "Test subject", "Mail for test purposes!");
    }

}
