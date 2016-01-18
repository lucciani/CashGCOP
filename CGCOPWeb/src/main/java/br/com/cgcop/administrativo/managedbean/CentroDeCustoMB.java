/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cgcop.administrativo.managedbean;

import br.com.cgcop.administrativo.controller.CentroDeCustoController;
import br.com.cgcop.administrativo.modelo.CentroDeCusto;
import br.com.cgcop.utilitario.BeanGenerico;
import br.com.cgcop.utilitario.mensagens.MensagensUtil;
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

/**
 *
 * @author Gian
 */
@Named
@ViewScoped
public class CentroDeCustoMB extends BeanGenerico implements Serializable {

    @Inject
    private CentroDeCustoController centroDeCustoController;

    private CentroDeCusto centroDeCusto;
    private List<CentroDeCusto> listaCentroDeCusto;

    @PostConstruct
    @Override
    public void init() {
        criarListaDeCamposDaConsulta();
        centroDeCusto = (CentroDeCusto) lerRegistroDaSessao("centroCusto");
        centroDeCusto = new CentroDeCusto();
        listaCentroDeCusto = new ArrayList<>();
    }

    public void salvar() {
        try {
            centroDeCustoController.salvar(centroDeCusto);
            MensagensUtil.enviarMessageParamentroInfo(MensagensUtil.REGISTRO_SUCESSO, centroDeCusto.getDescricao());
            init();
        } catch (Exception ex) {
            MensagensUtil.enviarMessageErro(MensagensUtil.REGISTRO_FALHA);
            Logger.getLogger(CentroDeCustoMB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void consultarCentroDeCusto() {
        try {
            listaCentroDeCusto = centroDeCustoController.consultarLike(getCampoConsuta(), getValorCampoConsuta().toUpperCase());
        } catch (Exception ex) {
            Logger.getLogger(CentroDeCustoMB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected Map<String, Object> getCampo() {
        Map<String, Object> map = new HashMap<>();
        map.put("Código", "id");
        return map;
    }

    public CentroDeCusto getCentroDeCusto() {
        return centroDeCusto;
    }

    public void setCentroDeCusto(CentroDeCusto centroDeCusto) {
        this.centroDeCusto = centroDeCusto;
    }

    public List<CentroDeCusto> getListaCentroDeCusto() {
        return listaCentroDeCusto;
    }
    
}
