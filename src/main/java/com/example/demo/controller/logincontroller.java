package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.models.login;
import com.example.demo.repository.loginrepository;

import ch.qos.logback.core.model.Model;
import jakarta.servlet.http.HttpSession;
import jakarta.websocket.Session;

@Controller
public class logincontroller {

	@Autowired
	loginrepository repo;
	
	@RequestMapping("/welcome")
	public String welcome() {
		return "welcome";
	}
	
	@GetMapping("/")
	public String home(org.springframework.ui.Model m)	{
		List<login> li = (List<login>) repo.findAll();
		m.addAttribute("add-products",li);
		return "home";
	}
	
	public String getbyid(@PathVariable(value = "id") int id,org.springframework.ui.Model m) {
		Optional<login> l = repo.findById(id);
		login g = l.get();
		m.addAttribute("products",g);
		return "edit";
		
	}
	@PostMapping("/save_products")
	public String insert(@ModelAttribute login m,HttpSession session) {
		repo.save(m);
		session.setAttribute("message", "sucessfully inserted");
		return "redirect:/loadform";
	}
	@PostMapping("/update")
	public String edit(@ModelAttribute login m,HttpSession session) {
		repo.save(m);
		session.setAttribute("message", "sucessfully updated");
		return "redirect:/";
	}
	@DeleteMapping("/delete/{id}")
	public String deletebyid(@PathVariable(value = "id")int id,Model m, HttpSession session) {
		repo.deleteById(id);
		session.setAttribute("message", "sucessfully deleted");
		return "redirect:/";

	}
	
	
	
	@GetMapping("/loadform")
	public String loadform() {
		return "add";
	}
}
