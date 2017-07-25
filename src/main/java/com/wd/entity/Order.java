package com.wd.entity;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * Created by wd on 2017/7/5.
 * 用来测试BigDecimal 插入数据库
 */
@Entity
@Table(name = "t_order",catalog = "test")
public class Order {
    private Long id;
    private BigDecimal price;
    @Id
    @GeneratedValue
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    @Column(name = "order_price")
    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return super.toString()+"  Order{" +
                "id=" + id +
                ", price=" + price +
                '}' ;
    }
}
