<h1>MyBatisPlus</h1>



----------

# 目录

[TOC]

# 一、简介



## 1.1：官方简介



MyBatis-Plus (opens new window)（简称 MP）是一个 MyBatis (opens new window)的增强工具，在 MyBatis 的基础上只做增强不做改变，为简化开发、提高效率而生。

我们的愿景是成为 MyBatis 最好的搭档，就像 魂斗罗 中的 1P、2P，基友搭配，效率翻倍。



官网：https://mybatis.plus/ 或 https://mp.baomidou.com/

**代码以及文档**

文档地址：https://mybatis.plus/guide/

源码地址：https://github.com/baomidou/mybatis-plus

<img src="images/image-20210816174059113.png" alt="image-20210816174059113" style="zoom:80%;" />



## 1.2：特性



| name                               | message                                                      |
| ---------------------------------- | ------------------------------------------------------------ |
| **无侵入**                         | 只做增强不做改变，引入它不会对现有工程产生影响，如丝般顺滑   |
| **损耗小**                         | 启动即会自动注入基本 CURD，性能基本无损耗，直接面向对象操作  |
| **强大的** **CRUD** **操作**       | 内置通用 Mapper、通用 Service，仅仅通过少量配置即可实现单表大部分 CRUD 操作，更有强大的条件构造器，满足各类使用需求 |
| **支持** **Lambda** **形式调用**   | 通过 Lambda 表达式，方便的编写各类查询条件，无需再担心字段写错 |
| **支持多种数据库**                 | 支持 MySQL、MariaDB、Oracle、DB2、H2、HSQL、SQLite、Postgre、 |
| **支持主键自动生成**               | 支持多达 4 种主键策略（内含分布式唯一 ID 生成器 - Sequence），可自由配置，完美解 |
| **支持** **XML** **热加载**        | Mapper 对应的 XML 支持热加载，对于简单的 CRUD 操作，甚至可以无 XML 启动 |
| **支持** **ActiveRecord** **模式** | 支持 ActiveRecord 形式调用，实体类只需继承 Model 类即可进行强大的 CRUD 操 作 |
| **支持自定义全局通用操作**         | 支持全局通用方法注入（ Write once, use anywhere ）           |
| **支持关键词自动转义**             | 支持数据库关键词（order、key......）自动转义，还可自定义关键词 |
| **内置代码生成器**                 | 采用代码或者 Maven 插件可快速生成 Mapper 、 Model 、 Service 、 Controller 层代码，支持模板引擎，更有超多自定义配置等您来使用 |
| **内置分页插件**                   | 基于 MyBatis 物理分页，开发者无需关心具体操作，配置好插件之后，写分页等同于普通 List查询 |
| **内置性能分析插件**               | 可输出 Sql 语句以及其执行时间，建议开发测试时启用该功能，能快速揪出慢查询 |
| **内置全局拦截插件**               | 提供全表 delete 、 update 操作智能分析阻断，也可自定义拦截规则，预防误操作 |
| **内置** **Sql** **注入剥离器**    | 支持 Sql 注入剥离，有效预防 Sql 注入攻击                     |



## 1.3：架构设计



<img src="images/image-20210816174757638.png" alt="image-20210816174757638" style="zoom:80%;" />






























































