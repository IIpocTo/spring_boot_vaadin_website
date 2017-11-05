package favourites.ui;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.Label;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;
import favourites.security.SecurityReference;
import org.springframework.beans.factory.annotation.Autowired;

@SpringUI(path = LoginUI.PATH)
@Theme(ValoTheme.THEME_NAME)
@Title(LoginUI.TITLE_UI)
public class LoginUI extends UI implements UIExceptionConfigurer {

    private static final long serialVersionUID = 8655698055156424298L;

    static final String PATH = "/ui/login";
    static final String TITLE_UI = "Login";

    private Label label;
    private TextField username;
    private PasswordField password;
    private Button loginButton;
    private Button registerButton;
    private VerticalLayout content;
    private CheckBox rememberMe;

    private final SecurityReference security;

    @Autowired
    public LoginUI(SecurityReference security) {
        super();
        this.security = security;
        this.label = new Label("Welcome to the app!");
        this.content = new VerticalLayout();
        this.username = new TextField("Username");
        this.password = new PasswordField("Password");
        this.loginButton = new Button("Sign in");
        this.registerButton = new Button("Register");
        this.rememberMe = new CheckBox("Remember me");
    }

    @Override
    protected void init(VaadinRequest request) {

        this.username.setPlaceholder("Enter your username");
        this.password.setPlaceholder("Enter your password");

        this.loginButton.addClickListener(click -> security.login(this.username.getValue(), this.password.getValue()));
        this.registerButton.addClickListener(click -> getUI().getPage().setLocation(RegisterUI.PATH));

        content.addComponents(registerButton, label, username, password, rememberMe, loginButton);

        content.setComponentAlignment(this.registerButton, Alignment.TOP_RIGHT);
        content.setComponentAlignment(this.label, Alignment.TOP_CENTER);
        content.setComponentAlignment(this.username, Alignment.MIDDLE_CENTER);
        content.setComponentAlignment(this.password, Alignment.MIDDLE_CENTER);
        content.setComponentAlignment(this.rememberMe, Alignment.MIDDLE_CENTER);
        content.setComponentAlignment(this.loginButton, Alignment.MIDDLE_CENTER);

        configureUIException(content).setContent(content);

    }

}
