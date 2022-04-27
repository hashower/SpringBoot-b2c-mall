package cn.luxun.mall.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Category {

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

}
