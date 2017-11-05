package favourites.ui;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;
import favourites.rest.resources.UserResource;
import favourites.security.SecurityReference;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;

@SpringUI(path = RegisterUI.PATH)
@Theme(ValoTheme.THEME_NAME)
@Title(RegisterUI.TITLE_UI)
public class RegisterUI extends UI implements UIExceptionConfigurer {

    private static final long serialVersionUID = 865569845436424298L;

    static final String PATH = "/ui/register";
    static final String TITLE_UI = "Register";

    private TextField username;
    private TextField email;
    private PasswordField password;
    private Button registerButton;
    private Button backToLoginButton;
    private VerticalLayout content;

    private final SecurityReference security;

    @Autowired
    public RegisterUI(SecurityReference security) {
        super();
        this.security = security;
        this.content = new VerticalLayout();
        this.username = new TextField("Username");
        this.email = new TextField("Email");
        this.password = new PasswordField("Password");
        this.backToLoginButton = new Button("Sign in");
        this.registerButton = new Button("Register");
    }

    @Override
    protected void init(VaadinRequest request) {

        this.username.setRequiredIndicatorVisible(true);
        this.username.setPlaceholder("Enter your username");

        this.email.setRequiredIndicatorVisible(true);
        this.email.setPlaceholder("Enter your e-mail");

        this.password.setRequiredIndicatorVisible(true);
        this.password.setPlaceholder("Enter your password");

        this.backToLoginButton.addClickListener(click -> getUI().getPage().setLocation(LoginUI.PATH));
        this.registerButton.addClickListener(click -> security.register(validateAndCreate()));

        content.addComponents(backToLoginButton, username, email, password, registerButton);

        content.setComponentAlignment(this.backToLoginButton, Alignment.TOP_RIGHT);
        content.setComponentAlignment(this.registerButton, Alignment.MIDDLE_LEFT);
        content.setComponentAlignment(this.email, Alignment.MIDDLE_LEFT);
        content.setComponentAlignment(this.username, Alignment.MIDDLE_LEFT);
        content.setComponentAlignment(this.password, Alignment.MIDDLE_LEFT);

        configureUIException(content).setContent(content);

    }

    @NotNull
    private UserResource validateAndCreate() {
        final String username = this.username.getValue();
        final String email = this.email.getValue();
        final String password = this.password.getValue();
        final LocalDate regDate = LocalDate.now();
        final LocalDateTime lastLoggedDT = LocalDateTime.now();
        if (username.isEmpty() || email.isEmpty() || password.isEmpty())
            throw new IllegalArgumentException("Username, Email and Password fields can't be empty");
        return new UserResource(username, null, email, lastLoggedDT, regDate, password);
    }

}
