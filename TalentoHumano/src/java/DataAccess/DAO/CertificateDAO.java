/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataAccess.DAO;

import DataAccess.Entity.Certificate;
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
public class CertificateDAO {

    @PersistenceContext(unitName = "TalentoHumanoPU")
    private EntityManager em;

    public Certificate persist(Certificate certificate) {

        try {
            em.persist(certificate);
            return certificate;
        } catch (Exception e) {
            return null;
        }

    }

    public Certificate editAproved(Certificate certificate) {
        Certificate dbCertificate = this.searchById(certificate.getPkID());
        dbCertificate.setAproved(true);
        return dbCertificate;
    }

    public List<Certificate> searchUnaproved() {

        List<Certificate> certificateObject = null;
        Query q = em.createNamedQuery("Certificate.findByAproved");
        q.setParameter("aproved", false);

        try {
            certificateObject = q.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("No hay certificados");
        } finally {
            return certificateObject;
        }
    }

    public List<Certificate> searchUserAproved() {

        List<Certificate> certificateObject = null;
        Query q = em.createNamedQuery("Certificate.findByAproved");
        q.setParameter("aproved", true);

        try {
            certificateObject = q.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("No hay certificados");
        } finally {
            return certificateObject;
        }
    }
    
    public Certificate searchById(Integer id) {
        
        Certificate certificateObject = null;
        Query q = em.createNamedQuery("Certificate.findByPkID");
        q.setParameter("pkID", id);

        try {
            certificateObject = (Certificate) q.getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("No hay certificados");
        } finally {
            return certificateObject;
        }
    }

}
