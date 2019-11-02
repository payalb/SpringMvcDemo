package com.java.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.java.service.AccoutnService;

@Controller
public class AccountController {

	@Autowired AccoutnService service;
	
	@PostMapping("/deposit")
	public String depositMoney(@RequestParam float amt, @RequestParam int accId) {
		service.depositMoney(amt, accId);
		return "home";
	}
	@PostMapping("/withdraw")
	public String withdrawMoney(@RequestParam float amt, @RequestParam int accId) {
		service.withdrawMoney(amt, accId);
		return "home";
	}
	@PostMapping("/transfer")
	public String transferMoney(@RequestParam float amt, @RequestParam int accId1,@RequestParam int accId2) {
		service.transferMoney(amt, accId1, accId2);
		return "home";
	}
	
	//Content Negotiation: Based on Accept type, can return data in that format
	//When the client makes a request, he can set the type of content he is looking for 
	//Accept in ur header.
	//Returning u data, not a view page
	@ResponseBody
	@GetMapping(path="/getBalance", produces=MediaType.TEXT_PLAIN_VALUE)
	public String getBalance(@RequestParam int accId) {
		return service.getBalance(accId)+"";
	}
	
	@ResponseBody
	@GetMapping(path="/getBalance", produces=MediaType.TEXT_HTML_VALUE)
	public String getBalance1(@RequestParam int accId) {
		return "<html>"+service.getBalance(accId)+"</html>";
	}
}
