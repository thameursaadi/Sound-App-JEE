package business;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Download implements Serializable {

   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
   private Long downloadId;

   @ManyToOne(fetch = FetchType.EAGER)
   private Customer customer;

   @Temporal(TemporalType.TIMESTAMP)
   private Date downloadDate;
   
   @ManyToOne(fetch = FetchType.EAGER)
   private Product product;

   public Download() {
      downloadDate = new Date();
   }

   public Long getDownloadId() {
      return downloadId;
   }

   public Customer getCustomer() {
      return customer;
   }

   public Date getDownloadDate() {
      return downloadDate;
   }

   public Product getProduct() {
      return product;
   }

   public void setDownloadId(Long downloadId) {
      this.downloadId = downloadId;
   }

   public void setCustomer(Customer customer) {
      this.customer = customer;
   }

   public void setDownloadDate(Date downloadDate) {
      this.downloadDate = downloadDate;
   }

   public void setProduct(Product product) {
      this.product = product;
   }

}
