package cn.luxun.mall.controller;

import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pay")
@Api(value = "提供支付操作接口", tags = "支付管理")
public class PayController {

	/**
	 * 回调接口
	 */
	@RequestMapping("/callback")
	public void paySuccess() {
		System.out.println("call back");
	}
}
