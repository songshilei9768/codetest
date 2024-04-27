package entity;

import lombok.Data;

@Data
public class ProductInfoEntity {

    //商品名称
    private String productName;
    //商品单价
    private String productPrice;
    //商品数量
    private String productNum;
    //商品折扣
    private String discount;



    public ProductInfoEntity() {
    }

    public ProductInfoEntity(String productName, String productPrice,String productNum, String discount) {
        this.productName = productName;
        this.productPrice = productPrice;
        this.discount = discount;
        this.productNum = productNum;
    }
}
