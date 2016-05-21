/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataAccess.DAO;

import DataAccess.Entity.Notifications;
import java.io.Serializable;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Edwin
 */
@Stateless
public class NotificationDAO implements Serializable {

    @PersistenceContext(unitName = "TalentoHumanoPU")
    private EntityManager em;

    public Notifications persist(Notifications noti) {
        try {
            em.persist(noti);
            return noti;
        } catch (Exception e) {
            return null;
        }

    }

    public List<Notifications> searchAll() {

        List<Notifications> notiObject = null;
        Query q = em.createNamedQuery("Notifications.findAll");

        try {
            notiObject = q.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return notiObject;
        }
    }

}
