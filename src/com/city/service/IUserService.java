package com.city.service;

import java.util.List;
import com.city.model.Role;
import com.city.model.User;

public interface IUserService {
	public String getUserName();
	public User queryByID(int uid);
	public List<User> queryByName(String name);
	public List<User> queryAll();
	public void insert(User user);
	public void update(User user);
	public void delete(User user);
	public User load(int uid);
	public List<Role> getUserRoles(String userName);
	public User queryByTel(String telephone);
}
