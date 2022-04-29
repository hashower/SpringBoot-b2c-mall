package cn.luxun.mall.entity;

import lombok.Data;

import java.util.Date;

@Data
public class UserAddr {

    // 地址主键id
    private String id;

    // 用户ID
    private String userId;

    // 收件人姓名
    private String receiverName;

    // 收件人手机号
    private String receiverMobile;

    // 省份
    private String province;

    // 城市
    private String city;

    // 区县
    private String area;

    // 详细地址
    private String addr;

    // 邮编
    private String postCode;

    // 状态,1正常，0无效
    private Integer status;

    // 是否默认地址 1是 1:是  0:否
    private Integer commonAddr;

    // 创建时间
    private Date createTime;

    // 更新时间
    private Date updateTime;

}