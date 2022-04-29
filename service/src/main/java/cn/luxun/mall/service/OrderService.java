package cn.luxun.mall.service;

import cn.luxun.mall.entity.Orders;
import cn.luxun.mall.vo.ResultVo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface OrderService extends IService<Orders> {

	/**
	 * 添加商品到购物车
	 *
	 * @param userId
	 * @param cartIds
	 * @return
	 */
	ResultVo addOrderToShoppingCart(List<Integer> cartIds, Orders order);
}
