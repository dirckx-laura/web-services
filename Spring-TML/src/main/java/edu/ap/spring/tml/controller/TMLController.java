package edu.ap.spring.tml.controller;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import edu.ap.spring.redis.RedisService;

import static java.lang.Integer.parseInt;

@Controller
public class TMLController {

   @Autowired
   private RedisService service;
   private int TOTAL_TICKETS = 5;
   private double RESERVE = 1.0;

   @GetMapping("/")
   private String getIndex() {

	    return "loginPage";
   }

	@GetMapping("/users")
	private String getSignIn() {

		return "users";
	}
	@GetMapping("/login")
	private String getLogin() {

		return "login";
	}

	@GetMapping("/loginCheck")
	private String checkIfLogin(@RequestParam("email") String email,@RequestParam("password") String password,Model model)  {
		String user = bytesToHex(email) + bytesToHex(password);


			model.addAttribute("ticketLink", "/tickets/1/" + user);

			return "buyTicket";



	}

   @GetMapping("/tickets/{event}/{user}")
   public String getTicket(@PathVariable("event") int event,
	   					   @PathVariable("user") String user,
                           Model model) throws Exception {

		String page = "previousSale";
	   try {
		String userKey = this.service.keys("user:" + user + ":*").iterator().next();
		int userId = parseInt(userKey.split(":")[2]);

			if (this.service.bitCount("event:" + event + ":users") <= TOTAL_TICKETS * RESERVE) {
				if (this.service.getBit("event:" + event + ":users", userId) != true) {
					this.service.setBit("event:" + event + ":users", userId, true);
					page = "redirect:/tickets/" + event + "/" + user + "/sale";
				}
			}
		}catch(Exception e){
			throw new Exception("Wrong login credentials!");
		}
		
		return page;	
   }

   @GetMapping("/tickets/{event}/{user}/sale")
   private String distributeTickets(@PathVariable("event") int event,
									@PathVariable("user") String user,
   								    Model model) {
		
	    return "sale";
   }

   @GetMapping("/tickets/{event}")
   private String tickets(@PathVariable("event") int event, Model model) {

		model.addAttribute("ticketsCount", TOTAL_TICKETS - this.service.bitCount("event:" + event + ":users"));

	    return "tickets";
   }

	@PostMapping("/signin")
	public String signin(@RequestParam("email") String email,
							@RequestParam("password") String password, Model model) {
		if(this.service.exists("usercount")) {
			this.service.incr("usercount");
		}
		else {
			this.service.setKey("usercount", "1");
		}
		String user = bytesToHex(email)+ bytesToHex(password);

		this.service.setKey("user:" + user+ ":" + this.service.getKey("usercount"), email);

		model.addAttribute("ticketLink", "/tickets/1/" + user);

		return "buyTicket";

	}

   private String bytesToHex(String str) {

		String retString = "";
		try {
			MessageDigest digest = MessageDigest.getInstance("SHA-256");
			byte[] encodedhash = digest.digest((str).getBytes(StandardCharsets.UTF_8));
			StringBuffer hexString = new StringBuffer();
			for (int i = 0; i < encodedhash.length; i++) {
			String hex = Integer.toHexString(0xff & encodedhash[i]);
			if(hex.length() == 1) hexString.append('0');
				hexString.append(hex);
			}
			retString = hexString.toString();
		}
		catch(Exception ex) {
			System.out.println(ex.getMessage());
		}

		return retString;
	}
}
