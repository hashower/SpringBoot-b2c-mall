package cn.luxun.mall.controller;

import cn.luxun.mall.dto.LoginDto;
import cn.luxun.mall.service.UserService;
import cn.luxun.mall.vo.ResultVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@Api(value = "提供用户的登录和注册接口",tags = "用户管理")
public class UserController {

	private final UserService userService;

	public UserController(UserService userService) {
		this.userService = userService;
	}

	/**
	 * 用户注册
	 *
	 * @param loginDto
	 * @return
	 */
	@ApiOperation("用户注册接口")
	@PostMapping("/register")
	public ResultVo registerByParams(@RequestBody LoginDto loginDto) {
		return userService.registerByParams(loginDto);
	}


	/**
	 * 用户登录
	 *
	 * @param loginDto
	 * @return
	 */
	@ApiOperation("用户登录接口")
	@PostMapping("/login")
	public ResultVo loginByParams(@RequestBody LoginDto loginDto) {
		return userService.loginByParams(loginDto);
	}


	/**
	 * 校验token是否 有效
	 *
	 * @param token
	 * @return
	 */
	@ApiOperation("校验token是否过期")
	@PostMapping("/checkToken")
	public ResultVo checkByToken(@RequestHeader("token") String token) {
		return userService.checkByToken(token);
	}

}
