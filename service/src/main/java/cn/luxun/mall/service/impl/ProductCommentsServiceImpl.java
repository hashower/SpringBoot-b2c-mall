package cn.luxun.mall.service.impl;

import cn.luxun.mall.entity.ProductComments;
import cn.luxun.mall.entity.User;
import cn.luxun.mall.mapper.ProductCommentsMapper;
import cn.luxun.mall.service.ProductCommentsService;
import cn.luxun.mall.service.UserService;
import cn.luxun.mall.vo.ProductCommentsVo;
import cn.luxun.mall.vo.ResultVo;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductCommentsServiceImpl extends ServiceImpl<ProductCommentsMapper, ProductComments> implements ProductCommentsService {

	public final UserService userService;

	public ProductCommentsServiceImpl(UserService userService) {
		this.userService = userService;
	}

	@Override
	public ResultVo getProductCommentsByProductId(String productId, int pageNum, int pageSize) {

		// 分页构造器
		Page<ProductComments> pageInfo = new Page<>(pageNum, pageSize);
		Page<ProductCommentsVo> productCommentsVoPage = new Page<>();

		// 条件构造器
		LambdaQueryWrapper<ProductComments> queryWrapper = new LambdaQueryWrapper<>();

		// 添加条件
		queryWrapper.eq(ProductComments::getProductId, productId);
		this.page(pageInfo, queryWrapper);

		BeanUtils.copyProperties(pageInfo, productCommentsVoPage, "records");

		// 处理对象 给每个comments对象加进user对象
		List<ProductCommentsVo> productCommentsVoList = pageInfo.getRecords().stream().map((item) -> {
			ProductCommentsVo productCommentsVo = new ProductCommentsVo();

			// 拷贝
			BeanUtils.copyProperties(item, productCommentsVo);

			// 根据用户id获得用户信息
			User user = userService.getUserByUserId(item.getUserId());
			productCommentsVo.setUser(user);

			return productCommentsVo;
		}).collect(Collectors.toList());
		productCommentsVoPage.setRecords(productCommentsVoList);

		return ResultVo.success(productCommentsVoPage);
	}

	@Override
	public ResultVo getProductCommentCountByProductId(String productId) {

		// 查询当前商品评价的总数
		LambdaQueryWrapper<ProductComments> queryWrapper = new LambdaQueryWrapper<>();
		queryWrapper.eq(ProductComments::getProductId, productId);
		long total = this.count(queryWrapper);

		// 好评 1好评，0中评，-1差评
		queryWrapper.clear();
		queryWrapper.eq(ProductComments::getProductId, productId);
		queryWrapper.eq(ProductComments::getCommType, 1);
		long good = this.count(queryWrapper);

		// 中评
		queryWrapper.clear();
		queryWrapper.eq(ProductComments::getProductId, productId);
		queryWrapper.eq(ProductComments::getCommType, 0);
		long medium = this.count(queryWrapper);

		// 差评
		queryWrapper.clear();
		queryWrapper.eq(ProductComments::getProductId, productId);
		queryWrapper.eq(ProductComments::getCommType, 0);
		long bad = this.count(queryWrapper);

		// 好评率
		double percent = (Double.parseDouble(good + "") / Double.parseDouble(total + "")) * 100;
		String percentValue = (percent + "").substring(0, (percent + "").lastIndexOf(".") + 3);

		HashMap<String, Object> map = new HashMap<>();
		map.put("total", total);
		map.put("goodTotal", good);
		map.put("midTotal", medium);
		map.put("badTotal", bad);
		map.put("percent", percentValue);
		return ResultVo.success(map);
	}
}
