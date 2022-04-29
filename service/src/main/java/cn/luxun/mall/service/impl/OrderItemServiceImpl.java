package cn.luxun.mall.service.impl;


import cn.luxun.mall.entity.OrderItem;
import cn.luxun.mall.mapper.OrderItemMapper;
import cn.luxun.mall.service.OrderItemService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sun.org.apache.xpath.internal.operations.Or;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderItemServiceImpl extends ServiceImpl<OrderItemMapper, OrderItem> implements OrderItemService {

	@Override
	public List<OrderItem> getOrderItemsByOrderId(String orderId) {
		LambdaQueryWrapper<OrderItem> queryWrapper = new LambdaQueryWrapper<>();
		queryWrapper.eq(OrderItem::getOrderId, orderId);
		return this.list(queryWrapper);
	}
}
