package org.example.dao;

import org.example.Repository;
import org.example.models.Phone;
import org.example.utils.HibernateUtil;
import org.hibernate.Session;

import javax.persistence.Query;
import java.util.List;

public class PhoneDAO implements Repository<Phone, Integer> {
//    create variable PRICE_LIMIT with type Long and value 50 million
    private static final Long PRICE_LIMIT = 50000000L;
    private static final Long DEFAULT_PRICE = 15000000L;
    private static final Session session;

    static {
        session = HibernateUtil.getFactory().openSession();
    }

    @Override
    public boolean add(Phone phone) {
        try {
            session.beginTransaction();
            session.save(phone);
            System.out.println("Phone " + phone.getName() + " added");
            session.getTransaction().commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Phone get(Integer id) {
        try {
            session.beginTransaction();
            Query query = session.createQuery("from Phone where id = :id");
            query.setParameter("id", id);
            Phone phone = (Phone) query.getSingleResult();
            session.getTransaction().commit();
            return phone;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Phone> getAll() {

        try {
            session.beginTransaction();
            Query query = session.createQuery("from Phone");
            List<Phone> phones = query.getResultList();
            session.getTransaction().commit();
            return phones;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean remove(Phone phone) {
        try {
            session.beginTransaction();
            session.remove(phone);
            session.getTransaction().commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean remove(Integer id) {
        try {
            session.beginTransaction();
            Query query = session.createQuery("from Phone where id = :id");
            query.setParameter("id", id);
            Phone phone = (Phone) query.getSingleResult();
            System.out.println(phone);
            session.remove(phone);
            session.getTransaction().commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean update(Phone phone) {
        try {
            session.beginTransaction();
            session.update(phone);
            session.getTransaction().commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public Phone getHighestPrice() {
        try {
            session.beginTransaction();
            Query query = session.createQuery("from Phone order by price desc");
            query.setMaxResults(1);
            Phone phone = (Phone) query.getSingleResult();
            session.getTransaction().commit();
            return phone;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Phone> getPhoneSorted() {
        try {
            session.beginTransaction();
            Query query = session.createQuery("from Phone order by country desc, price desc");
            List<Phone> phones = query.getResultList();
            session.getTransaction().commit();
            return phones;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean isGreaterThan(Phone phone) {
        return phone.getPrice() > PRICE_LIMIT;
    }

    public Phone getPhoneHasPinkColor() {
        try {
            session.beginTransaction();
            Query query = session.createQuery("from Phone where color like :color and price > :price");
            query.setParameter("color", "%pink%");
            query.setParameter("price", DEFAULT_PRICE);
            Phone phone = (Phone) query.getSingleResult();
            session.getTransaction().commit();
            return phone;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
