package com.taotao.controller;


import com.taotao.common.pojo.EasyUIDataGridResult;
import com.taotao.pojo.TbItem;

//import com.taotao.service.ItemService;
import com.taotao.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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


}
