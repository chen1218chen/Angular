package com.city.login;


import java.util.Set;

import javax.annotation.Resource;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import com.city.model.Role;
import com.city.model.User;
import com.city.service.IUserService;

public class myRealm extends AuthorizingRealm {
	@Resource
	private IUserService userManagerImpl;
	/**
	 * 回调函数，提取当事人的角色和权限 principals 当事人 ,权限验证
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(
			PrincipalCollection principals) {
		// TODO Auto-generated method stub
		String userName = (String) principals.fromRealm(getName()).iterator().next();
//		System.out.println("------------"+userName);
		//根据用户名查找拥有的角色
		User user = userManagerImpl.queryByTel(userName);
		Set<Role> roles = user.getRoles();
		if (roles != null) {
			SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();

			for (Role role : roles) {
				info.addRole(role.getRname());
			}

			return info;
		} else {
			return null;
		}
		
	}

	/**
	 * 登录验证
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken authcToken) throws AuthenticationException {
		// TODO Auto-generated method stub
		// 令牌——基于用户名和密码的令牌
		UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
		// 令牌中可以取出用户名密码
		String userName = token.getUsername();
		String passWord = new String(token.getPassword());
		User user = userManagerImpl.queryByTel(userName);
		String passwd = user.getPassword();
//		String approval = user.getIsapproval();
	    if(user.getId() == null){
	    	System.out.println("没有找到用户 ");
			throw new UnknownAccountException("没有找到用户 [" + userName + "]");
	    }
	    if(!passwd.equals(passWord)){
	    	System.out.println("密码错误！！");
	    	throw new IncorrectCredentialsException("密码错误！！");
	    }
	  /*  if(approval.equals("0")){
	    	System.out.println("审批未通过");
	    	throw new IncorrectCredentialsException("审批未通过！！");
	    }*/
		//将正确的用户信息，请求登录用户的用户名和正确的密码，创建AuthenticationInfo对象并返回  
		AuthenticationInfo authinfo = new SimpleAuthenticationInfo(userName,
				passWord, getName());
		return authinfo;
		// return new SimpleAuthenticationInfo("admin","1",getName());
	}
	
}
