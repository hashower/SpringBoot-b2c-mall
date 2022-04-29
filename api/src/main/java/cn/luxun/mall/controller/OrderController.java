package cn.luxun.mall.controller;

import cn.luxun.mall.entity.Orders;
import cn.luxun.mall.service.OrderService;
import cn.luxun.mall.vo.ResultVo;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/order")
@Api(value = "提供订单相关的操作接口", tags = "订单管理")
public class OrderController {

	private final OrderService orderService;

	public OrderController(OrderService orderService) {
		this.orderService = orderService;
	}

	@PostMapping("/add")
	public ResultVo addOrderByParams(@RequestBody Orders order) {
		List<Integer> cartIds = new ArrayList<>();
		cartIds.add(5);
		cartIds.add(7);
		return orderService.addOrderToShoppingCart(cartIds, order);

	}
}
