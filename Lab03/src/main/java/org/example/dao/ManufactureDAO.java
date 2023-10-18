package org.example.dao;

import org.example.Repository;
import org.example.exception.InvalidOperationException;
import org.example.models.Manufacture;
import org.example.utils.HibernateUtil;
import org.hibernate.Session;
import javax.persistence.Query;
import java.util.List;

public class ManufactureDAO implements Repository<Manufacture, Integer> {
    private static final Session session;

    static {
        session = HibernateUtil.getFactory().openSession();
    }

    @Override
    public boolean add(Manufacture manufacture) {
        try {
            session.beginTransaction();
            session.save(manufacture);
            session.getTransaction().commit();
            System.out.println("Manufacture " + manufacture.getName() + " added");
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Manufacture get(Integer id) {
        try {
            session.beginTransaction();
            Query query = session.createQuery("from Manufacture where id = :id");
            query.setParameter("id", id);
            Manufacture manufacture = (Manufacture) query.getSingleResult();
            session.getTransaction().commit();
            return manufacture;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Manufacture> getAll() {
        try {
            session.beginTransaction();
            Query query = session.createQuery("from Manufacture");
            List<Manufacture> manufactures = query.getResultList();
            session.getTransaction().commit();
            return manufactures;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean remove(Manufacture manufacture) {
        try {
            session.beginTransaction();
            session.delete(manufacture);
            session.getTransaction().commit();
            System.out.println("Manufacture '" + manufacture.getName() + "' was deleted");
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
            Query query = session.createQuery("delete from Manufacture where id = :id");
            query.setParameter("id", id);
            query.executeUpdate();
            session.getTransaction().commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean update(Manufacture manufacture) {
        try {
            session.beginTransaction();
            session.update(manufacture);
            session.getTransaction().commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

//    A method to check whether all manufacturers have more than 100 employees
    public boolean checkManufacture() {
        try {
            session.beginTransaction();
            Query query = session.createQuery("from Manufacture where employee > 100");
            List<Manufacture> manufactures = query.getResultList();
            session.getTransaction().commit();
            return manufactures.size() == getAll().size();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

//    A method that returns the sum of all employees of the manufactures.
    public int sumEmployee() {
        try {
            session.beginTransaction();
            Query query = session.createQuery("select sum(employee) from Manufacture");
            int sum = Integer.parseInt(query.getSingleResult().toString());
            session.getTransaction().commit();
            return sum;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

//  A method that returns the last manufacturer in the list of manufacturers that meet
//the criteria: based in the US. If there is no producer that meets the above criteria,
//throw an InvalidOperationException
    public Manufacture getLastManufactureByCountry(String location) throws InvalidOperationException {
        try {
            session.beginTransaction();
            Query query = session.createQuery("from Manufacture where location like :location");
            query.setParameter("location", location);

//            get last element
            List<Manufacture> manufactures = query.getResultList();
            Manufacture manufacture = manufactures.get(manufactures.size() - 1);
            session.getTransaction().commit();
            return manufacture;
        } catch (Exception e) {
            throw new InvalidOperationException("There is no producer that meets the above criteria");
        }
    }

}
