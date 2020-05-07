# example-component-spring-boot
本项目是基于Spring Boot的组件式开发示例

>我们都知道Spring Boot简化了配置，开发者只需要引入一个spring boot的包，其他的复杂的依赖Spring Boot已经帮我们解决了。在使用基于Spring Boot组件时，是非常简单的，比如我们会用到MyBatis这个组件，我们只需要三步即可使用这个组件：
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

>那么如果我们想要做一个这样的基于Spring Boot的组件，要怎么开发呢，主要有以下几个步骤：
1. 第一步：创建一个Maven项目
2. 第二步：创建三个子Module
    - 核心类子Module：里面定义的组件的核心功能，这个是组件的核心，功能实现都在这里。
    - autoconfigure子Module：Spring Boot应用在启动的时候会自动扫描的配置类，这里面主要有两个东西，一个是自动配置类，一个是resources/META-INF目录下要创建spring.factories文件，并把自动配置类写入，这样Spring Boot引用启动的时候才能扫描到。
    - starter子Module：简化依赖用，项目只有一个pom，依赖了autoconfigure子Module和核心类子Module。
3. 第三步：编译并打包到Maven私服
4. 第四步：新建一个项目并引用此组件，即可开箱即用
