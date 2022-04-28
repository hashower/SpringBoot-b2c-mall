package cn.luxun.mall.service;

import cn.luxun.mall.vo.ResultVo;

public interface ProductCommentsService {

	/**
	 * 根据商品id获取商品评论
	 *
	 * @param productId
	 * @return
	 */
	ResultVo getProductCommentsByProductId(String productId);
}
