package com.taotao.content.service;

import com.taotao.common.pojo.EasyUITreeNode;
import com.taotao.common.pojo.TaotaoResult;

import java.util.List;

public interface ContentCategoryService {

    //要用列表展示，所以还得用EasyUITreeNode展示
    List<EasyUITreeNode> getContentCategoryList(long parentId);

    //添加节点的方法
    TaotaoResult addContentCategory(long parentId, String name);


    //重命名节点的方法
    void renameContentCategory(long id, String name);

    //删除节点的方法
    TaotaoResult deleteContentCategory(long id);


}