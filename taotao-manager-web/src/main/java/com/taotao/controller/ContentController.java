package com.taotao.controller;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.content.service.ContentService;
import com.taotao.pojo.TbContent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
/**
 * 内容管理Controller
 */
public class ContentController {

    @Autowired
    private ContentService contentService;


    @RequestMapping("/content/save")
    @ResponseBody
    public TaotaoResult addContent(TbContent content) {
        TaotaoResult result = contentService.addContent(content);
        return result;
    }

    @RequestMapping("/content/delete")
    @ResponseBody
    /**
     * 删除内容的方法
     */
    public TaotaoResult deleteContent(long[] ids){
        TaotaoResult result = contentService.deleteContent(ids);
        return result;
    }

    @RequestMapping("/rest/content/edit")
    @ResponseBody
    public TaotaoResult editContent(TbContent tbContent) {
        TaotaoResult result = contentService.editContent(tbContent);
        return result;
    }
}
