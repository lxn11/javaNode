<h1>Shell脚本编程</h1>

[TOC]



---------



# 一、HelloWorld



## 1.1：什么是shell



Shell 是一个用 C 语言编写的程序，它是用户使用 Linux 的桥梁。Shell 既是一种命令语言，又是一种程序设计语言。

Shell 是指一种应用程序，这个应用程序提供了一个界面，用户通过这个界面访问操作系统内核的服务。

Ken Thompson 的 sh 是第一种 Unix Shell，Windows Explorer 是一个典型的图形界面 Shell。



## 1.2：Shell 脚本



Shell 脚本（shell script），是一种为 shell 编写的脚本程序。

业界所说的 shell 通常都是指 shell 脚本，但读者朋友要知道，shell 和 shell script 是两个不同的概念。

由于习惯的原因，简洁起见，本文出现的 "shell编程" 都是指 shell 脚本编程，不是指开发 shell 自身。



## 1.3：Shell 环境



Shell 编程跟 JavaScript、php 编程一样，只要有一个能编写代码的文本编辑器和一个能解释执行的脚本解释器就可以了。

Linux 的 Shell 种类众多，常见的有：

- Bourne Shell（/usr/bin/sh或/bin/sh）
- Bourne Again Shell（/bin/bash）
- C Shell（/usr/bin/csh）
- K Shell（/usr/bin/ksh）
- Shell for Root（/sbin/sh）
- ……

本教程关注的是 Bash，也就是 Bourne Again Shell，由于易用和免费，Bash 在日常工作中被广泛使用。同时，Bash 也是大多数Linux 系统默认的 Shell。

在一般情况下，人们并不区分 Bourne Shell 和 Bourne Again Shell，所以，像 **#!/bin/sh**，它同样也可以改为 **#!/bin/bash**。

**#!** 告诉系统其后路径所指定的程序即是解释此脚本文件的 Shell 程序。



## 1.4：base特性



###1.命令和文件自动补全



Tab只能补全==命令和文件== 



###2.常见的快捷键



| 快捷键         | 描述                       |
| -------------- | -------------------------- |
| CTRL+c         | 终止前台运行的程序         |
| CTRL+z         | 将前台运行的程序挂起到后台 |
| CTRL+d         | 退出 等价exit              |
| CTRL+l         | 清屏                       |
| CTRL+a \| home | 光标移到命令行的最前端     |
| CTRL+e \| end  | 光标移到命令行的后端       |
| CTRL+u         | 删除光标前所有字符         |
| CTRL+k         | 删除光标后所有字符         |
| CTRL+r         | 搜索历史命令               |

​		

###3 .常用的通配符（重点）



| 通配符                | 描述                                                |
| --------------------- | --------------------------------------------------- |
| *                     | 匹配0或多个任意字符                                 |
| ?                     | 匹配任意单个字符                                    |
| [list]                | 匹配[list]中的任意单个字符,或者一组单个字符   [a-z] |
| [!list]               | 匹配除list中的任意单个字符                          |
| {string1,string2,...} | 匹配string1,string2或更多字符串                     |



~~~java
# rm -f file*
# cp *.conf  /dir1
# touch file{1..5}
~~~



###4.bash中的引号（重点）



| 类型   | 名字 | 描述                                                         |
| ------ | ---- | ------------------------------------------------------------ |
| 双引号 | ""   | 会把引号的内容当成整体来看待，允许通过$符号引用其他变量值    |
| 单引号 | ''   | 会把引号的内容当成整体来看待，禁止引用其他变量值，shell中特殊符号都被视为普通字符 |
| 反撇号 | ``   | 反撇号和$()一样，引号或括号里的命令会优先执行，如果存在嵌套，反撇号不能用 |



~~~java
[root@localhost /]# date
2021年 09月 10日 星期五 15:26:35 CST
[root@localhost /]# echo "date"
date
[root@localhost /]# echo 'date'
date
[root@localhost /]# echo `date`
2021年 09月 10日 星期五 15:26:54 CST
[root@localhost /]# echo '$(date)'
$(date)
[root@localhost /]# echo "$(date)"
2021年 09月 10日 星期五 15:27:37 CST
~~~





