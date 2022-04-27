package cn.luxun.mall.service;

import cn.luxun.mall.dto.LoginDto;
import cn.luxun.mall.entity.User;
import cn.luxun.mall.vo.ResultVo;
import com.baomidou.mybatisplus.extension.service.IService;

public interface UserService extends IService<User> {

	/**
	 * 用户注册
	 *
	 * @param user
	 * @return
	 */
	ResultVo registerByParams(LoginDto loginDto);

	/**
	 * 用户登录
	 *
	 * @param user
	 * @return
	 */
	ResultVo loginByParams(LoginDto loginDto);

	/**
	 * 根据id寻找User
	 *
	 * @param id
	 * @return
	 */
	ResultVo findUserById(Integer id);

	/**
	 * 校验token是否有效
	 *
	 * @param token
	 * @return
	 */
	ResultVo checkByToken(String token);
}
