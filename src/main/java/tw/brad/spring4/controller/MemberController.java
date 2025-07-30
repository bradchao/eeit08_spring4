package tw.brad.spring4.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import tw.brad.spring4.service.MemberService;

@Controller
public class MemberController {
	@Autowired
	private MemberService service;
	
	@GetMapping("/register")
	public String displayRegister() {
		return "register";
	}
	
	@PostMapping("/register")
	public String doRegister(
			Model model,
			@RequestParam String account,
			@RequestParam String passwd,
			@RequestParam String name,
			@RequestParam MultipartFile icon) {
		try {
			service.register(account, passwd, name, icon);
			return "redirect:/login";
		} catch (Exception e) {
			System.out.println(e);
			model.addAttribute("error", "something error");
			return "register";
		}
	}
	
	@GetMapping("/login")
	public String login() {
		return "login";
	}
	
	
}
