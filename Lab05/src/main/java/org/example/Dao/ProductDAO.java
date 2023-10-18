package org.example.Dao;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.example.Models.*;
import org.example.Repository.*;
import org.example.Utils.*;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;
public class ProductDAO implements ProductRepository {
    private static final Session session;
    static{
        session = HibernateUtil.getFactory().openSession();
    }
    @Override
    public boolean add(Product product) {

        try{
            session.beginTransaction();
            session.save(product);
            session.getTransaction().commit();
            return true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean delete(Product product) {
        try{
            session.beginTransaction();
            session.delete(product);
            session.getTransaction().commit();
            return true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Product getById(Long id) {
        Transaction transaction = session.beginTransaction();
        try{
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Product> cr = builder.createQuery(Product.class);
            Root<Product> root = cr.from(Product.class);
            Predicate predicate = builder.equal(root.get("id"), id);
            cr.select(root).where(predicate);
            Query<Product> query = session.createQuery(cr);
            Product foundProduct = query.getSingleResult();
            transaction.commit();
            return foundProduct;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean update(Product product) {
        try{
            Product foundProduct = getById(product.getId());
            Transaction transaction = session.beginTransaction();
            if(foundProduct != null){
                foundProduct.setName(product.getName());
                foundProduct.setPrice(product.getPrice());
                session.update(foundProduct);
                transaction.commit();
                return true;
            }
            return false;
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<Product> getAllProduct() {
        Transaction transaction = session.beginTransaction();
        try{
            List<Product> products = new ArrayList<>();
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Product> cr = builder.createQuery(Product.class);
            Root<Product> root = cr.from(Product.class);
            cr.select(root);
            Query<Product> query = session.createQuery(cr);
            products = query.getResultList();
            transaction.commit();
            return products;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}