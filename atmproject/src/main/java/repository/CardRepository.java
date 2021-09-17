package repository;

import model.Card;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import util.HibernateUtil;

import java.util.List;

//Cdo klase do repository ( 6 ne) Ne nje klase te vecante si psh trasnactionMng do kemi funksionet .
public class CardRepository {

    public Card login(String cardNo, String pin) {
        Session session = HibernateUtil.getSessionFactory().openSession();

        Query query = session.createQuery("from Card c where c.cardNo = :cardNo and c.pin = :pin");

        query.setParameter("cardNo", cardNo);

        query.setParameter("pin", pin);

        Card card = null;

        List<Card> cards = query.getResultList();

        if (!cards.isEmpty()) {
            card = cards.get(0);
        }

        session.close();

        return card;
    }

    public static void update(Card card) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        session.update(card);

        transaction.commit();
    }
}
