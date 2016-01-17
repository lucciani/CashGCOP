package br.com.cgcop.utilitarios;

import java.util.ResourceBundle;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;

/**
 *
 * @author gilmario
 */
public abstract class ResourceUtil {

    public static final String MENSAGENS = "br.com.cgcop.utilitarios.arquivos.mensagens";
    public static final String LABEL = "br.com.cgcop.utilitarios.arquivos.label";
    public static final String MODULO = "br.com.cgcop.arquivos.modulos";

    public static String lerBundle(String messageId, String resource) {
        ResourceBundle bundle = null;
        String msg = "";
        if (FacesContext.getCurrentInstance() != null) {
            UIViewRoot viewRoot = FacesContext.getCurrentInstance().getViewRoot();
            bundle = ResourceBundle.getBundle(resource, viewRoot.getLocale());
        } else {

            bundle = ResourceBundle.getBundle(resource);
        }
        try {
            msg = bundle.getString(messageId);
        } catch (Exception e) {
        }
        return msg;
    }

    public static void main(String[] args) {
        System.out.println(ResourceUtil.lerBundle("incluir", ResourceUtil.LABEL));
    }
}
