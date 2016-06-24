/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cgcop.administrativo.managedbean;

import br.com.cgcop.administrativo.controller.AeroportoController;
import br.com.cgcop.administrativo.modelo.Aeroporto;
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
 * @author Giancarlo
 */
@Named
@ViewScoped
public class AeroportoMB extends BeanGenerico implements Serializable {

    @Inject
    private AeroportoController aeroportoController;
    private Aeroporto aeroporto;
    private List<Aeroporto> aeroportos;

    @PostConstruct
    @Override
    public void init() {
        try {
            criarListaDeCamposDaConsulta();
            aeroporto = (Aeroporto) lerRegistroDaSessao("aeroporto");
            if (aeroporto == null) {
                aeroporto = new Aeroporto();
            }
            aeroportos = new ArrayList<>();
        } catch (Exception ex) {
            Logger.getLogger(ColaboradorMB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void salvar() {
        try {
            aeroportoController.salvar(aeroporto);
            MensagensUtil.enviarMessageParamentroInfo(MensagensUtil.REGISTRO_SUCESSO, aeroporto.getAeroporto());
            init();
        } catch (Exception ex) {
            MensagensUtil.enviarMessageErro(MensagensUtil.REGISTRO_FALHA);
            Logger.getLogger(AeroportoMB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected Map<String, Object> getCampo() {
        Map<String, Object> map = new HashMap<>();
        map.put("Sigla ", "iata");
        map.put("Aeroporto", "aeroporto");
        return map;
    }

    public Aeroporto getAeroporto() {
        return aeroporto;
    }

    public List<Aeroporto> getAeroportos() {
        return aeroportos;
    }
    
}
