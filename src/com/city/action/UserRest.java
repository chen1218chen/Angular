package com.city.action;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import net.sf.json.JSONArray;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.city.model.Role;
import com.city.model.User;
import com.city.service.IUserService;

@Controller
@RequestMapping("/user")
public class UserRest {
	@Resource
	private IUserService userManagerImpl;

	@RequestMapping(value = "/queryAllUser", method = RequestMethod.GET)
	@ResponseBody
	public JSONArray queryAllUser() {
		
		List<User> dataList = userManagerImpl.queryAll();
		JSONArray userJSONList2 = JSONArray.fromObject(dataList);
		return userJSONList2;
	}

	@RequestMapping("/judgmentAdminByName")
	@ResponseBody
	public String judgmentAdminByName() {
		Subject subject = SecurityUtils.getSubject();
		String uname = (String) subject.getPrincipal();
		User user;
		try {
			user = userManagerImpl.queryByName(uname).get(0);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		Set<Role> roles = user.getRoles();
		Iterator<Role> iterator = roles.iterator();
		Role role = iterator.next();
		 
		return role.getRname();
	}

	@RequestMapping(value = "/queryByName", method = RequestMethod.POST)
	@ResponseBody
	public User queryByName(@RequestParam("uname") String uname) {

		User user;
		try {
			user = userManagerImpl.queryByName(uname).get(0);
		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
			return null;
		}

		return user;
	}

	@RequestMapping(value = "/saveUser", method = RequestMethod.POST)
	@ResponseBody
	public boolean saveUser(@RequestParam("uname") String uname,
			@RequestParam("upassword") String upassword,
			@RequestParam(value = "oid-add", required = false) int oid,
			@RequestParam("uduty1") int rid,
			@RequestParam(value = "uphone", required = false) String uphone) {

		Role role = new Role();
		User user = new User();
		
		try {
			userManagerImpl.insert(user);
		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@RequestMapping(value = "/delUser", method = RequestMethod.POST)
	@ResponseBody
	public boolean delUser(@RequestParam("ids") int[] ids) {

		User user = new User();
		try {
			for (int i = 0; i < ids.length; i++) {
				user.setId(ids[i]);
				userManagerImpl.delete(user);
			}
		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@RequestMapping(value = "/updateUser", method = RequestMethod.POST)
	@ResponseBody
	public boolean updateUser(@RequestParam("uid") int uid,
			@RequestParam("uname") String uname,
			@RequestParam("upassword") String upassword,
			@RequestParam("oid") int oid, @RequestParam("uduty") int rid,
			@RequestParam("uphone") String uphone,
			@RequestParam("isapproval") String isapproval) {

		Role role = new Role();
		User user = userManagerImpl.queryByID(uid);
		Set<Role> roles = user.getRoles();
		
		role.setRid(rid);
		if(role.getRid()!=rid){
			roles.add(role);
		}
		user.setRoles(roles);
		try {
			userManagerImpl.update(user);
		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
			return false;
		}
		return true;
	}
}
