# Eureka



## 简介



### Spring-Cloud Euraka介绍



Spring-Cloud Euraka是Spring Cloud集合中一个组件，它是对Euraka的集成，用于服务注册和发现。Eureka是Netflix中的一个开源框架。它和 zookeeper、Consul一样，都是用于服务注册管理的，同样，Spring-Cloud 还集成了Zookeeper和Consul。

在项目中使用Spring Cloud Euraka的原因是**它可以利用Spring Cloud Netfilix中其他的组件，如zull等，因为Euraka是属于Netfilix的。**



### Euraka介绍



Eureka由多个instance(服务实例)组成，这些服务实例可以分为两种：Eureka Server和Eureka Client。为了便于理解，我们将Eureka client再分为Service Provider和Service Consumer。

- Eureka Server 提供服务注册和发现
- Service Provider 服务提供方，将自身服务注册到Eureka，从而使服务消费方能够找到
- Service Consumer服务消费方，从Eureka获取注册服务列表，从而能够消费服务



### Eureka Server和Eureka Client



Eureka包含两个组件：

+ **Eureka Server提供服务注册服务**

各个微服务节点通过配置启动后，会在EurekaServer中进行注册，这样EurekaServer中的服务注册表中将会存储所有可用服务节点的信息，服务节点的信息可以在界面中直观看到。

+ **EurekaClient通过注册中心进行访问**

是一个Java客户端，用于简化Eureka Server的交互，客户端同时也具备一个内置的、使用轮询(round-robin)负载算法的负载均衡器。在应用启动后，将会向Eureka Server发送心跳(默认周期为30秒)。如果Eureka Server在多个心跳周期内没有接收到某个节点的心跳，EurekaServer将会从服务注册表中把这个服务节点移除（默认90秒）





## pom文件



+ 以前的老版本（当前使用2018）

```java
<dependency>
        <groupId>org.springframework.cloud</groupId>
        <artifactId>spring-cloud-starter-eureka</artifactId>
</dependency>
```



+ 现在新版本（当前使用2020.2）

```java
<!--eureka服务端-->
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-netflix-eureka-server</artifactId>
</dependency>
<!--eureka客户端-->    
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
</dependency>    
```



## 父工程-cloud-eureka



```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <parent>
        <artifactId>spring-cloud</artifactId>
        <groupId>com.xht.example.cloud</groupId>
        <version>1.0-SNAPSHOT</version>
    </parent>
    <modelVersion>4.0.0</modelVersion>
    <packaging>pom</packaging>
    <modules>
        <module>cloud-eureka-server7001</module>
    </modules>
    <artifactId>cloud-eureka</artifactId>

    <properties>
        <maven.compiler.source>8</maven.compiler.source>
        <maven.compiler.target>8</maven.compiler.target>
    </properties>
    <dependencies>
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-netflix-eureka-server</artifactId>
        </dependency>
    </dependencies>
</project>
```



## 服务端-cloud-eureka-server7001



### pom



```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <parent>
        <artifactId>cloud-eureka</artifactId>
        <groupId>com.xht.example.cloud</groupId>
        <version>1.0-SNAPSHOT</version>
    </parent>
    <modelVersion>4.0.0</modelVersion>
    <artifactId>cloud-eureka-server7001</artifactId>
    <properties>
        <maven.compiler.source>8</maven.compiler.source>
        <maven.compiler.target>8</maven.compiler.target>
    </properties>
</project>
```



### 启动类



```java
package com.it;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * 描述 ：EurekaServer7001
 *
 * @author : 小糊涂
 * @version : 1.0
 **/
@EnableEurekaServer
@SpringBootApplication
public class EurekaServer7001 {
    public static void main(String[] args) {
        SpringApplication.run(EurekaServer7001.class,args);
    }
}
```



### yaml配置



```yaml
server:
  port: 7001

spring:
  application:
    name: eureka-server-7001
eureka:
  instance:
    hostname: 127.0.0.1
  client:
    register-with-eureka: false #不需要王eureka注册中心中注册自己
    fetch-registry: false   #false表示自己端就是注册中心，我的职责就是维护服务实例，并不需要去检索服务
    service-url:
      defaultZone: http://${eureka.instance.hostname}:${server.port}/eureka/ #设置与Eureka Server交互的地址查询服务和注册服务都需要依赖这个地址。
```





### 测试



http://localhost:7001/

<img src="images/image-20220319114602443.png" alt="image-20220319114602443" style="zoom:67%;" />





## 支付服务提供者注册Eureka



### eureka-provider-payment8001



