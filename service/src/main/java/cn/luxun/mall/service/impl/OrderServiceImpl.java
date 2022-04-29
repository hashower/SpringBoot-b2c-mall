package cn.luxun.mall.service.impl;

import cn.luxun.mall.entity.OrderItem;
import cn.luxun.mall.entity.Orders;
import cn.luxun.mall.entity.ProductSku;
import cn.luxun.mall.mapper.OrderMapper;
import cn.luxun.mall.service.OrderItemService;
import cn.luxun.mall.service.OrderService;
import cn.luxun.mall.service.ShoppingCartService;
import cn.luxun.mall.vo.OrdersVO;
import cn.luxun.mall.vo.ResultVo;
import cn.luxun.mall.vo.ShoppingCartVo;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Orders> implements OrderService {

	private final ShoppingCartService shoppingCartService;
	private final OrderItemService orderItemService;

	public OrderServiceImpl(ShoppingCartService shoppingCartService, OrderItemService orderItemService) {
		this.shoppingCartService = shoppingCartService;
		this.orderItemService = orderItemService;
	}

	@Override
	@Transactional
	public ResultVo addOrderToShoppingCart(List<Integer> cartIds, Orders order) {
		// 根据 cartIds查询当前订单中关联的购物车记录详情  包括库存

		boolean flag = true;
		StringBuilder AllProductName = new StringBuilder();

		// 校验库存：根据cids查询当前订单中关联的购物车记录详情（包括库存）
		List<ShoppingCartVo> carts = shoppingCartService.getShoppingCartsByCartIds(cartIds);
		for (ShoppingCartVo cart : carts) {
			if (Integer.parseInt(cart.getNumber()) > cart.getProductSku().getStock()) {
				flag = false;
				break;
			}
			// 获取所有商品名称以，分割拼接成字符串
			AllProductName.append(cart.getProductSku().getName());
			// System.out.println(AllProductName);
		}

		if (flag) {

			order.setUntitled(AllProductName.toString());
			order.setCreateTime(new Date());
			order.setStatus("1");
			// 生成订单编号
			String orderId = UUID.randomUUID().toString().replace("-", "");
			order.setId(orderId);
			// 保存订单
			this.save(order);


			// 生成商品快照
			for (ShoppingCartVo cart : carts) {
				int cnum = Integer.parseInt(cart.getNumber());
				String itemId = System.currentTimeMillis() + "" + new Random().nextInt(8999) + 1000;
				OrderItem orderItem = new OrderItem(itemId, orderId, cart.getProductId(), cart.getProductSku().getName(), cart.getProductImg().getUrl(), cart.getSkuId(), cart.getProductSku().getName(), new BigDecimal(cart.getProductSku().getSellPrice()), cnum, new BigDecimal(cart.getProductSku().getSellPrice() * cnum), new Date(), new Date(), 0);
				System.out.println("@@@" + orderItem);
				orderItemService.save(orderItem);
			}

			// 扣减库存：根据套餐ID修改套餐库存量
			for (ShoppingCartVo cart : carts) {
				String skuId = cart.getSkuId();
				int newStock = cart.getProductSku().getStock() - Integer.parseInt(cart.getNumber());
				ProductSku productSku = new ProductSku();
				productSku.setId(skuId);
				productSku.setStock(newStock);
			}
			Map<String, String> map = new HashMap<>();
			// 删除购物车：当购物车中的记录购买成功之后，购物车中对应做删除操作
			for (int cartId : cartIds) {
				shoppingCartService.deleteCartByCartId(cartId);
			}
			map.put("orderId", orderId);
			map.put("productName", AllProductName.toString());
			return ResultVo.success(map);
		} else {
			// 表示库存不足
			return null;
		}


	}

	@Override
	public ResultVo getOrderStatusByOrderId(String orderId) {
		LambdaQueryWrapper<Orders> queryWrapper = new LambdaQueryWrapper<>();
		queryWrapper.eq(Orders::getId, orderId);
		Orders order = this.getOne(queryWrapper);
		return ResultVo.success(order.getStatus());
	}

	@Override
	public ResultVo getAllOrdersPageByUserIdAndStatus(String userId, String status, int pageNum, int pageSize) {
		LambdaQueryWrapper<Orders> queryWrapper = new LambdaQueryWrapper<>();
		queryWrapper.eq(Orders::getUserId, userId);
		queryWrapper.eq(Orders::getStatus, status);
		Page<Orders> pageInfo = new Page<>(pageNum, pageSize);
		Page<Orders> page = this.page(pageInfo, queryWrapper);


		List<OrdersVO> ordersVOList = page.getRecords().stream().map((item) -> {
			OrdersVO ordersVO = new OrdersVO();
			BeanUtils.copyProperties(item, ordersVO);
			ordersVO.setOrderItems(orderItemService.getOrderItemsByOrderId(item.getId()));
			return ordersVO;
		}).collect(Collectors.toList());
		return ResultVo.success(ordersVOList);
	}


}