package data;

import business.Subscriber;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;


public class SubscriberDB {

   
   public static Subscriber selectByEmail(String email) {
      EntityManager em = DBUtil.getEmFactory().createEntityManager();
      String queryString = "SELECT s FROM Subscriber s "
              + "WHERE s.email = :email";
      TypedQuery<Subscriber> query = em.createQuery(queryString, Subscriber.class);
      query.setParameter("email", email);

      Subscriber subscriber = null;
      try {
         subscriber = query.getSingleResult();
      } catch (Exception e) {
         System.err.println(e);
      } finally {
         em.close();
      }

      return subscriber;
   }

   
   public static void insert(Subscriber subscriber) {
      EntityManager em = DBUtil.getEmFactory().createEntityManager();
      EntityTransaction transaction = em.getTransaction();

      try {
         transaction.begin();
         em.persist(subscriber);
         transaction.commit();
      } catch (Exception e) {
         System.out.println(e);
         transaction.rollback();
      } finally {
         em.close();
      }
   }

   
   public static void update(Subscriber subscriber) {
      EntityManager em = DBUtil.getEmFactory().createEntityManager();
      EntityTransaction transaction = em.getTransaction();

      try {
         transaction.begin();
         em.merge(subscriber);
         transaction.commit();
      } catch (Exception e) {
         System.out.println(e);
         transaction.rollback();
      } finally {
         em.close();
      }
   }

   
   public static boolean emailExists(String email) {
      return selectByEmail(email) != null;
   }
}
