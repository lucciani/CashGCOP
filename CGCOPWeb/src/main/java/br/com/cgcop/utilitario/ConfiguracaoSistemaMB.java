/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cgcop.utilitario;

import br.com.cgcop.administrativo.managedbean.EmpresaMB;
import br.com.cgcop.seguranca.controller.ModuloController;
import br.com.cgcop.seguranca.controller.PermissaoController;
import br.com.cgcop.seguranca.controller.TarefaController;
import br.com.cgcop.seguranca.controller.UsuarioController;
import br.com.cgcop.utilitarios.ManipuladorDeArquivo;
import java.io.File;
import static java.io.File.separator;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

/**
 *
 * @author Ari
 */
@ApplicationScoped
@Named
public class ConfiguracaoSistemaMB implements Serializable {

    @Inject
    private ModuloController moduloController;
    @Inject
    private UsuarioController usuarioController;
    @Inject
    private TarefaController tarefaController;
    @Inject
    private PermissaoController permissaoController;
    private final String paraCarregarNoLogin = "Identificação do Usuário";

    @PostConstruct
    public void init() {

        try {
            usuarioController.criarUsuarioAdministrado();
            moduloController.criarModulos();
            tarefaController.criarTarefas();
            permissaoController.criarPermissaoUsuarioAdmim();
            addArquivosLogo();
        } catch (Exception ex) {
            Logger.getLogger(ConfiguracaoSistemaMB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String getDiretorioReal(String diretorio) {
        return FacesContext.getCurrentInstance().getExternalContext().getRealPath(diretorio);

    }

    private void addArquivosLogo() {
        List<File> listaDeArquivosDaPasta = ManipuladorDeArquivo.listaDeArquivosDaPasta(ManipuladorDeArquivo.getDiretorioLogos());
        try {
            for (File file : listaDeArquivosDaPasta) {
                InputStream is = null;
                byte[] buffer = null;

                is = new FileInputStream(file);
                buffer = new byte[is.available()];
                is.read(buffer);
                is.close();
                ManipuladorDeArquivo.gravarArquivoLocalmente(getDiretorioReal("resources" + separator + "images"), file.getName(), buffer);

            }
        } catch (IOException ex) {
            Logger.getLogger(EmpresaMB.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public String getParaCarregarNoLogin() {
        return paraCarregarNoLogin;
    }

}
