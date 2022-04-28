package cn.luxun.mall.service;

import cn.luxun.mall.entity.UserAddr;
import cn.luxun.mall.vo.ResultVo;
import com.baomidou.mybatisplus.extension.service.IService;

public interface UserAddrService extends IService<UserAddr> {

	/**
	 * 根据用户id获得所有用户地址
	 *
	 * @param id
	 * @return
	 */
	ResultVo getUserAddrsByUserId(Integer userId);
}
