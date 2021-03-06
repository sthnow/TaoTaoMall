package com.taotao.solrJ;

import org.apache.solr.client.solrj.impl.CloudSolrServer;
import org.apache.solr.common.SolrInputDocument;
import org.junit.Test;

public class TestSolrCloud {


    public void testSolrCloudAdDocument() throws Exception{

        //创建一个CloudSolrServer对象，在构造方法中指定zookeeper的地址列表
        CloudSolrServer cloudSolrServer = new CloudSolrServer("192.168.25.88:2182,192.168.25.88:2183,192.168.25.88:2184");
        //需要设置一个默认的collection
        cloudSolrServer.setDefaultCollection("collection2");
        //创建一个文档对象
        SolrInputDocument document = new SolrInputDocument();
        //向文档中添加对象
        document.addField("id", "test001");
        document.addField("item_title", "测试商品名称");
        document.addField("item_price", "100");
        //提交
        cloudSolrServer.add(document);
        cloudSolrServer.commit();
    }
}
