/* Business Service Layer  for all implemention changes and business logic only
 *
 * written by Utkarsh Dev 
 * devutkarsh.com
 */
package com.url.service;

import java.security.SecureRandom;

import java.util.Random;



import com.url.dao.SqlQuery;


public class URLService {

	// method to generate URL
	public String generateURL(String url){
		
		
		SqlQuery sq = new SqlQuery();
		String url2 = URLService.randomString();
		System.out.println(url2);

		String urlnew = sq.createShortURL(url, url2, 0);
			
		String jsonOut = "http://127.0.0.1:8080/URL/c="+urlnew;
			
	
		return jsonOut;
	}
	
	
	
	//to generate 5 digit random string for short URL
	public static String randomString() {
		char[] characterSet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789".toCharArray();
		int length = 6;
		
	    Random random = new SecureRandom();
	    char[] result = new char[length];
	    
	    
	    for (int i = 0; i < result.length; i++) {
	        // picks a random index out of character set > random character
	        int randomCharIndex = random.nextInt(characterSet.length);
	        result[i] = characterSet[randomCharIndex];
	    }
	    
	    return new String(result);
	}
	
	
	
	// fetch URL and hit count
	public String redirect(String urlnew){
		String url=null;
		
		
		
		SqlQuery sq = new SqlQuery();
		
		int hits = sq.getHitsCount(urlnew);
		System.out.println("Hits count for cuurent url is "+hits);
		
		sq.increaseHit(urlnew, hits);
		
		url = sq.fetchsURL(urlnew);
		

		
		return url;
	}
	
	
	// fetch URL and hit count for display
		public String Display(){

			SqlQuery sq = new SqlQuery();
			
			String data = sq.display();
			
			return data;
		}
	
}
