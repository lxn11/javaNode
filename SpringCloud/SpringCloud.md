# Cloud简介



## Spring Cloud的是什么？



Spring Cloud为开发人员提供了工具来快速构建分布式系统中的一些常见模式(例如**配置管理、服务发现、断路器、智能路由、微代理、控制总线、一次性令牌、全局锁、领导选举、分布式会话、集群状态**)。

协调分布式系统有固定样板模型，使用Spring Cloud开发人员可以快速地搭建基于实现了这些模型的服务和应用程序。他们将在任何分布式环境中工作，包括开发人员自己的笔记本电脑，裸机数据中心，和管理的平台，如云计算。



## SpringCloud版本



- 采用版本名+版本号，其中版本名采用伦敦地铁站命名，其中按照地铁首字母A-Z依次命令如Hoxton.SR9。但是现在已更改为主版本号.次版本号.修订号如2020.0.0
- 旧版本命名方式中,开发的快照版本(BUILD-SNAPSHOT)到里程碑版本(M),开发的差不多到会发布的候选发布版(RELEASE),最后到正式版(SR)版本。
- 新版本命名是`YYYY.MINOR.MICRO[-MODIFIER]`，拿**2020.0.1-SNAPSHOT** 这个版本来说，其中YYYY为年份全称、MINOR为辅助版本号、MICRO为补丁版本号。MODIFIER同上述修饰关键节点，BUILD-SNAPSHOT、里程碑M等。

SNAPSHOT 版本：正在开发中的快照版本，例如 2021.0.0-SNAPSHOT，快照版代表当前分支最新的代码进度，也是更新最为频繁的小版本类型，不推荐在线上正式环境使用；

Milestone 版本：在大版本正式发布前的里程碑版本，例如 2021.0.0-M1，M1 代表当前大版本的第一个里程碑版本，M2 代表第二个迭代里程碑，以此类推。在正式版本发布之前要经历多个里程碑的迭代，像 Spring Cloud Finchley 版足足经历了 9 个 M 版本之后，才过渡到了 RC 版。同样地，我也不推荐你在正式项目中使用 Milestone 版本；

Release Candidate 版本：这就是我们俗称的 RC 版，例如 2021.0.0-RC1。当一个版本迭代到 RC 版的时候，意味着离正式发布已经不远了。但是你要注意，RC 版是发布前的候选版本，走到这一步通常已经没有新的功能开发，RC 主要目的是开放出来让大家试用并尽量修复严重 Bug。

Release 版：稳定的正式发布版，比如 2020.0.1。你可以在自己的线上业务中放心使用 Release 稳定版。





## Cloud版本和Boot版本怎么选择



> 与springboot版本对应:https://start.spring.io/actuator/info

```java
{
      "Hoxton.SR12": "Spring Boot >=2.2.0.RELEASE and <2.4.0.M1",
      "2020.0.5": "Spring Boot >=2.4.0.M1 and <2.6.0-M1",
      "2021.0.0-M1": "Spring Boot >=2.6.0-M1 and <2.6.0-M3",
      "2021.0.0-M3": "Spring Boot >=2.6.0-M3 and <2.6.0-RC1",
      "2021.0.0-RC1": "Spring Boot >=2.6.0-RC1 and <2.6.1",
      "2021.0.1": "Spring Boot >=2.6.1 and <2.6.5-SNAPSHOT",
      "2021.0.2-SNAPSHOT": "Spring Boot >=2.6.5-SNAPSHOT and <3.0.0-M1",
      "2022.0.0-M1": "Spring Boot >=3.0.0-M1 and <3.1.0-M1"
}
```



##  AlibabaCloud 以及对应的适配Cloud 和 Boot 版本关系



https://github.com/alibaba/spring-cloud-alibaba/wiki/%E7%89%88%E6%9C%AC%E8%AF%B4%E6%98%8E



下表为按时间顺序发布的 Spring Cloud Alibaba 以及对应的适配 Spring Cloud 和 Spring Boot 版本关系（由于 Spring Cloud 版本命名有调整，所以对应的 Spring Cloud Alibaba 版本号也做了对应变化）

