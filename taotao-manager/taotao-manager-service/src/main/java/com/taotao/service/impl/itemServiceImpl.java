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
        //使用IDUtils工具生成商品的id，使用这个工具类生成id是因为这个工具类里面封装了一些方法，使得生成的id不易重复
        long itemId = IDUtils.genItemId();

        //从pojo中获取属性
        //补全item的属性，因为要存放到数据库的表中，该有的属性必须要有
        item.setId(itemId);
        //商品状态，1-正常，2-下架，3-删除
        item.setStatus((byte) 1);
        item.setCreated(new Date());
        item.setUpdated(new Date());
        //使用DAO层中接口的方法向数据库添加数据
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
    public TaotaoResult deleteItem(long[] ids) {
        for (long id : ids) {
            itemMapper.deleteByPrimaryKey(id);
        }
        return TaotaoResult.ok();
    }


    //实现商品的下架处理
    @Override
    public TaotaoResult instockItem(long[] ids) {
        for (long id : ids) {
            //1.创建查询条件通过id查询到商品
            TbItem tbItem = itemMapper.selectByPrimaryKey(id);

            //2.查到商品以后通过设置其status（上架状态为）
            //商品状态，1-正常，2-下架，3-删除
            //这时设置完数据还在内存中，要通过mapper更新到数据库中
            tbItem.setStatus((byte) 2);
            //使用mapper插入到数据库中
            itemMapper.updateByPrimaryKey(tbItem);
        }

        return TaotaoResult.ok();
    }
}
