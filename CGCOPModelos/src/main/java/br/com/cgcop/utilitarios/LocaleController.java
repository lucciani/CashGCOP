/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cgcop.utilitarios;

import java.io.Serializable;
import java.util.Locale;
import javax.annotation.PostConstruct;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;

/**
 *
 * @author ari
 */
//@ManagedBean
//@SessionScoped
public class LocaleController implements Serializable {

    private String teste;
    private Locale currentLocale = new Locale("pt", "BR");

    @PostConstruct
    public void init() {
        teste = currentLocale.getLanguage();
    }

    public void englishLocale() {
        UIViewRoot viewRoot = FacesContext.getCurrentInstance().getViewRoot();
        currentLocale = Locale.US;
        viewRoot.setLocale(currentLocale);
        teste = currentLocale.getLanguage();

    }

    public void portugueseLocale() {
        UIViewRoot viewRoot = FacesContext.getCurrentInstance().getViewRoot();
        currentLocale = new Locale("pt", "BR");
        viewRoot.setLocale(currentLocale);
        teste = currentLocale.getLanguage().toString();

    }

    public Locale getCurrentLocale() {
        return currentLocale;
    }

    public String getTeste() {
        return teste;
    }

}
