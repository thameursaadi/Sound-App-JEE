package data;

import business.Invoice;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;


public class InvoiceDB {

   
   public static void insert(Invoice invoice) {
      EntityManager em = DBUtil.getEmFactory().createEntityManager();
      EntityTransaction transaction = em.getTransaction();

      try {
         transaction.begin();
         em.persist(invoice);
         transaction.commit();
      } catch (Exception e) {
         System.err.println(e);
         transaction.rollback();
      } finally {
         em.close();
      }
   }

   
   public static void update(Invoice invoice) {
      EntityManager em = DBUtil.getEmFactory().createEntityManager();
      EntityTransaction transaction = em.getTransaction();

      try {
         transaction.begin();
         em.merge(invoice);
         transaction.commit();
      } catch (Exception e) {
         System.out.println(e);
         transaction.rollback();
      } finally {
         em.close();
      }
   }

  
   public static List<Invoice> selectInvoices() {
      EntityManager em = DBUtil.getEmFactory().createEntityManager();
      String queryString = "SELECT i FROM Invoice i";
      Query query = em.createQuery(queryString);

      List<Invoice> invoices = null;

      try {
         invoices = query.getResultList();
      } catch (Exception e) {
         System.err.println(e);
      } finally {
         em.close();
      }

      return invoices;
   }

   
   public static Invoice select(Long invoiceNumber) {
      EntityManager em = DBUtil.getEmFactory().createEntityManager();
      Invoice invoice = null;

      try {
         invoice = em.find(Invoice.class, invoiceNumber);
      } finally {
         em.close();
      }

      return invoice;
   }
}
