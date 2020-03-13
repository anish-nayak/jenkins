package assignments;


import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.hibernate.FirstHibernateDemo.Product;


public class TestMain {
	public static void insert(SessionFactory sessionfactory)
	{
		 Session session =sessionfactory.openSession();
	      Transaction transcation1 =session.beginTransaction();
	      Enquiry enquiry=new Enquiry("what is my account balance?");
	      Customer customer=new Customer("anish",enquiry);
	      session.persist(customer);
	      transcation1.commit();
	      session.close();
	}
	public static void update(SessionFactory sessionfactory)
	{
		 Session session =sessionfactory.openSession();
	      Transaction transcation =session.beginTransaction();
	      transcation.begin();
	      Customer customer=session.load(Customer.class, 1L);
	      customer.setName("asdfgh");
	      customer.setEnquiry(new Enquiry("what is your city"));
	      
	      transcation.commit();
	      session.close();
	}
	
	public static void delete(SessionFactory sessionfactory)
	{
		Session session =sessionfactory.openSession();
		try {

			Transaction transaction = session.beginTransaction();
		
			Object object = session.load(Customer.class, 1L);
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
	
	
public static List<Customer>getAllCustomer(SessionFactory sessionfactory )
	{
		 Session session =sessionfactory.openSession();
		Query query=session.createQuery("From Customer");
		 List<Customer> productList=query.list();
	      session.close();
	      return productList;
	}


	public static void main(String[] args) {
		 Configuration configuration =  new Configuration();
	      configuration.configure("hibernate1.cfg.xml");
	      SessionFactory sessionFactory = configuration.buildSessionFactory();
	      insert(sessionFactory);
	      System.out.println(getAllCustomer(sessionFactory));
	      update(sessionFactory);
	      System.out.println(getAllCustomer(sessionFactory));
	      delete(sessionFactory);
	      System.out.println(getAllCustomer(sessionFactory));

	      sessionFactory.close();

	}

}
