#Solr
## 是什么

- solr采用Java开发，是一个基于Lucene的全文搜索服务器。Solr提供了比Lucene更为丰富的查询语言，同时实现了可配置可扩展，并对搜索性能进行了优化

## 能做什么

- solr是一个企业级的搜索引擎系统，是一个搜索引擎服务，可以独立运行，通过solr可以快速的构建构建搜索引擎，即可以完成快速的站内搜索功能。

## Solr与Lucene的区别

- Lucene是一个开源的全文检索引擎工具包，它不是一个完整的全文检索引擎。Lucene提供了完整的查询引擎和索引引擎，目的是为开发人员提供一个工具包，以方便在目标系统中实现全文检索的功能，或者说就是以Lucene为基础构建全文检索引擎。

## Solr的工作流程

- 客户端发送get请求到Solr服务器，请求Solr服务器响应一个结果文档，Solr服务器根据传过去的参数在文档库中进行搜索。

[](https://www.notion.so/c11fb26642e4446a97b42b04f6f4ba20#bf8962d75d2c4b7b8bbf912baad6701d)

## Lucene的结构

[](https://www.notion.so/c11fb26642e4446a97b42b04f6f4ba20#2cf6f3e73f1a461a987b47a5a71d2969)

- **索引**：域名：词  这样的形式
    - 里面有指针指向这个词来源的文档
    - 索引库：存放索引的文件夹
    - Term词元：就是一个词，是lucene中词的最小单位
- **文档**：Document对象
    - 一个Document对象可以有多个Field对象，Field域对象中是key-value键值对的形式，有域名和域值。
    - 一个Document就是数据库表中的一条记录，一个Fileld域对象就是数据库表中的一行一列
- 创建索引和使用时的分词器必须一致

# 使用SolrJ管理索引库

- 什么是SolrJ
    - SolrJ是访问Solr服务的Java客户端，提供索引和搜索的请求方法。

        [](https://www.notion.so/c11fb26642e4446a97b42b04f6f4ba20#06a841160ca94cbcb5cab9caf4ec79a7)