package cn.luxun.mall.service;

import cn.luxun.mall.dto.LoginDto;
import cn.luxun.mall.entity.User;
import cn.luxun.mall.vo.ResultVo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.sun.deploy.net.HttpRequest;

public interface UserService extends IService<User> {

	/**
	 * 用户注册
	 *
	 * @param loginDto
	 * @return
	 */
	ResultVo registerByParams(LoginDto loginDto);

	/**
	 * 用户登录
	 *
	 * @param loginDto
	 * @return
	 */
	ResultVo loginByParams(LoginDto loginDto);


	/**
	 * 校验token是否有效
	 *
	 * @param token
	 * @return
	 */
	ResultVo checkByToken(String token);

	/**
	 * 根据用户id获取用户信息
	 *
	 * @param userId
	 * @return
	 */
	User getUserByUserId(String userId);
}
