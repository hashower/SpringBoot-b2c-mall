package cn.luxun.mall.entity;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ShoppingCart {

	// 主键
	private Integer id;

	// 商品ID
	private String productId;

	// skuID
	private String skuId;

	// 用户ID
	private String userId;

	// 购物车商品数量
	private String number;

	// 添加购物车时间
	private String createTime;

	// 添加购物车时商品价格
	private BigDecimal productPrice;

	// 选择的套餐的属性
	private String skuProps;
}