package br.gov.sp.fatec.lab4.dao;

import javax.persistence.EntityManager;
import javax.persistence.RollbackException;
import javax.persistence.TypedQuery;

import br.gov.sp.fatec.lab4.entities.Cliente;
import br.gov.sp.fatec.lab4.entities.ClientePF;
import br.gov.sp.fatec.lab4.entities.ClientePJ;

public class ClienteDao{
    private EntityManager manager;

    public ClienteDao() {
        manager = PersistenceManager
                .getInstance().getEntityManager();
    }
    
    public ClienteDao(EntityManager manager) {
        this.manager = manager;
    }
    public Cliente buscarCliente(Long id){
        return manager.find(Cliente.class, id);
    }    
    public ClientePF buscarPF(Long cpf) {
        return manager.find(ClientePF.class, cpf);
    }
    public ClientePJ buscarPJ(Long cnpj) {
        return manager.find(ClientePJ.class, cnpj);
    }

    public void salvar(Cliente cliente) throws RollbackException {
        try {
            manager.getTransaction().begin();
            salvarSemCommit(cliente);
            manager.flush();
            manager.getTransaction().commit();
        }
        catch(RollbackException e) {
            manager.getTransaction().rollback();
            throw e;
        }
    }
    
    public void salvarSemCommit(Cliente cliente) {
        if(cliente.getId() == null) {
            manager.persist(cliente);
        }
        else {
            manager.merge(cliente);
        }
    }


     public void excluir(Long cpf) throws RollbackException {
        Cliente cliente = manager.find(Cliente.class, cpf);
        try {
            manager.getTransaction().begin();
            manager.remove(cliente);
            manager.getTransaction().commit();
        }
        catch(RollbackException e) {
            manager.getTransaction().rollback();
            throw e;
        }
    }

}