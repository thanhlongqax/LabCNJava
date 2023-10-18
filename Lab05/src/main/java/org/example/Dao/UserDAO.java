package org.example.Dao;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.example.Models.*;
import org.example.Repository.*;
import org.example.Utils.*;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.*;
public class UserDAO implements UserRepository {
    private static final Session session;
    static {
        session = HibernateUtil.getFactory().openSession();
    }
    public boolean add(User user) {
        try {
            if (!session.getTransaction().isActive()) {
                session.beginTransaction();
            }
            session.save(user);
            session.getTransaction().commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


    public User findByEmail(String email) {
        try {
            session.beginTransaction();
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<User> cr = cb.createQuery(User.class);
            Root<User> root = cr.from(User.class);
            Predicate predicate = cb.equal(root.get("email"), email);
            cr.select(root).where(predicate);
            Query<User> query = session.createQuery(cr);

            List<User> users = query.getResultList();
            session.getTransaction().commit();

            if (!users.isEmpty()) {
                return users.get(0); // Trả về người dùng đầu tiên nếu có kết quả
            } else {
                return null; // Trả về null nếu không tìm thấy người dùng
            }
        } catch (Exception e) {
            return null;
        }
    }
    public User findByUser(String userfullname) {
        try {
            session.beginTransaction();
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<User> cr = cb.createQuery(User.class);
            Root<User> root = cr.from(User.class);
            Predicate predicate = cb.equal(root.get("userfullname"), userfullname);
            cr.select(root).where(predicate);
            Query<User> query = session.createQuery(cr);

            List<User> users = query.getResultList();
            session.getTransaction().commit();

            if (!users.isEmpty()) {
                return users.get(0); // Trả về người dùng đầu tiên nếu có kết quả
            } else {
                return null; // Trả về null nếu không tìm thấy người dùng
            }
        } catch (Exception e) {
            return null;
        }
    }

}

