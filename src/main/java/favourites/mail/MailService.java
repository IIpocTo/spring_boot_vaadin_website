package favourites.mail;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;

public interface MailService {
    void send(@NotNull @NotEmpty @Email String email, @NotNull @NotEmpty String theme, String text);
}
