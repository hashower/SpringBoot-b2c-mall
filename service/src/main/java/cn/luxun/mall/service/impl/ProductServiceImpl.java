package cn.luxun.mall.service.impl;

import cn.luxun.mall.entity.Product;
import cn.luxun.mall.entity.ProductImg;
import cn.luxun.mall.mapper.ProductMapper;
import cn.luxun.mall.service.ProductImgService;
import cn.luxun.mall.service.ProductService;
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
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product> implements ProductService {

	public final ProductImgService productImgService;

	public ProductServiceImpl(ProductImgService productImgService) {
		this.productImgService = productImgService;
	}

	@Override
	public ResultVo getAllRecommendProducts() {

		List<Product> products = this.list();
		List<ProductVo> productVos = this.getProductVoByProduct(products);
		return ResultVo.success(productVos);
	}

	@Override
	public List<Product> getProductsByCategoryId(String root_category_id) {

		String limit = "6";

		// 条件构造器
		LambdaQueryWrapper<Product> queryWrapper = new LambdaQueryWrapper<>();

		// 添加条件
		queryWrapper.eq(Product::getRootCategoryId, root_category_id);
		queryWrapper.orderByAsc(Product::getSoldNum);
		queryWrapper.last("limit " + limit);

		return this.list(queryWrapper);
	}

	public List<ProductVo> getProductVoByProduct(List<Product> productList) {
		return productList.stream().map((item) -> {

			ProductVo productVo = new ProductVo();
			// 拷贝
			BeanUtils.copyProperties(item, productVo);

			// 根据产品id获得产品图片列表
			List<ProductImg> productImgList = productImgService.getProductImgById(item.getId());

			// 设置图片
			productVo.setImgs(productImgList);
			return productVo;
		}).collect(Collectors.toList());
	}
}
