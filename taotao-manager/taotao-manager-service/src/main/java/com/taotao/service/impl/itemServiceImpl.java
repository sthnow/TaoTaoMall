package com.taotao.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.common.pojo.EasyUIDataGridResult;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.common.utils.IDUtils;
import com.taotao.mapper.TbItemDescMapper;
import com.taotao.mapper.TbItemMapper;
import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbItemDesc;
import com.taotao.pojo.TbItemExample;
import com.taotao.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;


@Service
public class itemServiceImpl implements ItemService {

    @Autowired
    private TbItemMapper itemMapper;

    @Autowired
    private TbItemDescMapper itemDescMapper;

    @Override
    public TbItem getItemById(long itemId) {
        TbItem item = itemMapper.selectByPrimaryKey(itemId);
        return item;
    }

    @Override
    /**
     * page：查询到的页数
     * row：返回的页所包含的行数
     */
    public EasyUIDataGridResult getItemList(int page, int rows) {
        //设置分页信息
        //page-第几页
        //rows-返回第几行到第几行
        PageHelper.startPage(page, rows);
        //创建example
        //不设置查询条件，即查询所有
        TbItemExample example = new TbItemExample();
        //执行查询
        List<TbItem> list = itemMapper.selectByExample(example);
        //取查询结果
        //将查询到的结果包装一下
        PageInfo<TbItem> pageInfo = new PageInfo<>(list);
        //通过EasyUIGridResult将结果转换前端页面接受的数据格式
        EasyUIDataGridResult result = new EasyUIDataGridResult();

        result.setRows(list);
        result.setTotal(pageInfo.getTotal());
        //返回结果
        return result;
    }



    @Override
    /**
     * 实现添加商品的方法
     */
    public TaotaoResult addItem(TbItem item, String desc) {
        //生成商品id
        long itemId = IDUtils.genItemId();
        //补全item的属性
        item.setId(itemId);
        //商品状态，1-正常，2-下架，3-删除
        item.setStatus((byte) 1);
        item.setCreated(new Date());
        item.setUpdated(new Date());
        //向商品表插入数据
        itemMapper.insert(item);
        //创建一个商品描述表的pojo
        TbItemDesc itemDesc = new TbItemDesc();
        //补全pojo的属性
        itemDesc.setItemId(itemId);
        itemDesc.setItemDesc(desc);
        itemDesc.setUpdated(new Date());
        itemDesc.setCreated(new Date());
        //向商品描述插入数据
        //调用DAO层的insert方法插入数据
        itemDescMapper.insert(itemDesc);
        //返回结果
        return TaotaoResult.ok();
    }

    @Override
    public TaotaoResult deleteItem(long ids) {
        itemMapper.deleteByPrimaryKey(ids);
        return TaotaoResult.ok();
    }
}
