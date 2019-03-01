package com.sh.service;

import com.sh.dto.Result;

public interface UserService {
	Result register(String username,String account,String password);
	
	Result login(String account,String password);
}
