package tw.brad.spring4.controller;

import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import jakarta.servlet.http.HttpSession;
import tw.brad.spring4.entity.Member;
import tw.brad.spring4.repository.MemberRepository;
import tw.brad.spring4.service.MemberService;

@Controller
public class MemberController {

    private final MemberRepository memberRepository;
	@Autowired
	private MemberService service;

    MemberController(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }
	
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
	public String showLogin() {
		return "login";
	}
	
	@PostMapping("/login")
	public String doLogin(			
			Model model,
			@RequestParam String account,
			@RequestParam String passwd,
			HttpSession session) {
		
		Member member = service.login(account, passwd);
		if (member != null) {
			session.setAttribute("member", member);
			return "redirect:/home";
		}
		
		return "login";
	}
	
	@GetMapping("/home")
	public String home(HttpSession session, Model model) {
		Member member = (Member)session.getAttribute("member");
		if (member == null) return "redirect:/login";
		
		String base64 = Base64.getEncoder().encodeToString(member.getIcon());
		
		model.addAttribute("member", member);
		model.addAttribute("icon", base64);
		
		return "home";
	}
	
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/login";
	}
}
