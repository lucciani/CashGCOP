/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cgcop.administrativo.modelo;

import br.com.cgcop.utilitarios.RemoveCaracteresUtil;
import java.io.Serializable;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.validator.constraints.NotBlank;

/**
 * Classe do Projeto GuardiaoModelos criada em 26/06/2013
 *
 * @author: ari
 */
@Entity
@Table(name = "municipio", schema = "administrativo")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Municipio implements Serializable {

    @Id
    @Column(name = "mun_id", nullable = false)
    @NotNull
    private Integer id;
    @Column(name = "mun_nome", nullable = false)
    @NotBlank
    private String nome;
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "und_fed_id", referencedColumnName = "und_fed_id", nullable = false)
    @NotNull
    private UnidadeFederativa unidadeFederativa;
    @Column(nullable = false, name = "mun_ativo", columnDefinition = " boolean DEFAULT true ")
    @NotNull
    private boolean ativo;

    public Municipio() {
    }

    public Municipio(Integer id) {
        this.id = id;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = RemoveCaracteresUtil.removeAccentos(nome.toUpperCase());
    }

    public UnidadeFederativa getUnidadeFederativa() {
        return unidadeFederativa;
    }

    public void setUnidadeFederativa(UnidadeFederativa unidadeFederativa) {
        this.unidadeFederativa = unidadeFederativa;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Municipio other = (Municipio) obj;
        return Objects.equals(this.id, other.id);
    }

    @Override
    public String toString() {
        return String.valueOf(id);
    }

   
    
}
