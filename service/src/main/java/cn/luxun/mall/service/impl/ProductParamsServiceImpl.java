package cn.luxun.mall.service.impl;

import cn.luxun.mall.entity.ProductParams;
import cn.luxun.mall.mapper.ProductParamsMapper;
import cn.luxun.mall.service.ProductParamsService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class ProductParamsServiceImpl extends ServiceImpl<ProductParamsMapper, ProductParams> implements ProductParamsService {

	@Override
	public ProductParams getProductParamsByProductId(String pid) {
		LambdaQueryWrapper<ProductParams> queryWrapper = new LambdaQueryWrapper<>();
		queryWrapper.eq(ProductParams::getProductId, pid);

		return this.getOne(queryWrapper);
	}
}