| Spring Cloud Alibaba Version      | Spring Cloud Version        | Spring Boot Version |
| --------------------------------- | --------------------------- | ------------------- |
| 2021.0.1.0                        | Spring Cloud 2021.0.1       | 2.6.3               |
| 2.2.7.RELEASE                     | Spring Cloud Hoxton.SR12    | 2.3.12.RELEASE      |
| 2021.1                            | Spring Cloud 2020.0.1       | 2.4.2               |
| 2.2.6.RELEASE                     | Spring Cloud Hoxton.SR9     | 2.3.2.RELEASE       |
| 2.1.4.RELEASE                     | Spring Cloud Greenwich.SR6  | 2.1.13.RELEASE      |
| 2.2.1.RELEASE                     | Spring Cloud Hoxton.SR3     | 2.2.5.RELEASE       |
| 2.2.0.RELEASE                     | Spring Cloud Hoxton.RELEASE | 2.2.X.RELEASE       |
| 2.1.2.RELEASE                     | Spring Cloud Greenwich      | 2.1.X.RELEASE       |
| 2.0.4.RELEASE(停止维护，建议升级) | Spring Cloud Finchley       | 2.0.X.RELEASE       |
| 1.5.1.RELEASE(停止维护，建议升级) | Spring Cloud Edgware        | 1.5.X.RELEASE       |



## CloudAlibaba版本及其自身所适配的各组件对应版本



https://github.com/alibaba/spring-cloud-alibaba/wiki/%E7%89%88%E6%9C%AC%E8%AF%B4%E6%98%8E

每个 Spring Cloud Alibaba 版本及其自身所适配的各组件对应版本（经过验证，自行搭配各组件版本不保证可用）如下表所示（最新版本用*标记）：

| Spring Cloud Alibaba Version                              | Sentinel Version | Nacos Version | RocketMQ Version | Dubbo Version | Seata Version |
| --------------------------------------------------------- | ---------------- | ------------- | ---------------- | ------------- | ------------- |
| 2021.0.1.0*                                               | 1.8.3            | 1.4.2         | 4.9.2            | 2.7.15        | 1.4.2         |
| 2.2.7.RELEASE                                             | 1.8.1            | 2.0.3         | 4.6.1            | 2.7.13        | 1.3.0         |
| 2.2.6.RELEASE                                             | 1.8.1            | 1.4.2         | 4.4.0            | 2.7.8         | 1.3.0         |
| 2021.1 or 2.2.5.RELEASE or 2.1.4.RELEASE or 2.0.4.RELEASE | 1.8.0            | 1.4.1         | 4.4.0            | 2.7.8         | 1.3.0         |
| 2.2.3.RELEASE or 2.1.3.RELEASE or 2.0.3.RELEASE           | 1.8.0            | 1.3.3         | 4.4.0            | 2.7.8         | 1.3.0         |
| 2.2.1.RELEASE or 2.1.2.RELEASE or 2.0.2.RELEASE           | 1.7.1            | 1.2.1         | 4.4.0            | 2.7.6         | 1.2.0         |
| 2.2.0.RELEASE                                             | 1.7.1            | 1.1.4         | 4.4.0            | 2.7.4.1       | 1.0.0         |
| 2.1.1.RELEASE or 2.0.1.RELEASE or 1.5.1.RELEASE           | 1.7.0            | 1.1.4         | 4.4.0            | 2.7.3         | 0.9.0         |
| 2.1.0.RELEASE or 2.0.0.RELEASE or 1.5.0.RELEASE           | 1.6.3            | 1.1.1         | 4.4.0            | 2.7.3         | 0.7.1         |



## 目前所使用的Cloud组件



<img src="images/image-20220315175550792.png" alt="image-20220315175550792" style="zoom:80%;" />



<img src="images/image-20220315185300579.png" alt="image-20220315185300579" style="zoom:80%;" />



## 相关网址



+ Git源码地址：https://github.com/spring-projects/spring-cloud
+ 官网：https://spring.io/projects/spring-cloud
+ 已发布的稳定版：https://mvnrepository.com/artifact/org.springframework.cloud/spring-cloud-dependencies
+ Cloud与Boot对应关系:https://start.spring.io/actuator/info
+ 中文文档：https://www.bookstack.cn/read/spring-cloud-docs/README.md





# CAP

## 由来



