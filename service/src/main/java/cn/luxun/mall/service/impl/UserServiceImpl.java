package cn.luxun.mall.service.impl;

import cn.luxun.mall.config.JwtUtil;
import cn.luxun.mall.dto.LoginDto;
import cn.luxun.mall.entity.User;
import cn.luxun.mall.mapper.UserMapper;
import cn.luxun.mall.service.UserService;
import cn.luxun.mall.vo.ErrorCodeVo;
import cn.luxun.mall.vo.ResultVo;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sun.deploy.net.HttpRequest;
import com.sun.deploy.net.HttpResponse;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.Date;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

	@Override
	public ResultVo registerByParams(LoginDto loginDto) {

		String userName = loginDto.getUsername();
		String passWord = loginDto.getPassword();

		if (StringUtils.isBlank(loginDto.getUsername()) || StringUtils.isBlank(loginDto.getPassword())) {
			return ResultVo.fail(ErrorCodeVo.PARAMS_ERROR.getCode(), ErrorCodeVo.PARAMS_ERROR.getMsg());
		}

		LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
		queryWrapper.eq(User::getUsername, loginDto.getUsername());
		User getUser = this.getOne(queryWrapper);
		if (getUser != null) {
			return ResultVo.fail(ErrorCodeVo.ACCOUNT_EXIST.getCode(), "账户已经被注册了");
		}

		User user = new User();
		user.setUsername(userName);
		user.setPassword(passWord);
		user.setImg("222");
		user.setCreateTime(new Date());
		user.setUpdateTime(new Date());
		this.save(user);
		String token = JwtUtil.createToken(user);

		System.out.println(token);
		return ResultVo.success(token);
	}

	@Override
	public ResultVo loginByParams(LoginDto loginDto) {
		if (StringUtils.isBlank(loginDto.getUsername()) || StringUtils.isBlank(loginDto.getPassword())) {
			return ResultVo.fail(ErrorCodeVo.PARAMS_ERROR.getCode(), ErrorCodeVo.PARAMS_ERROR.getMsg());
		}

		// 条件构造器
		LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();

		// 添加条件
		queryWrapper.eq(User::getUsername, loginDto.getUsername());
		queryWrapper.eq(User::getPassword, DigestUtils.md5DigestAsHex(loginDto.getPassword().getBytes()));

		User user = this.getOne(queryWrapper);
		if (user == null) {
			return ResultVo.fail(ErrorCodeVo.ACCOUNT_PWD_NOT_EXIST.getCode(), ErrorCodeVo.ACCOUNT_PWD_NOT_EXIST.getMsg());
		}
		// 生成token
		String token = JwtUtil.createToken(user);
		return ResultVo.success(token);
	}

	@Override
	public ResultVo findUserById(Integer user_id) {
		User user = this.getById(user_id);
		System.out.println(ResultVo.success(user));
		return ResultVo.success(user);
	}

	@Override
	public ResultVo checkByToken(String token) {
		return ResultVo.success("token未过期");
	}


}
