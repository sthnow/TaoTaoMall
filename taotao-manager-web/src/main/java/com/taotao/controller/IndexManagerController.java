package com.taotao.controller;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.search.service.SearchItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
/**
 * 索引库维护Controller
 */
public class IndexManagerController {

    @Autowired
    private SearchItemService searchItemService;


    @RequestMapping("/indedx/import")
    @ResponseBody
    public TaotaoResult importIndex(){
        TaotaoResult result = searchItemService.importItemsToIndex();
        return result;

    }


}
