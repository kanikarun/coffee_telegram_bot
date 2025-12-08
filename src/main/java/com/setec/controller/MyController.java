package com.setec.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.setec.entities.Booked;
import com.setec.repos.BookedRepo;
import com.setec.services.MyTelegramBot;

@Controller
public class MyController {
	//http://localhost:8080/
	
	@GetMapping({"/","/home","/p/p1"})
	public String home(Model mod) {
		Booked booked = new Booked(
				1,"Run Kanika",
				"0965686631",
				"runkanika0@gmail.com",
				"11/12/2025",
				"5:17 PM",
				5
				);
		
		mod.addAttribute("booked",booked);
		return "index";
	}
	
	//http://localhost:8080/about
	@GetMapping({"/about"})
	public String about() {
		return "about";
	}
	
	//http://localhost:8080/service
	@GetMapping({"/service"})
	public String service() {
		return "service";
	}
	
	//http://localhost:8080/menu
	@GetMapping({"/menu"})
	public String menu() {
		return "menu";
	}
	
	//http://localhost:8080/reservation
	@GetMapping({"/reservation"})
	public String reservation(Model mod) {
		Booked booked = new Booked(
				1,"Run Kanika",
				"0965686631",
				"runkanika0@gmail.com",
				"11/12/2025",
				"5:17 PM",
				5
				);
		
		mod.addAttribute("booked",booked);
		return "reservation";
		
	}
	
	//http://localhost:8080/testimonial
	@GetMapping({"/testimonial"})
	public String testimonial() {
		return "testimonial";
	}
	
	//http://localhost:8080/contact
	@GetMapping({"/contact"})
	public String contact() {
		return "contact";
	}
	
	@Autowired
	private BookedRepo bookedRepo;

	@Autowired
	private MyTelegramBot bot;
	
	//http://localhost:8080/success
	@PostMapping("/success")
	public String Success(@ModelAttribute Booked booked) {
		bookedRepo.save(booked);
		bot.sendMessage(
		        "📌 Booking Details\n\n" +
		        "🆔 ID: " + booked.getId() + "\n" +
		        "👤 Name: " + booked.getName() + "\n" +
		        "📞 Phone: " + booked.getPhoneNumber() + "\n" +
		        "📧 Email: " + booked.getEmail() + "\n" +
		        "--------------------------------------------"+
		        "📅 Date: " + booked.getDate() + "\n" +
		        "⏰ Time: " + booked.getTime() + "\n" +
		        "👥 Persons: " + booked.getPerson()
		);

		return "success";
	}
	
}
