# strawberry
A web framework implemented by Java

### Feature

- 实现 IoC 容器，自动管理 Bean
- MVC
- 支持 AOP
- 支持事务管理

### Todo

- IoC 容器实现

扫描classpath下的所有类，找到以@Bean or @Service 为注解的类，加载，存储，初始化

参考：

1. [Guice](https://github.com/google/guice)
2. [Spring beans](https://github.com/spring-projects/spring-framework)
