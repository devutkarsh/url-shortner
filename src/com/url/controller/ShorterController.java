/* Controller Layer for all control routing and request handling only
 *
 * written by Utkarsh Dev 
 * devutkarsh.com
 */
package com.url.controller;




import java.io.IOException;



import javax.servlet.http.HttpServletResponse;


















import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;








import com.url.service.URLService;


@Controller
public class ShorterController {
	

	@RequestMapping(value = "/short", method = RequestMethod.GET)
	public @ResponseBody String processAJAXRequest(@RequestParam("url") String url, HttpServletResponse response) {
		
			
			URLService us = new URLService();
			
			String res = us.generateURL(url);
			
			//System.out.println("new url is "+response);
		
			return res;
		}
	
	
	@RequestMapping(value="/c={urlnew}", method=RequestMethod.GET)
	public String getURL(@PathVariable("urlnew") String urlnew) {

		//System.out.println("c = "+urlnew);
		
		URLService us = new URLService();
		String url = us.redirect(urlnew);
		
		//System.out.println("Got it "+url);
		

		return "redirect:" + url;


	
		
	}
	
	
	@RequestMapping(value = "/displayData", method = RequestMethod.GET)
	public @ResponseBody void processDisplayRequest(HttpServletResponse response) throws IOException {
		//@RequestParam("displayData") String displayData
			
			URLService us = new URLService();
			
			String data= us.Display();
			
			//System.out.println("Controller json "+data);
			
			response.setContentType("application/json");
			response.getWriter().write(data);
			
			
			
		

		}
	

}
