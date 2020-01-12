package RussiaRT.news;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


public class App 
{

	public static void main( String[] args ) throws IOException, SQLException 
    {
    	Document news = Jsoup.connect("https://russian.rt.com/").get();
        Elements ul = news.select("ul[class=listing__rows listing__rows_main-news]");
        Elements timeList = ul.select("div[class=card__date card__date_main-news]");
        Elements headerList = ul.select("a[class=link link_color]");
        
        for (Element time : timeList) {
        	String timeString = time.text();
        	System.out.printf("%s%n", timeString);
        }
       
        for (Element header : headerList) {
        	String headerString = header.text();
        	System.out.printf("%s%n", headerString);
        	System.lineSeparator();
        	
        }

        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
        try {
        	List ListHeader = headerList.eachText();
        	List ListTime = timeList.eachText();
        	
        	Transaction tx = session.beginTransaction();
        	
        	for(int i =0; i<headerList.size(); i++) {
        		news asd = new news();
        		asd.setTime(ListTime.get(i).toString());
            	asd.setHeader(ListHeader.get(i).toString());
                session.save(asd);
        	}
        	       		
            tx.commit();
       	
        }catch(Exception e) {
        	session.getTransaction().rollback();
        	e.printStackTrace();
        }finally {
        	session.close();
        	sessionFactory.close();
        }
    }     
}
		
   


