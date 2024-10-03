package pl.shelter.web.errors;

import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.event.PhaseId;
import jakarta.inject.Inject;
import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.stream.JsonParsingException;
import jakarta.ws.rs.client.ClientRequestContext;
import jakarta.ws.rs.client.ClientResponseContext;
import jakarta.ws.rs.client.ClientResponseFilter;
import jakarta.ws.rs.ext.Provider;

import java.io.IOException;
import java.io.StringReader;
import java.nio.charset.StandardCharsets;

@Provider
public class RestClientExceptionHandler implements ClientResponseFilter {

    @Inject
    private FacesContext facesContext;

    @Override
    public void filter(ClientRequestContext requestContext, ClientResponseContext responseContext) throws IOException {
        int status = responseContext.getStatus();
        if (status >= 400 && responseContext.hasEntity()) {
            String responseMessage = new String(responseContext.getEntityStream().readAllBytes(), StandardCharsets.UTF_8);
            try {
                JsonObject object = Json.createReader(new StringReader(responseMessage)).readObject();
                String message = object.getString("message");
                PhaseId currentPhaseId = facesContext.getCurrentPhaseId();
                facesContext.getExternalContext().getFlash().setKeepMessages(true);
                facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, message, message));
            } catch (JsonParsingException jpe) {
                facesContext.addMessage(null, new FacesMessage(responseMessage));
            }
        }
    }

}
