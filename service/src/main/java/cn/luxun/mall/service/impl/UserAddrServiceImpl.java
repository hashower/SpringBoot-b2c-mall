package cn.luxun.mall.service.impl;

import cn.luxun.mall.entity.UserAddr;
import cn.luxun.mall.mapper.UserAddrMapper;
import cn.luxun.mall.service.UserAddrService;
import cn.luxun.mall.vo.ResultVo;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserAddrServiceImpl extends ServiceImpl<UserAddrMapper, UserAddr> implements UserAddrService {

	@Override
	public ResultVo getUserAddrsByUserId(Integer userId) {

		// 条件构造器
		LambdaQueryWrapper<UserAddr> queryWrapper = new LambdaQueryWrapper<>();

		// 添加条件
		queryWrapper.eq(UserAddr::getUserId, userId);
		queryWrapper.eq(UserAddr::getStatus, 1);
		List<UserAddr> list = this.list(queryWrapper);
		return ResultVo.success(list);
	}
}
