package com.tonghang.server.col;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.plaf.multi.MultiListUI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSON;
import com.tonghang.server.common.dto.BasicRequestDTO;
import com.tonghang.server.exception.ServiceException;
import com.tonghang.server.service.impl.SocialServiceImpl;

@Controller
@RequestMapping(value = "/api/social")
public class AppSocialController extends AppBaseController {

	@Autowired
	private SocialServiceImpl socialService;

	@RequestMapping(value = "/publishsns", method = { RequestMethod.POST, RequestMethod.PUT })
	public @ResponseBody Object publishSns(HttpServletRequest request, HttpServletResponse response,
			MultipartFile[] pictures) {
		BasicRequestDTO baseRequest = (BasicRequestDTO) request.getAttribute("requestDTO");
		String content = baseRequest.getContent();
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			Map<String, String> params = (Map<String, String>) JSON.parse(content);
			checkParams(params);
			String txt = params.get("content");
			result.put("code", 200);
			result.put("msg", "success");
			Map<String, Object> data = socialService.publishSns(baseRequest.getUserId().intValue(), txt, pictures);
			result.put("data", data);
		} catch (ServiceException e) {
			result.put("code", e.getCode());
			result.put("msg", e.getMessage());
		}

		return (result);
	}

}
