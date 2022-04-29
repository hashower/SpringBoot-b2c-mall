package cn.luxun.mall.service.impl;

import cn.luxun.mall.entity.Product;
import cn.luxun.mall.entity.ProductImg;
import cn.luxun.mall.entity.ProductParams;
import cn.luxun.mall.entity.ProductSku;
import cn.luxun.mall.mapper.ProductMapper;
import cn.luxun.mall.service.ProductImgService;
import cn.luxun.mall.service.ProductParamsService;
import cn.luxun.mall.service.ProductService;
import cn.luxun.mall.service.ProductSkuService;
import cn.luxun.mall.vo.ProductVo;
import cn.luxun.mall.vo.ResultVo;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product> implements ProductService {

	private final ProductImgService productImgService;
	private final ProductSkuService productSkuService;
	private final ProductParamsService productParamsService;

	public ProductServiceImpl(ProductImgService productImgService, ProductSkuService productSkuService, ProductParamsService productParamsService) {
		this.productImgService = productImgService;
		this.productSkuService = productSkuService;
		this.productParamsService = productParamsService;
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

	@Override
	public Product getProductByProductId(String productId) {

		// 条件构造器
		LambdaQueryWrapper<Product> queryWrapper = new LambdaQueryWrapper<>();

		// 添加条件
		queryWrapper.eq(Product::getId, productId);
		Product product = this.getOne(queryWrapper);
		return product;
	}

	@Override
	public ResultVo getProductBasicInfoByProductId(String productId) {

		// 商品信息
		Product product = this.getProductByProductId(productId);
		List<Product> productList = new ArrayList<>();
		productList.add(product);


		// 商品图片
		List<ProductImg> productImgList = productImgService.getProductImgById(productId);

		// 商品套餐
		List<ProductSku> productSkusList = productSkuService.getAllProductSkusByProductId(productId);

		HashMap<String, Object> map = new HashMap<>();
		map.put("product", productList);
		map.put("productSkus", productSkusList);
		map.put("productImgs", productImgList);
		return ResultVo.success(map);
	}

	@Override
	public ResultVo getProductParamsByProductId(String productId) {

		// 条件构造器
		LambdaQueryWrapper<ProductParams> queryWrapper = new LambdaQueryWrapper<>();

		// 添加条件
		queryWrapper.eq(ProductParams::getProductId, productId);
		ProductParams productParams = productParamsService.getOne(queryWrapper);
		return ResultVo.success(productParams);
	}

	@Override
	public ResultVo getProductsbyCategoryId(int cid, int pageNum, int pageSize) {
		LambdaQueryWrapper<Product> queryWrapper = new LambdaQueryWrapper<>();
		queryWrapper.eq(Product::getCategoryId, cid);

		List<Product> list = this.list(queryWrapper);
		Map<String, Object> map = new HashMap<>();
		map.put("count", list.size());
		map.put("list", list);
		return ResultVo.success(map);
	}

	@Override
	public ResultVo getBrandsbyCategoryId(String cid) {
		LambdaQueryWrapper<Product> productLambdaQueryWrapper = new LambdaQueryWrapper<>();
		productLambdaQueryWrapper.eq(Product::getCategoryId, cid);

		List<Product> list = this.list(productLambdaQueryWrapper);
		List<String> brandList = new ArrayList<>();
		for (Product product : list) {

			ProductParams productParams = productParamsService.getProductParamsByProductId(product.getId());
			String brands = productParams.getBrand();

			if (!brandList.contains(brands)) {
				brandList.add(brands);
			}
		}

		return ResultVo.success(brandList);
	}
}
