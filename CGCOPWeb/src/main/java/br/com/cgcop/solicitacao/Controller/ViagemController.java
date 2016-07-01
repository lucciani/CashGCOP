/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cgcop.solicitacao.Controller;

import br.com.cgcop.solicitacao.DAO.ViagemDAO;
import br.com.cgcop.solicitacao.modelo.Viagem;
import br.com.cgcop.utilitario.ControllerGenerico;
import br.com.cgcop.utilitarios.ManipuladorDeArquivo;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author Gian
 */
@Stateless
public class ViagemController extends ControllerGenerico<Viagem, Long> implements Serializable {

    @Inject
    private ViagemDAO dao;

    @PostConstruct
    @Override
    protected void inicializaDAO() {
        setDAO(dao);
    }

    public void addDoc(String nomeDoArquivo, byte[] conteudo, String diretorioRealDoc) throws IOException {
        if (conteudo != null) {
            //Nome da pasta q salva as imagens no hd
            String nomeDaPasta = ManipuladorDeArquivo.getDiretorioDocumentosCotacao();
            String nomeArquivoComExt = (nomeDoArquivo + ".pdf").trim().toLowerCase();
            //Remove do local
            ManipuladorDeArquivo.checarSeExisteExcluir(nomeDaPasta + File.separator + nomeArquivoComExt);
            //Remove do resource
            ManipuladorDeArquivo.checarSeExisteExcluir(diretorioRealDoc + File.separator + nomeArquivoComExt);
            //Grava no local
            ManipuladorDeArquivo.gravarArquivoLocalmente(nomeDaPasta, nomeArquivoComExt, conteudo);
            //GRava no resource
            ManipuladorDeArquivo.gravarArquivoLocalmente(diretorioRealDoc, nomeArquivoComExt, conteudo);
        }
//        //Joga o arquivo para dentro da pasta da aplicação 
//        String arq = diretorioRealLogo+separator+nomeArquivoComExt.trim().toLowerCase();
//        FileOutputStream img = new FileOutputStream(arq);
//        img.write(conteudo);
//        img.flush();
    }

    public Viagem salvarViagem(Viagem v) {
        return dao.atualizarGerenciar(v);
    }

    public List<Viagem> consultarPorPeriodo(Date data, Date dataFinal) {
        return dao.consultarPorPeriodo(data, dataFinal);
    }

}
