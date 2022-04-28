package cn.luxun.mall.entity;

import lombok.Data;

import java.util.Date;

@Data
public class ProductParams {

	// 商品参数id
	private String id;

	// 商品id
	private String productId;

	// 产地，例：中国江苏
	private String productPlace;

	// 保质期，例：180天
	private String footPeriod;

	// 品牌名，例：三只大灰狼
	private String brand;

	// 生产厂名，例：大灰狼工厂
	private String factoryName;

	// 生产厂址，例：大灰狼生产基地
	private String factoryAddress;

	// 包装方式，例：袋装
	private String packagingMethod;

	// 规格重量，例：35g
	private String weight;

	// 存储方法，例：常温5~25°
	private String storageMethod;

	// 食用方式，例：开袋即食
	private String eatMethod;

	// 创建时间
	private Date createTime;

	// 更新时间
	private Date updateTime;

}