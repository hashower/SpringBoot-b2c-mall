package cn.luxun.mall.controller;

import cn.luxun.mall.entity.Product;
import cn.luxun.mall.service.ProductService;
import cn.luxun.mall.vo.ResultVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/product")
@Api(value = "提供商品信息相关的接口", tags = "商品管理")
public class ProductController {

	public final ProductService productService;

	public ProductController(ProductService productService) {
		this.productService = productService;
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
}
