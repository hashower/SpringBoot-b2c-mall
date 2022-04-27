package cn.luxun.mall.service.impl;

import cn.luxun.mall.entity.ProductImg;
import cn.luxun.mall.mapper.ProductImgMapper;
import cn.luxun.mall.service.ProductImgService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ProductImgServiceImpl extends ServiceImpl<ProductImgMapper, ProductImg> implements ProductImgService {

	@Override
	public List<ProductImg> getProductImgById(String id) {

		// 条件构造器
		LambdaQueryWrapper<ProductImg> queryWrapper = new LambdaQueryWrapper<>();

		// 添加条件
		queryWrapper.eq(ProductImg::getItemId, id);

		List<ProductImg> list = this.list(queryWrapper);
		return list;
	}
}
