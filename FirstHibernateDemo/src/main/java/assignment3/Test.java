package assignment3;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import assignment2.Market;

public class Test {
	public static void insert(SessionFactory sessionFactory)
	{
		Session session=sessionFactory.openSession();
		session.beginTransaction();
		
		Student s1=new Student("a");
		Student s2=new Student("b");
		Set <Student> hs1=new HashSet<Student>();
		hs1.add(s1);
		hs1.add(s2);
		
		Course c1=new Course("x");
		Course c2=new Course("y");
		Set <Course> hs2=new HashSet<Course>();
		hs2.add(c1);
		hs2.add(c2);
		
		s1.setCourses(hs2);
		s2.setCourses(hs2);
		
		session.save(s1);
        session.save(s2);
         
        session.getTransaction().commit();
		
	}
	
	public static void display(SessionFactory sessionfactory )
	{
		 Session session =sessionfactory.openSession();
		Query query=session.createQuery("From Student");
		 List <Student>list=query.list();
	      session.close();
	      
	      for(Student s:list)
	    	  System.out.println(s);
	     
	}
	

	public static void main(String[] args) 
	{
		  Configuration configuration =  new Configuration();
	      configuration.configure("hibernate3.cfg.xml");
	      SessionFactory sessionFactory = configuration.buildSessionFactory();
	     // insert(sessionFactory);
	      display(sessionFactory);
	      sessionFactory.close();
	}

}
