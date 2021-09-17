package repository;

import model.Transaction;
import org.hibernate.Session;
import org.hibernate.query.Query;
import util.HibernateUtil;

public class TransactionRepository {

    public static Transaction getTransaction(Integer id) {
        Session session = HibernateUtil.getSessionFactory().openSession();

        Query query = session.createQuery("SELECT t FROM Transaction t inner join t.account a " +
                " WHERE a.id = :id ORDER BY t.time DESC");

        query.setParameter("id", id);
        query.setMaxResults(1);

        Transaction transaction = (Transaction) query.getSingleResult();

        session.close();

        return transaction;
    }

    public static void save(model.Transaction bill) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        org.hibernate.Transaction transaction = session.beginTransaction();

        session.save(bill);

        transaction.commit();
    }
}
