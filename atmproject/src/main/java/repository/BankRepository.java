package repository;

import model.Bank;
import model.Card;
import org.hibernate.Session;
import org.hibernate.query.Query;
import util.HibernateUtil;

public class BankRepository {

    public static Bank getBank(Integer id) {
        Session session = HibernateUtil.getSessionFactory().openSession();

        Query query = session.createQuery("SELECT b FROM Bank b INNER JOIN b.cards c " +
                " WHERE c.bank.id = :id");

        query.setParameter("id", id);

        Bank bank = (Bank) query.getSingleResult();

        session.close();

        return bank;
    }

    public static Card getCard(Integer id) {
        Session session = HibernateUtil.getSessionFactory().openSession();

        Query query = session.createQuery("SELECT c FROM Card c INNER JOIN c.bank b " +
                " WHERE b.id = :id");

        query.setParameter("id", id);

        Card card = (Card) query.getSingleResult();

        session.close();

        return card;
    }
}
