package com.sist.model;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class userDAOImpl implements userDAO {
	
	@Autowired
	private SqlSessionTemplate sqlSesssion;
	
	@Override
	public userDTO UserLogin(userDTO dto) {
		
		userDTO userDto = null;
		
		userDto =	this.sqlSesssion.selectOne("login", dto);
		
		try {
			if(userDto != null) {
				return userDto;
			}
		} catch (Exception e) {
			
		}
				
		return null;
	
	}
	
	@Override
	public int userIdCheck(String userId) {
		int res = 0;
		userDTO dto = null;
	
		dto = this.sqlSesssion.selectOne("userIdCheck", userId);
		
		try {
			if(dto == null) {
				res = 1;
			} else {
				res = 0;
			}
		} catch (Exception e) {
			
		}
		return res;
	}
	
	@Override
	public int insertUser(userDTO dto) {
		int res = 0;
		res = this.sqlSesssion.insert("join", dto);
		
		return res;
	}
	
	

	@Override
	public List<userDTO> showList() {
		
		
		return this.sqlSesssion.selectList("userlist");

	}
	@Override
	public userDTO UserInfo(String userId) {
		
		return this.sqlSesssion.selectOne("userInfo", userId);
	}
	

	@Override
	public int deleteUser(userDTO dto) {
		int res = 0;
		
		res = this.sqlSesssion.delete("userDelete", dto);
		
		return res;
	}

	@Override
	public int modifyUser(userDTO dto) {
		int res = 0;
		
		res = this.sqlSesssion.update("userModify", dto);
		
		return res;
	}

	

	

	

}
