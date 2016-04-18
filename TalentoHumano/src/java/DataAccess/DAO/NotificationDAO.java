/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataAccess.DAO;

import DataAccess.Entity.Notifications;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author Edwin
 */
public class NotificationDAO {

    public EntityManagerFactory emf1 = Persistence.createEntityManagerFactory("TalentoHumanoPU");

    public Notifications persist(Notifications noti) {

        EntityManager em = emf1.createEntityManager();
        em.getTransaction().begin();
        try {
            em.persist(noti);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
                em.close();
            }
            return null;
        }
        em.close();
        return noti;
    }

    public List<Notifications> searchAll() {

        EntityManager em = emf1.createEntityManager();
        List<Notifications> notiObject = null;
        Query q = em.createNamedQuery("Notifications.findAll");

        try {
            notiObject = q.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
            return notiObject;
        }
    }

}
