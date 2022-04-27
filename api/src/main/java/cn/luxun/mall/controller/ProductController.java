package cn.luxun.mall.controller;

import cn.luxun.mall.service.ProductService;
import cn.luxun.mall.vo.ResultVo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/product")
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
	public ResultVo getAllRecommendProducts() {
		return productService.getAllRecommendProducts();
	}
}
