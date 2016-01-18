/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cgcop.administrativo.managedbean;

import br.com.cgcop.administrativo.controller.NaturezaController;
import br.com.cgcop.administrativo.modelo.Natureza;
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
public class NaturezaMB extends BeanGenerico implements Serializable {

    @Inject
    private NaturezaController naturezaController;

    private Natureza natureza;
    private List<Natureza> listaNatureza;

    @PostConstruct
    @Override
    public void init() {
        criarListaDeCamposDaConsulta();
        natureza = (Natureza) lerRegistroDaSessao("natureza");
        natureza = new Natureza();
        listaNatureza = new ArrayList<>();

    }

    public void salvar() {
        try {
            naturezaController.salvar(natureza);
            MensagensUtil.enviarMessageParamentroInfo(MensagensUtil.REGISTRO_SUCESSO, natureza.getDescricao());
            init();
        } catch (Exception ex) {
            MensagensUtil.enviarMessageErro(MensagensUtil.REGISTRO_FALHA);
            Logger.getLogger(NaturezaMB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void consultarNatureza() {
        try {
            listaNatureza = naturezaController.consultarLike(getCampoConsuta(), getValorCampoConsuta().toUpperCase());
        } catch (Exception ex) {
            Logger.getLogger(NaturezaMB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected Map<String, Object> getCampo() {
        Map<String, Object> map = new HashMap<>();
        map.put("Código", "id");
        map.put("Descrição", "descricao");
        return map;
    }

    public Natureza getNatureza() {
        return natureza;
    }

    public void setNatureza(Natureza natureza) {
        this.natureza = natureza;
    }

    public List<Natureza> getListaNatureza() {
        return listaNatureza;
    }
    
}
