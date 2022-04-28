package cn.luxun.mall.service;

import cn.luxun.mall.entity.ProductSku;

import java.util.List;

public interface ProductSkuService {

	/**
	 * 根据商品id查询当前商品的套餐
	 *
	 * @param productId
	 * @return
	 */
	List<ProductSku> getAllProductSkusByProductId(String productId);
}
