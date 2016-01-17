/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cgcop.seguranca.modelo;

import br.com.cgcop.administrativo.modelo.Colaborador;
import br.com.cgcop.administrativo.modelo.Empresa;
import br.com.cgcop.utilitarios.CriptografiaSenha;
import java.io.Serializable;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

/**
 *
 * @author ari
 */
@Entity
@Table(name = "usuario", schema = "seguranca")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Usuario implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "usr_id", nullable = false)
    private Long id;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "col_id", referencedColumnName = "col_id", nullable = false)
    private Colaborador colaborador;

    @Length(min = 6)
    @NotBlank
    @Column(name = "usr_senha", nullable = false, length = 512)
    private String senha;

    @NotBlank
    @Column(name = "usr_login", nullable = false, length = 512, unique = true)
    private String login;

    @NotNull
    @Column(name = "usr_ativo", nullable = false)
    private boolean ativo;

    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @ManyToMany(fetch = FetchType.EAGER)
    @Fetch(FetchMode.SELECT)
    private List<Empresa> empresas;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Empresa> getEmpresas() {
        return Collections.unmodifiableList(empresas);
    }

    public void setEmpresas(List<Empresa> empresas) {
        this.empresas = empresas;
    }

    public void addEmpresa(Empresa empresa) {
        empresas.add(empresa);
    }

    public void removerEmpresa(Empresa empresa) {
        if (empresas.contains(empresa)) {
            empresas.remove(empresa);
        }
    }

    public Colaborador getColaborador() {
        return colaborador;
    }

    public void setColaborador(Colaborador colaborador) {
        this.colaborador = colaborador;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = CriptografiaSenha.criptografarSenha(senha);
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public String getNomeDoColaborador() {
        if (this.colaborador != null) {

            return this.colaborador.getNome();
        }
        return "";
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + Objects.hashCode(this.id);
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
        final Usuario other = (Usuario) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

}
