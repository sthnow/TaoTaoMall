package com.taotao.content.service.impl;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.content.service.ContentService;
import com.taotao.mapper.TbContentMapper;
import com.taotao.pojo.TbContent;
import com.taotao.pojo.TbContentExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

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

    @Override
    /**
     * 修改内容的方法
     */
    public TaotaoResult editContent(TbContent content) {
        TbContent tbContent = new TbContent();
        tbContent.setId(content.getId());
        tbContent.setCategoryId(content.getCategoryId());
        tbContent.setTitle(content.getTitle());
        tbContent.setSubTitle(content.getSubTitle());
        tbContent.setTitleDesc(content.getTitleDesc());
        tbContent.setUrl(content.getUrl());
        tbContent.setPic(content.getPic());
        tbContent.setPic2(content.getPic2());
        tbContent.setContent(content.getContent());
        contentMapper.updateByPrimaryKey(tbContent);

        return TaotaoResult.ok();
    }

    @Override
    public List<TbContent> getContentByCid(long cid) {

        TbContentExample example = new TbContentExample();
        TbContentExample.Criteria criteria = example.createCriteria();
        criteria.andCategoryIdEqualTo(cid);
        //执行查询
        List<TbContent> list = contentMapper.selectByExample(example);

        return list;
    }
}
