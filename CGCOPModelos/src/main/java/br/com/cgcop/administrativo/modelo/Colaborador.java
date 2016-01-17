/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cgcop.administrativo.modelo;

import br.com.cgcop.utilitarios.CpfCnpjUtil;
import br.com.cgcop.utilitarios.StringUtil;
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
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

/**
 *
 * @author Giancarlo
 */
@Entity
@Table(name = "colaborador", schema = "administrativo")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Colaborador implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "col_id", nullable = false)
    private Long id;
    
    @NotBlank
    @Column(name = "col_nome", nullable = false, unique = true)
    private String nome;
    
    @NotBlank
    @Column(name = "col_cpf", nullable = false, unique = true)
    private String cpf;
    
    @Email
    @Column(name = "col_email")
    private String email;
    
    @Column(name = "col_telefone")
    private String telefone;
    
    @Column(name = "col_celular")
    private String celular;
    
    @ManyToOne
    @JoinColumn(name = "pes_id", referencedColumnName = "pes_id")
    private Pessoa pessoa;
    
    @Column(name = "col_ativo",columnDefinition = "boolean default true")
    private boolean ativo;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return CpfCnpjUtil.formataCPF(cpf);
    }

    public void setCpf(String cpf) {
        this.cpf = StringUtil.removerCaracteresEspeciais(cpf);
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return StringUtil.formatarTelefone(telefone);
    }

    public void setTelefone(String telefone) {
        this.telefone = StringUtil.removerCaracteresEspeciais(telefone);
    }

    public String getCelular() {
        return StringUtil.formatarTelefone(celular);
    }

    public void setCelular(String celular) {
        this.celular = StringUtil.removerCaracteresEspeciais(celular);
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }
    
    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 23 * hash + Objects.hashCode(this.id);
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
        final Colaborador other = (Colaborador) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }
}
