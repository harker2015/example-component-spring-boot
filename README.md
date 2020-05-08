# example-component-spring-boot
**本项目是Spring Boot的组件开发示例**

我们都知道Spring Boot简化了配置，开发者只需要引入一个spring boot的包，其他的复杂的依赖Spring Boot已经帮我们解决了。在使用基于Spring Boot组件时，是非常简单的，比如我们会用到MyBatis这个组件，我们只需要三步即可使用这个组件：
1. 第一步：引用依赖：
    ```xml
    <!-- Mybatis -->
        <dependency>
            <groupId>org.mybatis.spring.boot</groupId>
            <artifactId>mybatis-spring-boot-starter</artifactId>
            <version>2.0.0</version>
        </dependency>
    ```
2. 第二步：修改配置：
    ```yml
    mybatis:
    # model的包
    type-aliases-package: ssm.app.model
    configuration:
    # 开启驼峰uName自动映射到u_name
    map-underscore-to-camel-case: true
    ```
3. 第三步：添加注解：
    ```java
    @Mapper
    public interface EmployeeMapper {
        @Select("select * from sys_static where id=#{id}")
        SysConfig findOne(Integer id);
    }
    ```
--------
那么如果我们想要做一个这样的基于Spring Boot的组件，要怎么开发呢，主要有以下几个步骤：
1. 第一步：创建一个Maven项目，此项目本身只是个壳，代码都在三个子Module里面，这个项目的意义是把三个子Module的公共pom依赖放到一起，这样方便管理依赖。具体参考项目中的[**pom**](https://github.com/harker2015/example-component-spring-boot/blob/master/pom.xml)
2. 第二步：创建三个子Module
    - 核心类子Module：里面定义的组件的核心功能，这个是组件的核心，功能实现都在这里。
    - autoconfigure子Module：Spring Boot应用在启动的时候会自动扫描的配置类，这里面主要有两个东西，一个是[**自动配置类**](https://github.com/harker2015/example-component-spring-boot/blob/master/example-component-spring-boot-autoconfigure/src/main/java/org/github/harker2015/example/component/spring/boot/autoconfigure/ExampleComponentAutoConfiguration.java)，另外一个是resources/META-INF目录下要创建[**spring.factories**](https://github.com/harker2015/example-component-spring-boot/blob/master/example-component-spring-boot-autoconfigure/src/main/resources/META-INF/spring.factories)文件(这里的路径不要写错了，不然扫描不到)，并把自动配置类写入，这样Spring Boot启动的时候就可以扫描到了。
    - starter子Module：简化依赖用，项目只有一个[**pom**](https://github.com/harker2015/example-component-spring-boot/blob/master/example-component-spring-boot-starter/pom.xml)，依赖了autoconfigure子Module和核心类子Module。
3. 第三步：编译并打包到Maven私服，如果自己测试用，可以只install到本地仓库
4. 第四步：新建一个项目并引用此组件，通过两板斧，即可开箱即用
    - 第一板：引入依赖
    ```xml
        <dependency>
            <groupId>org.github.harker2015</groupId>
            <artifactId>example-component-spring-boot-starter</artifactId>
            <version>1.0.0</version>
        </dependency>
    ```
    - 第二板：添加注解
    ```
        @Autowired
        LocalCache localCache;
    ```
    - 使用：
    ```java
    localCache.set("test", "123456");
    localCache.get("test");
    ```
