package com.taotao.item.listener;

import javax.jms.Message;
import javax.jms.MessageListener;

public class ItemAddMessageListener implements MessageListener {
    @Override
    public void onMessage(Message message) {
        //从消息中取出商品id
        //根据商品id查询商品信息及商品描述
        //使用freemarker生成静态页面
        //1.创建模板
        //2.加载模板对象
        //3.准备模板需要的数据
        //4.指定输出的目录及文件名
        //5.生成静态页面

    }
}
