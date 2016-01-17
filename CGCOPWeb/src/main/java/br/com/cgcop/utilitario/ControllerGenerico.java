/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cgcop.utilitario;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author ari
 * @param <T>
 * @param <PK>
 */
public abstract class ControllerGenerico<T, PK extends Serializable>  implements Serializable {
     private DAOGenerico dao;

    /**
     *
     */
    protected abstract void inicializaDAO();

    /**
     *
     * @param dao
     */
    protected void setDAO(DAOGenerico dao) {
        this.dao = dao;
    }

    /**
     *
     * @return
     */
    protected DAOGenerico getDAO() {
        return dao;
    }

    /**
     *
     * @param t
     * @throws Exception
     */
    public void salvar(T t) throws Exception {
        dao.salvar(t);
    }

    /**
     *
     * @param t
     * @throws Exception
     */
    public void atualizar(T t) throws Exception {
        dao.atualizar(t);
    }

    /**
     *
     * @param t
     * @throws Exception
     */
    public void salvarouAtualizar(T t) throws Exception {
       
        dao.atualizar(t);
    }

    /**
     *
     * @param t
     * @throws Exception
     */
    public void excluir(T t) throws Exception {
        dao.excluir(t);
    }

    /**
     *
     * @param id
     * @return
     * @throws Exception
     */
    public T carregar(PK id) throws Exception {
        return (T) dao.carregar(id);
    }

    /**
     *
     * @param id
     * @return
     * @throws Exception
     */
    public T gerenciar(PK id) throws Exception {
        return (T) dao.gerenciar(id);
    }

    /**
     *
     * @param ordem
     * @param campo
     * @param valor
     * @return
     * @throws Exception
     */
    public List<T> consultarTodos(String ordem, String campo, String valor) throws Exception {
        return dao.consultarTodos(ordem, campo, valor);
    }

    /**
     *
     * @param ordem
     * @return
     * @throws Exception
     */
    public List<T> consultarTodosOrdenadorPor(String ordem) throws Exception {
        return dao.consultarTodosOrdenadosPor(ordem);
    }
    
    /**
     * 
     * @param campo
     * @param valor
     * @return
     * @throws Exception 
     */
     public List<T> consultarLike(String campo,String valor) throws Exception {
        return dao.consultarLike(campo, valor);
    }
     
     public List<T> consultarAtivo(String campo,String valor, String ativo) throws Exception {
        return dao.consultarAtivo(campo, valor,ativo);
    }

   
}
