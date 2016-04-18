package DataAccess.DAO;

import DataAccess.Entity.Areaofinterest;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author Edwin
 */
public class InterestAreaDAO {

    public EntityManagerFactory emf1 = Persistence.createEntityManagerFactory("TalentoHumanoPU");

    public Areaofinterest persist(Areaofinterest area) {

        EntityManager em = emf1.createEntityManager();
        em.getTransaction().begin();
        try {
            em.persist(area);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
                em.close();
            }
            return null;
        }
        em.close();
        return area;
    }

    public List<Areaofinterest> getAllAreasOfInterest() {

        EntityManager em = emf1.createEntityManager();
        List<Areaofinterest> areaObject = null;
        Query q = em.createNamedQuery("Areaofinterest.findAll");
        try {
            areaObject = q.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
            return areaObject;
        }
    }

}
