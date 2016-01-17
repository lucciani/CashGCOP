/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cgcop.utilitario;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author ari
 * @param <T>
 * @param <PK>
 */
public abstract class DAOGenerico<T, PK extends Serializable> implements Serializable {

    @PersistenceContext(unitName = "cgcop_pu")
    private EntityManager em;
    private final Class<T> entityClass;

    protected EntityManager getEm() {
        return em;
    }

    public DAOGenerico(Class classe) {
        this.entityClass = classe;
    }

    public void salvar(T t) throws Exception {
        em.persist(t);
    }

    public void excluir(T t) throws Exception {
        em.remove(t);
    }

    public void atualizar(T t) throws Exception {
        em.merge(t);
    }

    public T atualizarGerenciar(T t) {
        return em.merge(t);
    }

    public T carregar(PK id) throws Exception {
        return (T) em.find(getEntityClass(), id);
    }

    public T gerenciar(PK id) throws Exception {
        return (T) em.getReference(getEntityClass(), id);
    }

    public List<T> consultarTodosOrdenadosPor(String nomeCampo) throws Exception {
        Query q = em.createQuery("SELECT a FROM " + getEntityClass().getName() + " a order by a.".concat(nomeCampo));
        return q.getResultList().isEmpty() ? new ArrayList<T>() : q.getResultList();
    }

    public List<T> consultarLike(String nomeCampo, String valor) throws Exception {
        return em.createQuery("FROM " + getEntityClass().getName() + " where UPPER(" + nomeCampo + ") like :vl")
                .setParameter("vl", "%" + valor.toUpperCase() + "%").getResultList();
    }

    public List<T> consultarAtivo(String nomeCampo, String valor, String ativo) throws Exception {
        return em.createQuery("FROM " + getEntityClass().getName() + " where UPPER(" + nomeCampo + ") like :vl and " + ativo)
                .setParameter("vl", "%" + valor.toUpperCase() + "%").getResultList();
    }

    public List<T> consultarTodos(String ordem, String campo, String valor) throws Exception {
        return em.createQuery("SELECT a FROM " + getEntityClass().getName() + " a WHERE a." + campo + " like :valor order by a." + ordem)
                .setParameter("valor", valor + "%").getResultList();
    }

    public List<T> pesquisaAutoComplete(String ordem, String campo, String sugest, int numeroResultados) throws Exception {
        return getEm().createQuery("SELECT t FROM " + getEntityClass().getName() + " t WHERE t." + campo + " like :sugest ORDER BY t." + campo)
                .setParameter("sugest", sugest + "%").setMaxResults(numeroResultados).getResultList();
    }

    protected Class getEntityClass() {
        return entityClass;
    }

//    public List<Permissao> consultarPermissoes(Usuario usuario, Modulo modulo, Tarefa tarefa) {
//        String sql = "SELECT p FROM Permissao p WHERE p.usuario =:u";
//        if (tarefa != null) {
//            sql += " AND p.tarefa =:t ";
//        }
//        if (modulo != null) {
//            sql += " AND p.tarefa.modulo =:m ";
//        }
//        Query q = getEm().createQuery(sql);
//        q.setParameter("u", usuario);
//        if (tarefa != null) {
//            q.setParameter("t", tarefa);
//        }
//        if (modulo != null) {
//            q.setParameter("m", modulo);
//        }
//        return q.getResultList();
//    }
}
