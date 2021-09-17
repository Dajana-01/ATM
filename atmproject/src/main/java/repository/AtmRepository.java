package repository;

import model.Atm;
import org.hibernate.Session;
import org.hibernate.query.Query;
import util.HibernateUtil;

import java.util.List;

public class AtmRepository {

    public static List<Atm> getAtm(Integer id) {
        Session session = HibernateUtil.getSessionFactory().openSession();

        Query query = session.createQuery("SELECT a FROM Atm a INNER JOIN a.bank b " +
                " WHERE b.id = :id");

        query.setParameter("id", id);

        List<Atm> atms = query.getResultList();
        session.close();

        return atms;
    }
}
