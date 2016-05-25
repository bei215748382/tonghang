package com.tonghang.server.col;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping(value = "/admin")
public class AdminCol {
	
	@RequestMapping(value = "index")
	public String index() {
		return "admin/index";
	}
	
	@RequestMapping(value = "index_info")
	public ModelAndView users_info(HttpServletRequest request,
			HttpServletResponse response) {
		ModelAndView modelAndView = new ModelAndView("admin/ajax/index_info");
		return modelAndView;
	}
}
