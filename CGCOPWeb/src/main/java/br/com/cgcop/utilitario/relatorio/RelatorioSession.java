/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cgcop.utilitario.relatorio;

import java.io.Serializable;
import javax.el.ELResolver;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;

/**
 * Classe do Projeto Guardião - Criado em 28/05/2013 -
 *
 * @author Gilmário
 */
@SessionScoped
@Named
public class RelatorioSession implements Serializable {

    public static final String CHAVE_RELATORIO = "byte_relatorio";
    private byte[] bytes;

    public byte[] getBytes() {
        return bytes;
    }

    public void clear() {
        bytes = new byte[0];
    }

    public void setBytes(byte[] bytes) {
        this.bytes = bytes;
    }

    public static void setBytesRelatorio(byte[] bytes) {
        try {
            FacesContext context = FacesContext.getCurrentInstance();
            ELResolver resolver = context.getApplication().getELResolver();
            RelatorioSession rel = (RelatorioSession) resolver.getValue(context.getELContext(), null, "relatorioSession");
            if (rel != null) {
                rel.setBytes(bytes);
            } else {
                rel = new RelatorioSession();
                rel.setBytes(bytes);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void setBytesRelatorioInSession(byte[] bytes) {
        try {
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put(CHAVE_RELATORIO, bytes);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
