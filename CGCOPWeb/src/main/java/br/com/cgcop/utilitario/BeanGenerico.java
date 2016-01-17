/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cgcop.utilitario;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import org.primefaces.context.RequestContext;

/**
 */
public abstract class BeanGenerico implements Serializable {

    private List<SelectItem> itensDeConsulta = new ArrayList<>();
    private String campoConsuta;
    private String valorCampoConsuta;

    public abstract void init();

    protected abstract Map<String, Object> getCampo();

    protected Object lerRegistroDaSessao(String chave) {
        return lerSessao().containsKey(chave) ? lerSessao().remove(chave) : null;
    }

    private Map<String, Object> lerSessao() {
        return FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
    }

    public void redirecionarPagina(String pag) {
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect(pag.concat(".xhtml"));
        } catch (IOException ex) {
            Logger.getLogger(BeanGenerico.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public RequestContext contextoRequisicaoPrimefaces() {
        return RequestContext.getCurrentInstance();
    }

    public String getNomeSistema() {
        return FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath();
    }

    public String getDiretorioReal(String diretorio) {
        return FacesContext.getCurrentInstance().getExternalContext().getRealPath(diretorio);

    }

    protected void escreverRegistroDaSessao(String chave, Object object) {
        lerSessao().put(chave, object);
    }

    public void escreverRegistroNasessaoNavegarParaEditar(Object p, String pagina, String chave) {
        escreverRegistroDaSessao(chave, p);
        redirecionarPagina(getNomeSistema().concat(pagina));
    }

    protected void criarListaDeCamposDaConsulta() {
        itensDeConsulta = new ArrayList<>();
        getCampo().entrySet().stream().map((getEntrySet) -> {
            Object key = getEntrySet.getKey();
            Object value = getEntrySet.getValue();
            SelectItem item = new SelectItem(value, key.toString());
            return item;
        }).forEach((item) -> {
            itensDeConsulta.add(item);
        });
    }

    public void setCampoConsuta(String campoConsuta) {
        this.campoConsuta = campoConsuta;
    }

    public String getCampoConsuta() {
        return campoConsuta;
    }

    public List<SelectItem> getItensDeConsulta() {
        return itensDeConsulta;
    }

    public String getValorCampoConsuta() {
        return valorCampoConsuta;
    }

    public void setValorCampoConsuta(String valorCampoConsuta) {
        this.valorCampoConsuta = valorCampoConsuta;
    }

}
