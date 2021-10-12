#笔记

## 查看统一使用typora软件查看
 
 
## 右键新建增加md文件
新建txt文档，写入下面代码,后缀名修改reg

```regexp
Windows Registry Editor Version 5.00

[HKEY_CLASSES_ROOT\.md]
@="typora.md"
"icon"="D:\\Software\\Typora\\typora.exe"

[HKEY_CLASSES_ROOT\.md\OpenWithProgids]
"Typora.md"=""
"VSCode.md"=""

[HKEY_CLASSES_ROOT\.md\ShellNew]
"NullFile"=""

```

## 虚拟机静态网络

```properties
TYPE=Ethernet
PROXY_METHOD=none
BROWSER_ONLY=no
BOOTPROTO=static
DEFROUTE=yes
IPV4_FAILURE_FATAL=no
IPV6INIT=yes
IPV6_AUTOCONF=yes
IPV6_DEFROUTE=yes
IPV6_FAILURE_FATAL=no
IPV6_ADDR_GEN_MODE=stable-privacy
NAME=ens33
UUID=ae9e7215-5fde-4acf-b543-617a67173252
DEVICE=ens33
ONBOOT=yes
IPADDR=192.168.244.100
GATEWAY=192.168.244.2
NETMASK=255.255.255.0
DNS1=192.168.244.2
```




















































































.png
.png



