package br.gov.sp.fatec.lab4.dao;

import javax.persistence.EntityManager;
import javax.persistence.RollbackException;

import br.gov.sp.fatec.lab4.entities.Pagamento;
import br.gov.sp.fatec.lab4.entities.PagamentoCartao;
import br.gov.sp.fatec.lab4.entities.PagamentoDinheiro;

public class PagamentoDao {
    private EntityManager manager;

    public PagamentoDao() {
        manager = PersistenceManager
                .getInstance().getEntityManager();
    }
    
    public PagamentoDao(EntityManager manager) {
        this.manager = manager;
    }
        
    public PagamentoCartao buscarParcelas(Long ped_id) {
         String consulta = "select p from Pagamento p where ped_id = :ped_id";
        TypedQuery<Pagamento> query = manager.createQuery(consulta, Pagamento.class);
        query.setParameter("ped_id", ped_id);
        return query.getSingleResult();
        return manager.find(PagamentoCartao.class, ped_id);
    }

   public PagamentoDinheiro buscarDesconto(Long ped_id) {
        return manager.find(PagamentoDinheiro.class, ped_id);
    }


    public void pagar(Pagamento pagamento) throws RollbackException {
        try {
            manager.getTransaction().begin();
            manager.flush();
            manager.getTransaction().commit();
        }
        catch(RollbackException e) {
            manager.getTransaction().rollback();
            throw e;
        }
    }
}