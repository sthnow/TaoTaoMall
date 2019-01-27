package com.taotao.search.service;

import com.taotao.common.pojo.TaotaoResult;

public interface SearchItemService {

    //实现上商品数据的导入
    TaotaoResult importItemsToIndex();
}
