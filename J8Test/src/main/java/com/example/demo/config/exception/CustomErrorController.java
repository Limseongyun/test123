package com.example.demo.config.exception;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.example.demo.cmm.code.ApiCd;
import com.example.demo.cmm.rvo.RVO;
import com.google.gson.Gson;

import lombok.extern.slf4j.Slf4j;
@Controller
@Slf4j
public class CustomErrorController implements ErrorController{
	@RequestMapping(value = "/error")
	public String err(HttpServletRequest req, HttpServletResponse resp, Model md) {
		log.debug("[Error Controller] {}", req.getRequestURI());
		log.debug("[request is] {}", req.getAttribute("isApi"));
		Boolean isApi = (Boolean) req.getAttribute("isApi");
		if(isApi != null && isApi) {
			return "forward:/public/apiError";
		} else {
			Object errCode = req.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
			Object exType = req.getAttribute(RequestDispatcher.ERROR_EXCEPTION);
			Object exMsg = req.getAttribute(RequestDispatcher.ERROR_MESSAGE);
			log.error("ERR!!>{}, {}, {}-----------------", errCode, exType, exMsg);
			if(errCode != null) md.addAttribute("errCode", errCode.toString());
			if(exType != null) md.addAttribute("exType", exType.toString());
			if(exMsg != null) md.addAttribute("exMsg", exMsg.toString());
			resp.setContentType("application/json; charset=UTF-8");
			return "error/errorPage";
		}
	}
	
	@RequestMapping(value = "/public/apiError", produces = "application/json")
	@ResponseBody
	public String apiErr(HttpServletRequest req, HttpServletResponse resp, Model md) {
		Object errCode = req.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
		Object exType = req.getAttribute(RequestDispatcher.ERROR_EXCEPTION);
		Object exMsg = req.getAttribute(RequestDispatcher.ERROR_MESSAGE);
		log.error("apiError!!>{}, {}, {}-----------------", errCode, exType, exMsg);
		if(errCode != null) md.addAttribute("errCode", errCode.toString());
		if(exType != null) md.addAttribute("exType", exType.toString());
		if(exMsg != null) md.addAttribute("exMsg", exMsg.toString());
		resp.setContentType("application/json; charset=UTF-8");
		
		return new Gson().toJson(RVO.builder().msg("오류발생^^..").data(exType.toString()).code(ApiCd.DEFAULT_ERR).build());
	}
}
