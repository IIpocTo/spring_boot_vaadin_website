package favourites.notification;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;

public interface NotificationSender {
    void successRegistrationNotificationSend(@NotNull @Email String email, @NotNull @NotEmpty String username,
                                             @NotNull @NotEmpty String password);
    void rememberPasswordSend(@NotNull @Email String email, @NotNull @NotEmpty String username);
    void dailyNotificationSend();
}
