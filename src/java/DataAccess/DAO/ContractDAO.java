/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataAccess.DAO;

import DataAccess.Entity.Contract;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Alejandro
 */
public class ContractDAO {

    public EntityManagerFactory emf1 = Persistence.createEntityManagerFactory("TalentoHumanoPU");

    public Contract persist(Contract contract) {

        EntityManager em = emf1.createEntityManager();
        em.getTransaction().begin();
        try {
            em.persist(contract);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            em.close();
            return null;
        }
        em.close();
        return contract;
    }
}
