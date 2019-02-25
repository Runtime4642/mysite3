package com.douzone.mysite.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.douzone.mysite.service.SiteService;
import com.douzone.mysite.vo.SiteVo;
import com.douzone.security.Auth;
import com.douzone.security.Auth.Role;


@Auth(Role.ADMIN)
@Controller
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	private SiteService siteService;
	
	@RequestMapping({"","/main"})
	public String main(Model model) {
		
		model.addAttribute("siteVo",siteService.getMain());
		return "admin/main";
	}
	
	@RequestMapping("/main/update")
	public String mainUpdate(@ModelAttribute SiteVo siteVo,Model model) {
		
		boolean result = siteService.mainUpdate(siteVo);
		model.addAttribute("result",result);
		return "admin/main";
	}
	
	
	@RequestMapping("board")
	public String board() {
		
		return "admin/board";
	}
	
	
}