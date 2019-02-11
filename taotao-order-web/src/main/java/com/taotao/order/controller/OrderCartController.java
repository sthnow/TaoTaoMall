package com.taotao.order.controller;

import com.taotao.common.utils.CookieUtils;
import com.taotao.common.utils.JsonUtils;
import com.taotao.pojo.TbItem;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * 订单确认页面处理Controller
 */
@Controller
public class OrderCartController {

    @Value("${CART_KEY}")
    public String CART_KEY;

    /**
     * 展示订单确认页面
     * @param request
     * @return
     */
    @RequestMapping("/order/order-cart")
    public String showOrderCart(HttpServletRequest request){
        //用户必须是登录状态
        //取用户id
        //根据用户信息取收货地址列表，使用静态数据模拟
        //把收货地址列表取出来传递给页面
        //从cookie中取购物车商品列表展示给页面
        List<TbItem> cartList = getCartItemList(request);
        request.setAttribute("cartList",cartList);
        //返回逻辑视图
        return "order-cart";
    }


    /**
     * 从购物车取商品列表
     * @param request
     * @return
     */
    private List<TbItem> getCartItemList(HttpServletRequest request) {
        //从cookie中取出购物车商品列表
        String json = CookieUtils.getCookieValue(request, CART_KEY, true);
        if (StringUtils.isBlank(json)) {
            //如果没有内容，返回一个空的列表
            return new ArrayList<>();
        }
        List<TbItem> list = JsonUtils.jsonToList(json, TbItem.class);
        return list;
    }
}

