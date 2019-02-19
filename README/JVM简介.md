#JVM简介
JVM，JRE，JDK三者之间的联系 [JRE,JDK,JVM的区别和联系](https://www.notion.so/c35e2865-0195-47bd-ad67-082979fbbcbc)

[关于Jvm知识看这一篇就够了](https://zhuanlan.zhihu.com/p/34426768)

## JVM的作用

- JVM的两个特性
    - 平台无关性
        - 指安装在不同平台的JVM会将class文件解释为本地的机器指令，从而实现write once，run anywhere
    - 语言无关性
        - 指实现了JAVA虚拟机规范的语言可以在JVM上运行

![](https://www.itcodemonkey.com/data/upload/portal/20190128/1548658655689654.jpg)

## JVM运行时候的数据区

- JAVA虚拟机在执行JAVA程序的过程中会将他所管理的内存区域划分成不同的区域，每个区域负责不同的功能

    ![](https://www.itcodemonkey.com/data/upload/portal/20190128/1548658655600510.png)

    - 方法区和堆是所有线程共享的数据区
    - 程序计数器，虚拟机栈，本地方法栈是线程隔离的数据区，用逻辑图说明

        ![](https://www.itcodemonkey.com/data/upload/portal/20190128/1548658655447771.png)

        - 程序计数器
            - 程序计数器的作用就是记录当前线程所执行的字节码的行数。记录执行的行数是为了线程挂起后恢复时，继续执行代码
        - 虚拟机栈
            - 虚拟机栈存放当前线程运行方法所需要的数据，指令，返回地址。
        - 本地方法栈
            - 本地方法栈与虚拟机栈的作用类似。不同在于虚拟机栈为虚拟机执行JAVA方法，本地方法栈为虚拟机使用到的Native方法
        - 堆
            - 堆是所有线程共享的一个区域。唯一目的就是存放对象的事例
        - 方法区
            - 和JAVA堆一样，同样是线程共享的。用于存储已被虚拟机加载的类信息，常量，静态变量，即时编译器编译后的代码等数据。
    - 参考资料

        [用图示和代码理解JVM - IT程序猿](https://www.itcodemonkey.com/article/13011.html?utm_source=wechat_session)