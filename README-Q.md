1. log4j配置文件的使用
2. dubbo的使用
3. jar，war，pom三种打包方式的区别

    pom打出来可以作为其他项目的maven依赖，比如你写了两个工程，工程a写了一些工具类，然后你可以把它打成pom，再在工程b里面添加a的依赖，然后你在工程b中就可以使用a定义好的工具类。jar是maven出来之前三方库普遍使用的方式，比较常见的是jdbc驱动包，一般从官网下载下来的都是jar文件。也可以打成可执行文件，类似.exe那样的，用命令java -jar xxx.jar执行。war常用于java web工程，但也不是绝对的，war也可以用java -jar xxx.war执行，前提是你有配置main函数入口

    作者：胡三汗
    链接：https://www.zhihu.com/question/273590151/answer/370883951
    来源：知乎
    著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。

    **pom 类型**：一般作为父工程存在，父工程主要是进行统一的版本申明，并不定义具体的依赖关系，常见于多模块或者说聚合工程中使用。

    **jar类型**：一般用于打包普通的java bean，资源库等，比如将公共的工具类放到一个模块，这个时候就其他模块如果想要使用的话，就可以直接引用便可，就把这个项目当作一个jar包来用。**war类型**：作为一个web工程，常放一些静态资源，controller等。

    作者：木心若素链接：https://www.jianshu.com/p/bf3c7c9bf70d來源：简书简书著作权归作者所有，任何形式的转载都请联系作者获得授权并注明出处。

4. 加上serevice注解，controller注解背后起到什么作用
5. xmlns有什么用
6. 端口的作用是什么
7. mvc中的资源映射有什么用
    1. 

    [MVC静态资源映射 标签的使用 - petercnmei的专栏 - CSDN博客](https://blog.csdn.net/petercnmei/article/details/50684829)

8. mybaits的核心配置文件SqlMapConfig为什么要放在service层中？

- 因为service和dao层都属于taotao-manager这个聚合工程下的子工程，service层打包方式为war包，会将该聚合工程下的jar包引入，同样也会引入配置文件。与其要从jar包引入不如直接放在该项目的war包中直接访问。
- SqlMapConfig是mybatis的核心配置文件，可以在里面配置插件，例如分页插件

9. Idea操作问题：java项目如何以debug模式启动

10. 为什么要实现序列化接口

- 实现Serializable接口的Bean对象，就可以在网络中传输，无论是读取或者是写入都还是该Bean对象。

11. maven的基本操作使用

12. json串和json对象的区别

- [https://www.jianshu.com/p/4b0bb59f585f](https://www.jianshu.com/p/4b0bb59f585f)

13. 几种常用的富文本编辑器

- kindEditor- 国产
- UEditor-百度的
- ckEditor-国外的

14. - CRM-customer relation management  客户关系管理

- CMS-content management service 内容管理服务

15. -  发布服务并不一定需要tomcat，写个main方法初始化一个容器就可以发布服务

有的时候使用tomcat是为了讲工程打包成war包，然后将其他其他工程的jar包添加进来

16. 什么时候要用maven -install，什么时候不用

17.，Page-Helper的作用是什么?

18. redis中能存放的5种类型

- String
- hash
- list
- set
- zset

19.redis集群的架构

- 为什么要有redis集群
    - redis存储数据是在内存中存储的，单台redis内存有限，因此要通过集群的方式扩展内存
- redis集群存储数据的方式
    - redis集群存储数据是往hash槽中存的，给集群中每个redis节点分配不同的hash槽，通过key计算出往哪个槽存储数据
- redis的备份机制
    - redis中不分主从节点，但是有备份节点，备份节点的hash槽和主节点一致，数据和主节点保持一致。
    - 失效机制：当一个redis节点失效时，采用投票机制，当半数以上的 redis节点都认为其失效后才认为该节点失效

20. java中的设计模式

21. 单例什么意思

22.Spring的autoWired

23. cid+"" 可以转换成字符串？

24. solr的学习

# 大坑

1. dubbo中**也会引入一个spring**，但是会和**前端控制器**中的**spring版本冲突**，造成web工程无法启动