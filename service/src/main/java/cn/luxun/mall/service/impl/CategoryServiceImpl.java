package cn.luxun.mall.service.impl;

import cn.luxun.mall.entity.Category;
import cn.luxun.mall.entity.Product;
import cn.luxun.mall.mapper.CategoryMapper;
import cn.luxun.mall.service.CategoryService;
import cn.luxun.mall.service.ProductImgService;
import cn.luxun.mall.service.ProductService;
import cn.luxun.mall.vo.CategoryVo;
import cn.luxun.mall.vo.ProductVo;
import cn.luxun.mall.vo.ResultVo;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {

	public final ProductService productService;
	public final ProductImgService productImgService;

	public CategoryServiceImpl(ProductService productService, ProductImgService productImgService) {
		this.productService = productService;
		this.productImgService = productImgService;
	}

	@Override
	public ResultVo getAllCategories() {

		List<Category> CategoriesList = this.list();

		List<CategoryVo> categoryVos = new ArrayList<>();
		for (Category category : CategoriesList) {
			// 判断是否为第一层分类
			if (category.getParentId() == 0) {
				CategoryVo categoryVo = new CategoryVo();
				BeanUtils.copyProperties(category, categoryVo);
				// 递归分类
				categoryVo.setCategories(findCategoriesNextTree(CategoriesList, (category.getId())));
				categoryVos.add(categoryVo);
			}
		}
		return ResultVo.success(categoryVos);
	}

	@Override
	public ResultVo getRecommendProductsByCategory() {
		LambdaQueryWrapper<Category> queryWrapper = new LambdaQueryWrapper<>();
		queryWrapper.eq(Category::getLevel, 1);
		List<Category> categoryList = this.list(queryWrapper);

		List<CategoryVo> categoryVoList = categoryList.stream().map((item) -> {

			CategoryVo categoryVo = new CategoryVo();

			// 拷贝
			BeanUtils.copyProperties(item, categoryVo);

			// 根据分类Id获取指定数量的产品
			List<Product> productsByCategory = productService.getProductsByCategoryId(item.getId() + "");

			// 通过product的分类id获取imgs并拷贝给vo对象
			List<ProductVo> productVos = productService.getProductVoByProduct(productsByCategory);

			categoryVo.setProducts(productVos);
			return categoryVo;

		}).collect(Collectors.toList());

		return ResultVo.success(categoryVoList);
	}

	/**
	 * 递归分类
	 * @param CategoriesList
	 * @param parentId
	 * @return
	 */
	private List<CategoryVo> findCategoriesNextTree(List<Category> CategoriesList, Integer parentId) {
		List<CategoryVo> CategoryVos = new ArrayList<>();

		// 遍历传来的分类列表
		for (Category category : CategoriesList) {
			CategoryVo categoryVo = new CategoryVo();

			// 如果值和传来的父级id相等
			if (category.getParentId() == parentId) {

				// 拷贝
				BeanUtils.copyProperties(category, categoryVo);

				// 递归
				categoryVo.setCategories(findCategoriesNextTree(CategoriesList, category.getId()));
				CategoryVos.add(categoryVo);
			}
		}
		return CategoryVos;
	}
}
