/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataAccess.DAO;

import DataAccess.Entity.Contract;
import DataAccess.Entity.User;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

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
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
                em.close();
            }
            return null;
        }
        em.close();
        return contract;
    }
    
    public Contract edit(Contract contract, int contractPosition) {
        Contract contractNew = null;
        EntityManager em = emf1.createEntityManager();
        em.getTransaction().begin();
        try {
            contractNew = em.merge(em.find(Contract.class, contract.getPkID()));
            contractNew.setSalary(contract.getSalary());
            contractNew.setType(contract.getType());
            contractNew.setEnddate(contract.getEnddate());
            contractNew.setStartDate(contract.getStartDate());
            contractNew.setHealthEnterprise(contract.getHealthEnterprise());
            contractNew.setStartHealthDate(contract.getStartHealthDate());
            contractNew.setPensionEnterprise(contract.getPensionEnterprise());
            contractNew.setStartPensionDate(contract.getStartPensionDate());
            contractNew.setFkuserID(contract.getFkuserID());
            PositionDAO positionDAO = new PositionDAO();
            contractNew.getPositionSet().add(positionDAO.searchByID(contractPosition));
            //contractNew.setBalance(account.getBalance());
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            return null;
        } finally {
            em.close();
            return contractNew;
        }
    }

    public int getAmountOfSalariesEquals(double salary) {

        EntityManager em = emf1.createEntityManager();
        Query q = em.createNamedQuery("Contract.findBySalary");
        q.setParameter("salary", salary);
        return q.getResultList().size();

    }

    public int getAmountOfSalariesSmallerThan(double salary) {

        EntityManager em = emf1.createEntityManager();
        Query q = em.createNamedQuery("Contract.findBySalarySmallerThan");
        q.setParameter("salary", salary);
        return q.getResultList().size();

    }

    public int getAmountOfSalariesBiggerThan(double salary) {

        EntityManager em = emf1.createEntityManager();
        Query q = em.createNamedQuery("Contract.findBySalaryBiggerThan");
        q.setParameter("salary", salary);
        return q.getResultList().size();

    }

    public int getAmountOfSalariesBetween(double salarySmaller, double salaryBigger) {
        if (salarySmaller == 1000000) {
            return getAmountOfSalariesSmallerThan(salaryBigger) - getAmountOfSalariesSmallerThan(salarySmaller) + getAmountOfSalariesEquals(salarySmaller) + getAmountOfSalariesEquals(salaryBigger);
        } else {
            return getAmountOfSalariesSmallerThan(salaryBigger) - getAmountOfSalariesSmallerThan(salarySmaller) + getAmountOfSalariesEquals(salaryBigger);
        }
    }

    public Contract getUserContract(User id) {
        EntityManager em = emf1.createEntityManager();
        Query q = em.createNamedQuery("Contract.findByfkuserID");
        q.setParameter("fkuserID", id);
        Contract cont = (Contract) q.getSingleResult();
        return cont;
    }
}
