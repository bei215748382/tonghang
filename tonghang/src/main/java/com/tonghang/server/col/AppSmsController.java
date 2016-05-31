package com.tonghang.server.col;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/api/support")
public class AppSmsController {

    private static Logger log = LoggerFactory.getLogger(AppSmsController.class);
    
    @RequestMapping(value = "/getverifycode", method = { RequestMethod.POST })
    public String getVerifyCode(HttpServletRequest request,
            HttpServletResponse response) {
        String connent = (String) request.getAttribute("content");
        log.info(connent);
        return null;
    }

}
