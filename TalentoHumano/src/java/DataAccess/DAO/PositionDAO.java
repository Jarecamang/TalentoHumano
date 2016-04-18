/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataAccess.DAO;

import DataAccess.Entity.Position;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author Alejandro
 */
public class PositionDAO {

    public EntityManagerFactory emf1 = Persistence.createEntityManagerFactory("TalentoHumanoPU");

    public Position persist(Position position) {

        EntityManager em = emf1.createEntityManager();
        em.getTransaction().begin();

        try {
            em.persist(position);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
                em.close();
            }
            return null;
        }
        em.close();
        return position;
    }

    public Position searchByName(String name) {

        EntityManager em = emf1.createEntityManager();
        Position positionObject = null;
        Query q = em.createNamedQuery("Position.findByName");
        q.setParameter("name", name);

        try {
            positionObject = (Position) q.getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("El usuario no existe");
        } finally {
            em.close();
            return positionObject;
        }
    }

    public Position searchByID(int ID) {

        EntityManager em = emf1.createEntityManager();
        Position positionObject = null;
        Query q = em.createNamedQuery("Position.findByPkID");
        q.setParameter("pkID", ID);

        try {
            positionObject = (Position) q.getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("El usuario no existe");
        } finally {
            em.close();
            return positionObject;
        }
    }

    public List<Position> searchAll() {

        EntityManager em = emf1.createEntityManager();
        List<Position> positionObject = null;
        Query q = em.createNamedQuery("Position.findAll");

        try {
            positionObject = q.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("El usuario no existe");
        } finally {
            em.close();
            return positionObject;
        }
    }

}
