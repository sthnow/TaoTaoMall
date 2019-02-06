package com.taotao.item.controller;

import com.taotao.item.pojo.Item;
import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbItemDesc;
import com.taotao.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 商品详情页面展示controller
 */
@Controller
public class ItemController {

    @Autowired
//    根据类型给接口装配实现类，@Autowired这种方法只适用于接口有一个实现类的情况
//    如果要按名称装配实现类，可以用 @Resource(name="baseDao") 和 @Autowired() @Qualifier("baseDao")
    private ItemService itemService;

    @RequestMapping("/item/{itemId}")
    public String showItem(@PathVariable Long itemId, Model model){
        //取商品基本信息
        TbItem tbItem = itemService.getItemById(itemId);
        Item item = new Item(tbItem);

        //取商品详情
        TbItemDesc itemDesc = itemService.getItemDescById(itemId);

        model.addAttribute("item",item);
        model.addAttribute("itemDesc", itemDesc);

        return "item";
    }
}
