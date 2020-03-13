package pakage2;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public abstract class Testmain_onetomany {
	public static void insert(SessionFactory sessionfactory)
	{
		Stock stock_1=new Stock(425000.00);
		Stock stock_2=new Stock(450000.00);
	      Set<Stock> stockset=new HashSet<Stock>();
	      stockset.add(stock_1);
	      stockset.add(stock_2);
	      Session session =sessionfactory.openSession();
	      Transaction transcation1 =session.beginTransaction();
	       //session.persist(stockset);
	      Market market=new Market("Toys Market",stockset);
	      stock_1.setMarket(market);
	  	 stock_2.setMarket(market);
	      session.persist(market);
	      //System.out.println("Product  id="+productId);
	      transcation1.commit();
	      session.close();
	}
	public static void update(SessionFactory sessionfactory)
	{
		 Session session =sessionfactory.openSession();
	      Transaction transcation =session.beginTransaction();
	      Market market=session.load(Market.class, 1L);
	      market.setMarket_name("Sports market");
	   //  customer. getEenquiry().setEnq_ques("What is this ?");
	      transcation.commit();
	      session.close();
	}
	public static void delete(SessionFactory sessionFactory) {
		Session newSession=sessionFactory.openSession();
		Transaction t=newSession.beginTransaction();
		Stock stock=newSession.load(Stock.class,3L);
		newSession.delete(stock);
		t.commit();
		newSession.close();
	}
	public static List<Market>getAllMarket(SessionFactory sessionfactory )
	{
		 Session session =sessionfactory.openSession();
		Query query=session.createQuery("From Market");
		 List<Market> productList=query.list();
	      session.close();
	      return productList;
	}
	public static void main(String[] args) {
		 Configuration configuration =  new Configuration();
	      configuration.configure("hibernate3.cfg.xml");
	      SessionFactory sessionFactory = configuration.buildSessionFactory();
	      //insert(sessionFactory);
	     // update(sessionFactory);
	     delete(sessionFactory);
	      System.out.println(getAllMarket(sessionFactory));
	      sessionFactory.close();
	}

}
