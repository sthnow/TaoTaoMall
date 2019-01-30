package com.taotao.search.service;

import com.taotao.common.pojo.SearchResult;

public interface SearchService {

    SearchResult search(String queryString, int pages, int rows) throws Exception;
}
