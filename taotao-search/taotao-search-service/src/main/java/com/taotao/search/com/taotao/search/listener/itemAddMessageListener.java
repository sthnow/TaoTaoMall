package com.taotao.search.com.taotao.search.listener;

import javax.jms.Message;
import javax.jms.MessageListener;

/**
 * 监听商品添加事件，同步索引库
 */
public class itemAddMessageListener implements MessageListener {

    @Override
    public void onMessage(Message message) {
        //从消息中取商品id

        //根据商品id查询数据库，取商品信息
        //创建文档对象
        //向文档对象中添加域
        //把文档对象写入索引库
        //提交
    }
}
