/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cgcop.utilitarios.relatorios;

import java.io.Serializable;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

/**
 * Classe do Projeto ******* - Criado em 16/05/2013 -
 *
 */
@ManagedBean
@ViewScoped
public class AssistentedeRelatorio implements Serializable {

    
    
    
    public AssistentedeRelatorio() {
    }

    public String getDiretorioReal(String diretorio) {
        HttpSession hSession = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        return hSession.getServletContext().getRealPath(diretorio);
    }
 

   

    public byte[] relatorioemByte(List lista, Map<String, Object> parametros, String pastaRelatorio,String arquivo,String nomeRelatorio) {
        byte[] b = null;
        try {
            String separator = System.getProperty("file.separator"); 

            parametros.put("img", getDiretorioReal("resources"+separator+"images"+separator+"LOGO.svg"));
            parametros.put("relatorio", nomeRelatorio);
            parametros.put("SUBREPORT_DIR", getDiretorioReal(pastaRelatorio) + separator);
            parametros.put("REPORT_LOCALE", new Locale("pt", "BR"));
           
            String rel = getDiretorioReal(arquivo);
            JRDataSource jrRS = new JRBeanCollectionDataSource(lista);
            JasperPrint print = JasperFillManager.fillReport(rel, parametros, jrRS);
            b = JasperExportManager.exportReportToPdf(print);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return b;
    }
    
   
}
