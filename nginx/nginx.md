<h1>Nginx</h1>



--------

# 目录

[TOC]



# 一、简介



## 1.1：背景简介



Nginx`（engine x）`一个具有高性能的**【HTTP】**和**【反向代理】**的**【WEB服务器】**，同时也是一个**【POP3/SMTP/IMAP代理服务器】**，是由伊戈尔·赛索耶夫(俄罗斯人)使用C语言编写的，Nginx的第一个版本是2004年10月4号发布的0.1.0版本。另外值得一提的是伊戈尔·赛索耶夫将Nginx的源码进行了开源，这也为Nginx的发展提供了良好的保障。





+ WEB服务器
	+ WEB服务器也叫网页服务器，英文名叫Web Server，主要功能是为用户提供网上信息浏览服务。

+ HTTP
	+ HTTP是超文本传输协议的缩写，是用于从WEB服务器传输超文本到本地浏览器的传输协议，也是互联网上应用最为广泛的一种网络协议。HTTP是一个客户端和服务器端请求和应答的标准，客户端是终端用户，服务端是网站，通过使用Web浏览器、网络爬虫或者其他工具，客户端发起一个到服务器上指定端口的HTTP请求。

+ POP3/SMTP/IMAP：
	+ POP3(Post Offic Protocol 3)邮局协议的第三个版本，
	+ SMTP(Simple Mail Transfer Protocol)简单邮件传输协议，
	+ IMAP(Internet Mail Access Protocol)交互式邮件存取协议，

**通过上述名词的解释，我们可以了解到Nginx也可以作为电子邮件代理服务器。**

+ 反向代理



![1573489359728](images/1573489359728.png)

+ 反向代理

![1573489653799](images/1573489653799.png)



## 1.2：常见服务器对比



| 名称         | 简介                                                         |
| ------------ | ------------------------------------------------------------ |
| IIS          | 全称(Internet Information Services)即互联网信息服务，是由微软公司提供的基于windows系统的互联网基本服务。windows作为服务器在稳定性与其他一些性能上都不如类UNIX操作系统，因此在需要高性能Web服务器的场合下，IIS可能就会被"冷落". |
| Tomcat       | Tomcat是一个运行Servlet和JSP的Web应用软件，Tomcat技术先进、性能稳定而且开放源代码，因此深受Java爱好者的喜爱并得到了部分软件开发商的认可，成为目前比较流行的Web应用服务器。但是Tomcat天生是一个重量级的Web服务器，对静态文件和高并发的处理比较弱。 |
| Apache       | Apache的发展时期很长，同时也有过一段辉煌的业绩。从上图可以看出大概在2014年以前都是市场份额第一的服务器。Apache有很多优点，如稳定、开源、跨平台等。但是它出现的时间太久了，在它兴起的年代，互联网的产业规模远远不如今天，所以它被设计成一个重量级的、不支持高并发的Web服务器。在Apache服务器上，如果有数以万计的并发HTTP请求同时访问，就会导致服务器上消耗大量能存，操作系统内核对成百上千的Apache进程做进程间切换也会消耗大量的CUP资源，并导致HTTP请求的平均响应速度降低，这些都决定了Apache不可能成为高性能的Web服务器。这也促使了Lighttpd和Nginx的出现。 |
| Lighttpd     | Lighttpd是德国的一个开源的Web服务器软件，它和Nginx一样，都是轻量级、高性能的Web服务器，欧美的业界开发者比较钟爱Lighttpd,而国内的公司更多的青睐Nginx，同时网上Nginx的资源要更丰富些。 |
| 其他的服务器 | Google Servers，Weblogic, Webshpere(IBM)...经过各个服务器的对比，种种迹象都表明，Nginx将以性能为王。这也是我们为什么选择Nginx的理由。 |



## 1.3：Nginx优点



### 1.速度更快、并发更高



单次请求或者高并发请求的环境下，Nginx都会比其他Web服务器响应的速度更快。一方面在正常情况下，单次请求会得到更快的响应，另一方面，在高峰期(如有数以万计的并发请求)，Nginx比其他Web服务器更快的响应请求。Nginx之所以有这么高的并发处理能力和这么好的性能原因在于Nginx**采用了多进程和I/O多路复用(epoll)的底层实现**。



### 2.配置简单，扩展性强



Nginx的设计极具扩展性，它本身就是由**很多模块组成**，这些模块的使用可以通过配置文件的配置来添加。这些模块有官方提供的也有第三方提供的模块，如果需要完全可以开发服务自己业务特性的定制模块。



### 3.高可靠性



**Nginx采用的是多进程模式运行，其中有一个master主进程和N多个worker进程**，worker进程的数量我们可以手动设置，每个worker进程之间都是相互独立提供服务，并且master主进程可以在某一个worker进程出错时，快速去"拉起"新的worker进程提供服务。



### 4.热部署



现在互联网项目都要求以7*24小时进行服务的提供，针对于这一要求，Nginx也提供了热部署功能，即可以在Nginx不停止的情况下，对Nginx进行文件升级、更新配置和更换日志文件等功能。



### 5.成本低、BSD许可证



BSD是一个开源的许可证，世界上的开源许可证有很多，现在比较流行的有六种分别是GPL、BSD、MIT、Mozilla、Apache、LGPL。这六种的区别是什么，我们可以通过下面一张图来解释下：

![1585139995444](images/1585139995444.png)





> **<font color='black'>Nginx本身是开源的，我们不仅可以免费的将Nginx应用在商业领域，而且还可以在项目中直接修改Nginx的源码来定制自己的特殊要求。这些点也都是Nginx为什么能吸引无数开发者继续为Nginx来贡献自己的智慧和青春。OpenRestry [Nginx+Lua]   Tengine[淘宝]</font>**



## 1.4：Nginx的功能特性及常用功能



### 1.简介



Nginx提供的基本功能服务从大体上归纳为"基本HTTP服务"、“高级HTTP服务”和"邮件服务"等三大类。



### 2.基本HTTP服务



Nginx可以提供基本HTTP服务，可以作为HTTP代理服务器和反向代理服务器，支持通过缓存加速访问，可以完成简单的负载均衡和容错，支持包过滤功能，支持SSL等。

- 处理静态文件、处理索引文件以及支持自动索引；
- 提供反向代理服务器，并可以使用缓存加上反向代理，同时完成负载均衡和容错；
- 提供对FastCGI、memcached等服务的缓存机制，，同时完成负载均衡和容错；
- 使用Nginx的模块化特性提供过滤器功能。Nginx基本过滤器包括gzip压缩、ranges支持、chunked响应、XSLT、SSI以及图像缩放等。其中针对包含多个SSI的页面，经由FastCGI或反向代理，SSI过滤器可以并行处理。
- 支持HTTP下的安全套接层安全协议SSL.
- 支持基于加权和依赖的优先权的HTTP/2



### 3.高级HTTP服务



- 支持基于名字和IP的虚拟主机设置
- 支持HTTP/1.0中的KEEP-Alive模式和管线(PipeLined)模型连接
- 自定义访问日志格式、带缓存的日志写操作以及快速日志轮转。
- 提供3xx~5xx错误代码重定向功能
- 支持重写（Rewrite)模块扩展
- 支持重新加载配置以及在线升级时无需中断正在处理的请求
- 支持网络监控
- 支持FLV和MP4流媒体传输



### 4.邮件服务



Nginx提供邮件代理服务也是其基本开发需求之一，主要包含以下特性：

- 支持IMPA/POP3代理服务功能
- 支持内部SMTP代理服务功能



### 5.常用功能模块



```java
静态资源部署
Rewrite地址重写
	正则表达式
反向代理
负载均衡
	轮询、加权轮询、ip_hash、url_hash、fair
Web缓存
环境部署
	高可用的环境
用户认证模块...
```



### 6.核心组成



+ nginx二进制可执行文件
+ nginx.conf配置文件
+ error.log错误的日志记录
+ access.log访问日志记录



## 1.5：官网



### 1.官网首页



+ Nginx的官方网站为: http://nginx.org

<img src="images/image-20210622170134624.png" alt="image-20210622170134624" style="zoom:80%;" />



### 2.下载地址



Nginx的官方下载网站为<http://nginx.org/en/download.html>，当然你也可以之间在首页选中右边的download进入版本下载网页。在下载页面我们会看到如下内容：



<img src="images/image-20210622170246197.png" alt="image-20210622170246197" style="zoom:80%;" />



### 获取Nginx源码

<http://nginx.org/download/>

打开上述网站，就可以查看到Nginx的所有版本，选中自己需要的版本进行下载。下载我们可以直接在windows上下载然后上传到服务器，也可以直接从服务器上下载，这个时候就需要准备一台服务器。

<img src="images/image-20210622170345846.png" alt="image-20210622170345846" style="zoom:80%;" />





## 1.6：安装



### 1.安装依赖



+ GCC

```java
yum install -y gcc
```

安装成功后，可以通过`gcc --version`来查看gcc是否安装成功



+ PCRE



Nginx在编译过程中需要使用到PCRE库（perl Compatible Regular Expressoin 兼容正则表达式库)，因为在Nginx的Rewrite模块和http核心模块都会使用到PCRE正则表达式语法。

```java
yum install -y pcre pcre-devel
```

安装成功后，可以通过`rpm -qa pcre pcre-devel`来查看是否安装成功



+ zlib



zlib库提供了开发人员的压缩算法，在Nginx的各个模块中需要使用gzip压缩，所以我们也需要提前安装其库及源代码zlib和zlib-devel

```java
yum install -y zlib zlib-devel
```

安装成功后，可以通过`rpm -qa zlib zlib-devel`来查看是否安装成功



+ OpenSSL



OpenSSL是一个开放源代码的软件库包，应用程序可以使用这个包进行安全通信，并且避免被窃听。

SSL:Secure Sockets Layer安全套接协议的缩写，可以在Internet上提供秘密性传输，其目标是保证两个应用间通信的保密性和可靠性。在Nginx中，如果服务器需要提供安全网页时就需要用到OpenSSL库，所以我们需要对OpenSSL的库文件及它的开发安装包进行一个安装。

```java
yum install -y openssl openssl-devel
```

安装成功后，可以通过`rpm -qa openssl openssl-devel`来查看是否安装成功



### 2.一键安装依赖



