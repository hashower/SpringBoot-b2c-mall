package cn.luxun.mall.controller;

import cn.luxun.mall.service.ProductService;
import cn.luxun.mall.vo.ResultVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/product")
@Api(value = "提供产品增删改查接口", tags = "产品管理")
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
}
