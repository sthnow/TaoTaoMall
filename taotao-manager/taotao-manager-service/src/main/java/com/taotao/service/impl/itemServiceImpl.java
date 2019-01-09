package com.taotao.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.common.pojo.EasyUIDataGridResult;
import com.taotao.mapper.TbItemMapper;
import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbItemExample;
import com.taotao.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class itemServiceImpl implements ItemService {

    @Autowired
    private TbItemMapper itemMapper;

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
}
