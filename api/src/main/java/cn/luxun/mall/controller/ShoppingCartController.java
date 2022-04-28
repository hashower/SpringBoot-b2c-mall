package cn.luxun.mall.controller;

import cn.luxun.mall.entity.ShoppingCart;
import cn.luxun.mall.service.ShoppingCartService;
import cn.luxun.mall.vo.ResultVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.transaction.ReactiveTransaction;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/shoppingcart")
@Api(value = "提供购物车业务相关接口", tags = "购物车管理")
public class ShoppingCartController {


	private final ShoppingCartService shoppingCartService;

	public ShoppingCartController(ShoppingCartService shoppingCartService) {
		this.shoppingCartService = shoppingCartService;
	}

	/**
	 * 获取购物车所有商品
	 *
	 * @return
	 */
	@ApiOperation("购物车查询接口")
	@GetMapping("/list/{userId}")
	public ResultVo getShoppingCartByUserId(@PathVariable("userId") String userId) {
		return shoppingCartService.getShoppingCartByUserId(userId);
	}

	/**
	 * 添加商品到购物车
	 *
	 * @return
	 */
	@ApiOperation("添加购物车接口")
	@GetMapping("/add")
	public ResultVo saveProductToShoppingCart(@RequestBody ShoppingCart shoppingCart, @RequestHeader("token") String token) {
		return shoppingCartService.saveProductToShoppingCart(shoppingCart);
	}

	/**
	 * 添加商品到购物车
	 *
	 * @param cartId
	 * @param cartNum
	 * @return
	 */
	@ApiOperation("修改购物车信息")
	@PutMapping("/update/{cid}/{cnum}")
	public ResultVo updateCartByCartIdAndCartNum(@PathVariable("cid") Integer cartId,
	                                             @PathVariable("cnum") String cartNum) {
		return shoppingCartService.updateCartByCartIdAndCartNum(cartId, cartNum);
	}

	/**
	 * 根据订单id获取订单列表
	 *
	 * @param cartIds
	 * @return
	 */
	@ApiOperation("获取已选购物车信息的接口")
	@GetMapping("/listbycids")
	public ResultVo getShoppingCartsByCartIds(@RequestParam("cartIds") List<Integer> cartIds) {
		return shoppingCartService.getShoppingCartsByCartIds(cartIds);
	}
}
