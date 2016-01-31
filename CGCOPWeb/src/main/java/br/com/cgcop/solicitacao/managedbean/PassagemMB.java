/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cgcop.solicitacao.managedbean;

import br.com.cgcop.administrativo.controller.MunicipioController;
import br.com.cgcop.administrativo.controller.UnidadeFederativaController;
import br.com.cgcop.administrativo.modelo.Endereco;
import br.com.cgcop.administrativo.modelo.Municipio;
import br.com.cgcop.administrativo.modelo.UnidadeFederativa;
import br.com.cgcop.solicitacao.Controller.PassagemController;
import br.com.cgcop.solicitacao.Controller.ViagemController;
import br.com.cgcop.solicitacao.modelo.Passagem;
import br.com.cgcop.solicitacao.modelo.Viagem;
import br.com.cgcop.utilitario.BeanGenerico;
import br.com.cgcop.utilitario.mensagens.MensagensUtil;
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
    private MunicipioController municipioController;
    private List<Municipio> listaDeMunicpios;
    @Inject
    private UnidadeFederativaController unidadeFederativaController;
    private UnidadeFederativa unidadeFederativa;
    private List<UnidadeFederativa> listaDeUnidadeFederativas;
    @Inject
    private ViagemController viagemController;
    private Viagem viagem;
    private List<Viagem> listaViagem;

    private Date data;
    private Date dataFinal;

    @PostConstruct
    @Override
    public void init() {
        try {
            criarListaDeCamposDaConsulta();
            passagem = (Passagem) lerRegistroDaSessao("passagem");
            if (passagem == null) {
                passagem = new Passagem();
                unidadeFederativa = new UnidadeFederativa();
                passagem.setOrigem(new Endereco());
                passagem.setDestino(new Endereco());
                passagem.setDataPartida(new Date());
                passagem.setDataRetorno(new Date());
                passagem.setViagem(new Viagem());
            } else {
                unidadeFederativa = passagem.getDestino().getUnidadeFederativa();
                unidadeFederativa = passagem.getOrigem().getUnidadeFederativa();
                consultarMuncipioPorUf();
            }
            listaPassagem = new ArrayList<>();
            listaDeUnidadeFederativas = unidadeFederativaController.consultarTodosOrdenadorPor("sigla");
        } catch (Exception ex) {
            Logger.getLogger(PassagemMB.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void consultarMuncipioPorUf() {
        listaDeMunicpios = municipioController.consultarMunicipioPor(unidadeFederativa);
    }

    public void salvar() {
        try {
            passagemController.salvar(passagem);
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

    public void setarViagem(Viagem v){
        passagem.setViagem(v);
    }
    
    public Passagem getPassagem() {
        return passagem;
    }

    public void setPassagem(Passagem passagem) {
        this.passagem = passagem;
    }

    public Viagem getViagem() {
        return viagem;
    }

    public void setViagem(Viagem viagem) {
        this.viagem = viagem;
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

    public List<Municipio> getListaDeMunicpios() {
        return listaDeMunicpios;
    }

    public List<UnidadeFederativa> getListaDeUnidadeFederativas() {
        return listaDeUnidadeFederativas;
    }

    public void setListaDeUnidadeFederativas(List<UnidadeFederativa> listaDeUnidadeFederativas) {
        this.listaDeUnidadeFederativas = listaDeUnidadeFederativas;
    }

    public List<Viagem> getListaViagem() {
        return listaViagem;
    }

    public void setListaViagem(List<Viagem> listaViagem) {
        this.listaViagem = listaViagem;
    }

    public UnidadeFederativa getUnidadeFederativa() {
        return unidadeFederativa;
    }

    public void setUnidadeFederativa(UnidadeFederativa unidadeFederativa) {
        this.unidadeFederativa = unidadeFederativa;
    }
    
}
