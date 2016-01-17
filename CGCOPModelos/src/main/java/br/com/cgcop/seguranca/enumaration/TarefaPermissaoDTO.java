/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cgcop.seguranca.enumaration;

import br.com.cgcop.seguranca.modelo.Tarefa;
import java.io.Serializable;
import java.util.Objects;

/**
 *
 * @author ari
 */
public class TarefaPermissaoDTO implements Serializable{
    
    private Tarefa tarefa;
    private boolean incluir;
    private boolean excluir;
    private boolean consultar;
    private boolean editar;

    public TarefaPermissaoDTO() {
        incluir =false;
        excluir =false;
        consultar =false;
        editar =false;
    }
    
    

    public Tarefa getTarefa() {
        return tarefa;
    }

    public void setTarefa(Tarefa tarefa) {
        this.tarefa = tarefa;
    }

    public boolean isIncluir() {
        return incluir;
    }

    public void setIncluir(boolean incluir) {
        this.incluir = incluir;
    }

    public boolean isExcluir() {
        return excluir;
    }

    public void setExcluir(boolean excluir) {
        this.excluir = excluir;
    }

    public boolean isConsultar() {
        return consultar;
    }

    public void setConsultar(boolean consultar) {
        this.consultar = consultar;
    }

    public boolean isEditar() {
        return editar;
    }

    public void setEditar(boolean editar) {
        this.editar = editar;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 43 * hash + Objects.hashCode(this.tarefa);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final TarefaPermissaoDTO other = (TarefaPermissaoDTO) obj;
        if (!Objects.equals(this.tarefa, other.tarefa)) {
            return false;
        }
        return true;
    }
    
    
}
