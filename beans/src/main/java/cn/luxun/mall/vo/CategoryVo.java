package cn.luxun.mall.vo;

import cn.luxun.mall.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryVo {

	// 分类id
	private Integer id;

	// 分类名称
	private String name;

	// 分类等级
	private Integer level;

	// 父级分类id
	private Integer parentId;

	// 分类图标
	private String icon;

	// 分类口号
	private String slogan;

	// 分类图
	private String pic;

	// 分类背景颜色
	private String bg_color;

	// 实现首页的类别显示
	private List<CategoryVo> categories;

	//实现首页分类商品推荐
	private List<ProductVo> products;
}
