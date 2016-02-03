/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cgcop.solicitacao.managedbean;

import br.com.cgcop.solicitacao.Controller.ItemDespesasController;
import br.com.cgcop.solicitacao.modelo.ItemDespesas;
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
public class ItemDespesasMB extends BeanGenerico implements Serializable {

    @Inject
    private ItemDespesasController itdController;
    private ItemDespesas itemDespesas;
    private List<ItemDespesas> listaItemDespesas;

    @PostConstruct
    @Override
    public void init() {
        criarListaDeCamposDaConsulta();
        itemDespesas = (ItemDespesas) lerRegistroDaSessao("itemDespesas");
        if(itemDespesas == null){
            itemDespesas = new ItemDespesas();
        }
        listaItemDespesas = new ArrayList<>();
    }

     public void salvar() {
        try {
            itdController.salvar(itemDespesas);
            MensagensUtil.enviarMessageParamentroInfo(MensagensUtil.REGISTRO_SUCESSO, itemDespesas.getId());
            init();
        } catch (Exception ex) {
            MensagensUtil.enviarMessageErro(MensagensUtil.REGISTRO_FALHA);
            Logger.getLogger(ItemDespesasMB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    

    public void consultarItemDeDespesas() {
        try {
            listaItemDespesas = itdController.consultarLike(getCampoConsuta(), getValorCampoConsuta().toUpperCase());
        } catch (Exception ex) {
            Logger.getLogger(ItemDespesasMB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected Map<String, Object> getCampo() {
        Map<String, Object> map = new HashMap<>();
        map.put("Código", "id");
        return map;
    }
    
    public ItemDespesas getItemDespesas() {
        return itemDespesas;
    }

    public void setItemDespesas(ItemDespesas itemDespesas) {
        this.itemDespesas = itemDespesas;
    }

    public List<ItemDespesas> getListaItemDespesas() {
        return listaItemDespesas;
    }
    
}
