package pl.shelter.web.security;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.faces.annotation.FacesConfig;
import jakarta.security.enterprise.authentication.mechanism.http.CustomFormAuthenticationMechanismDefinition;
import jakarta.security.enterprise.authentication.mechanism.http.LoginToContinue;

@FacesConfig
@ApplicationScoped
@CustomFormAuthenticationMechanismDefinition(
        loginToContinue = @LoginToContinue(
                loginPage = "/login/customFormLogin.xhtml",
                errorPage = "/login/customFormLoginError.xhtml",
                useForwardToLogin = false
        ))
public class  SecurityAppConfig {

}
