package cn.luxun.mall.service.impl;


import cn.luxun.mall.entity.OrderItem;
import cn.luxun.mall.mapper.OrderItemMapper;
import cn.luxun.mall.service.OrderItemService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class OrderItemServiceImpl extends ServiceImpl<OrderItemMapper, OrderItem> implements OrderItemService {
}