## 1.5：shell基本写法



+ **脚本第一行**，魔法字符==**#!**==指定解释器【==必写==】



`#!/bin/bash`  表示以下内容使用bash解释器解析

**==注意：==**
如果直接将解释器路径写死在脚本里，可能在某些系统就会存在找不到解释器的兼容性问题，所以可以使用:`#!/bin/env 解释器`



+ **脚本第二部分**，注释(#号)说明，对脚本的基本信息进行描述【可选】



~~~shell
#!/bin/env bash

# 以下内容是对脚本的基本信息的描述
# Name: 名字
# Desc:描述describe
# Path:存放路径
# Usage:用法
# Update:更新时间

#下面就是脚本的具体内容
commands
...
~~~



+ **脚本第三部分**，脚本要实现的具体代码内容





## 1.6：helloworld



```shell
#!/bin/env bash
echo 'hello shell'
echo 'hello shell'
echo 'hello shell'
```





## 1.7： shell执行



+ 第一步



```shell
chmod +x ./test.sh  #使脚本具有执行权限
```



+ 查看下面执行方式，推荐最后一种



```shell
[root@localhost shell]# chmod +x helloworld.sh 
[root@localhost shell]# ./helloworld.sh 
hello shell
hello shell
hello shell
[root@localhost shell]# sh helloworld.sh 
hello shell
hello shell
hello shell
[root@localhost shell]# bash helloworld.sh 
hello shell
hello shell
hello shell
[root@localhost shell]# bash -x helloworld.sh 
+ echo 'hello shell'
hello shell
+ echo 'hello shell'
hello shell
+ echo 'hello shell'
hello shell
[root@localhost shell]# source helloworld.sh 
hello shell
hello shell
hello shell
```



## 1.8：练习



练习案例1



# 二、变量



## 2.1：变量定义赋值取值



**变量名=====变量值**

+ 变量名：用来临时保存数据的

+ 变量值：就是临时的可变化的数

```java
[root@localhost shell]# a=hello
[root@localhost shell]# echo $a
hello
[root@localhost shell]# echo ${a}
hello
[root@localhost shell]# a=world
[root@localhost shell]# echo $a
world
[root@localhost shell]# unset a
[root@localhost shell]# echo $a
    
```



## 2.2：简单定义



> **直接赋值给一个变量**



<img src="images/image-20210910165345767.png" alt="image-20210910165345767" style="zoom:80%;" />



## 2.3：命令执行结果赋值给变量



<img src="images/image-20210910165806496.png" alt="image-20210910165806496" style="zoom:80%;" />



## 2.4：交互式定义变量-read



### 1.简介



+ **作用**
	+ 让==用户自己==给变量赋值，比较灵活。类似于java中的Scanner类命令行输入内容监听

+ **语法：**
	+ `read [选项] 变量名`

+ **常见选项：**



| 选项 | 释义                                                       |
| ---- | ---------------------------------------------------------- |
| -p   | 定义提示用户的信息                                         |
| -n   | 定义字符数（限制变量值的长度）                             |
| -s   | 不显示（不显示用户输入的内容）                             |
| -t   | 定义超时时间，默认单位为秒（限制用户输入变量值的超时时间） |



### 2.使用案例

















# 案例



## 案例1



### 1.需求



1. 删除/tmp/目录下的所有文件

2. 然后在/tmp目录里创建3个目录，分别是dir1~dir3

3. 拷贝/etc/hosts文件到刚创建的dir1目录里

4. 最后打印"报告首长，任务已于2019-05-05 10:10:10时间完成"内容



### 2.代码



```shell
# !/bin/env bash
echo '开始删除/tmp/目录下的所有文件'
rm -rf /tmp/*
echo '删除/tmp/目录下的所有文件,开始创建文件目录'
mkdir /tmp/dir1 /tmp/dir2 /tmp/dir3
cp /etc/hosts /tmp/dir1/hostsbak
echo '拷贝文件成功，文件名是  hostsbak'
echo  "报告首长，任务已于$(date +'%F %T')完成内容"
echo "$(tree /tmp/)"
```

































