/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cgcop.seguranca.controller;

import br.com.cgcop.administrativo.DAO.ColaboradorDAO;
import br.com.cgcop.administrativo.DAO.PessoaDAO;
import br.com.cgcop.administrativo.controller.ColaboradorController;
import br.com.cgcop.administrativo.enumeration.TipoPessoa;
import br.com.cgcop.administrativo.modelo.Colaborador;
import br.com.cgcop.administrativo.modelo.Pessoa;
import br.com.cgcop.seguranca.DAO.UsuarioDAO;
import br.com.cgcop.seguranca.modelo.Usuario;
import br.com.cgcop.utilitario.ControllerGenerico;
import br.com.cgcop.utilitarios.CriptografiaSenha;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author ari
 */
@Stateless
public class UsuarioController extends ControllerGenerico<Usuario, Long> implements Serializable {

    @Inject
    private UsuarioDAO dao;
    @Inject
    private ColaboradorDAO colaboradorDAO;
    @Inject
    private PessoaDAO pessoaDAO;

    @PostConstruct
    @Override
    protected void inicializaDAO() {
        setDAO(dao);
    }

    @Override
    public void salvar(Usuario u) throws Exception {
        u.setSenha("opovo");
        dao.atualizar(u);
    }

    public void ativarOuDesativar(Usuario usuario) throws Exception {
        if (usuario.isAtivo()) {
            usuario.setAtivo(false);
        } else {
            usuario.setAtivo(true);
        }
        dao.atualizar(usuario);
    }

    public void alterarSenha(Usuario usuario, String senhaAtual, String novaSenha, String confirmaSenha) throws Exception {
        senhaAtual = CriptografiaSenha.criptografarSenha(senhaAtual);
        if (usuario.getSenha().equals(senhaAtual)) {
            if (novaSenha.equals(confirmaSenha)) {
                usuario.setSenha(novaSenha);
                dao.atualizar(usuario);
            }
        }
    }

    public Usuario usuarioLogin(String usr) {
        return dao.usuarioLogin(usr);
    }

    public void criarUsuarioAdministrado() throws Exception {
        Pessoa pessoa = pessoaDAO.buscarPorNome("Administrador do Sistema");
        if (pessoa.getId() == null) {
            pessoa.setDescricao("Administrador do Sistema");
            pessoa.setNome("Administrador do Sistema");
            pessoa.setTipoPessoa(TipoPessoa.OUTRO);
            pessoa = pessoaDAO.atualizarGerenciar(pessoa);
            System.out.println("-------------------------------------------Cargo Admin incluido---------------------------------------");
        }

        Colaborador col = colaboradorDAO.buscarPorNome("Administrador do Sistema");
        if (col.getId() == null) {
            col.setAtivo(false);
            col.setPessoa(pessoa);
            col.setCpf("11111111111111");
            col.setEmail("adm@ss.com");
            col.setNome("Administrador do Sistema");
            col = colaboradorDAO.atualizarGerenciar(col);
            System.out.println("-------------------------------------------Colaborador Admin incluido---------------------------------------");
        }

        Usuario usuario = dao.usuarioLogin("adm");
        if (usuario.getId() == null) {
            usuario.setColaborador(col);
            usuario.setAtivo(true);
            usuario.setLogin("adm");
            usuario.setSenha("1234");
            System.out.println("-------------------------------------------Usuário  Admin incluido---------------------------------------");
            dao.atualizar(usuario);
        }
    }
}
