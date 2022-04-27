package cn.luxun.mall.service;

import cn.luxun.mall.vo.ResultVo;

public interface CategoryService {

	/**
	 * 获取分类列表
	 *
	 * @return
	 */
	ResultVo getAllCategories();

	/**
	 * 分类推荐新品列表
	 *
	 * @return
	 */
	ResultVo getRecommendProductsByCategory();
}
