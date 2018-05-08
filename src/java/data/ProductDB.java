package data;

import business.Product;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import javax.persistence.TypedQuery;


public class ProductDB {
   
   
   public static void insert(Product product) {
      EntityManager em = DBUtil.getEmFactory().createEntityManager();
      EntityTransaction transaction = em.getTransaction();
      
      try {
         transaction.begin();
         em.persist(product);
         transaction.commit();
      } catch (Exception e) {
         System.err.println(e);
         transaction.rollback();
      } finally {
         em.close();
      }
   }
   
   
   public static List<Product> selectProducts() {
      EntityManager em = DBUtil.getEmFactory().createEntityManager();
      String queryString = "SELECT p FROM Product p";
      Query query = em.createQuery(queryString);
      
      List<Product> products = null;
      
      try {
         products = query.getResultList();
      } catch (Exception e) {
         System.err.println(e);
      } finally {
         em.close();
      }
      
      return products;
   }
   
   
   public static Product selectProduct(String productCode) {
      EntityManager em = DBUtil.getEmFactory().createEntityManager();
      String queryString = "SELECT p FROM Product p "
                         + "WHERE p.code = :code";
      TypedQuery<Product> query = em.createQuery(queryString, Product.class);
      query.setParameter("code", productCode);
      
      Product product = null;
      try {
         product = query.getSingleResult();
      } catch (Exception e) {
         System.err.println(e);
      } finally {
         em.close();
      }
      
      return product;
   }
   
   
   public static Product selectNewestProduct() {
      EntityManager em = DBUtil.getEmFactory().createEntityManager();
      String queryString = "SELECT p FROM Product p "
                         + "ORDER BY p.productId";
      Query query = em.createQuery(queryString);
      
      Product product = null;
              
      try {
         List<Product> products = query.getResultList();
         product = products.get(products.size() - 1);
      } catch (Exception e) {
         System.err.println(e);
      } finally {
         em.close();
      }
      
      return product;
   }
}
