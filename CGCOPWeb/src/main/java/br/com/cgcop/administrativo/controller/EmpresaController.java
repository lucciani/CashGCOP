/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cgcop.administrativo.controller;

import br.com.cgcop.administrativo.DAO.EmpresaDAO;
import br.com.cgcop.administrativo.modelo.Empresa;
import br.com.cgcop.utilitario.ControllerGenerico;
import br.com.cgcop.utilitarios.ManipuladorDeArquivo;
import br.com.cgcop.utilitarios.StringUtil;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author ari
 */
@Stateless
public class EmpresaController extends ControllerGenerico<Empresa, Long> implements Serializable {

    @Inject
    private EmpresaDAO dao;
    @Inject
    private EnderecoController enderecoController;

    @PostConstruct
    @Override
    protected void inicializaDAO() {
        setDAO(dao);
    }

    @Override
    public void salvar(Empresa cl) throws Exception {
        cl.setCnpj(StringUtil.removerCaracteresEspeciais(cl.getCnpj()));
        dao.atualizar(cl);
    }

    public void addLogo(String nomeDaEmpresa, byte[] conteudo, String diretorioRealLogo) throws IOException {
        if (conteudo != null) {

            //Nome da pasta q salva as imagens no hd
            String nomeDaPasta = ManipuladorDeArquivo.PATH_WINDOWS + ManipuladorDeArquivo.getDiretorioLogos();

            String nomeArquivoComExt = (nomeDaEmpresa + ".png").trim().toLowerCase();

            //Remove do local
            ManipuladorDeArquivo.checarSeExisteExcluir(nomeDaPasta + File.separator + nomeArquivoComExt);

            //Remove do resource
            ManipuladorDeArquivo.checarSeExisteExcluir(diretorioRealLogo + File.separator + nomeArquivoComExt);

            //Grava no local
            ManipuladorDeArquivo.gravarArquivoLocalmente(nomeDaPasta, nomeArquivoComExt, conteudo);

            //GRava no resource
            ManipuladorDeArquivo.gravarArquivoLocalmente(diretorioRealLogo, nomeArquivoComExt, conteudo);

        }
//        //Joga o arquivo para dentro da pasta da aplicação 
//        String arq = diretorioRealLogo+separator+nomeArquivoComExt.trim().toLowerCase();
//        FileOutputStream img = new FileOutputStream(arq);
//        img.write(conteudo);
//        img.flush();
    }

}
