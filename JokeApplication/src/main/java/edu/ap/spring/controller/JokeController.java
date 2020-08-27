package edu.ap.spring.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

@Controller
@Scope("session")
public class JokeController {
   
   public JokeController() {
   }
       
   @RequestMapping("/joke")
   public String joke() {
	   return "Joke";
   }
		   
   @RequestMapping("/joke_post")
   public String joke_post(@RequestParam("firstName") String firstName,
						   @RequestParam("lastName") String lastName,
						   Model model) {
	   try {
		   URL url = new URL("http://api.icndb.com/jokes/random" + "?firstName=" + firstName + "&lastName=" + lastName);
		   HttpURLConnection con = (HttpURLConnection) url.openConnection();
		   
		   con.setRequestMethod("GET");

		   BufferedReader in = new BufferedReader(
			        new InputStreamReader(con.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();

			System.out.println(response.toString());
			
			ObjectMapper mapper = new ObjectMapper();
		    JsonNode actualObj = mapper.readTree(response.toString());
		    JsonNode jsonNode1 = actualObj.get("value");
		    JsonNode jsonNode2 = jsonNode1.get("joke");
			
			model.addAttribute("joke_text", jsonNode2.textValue());
	   } 
	   catch (Exception e) {
		e.printStackTrace();
	   }
	   return "Joke_result";
   }
   
   @RequestMapping("/")
   public String root() {
	   return "redirect:/joke";
   }
}
