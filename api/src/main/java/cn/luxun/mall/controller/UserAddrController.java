package cn.luxun.mall.controller;

import cn.luxun.mall.service.UserAddrService;
import cn.luxun.mall.vo.ResultVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/useraddr")
@Api(value = "提供收货地址相关接口", tags = "收货地址管理")
public class UserAddrController {

	private final UserAddrService userAddrService;

	public UserAddrController(UserAddrService userAddrService) {
		this.userAddrService = userAddrService;
	}

	/**
	 * 根据用户id获得所有用户地址
	 *
	 * @param id
	 * @return
	 */
	@ApiOperation("获取用户地址接口")
	@GetMapping("/list")
	public ResultVo getUserAddrsByUserId(Integer userId) {
		return userAddrService.getUserAddrsByUserId(userId);
	}
}
