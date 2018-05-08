package data;

import business.Download;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;


public class DownloadDB {

   
   public static void insert(Download download) {
      EntityManager em = DBUtil.getEmFactory().createEntityManager();
      EntityTransaction transaction = em.getTransaction();

      try {
         transaction.begin();
         em.persist(download);
         transaction.commit();
      } catch (Exception e) {
         System.err.println(e);
         transaction.rollback();
      } finally {
         em.close();
      }
   }

   public static List<Download> selectDownloads() {
      EntityManager em = DBUtil.getEmFactory().createEntityManager();
      String queryString = "SELECT d FROM Download d";
      Query query = em.createQuery(queryString);

      List<Download> downloads = null;

      try {
         downloads = query.getResultList();
      } catch (Exception e) {
         System.err.println(e);
      } finally {
         em.close();
      }
      
      return downloads;
   }

}
