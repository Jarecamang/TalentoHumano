/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataAccess.DAO;

import DataAccess.Entity.User;
import java.io.Serializable;
import java.util.List;
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
public class UserDAO implements Serializable {
    
    @PersistenceContext(unitName = "TalentoHumanoPU")
    private EntityManager em;

    public User persist(User user) {
        //em = emf.createEntityManager();
        try {
            em.persist(user);
            return user;
        } catch (Exception e) {
            return null;
        }
    }
    
    public User searchByUsername(String username) {
        User user = null;
        Query query = em.createNamedQuery("User.findByUsername");
        query.setParameter("username", username);
        
        try {
            user = (User) query.getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("El usuario no existe");
        } finally {
            return user;
        }
    }  



    public User searchByDoccument(long doccument) {
        
        User userObject = null;
        Query q = em.createNamedQuery("User.findByIdentifyCard");
        q.setParameter("identifyCard", Long.toString(doccument));

        try {
            userObject = (User) q.getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("El usuario no existe");
        } finally {
            return userObject;
        }
    }

    public int getAmountOf(String training_level) {

        Query q = em.createNamedQuery("User.findByLevelTraining");
        q.setParameter("levelTraining", training_level);
        return q.getResultList().size();

    }

    public User searchByPkID(int idUser) {

        User userObject = null;
        Query q = em.createNamedQuery("User.findByPkID");
        q.setParameter("pkID", idUser);
        try {
            userObject = (User) q.getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("El usuario no existe");
        } finally {
            return userObject;
        }
    }

    public List<User> getAllUsers() {
        
        List<User> userObject = null;
        Query q = em.createNamedQuery("User.findAll");
        try {
            userObject = q.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("El usuario no existe");
        } finally {
            return userObject;
        }

    }
}
