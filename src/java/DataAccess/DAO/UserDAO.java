/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataAccess.DAO;

import DataAccess.Entity.User;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author Alejandro
 */
public class UserDAO {
    public EntityManagerFactory emf1 = Persistence.createEntityManagerFactory("TalentoHumanoPU");
    
    public User persist(User user) {
        
        EntityManager em = emf1.createEntityManager();
        em.getTransaction().begin();
        
        try {
            em.persist(user);
            em.getTransaction().commit();
        } catch(Exception e) {
            em.getTransaction().rollback();
        } finally {
            em.close();
            return user;  
        }
    }
    
    public User searchByUsername(String username) {
        
        EntityManager em = emf1.createEntityManager();
        User userObject = null;
        Query q = em.createNamedQuery("User.findByUsername");
        q.setParameter("username", username);
        
        try {
            userObject = (User) q.getSingleResult();
        } catch (Exception e) {
            //e.printStackTrace();
            System.out.println("El usuario no existe");
        } finally {
            em.close();
            return userObject;
        }
    }
}