CAP 这个概念最初是由埃里克·布鲁尔博士（Dr. Eric Brewer）在 2000 年的 ACM 年度学术研讨会上提出的。如果你对这次演讲感兴趣的话，可以翻阅他那次名为“Towards Robust Distributed Systems”的演讲 deck。在两年之后，塞思·吉尔伯特（Seth Gilbert）和麻省理工学院的南希·林奇教授（Nancy Ann Lynch）在他们的论文`“Brewer’s conjecture and the Feasibility of Consistent, Available, Partition-Tolerant Web Services”`中证明了这一概念。

他们在这篇论文中证明了：在任意的分布式系统中，**一致性（Consistency）**，**高可用性（Availability）**和**分区容错性（Partition-tolerance）**这三种属性最多只能同时存在两个属性。

<img src="images/image-20220315181200687.png" alt="image-20220315181200687" style="zoom:50%;" />



## CAP简介



|      | 描述       |
| ---- | ---------- |
| C    | 一致性     |
| A    | 高可用性   |
| P    | 分区容错性 |



## C(一致性)



一致性在这里指的是**线性一致性（Linearizability Consistency）**。在线性一致性的保证下，所有分布式环境下的操作都像是在单机上完成的一样，也就是说图中 Sever A、B、C 的状态一直是一致的。

<img src="images/image-20220315182618481.png" alt="image-20220315182618481" style="zoom:80%;" />

> **例子：**



假设我们设计了一个分布式的购物系统，在这个系统中，商品的存货状态分别保存在服务器 A 和服务器 B 中。我们把存货状态定义为“有货状态”或者“无货状态”。

在最开始的时候，服务器 A 和服务器 B 都会显示商品为有货状态。

等一段时间过后，商品卖完了，后台就必须将这两台服务器上的商品状态更新为无货状态。因为是在分布式的环境下，商品状态的更新在服务器 A 上完成了，显示为无货状态。而服务器 B 的状态因为网络延迟的原因更新还未完成，还是显示着有货状态。

这时，恰好有两个用户使用着这个购物系统，先后发送了一个查询操作（Query Operation）到后台服务器中查询商品状态。

我们假设是用户 A 先查询的，这个查询操作 A 被发送到了服务器 A 上面，并且成功返回了商品是无货状态的。

用户 B 在随后也对同一商品进行查询，而这个查询操作 B 被发送到了服务器 B 上面，并且成功返回了商品是有货状态的。

我们知道，对于整个系统来说，商品的系统状态应该为无货状态。而操作 A 又是在操作 B 之前发送并且成功完成的，所以如果这个系统有线性一致性这个属性的话，操作 B 所看到的系统状态理论上应该是无货状态。

**但在我们这个例子中，操作 B 却返回了有货状态。所以我们说，这个分布式的购物系统并不满足论文里所讲到的线性一致性。聊完了一致性，我们一起来看看可用性的含义。**



## A(高可用性)



可用性的概念比较简单，在这里指的是在分布式系统中，任意非故障的服务器都必须对客户的请求产生响应。

当系统满足可用性的时候，不管出现什么状况（除非所有的服务器全部崩溃），都能返回消息。

**也就是说，当客户端向系统发送请求，只要系统背后的服务器有一台还未崩溃，那么这个未崩溃的服务器必须最终响应客户端。**



<img src="images/image-20220315183213812.png" alt="image-20220315183213812" style="zoom:80%;" />







## P(分区容错性)



在了解了可用性之后，你还需要了解分区容错性。

**它分为两个部分，“分区”和“容错”。**

在一个分布式系统里，如果出现一些故障，可能会使得部分节点之间无法连通。由于这些故障节点无法联通，造成整个网络就会被分成几块区域，从而使数据分散在这些无法连通的区域中的情况，你可以认为这就是发生了分区错误。

如图所示，如果你要的数据只在 Sever A 中保存，当系统出现分区错误，在不能直接连接 Sever A 时，你是无法获取数据的。我们要“分区容错”，意思是即使出现这样的“错误”，系统也需要能“容忍”。也就是说，就算错误出现，系统也必须能够返回消息。

分区容错性，**在这里指的是我们的系统允许网络丢失从一个节点发送到另一个节点的任意多条消息。**

<img src="images/image-20220315183701620.png" alt="image-20220315183701620" style="zoom:80%;" />



