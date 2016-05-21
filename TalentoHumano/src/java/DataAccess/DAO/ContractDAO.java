/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataAccess.DAO;

import DataAccess.Entity.Contract;
import DataAccess.Entity.User;
import java.io.Serializable;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Alejandro
 */
@Stateless
public class ContractDAO implements Serializable {

    @PersistenceContext(unitName = "TalentoHumanoPU")
    private EntityManager em;

    public Contract persist(Contract contract) {
        //em = emf.createEntityManager();
        try {
            em.persist(contract);
            return contract;
        } catch (Exception e) {
            return null;
        }
    }

    public Contract edit(PositionDAO positionDAO, Contract contract, int contractPosition) {
        Contract dbContract = this.getById(contract.getPkID());
        dbContract.setSalary(contract.getSalary());
        dbContract.setType(contract.getType());
        dbContract.setEnddate(contract.getEnddate());
        dbContract.setStartDate(contract.getStartDate());
        dbContract.setHealthEnterprise(contract.getHealthEnterprise());
        dbContract.setStartHealthDate(contract.getStartHealthDate());
        dbContract.setPensionEnterprise(contract.getPensionEnterprise());
        dbContract.setStartPensionDate(contract.getStartPensionDate());
        dbContract.setFkuserID(contract.getFkuserID());
        dbContract.getPositionSet().add(positionDAO.searchByID(contractPosition));
        return dbContract;
    }

    public int getAmountOfSalariesEquals(double salary) {

        Query q = em.createNamedQuery("Contract.findBySalary");
        q.setParameter("salary", salary);
        return q.getResultList().size();

    }

    public int getAmountOfSalariesSmallerThan(double salary) {

        Query q = em.createNamedQuery("Contract.findBySalarySmallerThan");
        q.setParameter("salary", salary);
        return q.getResultList().size();

    }

    public int getAmountOfSalariesBiggerThan(double salary) {

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
        Contract cont = null;
        Query q = em.createNamedQuery("Contract.findByfkuserID");
        q.setParameter("fkuserID", id);

        try {
            cont = (Contract) q.getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("El usuario no tiene contrato");
        } finally {
            return cont;
        }
    }

    public Contract getById(Integer id) {
        Contract cont = null;
        Query q = em.createNamedQuery("Contract.findByPkID");
        q.setParameter("pkID", id);

        try {
            cont = (Contract) q.getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("No se encontr[o el contrato");
        } finally {
            return cont;
        }
    }
}
