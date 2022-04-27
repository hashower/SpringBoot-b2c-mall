package cn.luxun.mall.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

	// 用户id
	private Integer id;

	// 用户名
	private String username;

	// 密码
	private String password;

	// 昵称
	private String nickname;

	// 真实姓名
	private String realname;

	// 头像
	private String img;

	// 手机号
	private String phone;

	// 邮箱地址
	private String email;

	// 性别 M(男) or F(女)
	private String sex;

	// 生日
	private Date birthday;

	// 创建时间
	private Date createTime;

	// 更新时间
	private Date updateTime;
}
