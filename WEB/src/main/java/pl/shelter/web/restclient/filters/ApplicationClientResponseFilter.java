package pl.shelter.web.restclient.filters;

import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.client.ClientRequestContext;
import jakarta.ws.rs.client.ClientResponseContext;
import jakarta.ws.rs.ext.Provider;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.logging.Logger;

@Provider
public class ApplicationClientResponseFilter implements jakarta.ws.rs.client.ClientResponseFilter {

    private static final Logger LOG = Logger.getLogger(ApplicationClientResponseFilter.class.getName());
    @Inject
    private FacesContext facesContext;

    @Override
    public void filter(ClientRequestContext requestContext, ClientResponseContext responseContext) throws IOException {
        final int STATUS = responseContext.getStatus();
        String responseDescription = "REST CLIENT: " + responseContext.toString();

        if (STATUS >= 300) {
            String responseMessage = responseContext.hasEntity() ? new String(responseContext.getEntityStream().readAllBytes(), StandardCharsets.UTF_8) : "<<NO RESPONSE BODY>>";
            LOG.severe(responseDescription + ", " + responseMessage);

            throw new WebApplicationException(responseMessage, STATUS);
        } else
            LOG.fine(responseDescription);
//            JsonObject object = Json.createReader(new StringReader(responseMessage)).readObject();
//            String message = object.getString("message");
//            PhaseId currentPhaseId = facesContext.getCurrentPhaseId();//if render response - messages are already rendered
//            facesContext.getExternalContext().getFlash().setKeepMessages(true);
//            facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, message, message));
//        }
    }

}
