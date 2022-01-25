package com.example.demo.config.exception;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;
@Controller
@Slf4j
public class CustomErrorController implements ErrorController{
	@RequestMapping("/error")
	public String err(HttpServletRequest req, Model md) {
		Object errCode = req.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
		Object exType = req.getAttribute(RequestDispatcher.ERROR_EXCEPTION);
		Object exMsg = req.getAttribute(RequestDispatcher.ERROR_MESSAGE);
		log.error("ERR!!>{}, {}, {}-----------------", errCode, exType, exMsg);
		if(errCode != null) md.addAttribute("errCode", errCode.toString());
		if(exType != null) md.addAttribute("exType", exType.toString());
		if(exMsg != null) md.addAttribute("exMsg", exMsg.toString());
		return "error/errorPage";
	}
}