上述命令，一个个来的话比较麻烦，我们也可以通过一条命令来进行安装



```java
yum install -y gcc pcre pcre-devel zlib zlib-devel openssl openssl-devel
```



### 3.源码安装



+ 地址

```java
wget  http://nginx.org/download/nginx-1.18.0.tar.gz
```

+ 复制到我们安装位置

```java
cp /app/package/nginx-1.18.0.tar.gz /app/nginx/core/
```

+ 解压

```java
tar -zxvf nginx-1.18.0.tar.gz
```

+ 进入目录配置

```java
./configure 
```

+ 编译

```java
make
```

+ 安装

```java
make install
```

+ 安装路径

```java
默认安装在：/usr/local/nginx
```



### 4.yum安装



使用源码进行简单安装，我们会发现安装的过程比较繁琐，需要提前准备GCC编译器、PCRE兼容正则表达式库、zlib压缩库、OpenSSL安全通信的软件库包，然后才能进行Nginx的安装。

+ 安装yum-utils

```java
sudo yum  install -y yum-utils
```

+ 安装yum源文件

```java
vim /etc/yum.repos.d/nginx.repo
```

```shell
[nginx-stable]
name=nginx stable repo
baseurl=http://nginx.org/packages/centos/$releasever/$basearch/
gpgcheck=1
enabled=1
gpgkey=https://nginx.org/keys/nginx_signing.key
module_hotfixes=true

[nginx-mainline]
name=nginx mainline repo
baseurl=http://nginx.org/packages/mainline/centos/$releasever/$basearch/
gpgcheck=1
enabled=0
gpgkey=https://nginx.org/keys/nginx_signing.key
module_hotfixes=true
```

+ 查看是否安装成功

```java
yum list | grep nginx
```

+ 安装

```java
yun install -y nginx
```

+ 查看安装位置

```java
whereis nginx
```



# 二、基础内容



## 2.1：目录结构分析



### 1.图



> 在使用Nginx之前，我们先对安装好的Nginx目录文件进行一个分析，在这块给大家介绍一个工具tree，通过tree我们可以很方面的去查看centos系统上的文件目录结构，当然，如果想使用tree工具，就得先通过`yum install -y tree`来进行安装，安装成功后，可以通过执行`tree /usr/local/nginx`(tree后面跟的是Nginx的安装目录)，获取的结果如下：

<img src="images/image-20210622174258366.png" alt="image-20210622174258366" style="zoom:80%;" />



### 2.主要目录



| 名称 | 简介                                                         |
| ---- | ------------------------------------------------------------ |
| conf | nginx所有配置文件目录                                        |
| html | 存放nginx自带的两个静态的html页面                            |
| logs | 记录日志的文件，当nginx服务器启动后，这里面会有 access.log error.log 和nginx.pid三个文件出现。 |
| sbin | 是存放执行程序文件nginx                                      |



### 3.CGI



公共网关接口（Common Gateway Interface，CGI）是Web 服务器运行时外部程序的规范，按CGI 编写的程序可以扩展服务器功能。CGI 应用程序能与浏览器进行交互，还可通过数据API与数据库服务器等外部数据源进行通信，从数据库服务器中获取数据。格式化为HTML文档后，发送给浏览器，也可以将从浏览器获得的数据放到数据库中。几乎所有服务器都支持CGI，可用任何语言编写CGI，包括流行的C、C ++、Java、VB 和Delphi 等。CGI分为标准CGI和间接CGI两种。标准CGI使用命令行参数或环境变量表示服务器的详细请求，服务器与浏览器通信采用标准输入输出方式。间接CGI又称缓冲CGI，在CGI程序和CGI接口之间插入一个缓冲程序，缓冲程序与CGI接口间用标准输入输出进行通信 。



### 4.conf目录文件



| 文件名                    | 简称                                                         |
| ------------------------- | ------------------------------------------------------------ |
| fastcgi.conf              | fastcgi相关配置文件                                          |
| fastcgi.conf.default      | fastcgi.conf的备份文件                                       |
| fastcgi_params            | fastcgi的参数文件                                            |
| fastcgi_params.default    | fastcgi的参数备份文件                                        |
| scgi_params               | scgi的参数文件                                               |
| scgi_params.default       | scgi的参数备份文件                                           |
| uwsgi_params              | uwsgi的参数文件                                              |
| uwsgi_params.default      | uwsgi的参数备份文件                                          |
| mime.types                | 记录的是HTTP协议中的Content-Type的值和文件后缀名的对应关系   |
| mime.types.default        | mime.types的备份文件                                         |
| nginx.conf                | 这个是Nginx的核心配置文件，这个文件非常重要，也是我们即将要学习的重点 |
| nginx.conf.default        | nginx.conf的备份文件                                         |
| oi-utf、koi-win、win-utfk | 这三个文件都是与编码转换映射相关的配置文件，用来将一种编码转换成另一种编码 |



### 5.html目录



| 文件名称   | 描述                 |
| ---------- | -------------------- |
| 50x.html   | 访问失败后的失败页面 |
| index.html | 成功访问的默认首页   |



### 6.sbin目录



| 文件名称 | 描述                                    |
| -------- | --------------------------------------- |
| nginx    | 是用来控制Nginx的启动和停止等相关的命令 |





## 2.2：启动/停止/重启



### 1.简介



对于Nginx的启停在linux系统中也有很多种方式：

1. Nginx服务的信号控制

2. Nginx的命令行控制



### 2.Nginx服务的信号控制



#### 简介

前面在提到Nginx的高性能，其实也和它的架构模式有关。Nginx默认采用的是多进程的方式来工作的，当将Nginx启动后，我们通过`ps -ef | grep nginx`命令可以查看到如下内容：

<img src="images/image-20210624154731947.png" alt="image-20210624154731947" style="zoom:80%;" />

从上图中可以看到：

+ **Nginx后台进程中包含一个master进程和多个worker进程，**
+ **master进程主要用来管理worker进程，包含接收外界的信息，并将接收到的信号发送给各个worker进程，监控worker进程的状态，**
+ **当worker进程出现异常退出后，会自动重新启动新的worker进程。**
+ **而worker进程则是专门用来处理用户请求的，各个worker进程之间是平等的并且相互独立，处理请求的机会也是一样的。**



nginx的进程模型，我们可以通过下图来说明下：

<img src="images/image-20210624155017589.png" alt="image-20210624155017589" style="zoom:80%;" />



我们现在作为管理员，只需要通过给master进程发送信号就可以来控制Nginx,这个时候我们需要有两个前提条件，一个是要操作的master进程，一个是信号。



#### 获取master进程id



+ 方式一：通过`ps -ef | grep nginx`；
+ 方式二：在讲解nginx的`./configure`的配置参数的时候，有一个参数是`--pid-path=PATH`默认是`/usr/local/nginx/logs/nginx.pid`,所以可以通过查看该文件来获取nginx的master进程ID.



#### 信号种类



| 信号     | 作用                                                       |
| -------- | ---------------------------------------------------------- |
| TERM/INT | 立即关闭整个服务                                           |
| QUIT     | "优雅"地关闭整个服务                                       |
| HUP      | 重读配置文件并使用服务对新配置项生效                       |
| USR1     | 重新打开日志文件，可以用来进行日志切割                     |
| USR2     | 平滑升级到最新版的nginx                                    |
| WINCH    | 所有子进程不在接收处理新连接，相当于给work进程发送QUIT指令 |



#### 使用信号语法



```java
kill -signal PID
```



+ signal:即为信号；

+ PID即为获取到的master线程ID



#### 信号具体使用



1. 发送TERM/INT信号给master进程，会将Nginx服务立即关闭。



```shell
kill -TERM PID / kill -TERM `cat /usr/local/nginx/logs/nginx.pid`
kill -INT PID / kill -INT `cat /usr/local/nginx/logs/nginx.pid`
```



2. 发送QUIT信号给master进程，master进程会控制所有的work进程不再接收新的请求，等所有请求处理完后，在把进程都关闭掉。



```shell
kill -QUIT PID / kill -TERM `cat /usr/local/nginx/logs/nginx.pid`
```



3. 发送HUP信号给master进程，master进程会把控制旧的work进程不再接收新的请求，等处理完请求后将旧的work进程关闭掉，然后根据nginx的配置文件重新启动新的work进程



```shell
kill -HUP PID / kill -TERM `cat /usr/local/nginx/logs/nginx.pid`
```



4. 发送USR1信号给master进程，告诉Nginx重新开启日志文件



```shell
kill -USR1 PID / kill -TERM `cat /usr/local/nginx/logs/nginx.pid`
```



5. 发送USR2信号给master进程，告诉master进程要平滑升级，这个时候，会重新开启对应的master进程和work进程，整个系统中将会有两个master进程，并且新的master进程的PID会被记录在`/usr/local/nginx/logs/nginx.pid`而之前的旧的master进程PID会被记录在`/usr/local/nginx/logs/nginx.pid.oldbin`文件中，接着再次发送QUIT信号给旧的master进程，让其处理完请求后再进行关闭



```shell
kill -USR2 PID / kill -USR2 `cat /usr/local/nginx/logs/nginx.pid`
```

```shell
kill -QUIT PID / kill -QUIT `cat /usr/local/nginx/logs/nginx.pid.oldbin`
```

![1586368250085](images/1586368250085.png)



6. 发送WINCH信号给master进程,让master进程控制不让所有的work进程在接收新的请求了，请求处理完后关闭work进程。注意master进程不会被关闭掉



```shell
kill -WINCH PID /kill -WINCH`cat /usr/local/nginx/logs/nginx.pid`
```





### 3.命令行控制



#### 简介

此方式是通过Nginx安装目录下的sbin下的可执行文件nginx来进行Nginx状态的控制，我们可以通过`nginx -h`来查看都有哪些参数可以用：

<img src="images/image-20210624160946806.png" alt="image-20210624160946806" style="zoom:80%;" />



