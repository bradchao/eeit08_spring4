package tw.brad.spring4.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import tw.brad.spring4.service.MemberService;

@Controller
public class MemberController {
	@Autowired
	private MemberService service;
}