```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <parent>
        <artifactId>cloud-provider</artifactId>
        <groupId>com.xht.example.cloud</groupId>
        <version>1.0-SNAPSHOT</version>
    </parent>
    <modelVersion>4.0.0</modelVersion>

    <artifactId>eureka-provider-payment8001</artifactId>

    <properties>
        <maven.compiler.source>8</maven.compiler.source>
        <maven.compiler.target>8</maven.compiler.target>
    </properties>
    <dependencies>
        <!--eureka客户端-->
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
        </dependency>
        <!--热部署插件-->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-devtools</artifactId>
            <scope>runtime</scope>
            <optional>true</optional>
        </dependency>
    </dependencies>
</project>
```



### 复制代码



复制 `demo-provider-payment8001`里面的代码和配置文件到 `eureka-provider-payment8001`

修改启动类名字：`EurekaPaymentAPP8001`

<img src="images/image-20220319120251381.png" alt="image-20220319120251381" style="zoom:80%;" />

### application.yaml



```yaml
server:
  port: 8001

spring:
  application:
    name: eureka-payment-service
  datasource:
    username: root
    password: 123456
    driver-class-name: com.mysql.jdbc.Driver
    url: jdbc:mysql://127.0.0.1:3306/test?useSSL=false&allowPublicKeyRetrieval=true&useUnicode=true&characterEncoding=utf-8&serverTimezone=GMT%2B8
  boot:
    admin:
      client:
        url: http://localhost:5555
        instance:
          prefer-ip: true
#开放端点用于SpringBoot Admin的监控
management:
  endpoints:
    web:
      exposure:
        include: '*'

logging:
  file:
    name: admin-client.log  #配置生成日志文件名称
mybatis:
  mapperLocations: classpath:mapper/*.xml
  type-aliases-package: com.it.common.entity    # 所有Entity别名类所在包

# eureka 的配置
eureka:
  instance:
    hostname: 127.0.0.1
  client:
    fetch-registry: true  #是否从EurekaServer抓取已有的注册信息，默认为true。单节点无所谓，集群必须设置为true才能配合ribbon使用负载均衡
    register-with-eureka: true #向eureka服务注册中心中注册自己
    service-url:
      defaultZone: http://localhost:7001/eureka
```



### 启动类



```java
@EnableEurekaClient  //添加eureka客户端的注解
@SpringBootApplication
public class EurekaPaymentAPP8001 {
    public static void main(String[] args) {
        SpringApplication.run(EurekaPaymentAPP8001.class, args);
    }
}
```





### 测试



访问： http://localhost:7001/

<img src="images/image-20220319121023422.png" alt="image-20220319121023422" style="zoom:67%;" />





## 支付服务消费者注册Eureka



### eureka-consumer-order80



```java
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <parent>
        <artifactId>cloud-consumer</artifactId>
        <groupId>com.xht.example.cloud</groupId>
        <version>1.0-SNAPSHOT</version>
    </parent>
    <modelVersion>4.0.0</modelVersion>

    <artifactId>eureka-consumer-order80</artifactId>

    <properties>
        <maven.compiler.source>8</maven.compiler.source>
        <maven.compiler.target>8</maven.compiler.target>
    </properties>
    <dependencies>
        <!--eureka客户端-->
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
        </dependency>
        <!--热部署插件-->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-devtools</artifactId>
            <scope>runtime</scope>
            <optional>true</optional>
        </dependency>
    </dependencies>
</project>
```



### 复制代码



复制 `demo-consumer-order80`里面的代码和配置文件到 `eureka-consumer-order80`

修改启动类名字：`EurekaOrderAPP80`

<img src="images/image-20220319132245817.png" alt="image-20220319132245817" style="zoom:80%;" />



### applcation.yaml



```java
server:
  port: 80

eureka:
  instance:
    hostname: 127.0.0.1
  client:
    register-with-eureka: true
    fetch-registry: true
    service-url:
      defaultZone: http://localhost:7001/eureka
spring:
  application:
    name: eureka-consumer-order
```



### 启动类



```java
package com.it;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * 描述 ：
 *
 * @author : 小糊涂
 * @version : 1.0
 **/
@EnableEurekaClient
@SpringBootApplication
public class EurekaOrderAPP80 {
    public static void main(String[] args) {
        SpringApplication.run(EurekaOrderAPP80.class, args);
    }
}
```



### 测试



http://localhost:7001/

<img src="images/image-20220319132352147.png" alt="image-20220319132352147" style="zoom:80%;" />





## 服务发现Discovery



### 简介



对于注册进eureka里面的微服务，可以通过服务发现来获得该服务的信息



