package com.taotao.search.service.impl;

import com.taotao.common.pojo.SearchResult;
import com.taotao.search.dao.SearchDao;
import com.taotao.search.service.SearchService;
import org.apache.solr.client.solrj.SolrQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SearchServiceImpl implements SearchService {

    @Autowired
    private SearchDao searchDao;

    @Override
    public SearchResult search(String queryString, int pages, int rows) throws Exception {
        //根据查询条件拼装查询对象
        //创建一个SolrQuery对象
        SolrQuery query = new SolrQuery();
        //设置查询条件
        query.setQuery(queryString);
        //设置分页条件
        if (pages < 1) pages = 1;
        query.setStart((pages - 1) * rows);
        if(rows < 1) rows = 10;
        query.setRows(rows);
        //设置默认搜索域
        //如果用keywords域准确度不高
        query.set("df", "item_title");
        //设置高亮显示
            //开启高亮
        query.setHighlight(true);
            //设置高亮显示的域
        query.addHighlightField("item_title");
            //开启前缀和后缀
        query.setHighlightSimplePre("<font color='red'>");
        query.setHighlightSimplePost("</font>");
        //调用DAO执行查询
        SearchResult searchResult = searchDao.search(query);
        //计算查询结果的总页数
        long recordCount = searchResult.getRecordCount();
        long pageSum = recordCount / rows;
        if (recordCount % rows > 0) {
            pageSum++;
        }
        searchResult.setTotlaPages(pageSum);
        //返回结果
        return searchResult;
    }
}
