package com.taotao.content.service;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.pojo.TbContent;

import java.util.List;

public interface ContentService {

    TaotaoResult addContent(TbContent Content);

    //删除内容
    TaotaoResult deleteContent(long[] ids);

    //修改内容
    TaotaoResult editContent(TbContent content);

    List<TbContent> getContentByCid(long cid);
}
