package cn.luxun.mall.service;

import cn.luxun.mall.entity.Product;
import cn.luxun.mall.vo.ProductVo;
import cn.luxun.mall.vo.ResultVo;

import java.util.List;

public interface ProductService {

	/**
	 * 获取推荐商品
	 *
	 * @return
	 */
	ResultVo getAllRecommendProducts();

	/**
	 * 获取每个分类下销量前6的商品
	 * @param root_category_id
	 * @return
	 */
	List<Product> getProductsByCategoryId(String root_category_id);


	/**
	 * 获取ProductVo
	 * @param productList
	 * @return
	 */
	List<ProductVo> getProductVoByProduct(List<Product> productList);
}
