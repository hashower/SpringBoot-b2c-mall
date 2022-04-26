package cn.luxun.mall;

import cn.luxun.mall.entity.User;
import cn.luxun.mall.service.UserService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserServiceTest {

	@Autowired
	private UserService userService;

	@Test
	public void getUserByUserId() {
		LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
		queryWrapper.eq(User::getId, 1);
		User user = userService.getOne(queryWrapper);
		System.out.println(user);
	}
}
