/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cgcop.administrativo.managedbean;

import br.com.cgcop.administrativo.controller.EmpresaController;
import br.com.cgcop.administrativo.controller.MunicipioController;
import br.com.cgcop.administrativo.controller.UnidadeFederativaController;
import br.com.cgcop.administrativo.modelo.Empresa;
import br.com.cgcop.administrativo.modelo.Endereco;
import br.com.cgcop.administrativo.modelo.Municipio;
import br.com.cgcop.administrativo.modelo.UnidadeFederativa;
import br.com.cgcop.utilitario.BeanGenerico;
import br.com.cgcop.utilitario.mensagens.MensagensUtil;
import br.com.cgcop.utilitario.relatorio.RelatorioSession;
import br.com.cgcop.utilitarios.ManipuladorDeArquivo;
import br.com.cgcop.utilitarios.relatorios.AssistentedeRelatorio;
import br.com.cgcop.utilitarios.relatorios.PastasRelatorio;
import static java.io.File.separator;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author ari
 */
@Named
@ViewScoped
public class EmpresaMB extends BeanGenerico implements Serializable {

    @Inject
    private EmpresaController empresaController;
    private Empresa empresa;
    private List<Empresa> listaDeEmpresas;
    private UploadedFile arquivoUpload;
    private byte logo[];

    @PostConstruct
    @Override
    public void init() {
        try {
            criarListaDeCamposDaConsulta();
            empresa = (Empresa) lerRegistroDaSessao("empresa");
            if (empresa == null) {
                empresa = new Empresa();
                empresa.setAtivo(true);
            } else {
                logo = ManipuladorDeArquivo.lerArquivoEmByte(getDiretorioReal("resources" + separator + "images"+separator+empresa.getNome()+".png"));
            }
        } catch (Exception ex) {
            Logger.getLogger(EmpresaMB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void salvar() {
        try {

            empresaController.salvar(empresa);
            empresaController.addLogo(empresa.getNome(), logo, getDiretorioReal("resources" + separator + "images"));
            MensagensUtil.enviarMessageParamentroInfo(MensagensUtil.REGISTRO_SUCESSO, empresa.getNome());
            init();
        } catch (Exception ex) {
            MensagensUtil.enviarMessageErro(MensagensUtil.REGISTRO_FALHA);
            Logger.getLogger(EmpresaMB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void desativarEmpresa(){
        try {
            empresa.setAtivo(false);
            empresaController.atualizar(empresa);
            MensagensUtil.enviarMessageParamentroInfo(MensagensUtil.REGISTRO_ATUALIZADO, "teress","rewe");
        } catch (Exception ex) {
            MensagensUtil.enviarMessageErro(MensagensUtil.REGISTRO_FALHA);
            Logger.getLogger(EmpresaMB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void consultarEmpresa() {
        try {
            listaDeEmpresas = empresaController.consultarTodos("nome", getCampoConsuta(), getValorCampoConsuta());
        } catch (Exception ex) {
            Logger.getLogger(EmpresaMB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void fileUploud(FileUploadEvent event) {
        try {
            logo = event.getFile().getContents();

//            MenssagemUtil.enaddMessageInfo(NavegacaoMB.getMsg("salvar_processo", MenssagemUtil.MENSAGENS));
        } catch (Exception ex) {
            Logger.getLogger(EmpresaMB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
     

    @Override
    protected Map<String, Object> getCampo() {
        Map<String, Object> map = new HashMap<>();
        map.put("Nome", "nome");

        return map;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public List<Empresa> getListaDeEmpresas() {
        return listaDeEmpresas;
    }


    public UploadedFile getArquivoUpload() {
        return arquivoUpload;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public void setArquivoUpload(UploadedFile arquivoUpload) {
        this.arquivoUpload = arquivoUpload;
    }

}
