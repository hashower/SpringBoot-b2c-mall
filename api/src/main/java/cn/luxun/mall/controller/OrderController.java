package cn.luxun.mall.controller;

import cn.luxun.mall.entity.Orders;
import cn.luxun.mall.service.OrderService;
import cn.luxun.mall.vo.ResultVo;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;

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
	public ResultVo addOrderByParams(@RequestParam List<Integer> cartIds, @RequestBody Orders order) {
		return orderService.addOrderToShoppingCart(cartIds, order);
	}
}
