package cn.luxun.mall.service.impl;

import cn.luxun.mall.entity.Category;
import cn.luxun.mall.mapper.CategoryMapper;
import cn.luxun.mall.service.CategoryService;
import cn.luxun.mall.vo.CategoryVo;
import cn.luxun.mall.vo.ResultVo;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {

	@Override
	public ResultVo getCategoriesList() {

		List<Category> CategoriesList = this.list();

		List<CategoryVo> categoryVos = new ArrayList<>();
		for (Category category : CategoriesList) {
			if (category.getParentId() == 0) {
				CategoryVo categoryVo = new CategoryVo();
				BeanUtils.copyProperties(category, categoryVo);
				categoryVo.setCategories(findCategoriesNextTree(CategoriesList, (category.getId())));
				categoryVos.add(categoryVo);
			}
		}
		return ResultVo.success(categoryVos);
	}

	private List<CategoryVo> findCategoriesNextTree(List<Category> CategoriesList, Integer parentId) {
		List<CategoryVo> CategoryVos = new ArrayList<>();

		for (Category category : CategoriesList) {
			CategoryVo categoryVo = new CategoryVo();
			if (category.getParentId() == parentId) {
				BeanUtils.copyProperties(category, categoryVo);
				categoryVo.setCategories(findCategoriesNextTree(CategoriesList, category.getId()));
				CategoryVos.add(categoryVo);
			}
		}
		return CategoryVos;
	}
}
