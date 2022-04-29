package cn.luxun.mall.service;


import cn.luxun.mall.entity.ProductParams;
import com.baomidou.mybatisplus.extension.service.IService;

public interface ProductParamsService extends IService<ProductParams> {

	/**
	 * 根据商品id获得商品信息
	 *
	 * @param pid
	 * @return
	 */
	ProductParams getProductParamsByProductId(String pid);
}
