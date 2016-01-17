/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cgcop.seguranca.modelo;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 *
 * @author ari
 */
@Entity
@Table(name = "permissao", schema = "seguranca", uniqueConstraints = @UniqueConstraint(columnNames = {"tar_id", "usr_id"}))
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Permissao implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "per_id", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "tar_id", referencedColumnName = "tar_id", nullable = false)
    private Tarefa tarefa;

    @ManyToOne
    @JoinColumn(name = "usr_id", referencedColumnName = "usr_id", nullable = false)
    private Usuario usuario;

    // Permitir ao usuario acessar e consultar um determinado formulario/página(Tarefas)
    @Column(name = "per_consultar")
    private boolean consultar;

    // Permitir ao usuario editar valores em uma detarminada tarefa
    @Column(name = "per_editar")
    private boolean editar;

    // Permitir ao usuario excluir valores em uma detarminada tarefa
    @Column(name = "per_excluir")
    private boolean excluir;

    // Permitir ao usuario incluir valores em uma detarminada tarefa
    @Column(name = "per_incluir")
    private boolean incluir;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Tarefa getTarefa() {
        return tarefa;
    }

    public void setTarefa(Tarefa tarefa) {
        this.tarefa = tarefa;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
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

    public boolean isExcluir() {
        return excluir;
    }

    public void setExcluir(boolean excluir) {
        this.excluir = excluir;
    }

    public boolean isIncluir() {
        return incluir;
    }

    public void setIncluir(boolean incluir) {
        this.incluir = incluir;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 53 * hash + Objects.hashCode(this.id);
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
        final Permissao other = (Permissao) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }
    
    
    

}
