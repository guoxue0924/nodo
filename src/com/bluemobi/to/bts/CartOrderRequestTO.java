package com.bluemobi.to.bts;

import com.bluemobi.constant.OrderConstant;


public class CartOrderRequestTO extends AbstractOrderRequestTO {

    private static final long serialVersionUID = 1L;

    private Integer[] cartList;

    public Integer[] getCartList() {
        return cartList;
    }

    public void setCartList(Integer[] cartList) {
        this.cartList = cartList;
    }
    
    public CartOrderRequestTO() {
        super.setOrderType(OrderConstant.OrderType.COMMON.getCode().byteValue());
    }

}
