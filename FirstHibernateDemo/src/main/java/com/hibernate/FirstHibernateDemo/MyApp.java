package com.hibernate.FirstHibernateDemo;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

public class MyApp {
	
	public static void testGetVSLoad(SessionFactory sessionFactory)
	{
		Session session =sessionFactory.openSession();
		Transaction t=session.beginTransaction();
		t.begin();
		//load gives object not found exception
		//get gives null object
		try {
		  Product product=session.get(Product.class, 20L);
		  System.out.println("product: "+product);
			}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		  
	       t.commit();
	       session.close();
	  }
	
	public static void testSaveVSPersist(SessionFactory sessionFactory)
	{//save gives id of object
	 //persist doesnt give id of object returns void
		Session session =sessionFactory.openSession();
		Transaction t=session.beginTransaction();
		t.begin();
		  Product product=new Product("vA", 3000);
	      //Long productId=(Long)session.persist(product);
	     // System.out.println(session.persist(product));
	      t.commit();
	      session.close();
	 }
	
	public static void testUpdateVSMerge(SessionFactory sessionFactory)
	{		
		//merge() return a object while updating
		//update doesnt return a object while updating
			Session session =sessionFactory.openSession();
			Transaction t=session.beginTransaction();
			t.begin();
			Product product=session.load(Product.class, 3L);
			product.setName("asdfghgjhj");		  
			product.setPrice(500.0);
		    t.commit();
		    product.setName("anish1");
		    t=session.beginTransaction();
		    //Product p= (Product)session.merge(product);
		    session.update(product);
		    t.commit();
		    session.close(); 
	}
	
	
	
	
	public static void add(SessionFactory sessionFactory)
	{
		Session session =sessionFactory.openSession();
		Transaction t=session.beginTransaction();
		t.begin();
		  Product product=new Product("vA", 3000);
	      Long productId=(Long)session.save(product);
	      System.out.println("Product  id="+productId);
	      t.commit();
	      session.close();
	}
	
	public static void update(SessionFactory sessionFactory)
	{
		Session session =sessionFactory.openSession();
		Transaction t=session.beginTransaction();
		t.begin();
		  Product product=session.load(Product.class, 3L);
		  product.setName("asdfghgjhj");		  
		  product.setPrice(500.0);
	       t.commit();
	       session.close();
	}
	
	public static void delete(SessionFactory sessionFactory)
	{
		Session session =sessionFactory.openSession();
		try {

			Transaction transaction = session.beginTransaction();
		
			Object object = session.load(Product.class, 6L);
			session.delete(object);
			transaction.commit();
		
			} catch (Exception e)
			{
				e.printStackTrace();
			} finally 
			{
				session.close();
			}

	}
	
	
	public static void displayAll(SessionFactory sessionFactory)
	{
		Session session =sessionFactory.openSession();
		Query query=session.createQuery("from Product");
		List<Product> list= query.list();
		
		for(Product p:list)
		System.out.println(p);
		session.close();
		
	
		
	}
	
/*	public static void insertHQL(SessionFactory sessionFactory)
	{
		Session session =sessionFactory.openSession();
	/*String hql = "INSERT INTO Employee(firstName, lastName, salary)";
	Query query = session.createQuery(hql);
	int result = query.executeUpdate();
	System.out.println("Rows affected: " + result);
	*/
	
	/*int query = session.createSQLQuery("INSERT INTO product_master (name, cost, p_id) VALUES ('asdf', 152.0, 25)").executeUpdate();
	
	//query.setParameter("name", "aaa");
	//query.setParameter("price", 78945);
	//query.setParameter("id", 20);
	//System.out.println("value: "+query.executeUpdate());
	session.close();
	}
*/
	
	public static void totalcost(SessionFactory sessionfactory)
	{
		Session session = sessionfactory.openSession();
		Criteria cr = session.createCriteria(Product.class);
		cr.setProjection(Projections.sum("price"));
		System.out.println("Total Cost: "+cr.uniqueResult());
		session.close();
	}
	
	public static void ordercost(SessionFactory sessionFactory)
	{
		System.out.println("ORDER BY COST DESC");
		Session session = sessionFactory.openSession();
		Criteria cr = session.createCriteria(Product.class);
		cr.addOrder(Order.desc("price"));
		List <Product> list=cr.list();
		
		for(Product p:list)
			System.out.println(p);
		session.close();
	}
	
	public static void ordercostHQL(SessionFactory sessionFactory)
	{
		System.out.println("ORDER BY COST DESC HQL");
		Session session = sessionFactory.openSession();
		String q="FROM Product p order by p.price desc";
		Query query=session.createQuery(q);
		
		List <Product> list=query.list();
		
		for(Product p:list)
			System.out.println(p);
		session.close();
	}
	
	public static void costLT(SessionFactory sessionFactory)
	{
		System.out.println("COST < 2000 DESC");
		Session session = sessionFactory.openSession();
		Criteria cr = session.createCriteria(Product.class);
		cr.add(Restrictions.gt("price", 2000));
		cr.addOrder(Order.desc("price"));
		List <Product> list=cr.list();
		
		for(Product p:list)
			System.out.println(p);
		session.close();
	}
	
	
	public static void main(String[] args) throws Exception
	{ 
      Configuration configuration =  new Configuration();
      configuration.configure("hibernate.cfg.xml");
      SessionFactory sessionFactory = configuration.buildSessionFactory();            
      /*add(sessionFactory);
      add(sessionFactory);
      add(sessionFactory);
      add(sessionFactory);
      add(sessionFactory);
      add(sessionFactory);*/
      //displayAll(sessionFactory);

      //update(sessionFactory);
      //displayAll(sessionFactory);
      
      //delete(sessionFactory);
      //displayAll(sessionFactory);
     // testGetVSLoad(sessionFactory);
     // testSaveVSPersist(sessionFactory);
     // testUpdateVSMerge(sessionFactory);
      System.out.println("old records: ");
      displayAll(sessionFactory);
      System.out.println("new records: ");
      //insertHQL(sessionFactory);
      displayAll(sessionFactory);
      totalcost(sessionFactory);
      ordercost(sessionFactory);
      ordercostHQL(sessionFactory);
      costLT(sessionFactory);
      sessionFactory.close();
	}

}