package pl.shelter.web.utils;

import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

public class I18nUtils {
    private final static String messagesBundlePath = "i18n.messages";

    private static ResourceBundle getMessagesBundleForJsfViewLocale() {
        return ResourceBundle.getBundle(messagesBundlePath, FacesContext.getCurrentInstance().getViewRoot().getLocale());
    }

    public static String getMessage(String key) {
        return getMessagesBundleForJsfViewLocale().getString(key);
    }

    public static boolean isInternationalizationKeyExist(String key) {
        try {
            String message = I18nUtils.getMessagesBundleForJsfViewLocale().getString(key);
            if (message.length() >= 2)
                return true;
        } catch (MissingResourceException mre) {
            return false;
        }

        return false;
    }

    public static void emitGeneralErrorMessage() {
        emitInternationalizedMessage(null, "ERROR.GENERAL.UNKNOWN");
    }
    public static void emitInternationalizedMessage(String id, String key) {
        FacesMessage msg = new FacesMessage(I18nUtils.getMessagesBundleForJsfViewLocale().getString(key));
        msg.setSeverity(FacesMessage.SEVERITY_ERROR);
        FacesContext.getCurrentInstance().addMessage(id, msg);
    }

}
