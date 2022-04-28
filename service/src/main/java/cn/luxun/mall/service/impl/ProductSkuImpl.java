package cn.luxun.mall.service.impl;

import cn.luxun.mall.entity.ProductSku;
import cn.luxun.mall.mapper.ProductSkuMapper;
import cn.luxun.mall.service.ProductSkuService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductSkuImpl extends ServiceImpl<ProductSkuMapper, ProductSku> implements ProductSkuService {

	@Override
	public List<ProductSku> getAllProductSkusByProductId(String productId) {

		LambdaQueryWrapper<ProductSku> queryWrapper = new LambdaQueryWrapper<>();
		queryWrapper.eq(ProductSku::getProductId, productId);
		List<ProductSku> productSkusList = this.list(queryWrapper);
		return productSkusList;
	}
}
