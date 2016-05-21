/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataAccess.DAO;

import DataAccess.Entity.Certifications;
import java.io.Serializable;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Edwin
 */
@Stateless
public class CertificationDAO implements Serializable {

    @PersistenceContext(unitName = "TalentoHumanoPU")
    private EntityManager em;

    public Certifications persist(Certifications certification) {
        
        try {
            em.persist(certification);
        return certification;
        } catch (Exception e) {
            return null;
        }
    }

}
