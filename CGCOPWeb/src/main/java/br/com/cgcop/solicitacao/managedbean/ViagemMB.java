/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cgcop.solicitacao.managedbean;

import br.com.cgcop.administrativo.controller.CentroDeCustoController;
import br.com.cgcop.administrativo.controller.ColaboradorController;
import br.com.cgcop.administrativo.controller.EmpresaController;
import br.com.cgcop.administrativo.controller.MunicipioController;
import br.com.cgcop.administrativo.controller.UnidadeFederativaController;
import br.com.cgcop.administrativo.modelo.CentroDeCusto;
import br.com.cgcop.administrativo.modelo.Colaborador;
import br.com.cgcop.administrativo.modelo.Empresa;
import br.com.cgcop.administrativo.modelo.Municipio;
import br.com.cgcop.administrativo.modelo.UnidadeFederativa;
import br.com.cgcop.solicitacao.Controller.HospedagemController;
import br.com.cgcop.solicitacao.Controller.PassagemController;
import br.com.cgcop.solicitacao.Controller.ViagemController;
import br.com.cgcop.solicitacao.modelo.Hospedagem;
import br.com.cgcop.solicitacao.modelo.Passageiro;
import br.com.cgcop.solicitacao.modelo.Passagem;
import br.com.cgcop.solicitacao.modelo.Viagem;
import br.com.cgcop.utilitario.BeanGenerico;
import br.com.cgcop.utilitario.mensagens.MensagensUtil;
import br.com.cgcop.utilitario.relatorio.RelatorioSession;
import br.com.cgcop.utilitarios.relatorios.AssistentedeRelatorio;
import br.com.cgcop.utilitarios.relatorios.PastasRelatorio;
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
public class ViagemMB extends BeanGenerico implements Serializable {

    @Inject
    private ViagemController viagemController;
    private Viagem viagem;
    private List<Viagem> listaViagem;
    @Inject
    private EmpresaController empresaController;
    private Empresa empresa;
    private List<Empresa> listaEmpresa;
    @Inject
    private ColaboradorController colaboradorController;
    private Colaborador colaborador;
    private List<Colaborador> passageiros;
    @Inject
    private PassagemController passagemController;
    private Passagem passagem;
    private List<Passagem> listaDePassagens;
    @Inject
    private HospedagemController hospedagemController;
    private Hospedagem hospedagem;
    private List<Hospedagem> listaDeHospedagens;
    @Inject
    private UnidadeFederativaController unidadeFederativaController;
    private UnidadeFederativa unidadeFederativa;
    private List<UnidadeFederativa> listaDeUnidadeFederativas;
    @Inject
    private MunicipioController municipioController;
    private List<Municipio> listaDeMunicpios;

    private List<CentroDeCusto> listaCentroDeCusto;

    private Date dataDaSolicitacao;
    private Date dataFinal;

