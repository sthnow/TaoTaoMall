package com.taotao.search.controller;

import com.taotao.common.pojo.SearchResult;
import com.taotao.search.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 搜索服务controller
 */
@Controller
public class SearchController {

    //注入service对象
    @Autowired
    private SearchService searchService;

    //加载属性文件的配置
    @Value("${SEARCH_RESULT_ROWS}")
    private Integer SEARCH_RESULT_ROWS;


    //请求url
    @RequestMapping("/search")
    //使用String作为返回值，SpringMVC可以根据这个字符串自动找到对应的页面
    public String search(@RequestParam("q") String queryString,
                         @RequestParam(defaultValue = "1") Integer page,
                         Model model) throws Exception {


        //调用服务执行查询

            //把查询条件进行转码，解决get乱码问题
            //取出queryString的字节，然后通过String类的方法转换成对应的格式
            queryString = new String(queryString.getBytes("iso-8859-1"),"utf-8");
            SearchResult searchResult = searchService.search(queryString, page, SEARCH_RESULT_ROWS);
            //把结果传递给页面
            //使用model设置数据，根据返回页面需要什么数据设置什么数据
            model.addAttribute("query", queryString);
            model.addAttribute("totalPages", searchResult.getTotlaPages());
            model.addAttribute("itemList", searchResult.getItemList());
            model.addAttribute("page", page);


        //返回逻辑视图
        return "search";
    }
}
