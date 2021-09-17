package repository;

import model.Account;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import util.HibernateUtil;

import java.util.List;

public class AccountRepository {

    public static List<Account> getAccounts(Integer id) {
        Session session = HibernateUtil.getSessionFactory().openSession();

        Query query = session.createQuery("SELECT a FROM Account a inner join a.bank b " +
                " WHERE b.id = :id");

        query.setParameter("id", id);
        List<Account> accounts = query.getResultList();

        session.close();

        return accounts;
    }

    public static void update(Account account) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        session.update(account);

        transaction.commit();
    }
}