| 参数   | 说明                                                         |
| ------ | ------------------------------------------------------------ |
| -?和-h | 显示帮助信息                                                 |
| -v     | 打印版本号信息并退出                                         |
| -V     | 打印版本号信息和配置信息并退出                               |
| -t     | 测试nginx的配置文件语法是否正确并退出                        |
| -T     | 测试nginx的配置文件语法是否正确并列出用到的配置文件信息然后退出 |
| -q     | 在配置测试期间禁止显示非错误消息                             |
| -s     | signal信号，后面可以跟 ：<br>          stop[快速关闭，类似于TERM/INT信号的作用]<br>          quit[优雅的关闭，类似于QUIT信号的作用] 	<br>          reopen[重新打开日志文件类似于USR1信号的作用] 	<br>          reload[类似于HUP信号的作用] |
| -p     | prefix指定Nginx的prefix路径，(默认为: /usr/local/nginx/)     |
| -c     | filename指定Nginx的配置文件路径,(默认为: conf/nginx.conf)    |
| -g     | directives用来补充Nginx配置文件，向Nginx服务指定启动时应用全局的配置 |



### 4.常用命令



+ 查看 nginx 版本号

```java
./nginx -v
```

+ 启动 nginx

```java
./nginx
```

+  停止 nginx

```java
./nginx -s stop
```

+ 重新加载 nginx

```java
./nginx -s reload
```



## 2.2：nginx.conf





从前面的内容学习中，我们知道Nginx的核心配置文件默认是放在`/usr/local/nginx/conf/nginx.conf`，这一节，我们就来学习下nginx.conf的内容和基本配置方法。

读取Nginx自带的Nginx配置文件，我们将其中的注释部分【学习一个技术点就是在Nginx的配置文件中可以使用`#`来注释】删除掉后，就剩下下面内容:	

> **简单小结下:**
>
> **nginx.conf配置文件中默认有三大块：全局块、events块、http块**
>
> **http块中可以配置多个server块，每个server块又可以配置多个location块。**

```java
worker_processes  1;

events {
    worker_connections  1024;
}

http {
    include       mime.types;
    default_type  application/octet-stream;
    sendfile        on;
    keepalive_timeout  65;

    server {
        listen       80;
        server_name  localhost;
        location / {
            root   html;
            index  index.html index.htm;
        }
        error_page   500 502 503 504  /50x.html;
        location = /50x.html {
            root   html;
        }
    }

}
```



```java
指令名	指令值;  #全局块，主要设置Nginx服务器整体运行的配置指令

 #events块,主要设置,Nginx服务器与用户的网络连接,这一部分对Nginx服务器的性能影响较大
events {	 
    指令名	指令值;
}
#http块，是Nginx服务器配置中的重要部分，代理、缓存、日志记录、第三方模块配置...             
http {		
    指令名	指令值;
    server { #server块，是Nginx配置和虚拟主机相关的内容
        指令名	指令值;
        location / { 
        #location块，基于Nginx服务器接收请求字符串与location后面的值进行匹配，对特定请求进行处理
            指令名	指令值;
        }
    }
	...
}
```



## 2.3：全局块



### 1.简介

主要设置Nginx服务器整体运行的配置指令



<img src="images/image-20210624171534918.png" alt="image-20210624171534918" style="zoom:80%;" />



### 2.user指令简介



nginx 运行后可以指定用户，比如说一个静态网页服务器的文件目录的不同的用户有不同的访问权限，使用 nginx 指定用户就可以有权限对此目录读写。

user指令:用于配置运行Nginx服务器的worker进程的用户和用户组。

| 语法   | user user [group] |
| ------ | ----------------- |
| 默认值 | nobody            |
| 位置   | 全局块            |

该属性也可以在编译的时候指定，语法如下

```java
./configure --user=user --group=group
```

如果两个地方都进行了设置，最终生效的是配置文件中的配置。



### 3.user指令使用



+ centos新建用户admin

```java
useradd admin
```

+ 配置nginx.conf

```java
user admin;
```

<img src="images/image-20210624180745247.png" alt="image-20210624180745247" style="zoom:80%;" />

+ 测试配置文件语法格式

```java
cd /usr/local/nginx/sbin
./nginx -t    
```

<img src="images/image-20210624175902494.png" alt="image-20210624175902494" style="zoom:80%;" />



+ 创建/root/html/index.html文件

```java
<h1>MY name is admin`</h1>
```

+ 再次修改nginx.conf文件

```java
location / {
	root   /root/html;
	index  index.html index.htm;
}
```



<img src="images/image-20210624180429507.png" alt="image-20210624180429507" style="zoom:67%;" />

+ 重启nginx

```java
./nginx -s reload
```



+ 403解决

页面会报403拒绝访问的错误

因为当前用户没有访问/root/html目录的权限

将文件创建到 `/home/admin/html/index.html`,修改配置

```java
location / {
	root   /home/admin/html;
	index  index.html index.htm;
}
```

<img src="images/image-20210624181146794.png" alt="image-20210624181146794" style="zoom:80%;" />

+ 重启测试

	

<img src="images/image-20210624181545155.png" alt="image-20210624181545155" style="zoom:80%;" />

### 4.user指令总结



综上所述，使用user指令可以指定**启动运行工作进程的用户及用户组**，这样对于系统的权限访问控制的更加精细，也更加安全。

这里的用户和用户组指的是：`liunx的用户`



### 5.master_process指令



master_process:用来指定**是否开启工作进程**，如果开启只会主进程



| 语法   | master_process on\|off; |
| ------ | ----------------------- |
| 默认值 | master_process on;      |
| 位置   | 全局块                  |

**注意：如果修改了mater_process的值我们是需要来重启nginx的**



### 6.worker_processes指令



worker_processes:用于配置Nginx生成工作进程的数量，这个是Nginx服务器实现并发处理服务的关键所在。理论上来说workder process的值越大，可以支持的并发处理量也越多，但事实上这个值的设定是需要受到来自服务器自身的限制，建议将该值和服务器CPU的内核数保存一致。



| 语法   | worker_processes     num/auto; |
| ------ | ------------------------------ |
| 默认值 | 1                              |
| 位置   | 全局块                         |



+ 如果将worker_processes设置成2，则会看到如下内容:



<img src="images/image-20210629172117518.png" alt="image-20210629172117518" style="zoom:80%;" />



+ 注意：**开启worker的时候，不能设置master_pocesses进程**



### 7.daemon指令



设定Nginx是否以守护进程的方式启动。

守护式进程是linux后台执行的一种服务进程，特点是独立于控制终端，不会随着终端关闭而停止。

简单的说是否以后台方式启动，**默认on打开**

| 简介   | 描述            |
| ------ | --------------- |
| 语法   | daemon on\|off; |
| 默认值 | daemon on;      |
| 位置   | 全局块          |



**<font color='red'>重启生效</font>**



### 8.pid指令



pid:用来配置Nginx当前master进程的进程号ID存储的文件路径。



|        | 描述                                     |
| ------ | ---------------------------------------- |
| 语法   | pid file;                                |
| 默认值 | 默认为:`/usr/local/nginx/logs/nginx.pid` |
| 位置   | 全局块                                   |

该属性可以通过`./configure --pid-path=PATH`来指定



### 9.error_log指令



error_log:用来配置Nginx的错误日志存放路径

|        | 描述                            |
| ------ | ------------------------------- |
| 语法   | error_log  file [日志级别];     |
| 默认值 | error_log logs/error.log error; |
| 位置   | 全局块、http、server、location  |

该属性可以通过`./configure --error-log-path=PATH`来指定

其中日志级别的值有：`debug|info|notice|warn|error|crit|alert|emerg`，翻译过来为试`信息|通知|警告|错误|临界|警报|紧急`，这块建议大家设置的时候不要设置成info以下的等级，因为会带来大量的磁盘I/O消耗，影响Nginx的性能。



### 10.include



include:用来引入其他配置文件，使Nginx的配置更加灵活

|        | 描述          |
| ------ | ------------- |
| 语法   | include file; |
| 默认值 | 无            |
| 位置   | any           |



## 2.4：events块



### 1.简介

主要设置,Nginx服务器与用户的网络连接,这一部分对Nginx服务器的性能影响较大

所在位置

<img src="images/image-20210630151640156.png" alt="image-20210630151640156" style="zoom:80%;" />



### 2.accept_mutex-设置Nginx网络连接序列化



用来设置Nginx网络连接序列化

|        | 描述                  |
| ------ | --------------------- |
| 语法   | accept_mutex on\|off; |
| 默认值 | accept_mutex on;      |
| 位置   | events                |

这个配置主要可以用来解决常说的"惊群"问题。大致意思是在某一个时刻，客户端发来一个请求连接，Nginx后台是以多进程的工作模式，也就是说有多个worker进程会被同时唤醒，但是最终只会有一个进程可以获取到连接，如果每次唤醒的进程数目太多，就会影响Nginx的整体性能。如果将上述值设置为on(开启状态)，将会对多个Nginx进程接收连接进行序列号，一个个来唤醒接收，就防止了多个进程对连接的争抢。

![1581566971955](images/1581566971955.png)

> 简单的就说如果不设置的话，那么我们进来的请求，nginx不知道安排那个work proeccs来处理



### 3.multi_accept-接收多个网络连接



用来设置是否允许同时接收多个网络连接

如果multi_accept被禁止了，nginx一个工作进程只能同时接受一个新的连接。否则，一个工作进程可以同时接受所有的新连接

|        | 描述                  |
| ------ | --------------------- |
| 语法   | multi_accept on\|off; |
| 默认值 | multi_accept off;     |
| 位置   | events                |



### 4.worker_connections-单个worker进程最大的连接数



用来配置单个worker进程最大的连接数

这里的连接数不仅仅包括和前端用户建立的连接数，而是包括所有可能的连接数。另外，number值不能大于操作系统支持打开的最大文件句柄数量。

|        | 描述                       |
| ------ | -------------------------- |
| 语法   | worker_connections number; |
| 默认值 | worker_commections 512;    |
| 位置   | events                     |



### 5.use-事件驱动来处理网络消息



用来设置Nginx服务器选择哪种事件驱动来处理网络消息。

注意：此处所选择事件处理模型是Nginx优化部分的一个重要内容，**method的可选值有`select/poll/epoll/kqueue`等**，之前在准备centos环境的时候，我们强调过要使用linux内核在2.6以上，就是为了能使用epoll函数来优化Nginx。



|        | 描述           |
| ------ | -------------- |
| 语法   | use  method;   |
| 默认值 | 根据操作系统定 |
| 位置   | events         |



另外这些值的选择，我们也可以在编译的时候使用

`--with-select_module`、`--without-select_module`、

` --with-poll_module`、` --without-poll_module`来设置是否需要将对应的事件驱动模块编译到Nginx的内核。

### 6.events指令配置实例



+ 打开Nginx的配置文件 nginx.conf,添加如下配置



```java
events{
	accept_mutex on;
	multi_accept on;
	worker_connections 1024;
	use epoll;
}
```



+ 启动测试



```java
./nginx -t
./nginx -s reload
```



## 2.5：http块



### 1.简介



是Nginx服务器配置中的重要部分，代理、缓存、日志记录、第三方模块配置...   



### 2.定义MIME-Type



我们都知道浏览器中可以显示的内容有HTML、XML、GIF等种类繁多的文件、媒体等资源，浏览器为了区分这些资源，就需要使用MIME Type。所以说MIME Type是网络资源的媒体类型。Nginx作为web服务器，也需要能够识别前端请求的资源类型。

在Nginx的配置文件中，默认有两行配置

```java
include mime.types;
default_type application/octet-stream;
```



> default_type:用来配置Nginx响应前端请求默认的MIME类型。
>
> |        | 描述                      |
> | ------ | ------------------------- |
> | 语法   | default_type mime-type;   |
> | 默认值 | default_type text/plain； |
> | 位置   | http、server、location    |
>
> 在default_type之前还有一句`include mime.types`,include之前我们已经介绍过，相当于把mime.types文件中MIMT类型与相关类型文件的文件后缀名的对应关系加入到当前的配置文件中。
>
> 举例来说明：
>
> 有些时候请求某些接口的时候需要返回指定的文本字符串或者json字符串，如果逻辑非常简单或者干脆是固定的字符串，那么可以使用nginx快速实现，这样就不用编写程序响应请求了，可以减少服务器资源占用并且响应性能非常快。

如何实现:

```java
location /get_text {
	#这里也可以设置成text/plain
    default_type text/html;
    return 200 "This is nginx's text";
}
location /get_json{
    default_type application/json;
    return 200 '{"name":"TOM","age":18}';
}
```



### 3.自定义服务日志



Nginx中日志的类型分access.log、error.log。

| 分类       | 描述                                                    |
| ---------- | ------------------------------------------------------- |
| access.log | 用来记录用户所有的访问请求。                            |
| error.log  | 记录nginx本身运行时的错误信息，不会记录用户的访问请求。 |

Nginx服务器支持对服务日志的格式、大小、输出等进行设置，需要使用到两个指令，分别是access_log和log_format指令。



+ access_log:用来设置用户访问日志的相关属性。



|        | 描述                                 |
| ------ | ------------------------------------ |
| 语法   | access_log path[format[buffer=size]] |
| 默认值 | access_log logs/access.log combined; |
| 位置   | `http`, `server`, `location`         |



+ log_format:用来指定日志的输出格式。



|        | 描述                                                     |
| ------ | -------------------------------------------------------- |
| 语法   | log_format name [escape=default\|json\|none] string....; |
| 默认值 | log_format combined "...";                               |
| 位置   | http                                                     |



### 4.sendfile-处理静态资源的性能



用来设置Nginx服务器是否使用sendfile()传输文件，该属性可以大大提高Nginx处理静态资源的性能

|        | 描述                   |
| ------ | ---------------------- |
| 语法   | sendfile on\|off；     |
| 默认值 | sendfile off;          |
| 位置   | http、server、location |



### 5.keepalive_timeout-长连接的超时时间。



用来设置长连接的超时时间。

> 为什么要使用keepalive？
>
> 我们都知道HTTP是一种无状态协议，客户端向服务端发送一个TCP请求，服务端响应完毕后断开连接。
> 如何客户端向服务端发送多个请求，每个请求都需要重新创建一次连接，效率相对来说比较多，使用keepalive模式，可以告诉服务器端在处理完一个请求后保持这个TCP连接的打开状态，若接收到来自这个客户端的其他请求，服务端就会利用这个未被关闭的连接，而不需要重新创建一个新连接，提升效率，但是这个连接也不能一直保持，这样的话，连接如果过多，也会是服务端的性能下降，这个时候就需要我们进行设置其的超时时间。



|        | 描述                    |
| ------ | ----------------------- |
| 语法   | keepalive_timeout time; |
| 默认值 | keepalive_timeout 75s;  |
| 位置   | http、server、location  |



### 6.keepalive_requests



keepalive_requests:用来设置一个keep-alive连接使用的次数。

|        | 描述                       |
| ------ | -------------------------- |
| 语法   | keepalive_requests number; |
| 默认值 | keepalive_requests 100;    |
| 位置   | http、server、location     |





### 7.server块和location块



server块和location块都是我们要重点讲解和学习的内容，因为我们后面会对Nginx的功能进行详细讲解，所以这块内容就放到静态资源部署的地方给大家详细说明。

本节我们主要来认识下Nginx默认给的nginx.conf中的相关内容，以及server块与location块在使用的时候需要注意的一些内容。



```java
	server {
        listen       80;
        server_name  localhost;
        location / {
            root   html;
            index  index.html index.htm;
        }
       
        error_page   500 502 503 504 404  /50x.html;
        location = /50x.html {
            root   html;
        }
    }
