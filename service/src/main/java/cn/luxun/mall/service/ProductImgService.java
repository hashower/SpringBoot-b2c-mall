package cn.luxun.mall.service;

import cn.luxun.mall.entity.ProductImg;

import java.util.List;


public interface ProductImgService {

	/**
	 * 根据商品id查询当前商品的图片信息
	 *
	 * @param item_id
	 * @return
	 */
	List<ProductImg> getProductImgById(String item_id);


}
