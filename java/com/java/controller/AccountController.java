package com.java.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
}
