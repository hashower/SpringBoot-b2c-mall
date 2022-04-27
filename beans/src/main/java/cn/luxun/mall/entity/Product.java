package cn.luxun.mall.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {

	// 商品主键id
	private String id;

	// 商品名称
	private String name;

	// 分类id
	private Integer categoryId;

	// 一级分类外键id 一级分类id，用于优化查询
	private Integer rootCategoryId;

	// 销量 累计销售
	private Integer soldNum;

	// 默认是1，表示正常状态, -1表示删除, 0下架 默认是1，表示正常状态, -1表示删除, 0下架
	private Integer status;

	// 创建时间
	private Date createTime;

	// 更新时间
	private Date updateTime;

	// 商品内容
	private String content;

}