package cn.luxun.mall.vo;

import cn.luxun.mall.entity.ProductImg;
import cn.luxun.mall.entity.ProductSku;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductVo {

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

	// 在查询商品的时候，关联查询商品图片信息
	private List<ProductImg> imgs;

	// 在查询商品的时候，关联查询商品套餐信息
	private List<ProductSku> skus;

}