```



## 2.6：nginx配置成系统服务



### 1.前言



经过前面的操作，我们会发现，如果想要启动、关闭或重新加载nginx配置文件，都需要先进入到nginx的安装目录的sbin目录，然后使用nginx的二级制可执行文件来操作，相对来说操作比较繁琐，这块该如何优化？另外如果我们想把Nginx设置成随着服务器启动就自动完成启动操作，又该如何来实现?



### 2.配置

把Nginx应用服务设置成为系统服务，方便对Nginx服务的启动和停止等相关操作，具体实现步骤:

```shell
vim /usr/lib/systemd/system/nginx.service
```

```c#
[Unit]
Description=nginx web service
Documentation=http://nginx.org/en/docs/
After=network.target

[Service]
Type=forking
PIDFile=/usr/local/nginx/logs/nginx.pid
ExecStartPre=/usr/local/nginx/sbin/nginx -t -c /usr/local/nginx/conf/nginx.conf
ExecStart=/usr/local/nginx/sbin/nginx
ExecReload=/usr/local/nginx/sbin/nginx -s reload
ExecStop=/usr/local/nginx/sbin/nginx -s stop
PrivateTmp=true

[Install]
WantedBy=default.target
```



### 3.修改权限



```shell
chmod 755 /usr/lib/systemd/system/nginx.service
```



### 4.使用命令来操作nginx



| 描述             | 命令                    |
| ---------------- | ----------------------- |
| 启动             | systemctl start nginx   |
| 停止             | systemctl stop nginx    |
| 重启             | systemctl restart nginx |
| 重新加载配置文件 | systemctl reload nginx  |
| 查看nginx状态    | systemctl status nginx  |
| 开机启动         | systemctl enable nginx  |



## 2.7：Nginx命令配置到系统环境



### 1.简介



前面我们介绍过Nginx安装目录下的二级制可执行文件`nginx`的很多命令，要想使用这些命令前提是需要进入sbin目录下才能使用，很不方便，如何去优化，我们可以将该二进制可执行文件加入到系统的环境变量，这样的话在任何目录都可以使用nginx对应的相关命令。具体实现步骤如下:



### 2.配置



(1)修改`/etc/profile`文件

```java
vim /etc/profile
在最后一行添加
export PATH=$PATH:/usr/local/nginx/sbin
```

<img src="images/image-20210630170944986.png" alt="image-20210630170944986" style="zoom:80%;" />

(2)使之立即生效

```shell
source /etc/profile
```

(3)执行nginx命令

```shell
nginx -V
```

<img src="images/image-20210630171011141.png" alt="image-20210630171011141" style="zoom:80%;" />









# 三、基础配置实战



## 3.1：需求分析



前面我们已经对Nginx服务器默认配置文件的结构和涉及的基本指令做了详细的阐述。通过这些指令的合理配置，我们就可以让一台Nginx服务器正常工作，并且提供基本的web服务器功能。

接下来我们将通过一个比较完整和最简单的基础配置实例，来巩固下前面所学习的指令及其配置。

+ 需求如下

```c
（1）有如下访问：
	http://192.168.244.100:8081/server1/location1
		访问的是：index_sr1_location1.html
	http://192.168.244.100:8081/server1/location2
		访问的是：index_sr1_location2.html
	http://192.168.244.100:8082/server2/location1
		访问的是：index_sr2_location1.html
	http://192.168.244.100:8082/server2/location2
		访问的是：index_sr2_location2.html
（2）如果访问的资源不存在，
	返回自定义的404页面
（3）将/server1和/server2的配置使用不同的配置文件分割
	将文件放到/home/www/conf.d目录下，然后使用include进行合并
（4）为/server1和/server2各自创建一个访问日志文件
```



## 3.2：静态资源准备



<img src="images/image-20210630162336798.png" alt="image-20210630162336798" style="zoom:67%;" />



## 3.3：配置文件



### 1.操作1

```shell
cd /usr/local/nginx/conf/
cp nginx.conf nginx-1.conf
vim nginx.conf
```

### 2.写入以下内容

```java
##全局块 begin##
#配置允许运行Nginx工作进程的用户和用户组
user admin;
#配置运行Nginx进程生成的worker进程数
worker_processes 2;
#配置Nginx服务器运行对错误日志存放的路径
error_log logs/error.log;
#配置Nginx服务器允许时记录Nginx的master进程的PID文件路径和名称
pid logs/nginx.pid;
#配置Nginx服务是否以守护进程方法启动
#daemon on;
##全局块 end##

