package cn.luxun.mall.service;

import cn.luxun.mall.entity.OrderItem;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface OrderItemService extends IService<OrderItem> {

	/**
	 * 根据订单id获得订单项
	 *
	 * @param orderId
	 * @return
	 */
	List<OrderItem> getOrderItemsByOrderId(String orderId);
}
