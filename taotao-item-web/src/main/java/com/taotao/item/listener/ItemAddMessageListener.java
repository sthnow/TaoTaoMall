package com.taotao.item.listener;

import com.taotao.item.pojo.Item;
import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbItemDesc;
import com.taotao.service.ItemService;
import freemarker.core.ParseException;
import freemarker.template.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

public class ItemAddMessageListener implements MessageListener {

    @Autowired
    private ItemService itemService;

    @Autowired
    private FreeMarkerConfigurer freeMarkerConfigurer;

    @Value("${HTML_OUT_PATH}")
    private String HTML_OUT_PATH;

    @Override
    public void onMessage(Message message) {
        //从消息中取出商品id
        TextMessage textMessage = (TextMessage) message;
        String strId = null;
        try {
            strId = textMessage.getText();
            long itemId = Long.parseLong(strId);
            //等待事务提交
            Thread.sleep(1000);
            //根据商品id查询商品信息及商品描述
            TbItem tbItem = itemService.getItemById(itemId);
            Item item = new Item(tbItem);
            TbItemDesc itemDesc = itemService.getItemDescById(itemId);
            //使用freemarker生成静态页面
            //1.创建模板
            Configuration configuration = freeMarkerConfigurer.getConfiguration();
            //2.加载模板对象
            Template template = configuration.getTemplate("item.ftl");
            //3.准备模板需要的数据
            Map data = new HashMap();
            data.put("item", item);
            data.put("itemDesc", itemDesc);
            //4.指定输出的目录及文件名
            Writer out = new FileWriter(new File(HTML_OUT_PATH + itemId + ".html"));
            //5.生成静态页面
            template.process(data,out);
            //关闭流
            out.close();
        } catch (JMSException e) {
            e.printStackTrace();
        } catch (MalformedTemplateNameException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (TemplateNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TemplateException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }
}
