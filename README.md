#笔记

```txt
我也不知道你们都怎么学习的，但是我学习的时候喜欢记录一下学习的笔记，我用的软件是typora，希望呢？我的笔记可以帮助你们，当然如果有人说我的笔记抄袭严重，那么我也不知道说什么，我也是慢慢的从网上查资料自己学习的，如果原作者不愿意请联系一下我，抱歉！
```

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



# 阿里巴巴矢量图标库批量下载




https://www.iconfont.cn/

```javascript
var span = document.querySelectorAll('.icon-cover');
for (var i = 0, len = span.length; i < len; i++) {
    if (0<=i && i < 20){
        console.log(span[i].querySelector('span').click());
    } 
}
```


