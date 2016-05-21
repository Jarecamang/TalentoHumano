/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataAccess.DAO;

import DataAccess.Entity.Position;
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
public class PositionDAO implements Serializable {

    @PersistenceContext(unitName = "TalentoHumanoPU")
    private EntityManager em;

    
    public Position persist(Position position) {
        //em = emf.createEntityManager();
        try {
            em.persist(position);
            return position;
        } catch (Exception e) {
            return null;
        }
    }

    public Position searchByName(String name) {

        Position positionObject = null;
        Query q = em.createNamedQuery("Position.findByName");
        q.setParameter("name", name);

        try {
            positionObject = (Position) q.getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("El usuario no existe");
        } finally {
            return positionObject;
        }
    }

    public Position searchByID(int ID) {

        Position positionObject = null;
        Query q = em.createNamedQuery("Position.findByPkID");
        q.setParameter("pkID", ID);

        try {
            positionObject = (Position) q.getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("El usuario no existe");
        } finally {
            return positionObject;
        }
    }

    public List<Position> searchAll() {

        List<Position> positionObject = null;
        Query q = em.createNamedQuery("Position.findAll");

        try {
            positionObject = q.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("El usuario no existe");
        } finally {
            return positionObject;
        }
    }

}