##events块 begin##
events{
	#设置Nginx网络连接序列化
	accept_mutex on;
	#设置Nginx的worker进程是否可以同时接收多个请求
	multi_accept on;
	#设置Nginx的worker进程最大的连接数
	worker_connections 1024;
	#设置Nginx使用的事件驱动模型
	use epoll;
}
##events块 end##
##http块 start##
http{
	#定义MIME-Type
	include mime.types;
	default_type application/octet-stream;
	#配置允许使用sendfile方式运输
	sendfile on;
	#配置连接超时时间
	keepalive_timeout 65;
	#配置请求处理日志格式
	log_format server1 '===>server1 access log';
	log_format server2 '===>server2 access log';
	##server块 开始##
	include /home/admin/html/conf.d/*.conf;
	##server块 结束##
}
##http块 end##
```



### 3.操作2



```shell
vim /home/admin/html/conf.d/server1.conf
```



### 4.写入以下内容



```java
server{
		#配置监听端口和主机名称
		listen 8081;
		server_name localhost;
		#配置请求处理日志存放路径
		access_log /home/admin/html/server1/log/access.log server1;
		#配置错误页面
		error_page 404 /404.html;
		#配置处理/server1/location1请求的location
		location /server1/location1{
			root /home/admin/html;
			index index_sr1_location1.html;
		}
		#配置处理/server1/location2请求的location
		location /server1/location2{
			root /home/admin/html;
			index index_sr2_location2.html;
		}
		#配置错误页面转向
		location = /404.html {
			root /home/admin/html;
			index 404.html;
		}
}
```



### 5.操作2



```shell
vim /home/admin/html/conf.d/server2.conf
```



### 6.写入以下内容



```java
server{
		#配置监听端口和主机名称
		listen 8082;
		server_name localhost;
		#配置请求处理日志存放路径
		access_log /home/admin/html/server2/log/access.log server1;
		#配置错误页面
		error_page 404 /404.html;
		#配置处理/server2/location1请求的location
		location /server2/location1{
			root /home/admin/html;
			index index_sr1_location1.html;
		}
		#配置处理/server2/location2请求的location
		location /server2/location2{
			root /home/admin/html;
			index index_sr2_location2.html;
		}
		#配置错误页面转向
		location = /404.html {
			root /home/admin/html;
			index 404.html;
		}
}
```



### 7.操作



```java
vim /home/admin/html/404.html
```



```html
<h1>404</h1>
```



## 3.4：测试



```shell
#运行
./nginx -t
#重启
./nginx -s reload
```



游览器访问：



http://192.168.244.100:8081/server1/location1/

http://192.168.244.100:8081/server1/location2/

http://192.168.244.100:8082/server2/location1/

http://192.168.244.100:8082/server2/location2/



# 四、静态资源配置

## 4.1：静态资源概述



上网去搜索访问资源对于我们来说并不陌生，通过浏览器发送一个HTTP请求实现从客户端发送请求到服务器端获取所需要内容后并把内容回显展示在页面的一个过程。这个时候，我们所请 求的内容就分为两种类型，一类是静态资源、一类是动态资源。静态资源即指在服务器端真实存在并且能直接拿来展示的一些文件，比如常见的html页面、css文件、js文件、图 片、视频等资源；动态资源即指在服务器端真实存在但是要想获取需要经过一定的业务逻辑处理，根据不同的条件展示在页面不同这 一部分内容，比如说报表数据展示、根据当前登录用户展示相关具体数据等资源；

Nginx处理静态资源的内容，我们需要考虑下面这几个问题：

```java
（1）静态资源的配置指令
（2）静态资源的配置优化
（3）静态资源的压缩配置指令
（4）静态资源的缓存处理
（5）静态资源的访问控制，包括跨域问题和防盗链问题
```



## 4.2：listen监听端口



### 1.简介



listen:用来配置监听端口。

|        | 描述                                                         |
| ------ | ------------------------------------------------------------ |
| 语法   | listen address[:port] [default_server]...;<br/>listen port [default_server]...; |
| 默认值 | listen *:80 \| *:8000                                        |
| 位置   | server                                                       |

listen的设置比较灵活，我们通过几个例子来把常用的设置方式熟悉下：

```java
listen 127.0.0.1:8000; 监听指定的IP和端口
listen localhost:8000; 监听指定的IP和端口 
listen 127.0.0.1;	监听指定IP的所有端口
listen 8000;	监听指定端口上的连接
listen *:8000;	监听指定端口上的连接
```



### 2.default_server



default_server属性是标识符，用来将此虚拟主机设置成默认主机。所谓的默认主机指的是如果没有匹配到对应的address:port，则会默认执行的。如果不指定默认使用的是第一个server。

```java
server{
	listen 8080;
	server_name 127.0.0.1;
	location /{
		root html;
		index index.html;
	}
}
server{
	listen 8080 default_server;
	server_name localhost;
	default_type text/plain;
	return 444 'This is a error request';
}
```



<img src="images/image-20210630171918537.png" alt="image-20210630171918537" style="zoom:80%;" />



## 4.3：host文件配置



为了方便学习配置一下host

hosts是一个没有扩展名的系统文件，可以用记事本等工具打开，其作用就是将一些常用的网址域名与其对应的IP地址建立一个关联“数据库”，当用户在浏览器中输入一个需要登录的网址时，系统会首先自动从hosts文件中寻找对应的IP地址，一旦找到，系统会立即打开对应网页，如果没有找到，则系统会再将网址提交DNS域名解析服务器进行IP地址的解析。

windows:C:\Windows\System32\drivers\etc

centos：/etc/hosts

因为域名是要收取一定的费用，所以我们可以使用修改hosts文件来制作一些虚拟域名来使用。需要修改 `/etc/hosts`文件来添加

```java
vim /etc/hosts
127.0.0.1 www.xm.cn
127.0.0.1 www.itxm.cn
127.0.0.1 www.itwhite.cn
```



## 4.4：server_name



### 1.简介



server_name：用来设置虚拟主机服务名称。

127.0.0.1 、 localhost 、域名[www.baidu.com | www.jd.com]

127.0.0.1 、 localhost 、域名[www.baidu.com | www.jd.com]

|        |                                                           |
| ------ | --------------------------------------------------------- |
| 语法   | server_name  name ...;<br/>name可以提供多个中间用空格分隔 |
| 默认值 | server_name  "";                                          |
| 位置   | server                                                    |

关于server_name的配置方式有三种，分别是：

```
精确匹配
通配符匹配
正则表达式匹配
```



### 2.配置方式一：精确匹配





```java
server{
		listen 80;
		server_name www.xm.cn www.itxm.cn;
		location /{
			root html;
			index index.html;
		}
}	
```



我们在centos的游览器访问：http://192.168.244.100:80/

<img src="images/image-20210630181033903.png" alt="image-20210630181033903" style="zoom:80%;" />



### 3.配置方式二：通配符





server_name中支持通配符"*",但需要注意的是通配符不能出现在域名的中间，只能出现在首段或尾段，如：

```java
server {
	listen 80;
	server_name  *.xm.cn	www.xm.*;
	# www.itcast.cn abc.itcast.cn www.itheima.cn www.itheima.com
	...
}
```

下面的配置就会报错

```java
server {
	listen 80;
	server_name  www.*.cn www.itheima.c*
	...
}
```



### 4.配置方式三:使用正则表达式配置



server_name中可以使用正则表达式，并且使用`~`作为正则表达式字符串的开始标记。

常见的正则表达式

| 代码  | 说明                                                       |
| ----- | ---------------------------------------------------------- |
| ^     | 匹配搜索字符串开始位置                                     |
| $     | 匹配搜索字符串结束位置                                     |
| .     | 匹配除换行符\n之外的任何单个字符                           |
| \     | 转义字符，将下一个字符标记为特殊字符                       |
| [xyz] | 字符集，与任意一个指定字符匹配                             |
| [a-z] | 字符范围，匹配指定范围内的任何字符                         |
| \w    | 与以下任意字符匹配 A-Z a-z 0-9 和下划线,等效于[A-Za-z0-9_] |
| \d    | 数字字符匹配，等效于[0-9]                                  |
| {n}   | 正好匹配n次                                                |
| {n,}  | 至少匹配n次                                                |
| {n,m} | 匹配至少n次至多m次                                         |
| *     | 零次或多次，等效于{0,}                                     |
| +     | 一次或多次，等效于{1,}                                     |
| ?     | 零次或一次，等效于{0,1}                                    |



配置如下：



```java
server{
        listen 80;
        server_name ~^www\.(\w+)\.com$;
        default_type text/plain;
        return 200 $1  $2 ..;
}
注意 ~后面不能加空格，括号可以取值
```



### 5.匹配顺序



由于server_name指令支持通配符和正则表达式，因此在包含多个虚拟主机的配置文件中，可能会出现一个名称被多个虚拟主机的server_name匹配成功，当遇到这种情况，当前的请求交给谁来处理呢？

配置如下：

```java
server{
	listen 80;
	server_name ~^www\.\w+\.com$;
	default_type text/plain;
	return 200 'regex_success';
}

server{
	listen 80;
	server_name www.itxm.*;
	default_type text/plain;
	return 200 'wildcard_after_success';
}

server{
	listen 80;
	server_name *.itxm.com;
	default_type text/plain;
	return 200 'wildcard_before_success';
}

server{
	listen 80;
	server_name www.itxm.com;
	default_type text/plain;
	return 200 'exact_success';
}

server{
	listen 80 default_server;
	server_name _;
	default_type text/plain;
	return 444 'default_server not found server';
}
```



结果

No1:准确匹配server_name

No2:通配符在开始时匹配server_name成功

No3:通配符在结束时匹配server_name成功

No4:正则表达式匹配server_name成功

No5:被默认的default_server处理，如果没有指定默认找第一个server





## 4.5：location



### 1.简介



用来设置请求的URI

|        | 描述                                                     |
| ------ | -------------------------------------------------------- |
| 语法   | location [  =  \|   ~  \|  ~*   \|   ^~   \|@ ] uri{...} |
| 默认值 | —                                                        |
| 位置   | server,location                                          |

uri变量是待匹配的请求字符串，可以不包含正则表达式，也可以包含正则表达式，那么nginx服务器在搜索匹配location的时候，是先使用不包含正则表达式进行匹配，找到一个匹配度最高的一个，然后在通过包含正则表达式的进行匹配，如果能匹配到直接访问，匹配不到，就使用刚才匹配度最高的那个location来处理请求。

### 2.属性介绍



+ 不带符号，要求必须以指定模式开始



```java
server {
	listen 80;
	server_name 127.0.0.1;
	location /abc{
		default_type text/plain;
		return 200 "access success";
	}
}
以下访问都是正确的
http://192.168.200.133/abc
http://192.168.200.133/abc?p1=TOM
http://192.168.200.133/abc/
http://192.168.200.133/abcdef
```





+ `=` :  用于不包含正则表达式的uri前，必须与指定的模式精确匹配



```java
server {
	listen 80;
	server_name 127.0.0.1;
	location =/abc{
		default_type text/plain;
		return 200 "access success";
	}
}
可以匹配到
http://192.168.200.133/abc
http://192.168.200.133/abc?p1=TOM
匹配不到
http://192.168.200.133/abc/
http://192.168.200.133/abcdef
```



+ `~ `： 用于表示当前uri中包含了正则表达式，并且区分大小写
+ `~*`:  用于表示当前uri中包含了正则表达式，并且不区分大小写

换句话说，如果uri包含了正则表达式，需要用上述两个符合来标识



```java
server {
	listen 80;
	server_name 127.0.0.1;
	location ~^/abc\w${
		default_type text/plain;
		return 200 "access success";
	}
}
server {
	listen 80;
	server_name 127.0.0.1;
	location ~*^/abc\w${
		default_type text/plain;
		return 200 "access success";
	}
}
```



+ `^~`: 用于不包含正则表达式的uri前，功能和不加符号的一致，唯一不同的是，如果模式匹配，那么就停止搜索其他模式了。



```java
server {
	listen 80;
	server_name 127.0.0.1;
	location ^~/abc{
		default_type text/plain;
		return 200 "access success";
	}
}
```





## 4.6：root/alias设置请求资源的目录



### 1.root设置请求的根目录



|        | 描述                   |
| ------ | ---------------------- |
| 语法   | root path;             |
| 默认值 | root html;             |
| 位置   | http、server、location |

path为Nginx服务器接收到请求以后查找资源的根目录路径。



### 2.alias用来更改location的URI



|        | 描述        |
| ------ | ----------- |
| 语法   | alias path; |
| 默认值 | —           |
| 位置   | location    |

path为修改后的根路径。

以上两个指令都可以来指定访问资源的路径，那么这两者之间的区别是什么?

### 3.举例说明：



（1）在`/usr/local/nginx/html`目录下创建一个 images目录,并在目录下放入一张图片`mv.png`图片

```java
location /images {
	root /usr/local/nginx/html;
}
```

访问图片的路径为:

```java
http://192.168.200.133/images/mv.png
```

（2）如果把root改为alias

```java
location /images {
	alias /usr/local/nginx/html;
}
```

再次访问上述地址，页面会出现404的错误，查看错误日志会发现是因为地址不对，所以验证了：

```java
root的处理结果是: root路径+location路径
/usr/local/nginx/html/images/mv.png
alias的处理结果是:使用alias路径替换location路径
/usr/local/nginx/html/images
```

需要在alias后面路径改为

```java
location /images {
	alias /usr/local/nginx/html/images;
}
```

（3）如果location路径是以/结尾,则alias也必须是以/结尾，root没有要求

将上述配置修改为

```java
location /images/ {
	alias /usr/local/nginx/html/images;
}
```

访问就会出问题，查看错误日志还是路径不对，所以需要把alias后面加上 / 



### 4.小结



+ root的处理结果是: root路径+location路径
+ alias的处理结果是:使用alias路径替换location路径
+ alias是一个目录别名的定义，root则是最上层目录的含义。
+ 如果location路径是以/结尾,则alias也必须是以/结尾，root没有要求

## 4.7：index指令



### 1.简介



index:设置网站的默认首页

|        | 描述                   |
| ------ | ---------------------- |
| 语法   | index file ...;        |
| 默认值 | index index.html;      |
| 位置   | http、server、location |

index后面可以跟多个设置，如果访问的时候没有指定具体访问的资源，则会依次进行查找，找到第一个为止。



### 2.举例说明



```java
location / {
	root /usr/local/nginx/html;
	index index.html index.htm;
}
访问该location的时候，可以通过 http://ip:port/，地址后面如果不添加任何内容，则默认依次访问index.html和index.htm，找到第一个来进行返回
```



## 4.8：error_page指令



### 1.简介



error_page:设置网站的错误页面

|        | 描述                                   |
| ------ | -------------------------------------- |
| 语法   | error_page code ... [=[response]] uri; |
| 默认值 | —                                      |
| 位置   | http、server、location......           |

当出现对应的响应code后，如何来处理。



### 2.举例说明



（1）可以指定具体跳转的地址

```java
server {
	error_page 404 http://www.itcast.cn;
}
```

（2）可以指定重定向地址

```java
server{
	error_page 404 /50x.html;
	error_page 500 502 503 504 /50x.html;
	location =/50x.html{
		root html;
	}
}
```

（3）使用location的@符合完成错误信息展示

```java
server{
	error_page 404 @jump_to_error;
	location @jump_to_error {
		default_type text/plain;
		return 404 'Not Found Page...';
	}
}
```

可选项`=[response]`的作用是用来将相应代码更改为另外一个

```java
server{
	error_page 404 =200 /50x.html;
	location =/50x.html{
		root html;
	}
}
这样的话，当返回404找不到对应的资源的时候，在浏览器上可以看到，最终返回的状态码是200，这块需要注意下，编写error_page后面的内容，404后面需要加空格，200前面不能加空格
```



## 4.9：静态资源优化配置语法



### 1.简介



Nginx对静态资源如何进行优化配置。这里从三个属性配置进行优化：

```java
sendfile on;
tcp_nopush on;
tcp_nodeplay on;
```



### 2.sendﬁle用来开启高效的文件传输模式



|        | 描述                      |
| ------ | ------------------------- |
| 语法   | sendﬁle on \|oﬀ;          |
| 默认值 | sendﬁle oﬀ;               |
| 位置   | http、server、location... |

请求静态资源的过程：客户端通过网络接口向服务端发送请求，操作系统将这些客户端的请求传递给服务器端应用程序，服务器端应用程序会处理这些请求，请求处理完成以后，操作系统还需要将处理得到的结果通过网络适配器传递回去。

如：

```java
server {
	listen 80;
	server_name localhost；
	location / {
		root html;
		index index.html;
	}
}
在html目录下有一个welcome.html页面，访问地址
http://192.168.200.133/welcome.html
```

![1587655397104](images/1587655397104.png)



![1587665814562](images/1587665814562.png)



### 3.tcp_nopush主要是用来提升网络包的传输'效率'



该指令必须**在sendfile打开的状态下才会生效**，主要是用来提升网络包的传输'效率'

|        | 描述                   |
| ------ | ---------------------- |
| 语法   | tcp_nopush on\|off;    |
| 默认值 | tcp_nopush oﬀ;         |
| 位置   | http、server、location |



### 4.tcp_nodelay提高网络包传输的'实时性'



该指令必须在**keep-alive连接开启的情况下才生效**，来提高网络包传输的'实时性'

|        | 描述                   |
| ------ | ---------------------- |
| 语法   | tcp_nodelay on\|off;   |
| 默认值 | tcp_nodelay on;        |
| 位置   | http、server、location |

![1587832596733](images/1587832596733.png)



### 5.总结



经过刚才的分析，"tcp_nopush"和”tcp_nodelay“看起来是"互斥的"，那么为什么要将这两个值都打开，这个大家需要知道的是在linux2.5.9以后的版本中两者是可以兼容的，三个指令都开启的好处是，sendfile可以开启高效的文件传输模式，tcp_nopush开启可以确保在发送到客户端之前数据包已经充分“填满”， 这大大减少了网络开销，并加快了文件发送的速度。 然后，当它到达最后一个可能因为没有“填满”而暂停的数据包时，Nginx会忽略tcp_nopush参数， 然后，tcp_nodelay强制套接字发送数据。由此可知，TCP_NOPUSH可以与TCP_NODELAY一起设置，它比单独配置TCP_NODELAY具有更强的性能。所以我们可以使用如下配置来优化Nginx静态资源的处理



```java
sendfile on;
tcp_nopush on;
tcp_nodelay on;
```



## 4.10：Nginx静态资源压缩实战 



### 1.简介



经过上述内容的优化，我们再次思考一个问题，假如在满足上述优化的前提下，我们传送一个1M的数据和一个10M的数据那个效率高?，答案显而易见，传输内容小，速度就会快。那么问题又来了，同样的内容，如果把大小降下来，我们脑袋里面要蹦出一个词就是"压缩"，接下来，我们来学习Nginx的静态资源压缩模块。

在Nginx的配置文件中可以通过配置gzip来对静态资源进行压缩，相关的指令可以配置在http块、server块和location块中，Nginx可以通过

```java
ngx_http_gzip_module模块
ngx_http_gzip_static_module模块
ngx_http_gunzip_module模块
```

对这些指令进行解析和处理。



### 2.什么是GZIP



**GZIP是网站压缩加速的一种技术，对于开启后可以加快我们网站的打开速度，原理是经过服务器压缩，客户端浏览器快速解压的原理，可以大大减少了网站的流量。**

1.什么是GZIP答：GZIP最早由Jean-loup Gailly和Mark Adler创建，用于UNIX系统的文件压缩。我们在Linux中经常会用到后缀为.gz的文件，它们就是GZIP格式的。现今已经成为Internet 上使用非常普遍的一种数据压缩格式，或者说一种文件格式。HTTP协议上的GZIP编码是一种用来改进WEB应用程序性能的技术。大流量的WEB站点常常使用GZIP压缩技术来让用户感受更快的速度。这一般是指WWW服务器中安装的一个功能,当有人来访问这个服务器中的网站时,服务器中的这个功能就将网页内容压缩后传输到来访的电脑浏览器中显示出来.一般对纯文本内容可压缩到原大小的40％.这样传输就快了,效果就是你点击网址后会很快的显示出来.当然这也会增加服务器的负载. 一般服务器中都安装有这个功能模块的.

2.开GZIP有什么好处？答：Gzip开启以后会将输出到用户浏览器的数据进行压缩的处理，这样就会减小通过网络传输的数据量，提高浏览的速度。



### 3.gzip指令



该指令用于开启或者关闭gzip功能



|        | 描述                      |
| ------ | ------------------------- |
| 语法   | gzip on\|off;             |
| 默认值 | gzip off;                 |
| 位置   | http、server、location... |

注意只有该指令为打开状态，下面的指令才有效果

```java
http{
   gzip on;
}
```



### 4.gzip_types指令



该指令可以根据响应页的MIME类型选择性地开启Gzip压缩功能

|        | 描述                      |
| ------ | ------------------------- |
| 语法   | gzip_types mime-type ...; |
| 默认值 | gzip_types text/html;     |
| 位置   | http、server、location    |

**所选择的值可以从mime.types文件中进行查找，也可以使用"*"代表所有。如果配置多个可以使用空格**



### 5.gzip_comp_level指令



该指令用于设置Gzip压缩程度，级别从1-9,

+ `1`表示要是程度最低，要是效率最高，
+ `9`刚好相反，压缩程度最高，但是效率最低最费时间。



|        | 描述                   |
| ------ | ---------------------- |
| 语法   | gzip_comp_level level; |
| 默认值 | gzip_comp_level 1;     |
| 位置   | http、server、location |



```java
http{
	gzip_comp_level 6;
}
```



### 6.gzip_vary指令



该指令用于设置使用Gzip进行压缩发送是否携带“Vary:Accept-Encoding”头域的响应头部。主要是告诉接收方，所发送的数据经过了Gzip压缩处理

|        | 描述                   |
| ------ | ---------------------- |
| 语法   | gzip_vary on\|off;     |
| 默认值 | gzip_vary off;         |
| 位置   | http、server、location |

![1587361606028](images/1587361606028.png)



### 7.gzip_buffers指令



该指令用于处理请求压缩的缓冲区数量和大小。



|        | 描述                       |
| ------ | -------------------------- |
| 语法   | gzip_buffers number size;  |
| 默认值 | gzip_buffers 32 4k\|16 8k; |
| 位置   | http、server、location     |



其中number:指定Nginx服务器向系统申请缓存空间个数，size指的是每个缓存空间的大小。主要实现的是申请number个每个大小为size的内存空间。这个值的设定一般会和服务器的操作系统有关，**所以建议此项不设置，使用默认值即可。**



```java
gzip_buffers 4 16K;	  #缓存空间大小
```



### 7.ip_disable指令

针对不同种类客户端发起的请求，可以选择性地开启和关闭Gzip功能。

|        | 描述                    |
| ------ | ----------------------- |
| 语法   | gzip_disable regex ...; |
| 默认值 | —                       |
| 位置   | http、server、location  |

regex:根据客户端的浏览器标志(user-agent)来设置，支持使用正则表达式。指定的浏览器标志不使用Gzip.该指令一般是用来排除一些明显不支持Gzip的浏览器。

```java
gzip_disable "MSIE [1-6]\.";
```



### 8.gzip_http_version指令



针对不同的HTTP协议版本，可以选择性地开启和关闭Gzip功能。



|        | 描述                        |
| ------ | --------------------------- |
| 语法   | gzip_http_version 1.0\|1.1; |
| 默认值 | gzip_http_version 1.1;      |
| 位置   | http、server、location      |

该指令是指定使用Gzip的HTTP最低版本，**该指令一般采用默认值即可。**





### 9.gzip_min_length指令



该指令针对传输数据的大小，可以选择性地开启和关闭Gzip功能

|        | 描述                    |
| ------ | ----------------------- |
| 语法   | gzip_min_length length; |
| 默认值 | gzip_min_length 20;     |
| 位置   | http、server、location  |

```java
nignx计量大小的单位：bytes[字节] / kb[千字节] / M[兆]
例如: 1024 / 10k|K / 10m|M
```

Gzip压缩功能对大数据的压缩效果明显，但是如果要压缩的数据比较小的化，可能出现越压缩数据量越大的情况，因此我们需要根据响应内容的大小来决定是否使用Gzip功能，响应页面的大小可以通过头信息中的`Content-Length`来获取。但是如何使用了Chunk编码动态压缩，该指令将被忽略。建议设置为1K或以上。



### 10.gzip_proxied指令



该指令设置是否对服务端返回的结果进行Gzip压缩。

|        | 描述                                                         |
| ------ | ------------------------------------------------------------ |
| 语法   | gzip_proxied  off\|expired\|no-cache\|<br/>no-store\|private\|no_last_modified\|no_etag\|auth\|any; |
| 默认值 | gzip_proxied off;                                            |
| 位置   | http、server、location                                       |

+ off - 关闭Nginx服务器对后台服务器返回结果的Gzip压缩
+ expired - 启用压缩，如果header头中包含 "Expires" 头信息
+ no-cache - 启用压缩，如果header头中包含 "Cache-Control:no-cache" 头信息
+ no-store - 启用压缩，如果header头中包含 "Cache-Control:no-store" 头信息
+ private - 启用压缩，如果header头中包含 "Cache-Control:private" 头信息
+ no_last_modified - 启用压缩,如果header头中不包含 "Last-Modified" 头信息
+ no_etag - 启用压缩 ,如果header头中不包含 "ETag" 头信息
+ auth - 启用压缩 , 如果header头中包含 "Authorization" 头信息
+ any - 无条件启用压缩



### 11.Gzip压缩功能的实例配置



这些配置在很多地方可能都会用到，所以我们可以将这些内容抽取到一个配置文件中，然后通过include指令把配置文件再次加载到nginx.conf配置文件中，方法使用。



+ nginx_gzip.conf



```shell
#开启gzip功能
gzip on; 
#压缩源文件类型,根据具体的访问资源类型设定
gzip_types *;
#gzip压缩级别
gzip_comp_level 6;	 	
#进行压缩响应页面的最小长度,content-length
gzip_min_length 1024;
#缓存空间大小
gzip_buffers 4 16K;	 
#指定压缩响应所需要的最低HTTP请求版本
gzip_http_version 1.1; 	
#往头信息中添加压缩标识
gzip_vary  on;		  
#对IE6以下的版本都不进行压缩
gzip_disable "MSIE [1-6]\."; 		
#nginx作为反向代理压缩服务端返回数据的条件
gzip_proxied  off; 			
```



+ nginx.conf



```java
include nginx_gzip.conf;
```

<img src="images/image-20210701161018816.png" alt="image-20210701161018816" style="zoom:80%;" />





## 4.11：Gzip和sendfile共存问题



### 1.简介



前面在讲解sendfile的时候，提到过，开启sendfile以后，在读取磁盘上的静态资源文件的时候，可以减少拷贝的次数，可以不经过用户进程将静态文件通过网络设备发送出去，但是Gzip要想对资源压缩，是需要经过用户进程进行操作的。所以如何解决两个设置的共存问题。

可以使用ngx_http_gzip_static_module模块的`gzip_static`指令来解决。



### 2.gzip_static指令



gzip_static: 检查与访问资源同名的.gz文件时，response中以gzip相关的header返回.gz文件的内容。

| 语法   | **gzip_static** on \| off \| always; |
| ------ | ------------------------------------ |
| 默认值 | gzip_static off;                     |
| 位置   | http、server、location               |

添加上述命令后，会报一个错误，`unknown directive "gzip_static"`主要的原因是Nginx默认是没有添加ngx_http_gzip_static_module模块。如何来添加?



### 3.添加模块到Nginx的实现步骤



(1)查询当前Nginx的配置参数

```java
nginx -V
```

(2)将nginx安装目录下sbin目录中的nginx二进制文件进行更名

```java
cd /usr/local/nginx/sbin
mv nginx nginxold
```

(3) 进入Nginx的安装目录

```java
cd /root/nginx/core/nginx-1.16.1
```

(4)执行make clean清空之前编译的内容

```java
make clean
```

(5)使用configure来配置参数

```java
./configure --with-http_gzip_static_module
```

(6)使用make命令进行编译

```java
make
```

(7) 将objs目录下的nginx二进制执行文件移动到nginx安装目录下的sbin目录中

```java
mv objs/nginx /usr/local/nginx/sbin
```

(8)执行更新命令

```java
make upgrade
```



### 4.gzip_static测试使用



(1)直接访问`http://192.168.200.133/jquery.js`

![1587932106429](images/1587932106429.png)



(2)使用gzip命令进行压缩

```java
cd /usr/local/nginx/html
gzip jquery.js
```

(3)再次访问`http://192.168.200.133/jquery.js`

![1587932300006](images/1587932300006.png)

## 4.12：静态资源的缓存处理



### 1.什么是缓存



缓存（cache），原始意义是指访问速度比一般随机存取存储器（RAM）快的一种高速存储器，通常它不像系统主存那样使用DRAM技术，而使用昂贵但较快速的SRAM技术。缓存的设置是所有现代计算机系统发挥高性能的重要因素之一。



### 2.什么是web缓存



Web缓存是指一个Web资源（如html页面，图片，js，数据等）存在于Web服务器和客户端（浏览器）之间的副本。缓存会根据进来的请求保存输出内容的副本；当下一个请求来到的时候，如果是相同的URL，缓存会根据缓存机制决定是直接使用副本响应访问请求，还是向源服务器再次发送请求。比较常见的就是浏览器会缓存访问过网站的网页，当再次访问这个URL地址的时候，如果网页没有更新，就不会再次下载网页，而是直接使用本地缓存的网页。只有当网站明确标识资源已经更新，浏览器才会再次下载网页



### 3.web缓存的种类



+ 客户端缓存
	+ 浏览器缓存
+ 服务端缓存
	+ Nginx / Redis / Memcached等



### 4.浏览器缓存



是为了节约网络的资源加速浏览，浏览器在用户磁盘上对最近请求过的文档进行存储，当访问者再次请求这个页面时，浏览器就可以从本地磁盘显示文档，这样就可以加速页面的阅览.



### 5.为什么要用浏览器缓存



+ 成本最低的一种缓存实现
+ 减少网络带宽消耗
+ 降低服务器压力
+ 减少网络延迟，加快页面打开速度



### 6.浏览器缓存的执行流程



HTTP协议中和页面缓存相关的字段，我们先来认识下：

| header        | 说明                                        |
| ------------- | ------------------------------------------- |
| Expires       | 缓存过期的日期和时间                        |
| Cache-Control | 设置和缓存相关的配置信息                    |
| Last-Modified | 请求资源最后修改时间                        |
| ETag          | 请求变量的实体标签的当前值，比如文件的MD5值 |



![](images/1581762832290.png)

+ 用户首次通过浏览器发送请求到服务端获取数据，客户端是没有对应的缓存，所以需要发送request请求来获取数据；

+ 服务端接收到请求后，获取服务端的数据及服务端缓存的允许后，返回200的成功状态码并且在响应头上附上对应资源以及缓存信息；

+ 当用户再次访问相同资源的时候，客户端会在浏览器的缓存目录中查找是否存在响应的缓存文件

+ 如果没有找到对应的缓存文件，则走(2)步

+ 如果有缓存文件，接下来对缓存文件是否过期进行判断，过期的判断标准是(Expires),

+ 如果没有过期，则直接从本地缓存中返回数据进行展示

+ 如果Expires过期，接下来需要判断缓存文件是否发生过变化

+ 判断的标准有两个，一个是ETag(Entity Tag),一个是Last-Modified

+ 判断结果是未发生变化，则服务端返回304，直接从缓存文件中获取数据

+ 如果判断是发生了变化，重新从服务端获取数据，并根据缓存协商(服务端所设置的是否需要进行缓存数据的设置)来进行数据缓存。



### 7.expires指令



expires:该指令用来控制页面缓存的作用。可以通过该指令控制HTTP应答中的“Expires"和”Cache-Control"

|        | 语法                                                   |
| ------ | ------------------------------------------------------ |
| 语法   | expires   [modified] time<br/>expires epoch\|max\|off; |
| 默认值 | expires off;                                           |
| 位置   | http、server、location                                 |

+ time:可以整数也可以是负数，指定过期时间，如果是负数，Cache-Control则为no-cache,如果为整数或0，则Cache-Control的值为max-age=time;

+ epoch: 指定Expires的值为'1 January,1970,00:00:01 GMT'(1970-01-01 00:00:00)，Cache-Control的值no-cache

+ max:指定Expires的值为'31 December2037 23:59:59GMT' (2037-12-31 23:59:59) ，Cache-Control的值为10年

+ off:默认不缓存。



### 8.add_header指令



add_header指令是用来添加指定的响应头和响应值。

| 语法   | add_header name value [always]; |
| ------ | ------------------------------- |
| 默认值 | —                               |
| 位置   | http、server、location...       |

Cache-Control作为响应头信息，可以设置如下值：

缓存响应指令：

```properties
Cache-control: must-revalidate
Cache-control: no-cache
Cache-control: no-store
Cache-control: no-transform
Cache-control: public
Cache-control: private
Cache-control: proxy-revalidate
Cache-Control: max-age=<seconds>
Cache-control: s-maxage=<seconds>
```

| 指令             | 说明                                           |
| ---------------- | ---------------------------------------------- |
| must-revalidate  | 可缓存但必须再向源服务器进行确认               |
| no-cache         | 缓存前必须确认其有效性                         |
| no-store         | 不缓存请求或响应的任何内容                     |
| no-transform     | 代理不可更改媒体类型                           |
| public           | 可向任意方提供响应的缓存                       |
| private          | 仅向特定用户返回响应                           |
| proxy-revalidate | 要求中间缓存服务器对缓存的响应有效性再进行确认 |
| max-age=<秒>     | 响应最大Age值                                  |
| s-maxage=<秒>    | 公共缓存服务器响应的最大Age值                  |

max-age=[秒]：



## 4.13：跨域处理



### 1.同源策略



浏览器的同源策略：是一种约定，是浏览器最核心也是最基本的安全功能，如果浏览器少了同源策略，则浏览器的正常功能可能都会受到影响。

同源:  协议、域名(IP)、端口相同即为同源

```java
http://192.168.200.131/user/1
https://192.168.200.131/user/1
不

http://192.168.200.131/user/1
http://192.168.200.132/user/1
不

http://192.168.200.131/user/1
http://192.168.200.131:8080/user/1
不

http://www.nginx.com/user/1
http://www.nginx.org/user/1
不

http://192.168.200.131/user/1
http://192.168.200.131:8080/user/1
不

http://www.nginx.org:80/user/1
http://www.nginx.org/user/1
满足
```



### 2.跨域问题



简单描述下:

**有两台服务器分别为A,B,如果从服务器A的页面发送异步请求到服务器B获取数据，如果服务器A和服务器B不满足同源策略，则就会出现跨域问题。**



### 3.跨域问题的案例演示



#### 简介



出现跨域问题会有什么效果?,接下来通过一个需求来给大家演示下：

<img src="images/image-20210701165635748.png" alt="image-20210701165635748" style="zoom:80%;" />

#### nginx的html目录下新建一个a.html



```html
<html>
  <head>
        <meta charset="utf-8">
        <title>跨域问题演示</title>
        <script src="jquery.js"></script>
        <script>
            $(function(){
                $("#btn").click(function(){
                        $.get('http://192.168.244.100:8080/getUser',function(data){
                                alert(JSON.stringify(data));
                        });
                });
            });
        </script>
  </head>
  <body>
        <input type="button" value="获取数据" id="btn"/>
  </body>
</html>

```



#### 在nginx.conf配置如下内容



```java
server{
        listen  8080;
        server_name localhost;
        location /getUser{
                default_type application/json;
                return 200 '{"id":1,"name":"TOM","age":18}';
        }
}
server{
	listen 	80;
	server_name localhost;
	location /{
		root html;
		index index.html;
	}
}
```



#### 通过浏览器访问测试



<img src="images/image-20210701170727661.png" alt="image-20210701170727661" style="zoom:80%;" />



#### 解决方案



使用add_header指令，该指令可以用来添加一些头信息

|        | 语法                      |
| ------ | ------------------------- |
| 语法   | add_header name  value... |
| 默认值 | —                         |
| 位置   | http、server、location    |

此处用来解决跨域问题，需要添加两个头信息，一个是`Access-Control-Allow-Origin`,`Access-Control-Allow-Methods`

+ Access-Control-Allow-Origin: 直译过来是允许跨域访问的源地址信息，可以配置多个(多个用逗号分隔)，也可以使用`*`代表所有源

+ Access-Control-Allow-Methods:直译过来是允许跨域访问的请求方式，值可以为 GET POST PUT DELETE...,可以全部设置，也可以根据需要设置，多个用逗号分隔

具体配置方式

```java
location /getUser{
    add_header Access-Control-Allow-Origin *;
    add_header Access-Control-Allow-Methods GET,POST,PUT,DELETE;
    default_type application/json;
    return 200 '{"id":1,"name":"TOM","age":18}';
}
```





## 4.15：静态资源防盗链



### 1.什么是资源盗链



资源盗链指的是此内容不在自己服务器上，而是通过技术手段，绕过别人的限制将别人的内容放到自己页面上最终展示给用户。以此来盗取大网站的空间和流量。简而言之就是用别人的东西成就自己的网站。

效果演示

京东:https://img14.360buyimg.com/n7/jfs/t1/101062/37/2153/254169/5dcbd410E6d10ba22/4ddbd212be225fcd.jpg

百度:https://pics7.baidu.com/feed/cf1b9d16fdfaaf516f7e2011a7cda1e8f11f7a1a.jpeg?token=551979a23a0995e5e5279b8fa1a48b34&s=BD385394D2E963072FD48543030030BB

我们自己准备一个页面，在页面上引入这两个图片查看效果

![](images/1581827029973.png)

从上面的效果，可以看出来，下面的图片地址添加了防止盗链的功能，京东这边我们可以直接使用其图片。

### 2.Nginx防盗链的实现原理



了解防盗链的原理之前，我们得先学习一个HTTP的头信息Referer,当浏览器向web服务器发送请求的时候，一般都会带上Referer,来告诉浏览器该网页是从哪个页面链接过来的。

![](images/1581769820325.png)

后台服务器可以根据获取到的这个Referer信息来判断是否为自己信任的网站地址，如果是则放行继续访问，如果不是则可以返回403(服务端拒绝访问)的状态信息。

在本地模拟上述的服务器效果：

![](images/1581769079083.png)

Nginx防盗链的具体实现:

valid_referers:nginx会通就过查看referer自动和valid_referers后面的内容进行匹配，如果匹配到了就将$invalid_referer变量置0，如果没有匹配到，则将\$invalid_referer变量置为1，匹配的过程中不区分大小写。

|        | 描述                                                  |
| ------ | ----------------------------------------------------- |
| 语法   | valid_referers none\|blocked\|server_names\|string... |
| 默认值 | —                                                     |
| 位置   | server、location                                      |

+ none: 如果Header中的Referer为空，允许访问

+ blocked:在Header中的Referer不为空，但是该值被防火墙或代理进行伪装过，如不带"http://" 、"https://"等协议头的资源允许访问。

+ server_names:指定具体的域名或者IP

+ string: 可以支持正则表达式和*的字符串。如果是正则表达式，需要以`~`开头表示，例如

```java
location ~*\.(png|jpg|gif){
           valid_referers none blocked www.baidu.com 192.168.200.222 *.example.com example.*  www.example.org  ~\.google\.;
           if ($invalid_referer){
                return 403;
           }
           root /usr/local/nginx/html;

}
```



遇到的问题:图片有很多，该如何批量进行防盗链？



### 3.针对目录进行防盗链



配置如下：

```java
location /images {
           valid_referers none blocked www.baidu.com 192.168.200.222 *.example.com example.*  www.example.org  ~\.google\.;
           if ($invalid_referer){
                return 403;
           }
           root /usr/local/nginx/html;

}
```

这样我们可以对一个目录下的所有资源进行翻到了操作。

遇到的问题：Referer的限制比较粗，比如随意加一个Referer，上面的方式是无法进行限制的。那么这个问题改如何解决？

此处我们需要用到Nginx的第三方模块`ngx_http_accesskey_module`，第三方模块如何实现盗链，如果在Nginx中使用第三方模块的功能，这些我们在后面的Nginx的模块篇再进行详细的讲解。



# 五、Rewrite功能配置



## 5.1：简介



Rewrite是Nginx服务器提供的一个重要基本功能，是Web服务器产品中几乎必备的功能。主要的作用是用来实现URL的重写。

注意:Nginx服务器的Rewrite功能的实现依赖于PCRE的支持，因此在编译安装Nginx服务器之前，需要安装PCRE库。Nginx使用的是`ngx_http_rewrite_module`模块来解析和处理Rewrite功能的相关配置。





## 5.2："地址重写"与"地址转发"



重写和转发的区别:

+ 地址重写浏览器地址会发生变化而地址转发则不变
+ 一次地址重写会产生两次请求而一次地址转发只会产生一次请求
+ 地址重写到的页面必须是一个完整的路径而地址转发则不需要
+ 地址重写因为是两次请求所以request范围内属性不能传递给新页面而地址转发因为是一次请求所以可以传递值
+ 地址转发速度快于地址重写



#### Rewrite规则

















