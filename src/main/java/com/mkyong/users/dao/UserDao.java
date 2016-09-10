package com.mkyong.users.dao;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.mkyong.users.model.User;
@Transactional
public interface UserDao {

	User findByUserName(String username);
	List<User> loadAll();
	
	public void updateUser(User inUser);
	
	
}