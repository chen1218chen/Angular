package com.city.action;

import javax.annotation.Resource;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.UnknownSessionException;
import org.apache.shiro.subject.Subject;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ExceptionMapping;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.stereotype.Controller;

import com.city.model.LoginModel;
import com.city.model.User;
import com.city.service.IUserService;
import com.opensymphony.xwork2.ModelDriven;

@ParentPackage(value = "json-default")
@Controller
@Action(value = "/loginAction", results = { @Result(name = "success", type = "redirect", location = "/index.jsp"),
		@Result(name = "input", type = "redirect", location = "/login.jsp"),
		@Result(name = "error", type = "redirect", location = "/login.jsp") }, exceptionMappings = {
				@ExceptionMapping(exception = "java.lang.Exception", result = "error") })
public class LoginAction extends BaseAction implements ModelDriven<LoginModel> {

	private static final long serialVersionUID = 1L;

	@Resource
	private IUserService userManagerImpl;
	private LoginModel login = new LoginModel();

	@Override
	public LoginModel getModel() {
		// TODO Auto-generated method stub
		return login;
	}

	// 重载
	public String execute() throws Exception {
		String password = login.getPassword();
		String telephone = login.getTelephone();
		if (password != null && !"".equals(password)) {
			// password = MD5.MD5Encode(password);
		}
		if (telephone == null || telephone == "" || password == null || password == "")
			return INPUT;
		Subject subject = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken(telephone, password);
		token.setRememberMe(true);// 记住我

		try {
			// 登录，即进行身份验证操作
			subject.login(token);// 调用 myRealm的 doGetAuthenticationInfo方法
			String telephone2 = (String) subject.getPrincipal();// 登陆成功就可以这样子拿到用户名了
			User user = userManagerImpl.queryByTel(telephone2);
			subject.getSession().setAttribute("uname", user.getName());
		} catch (UnknownSessionException uae) {
			subject.getSession().setAttribute("myerror", "登录异常!");
			return ERROR;
		} catch (UnknownAccountException ex) {
			subject.getSession().setAttribute("myerror", "用户名不存在!");
			return INPUT;
		} catch (IncorrectCredentialsException ice) {
			subject.getSession().setAttribute("myerror", "密码错误!");
			return INPUT;
		} catch (LockedAccountException lae) {
			subject.getSession().setAttribute("myerror", "登录异常!");
			return INPUT;
		} catch (AuthenticationException e) {
			subject.getSession().setAttribute("myerror", "用户登录失败!");
			return INPUT;
		}

		return SUCCESS;
	}
	
	public String logout() throws Exception {
		Subject subject = SecurityUtils.getSubject();
		if (subject.isAuthenticated()) {
			subject.logout(); // session 会销毁，在SessionListener监听session销毁，清理权限缓存
			/*
			 * if (LOG.isDebugEnabled()) { LOG.debug("用户" + userName + "退出登录");
			 * }
			 */
		}
		return INPUT;
	}
}