    @PostConstruct
    @Override
    public void init() {
        try {
            criarListaDeCamposDaConsulta();
            viagem = (Viagem) lerRegistroDaSessao("viagem");
            if (viagem == null) {
                viagem = new Viagem();
                dataDaSolicitacao = new Date();
                dataFinal = new Date();
                unidadeFederativa = new UnidadeFederativa();
                viagem.setEmpresa(new Empresa());
                viagem.setCentroDeCusto(new CentroDeCusto());
//                viagem.setPassageiros(new ArrayList<>());
//                viagem.setPassagens(new ArrayList<>());
            }
            listaViagem = new ArrayList<>();
            listaEmpresa = empresaController.consultarTodosOrdenadorPor("nome");
        } catch (Exception ex) {
            Logger.getLogger(ViagemMB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void salvar() {
        try {
            viagemController.salvar(viagem);
            MensagensUtil.enviarMessageParamentroInfo(MensagensUtil.REGISTRO_SUCESSO, viagem.getId());
            init();
        } catch (Exception ex) {
            MensagensUtil.enviarMessageErro(MensagensUtil.REGISTRO_FALHA);
            Logger.getLogger(ViagemMB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void consultarViagem() {
        try {
            listaViagem = viagemController.consultarPorPeriodo(dataDaSolicitacao, dataFinal);
        } catch (Exception ex) {
            Logger.getLogger(ViagemMB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void consultarPassageiro() {
        try {
            passageiros = colaboradorController.consultarAtivo(getCampoConsuta(), getValorCampoConsuta(), "col_ativo=true");
        } catch (Exception ex) {
            Logger.getLogger(ViagemMB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void consultarMuncipioPorUf() {
        listaDeMunicpios = municipioController.consultarMunicipioPor(unidadeFederativa);
    }

    @Override
    protected Map<String, Object> getCampo() {
        Map<String, Object> map = new HashMap<>();
        map.put("Data", "dataDeSolicitacao");
        return map;
    }

    public void setarViagem(Viagem v) {
        try {
            viagem = v;
            passageiros = colaboradorController.consultarTodosOrdenadorPor("nome");
        } catch (Exception ex) {
            Logger.getLogger(ViagemMB.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void setarCentroDeCusto(CentroDeCusto c) {
        viagem.setCentroDeCusto(c);
    }

    public void addPassageiro(Colaborador c) {
        try {
//            viagem.addPassageiros(c);
            viagemController.atualizar(viagem);
            MensagensUtil.enviarMessageInfo(MensagensUtil.REGISTRO_SUCESSO);
        } catch (Exception ex) {
            MensagensUtil.enviarMessageErro(MensagensUtil.REGISTRO_FALHA);
            Logger.getLogger(ViagemMB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void delPassageiro(Colaborador c) {
        try {
//            viagem.removerPassageiro(c);
            viagemController.atualizar(viagem);
            MensagensUtil.enviarMessageInfo(MensagensUtil.REGISTRO_SUCESSO);
        } catch (Exception ex) {
            MensagensUtil.enviarMessageErro(MensagensUtil.REGISTRO_FALHA);
            Logger.getLogger(ViagemMB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

//    public boolean renderBtnAdd(Colaborador c) {
//        return viagem.getPassageiros().contains(c);
//    }

    public Viagem getViagem() {
        return viagem;
    }

    public void setViagem(Viagem viagem) {
        this.viagem = viagem;
    }

    public Date getDataDaSolicitacao() {
        return dataDaSolicitacao;
    }

    public void setDataDaSolicitacao(Date dataDaSolicitacao) {
        this.dataDaSolicitacao = dataDaSolicitacao;
    }

    public Date getDataFinal() {
        return dataFinal;
    }

    public void setDataFinal(Date dataFinal) {
        this.dataFinal = dataFinal;
    }

    public List<Viagem> getListaViagem() {
        return listaViagem;
    }

    public List<Empresa> getListaEmpresa() {
        return listaEmpresa;
    }

    public List<CentroDeCusto> getListaCentroDeCusto() {
        return listaCentroDeCusto;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public Colaborador getColaborador() {
        return colaborador;
    }

    public void setColaborador(Colaborador colaborador) {
        this.colaborador = colaborador;
    }

    public List<Colaborador> getPassageiros() {
        return passageiros;
    }

    public Passagem getPassagem() {
        return passagem;
    }

    public void setPassagem(Passagem passagem) {
        this.passagem = passagem;
    }

    public List<Passagem> getListaDePassagens() {
        return listaDePassagens;
    }

    public List<UnidadeFederativa> getListaDeUnidadeFederativas() {
        return listaDeUnidadeFederativas;
    }

    public List<Municipio> getListaDeMunicpios() {
        return listaDeMunicpios;
    }

    public Hospedagem getHospedagem() {
        return hospedagem;
    }

    public void setHospedagem(Hospedagem hospedagem) {
        this.hospedagem = hospedagem;
    }

    public List<Hospedagem> getListaDeHospedagens() {
        return listaDeHospedagens;
    }

}