### 添加Controller



```java
    @GetMapping(value = "/payment/discovery")
    public Object discovery()
    {
        List<String> services = discoveryClient.getServices();
        for (String element : services) {
            System.out.println(element);
        }

        List<ServiceInstance> instances = discoveryClient.getInstances("EUREKA-PAYMENT-SERVICE");
        for (ServiceInstance element : instances) {
            System.out.println(element.getServiceId() + "\t" + element.getHost() + "\t" + element.getPort() + "\t"
                    + element.getUri());
        }
        return this.discoveryClient;
    }
```



### 启动类



添加： @EnableDiscoveryClient //服务注册与发现



### 测试



请求：http://localhost:8001/payment/discovery

<img src="images/image-20220319133145838.png" alt="image-20220319133145838" style="zoom:80%;" />



## 自我保护



### 现象



我们过一段时间去看eureka的页面，会发现：

保护模式主要用于一组客户端和Eureka Server之间存在网络分区场景下的保护。一旦进入保护模式，
Eureka Server将会尝试保护其服务注册表中的信息，不再删除服务注册表中的数据，也就是不会注销任何微服务。

如果在Eureka Server的首页看到以下这段提示，则说明Eureka进入了保护模式：
**EMERGENCY! EUREKA MAY BE INCORRECTLY CLAIMING INSTANCES ARE UP WHEN THEY'RE NOT.** 
**RENEWALS ARE LESSER THAN THRESHOLD AND HENCE THE INSTANCES ARE NOT BEING EXPIRED JUST TO BE SAFE**

<img src="images/image-20220319134627956.png" alt="image-20220319134627956" style="zoom:80%;" />



### GitHub上的简介



Eureka 客户端尝试与同一区域中的 Eureka Server 通信。如果与服务器通信时出现问题，或者如果服务器不在同一区域中，则客户端将故障转移到其他区域中的服务器。

