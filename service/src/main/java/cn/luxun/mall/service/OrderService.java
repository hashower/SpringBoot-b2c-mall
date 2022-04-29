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

	/**
	 * 根据订单id查看订单状态
	 *
	 * @param orderId
	 * @return
	 */
	ResultVo getOrderStatusByOrderId(String orderId);

	/**
	 * 根据用户id和状态获得订单分页
	 *
	 * @param userId
	 * @param status
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	ResultVo getAllOrdersPageByUserIdAndStatus(String userId, String status, int pageNum, int pageSize);
}
