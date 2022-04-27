package cn.luxun.mall.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@ApiModel(value = "ResultVO对象", description = "封装接口返回给前端的数据")
public class ResultVo {

	//响应给前端的状态码
	@ApiModelProperty(value = "响应是否成功", dataType = "boolean")
	private boolean success;

	//响应给前端的状态码
	@ApiModelProperty(value = "响应状态码", dataType = "int")
	private int code;

	//响应给前端的提示信息
	@ApiModelProperty("响应提示信息")
	private String msg;

	//响应给前端的数据
	@ApiModelProperty("响应数据")
	private Object data;

	public static ResultVo success(Object data) {
		return new ResultVo(true, 200, "success", data);

	}

	public static ResultVo fail(int code, String msg) {
		return new ResultVo(false, code, msg, null);


	}

}
