package pl.shelter.web.security;

import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.io.Serializable;
import java.util.List;

@SessionScoped
@Named
public class RestSecurityContext implements Serializable {

    @Inject
    private FacesContext facesContext;

    private String userName;

    private List<String> groups;

    private String jwt;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public List<String> getGroups() {
        return groups;
    }

    public void setGroups(List<String> groups) {
        this.groups = groups;
    }

    public String getJwt() {
        return jwt;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }

    public boolean isAuthenticated() {
        return jwt != null;
    }

    public String logout() {
        jwt = null;
        facesContext.getExternalContext().invalidateSession();
        return "main";
    }
}
