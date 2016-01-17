/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cgcop.administrativo.managedbean;

import br.com.cgcop.administrativo.controller.PessoaController;
import br.com.cgcop.administrativo.enumeration.TipoPessoa;
import br.com.cgcop.administrativo.modelo.Pessoa;
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
public class PessoaMB extends BeanGenerico implements Serializable {

    @Inject
    private PessoaController pessoaController;

    private Pessoa pessoa;
    private List<Pessoa> listaPessoas;

    @PostConstruct
    @Override
    public void init() {
        pessoa = new Pessoa();
        listaPessoas = new ArrayList<>();
        criarListaDeCamposDaConsulta();
    }

    public void salvar() {
        try {
            pessoaController.salvar(pessoa);
            MensagensUtil.enviarMessageParamentroInfo(MensagensUtil.REGISTRO_SUCESSO, pessoa.getNome());
            init();
        } catch (Exception ex) {
            MensagensUtil.enviarMessageErro(MensagensUtil.REGISTRO_FALHA);
            Logger.getLogger(PessoaMB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void consultar() {
        try {
            listaPessoas = pessoaController.consultarLike(getCampoConsuta(), getValorCampoConsuta());
        } catch (Exception ex) {
            Logger.getLogger(PessoaMB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected Map<String, Object> getCampo() {
        Map<String, Object> map = new HashMap<>();
        map.put("Nome", "nome");
        return map;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public List<Pessoa> getListaPessoas() {
        return listaPessoas;
    }
    
    public TipoPessoa[] getListaTipoDePessoas() {
        return TipoPessoa.values();
    }

}
