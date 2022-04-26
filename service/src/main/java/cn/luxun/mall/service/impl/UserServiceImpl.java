package cn.luxun.mall.service.impl;

import cn.luxun.mall.entity.User;
import cn.luxun.mall.mapper.UserMapper;
import cn.luxun.mall.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
}
