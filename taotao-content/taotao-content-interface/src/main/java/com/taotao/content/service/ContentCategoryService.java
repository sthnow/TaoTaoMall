package com.taotao.content.service;

import com.taotao.common.pojo.EasyUITreeNode;

import java.util.List;

public interface ContentCategoryService {

    //要用列表展示，所以还得用EasyUITreeNode展示
     List<EasyUITreeNode> getContentCategoryList(long parentId);
}