## CAP如何选择



我们知道，在现代网络通信中，节点出现故障或者网络出现丢包这样的情况是时常会发生的。

如果没有了分区容错性，也就是说系统不允许这些节点间的通讯出现任何错误的话，那我们日常所用到的很多系统就不能再继续工作了。所以在大部分情况下，**系统设计都会保留 P 属性，而在 C 和 A 中二选一。**

在任意系统中，我们最多可以保留 CAP 属性中的两种，也就是 CP 或者 AP 或者 CA。



CP 系统：Google BigTable, Hbase, MongoDB, Redis, MemCacheDB，这些存储架构都是放弃了高可用性（High Availablity）而选择 CP 属性的。

AP 系统：Amazon Dynamo 系统以及它的衍生存储系统 Apache Cassandra 和 Voldemort 都是属于 AP 系统CA 系统：Apache Kafka 是一个比较典型的 CA 系统。

我在上面说过，P 属性在现代网络时代中基本上是属于一个必选项，那为什么 Apache Kafka 会放弃 P 选择 CA 属性呢？我来给你解释一下它的架构思想。



## 放弃了P属性的 Kafka



在 Kafka 发布了 0.8 版本之后，Kafka 系统引入了 Replication 的概念。Kafka Relocation 通过将数据复制到不同的节点上，从而增强了数据在系统中的持久性（Durability）和可用性（Availability）。在 Kafka Replication 的系统设计中，所有的数据日志存储是设计在同一个数据中心（Data Center）里面的，也就是说，在同一个数据中心里网络分区出现的可能性是十分之小的。

它的具体架构是这样的，在 Kafka 数据副本（Data Replication）的设计中，先通过 Zookeeper 选举出一个领导者节点（Leader）。这个领导者节点负责维护一组被称作同步数据副本（In-sync-replica）的节点，所有的数据写入都必须在这个领导者节点中记录。

我来举个例子，假设现在数据中心有三台服务器，一台被选为作为领导者节点，另外两台服务器用来保存数据副本，分别是 Replication1 和 Replication2，它们两个节点就是被领导者节点维护的同步数据副本了。领导者节点知道它维护着两个同步数据副本。

如果用户想写入一个数据，假设是“Geekbang”

1. 用户会发请求到领导者节点中想写入“Geekbang”。
2. 领导者节点收到请求后先在本地保存好，然后也同时发消息通知 eplication1 和 Replication2。
3. Replication1 和 Replication2 收到消息后也保存好这条消息并且回复领导者节点写入成功。
4. 领导者节点记录副本 1 和副本 2 都是健康（Healthy）的，并且回复用户写入成功。



**红色的部分是领导者节点本地日志，记录着有哪些同步数据副本是健康的。**

<img src="images/image-20220315184355077.png" alt="image-20220315184355077" style="zoom:80%;" />

往后用户如果想查询写入的数据，无论是领导者节点还是两个副本都可以返回正确同步的结果。

那假如分区出现了该怎么办呢？例如领导者节点和副本 1 无法通讯了，这个时候流程就变成这样了。



1. 用户会发请求到领导者节点中想写入“Geekbang”。

2. 领导者节点收到请求后先在本地保存好，然后也同时发消息通知 Replication1 和 Replication2。

3. 只有 Replication2 收到消息后也保存好这条消息并且回复领导者节点写入成功。

4. 领导者节点记录副本 2 是健康的，并且回复用户写入成功。



**同样，红色的部分是领导者节点本地日志，记录着有哪些同步数据副本是健康的。**

<img src="images/image-20220315184650476.png" alt="image-20220315184650476" style="zoom:80%;" />



如果所有副本都无法通讯的时候，Apache Kafka 允许系统只有一个节点工作，也就是领导者节点。这个时候所有的写入都只保存在领导者节点了。过程如下，

1. 用户会发请求到领导者节点中想写入“Geekbang”。
2. 领导者节点收到请求后先在本地保存好，然后也同时发消息通知 Replication1 和 Replication2。
3. 没有任何副本回复领导者节点写入成功，领导者节点记录无副本是健康的，并且回复用户写入成功。

<img src="images/image-20220315184729636.png" alt="image-20220315184729636" style="zoom:80%;" />



# 项目模块建立





