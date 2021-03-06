/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufscar.dc.dsw.dao;

import br.ufscar.dc.dsw.pojo.Cliente;
import br.ufscar.dc.dsw.pojo.Locacao;
import br.ufscar.dc.dsw.pojo.Locadora;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

/**
 *
 * @author catit
 */
public class LocacaoDAO extends GenericDAO<Locacao> {

    @Override
    public Locacao get(Long id) {
        EntityManager em = this.getEntityManager();
        Locacao locacao = em.find(Locacao.class, id);
        em.close();
        return locacao;
    }

    @Override
    public List<Locacao> getAll() {
        EntityManager em = this.getEntityManager();
        Query q = em.createQuery("select l from Locacao l", Locacao.class);
        List<Locacao> locacoes = q.getResultList();
        em.close();
        return locacoes;
    }

    public List<Locacao> getAllCliente(Cliente cliente) {
        EntityManager em = this.getEntityManager();
        Query q = em.createQuery("select l from Locacao l where l.cliente = :cliente", Locacao.class)
                                .setParameter("cliente", cliente);
        List<Locacao> locacoes = q.getResultList();
        em.close();
        return locacoes;
    }
    
    public List<Locacao> getAllLocadora(Locadora locadora) {
        EntityManager em = this.getEntityManager();
        Query q = em.createQuery("select l from Locacao l where l.locadora = :locadora", Locacao.class)
                                .setParameter("locadora", locadora);
        List<Locacao> locacoes = q.getResultList();
        em.close();
        return locacoes;
    }
    
    
    @Override
    public void save(Locacao locacao) {
        EntityManager em = this.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        em.persist(locacao);
        tx.commit();
        em.close();
    }

    @Override
    public void update(Locacao locacao) {
        EntityManager em = this.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        em.merge(locacao);
        tx.commit();
        em.close();
    }

    @Override
    public void delete(Locacao locacao) {
        EntityManager em = this.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        locacao = em.getReference(Locacao.class, locacao.getId());
        tx.begin();
        em.remove(locacao);
        tx.commit();
    }    
}
