package entity;

import lombok.Data;

import java.util.List;

@Data
public class OrderEntity {
    //订单号
    private String orderNo;
    //用户名
    private String userName;
    //商品信息清单
    private List<ProductInfoEntity> list;
    //总价格
    private String totalPrice;

    //活动类型：1.满减活动，....
    private String activityType;
}
