package com.sh.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sh.domain.User;
import com.sh.dto.Result;
import com.sh.repos.UserRepos;

@RestController
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserRepos userRepos;
	
	@PostMapping("/register")
	public Result register(String username,String account,String password,String isVip) {
		User user = userRepos.findByAccount(account);
		if (user != null) {
			return Result.error("该账号已注册");
		}
		
		user = new User();
		user.setUsername(username);
		user.setAccount(account);
		user.setPassword(DigestUtils.md5DigestAsHex(password.getBytes()));
		user.setVip(Boolean.valueOf(isVip));
		userRepos.save(user);
		return Result.ok("注册成功");
	}
	
	@PostMapping("/login")
	public Result login(String account,String password) {
		User user = userRepos.findByAccountAndPassword(account, DigestUtils.md5DigestAsHex(password.getBytes()));
		if (user == null) {
			return Result.error("账号或密码错误");
		}
		return Result.ok("登陆成功").put("user", user);
	}
	
	@PostMapping("/modify")
	public Result modify(long id,String img) {
		User user = userRepos.getOne(id);
		user.setImg(img);
		userRepos.save(user);
		return Result.ok("修改成功");
	}
}
