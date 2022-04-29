package cn.luxun.mall.service;

import cn.luxun.mall.entity.ShoppingCart;
import cn.luxun.mall.vo.ResultVo;
import cn.luxun.mall.vo.ShoppingCartVo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface ShoppingCartService extends IService<ShoppingCart> {

	/**
	 * 获取购物车所有商品
	 *
	 * @return
	 */
	ResultVo getAllShoppingCart(Integer userId);

	/**
	 * 添加商品到购物车
	 *
	 * @return
	 */
	ResultVo saveProductToShoppingCart(ShoppingCart shoppingCart);

	/**
	 * 根据用户id查询当前用户的购物车信息
	 *
	 * @param userId
	 * @return
	 */
	ResultVo getShoppingCartByUserId(String userId);

	/**
	 * 添加商品到购物车
	 *
	 * @param cartId
	 * @param cartNum
	 * @return
	 */
	ResultVo updateCartByCartIdAndCartNum(Integer cartId, String cartNum);

	/**
	 * 根据订单id获取订单列表
	 *
	 * @param cartIds
	 * @return
	 */
	List<ShoppingCartVo> getShoppingCartsByCartIds(List<Integer> cartIds);

	/**
	 * 根据订单id删除订单
	 *
	 * @param cartId
	 * @return
	 */
	ResultVo deleteCartByCartId(int cartId);
}
