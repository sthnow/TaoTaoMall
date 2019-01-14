package com.taotao.content.service.impl;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.content.service.ContentService;
import com.taotao.mapper.TbContentMapper;
import com.taotao.pojo.TbContent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class ContentServiceImpl implements ContentService {

    @Autowired
    TbContentMapper contentMapper;
    @Override
    public TaotaoResult addContent(TbContent content) {
       //补全pojo属性
        content.setCreated(new Date());
        content.setUpdated(new Date());
        //插入到内容表
        contentMapper.insert(content);
        return TaotaoResult.ok();
    }

    @Override
    /**
     * 删除内容的方法
     */
    public TaotaoResult deleteContent(long[] ids) {
        for (long id : ids){
            contentMapper.deleteByPrimaryKey(id);
        }
        return TaotaoResult.ok();
    }
}
