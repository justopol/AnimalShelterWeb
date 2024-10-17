package pl.shelter.web.ctrl.errors;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.Conversation;
import jakarta.enterprise.context.ConversationScoped;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import pl.shelter.dto.accounts.adopters.AdopterDto;
import pl.shelter.dto.accounts.adoptions.AdoptionDto;
import pl.shelter.web.restclient.AdopterRestClient;
import pl.shelter.web.restclient.AdoptionRestClient;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

@ConversationScoped
@Named
public class ErrorController implements Serializable {
    @Inject
    private Conversation conversation;
    private String error;

    public void fetchError(String error) {
        if (!conversation.isTransient()) conversation.end();
        conversation.begin();
        this.error=error;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
