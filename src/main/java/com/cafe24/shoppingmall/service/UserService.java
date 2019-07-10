package com.cafe24.shoppingmall.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cafe24.shoppingmall.repository.UserDAO;
import com.cafe24.shoppingmall.vo.UserVO;

@Service
public class UserService {

	@Autowired
	private UserDAO userDAO;
	
	public Boolean checkId(String id) {
		return userDAO.get(id) != null;
	}

	public Boolean registerMember(UserVO vo) {
		return userDAO.insert(vo);
	}

	public Boolean deleteAll() {
		return userDAO.deleteAll();
	}
}
