package cn.luxun.mall.controller;

import cn.luxun.mall.service.CategoryService;
import cn.luxun.mall.vo.ResultVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/category")
@Api(value = "提供分类增删改查接口", tags = "分类管理")
public class CategoryController {

	public final CategoryService categoryService;

	public CategoryController(CategoryService categoryService) {
		this.categoryService = categoryService;
	}

	/**
	 * 获取分类列表
	 *
	 * @return
	 */
	@GetMapping("/list")
	@ApiOperation("商品分类查询接口")
	public ResultVo listCatetory() {
		return categoryService.getCategoriesList();
	}
}
