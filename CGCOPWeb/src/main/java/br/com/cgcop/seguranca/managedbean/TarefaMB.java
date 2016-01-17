/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cgcop.seguranca.managedbean;

import br.com.cgcop.seguranca.controller.ModuloController;
import br.com.cgcop.seguranca.controller.TarefaController;
import br.com.cgcop.seguranca.modelo.Modulo;
import br.com.cgcop.seguranca.modelo.Tarefa;
import br.com.cgcop.utilitario.BeanGenerico;
import java.io.Serializable;
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
 * @author ari
 */
@ViewScoped
@Named
public class TarefaMB extends BeanGenerico implements Serializable{
    
    
    @Inject
    private TarefaController tarefaController;
    @Inject
    private ModuloController moduloController;
    
    private List<Tarefa> listaTarefas;
    private List<Modulo> listaModulos;
    

    @PostConstruct
    @Override
    public void init() {
        try {
            listaModulos = moduloController.consultarTodosOrdenadorPor("nome");
        } catch (Exception ex) {
            Logger.getLogger(TarefaMB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void consultarTarefas(){
        try {
            listaTarefas = tarefaController.consultarPorModulo(getValorCampoConsuta());
        } catch (Exception ex) {
            Logger.getLogger(TarefaMB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected Map<String, Object> getCampo() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public List<Tarefa> getListaTarefas() {
        return listaTarefas;
    }

    public List<Modulo> getListaModulos() {
        return listaModulos;
    }
    
    
}
