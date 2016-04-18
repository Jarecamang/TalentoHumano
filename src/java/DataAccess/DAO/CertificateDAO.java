/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataAccess.DAO;

import DataAccess.Entity.Certificate;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author Alejandro
 */
public class CertificateDAO {

    public EntityManagerFactory emf1 = Persistence.createEntityManagerFactory("TalentoHumanoPU");

    public Certificate persist(Certificate certificate) {

        EntityManager em = emf1.createEntityManager();
        em.getTransaction().begin();

        try {
            em.persist(certificate);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
                em.close();
            }
            return null;
        }
        em.close();
        return certificate;
    }

    public void editAproved(Certificate certificate) {
        Certificate certificateNew;
        EntityManager em = emf1.createEntityManager();
        em.getTransaction().begin();
        try {
            certificateNew = em.merge(em.find(Certificate.class, certificate.getPkID()));
            certificateNew.setAproved(true);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }

    public List<Certificate> searchUnaproved() {

        EntityManager em = emf1.createEntityManager();
        List<Certificate> certificateObject = null;
        Query q = em.createNamedQuery("Certificate.findByAproved");
        q.setParameter("aproved", false);

        try {
            certificateObject = q.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("No hay certificados");
        } finally {
            em.close();
            return certificateObject;
        }
    }

    public List<Certificate> searchUserAproved() {

        EntityManager em = emf1.createEntityManager();
        List<Certificate> certificateObject = null;
        Query q = em.createNamedQuery("Certificate.findByAproved");
        q.setParameter("aproved", true);

        try {
            certificateObject = q.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("No hay certificados");
        } finally {
            em.close();
            return certificateObject;
        }
    }

}
