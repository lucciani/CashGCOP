/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cgcop.utilitario;

import br.com.cgcop.seguranca.controller.PermissaoController;
import br.com.cgcop.seguranca.controller.UsuarioController;
import br.com.cgcop.seguranca.managedbean.UsuarioMB;
import br.com.cgcop.seguranca.modelo.Permissao;
import br.com.cgcop.seguranca.modelo.Usuario;
import br.com.cgcop.utilitario.mensagens.MensagensUtil;
import br.com.cgcop.utilitarios.CriptografiaSenha;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.inject.Inject;
import javax.inject.Named;
import org.primefaces.context.RequestContext;

/**
 *
 * @author ari
 */
@Named
@SessionScoped
public class UtilitarioNavegacaoMB implements Serializable {

    @Inject
    private UsuarioController usuarioController;
    @Inject
    private PermissaoController permissaoController;
    private Usuario usuarioLogado;
    private List<Permissao> listaDePermissaoDoUsuario;
    private final Map<String, Permissao> menu;

    private String senhaAtual;
    private String novaSenha;
    private String confirmaSenha;

    public UtilitarioNavegacaoMB() {
        menu = new HashMap<>();
    }

    @PostConstruct
    public void init() {
        try {
            usuarioLogado = usuarioController.usuarioLogin(getContexto().getRemoteUser());
            if (usuarioLogado.getId() == null) {
                logout();
            } else {
                listaDePermissaoDoUsuario = permissaoController.consultarTodos("id", "usuario.colaborador.nome", getUsuarioLogado().getNomeDoColaborador());
                popularMenu();
            }
        } catch (Exception ex) {
            Logger.getLogger(UtilitarioNavegacaoMB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void popularMenu() {
        for (Permissao p : listaDePermissaoDoUsuario) {
            menu.put(p.getTarefa().getDescricao(), p);
        }
    }

    public boolean permissaoExiste(String tarNome) {
        if (menu.containsKey(tarNome)) {
            return true;
        } else {
            return false;
        }
    }




    public boolean existePermissaoModuloAdministrativo() {
        for (Permissao p : listaDePermissaoDoUsuario) {
            if (p.getTarefa().getModulo().getNome().equals("ADMINISTRATIVO")) {
                return true;
            }
        }
        return false;
    }
    public boolean existePermissaoModuloSolicitacao() {
        for (Permissao p : listaDePermissaoDoUsuario) {
            if (p.getTarefa().getModulo().getNome().equals("SOLICITAÇÕES")) {
                return true;
            }
        }
        return false;
    }
    public boolean existePermissaoModuloSeguranca() {
        for (Permissao p : listaDePermissaoDoUsuario) {
            if (p.getTarefa().getModulo().getNome().equals("SEGURANÇA")) {
                return true;
            }
        }
        return false;
    }

    /**
     *
     * @param tarNome
     * @return
     */
    public boolean permissaoIncluir(String tarNome) {
        if (menu.containsKey(tarNome)) {
            return menu.get(tarNome).isIncluir();
        } else {
            return false;
        }
    }

    public boolean permissaoConsultar(String tarNome) {
        if (menu.containsKey(tarNome)) {
            return menu.get(tarNome).isConsultar();
        } else {
            return false;
        }
    }

    public boolean permissaoEditar(String tarNome) {
        if (menu.containsKey(tarNome)) {
            return menu.get(tarNome).isEditar();
        } else {
            return false;
        }
    }

    public boolean permissaoExcluir(String tarNome) {
        if (menu.containsKey(tarNome)) {
            return menu.get(tarNome).isExcluir();
        } else {
            return false;
        }
    }

    public void alterarSenha() {
        try {
            usuarioController.alterarSenha(usuarioLogado, senhaAtual, novaSenha, confirmaSenha);
            novaSenha = "";
            confirmaSenha = "";
            senhaAtual = "";
            MensagensUtil.enviarMessageParamentroInfo(MensagensUtil.REGISTRO_ATUALIZADO, usuarioLogado.getNomeDoColaborador());
        } catch (Exception ex) {
            MensagensUtil.enviarMessageErro(MensagensUtil.REGISTRO_FALHA);
            Logger.getLogger(UsuarioMB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void validateSenhaAtual(FacesContext context, UIComponent component, Object value) throws ValidatorException {

        senhaAtual = (String) value;
        if (!CriptografiaSenha.criptografarSenha(senhaAtual).equals(usuarioLogado.getSenha())) {
            senhaAtual = "";
            RequestContext.getCurrentInstance().update("@(.ui-password)");
            RequestContext.getCurrentInstance().execute("PF('sen').focus();");
            MensagensUtil.enviarMessageWarn(MensagensUtil.SENHA_INVALIDA);
        }

    }

    public String logout() {
        return getContexto().getRequestContextPath() + "/j_spring_security_logout";
    }

    public static ExternalContext getContexto() {
        FacesContext context = FacesContext.getCurrentInstance();
        ExternalContext external = context.getExternalContext();
        return external;
    }

    public Usuario getUsuarioLogado() {
        return usuarioLogado;
    }

    public String getSenhaAtual() {
        return senhaAtual;
    }

    public void setSenhaAtual(String senhaAtual) {
        this.senhaAtual = senhaAtual;
    }

    public String getNovaSenha() {
        return novaSenha;
    }

    public void setNovaSenha(String novaSenha) {
        this.novaSenha = novaSenha;
    }

    public String getConfirmaSenha() {
        return confirmaSenha;
    }

    public void setConfirmaSenha(String confirmaSenha) {
        this.confirmaSenha = confirmaSenha;
    }

}
