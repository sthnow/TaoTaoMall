package com.taotao.service;

import com.taotao.common.pojo.EasyUIDataGridResult;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.pojo.TbItem;

public interface ItemService {

    TbItem getItemById(long itemId);

    EasyUIDataGridResult getItemList(int page, int rows);

    TaotaoResult addItem(TbItem item,String desc);

    TaotaoResult deleteItem(long[] ids);

    //商品下架功能处理
    TaotaoResult instockItem(long[] ids);

    //实现商品上架功能
    TaotaoResult reshelf(long[] ids);
}
