package DataAccess.DAO;

import DataAccess.Entity.Areaofinterest;
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
 * @author Edwin
 */
@Stateless
public class InterestAreaDAO implements Serializable {

    @PersistenceContext(unitName = "TalentoHumanoPU")
    private EntityManager em;

    public Areaofinterest persist(Areaofinterest area) {
        try{
            em.persist(area);
            return area;
        }catch(Exception e){
            return null;
        }
    }
    
    public Areaofinterest update(Areaofinterest area) {
        try {
            em.merge(area);
            return area;
        } catch (Exception e) {
            return null;
        }
    }

    public List<Areaofinterest> getAllAreasOfInterest() {

        List<Areaofinterest> areaObject = null;
        Query q = em.createNamedQuery("Areaofinterest.findAll");
        try {
            areaObject = q.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return areaObject;
        }
    }
    
    public Areaofinterest searchByName(String name) {
        Areaofinterest area = null;
        Query query = em.createNamedQuery("Areaofinterest.findByName");
        query.setParameter("name",name);
        try {
            area = (Areaofinterest)query.getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return area;
        }
    } 

}
