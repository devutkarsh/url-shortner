/* Data Access Layer for all sql hibernate qureies call only though some logic used to save time
 * used Jackson JSON to set JSON output
 * written by Utkarsh Dev 
 * devutkarsh.com
 */

package com.url.dao;

import java.io.IOException;
import java.util.List;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.ObjectWriter;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

import com.url.entity.URLInfo;

public class SqlQuery {
	
	//to create a new URL
	public String createShortURL(String url, String urlnew, int hits) {
		
		URLInfo uin = new URLInfo();
		
		//set url params
		uin.setUrl(url);
		uin.setUrlnew(urlnew);
		uin.setHits(hits);
		
		//execute query
		SessionFactory sf = new AnnotationConfiguration().configure().buildSessionFactory();
		Session s = sf.openSession();
		s.beginTransaction();
		s.save(uin);
		s.getTransaction().commit();
		s.close();
		sf.close();
		

		
		return urlnew;
	}
	


	//to fetch existing original URL
	public String fetchsURL(String urlnew) {

		String url="";
		
		//execute query
		SessionFactory sf = new AnnotationConfiguration().configure().buildSessionFactory();
		Session s = sf.openSession();
		s.beginTransaction();
			
		
		String hql = "FROM URLInfo U WHERE U.urlnew = :urlnew";
		Query query = s.createQuery(hql);
		query.setParameter("urlnew",urlnew);
		
		@SuppressWarnings("unchecked")
		List<URLInfo> results =(List<URLInfo>) query.list();
		
		
		for(URLInfo u : results)
	    {
			  url = u.getUrl();
	    }
	      
	    
		
		s.getTransaction().commit();
		s.close();
		sf.close();
		
		return url;
	}
	
	
	//to get current hit count
		public int getHitsCount(String urlnew) {

			int hits=0;
			
			//execute query
			SessionFactory sf = new AnnotationConfiguration().configure().buildSessionFactory();
			Session s = sf.openSession();
			s.beginTransaction();
				
			
			String hql = "FROM URLInfo U WHERE U.urlnew = :urlnew";
			Query query = s.createQuery(hql);
			query.setParameter("urlnew",urlnew);
			
			@SuppressWarnings("unchecked")
			List<URLInfo> results =(List<URLInfo>) query.list();
			
			
			for(URLInfo u : results)
		    {
				  hits = u.getHits();
		    }
		      
		    
			
			s.getTransaction().commit();
			s.close();
			sf.close();
			
			return hits;
		}
	
	//to increase a count
		public void increaseHit(String urlnew, int hits) {
			
			hits++;
			
			//execute query
			SessionFactory sf = new AnnotationConfiguration().configure().buildSessionFactory();
			Session s = sf.openSession();
			s.beginTransaction();

			String hql = "Update URLInfo set hits = :hits " +"WHERE urlnew = :urlnew";
			Query query = s.createQuery(hql);
			query.setParameter("hits",hits);
			query.setParameter("urlnew",urlnew);
			
			@SuppressWarnings("unused")
			int result = query.executeUpdate();
			//System.out.println("Rows affected: " + result);
			
			s.getTransaction().commit();
			s.close();
			sf.close();
			

		}
		
		
		
		
		//to fetch existing original URL
		public String display() {

			String data=null;
			
			//execute query
			SessionFactory sf = new AnnotationConfiguration().configure().buildSessionFactory();
			Session s = sf.openSession();
			s.beginTransaction();
				
			
			String hql = "FROM URLInfo U ORDER BY U.hits DESC";
			Query query = s.createQuery(hql);
			
			@SuppressWarnings("unchecked")
			List<URLInfo> results =(List<URLInfo>) query.list();
			
			
			//write to json / should have done in URLService layer :P
			ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
			try {
				 data = ow.writeValueAsString(results);
			} catch (JsonGenerationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (JsonMappingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			//System.out.println("JSON in DAO "+data);
			
		    
			
			s.getTransaction().commit();
			s.close();
			sf.close();
			
			return data;
		}
		
	
}
