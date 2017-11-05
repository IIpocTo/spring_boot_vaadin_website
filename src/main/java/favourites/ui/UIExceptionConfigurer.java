package favourites.ui;

import com.vaadin.server.DefaultErrorHandler;
import com.vaadin.server.ErrorEvent;
import com.vaadin.shared.ui.ContentMode;
import com.vaadin.ui.Label;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

import javax.validation.constraints.NotNull;

public interface UIExceptionConfigurer {

    @NotNull
    default UI configureUIException(@NotNull VerticalLayout content) {
        UI.getCurrent().setErrorHandler(new DefaultErrorHandler() {
            private static final long serialVersionUID = 4324322326324432L;

            @Override
            public void error(ErrorEvent event) {
                final StringBuilder cause = new StringBuilder("<b>Error: </b>");
                for (Throwable t = event.getThrowable(); t != null; t = t.getCause()) {
                    if (t.getCause() == null) cause.append(t.getMessage()).append("<br/>");
                }
                content.addComponent(new Label(cause.toString(), ContentMode.HTML));
                doDefault(event);
            }
        });
        return UI.getCurrent();
    }

}
