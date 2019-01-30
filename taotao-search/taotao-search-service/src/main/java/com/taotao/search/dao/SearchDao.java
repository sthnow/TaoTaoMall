package com.taotao.search.dao;

import com.taotao.common.pojo.SearchItem;
import com.taotao.common.pojo.SearchResult;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrRequest;
import org.apache.solr.client.solrj.SolrResponse;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 查询索引库商品DAO
 */
@Repository
public class SearchDao {

    @Autowired
    private SolrServer solrServer;

    public SearchResult search(SolrQuery query) throws  Exception{
        //根据query对象进行查询
        SolrResponse response = solrServer.query(query);
        //取查询结果
        SolrDocumentList solrDocumentList = ((QueryResponse) response).getResults();
        //取查询结果的总记录数
        long numFound = solrDocumentList.getNumFound();
        //把查询结果封装到SearchItem对象
        SearchResult result = new SearchResult();
        result.setRecordCount(numFound);
        List<SearchItem> itemList = new ArrayList<>();
        //把结果封装到SearchResult对象中
        for (SolrDocument solrDocument : solrDocumentList){
            //封装对象
            SearchItem item = new SearchItem();
            item.setCategory_name((String) solrDocument.get("item_category_name"));
            item.setId((String) solrDocument.get("id"));
            item.setImage((String) solrDocument.get("item_image"));
            item.setPrice((Long) solrDocument.get("item_price"));
            item.setSell_point((String) solrDocument.get("item_sell_point"));

            //取高亮显示
            Map<String, Map<String, List<String>>> highlighting = ((QueryResponse) response).getHighlighting();
            List<String> list = highlighting.get(solrDocument.get("id")).get("item_title");
            String title = "";
            if (list != null && list.size() > 0) {
                title = list.get(0);

            }else{
                title = (String) solrDocument.get("item_title");
            }
            item.setTitle(title);

            //添加到商品列表
            itemList.add(item);



        }
        //把结果加到ResultSet里面
        result.setItemList(itemList);

        //返回
        return result;

    }
}
