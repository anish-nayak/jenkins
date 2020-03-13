package caching;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;


import assignment2.Stock;

public class test {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		
		Configuration configuration =  new Configuration();
	      configuration.configure("hibernate6.cfg.xml");
	      SessionFactory sessionFactory = configuration.buildSessionFactory();
	      
	          Session session =sessionFactory.openSession();
		      //Product p=session.load(Product.class,3L);	
		      Product p=session.get(Product.class,3L);		      
		      System.out.println("Product  ="+p);
//		      session.evict(p);
//		     // p=
//		       p=session.get(Product.class,3L);		      
//		      System.out.println("Product<>  ="+p);
     	      session.close();
//		    //  session.evict(p);

		      sessionFactory.getCache().evictAllRegions();
		      
		      Session session1 =sessionFactory.openSession();
		      p=session1.get(Product.class,3L);		      
		      System.out.println("Product  ="+p);		
		      session1.close();
		      
		      
		      ////////////////////////////
		      /*Thread.sleep(10000);
		      Session session1 =sessionFactory.openSession();
				
			    Product p1=session1.load(Product.class,3L);
			    System.out.println("Product  id="+p);
			    p=session1.load(Product.class,3L);
			    session1.close();
		      
*/
sessionFactory.close();
	}

}
