/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cgcop.solicitacao.managedbean;

import br.com.cgcop.administrativo.controller.CentroDeCustoController;
import br.com.cgcop.administrativo.controller.ColaboradorController;
import br.com.cgcop.administrativo.controller.EmpresaController;
import br.com.cgcop.administrativo.modelo.CentroDeCusto;
import br.com.cgcop.administrativo.modelo.Colaborador;
import br.com.cgcop.administrativo.modelo.Empresa;
import br.com.cgcop.solicitacao.Controller.FundoFixoController;
import br.com.cgcop.solicitacao.modelo.FundoFixo;
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
public class FundoFixoMB extends BeanGenerico implements Serializable {

    @Inject
    private FundoFixoController fundoFixoController;
    @Inject
    private EmpresaController empresaController;
    @Inject
    private CentroDeCustoController centroDeCustoController;
    @Inject
    private ColaboradorController colaboradorController;

    private FundoFixo fundoFixo;
    private List<FundoFixo> listaFundoFixo;

    private Empresa empresa;
    private List<Empresa> listaDeEmpresas;

    private CentroDeCusto centroDeCusto;
    private List<CentroDeCusto> listaDeCentroDeCusto;

    private Colaborador colaborador;
    private List<Colaborador> listaDeColaboradores;

    private Date dataSolicitacao;

    @PostConstruct
    @Override
    public void init() {
        try {
            criarListaDeCamposDaConsulta();
            fundoFixo = (FundoFixo) lerRegistroDaSessao("fundoFixo");
            if (fundoFixo == null) {
                fundoFixo = new FundoFixo();
                dataSolicitacao = new Date();
                fundoFixo.setEmpresa(new Empresa());
                fundoFixo.setCentroDeCusto(new CentroDeCusto());
                fundoFixo.setColaborador(new Colaborador());
            }
            listaFundoFixo = new ArrayList<>();
            listaDeEmpresas = empresaController.consultarTodosOrdenadorPor("nome");
            listaDeCentroDeCusto = centroDeCustoController.consultarTodosOrdenadorPor("id");
            listaDeColaboradores = colaboradorController.consultarTodosOrdenadorPor("nome");
        } catch (Exception ex) {
            Logger.getLogger(FundoFixoMB.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void salvar() {
        try {
            fundoFixoController.salvar(fundoFixo);
            MensagensUtil.enviarMessageParamentroInfo(MensagensUtil.REGISTRO_SUCESSO, fundoFixo.getId());
            init();
        } catch (Exception ex) {
            MensagensUtil.enviarMessageErro(MensagensUtil.REGISTRO_FALHA);
            Logger.getLogger(FundoFixoMB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void consultarFundoFixo() {
        try {
            listaFundoFixo = fundoFixoController.consultarLike(getCampoConsuta(), getValorCampoConsuta().toUpperCase());
        } catch (Exception ex) {
            Logger.getLogger(FundoFixoMB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected Map<String, Object> getCampo() {
        Map<String, Object> map = new HashMap<>();
        map.put("Código", "id");
        return map;
    }

    public void setarCentroDeCusto(CentroDeCusto c){
        fundoFixo.setCentroDeCusto(c);
    }
    
    public void setarColaborador(Colaborador col){
        fundoFixo.setColaborador(col);
    }
    
    public FundoFixo getFundoFixo() {
        return fundoFixo;
    }

    public void setFundoFixo(FundoFixo fundoFixo) {
        this.fundoFixo = fundoFixo;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public CentroDeCusto getCentroDeCusto() {
        return centroDeCusto;
    }

    public void setCentroDeCusto(CentroDeCusto centroDeCusto) {
        this.centroDeCusto = centroDeCusto;
    }

    public Colaborador getColaborador() {
        return colaborador;
    }

    public void setColaborador(Colaborador colaborador) {
        this.colaborador = colaborador;
    }

    public Date getDataSolicitacao() {
        return dataSolicitacao;
    }

    public void setDataSolicitacao(Date dataSolicitacao) {
        this.dataSolicitacao = dataSolicitacao;
    }

    public List<FundoFixo> getListaFundoFixo() {
        return listaFundoFixo;
    }

    public List<Empresa> getListaDeEmpresas() {
        return listaDeEmpresas;
    }

    public List<CentroDeCusto> getListaDeCentroDeCusto() {
        return listaDeCentroDeCusto;
    }

    public List<Colaborador> getListaDeColaboradores() {
        return listaDeColaboradores;
    }

}
