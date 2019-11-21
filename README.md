# SSM-JavaConfig-
java config ssm(Spring、 SpringMVC、Mybatis)
### 使用java config配置基本的ssm框架，javaconfig 主要是为了替代xml文件进行项目的开发
### java config 项目配置web.xml 配置项目的入口AbstractAnnotationConfigDispatcherServletInitializer的子类，并实现相关的方法。
配置rootconfig为spring的context，配置springmvcconfig 为springmvc 的context，由于spring的context为springmvc context的父上下文，配置path
