package com.etoak.serverce;

import org.springframework.stereotype.Service;

/**
 * @author wang
 */

@Service
public class OrderService {

    public void deleteOrder(){
        System.out.println("商品订单已删除");
    }
}
