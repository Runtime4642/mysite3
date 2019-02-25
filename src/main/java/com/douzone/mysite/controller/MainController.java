package com.douzone.mysite.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.douzone.mysite.service.SiteService;


@Controller
public class MainController {
	@Autowired
	private SiteService siteService;
	
	@RequestMapping({"/main",""})
	public String main(Model model) {
//		return "/WEB-INF/views/main/index.jsp"  view ViewResolver 설정을 해주면 밑에처럼 써주면됨 prefix + return + suffix
		model.addAttribute("siteVo",siteService.getMain());
		return "main/index";
	}
	
	@RequestMapping({"/weather"})
	public String weather() {
//		return "/WEB-INF/views/main/index.jsp"  view ViewResolver 설정을 해주면 밑에처럼 써주면됨 prefix + return + suffix
		return "main/weather";
	}
	

}
