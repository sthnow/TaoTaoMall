package com.taotao.controller;


import com.taotao.common.pojo.EasyUITreeNode;
import com.taotao.service.ItemCatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 商品分类管理controller
 */
@Controller
public class ItemCatController {


    @Autowired
    private ItemCatService itemCatService;


    @RequestMapping("/item/cat/list")
    //要响应一个json数据，所以要加上ResponseBody
    @ResponseBody

    public List<EasyUITreeNode> getItemCatList(@RequestParam(name="id",defaultValue = "0") long parentId){
        List<EasyUITreeNode> list = itemCatService.getItemCatList(parentId);
        return list;

    }
}
