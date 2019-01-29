package com.taotao.solrJ;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.junit.Test;

import java.util.List;
import java.util.Map;

public class TestSolrJ {

    @Test
    public void testAddDocument() throws Exception {
        //创建一个SolrServer对象，创建一个HttpSolrServer对象
        //需要指定solr服务的url
        SolrServer solrServer = new HttpSolrServer("http://192.168.25.88:8080/solr/collection1");
        //创建一个文档对象SolrInputDocument
        SolrInputDocument document = new SolrInputDocument();
        //向文档中添加域，必须有id域，域的名称必须在schema.xml中定义
        document.addField("id", "test002");
        document.addField("item_title", "测试商品2");
        document.addField("item_price", 1000);
        //把文档对象写入索引库
        solrServer.add(document);
        //提交
        solrServer.commit();
    }


    @Test
    public void deleteDocumentById() throws Exception {
        SolrServer solrServer = new HttpSolrServer("http://192.168.25.88:8080/solr/collection1");
        solrServer.deleteById("test001");
        solrServer.commit();
    }

    @Test
    //根据内容删
    public void deleteByQuery() throws Exception {
        SolrServer solrServer = new HttpSolrServer("http://192.168.25.88:8080/solr/collection1");
        solrServer.deleteByQuery("id:123");
        solrServer.commit();
    }

    @Test
    //测试查询文档
    public void searchDocument() throws Exception {
        //创建solrServer对象
        SolrServer solrServer = new HttpSolrServer("http://192.168.25.88:8080/solr/collection1");
        //创建一个solrQuery对象
        SolrQuery solrQuery = new SolrQuery();
        //设置查询条件，过滤条件，分页条件，排序条件，设置高亮
        // solrQuery.set("q", "*:*");
        solrQuery.setQuery("手机");
        //分页条件
        solrQuery.setStart(0);
        solrQuery.setRows(10);
        //设置默认搜索域
        solrQuery.set("df", "item_keywords");
        //设置高亮
        solrQuery.setHighlight(true);
        //设置高亮显示的域
        solrQuery.addHighlightField("item_title");
        //高亮显示的前缀
        //参数里的是html标签
        solrQuery.setHighlightSimplePre("<div>");
        //高亮显示的后缀
        solrQuery.setHighlightSimplePost("<div>");
        //执行查询，得到一个Response对象
        QueryResponse response= solrServer.query(solrQuery);
        //取查询结果
        SolrDocumentList solrDocumentList = response.getResults();
        //取查询结果总记录数
        System.out.println("查询结果总记录数" + solrDocumentList.getNumFound());
        for (SolrDocument solrDocument:solrDocumentList
             ) {
            System.out.println(solrDocument.get("id"));
            //取高亮显示
            Map<String, Map<String, List<String>>> highlighting = response.getHighlighting();
            List<String> list = highlighting.get(solrDocument.get("id")).get("item_title");
            String itemTitle = "";
            if (list != null && list.size() > 0) {
                itemTitle = list.get(0);
            }else{
                itemTitle = (String) solrDocument.get("item_title");
            }
            System.out.println(itemTitle);
            System.out.println(solrDocument.get("item_title"));
            System.out.println(solrDocument.get("item_sell_point"));
            System.out.println(solrDocument.get("item_price"));
            System.out.println(solrDocument.get("item_image"));
            System.out.println("=============");
        }

    }
}
