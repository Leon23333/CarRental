package com.sh.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import com.sh.domain.User;
import com.sh.dto.Result;
import com.sh.repos.UserRepos;
import com.sh.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserRepos userRepos;

	@Override
	public Result register(String username, String account, String password) {
		User user = userRepos.findByAccount(account);
		if (user != null) {
			return Result.error("该账号已注册");
		}
		user = new User();
		user.setUsername(username);
		user.setAccount(account);
		user.setPassword(DigestUtils.md5DigestAsHex(password.getBytes()));
		userRepos.save(user);
		return Result.ok("注册成功");
	}

	@Override
	public Result login(String account, String password) {
		User user = userRepos.findByAccountAndPassword(account, DigestUtils.md5DigestAsHex(password.getBytes()));
		if (user == null) {
			return Result.error("账号或密码错误");
		}
		return Result.ok("登陆成功").put("user", user);
	}

}
