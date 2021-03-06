package cn.luxun.mall.controller;

import cn.luxun.mall.entity.Product;
import cn.luxun.mall.service.ProductCommentsService;
import cn.luxun.mall.service.ProductService;
import cn.luxun.mall.vo.ResultVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.models.auth.In;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/product")
@Api(value = "提供商品信息相关的接口", tags = "商品管理")
public class ProductController {

	public final ProductService productService;
	public final ProductCommentsService productCommentsService;

	public ProductController(ProductService productService, ProductCommentsService productCommentsService) {
		this.productService = productService;
		this.productCommentsService = productCommentsService;
	}

	/**
	 * 获取推荐商品
	 *
	 * @return
	 */
	@GetMapping("/getAllRecommendProducts")
	@ApiOperation("新品推荐接口")
	public ResultVo getAllRecommendProducts() {
		return productService.getAllRecommendProducts();
	}

	/**
	 * 根据产品id获取产品信息
	 *
	 * @param productId
	 * @return
	 */
	@ApiOperation("根据产品id获取产品信息")
	@PostMapping("/{id}")
	public Product getProdductById(@PathVariable("id") String productId) {
		return productService.getProductByProductId(productId);
	}

	/**
	 * 商品基本信息查询接口
	 *
	 * @param productId
	 * @return
	 */
	@ApiOperation("商品基本信息查询接口")
	@GetMapping("/detail-info/{productId}")
	public ResultVo getProductBasicInfoByProductId(@PathVariable("productId") String productId) {
		return productService.getProductBasicInfoByProductId(productId);
	}

	/**
	 * 商品参数信息查询接口
	 *
	 * @param productId
	 * @return
	 */
	@ApiOperation("商品参数信息查询接口")
	@GetMapping("/detail-params/{productId}")
	public ResultVo getProductParamsByProductId(@PathVariable("productId") String productId) {
		return productService.getProductParamsByProductId(productId);
	}

	/**
	 * 根据商品id查询商品信息
	 *
	 * @param productId
	 * @return
	 */
	@ApiOperation("根据商品id获取商品评论")
	@GetMapping("/detail-comments/{productId}/{pageNum}/{pageSize}")
	public ResultVo getProductCommentsByProductId(@PathVariable("productId") String productId, @PathVariable("pageNum") int pageNum, @PathVariable("pageSize") int pageSize) {
		return productCommentsService.getProductCommentsByProductId(productId, pageNum, pageSize);
	}

	/**
	 * 根据商品id查询商品评价统计
	 *
	 * @param productId
	 * @return
	 */
	@ApiOperation("商品评价统计查询接口")
	@GetMapping("/detail-commentsCount/{productId}/}")
	public ResultVo getProductCommentCountByProductId(@PathVariable("productId") String productId) {
		return productCommentsService.getProductCommentCountByProductId(productId);
	}


	/**
	 * 三级分类id分页查询商品信息
	 * 根据类别查询商品接口
	 *
	 * @param cid
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	@ApiOperation("根据类别查询商品接口")
	@GetMapping("/listbycid/{cid}")
	public ResultVo getProductsbyCategoryId(@PathVariable("cid") int cid, int pageNum, int pageSize) {
		return productService.getProductsbyCategoryId(cid, pageNum, pageSize);
	}

	/**
	 * 根据类别查询商品品牌
	 *
	 * @param cid
	 * @return
	 */
	@ApiOperation("根据类别查询商品品牌接口")
	@GetMapping("/listbrans/{cid}")
	public ResultVo getBrandsByCategoryId(@PathVariable("cid") String cid) {
		return productService.getBrandsByCategoryId(cid);
	}

	/**
	 * 根据关键词查询商品
	 *
	 * @param keyword
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	@ApiOperation("根据关键词查询商品接口")
	@GetMapping("/listbykeyword")
	public ResultVo getProductsByProductName(String keyword, int pageNum, int pageSize) {
		return productService.getProductsByProductName(keyword, pageNum, pageSize);
	}

	/**
	 * 根据关键词查询商品品牌
	 *
	 * @param keyword
	 * @return
	 */
	@ApiOperation("根据关键词查询商品品牌接口")
	@GetMapping("/listbrands-keyword")
	public ResultVo getBrandsByProductName(String keyword) {
		return productService.getBrandsByProductName(keyword);
	}


}
