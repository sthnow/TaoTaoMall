package com.taotao.controller;


import com.taotao.common.pojo.EasyUITreeNode;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.content.service.ContentCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 内容分类管理controller
 */
@Controller
public class ContentCategoryController {

    @Autowired
    private ContentCategoryService contentCategoryService;


    @RequestMapping("/content/category/list")
    @ResponseBody
    public List<EasyUITreeNode> getContentCategoryList
            (@RequestParam(value = "id",defaultValue = "0") long parentId){
        List<EasyUITreeNode> list = contentCategoryService.getContentCategoryList(parentId);
        return list;
    }


    @RequestMapping("/content/category/create")
    @ResponseBody
    public TaotaoResult addContentCategory(long parentId,String name){
        TaotaoResult result = contentCategoryService.addContentCategory(parentId, name);
        return result;
    }

    @RequestMapping("/content/category/update")
    /**
     * 重命名节点的方法
     */
    public void renameContentCategory(long id, String name) {
        contentCategoryService.renameContentCategory(id, name);
    }


    @RequestMapping("/content/category/delete/")
    public TaotaoResult deleteContentCategory(long id){
        contentCategoryService.deleteContentCategory(id);
        return TaotaoResult.ok();
    }

}
