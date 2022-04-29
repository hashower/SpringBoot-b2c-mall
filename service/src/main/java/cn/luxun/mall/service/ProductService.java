package cn.luxun.mall.service;

import cn.luxun.mall.entity.Product;
import cn.luxun.mall.entity.ProductSku;
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
	 *
	 * @param root_category_id
	 * @return
	 */
	List<Product> getProductsByCategoryId(String root_category_id);


	/**
	 * 获取ProductVo
	 *
	 * @param productList
	 * @return
	 */
	List<ProductVo> getProductVoByProduct(List<Product> productList);

	/**
	 * 根据产品id获取产品信息
	 *
	 * @param id
	 * @return
	 */
	Product getProductByProductId(String productId);

	/**
	 * 商品基本信息查询接口
	 *
	 * @param productId
	 * @return
	 */
	ResultVo getProductBasicInfoByProductId(String productId);

	/**
	 * 商品参数信息查询接口
	 *
	 * @param productId
	 * @return
	 */
	ResultVo getProductParamsByProductId(String productId);

	/**
	 * 三级分类id分页查询商品信息
	 * 根据类别查询商品接口
	 *
	 * @param cid
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	ResultVo getProductsbyCategoryId(int cid, int pageNum, int pageSize);

	/**
	 * 根据类别查询商品品牌
	 *
	 * @param cid
	 * @return
	 */
	ResultVo getBrandsbyCategoryId(String cid);
}