一旦服务器开始接收流量，在服务器上执行的所有[操作](https://github.com/Netflix/eureka/wiki/Understanding-eureka-client-server-communication)都会复制到服务器知道的所有对等节点。如果某项操作由于某种原因失败，则该信息将在下一个心跳信号中进行协调，该心跳信号也会在服务器之间复制。

当 Eureka 服务器启动时，它会尝试从相邻节点获取所有实例注册信息。如果从节点获取信息时出现问题，服务器会在放弃之前尝试所有对等方。如果服务器能够成功获取所有实例，它会根据该信息设置它应该接收的续订阈值。如果任何时候续订低于为该值配置的百分比（在 15 分钟内低于 85%），服务器将停止使实例过期以保护当前实例注册表信息。

在 Netflix 中，上述保护被称为**自我保护**模式，主要用于在一组客户端和 Eureka Server 之间存在网络分区的场景中作为保护。在这些情况下，服务器会尝试保护它已经拥有的信息。在大规模中断的情况下，可能会导致客户端获取不再存在的实例。客户端必须确保它们对 eureka 服务器返回不存在或无响应的实例具有弹性。在这些情况下最好的保护是快速超时并尝试其他服务器。

在服务器无法从相邻节点获取注册信息的情况下，它会等待几分钟（5 分钟），以便客户端可以注册他们的信息。服务器通过将流量仅倾斜到一组实例并导致容量问题来努力不向那里的客户端提供部分信息。



### 为什么会产生Eureka自我保护机制？



为了防止EurekaClient可以正常运行，但是 与 EurekaServer网络不通情况下，EurekaServer不会立刻将EurekaClient服务剔除



### 什么是自我保护模式？





如果Eureka Server在一定时间内没有接收到某个微服务实例的心跳，Eureka Server将会注销该实例（默认90秒）。但是当网络分区故障发生时，微服务与Eureka Server之间无法正常通信，此时会触发Eureka Server进入保护模式，进入自我保护模式后，将会保护服务注册表中的信息，不再删除服务注册表中的数据。

Eureka通过“自我保护模式”来解决这个问题——当EurekaServer节点在短时间内丢失过多客户端时（可能发生了网络分区故障），那么这个节点就会进入自我保护模式。



<img src="images/image-20220319134924025.png" alt="image-20220319134924025" style="zoom:80%;" />



**在自我保护模式中，Eureka Server会保护服务注册表中的信息，不再注销任何服务实例。**

它的设计哲学就是宁可保留错误的服务注册信息，也不盲目注销任何可能健康的服务实例。一句话讲解：好死不如赖活着

**综上，自我保护模式是一种应对网络异常的安全保护措施**。它的架构哲学是宁可同时保留所有微服务（健康的微服务和不健康的微服务都会保留）也不盲目注销任何健康的微服务。**使用自我保护模式，可以让Eureka集群更加的健壮、稳定。**



### 一句话概述



一句话：某时刻某一个微服务不可用了，Eureka不会立刻清理，依旧会对该微服务的信息进行保存

属于CAP里面的AP分支



### 服务端禁止自我保护



在eureka服务端的application.yaml配置文件中加上

```yaml
eureka:
  server:
    enable-self-preservation: false #关闭自我保护机制，保证不可用服务被及时踢除
    eviction-interval-timer-in-ms: 2000
```



<img src="images/image-20220319135731410.png" alt="image-20220319135731410" style="zoom:80%;" />





### 客户端禁止自我保护



```yaml
eureka:
  client: #服务提供者provider注册进eureka服务列表内
    service-url:
      register-with-eureka: true
      fetch-registry: true
      # singleton version
      defaultZone: http://eureka7001.com:7001/eureka
#心跳检测与续约时间
#开发时设置小些，保证服务关闭后注册中心能即使剔除服务
  instance:
  #Eureka客户端向服务端发送心跳的时间间隔，单位为秒(默认是30秒)
    lease-renewal-interval-in-seconds: 1
  #Eureka服务端在收到最后一次心跳后等待时间上限，单位为秒(默认是90秒)，超时将剔除服务
    lease-expiration-duration-in-seconds: 2
```



先启动7001再启动8001再关闭8001，马上就被删除了



## 查看服务实例信息



查看服务实例信息。格式如下

```java
/eureka/apps/{appName}
```



举例如下：



```java
http://localhost:7001/eureka/apps/EUREKA-PAYMENT-SERVICE
```



结果为：



```xml
<application>
    <name>EUREKA-PAYMENT-SERVICE</name>
    <instance>
        <instanceId>DESKTOP-81AEV1P:eureka-payment-service:8001</instanceId>
        <hostName>127.0.0.1</hostName>
        <app>EUREKA-PAYMENT-SERVICE</app>
        <ipAddr>10.0.84.74</ipAddr>
        <status>UP</status>
        <overriddenstatus>UNKNOWN</overriddenstatus>
        <port enabled="true">8001</port>
        <securePort enabled="false">443</securePort>
        <countryId>1</countryId>
        <dataCenterInfo class="com.netflix.appinfo.InstanceInfo$DefaultDataCenterInfo">
            <name>MyOwn</name>
        </dataCenterInfo>
        <leaseInfo>
            <renewalIntervalInSecs>30</renewalIntervalInSecs>
            <durationInSecs>90</durationInSecs>
            <registrationTimestamp>1647669463462</registrationTimestamp>
            <lastRenewalTimestamp>1647669733607</lastRenewalTimestamp>
            <evictionTimestamp>0</evictionTimestamp>
            <serviceUpTimestamp>1647669463462</serviceUpTimestamp>
        </leaseInfo>
        <metadata>
            <management.port>8001</management.port>
        </metadata>
        <homePageUrl>http://127.0.0.1:8001/</homePageUrl>
        <statusPageUrl>http://127.0.0.1:8001/actuator/info</statusPageUrl>
        <healthCheckUrl>http://127.0.0.1:8001/actuator/health</healthCheckUrl>
        <vipAddress>eureka-payment-service</vipAddress>
        <secureVipAddress>eureka-payment-service</secureVipAddress>
        <isCoordinatingDiscoveryServer>false</isCoordinatingDiscoveryServer>
        <lastUpdatedTimestamp>1647669463462</lastUpdatedTimestamp>
        <lastDirtyTimestamp>1647669463460</lastDirtyTimestamp>
        <actionType>ADDED</actionType>
    </instance>
</application>
```





## 优雅停服



我们已经禁止了eureka的自我保护机制，然后我们去关闭服务的话，就会迅速的从eureka里面剔除，但是我们可以使用更好的方式来进行那就是：`spring-boot-actuator`

+ 添加依赖



```java
<dependency>
  <groupId>org.springframework.boot</groupId>
  <artifactId>spring-boot-starter-actuator</artifactId>
</dependency>
```



+ application.yml



```yaml
#开放端点用于SpringBoot Admin的监控
management:
  endpoints:
    web:
      exposure:
        include: '*'
  endpoint:
    shutdown:
      enabled: true
```



+ postMan模拟测试

记得把eureka的自我保护机制打开，否则测试不出来效果：

```java
http://127.0.0.1:8001/actuator/shutdown
```

post方式请求，然后发现请求就停止了





## 集群搭建



### 简介



<img src="images/image-20220319144317699.png" alt="image-20220319144317699" style="zoom:80%;" />

问题：微服务RPC远程服务调用最核心的是什么：**高可用**，

试想你的注册中心只有一个only one， 它出故障了那就呵呵(￣▽￣)"了，会导致整个为服务环境不可用，所以

解决办法：搭建Eureka注册中心集群 ，实现负载均衡+故障容错



### 配置host



打开自己电脑的`hosts`

window：`C:\Windows\System32\drivers\etc`

liunx: `/etc/hosts`



```shell
127.0.0.1		eureka1
127.0.0.1		eureka2
127.0.0.1		eureka3
```





### 创建模块





cloud-eureka-cluster-server7001

cloud-eureka-cluster-server7002

cloud-eureka-cluster-server7003

这三个模块的pom文件都是

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <parent>
        <artifactId>cloud-eureka</artifactId>
        <groupId>com.xht.example.cloud</groupId>
        <version>1.0-SNAPSHOT</version>
    </parent>
    <modelVersion>4.0.0</modelVersion>

    <artifactId>这里写项目的名称</artifactId>

    <properties>
        <maven.compiler.source>8</maven.compiler.source>
        <maven.compiler.target>8</maven.compiler.target>
    </properties>

</project>
```



### 启动类



启动类一摸一样，只是文件名字类名不相同而已

| 项目名称                        | 启动类名称        |
| ------------------------------- | ----------------- |
| cloud-eureka-cluster-server7001 | EurekaCluster7001 |
| cloud-eureka-cluster-server7002 | EurekaCluster7002 |
| cloud-eureka-cluster-server7003 | EurekaCluster7003 |



```java
/**
 * 描述 ：
 *
 * @author : 小糊涂
 * @version : 1.0
 **/
@EnableEurekaServer
@SpringBootApplication
public class 根据项目名称选择模块 {
    public static void main(String[] args) {
        SpringApplication.run(EurekaCluster7003.class, args);
    }
}
```





### application.yaml





cloud-eureka-cluster-server7001



```yaml
server:
  port: 7001

spring:
  application:
    name: cloud-eureka-cluster-server

eureka:
  instance:
    hostname: erureka1
  client:
    fetch-registry: true  #false表示自己端就是注册中心，我的职责就是维护服务实例，并不需要去检索服务
    register-with-eureka: false #不需要向eureka注册中心中注册自己
    service-url:
      defaultZone: http://eureka2:7002/eureka/,http://eureka3:7003/eureka/

```



cloud-eureka-cluster-server7002



```yaml
server:
  port: 7002

spring:
  application:
    name: cloud-eureka-cluster-server

eureka:
  instance:
    hostname: erureka2
  client:
    fetch-registry: true  #false表示自己端就是注册中心，我的职责就是维护服务实例，并不需要去检索服务
    register-with-eureka: false #不需要向eureka注册中心中注册自己
    service-url:
      defaultZone: http://eureka1:7001/eureka/,http://eureka3:7003/eureka/

```



cloud-eureka-cluster-server7003



```yaml
server:
  port: 7003

spring:
  application:
    name: cloud-eureka-cluster-server

eureka:
  instance:
    hostname: erureka3
  client:
    fetch-registry: true  #false表示自己端就是注册中心，我的职责就是维护服务实例，并不需要去检索服务
    register-with-eureka: false #不需要向eureka注册中心中注册自己
    service-url:
      defaultZone: http://eureka1:7001/eureka/,http://eureka2:7002/eureka/

```



### 测试



+ 访问：http://eureka1:7001/ 或者 http://127.0.0.1:7001/



<img src="images/image-20220319160640098.png" alt="image-20220319160640098" style="zoom:80%;" />



+ 访问：http://eureka2:7002/ 或者 http://127.0.0.1:7002/



<img src="images/image-20220319160701666.png" alt="image-20220319160701666" style="zoom:80%;" />![image-20220319160824479](images/image-20220319160824479.png)



+ 访问：http://eureka3:7003/ 或者 http://127.0.0.1:7003/



<img src="images/image-20220319160824479.png" alt="image-20220319160701666" style="zoom:80%;" />





## 订单提供者注册Eureka集群





### eureka-cluster-provider-payment8001



```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <parent>
        <artifactId>cloud-provider</artifactId>
        <groupId>com.xht.example.cloud</groupId>
        <version>1.0-SNAPSHOT</version>
    </parent>
    <modelVersion>4.0.0</modelVersion>

    <artifactId>eureka-cluster-provider-payment8001</artifactId>

    <properties>
        <maven.compiler.source>8</maven.compiler.source>
        <maven.compiler.target>8</maven.compiler.target>
    </properties>
    <dependencies>
        <!--eureka客户端-->
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
        </dependency>
        <!--热部署插件-->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-devtools</artifactId>
            <scope>runtime</scope>
            <optional>true</optional>
        </dependency>
    </dependencies>
</project>
```



### 复制代码



复制 `eureka-provider-payment8001`里面的代码和配置文件到 `eureka-cluster-provider-payment8001`

修改启动类名字：`EurekaClusterPaymentAPP8001`

<img src="images/image-20220319193426168.png" alt="image-20220319193426168" style="zoom:80%;" />



### application.yml



```yaml
server:
  port: 8001

spring:
  application:
    name: eureka-cluster-provider-service
  datasource:
    username: root
    password: 123456
    driver-class-name: com.mysql.jdbc.Driver
    url: jdbc:mysql://127.0.0.1:3306/test?useSSL=false&allowPublicKeyRetrieval=true&useUnicode=true&characterEncoding=utf-8&serverTimezone=GMT%2B8
  boot:
    admin:
      client:
        url: http://localhost:5555
        instance:
          prefer-ip: true
#开放端点用于SpringBoot Admin的监控
management:
  endpoints:
    web:
      exposure:
        include: '*'
  endpoint:
    shutdown:
      enabled: true

logging:
  file:
    name: admin-client.log  #配置生成日志文件名称
mybatis:
  mapperLocations: classpath:mapper/*.xml
  type-aliases-package: com.it.common.entity    # 所有Entity别名类所在包

# eureka 的配置
eureka:
  instance:
    hostname: 127.0.0.1
  client:
    fetch-registry: true  #是否从EurekaServer抓取已有的注册信息，默认为true。单节点无所谓，集群必须设置为true才能配合ribbon使用负载均衡
    register-with-eureka: true #向eureka服务注册中心中注册自己
    service-url:
      defaultZone: http://eureka1:7001/eureka,http://eureka2:7002/eureka,http://eureka3:7003/eureka
```





### 启动类



```java

/**
 * 描述 ：
 *
 * @author : 小糊涂
 * @version : 1.0
 **/
@EnableEurekaClient
@EnableDiscoveryClient //服务注册与发现
@SpringBootApplication
public class EurekaClusterPaymentAPP8001 {
    public static void main(String[] args) {
        SpringApplication.run(EurekaClusterPaymentAPP8001.class, args);
    }
}
```





### 测试



http://127.0.0.1:7001/

http://127.0.0.1:7002/

http://127.0.0.1:7003/

<img src="images/image-20220319193753331.png" alt="image-20220319193753331" style="zoom:80%;" />





## 订单消费者注册到Eureka集群





### eureka-cluster-consumer-order80



```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <parent>
        <artifactId>cloud-consumer</artifactId>
        <groupId>com.xht.example.cloud</groupId>
        <version>1.0-SNAPSHOT</version>
    </parent>
    <modelVersion>4.0.0</modelVersion>

    <artifactId>eureka-cluster-consumer-order80</artifactId>

    <properties>
        <maven.compiler.source>8</maven.compiler.source>
        <maven.compiler.target>8</maven.compiler.target>
    </properties>
    <dependencies>
        <!--eureka客户端-->
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
        </dependency>
        <!--热部署插件-->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-devtools</artifactId>
            <scope>runtime</scope>
            <optional>true</optional>
        </dependency>
    </dependencies>
</project>
```



### 复制代码



复制 `eureka-consumer-order80`里面的代码和配置文件到 `eureka-cluster-consumer-order80`

修改启动类名字：`EurekaClusterOrderAPP80`



### application.yml



```yaml
server:
  port: 80

spring:
  application:
    name: eureka-cluster-consumer-service

eureka:
  instance:
    hostname: 127.0.0.1
  client:
    register-with-eureka: true
    fetch-registry: true
    service-url:
      defaultZone: http://eureka1:7001/eureka,http://eureka2:7002/eureka,http://eureka3:7003/eureka

```



### 启动类



```java
/**
 * 描述 ：
 *
 * @author : 小糊涂
 * @version : 1.0
 **/
@EnableEurekaClient
@SpringBootApplication
public class EurekaClusterOrderAPP80 {
    public static void main(String[] args) {
        SpringApplication.run(EurekaClusterOrderAPP80.class, args);
    }
}
```



### 测试



http://127.0.0.1:7001/

http://127.0.0.1:7002/

http://127.0.0.1:7003/



<img src="images/image-20220319194129423.png" alt="image-20220319194129423" style="zoom:80%;" />





## 订单提供者集群搭建



### eureka-cluster-provider-payment8002



```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <parent>
        <artifactId>cloud-provider</artifactId>
        <groupId>com.xht.example.cloud</groupId>
        <version>1.0-SNAPSHOT</version>
    </parent>
    <modelVersion>4.0.0</modelVersion>

    <artifactId>eureka-cluster-provider-payment8002</artifactId>

    <properties>
        <maven.compiler.source>8</maven.compiler.source>
        <maven.compiler.target>8</maven.compiler.target>
    </properties>
    <dependencies>
        <!--eureka客户端-->
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
        </dependency>
        <!--热部署插件-->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-devtools</artifactId>
            <scope>runtime</scope>
            <optional>true</optional>
        </dependency>
    </dependencies>
</project>
```



### 复制代码



复制 `eureka-cluster-provider-payment8001`里面的代码和配置文件到 `eureka-cluster-provider-payment8002`

修改启动类名字：`EurekaClusterPaymentAPP8002`

<img src="images/image-20220319194912503.png" alt="image-20220319194912503" style="zoom:80%;" />



### application.yml



就端口改变，其他内容不变，切记**服务名一定要一样！**  **服务名一定要一样！**  **服务名一定要一样！**

```yaml
server:
  port: 8002

spring:
  application:
    name: eureka-cluster-provider-service
  datasource:
    username: root
    password: 123456
    driver-class-name: com.mysql.jdbc.Driver
    url: jdbc:mysql://127.0.0.1:3306/test?useSSL=false&allowPublicKeyRetrieval=true&useUnicode=true&characterEncoding=utf-8&serverTimezone=GMT%2B8
  boot:
    admin:
      client:
        url: http://localhost:5555
        instance:
          prefer-ip: true
#开放端点用于SpringBoot Admin的监控
management:
  endpoints:
    web:
      exposure:
        include: '*'
  endpoint:
    shutdown:
      enabled: true

logging:
  file:
    name: admin-client.log  #配置生成日志文件名称
mybatis:
  mapperLocations: classpath:mapper/*.xml
  type-aliases-package: com.it.common.entity    # 所有Entity别名类所在包

# eureka 的配置
eureka:
  instance:
    hostname: 127.0.0.1
  client:
    fetch-registry: true  #是否从EurekaServer抓取已有的注册信息，默认为true。单节点无所谓，集群必须设置为true才能配合ribbon使用负载均衡
    register-with-eureka: true #向eureka服务注册中心中注册自己
    service-url:
      defaultZone: http://eureka1:7001/eureka,http://eureka2:7002/eureka,http://eureka3:7003/eureka
```



### 启动类



```java

/**
 * 描述 ：
 * @author : 小糊涂
 * @version : 1.0
 **/
@EnableEurekaClient
@EnableDiscoveryClient //服务注册与发现
@SpringBootApplication
public class EurekaClusterPaymentAPP8002 {
    public static void main(String[] args) {
        SpringApplication.run(EurekaClusterPaymentAPP8002.class, args);
    }
}
```



### 测试



<img src="images/image-20220319195221885.png" alt="image-20220319195221885" style="zoom:80%;" />





## 疑问？



我们已经搭建了两套服务提供者，那么消费者怎么去访问？？？？？

还记得我们设置的服务名称吗？`eureka-cluster-provider-service`

我们可以这样来改：下一节



## 疑问解决



### 修改代码



修改订单消费者服务：`eureka-cluster-consumer-order80`的OrderController



```java
@RestController
public class OrderController
{

    //public static final String PaymentSrv_URL = "http://localhost:8001";
    public static final String PaymentSrv_URL = "http://eureka-cluster-provider-service";

    @Autowired
    private RestTemplate restTemplate;

 	//以下内容均一样不改动，只修改了调用地址
}
```



### 测试-还是失败



postman请求一下：http://127.0.0.1:80/consumer/payment/get/1

<img src="images/image-20220319200246792.png" alt="image-20220319200246792" style="zoom:80%;" />



出错了





### 错误解决



修改RestTemplateConfig类

```java
@Configuration
public class RestTemplateConfig {
    @Bean
    //使用@LoadBalanced注解赋予RestTemplate负载均衡的能力,他是后面的Ribbon组件的注解，提前用一下后面详解
    @LoadBalanced
    public RestTemplate restTemplate(){
        RestTemplate restTemplate = new RestTemplate();
        HttpComponentsClientHttpRequestFactory httpRequestFactory = new HttpComponentsClientHttpRequestFactory();
        httpRequestFactory.setConnectionRequestTimeout(30 * 1000);//请求连接超时
        httpRequestFactory.setConnectTimeout(30 * 3000);//连接超时时间
        httpRequestFactory.setReadTimeout(30 * 3000);//读取超时
        restTemplate.setRequestFactory(httpRequestFactory);
        return restTemplate;
    }
}
```



### 再次测试-成功



http://127.0.0.1:80/consumer/payment/get/1

<img src="images/image-20220319200554496.png" alt="image-20220319200554496" style="zoom:80%;" />



### 结论(不是重点想看就看不看就不看)



Ribbon和Eureka整合后Consumer可以直接调用服务而不用再关心地址和端口号，且该服务还有负载功能了。O(∩_∩)O





## IP+端口的形式注册服务



当我们的服务集群多了起来，我们就分不清那个服务了，我们可以使用IP+Port来进行显示

<img src="images/image-20220319202144744.png" alt="image-20220319202144744" style="zoom:80%;" />



+ application.yaml文件



**不需要hostname那个属性**

```java
# eureka 的配置
eureka:
  instance:
    prefer-ip-address: true
    instance-id: ${spring.cloud.client.ip-address}:${server.port}
  #    hostname: 127.0.0.1
```



**可以达到下面的结果**

<img src="images/image-20220319202700886.png" alt="image-20220319202700886" style="zoom:80%;" />





## 安全认证



### 简介





如果谁都可以访问这个这个页面那么就是不安全的，我们需要为他设置一个密码



### 注册中心添加依赖



我们分别向

+ cloud-eureka-cluster-server7001
+ cloud-eureka-cluster-server7002
+ cloud-eureka-cluster-server7003

里面添加以下依赖：

```xml
 <dependency>
     <groupId>org.springframework.boot</groupId>
     <artifactId>spring-boot-starter-security</artifactId>
</dependency>
```



### 注册中心修改application.yml



```yaml
server:
  port: 7003 #按项目需求来写

spring:
  application:
    name: cloud-eureka-cluster-server #按项目需求来写
  security:
    user:
      name: root
      password: root
      
eureka:
  instance:
    hostname: erureka3
  client:
    fetch-registry: true  #false表示自己端就是注册中心，我的职责就是维护服务实例，并不需要去检索服务
    register-with-eureka: false #不需要向eureka注册中心中注册自己
    service-url:
      defaultZone: http://root:root@eureka1:7001/eureka/,http://root:root@eureka2:7002/eureka/ #按项目需求来写格式按我的写

```



### 服务的提供者和发现者配置文件



```java
eureka:
  instance:
    prefer-ip-address: true
    instance-id: ${spring.cloud.client.ip-address}:${server.port}
  client:
    register-with-eureka: true
    fetch-registry: true
    service-url:
       defaultZone: http://root:root@eureka1:7001/eureka,http://root:root@eureka2:7002/eureka,http://root:root@eureka3:7003/eureka
```





### 过滤CSRF



 Eureka会自动配置CSRF防御机制,spring security认为post,put and delete http methods 都是有风险的,如果这些method发送过程中没有带上CSRF token,会被直接拦截并返回403 forbidden

 首先注册中心配置一个@EnableWebSecurity配置类,继承WebSecurityConfigurerAdapter,然后重写configure方法



### 过滤CSRF方案一



使CSRF忽略/eureka/**的所有请求

```java
package com.it.config;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * 描述 ：
 *
 * @author : 小糊涂
 * @version : 1.0
 **/
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    /**
     * 方案一 过滤指定路径
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //这句为了访问eureka控制台和/actuator时能做安全控制
        super.configure(http);
        //忽略指定路径的所有请求
        http.csrf().ignoringAntMatchers("/eureka/**");
    }
}
```



<img src="images/image-20220319214510644.png" alt="image-20220319214510644" style="zoom:80%;" />





### 过滤CSRF方案二



 保持密码验证的同时禁用CSRF的防御机制



```java
package com.it.config;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
/**
 * 描述 ：
 *
 * @author : 小糊涂
 * @version : 1.0
 **/
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    /**
     * 方案二 保持密码验证的同时禁用CSRF防御机制
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //如果直接用disable()会把安全验证也警用掉
        http.csrf().disable().authorizeRequests()
                .anyRequest()
                .authenticated()
                .and()
                .httpBasic();
    }
}
```



<img src="images/image-20220319214333609.png" alt="image-20220319214333609" style="zoom:80%;" />



### 过滤CSRF登录成功



<img src="images/image-20220319214550266.png" alt="image-20220319214550266" style="zoom:80%;" />





























