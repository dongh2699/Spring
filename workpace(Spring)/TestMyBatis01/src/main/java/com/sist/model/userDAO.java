package com.sist.model;

import java.util.List;

public interface userDAO {
		
	int insertUser(userDTO dto);
	List<userDTO> showList();
	userDTO UserLogin(userDTO dto);
	userDTO UserInfo(String userId);
	int deleteUser(userDTO dto);
	int modifyUser(userDTO dto);
	int userIdCheck(String userId);
}
