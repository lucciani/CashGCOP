/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cgcop.solicitacao.managedbean;

import br.com.cgcop.administrativo.controller.AeroportoController;
import br.com.cgcop.administrativo.controller.MunicipioController;
import br.com.cgcop.administrativo.controller.UnidadeFederativaController;
import br.com.cgcop.administrativo.modelo.Aeroporto;
import br.com.cgcop.administrativo.modelo.Endereco;
import br.com.cgcop.administrativo.modelo.Municipio;
import br.com.cgcop.administrativo.modelo.UnidadeFederativa;
import br.com.cgcop.solicitacao.Controller.PassagemController;
import br.com.cgcop.solicitacao.Controller.ViagemController;
import br.com.cgcop.solicitacao.modelo.Passagem;
import br.com.cgcop.solicitacao.modelo.Viagem;
import br.com.cgcop.utilitario.BeanGenerico;
import br.com.cgcop.utilitario.mensagens.MensagensUtil;
import br.com.cgcop.utilitarios.ManipuladorDeArquivo;
import static java.io.File.separator;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
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
 * @author Gian
 */
@Named
@ViewScoped
public class PassagemMB extends BeanGenerico implements Serializable {

    @Inject
    private PassagemController passagemController;
    private Passagem passagem;
    private List<Passagem> listaPassagem;
    @Inject
    private AeroportoController aeroportoController;
    private Aeroporto aeroporto;
    private List<Aeroporto> aeroportos;

    private Date data;
    private Date dataFinal;

    private UploadedFile arquivoUpload;
    private byte documento[];
    private String nomeDocumendo;

    @PostConstruct
    @Override
    public void init() {
        try {
            criarListaDeCamposDaConsulta();
            passagem = (Passagem) lerRegistroDaSessao("passagem");
            if (passagem == null) {
                passagem = new Passagem();
                passagem.setOrigem(new Aeroporto());
                passagem.setDestino(new Aeroporto());
                passagem.setDataPartida(new Date());
                passagem.setViagem(new Viagem());
            } else {
                documento = ManipuladorDeArquivo.lerArquivoEmByte(getDiretorioReal("resources" + separator + "images"+separator+passagem.getId().toString()+".pdf"));
            }
            listaPassagem = new ArrayList<>();
            aeroportos = aeroportoController.consultarTodosOrdenadorPor("cidade");
        } catch (Exception ex) {
            Logger.getLogger(PassagemMB.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void salvar() {
        try {
            passagemController.salvar(passagem);
            passagemController.addDoc(passagem.getId().toString(), documento, getDiretorioReal("resources" + separator + "images"));
            MensagensUtil.enviarMessageParamentroInfo(MensagensUtil.REGISTRO_SUCESSO, passagem.getClass());
            init();
        } catch (Exception ex) {
            MensagensUtil.enviarMessageErro(MensagensUtil.REGISTRO_FALHA);
            Logger.getLogger(PassagemMB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void consultarPassagem() {
        try {
            listaPassagem = passagemController.consultarPorPeriodo(data, dataFinal);
        } catch (Exception ex) {
            Logger.getLogger(PassagemMB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected Map<String, Object> getCampo() {
        Map<String, Object> map = new HashMap<>();
        map.put("Data", "dataPartida");
        return map;
    }

    public void fileUploud(FileUploadEvent event) {
        try {
            documento = event.getFile().getContents();
        } catch (Exception ex) {
            Logger.getLogger(PassagemMB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void setarViagem(Viagem v) {
        passagem.setViagem(v);
    }

    public Passagem getPassagem() {
        return passagem;
    }

    public void setPassagem(Passagem passagem) {
        this.passagem = passagem;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public Date getDataFinal() {
        return dataFinal;
    }

    public void setDataFinal(Date dataFinal) {
        this.dataFinal = dataFinal;
    }

    public List<Passagem> getListaPassagem() {
        return listaPassagem;
    }

    public void setListaPassagem(List<Passagem> listaPassagem) {
        this.listaPassagem = listaPassagem;
    }

    public Aeroporto getAeroporto() {
        return aeroporto;
    }

    public void setAeroporto(Aeroporto aeroporto) {
        this.aeroporto = aeroporto;
    }

    public List<Aeroporto> getAeroportos() {
        return aeroportos;
    }

    public UploadedFile getArquivoUpload() {
        return arquivoUpload;
    }

    public void setArquivoUpload(UploadedFile arquivoUpload) {
        this.arquivoUpload = arquivoUpload;
    }

}
