package cn.luxun.mall.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductImg {

    // 图片主键
    private String id;

    // 商品外键id
    private String itemId;

    // 图片地址
    private String url;

    // 图片顺序，从小到大
    private Integer sort;

    // 是否主图，1：是，0：否
    private Integer isMain;

    // 创建时间
    private Date createTime;

    // 更新时间
    private Date updateTime;

}