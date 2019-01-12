package com.taotao.controller;


import com.taotao.common.pojo.EasyUIDataGridResult;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.pojo.TbItem;

//import com.taotao.service.ItemService;
import com.taotao.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.swing.border.TitledBorder;

/**
 * 商品管理Controller
 */

@Controller

public class ItemController {
    @Autowired
    private ItemService itemService;

    @RequestMapping("/item/{itemId}")
    @ResponseBody
//    如果出现406错误，是没有将jsckson包加进来，无法将json转换为字符串
    public TbItem getItemById(@PathVariable Long itemId){
        TbItem tbItem = itemService.getItemById(itemId);
        return tbItem;
    }


    @RequestMapping("/item/list")
    @ResponseBody
    public EasyUIDataGridResult getItemList(Integer page, Integer rows){
        //controller层调用service层提供的服务
        EasyUIDataGridResult result = itemService.getItemList(page,rows);
        return result;
    }


    @RequestMapping("/item/save")
    @ResponseBody
    //表单的数据由springMVC封装到pojo里面
    public TaotaoResult addItem(TbItem item, String desc) {
        //前端页面通过向注册中心请求接口方法的url，然后通过该url从dubbo中获取服务
        TaotaoResult result = itemService.addItem(item, desc);
        return result;
    }

    //实现商品的删除功能
    //思路分析：
        //首先在前端页面中获取到请求的url
        //然后获取到请求删除商品的id
        //通过在服务层编写删除的方法
        //服务层调用DAO层的删除方法删除商品
    @RequestMapping("/rest/item/delete")
    @ResponseBody
    public TaotaoResult deleteItem(long[] ids){
        TaotaoResult result = itemService.deleteItem(ids);
        return result;
    }
}