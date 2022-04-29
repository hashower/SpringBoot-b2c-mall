package cn.luxun.mall.controller;

import cn.luxun.mall.entity.Orders;
import cn.luxun.mall.service.OrderService;
import cn.luxun.mall.vo.ResultVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
	@ApiOperation("添加订单接口")
	public ResultVo addOrderByParams(@RequestParam List<Integer> cartIds, @RequestBody Orders order) {
		return orderService.addOrderToShoppingCart(cartIds, order);
	}

	/**
	 * 根据订单id查看订单状态
	 *
	 * @param orderId
	 * @return
	 */
	@PostMapping("/status/{orderId}")
	@ApiOperation("查询订单状态接口")
	public ResultVo getOrderStatusByOrderId(@PathVariable String orderId) {
		return orderService.getOrderStatusByOrderId(orderId);
	}

	/**
	 * 根据用户id和状态获得订单分页
	 *
	 * @param userId
	 * @param status
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	@GetMapping("/list")
	@ApiOperation("订单查询接口")
	public ResultVo getAllOrdersPageByUserIdAndStatus(String userId, String status, int pageNum, int pageSize) {
		return orderService.getAllOrdersPageByUserIdAndStatus(userId, status, pageNum, pageSize);

	}
}
