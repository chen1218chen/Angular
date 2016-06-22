package com.city.action;


import org.apache.shiro.SecurityUtils;

import org.apache.shiro.subject.Subject;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ExceptionMapping;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.stereotype.Controller;


@ParentPackage(value = "json-default")
@Controller
public class LogoutAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	
	@Action(value = "/logoutAction", results = { 
			@Result(name = "success", type = "redirect", location = "/login.jsp"),
			@Result(name = "error", type = "redirect", location = "/index.jsp") }, exceptionMappings = {
					@ExceptionMapping(exception = "java.lang.Exception", result = "error") })
	public String logout() throws Exception {
		Subject subject = SecurityUtils.getSubject();
		if (subject.isAuthenticated()) {
			subject.logout(); // session 会销毁，在SessionListener监听session销毁，清理权限缓存
		}
		return SUCCESS;
	}
}
