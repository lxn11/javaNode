# centos8命令

## 添加中文语言包（解决 vi 中文乱码）



```java
yum -y install langpacks-zh_CN.noarch
```



## dnf同yum使用方法


```java
dnf -y update
```



## 查看ip信息



```java
nmcli
```





## 重启网卡 指定网卡名重启，否则重启所有网卡


```java
nmcli c reload + 网卡名
```



## 取关于已知设备的完整信息


```java
nmcli device show
```



## 取活动连接配置集的概述


````java
nmcli connection show
nmcli c reload                   #重启网卡
nmcli c up ens33             #启用ens33网卡
nmcli networking off     #关闭网络
nmcli networking on     #开启网络
nmcli device show          #显示网络的详细情况
````

