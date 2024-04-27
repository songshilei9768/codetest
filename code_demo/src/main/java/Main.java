import entity.ProductInfoEntity;
import entity.OrderEntity;
import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Main {

    //活动类型1：购物满100减10块
    private static final String TYPE_ONE = "1";

    public static void main(String[] args) {
        //顾客A,购买苹果10斤，草莓10斤
        List<ProductInfoEntity> listA = new ArrayList<>();
        listA.add(new ProductInfoEntity("苹果", "8", "10", ""));
        listA.add(new ProductInfoEntity("草莓", "13", "10", ""));
        OrderEntity oeA = initData("顾客A", listA, "");
        System.out.println("顾客A商品总价格：" + calculatePrivate(oeA));

        //顾客B，购买苹果10斤，草莓10斤，芒果10斤
        List<ProductInfoEntity> listB = new ArrayList<>();
        listB.add(new ProductInfoEntity("苹果", "8", "10", ""));
        listB.add(new ProductInfoEntity("草莓", "13", "10", ""));
        listB.add(new ProductInfoEntity("芒果", "20", "10", ""));
        OrderEntity oeB = initData("顾客B", listB, "");
        System.out.println("顾客B商品总价格：" + calculatePrivate(oeB));

        //顾客C，购买苹果10斤，草莓10斤，芒果10斤
        List<ProductInfoEntity> listC = new ArrayList<>();
        listC.add(new ProductInfoEntity("苹果", "8", "10", ""));
        listC.add(new ProductInfoEntity("草莓", "13", "10", "0.8"));
        listC.add(new ProductInfoEntity("芒果", "20", "10", ""));
        OrderEntity oeC = initData("顾客C", listC, "");
        System.out.println("顾客B商品总价格：" + calculatePrivate(oeC));

        //顾客D，购买苹果10斤，草莓10斤，芒果10斤
        List<ProductInfoEntity> listD = new ArrayList<>();
        listD.add(new ProductInfoEntity("苹果", "8", "10", ""));
        listD.add(new ProductInfoEntity("草莓", "13", "10", "0.8"));
        listD.add(new ProductInfoEntity("芒果", "20", "10", ""));
        OrderEntity oeD = initData("顾客C", listD, "1");
        System.out.println("顾客B商品总价格：" + calculatePrivate(oeD));
    }

    public static String calculatePrivate(OrderEntity entity) {
        BigDecimal total = new BigDecimal(0);
        List<ProductInfoEntity> list = entity.getList();
        for (ProductInfoEntity e : list) {
            BigDecimal price = new BigDecimal(e.getProductPrice()).multiply(new BigDecimal(e.getProductNum()));
            if (StringUtils.isNotEmpty(e.getDiscount())) {
                price = price.multiply(new BigDecimal(e.getDiscount()));
            }
            total = total.add(price);
        }
        if (StringUtils.isNotEmpty(entity.getActivityType())) {
            total = afterActivityPrice(total, entity.getActivityType());
        }
        //四舍五入，保留2位小数
        total = total.setScale(2, BigDecimal.ROUND_HALF_UP);
        return  total + "元";
    }

    //活动策略
    private static BigDecimal afterActivityPrice(BigDecimal totalPrice, String activityType) {
        if (TYPE_ONE.equals(activityType) && totalPrice.compareTo(new BigDecimal(100)) >= 0) {
            totalPrice = totalPrice.subtract(new BigDecimal(10));
        }
        return totalPrice;
    }


    /**
     * 初始化订单信息
     *
     * @param userName
     * @param list         商品信息
     * @param activityType
     * @return
     */
    public static OrderEntity initData(String userName, List<ProductInfoEntity> list, String activityType) {
        OrderEntity oe = new OrderEntity();
        oe.setOrderNo(UUID.randomUUID().toString());
        oe.setUserName(userName);
        oe.setList(list);
        oe.setActivityType(activityType);
        return oe;
    }
}
