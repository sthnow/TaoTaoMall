package com.taotao.content.service.impl;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.common.utils.JsonUtils;
import com.taotao.content.service.ContentService;
import com.taotao.jedis.JedisClient;
import com.taotao.mapper.TbContentMapper;
import com.taotao.pojo.TbContent;
import com.taotao.pojo.TbContentExample;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;

import java.util.Date;
import java.util.List;

@Service
public class ContentServiceImpl implements ContentService {

    @Autowired
    private TbContentMapper contentMapper;
    @Autowired
    private JedisClient jedisClient;
    @Value("${INDEX_CONTENT}")
    private String INDEX_CONTENT;
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
        //先查询缓存
        //添加缓存不能影响正常的业务逻辑
        try{
            //查询缓存
            //cid+""  可以转换成字符串？
            String json = jedisClient.hget(INDEX_CONTENT, cid + "");
            //查询到结果，把json转换成list返回
            if(StringUtils.isNotBlank(json)){
                List<TbContent> list = JsonUtils.jsonToList(json, TbContent.class);
                return list;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        //缓存中没有命中，需要查询数据库


        TbContentExample example = new TbContentExample();
        TbContentExample.Criteria criteria = example.createCriteria();
        criteria.andCategoryIdEqualTo(cid);
        //执行查询
        List<TbContent> list = contentMapper.selectByExample(example);

        //把结果添加到缓存
        try{
            jedisClient.hset(INDEX_CONTENT, cid + "", JsonUtils.objectToJson(list));
        }catch (Exception e){
                e.printStackTrace();

        }
        //返回结果
        return list;
    }
}
