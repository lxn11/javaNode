# 目录

[TOC]



# 一、File类的使用



## 1、简介



### 1.1、描述



File**这个对象是来操作文件夹和文本文本文件的**



### 1.2、File创建方式



<font color='blue'>Java文件类以抽象的方式代表文件名和目录路径名。该类主要用于文件和目录的创建、文件的查找和文件的删除等。</font>

<font color='gree'>File对象代表磁盘中实际存在的文件和目录。通过以下构造方法创建一个File对象。</font>



| File(String pathname)             | 通过路径名创建一个新 File 实例                               |
| --------------------------------- | ------------------------------------------------------------ |
| File(File parent, String child)   | 根据父目录路径File实例和子目录或文件路径创建一个新 File 实例 |
| File(String parent, String child) | 根据父目录路径和子目录或文件路径创建一个新 File 实例         |

```java
public class Test {
 
	public static void main(String[] args) {
		//File(String pathname)
		File file = new File("D:\\a");
		//File(File parent, String child)
		file = new File(file,"b");
		//File(String parent, String child) 
		file = new File("D:\\a","b");
	}
}
```



### 1.3、注意



File类代表一个特定的**文件**或者**目录**；具体代表文件还是目录是要根据调用构造方法时的赋值来判断；如果所赋之值为路径，则 file对象 代表路径即目录；**如果所赋之值为 完整路径 + 文件名**；则 file 对象 代表文件；如下：

```java
public class Test {
 
	public static void main(String[] args) {
		File file = new File("D:\\adm");
		System.out.println(file);
		file = new File("D:\\adm\\新建日记本文档.jnt");
		System.out.println(file);
	}
}
```



<img src="images\image-20200920004145432.png" alt="image-20200920004145432" style="zoom:80%;" />



## 2、判断是否为文件/文件夹



### 2.1、简介



| 名字        | 作用           | 返回值           |
| ----------- | -------------- | ---------------- |
| isDirectory | 是否为文件夹   | true是/false不是 |
| isFile      | 判断是否为文件 | true是/false不是 |



### 2.2、用法



```java
public class Test {
	public static void main(String[] args) {
		File file = new File("D:\\adm\\新建日记本文档.jnt");
		//判断是否为文件夹
		if(file.isDirectory()) {
			System.out.println("YES");
		}else {
			System.out.println("NO");
		}
		//判断是否为文件
		if(file.isFile()) {
			System.out.println("YES");
		}else {
			System.out.println("NO");
		}		
	}
}
/*
输出结果：
NO
YES
*/
```



## 3、创建文件



### 3.1、简介



| name          | 作用           | 返回值             |
| ------------- | -------------- | ------------------ |
| mkdir         | 创建一级文件夹 | true创建成功/false |
| mkdirs        | 创建多级文件夹 | true创建成功/false |
| createNewFile | 创建文件       | true创建成功/false |



### 3.2、用法



```java
import java.io.File;
import java.io.IOException;
 
public class Test {
 
	public static void main(String[] args) {
		//创建文件夹 ：一级文件夹（根目录）
		File file = new File("D:\\adm");
		if(file .mkdir()) {
			System.out.println("创建成功");
		}else {
			System.out.println("创建失败");
		}
		//创建多级文件夹
		file = new File("D:\\a\\b\\c\\d");
		if(file.mkdirs()) {
			System.out.println("创建成功");
		}else {
			System.out.println("创建失败");
		}
		//创建文件
		file = new File("D:\\说明.txt");
		try {
			if(file.createNewFile()){
				System.out.println("创建成功");
			}else {
				System.out.println("创建失败");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
 /*
结果如下：
创建成功
创建成功
创建成功*/
```



## 4、删除/重命名/是否存在



### 4.1、简介



| name     | 作用             | 返回值                     |
| -------- | ---------------- | -------------------------- |
| delete   | 删除文件         | true删除成功/false删除失败 |
| renameTo | 修改文件名       | true成功/false失败         |
| exists   | 判断文件是否存在 | true存在/false不存在       |



### 4.2、用法



```java
import java.io.File;
 
public class Test {
 
	public static void main(String[] args) {
		//删除
		File file = new File("D:\\a\\b\\c\\d");
		if(file.delete()) {
			System.out.println("删除成功");
		}else {
			System.out.println("删除失败");
		}
		//重命名
		file = new File("D:\\说明.txt");
		if(file.renameTo(new File("D:\\shuoming.txt"))) {
			System.out.println("修改成功");
		}else {
			System.out.println("修改失败");
		}
		//判断是否存在
		file = new File("D:\\shuoming.txt");
		if(file.exists()) {
			System.out.println("Yes");
		}else {
			System.out.println("No");
		}
	}
}
 /*
结果如下：
删除成功
修改成功
Yes*/
```



## 5、获取路径



### 5.1、简介



<font color='red'>**前两个的返回值都是String类型，最后一个是File对象**</font>

| name      | 作用                         |
| --------- | ---------------------------- |
| getParent | 获取父级路径                 |
| getPath   | 获取当前路径                 |
| getPare   | 获取父级路径所对应的File对象 |



### 5.2、用法



```java
import java.io.File;
 
public class Test {
 
	public static void main(String[] args) {
		//获取父级路径
		File file = new File("D:\\adm\\新建日记本文档.jnt");
		System.out.println(file.getParent());
		//获取当前路径
		System.out.println(file.getPath());
		//获取父级路径对应的File对象
		System.out.println(file.getParentFile());
	}
}
 /*
结果如下：
D:\adm
D:\adm\新建日记本文档.jnt
D:\adm*/
```



## 6、获取文件夹



### 6.1、简介



| name      | 作用                                                   | 返回值   |
| --------- | ------------------------------------------------------ | -------- |
| list      | 获取文件夹所有子文件（夹）所组成的数组                 | String   |
| listFiles | 获取文件夹所有子文件（夹）对应的File对象所组成的数组； | File对象 |



### 6.2、用法



```java
import java.io.File;
 
public class Test {
 
	public static void main(String[] args) {
		//获取文件夹所有子文件夹的名字
		File file = new File("D:\\a");
		String [] names = file.list();
		for (String name : names) {
			System.out.println(name);
		}
		//获取文件夹所有子文件对应的File对象构成的数组
		File [] files = file.listFiles();
		for (File f : files) {
			System.out.println(f.getPath());
		}
	}
}
```

<img src="images\image-20200920005950158.png" style="zoom:80%;" />

## 7、路径拼接



### 7.1、简介



Linux系统只能识别 “/” 的文件目录，而不能识别 “\\” 的文件目录；因此需要采用拼接技术来实现代码在各个系统之间的兼容性；



```java
import java.io.File;
 
public class Test {
 
	public static void main(String[] args) {
		//separator 拼接
		File file = new File("D:\\adm\\新建日记本文档.jnt");
		file = new File("D:"+file.separator+"adm"+file.separator+"新建日记本文档.jnt");
		System.out.println(file.separator);
		System.out.println(file);
	}
}
 /*
输出结果如下：
\
D:\adm\新建日记本文档.jnt*/
```



# 二、IO流一



## 1、先看一张图片



<img src="images\image-20200920010441011.png" alt="image-20200920010441011" style="zoom:80%;" />

## 2、简介



### 2.1：什么是流



当不同的介质之间有数据交互的时候，JAVA就使用流来实现。数据源可以是文件，还可以是数据库，网络甚至是其他的程序

比如读取文件的数据到程序中，站在程序的角度来看，就叫做输入流

输入流： InputStream

输出流：OutputStream



![image-20200920122919190](images\image-20200920122919190.png)

### 2.2：什么是io流



流是一种抽象概念，它代表了数据的无结构化传递。按照流的方式进行输入输出，数据被当成无结构的字节序或字符序列。从流中取得数据的操作称为提取操作，而向流中添加数据的操作称为插入**[操作]**。用来进行输入输出操作的流就称为IO流。换句话说，IO流就是以流的方式进行输入输出

-  **i：input（输入）**

-  **o：output（输出)**



### 2.3：常用流概述



1. 按照流的方向分：**输入流**（InputStream）和**输出流**（OutputStream）；输入和输出是站在内存的输入和输出角度来讲的。
2. 按照实现功能分：**节点流**（可以从或向一个特定的地方（节点）读写数据。例如FileReader）和**处理流**（是对一个已存在的流的连接和封装，通过所封装的流的功能调用实现数据读写，如 BufferedReader。处理流的构造方法总是要带一个其他的流对象做参数。一个流对象经过其他流的多次包装，称为**流的链接**）；
3. 按照数据处理单元分：字节流和字符流。



| 分类       | 字节输入流                | 字节输出流            | 字符输入流           | 字符输出流        |
| ---------- | ------------------------- | --------------------- | -------------------- | ----------------- |
| 抽象基类   | InputStream               | OutputStream          | Reader               | Write             |
| 访问文件   | FileInputStream           | FileOutputStream      | FileReader           | FileWrite         |
| 访问数组   | ByteArrayInputStream      | ByteArrayOutputStream | ByteArrayReader      | ByteArrayWrite    |
| 访问管道   | PipedInputStream          | PipedOutputStream     | PipedReader          | PipedWrite        |
| 访问字符串 |                           |                       | StringReader         | StringReader      |
| 缓冲流     | BufferedInputStream       | BufferedOutputStream  | BufferedReader       | BuferedWrite      |
| 转换流     |                           |                       | InputStreamReader    | OutputStreamWrite |
| 对象流     | ObjectInputStream         | ObjectOutputStream    |                      |                   |
|            | FilterInputStream         | FilterOutputSteam     | FilterReader         | FilterWrite       |
| 打印流     |                           | PrintStream           |                      | PrintWrite        |
| 推回输入流 | PushbackInputStreamStream |                       | PushbackOutputStream |                   |
| 特殊流     | DataInputStream           | DataOutputStream      |                      |                   |



### 2.4：字节流与字符流区别？



1、**处理数据的方式不同**：字节流读取的时候，读到一个字节就返回一个字节；字符流使用了字节流读到一个或多个字节（中文对应的字节数是两个，在 UTF-8 码表中是 3 个字节）时，先去查指定的编码表，再将查到的字符返回。

2**、处理单元和操作对象不同**：字符流处理的单元为 2 个字节的 Unicode 字符，分别操作字符、字符数组或字符串，而字节流处理单元为 1 个字节，操作字节和字节数组。

3、**字节流可以处理所有类型数据**，如：图片， MP3， AVI 视频文件，而字符流只能处理字符数据。只要是处理纯文本数据，就要优先考虑使用字符流，除此之外都用字节流。





**注意：**

> <font color='red'>字节流的抽象基类是**InputStream**（输入流-读）和**OutputStream**（输出流-写）；</font>
>
> <font color='red'>字符流的抽象基类是**Reader**（输入流-读）和**Writer**（输出流，写）。</font>
>
> **由抽象基类派生出来的子类名称都是以其父类名作为子类名的后缀**。这句话很重要，由此可以很简单的从众多流中判断出是属于字节流还是字符流。
>
> **抽象基类都不能直接实例化，我们只能对其子类进行实例化。**





## 3、四大抽象基类介绍



### 3.1：字节输入流InputStram

#### 1.全部方法



<img src="images\image-20200920115726089.png" alt="image-20200920115726089" style="zoom:80%;" />



#### 2.read



| read() 从输入流中读取数据的下一个字节。返回 0 到 255 范围内的 int 字节值 如果因为已经到达流末尾而没有可用的字节，则返回值 -1 |
| ------------------------------------------------------------ |
| 方法将会一直阻塞,直到数据可用,检测到流的末尾或者抛出异常     |
| 无参数的read() 是抽象方法,由实现类提供实现 三个read方法实际上根本方法都是read()方法其他两个方法为拓展功能,逻辑便捷方法 |
| 无参数的read()返回的数据为读取到的字节值 而有参数的则是读取到字节数组中,所以返回值为读取到的个数 |
| read方法关键点 要么就是直接返回读取的字节 要么就是将读取到的字节放入字节数组中,字节数组是你传递进去的 |



#### 3.write



| write(int b)将指定的字节写入此输出流 write 的常规协定是：向输出流写入一个字节, 要写入的字节是参数 b 的八个低位  b 的 24 个高位将被忽略 说白了就是写入的是byte虽然参数是int |
| ------------------------------------------------------------ |
| write(byte[] b)将 b.length 个字节从指定的 byte 数组写入此输出流write(b) 的常规协定是：应该与调用 write(b, 0, b.length) 的效果完全相同 |
| write(byte[] b,int off,int len)将指定 byte 数组中从偏移量 off 开始的 len 个字节写入此输出流write(b, off, len) 的常规协定是：将数组 b 中的某些字节按顺序写入输出流；元素 b[off] 是此操作写入的第一个字节，b[off+len-1] 是此操作写入的最后一个字节 |
| 类似read的调用形式 直接写入指定字节的write(int b) 方法是根本 其他的是拓展功能 |



#### 4.read/write方法区别



> read() 与write(int b) 是根本的读取一个字节或者写入一个字节的方法 其余形式是针对传入字节数组作为参数,以及指定字节数组的偏移量时的一些拓展功能 一旦传递了字节数组作为参数 read将会读取数据到字节数组 write将会将字节数组的数据写入



#### **5.close**



| 都需要关闭流,所以都有close方法 都是关闭流并释放与此流有关的系统资源 都可能抛出IOException |
| ------------------------------------------------------------ |
| 在InputStream和OutputStream中,两个close方法都是空方法        |



#### **6.flush**



| flush的含义为刷新,在写入数据时使用 所以,只有输出流拥有flush方法 |
| ------------------------------------------------------------ |
| 之所以需要刷新,是因为有的输出流的写方法实现,可能已经缓冲了以前写入的任何字节 那么,这个方法用于提供能够立即将数据写入到磁盘的功能 不过,只是立即请求操作系统进行处理,而不保证这些字节实际已经写入到物理设备,比如磁盘 |



#### 7.available



public int **available()** throws IOException 返回此输入流下一个方法调用可以不受阻塞地从此输入流读取（或跳过）的估计字节数这句话有些绕口,直白的说就是:在方法调用前,可以获取到这个流中可用的字节数目假设说有N个字节可以使用,显然你应该很可能读取到N个字节,或者能够跳过N个字节一次读取或跳过此估计数个字节不会受阻塞

| 注意: 这个数目是一个预估的数量 实际的读取或者跳过的字节数可能小于这个数 |
| ------------------------------------------------------------ |
| InputStream中的这个方法总是返回0  所以这个方法能否使用依赖于子类的实现 |



#### 8.skip



| public long **skip(long n)** throws IOException              |
| ------------------------------------------------------------ |
| 返回的是实际跳过的字节数 在内部创建一个 byte 数组，然后重复将字节读入其中，直到读够 n 个字节或已到达流末尾为止 |



#### 9.rest/mark/markSupported



**reset()、mark(int)、markSupported()** 三个方法是对于同一个功能点的不同方法 ,可以**解决重复读的问题**



> | mark(int)用来在此输入流中做标记,标记当前位置 打一个书签 markSupported()  测试此输入流是否支持 mark 和 reset 方法 reset() 将此流重新定位到最后一次对此输入流调用 mark 方法时的位置  回到书签 |
> | ------------------------------------------------------------ |
> | 看下类中的默认代码可以发现:默认情况下mark什么都不做markSupported直接返回falsereset方法的调用会抛出异常 |
> | ![image-20200920121238392](images\image-20200920121238392.png) |
> | mark的参数用于告知输入流在标记位置失效之前允许读取的字节数   |
>
> **<font color='red'>标记已关闭的流对其无效：</font>**
>
> | 说起来很迷惑,用起来却很简单 比如 xxxStream.mark(50);//表明系统至少应该缓冲50以上个数据,以保证可以回来重新读取 xxxStream.read(); ..... xxxStream.read();  xxxStream.reset();//reset之后,读取到的数据将会和刚才调用mark 方法后read的数据是相同的 xxxStream.read();.....xxxStream.read(); |
> | ------------------------------------------------------------ |
> | 如果方法 markSupported 返回 true，那么输入流总是在调用 mark 之后记录所有读取的字节 并时刻准备在调用方法 reset 时（无论何时），再次提供这些相同的字节 但是，如果在调用 reset 之前可以从流中读取多于 readlimit 的字节，则不需要该流记录任何数据 |



### 3.2：字节输出流OutputStream



#### 1.全部方法



![image-20200920121653712](images\image-20200920121653712.png)





#### 2.read



| read() 从输入流中读取数据的下一个字节。返回 0 到 255 范围内的 int 字节值 如果因为已经到达流末尾而没有可用的字节，则返回值 -1 |
| ------------------------------------------------------------ |
| 方法将会一直阻塞,直到数据可用,检测到流的末尾或者抛出异常     |
| 无参数的read() 是抽象方法,由实现类提供实现 三个read方法实际上根本方法都是read()方法其他两个方法为拓展功能,逻辑便捷方法 |
| 无参数的read()返回的数据为读取到的字节值 而有参数的则是读取到字节数组中,所以返回值为读取到的个数 |
| read方法关键点 要么就是直接返回读取的字节 要么就是将读取到的字节放入字节数组中,字节数组是你传递进去的 |



#### 3.write



| write(int b)将指定的字节写入此输出流 write 的常规协定是：向输出流写入一个字节, 要写入的字节是参数 b 的八个低位  b 的 24 个高位将被忽略 说白了就是写入的是byte虽然参数是int |
| ------------------------------------------------------------ |
| write(byte[] b)将 b.length 个字节从指定的 byte 数组写入此输出流write(b) 的常规协定是：应该与调用 write(b, 0, b.length) 的效果完全相同 |
| write(byte[] b,int off,int len)将指定 byte 数组中从偏移量 off 开始的 len 个字节写入此输出流write(b, off, len) 的常规协定是：将数组 b 中的某些字节按顺序写入输出流；元素 b[off] 是此操作写入的第一个字节，b[off+len-1] 是此操作写入的最后一个字节 |
| 类似read的调用形式 直接写入指定字节的write(int b) 方法是根本 其他的是拓展功能 |



#### 4.read/write方法区别



> read() 与write(int b) 是根本的读取一个字节或者写入一个字节的方法 其余形式是针对传入字节数组作为参数,以及指定字节数组的偏移量时的一些拓展功能 一旦传递了字节数组作为参数 read将会读取数据到字节数组 write将会将字节数组的数据写入



#### **5.close**



| 都需要关闭流,所以都有close方法 都是关闭流并释放与此流有关的系统资源 都可能抛出IOException |
| ------------------------------------------------------------ |
| 在InputStream和OutputStream中,两个close方法都是空方法        |



### 3.3：字符输入流Reader



#### 1.全部方法



![image-20200920122543358](images\image-20200920122543358.png)

#### 2.read



- int read()：每次读取一个字符，并把它转换为 0~65535 的整数，然后返回读取到的字符。读到末尾返回-1。(为了提高 I/O 操作的效率，建议尽量使用下面两种read()方法)
- int read(char[] cbuf)：将读取到的多个字符存储到 cbuf 中，然后返回读取的字符，读到末尾时返回-1。
- int read(char[] cbuf,int off,int len)：将读取到的多个字符存储进字符数组 cbuf，从索引 off 开始，长度为len字符，返回结果为读取的字符数.



#### 3.其他方法



| 方法名               | 描述                                 |
| -------------------- | ------------------------------------ |
| close                | 关闭流并释放与之相关联的任何系统资源 |
| mark(int)            | 标记流中的当前位置                   |
| markSupported()      | 告诉这个流是否支持mar()支持          |
| reda（）             | 读取一个字符                         |
| read(char[])         | 将字符读入数据                       |
| read(char[],int,int) | 将字符读入数据的一部分               |
| read(CharBuffer)     | 尝试讲字符读入指定的字符缓冲区       |
| ready()              | 告诉这个流是否准备号被读取           |
| reset()              | 重置流。                             |
| skip(long)           | 跳过字符                             |



### 3.4：**字符输出流Writer**



#### 1.全部方法



Writer是所有字符输出流的父类，定义了所有字符输出流都具有的共同特征。其内部提供的方法如下：

![image-20200920130620779](images\image-20200920130620779.png)





#### 2.write方法



- void write(int c)：把一个字符写入到文件中。
- void write(char[] cbuf)：把cbuf字符数组写入到文件中。
- void write(char[] cbuf,int off,int len)：把部分字符数组写入到文件中，从 cbuf 数组的 off 索引开始，写入len个字符。
- void write(String str)：把一个字符串写入到文件中。
- void write(String str,int off,int len)：把部分字符串写入到文件中，从 字符串的 off 索引开始，写入len个字符。



#### 3.其他方法



| 方法名字                     | 描述                               |
| ---------------------------- | ---------------------------------- |
| append(char )                | 将指定的字符附加到此作者           |
| append(charsequence)         | 将指定的字符序列附加到词作者       |
| append(charsequence,int,int) | 将指定字符序列的子序列附加到此作者 |
| close                        | 关闭流，注意先刷新流               |
| flush                        | 刷新流                             |
| write(char[])                | 写入一个字符数组                   |
| write(char[],int,int)        | 写入字符数组的一部分。             |
| write(int)                   | 写入一个字符                       |
| write(string)                | 写入一个字符串                     |
| write(string,int,int)        | 写一个字符串的一部分               |



## 4、ASCII码 概念



所有的数据存放在计算机中都是以数字的形式存放的。 所以**字母就需要转换为数字才能够存放**。比如A就对应的数字65，a对应的数字97. 不同的字母和符号对应不同的数字，就是一张码表。ASCII是这样的一种码表。 只**包含简单的英文字母**，符号，数字等等。 **不包含中文，德文，俄语等复杂**的。示例中列出了可见的ASCII码以及对应的十进制和十六进制数字，不可见的暂未列出：



| 字符 | 十进制数字 | 十六进制数字 |
| ---- | ---------- | ------------ |
| !    | 33         | 21           |
| "    | 34         | 22           |
| #    | 35         | 23           |
| $    | 36         | 24           |
| %    | 37         | 25           |
| &    | 38         | 26           |
| '    | 39         | 27           |
| (    | 40         | 28           |
| )    | 41         | 29           |
| *    | 42         | 2A           |
| +    | 43         | 2B           |
| ,    | 44         | 2C           |
| -    | 45         | 2D           |
| .    | 46         | 2E           |
| /    | 47         | 2F           |
| 0    | 48         | 30           |
| 1    | 49         | 31           |
| 2    | 50         | 32           |
| 3    | 51         | 33           |
| 4    | 52         | 34           |
| 5    | 53         | 35           |
| 6    | 54         | 36           |
| 7    | 55         | 37           |
| 8    | 56         | 38           |
| 9    | 57         | 39           |
| :    | 58         | 3A           |
| ;    | 59         | 3B           |
| <    | 60         | 3C           |
| =    | 61         | 3D           |
| >    | 62         | 3E           |
| @    | 64         | 40           |
| A    | 65         | 41           |
| B    | 66         | 42           |
| C    | 67         | 43           |
| D    | 68         | 44           |
| E    | 69         | 45           |
| F    | 70         | 46           |
| G    | 71         | 47           |
| H    | 72         | 48           |
| I    | 73         | 49           |
| J    | 74         | 4A           |
| K    | 75         | 4B           |
| L    | 76         | 4C           |
| M    | 77         | 4D           |
| N    | 78         | 4E           |
| O    | 79         | 4F           |
| P    | 80         | 50           |
| Q    | 81         | 51           |
| R    | 82         | 52           |
| S    | 83         | 53           |
| T    | 84         | 54           |
| U    | 85         | 55           |
| V    | 86         | 56           |
| W    | 87         | 57           |
| X    | 88         | 58           |
| Y    | 89         | 59           |
| Z    | 90         | 5A           |
| [    | 91         | 5B           |
| \    | 92         | 5C           |
| ]    | 93         | 5D           |
| ^    | 94         | 5E           |
| _    | 95         | 5F           |
| `    | 96         | 60           |
| a    | 97         | 61           |
| b    | 98         | 62           |
| c    | 99         | 63           |
| d    | 100        | 64           |
| e    | 101        | 65           |
| f    | 102        | 66           |
| g    | 103        | 67           |
| h    | 104        | 68           |
| i    | 105        | 69           |
| j    | 106        | 6A           |
| k    | 107        | 6B           |
| l    | 108        | 6C           |
| m    | 109        | 6D           |
| n    | 110        | 6E           |
| o    | 111        | 6F           |
| p    | 112        | 70           |
| q    | 113        | 71           |
| r    | 114        | 72           |
| s    | 115        | 73           |
| t    | 116        | 74           |
| u    | 117        | 75           |
| v    | 118        | 76           |
| w    | 119        | 77           |
| x    | 120        | 78           |
| y    | 121        | 79           |
| z    | 122        | 7A           |
| {    | 123        | 7B           |
| \|   | 124        | 7C           |
| }    | 125        | 7D           |
| ~    | 126        | 7E           |

## 5、字节流FileInputStream读取文件



### 5.1：简介



**InputStream是字节输入流，同时也是抽象类，只提供方法声明，不提供方法的具体实现**。FileInputStream 是InputStream子类，以FileInputStream 为例进行文件读取



### 5.2：代码



```java
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
  
public class TestStream {
  
    public static void main(String[] args) {
        try {
            //准备文件lol.txt其中的内容是AB，对应的ASCII分别是65 66
            File f =new File("d:/lol.txt");
            //创建基于文件的输入流
            FileInputStream fis =new FileInputStream(f);
            //创建字节数组，其长度就是文件的长度
            byte[] all =new byte[(int) f.length()];
            //以字节流的形式读取文件所有内容
            fis.read(all);
            for (byte b : all) {
                //打印出来是65 66
                System.out.println(b);
            }
             
            //每次使用完流，都应该进行关闭
            fis.close();
              
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
```



## 6、字节流FileOutputStram写入文件



### 6.1：简介



**OutputStream是字节输出流，同时也是抽象类，只提供方法声明，不提供方法的具体实现**。FileOutputStream 是OutputStream子类，以FileOutputStream 为例向文件写出数据

<font color='red'>注: 如果文件d:/lol2.txt不存在，写出操作会自动创建该文件。但是如果是文件 d:/xyz/lol2.txt，而目录xyz又不存在，会抛出异常</font>



### 6.2：代码



```java
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
 
public class TestStream {
 
    public static void main(String[] args) {
        try {
            // 准备文件lol2.txt其中的内容是空的
            File f = new File("d:/lol2.txt");
            // 准备长度是2的字节数组，用88,89初始化，其对应的字符分别是X,Y
            byte data[] = { 88, 89 };
 
            // 创建基于文件的输出流
            FileOutputStream fos = new FileOutputStream(f);
            // 把数据写入到输出流
            fos.write(data);
            // 关闭输出流
            fos.close();
             
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
 
    }
}
```



#### 6.3：文件追加



```java
  // 2．应该使用OutputStream和其子类进行对象的实例化，此时目录存在，文件还不存在

  OutputStream output = **new** FileOutputStream(file,**true**) ;
```



## 7、关闭流



### 7.1:简介



所有的流，无论是输入流还是输出流，使用完毕之后，都应该关闭。 如果不关闭，会产生对资源占用的浪费。 当量比较大的时候，会影响到业务的正常开展。



### 7.2:try中关闭



在try的作用域里关闭文件输入流，在前面的示例中都是使用这种方式，这样做有一个弊端；如果文件不存在，或者读取的时候出现问题而抛出异常，那么就不会执行这一行关闭流的代码，存在巨大的资源占用隐患。 **不推荐**使用



```java
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
 
public class TestStream {
 
    public static void main(String[] args) {
        try {
            File f = new File("d:/lol.txt");
            FileInputStream fis = new FileInputStream(f);
            byte[] all = new byte[(int) f.length()];
            fis.read(all);
            for (byte b : all) {
                System.out.println(b);
            }
            // 在try 里关闭流
            fis.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
 
    }
}
```



### 7.3:在finally中关闭



**这是标准的关闭流的方式**

1. 首先把流的引用声明在try的外面，如果声明在try里面，其作用域无法抵达finally.

2. 在finally关闭之前，要先判断该引用是否为空

3. 关闭的时候，需要再一次进行try catch处理



<font color='blue'>这是标准的严谨的关闭流的方式，但是看上去很繁琐，所以写不重要的或者测试代码的时候，都会采用上面的**有隐患**try的方式，因为不麻烦~</font>



```java
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class TestStream {

    public static void main(String[] args) {
        File f = new File("d:/lol.txt");
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(f);
            byte[] all = new byte[(int) f.length()];
            fis.read(all);
            for (byte b : all) {
                System.out.println(b);
            }
 
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 在finally 里关闭流
            if (null != fis)
                try {
 
                    fis.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
        }
    }
}
```



### 7.4:使用try()的方式



把流定义在try()里,try,catch或者finally结束的时候，会自动关闭。

这种编写代码的方式叫做 **try-with-resources**， 这是从**JDK7开始支持的技术**

所有的流，都实现了一个接口叫做 **AutoCloseable**，任何类实现了这个接口，都可以在try()中进行实例化。 并且在try, catch, finally结束的时候自动关闭，回收相关资源。



```java
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
  
public class TestStream {
  
    public static void main(String[] args) {
        File f = new File("d:/lol.txt");
  
        //把流定义在try()里,try,catch或者finally结束的时候，会自动关闭
        try (FileInputStream fis = new FileInputStream(f)) {
            byte[] all = new byte[(int) f.length()];
            fis.read(all);
            for (byte b : all) {
                System.out.println(b);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
  
    }
}
```



## 8、字符流FileReader 读取文件



### 8.1：简介



FileReader 是Reader子类，以FileReader 为例进行文件读取



#### 8.2：代码



```java
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
 
public class TestStream {
    public static void main(String[] args) {
        // 准备文件lol.txt其中的内容是AB
        File f = new File("d:/lol.txt");
        // 创建基于文件的Reader
        try (FileReader fr = new FileReader(f)) {
            // 创建字符数组，其长度就是文件的长度
            char[] all = new char[(int) f.length()];
            // 以字符流的形式读取文件所有内容
            fr.read(all);
            for (char b : all) {
                // 打印出来是A B
                System.out.println(b);
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
```



## 9、字符流FileWrite写入文件



#### 9.1：简介



FileWriter 是Writer的子类，以FileWriter 为例把字符串写入到文件



#### 9.2：代码



```java
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
  
public class TestStream {
  
    public static void main(String[] args) {
        // 准备文件lol2.txt
        File f = new File("d:/lol2.txt");
        // 创建基于文件的Writer
        try (FileWriter fr = new FileWriter(f)) {
            // 以字符流的形式把数据写入到文件中
            String data="abcdefg1234567890";
            char[] cs = data.toCharArray();
            fr.write(cs);
  
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
  
    }
}
```



## 10、while循环实现输入流



```java
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

public class TestDemo {
    public static void main(String[] args) throws Exception { 	// 直接抛出
        File file = new File("d:" + File.separator + "demo" + File.separator
              + "mldn.txt");							// 1．定义要输出文件的路径
        if (file.exists()) {								// 需要判断文件是否存在后才可以进行读取
           // 2．使用InputStream进行读取
           InputStream input = new FileInputStream(file) ;
            // 准备出一个1024的数组
           byte data [] = new byte [1024] ;		
            // 表示字节数组的操作脚标 
           int foot = 0 ;	
            // 表示接收每次读取的字节数据
           int temp = 0 ;								
           // 第一部分：(temp = input.read())，表示将read()方法读取的字节内容给temp变量
           // 第二部分：(temp = input.read()) != -1，判断读取的temp内容是否是-1
            // 3．读取数据
           while((temp = input.read()) != -1) {				
               data[foot ++] = (byte) temp ;				
           }
           input.close();								// 4．关闭输入流
           System.out.println("[" + new String(data,0,foot) + "]");
        }
    }
}
```



# 三、IO流二



## 1、do…while实现数据读取



```java
 byte data [] = new byte [1024] ;		// 准备出一个1024的数组
    int foot = 0 ;					// 表示字节数组的操作脚标 
    int temp = 0 ;					// 表示接收每次读取的字节数据
    do {
        temp = input.read() ;			// 读取一个字节
        if (temp != -1) {				// 现在是真实的内容
           data[foot ++] = (byte) temp ;	// 保存读取的字节到数组中
        }
    } while (temp != -1) ;		// 如果现在读取的temp的字节数据不是-1，表示还有内容
```



## 2、强制清楚字符缓冲区



```java
import java.io.File;
import java.io.FileWriter;
import java.io.Writer;
public class TestDemo {
    public static void main(String[] args) throws Exception { // 此处直接抛出
        File file = new File("d:" + File.separator + "demo" + File.separator
              + "mldn.txt");						// 1．定义要输出文件的路径
        if (!file.getParentFile().exists()) {				// 判断目录是否存在
           file.getParentFile().mkdirs();				// 创建文件目录
        } 
        Writer out = new FileWriter(file);				// 2．实例化了Writer类的对象
        String str = "更多课程请访问：www.yootk.com";	// 定义输出内容
        out.write(str);							// 3．输出字符串数据
        out.flush();								// 强制刷新缓冲区
        out.close();						//关闭流
    }
}
```



## 3、中文问题



### 3.1:编码概念



计算机存放数据只能存放数字，所有的字符都会被转换为不同的数字。就像一个棋盘一样，不同的字，处于不同的位置，而不同的位置，有不同的数字编号。有的棋盘很小，只能放数字和英文有的大一点，还能放中文有的“足够”大，能够放下世界人民所使用的所有文字和符号

如图所示，英文字符 **A** 能够放在所有的棋盘里，而且位置都差不多中文字符, 中文字符 **中** 能够放在后两种棋盘里，并且位置不一样，而且在小的那个棋盘里，就放不下中文



<img src="images\image-20200920135129782.png" style="zoom:80%;" />



### 3.2:常见编码



**工作后经常接触的编码方式有如下几种：**

- **ISO-8859-1 ASCII** 数字和西欧字母
- **GBK GB2312 BIG5** 中文
- **UNICODE** (统一码，万国码)

**其中**

- ISO-8859-1 包含 ASCII
- GB2312 是简体中文，BIG5是繁体中文，GBK同时包含简体和繁体以及日文。
- UNICODE 包括了所有的文字，无论中文，英文，藏文，法文，世界所有的文字都包含其中



### 3.3:UNICODE和UTF



根据前面的学习，我们了解到不同的编码方式对应不同的**棋盘**，而UNICODE因为要存放所有的数据，那么它的棋盘是最大的。
不仅如此，棋盘里每个数字都是很长的(4个字节)，因为不仅要表示字母，还要表示汉字等。

如果完全按照UNICODE的方式来存储数据，就会有很大的浪费。比如在ISO-8859-1中，**a** 字符对应的数字是0x61
而UNICODE中对应的数字是 0x00000061，倘若一篇文章大部分都是英文字母，那么按照UNICODE的方式进行数据保存就会消耗很多空间

在这种情况下，就出现了UNICODE的各种**减肥**子编码, 比如UTF-8对数字和字母就使用一个字节，而对汉字就使用3个字节，从而达到了**减肥还能保证健康**的效果

UTF-8，UTF-16和UTF-32 针对不同类型的数据有不同的**减肥效果**，一般说来UTF-8是比较常用的方式

UTF-8，UTF-16和UTF-32 彼此的区别在此不作赘述，有兴趣的可以参考 http://baike.baidu.com/link?url=ty4mEX5hSfK2xAyPO8N2zgxTibBE59CShSb5yFxbVkBun_QVz65llOPEXOepgPeqe3AQDLt6LLjTayn6tioS4_#4



<img src="images\image-20200920135423314.png" alt="image-20200920135423314" style="zoom:80%;" />



### 3.4:Java采用的是Unicode



写在.java源代码中的汉字，在执行之后，都会变成JVM中的字符。而这些中文字符采用的编码方式，都是使用UNICODE. "中"字对应的UNICODE是**4E2D**,所以在内存中，实际保存的数据就是十六进制的0x4E2D, 也就是十进制的20013。



```java
public class TestStream {
    public static void main(String[] args) {
        String str = "中";
    }
}
```



### 3.5:一个汉字使用不同编码方式的表现



以字符 **中** 为例，查看其在不同编码方式下的值是多少也即在不同的**棋盘上的位置**



```java
import java.io.UnsupportedEncodingException;
 
public class TestStream {
 
    public static void main(String[] args) {
        String str = "中";
        showCode(str);
    }
 
    private static void showCode(String str) {
        String[] encodes = { "BIG5", "GBK", "GB2312", "UTF-8", "UTF-16", "UTF-32" };
        for (String encode : encodes) {
            showCode(str, encode);
        }
 
    }
 
    private static void showCode(String str, String encode) {
        try {
            System.out.printf("字符: \"%s\" 的在编码方式%s下的十六进制值是%n", str, encode);
            byte[] bs = str.getBytes(encode);
 
            for (byte b : bs) {
                int i = b&0xff;
                System.out.print(Integer.toHexString(i) + "\t");
            }
            System.out.println();
            System.out.println();
        } catch (UnsupportedEncodingException e) {
            System.out.printf("UnsupportedEncodingException: %s编码方式无法解析字符%s\n", encode, str);
        }
    }
}
```



> **输出结构**
>
> <img src="images\image-20200920135648355.png" alt="image-20200920135648355" style="zoom:67%;" />



### 3.6:FileInputStream 字节流读取中文



为了能够正确的读取中文内容

1. 必须了解文本是以哪种编码方式保存字符的
2. 使用字节流读取了文本后，再使用对应的**编码方式去识别这些数字**，得到正确的字符

如本例，一个文件中的内容是字符**中**，编码方式是GBK，那么读出来的数据一定是D6D0。再使用GBK编码方式识别D6D0，就能正确的得到字符**中**

**注：** 在GBK的棋盘上找到的**中**字后，JVM会自动找到**中**在UNICODE这个棋盘上对应的数字，并且以**<font color='red'>[UNICODE上的数字保存在内存中]</font>**



```java
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
   
public class TestStream {
   
    public static void main(String[] args) {
        File f = new File("E:\\project\\j2se\\src\\test.txt");
        try (FileInputStream fis = new FileInputStream(f);) {
            byte[] all = new byte[(int) f.length()];
            fis.read(all);
   
            //文件中读出来的数据是
            System.out.println("文件中读出来的数据是：");
            for (byte b : all)
            {
                int i = b&0x000000ff;  //只取16进制的后两位
                System.out.println(Integer.toHexString(i));
            }
            System.out.println("把这个数字，放在GBK的棋盘上去：");
            String str = new String(all,"GBK");
            System.out.println(str);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
   
    }
}
```



### 3.7:FileReader字符流读取中文



FileReader得到的是字符，所以一定是已经把字节**根据某种编码识别成了字符**了。

而FileReader使用的编码方式是Charset.defaultCharset()的返回值，如果是中文的操作系统，就是GB。

FileReader是不能手动设置编码方式的，为了使用其他的编码方式，只能使用InputStreamReader来代替，像这样：

```java
new InputStreamReader(new FileInputStream(f),Charset.forName("UTF-8")); 
```



在本例中，用记事本另存为UTF-8格式，然后用UTF-8就能识别对应的中文了。

**解释：** 为什么中字前面有一个?如果是使用记事本另存为UTF-8的格式，那么在第一个字节有一个**标示符**，叫做BOM用来标志这个文件是用UTF-8来编码的。



```java
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
 
public class TestStream {
 
    public static void main(String[] args) throws UnsupportedEncodingException, FileNotFoundException {
        File f = new File("E:\\project\\j2se\\src\\test.txt");
        System.out.println("默认编码方式:"+Charset.defaultCharset());
        //FileReader得到的是字符，所以一定是已经把字节根据某种编码识别成了字符了
        //而FileReader使用的编码方式是Charset.defaultCharset()的返回值，如果是中文的操作系统，就是GBK
        try (FileReader fr = new FileReader(f)) {
            char[] cs = new char[(int) f.length()];
            fr.read(cs);
            System.out.printf("FileReader会使用默认的编码方式%s,识别出来的字符是：%n",Charset.defaultCharset());
            System.out.println(new String(cs));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        //FileReader是不能手动设置编码方式的，为了使用其他的编码方式，只能使用InputStreamReader来代替
        //并且使用new InputStreamReader(new FileInputStream(f),Charset.forName("UTF-8")); 这样的形式
        try (InputStreamReader isr = new InputStreamReader(new FileInputStream(f),Charset.forName("UTF-8"))) {
            char[] cs = new char[(int) f.length()];
            isr.read(cs);
            System.out.printf("InputStreamReader 指定编码方式UTF-8,识别出来的字符是：%n");
            System.out.println(new String(cs));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
         
    }
}
```



## 4、缓冲流



### 4.1:简介



> - 以介质是硬盘为例，**字节流和字符流的弊端**：在每一次读写的时候，都会访问硬盘。 如果读写的频率比较高的时候，其性能表现不佳。
>
> - 为了解决以上弊端，采用缓存流。
>
> - 缓存流在读取的时候，**会一次性读较多的数据到缓存中**，以后每一次的读取，都是在缓存中访问，直到缓存中的数据读取完毕，再到硬盘中读取。
>
> - 就好比吃饭，**不用缓存就是每吃一口都到锅里去铲**。**用缓存就是先把饭盛到碗里**，碗里的吃完了，再到锅里去铲
>
> - 缓存流在写入数据的时候，会先把数据写入到缓存区，直到缓存区**达到一定的量**，才把这些数据，**一起写入到硬盘中去**。按照这种操作模式，就不会像字节流，字符流那样**每写一个字节都访问硬盘**，从而减少了IO操作



### 4.2:缓存流BufferedReader读数据



<font color='red'>**缓存字符输入流 BufferedReader 可以一次读取一行数据**</font>



```java
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
  
public class TestStream {
  
    public static void main(String[] args) {
        // 准备文件lol.txt其中的内容是
        // garen kill teemo
        // teemo revive after 1 minutes
        // teemo try to garen, but killed again
        File f = new File("d:/lol.txt");
        // 创建文件字符流
        // 缓存流必须建立在一个存在的流的基础上
        try (
                FileReader fr = new FileReader(f);
                BufferedReader br = new BufferedReader(fr);
            )
        {
            while (true) {
                // 一次读一行
                String line = br.readLine();
                if (null == line)
                    break;
                System.out.println(line);
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
  
    }
}
```



### 4.3:缓冲PrintWriter写数据



<font color='red'>**PrintWriter 缓存字符输出流， 可以一次写出一行数据**</font>****



```java
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
   
public class TestStream {
   
    public static void main(String[] args) {
        // 向文件lol2.txt中写入三行语句
        File f = new File("d:/lol2.txt");
          
        try (
                // 创建文件字符流
                FileWriter fw = new FileWriter(f);
                // 缓存流必须建立在一个存在的流的基础上              
                PrintWriter pw = new PrintWriter(fw);              
        ) {
            pw.println("garen kill teemo");
            pw.println("teemo revive after 1 minutes");
            pw.println("teemo try to garen, but killed again");
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
   
    }
}
```



### 4.4:flush



<font color='red'>有的时候，需要**立即把数据写入到硬盘**，而不是等缓存满了才写出去。 这时候就需要用到flush</font>



```java
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
public class TestStream {
    public static void main(String[] args) {
        //向文件lol2.txt中写入三行语句
        File f =new File("d:/lol2.txt");
        //创建文件字符流
        //缓存流必须建立在一个存在的流的基础上
        try(FileWriter fr = new FileWriter(f);PrintWriter pw = new PrintWriter(fr);) {
            pw.println("garen kill teemo");
            //强制把缓存中的数据写入硬盘，无论缓存是否已满
                pw.flush();           
            pw.println("teemo revive after 1 minutes");
                pw.flush();
            pw.println("teemo try to garen, but killed again");
                pw.flush();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
```



## 5、数据流



DataInputStream 数据输入流

DataOutputStream 数据输出流

使用数据流的writeUTF()和readUTF() 可以进行数据的**格式化顺序读写**
如本例，通过DataOutputStream 向文件顺序写出 布尔值，整数和字符串。 然后再通过DataInputStream 顺序读入这些数据。

**注：** 要用DataInputStream 读取一个文件，这个文件必须是由DataOutputStream 写出的，否则会出现EOFException，因为DataOutputStream 在写出的时候会做一些特殊标记，只有DataInputStream 才能成功的读取。

```java
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
      
public class TestStream {
      
    public static void main(String[] args) {
        write();
        read();
    }
 
    private static void read() {
        File f =new File("d:/lol.txt");
        try (
                FileInputStream fis  = new FileInputStream(f);
                DataInputStream dis =new DataInputStream(fis);
        ){
            boolean b= dis.readBoolean();
            int i = dis.readInt();
            String str = dis.readUTF();
             
            System.out.println("读取到布尔值:"+b);
            System.out.println("读取到整数:"+i);
            System.out.println("读取到字符串:"+str);
 
        } catch (IOException e) {
            e.printStackTrace();
        }
         
    }
 
    private static void write() {
        File f =new File("d:/lol.txt");
        try (
                FileOutputStream fos  = new FileOutputStream(f);
                DataOutputStream dos =new DataOutputStream(fos);
        ){
            dos.writeBoolean(true);
            dos.writeInt(300);
            dos.writeUTF("123 this is gareen");
        } catch (IOException e) {
            e.printStackTrace();
        }
         
    }
}
```



## 6、序列化和反序列化



### 6.1:简介



(1)Java序列化就是指把Java对象转换为字节序列的过程

​    Java反序列化就是指把字节序列恢复为Java对象的过程。

(2)序列化最重要的作用：在传递和保存对象时.保证对象的完整性和可传递性。对象转换为有序字节流,以便在网络上传输或者保存在本地文件中。

​    反序列化的最重要的作用：根据字节流中保存的对象状态及描述信息，通过反序列化重建对象。

**<font color='red'>总结：核心作用就是对象状态的保存和重建。（整个过程核心点就是字节流中所保存的对象状态及描述信息）</font>**



### 6.2:json/xml的数据传递：



在数据传输(也可称为网络传输)前，先通过序列化工具类将Java对象序列化为json/xml文件。

在数据传输(也可称为网络传输)后，再将json/xml文件反序列化为对应语言的对象



### 6.3:序列化优点



①将对象转为字节流存储到硬盘上，当JVM停机的话，字节流还会在硬盘上默默等待，等待下一次JVM的启动，把序列化的对象，通过反序列化为原来的对象，并且序列化的二进制序列能够减少存储空间（永久性保存对象）。

②序列化成字节流形式的对象可以进行网络传输(二进制形式)，方便了网络传输。

③通过序列化可以在进程间传递对象。



### 6.4:序列化算法需要做的事



① 将对象实例相关的类元数据输出。

② 递归地输出类的超类描述直到不再有超类。

③ 类元数据输出完毕后，从最顶端的超类开始输出对象实例的实际数据值。

④ 从上至下递归输出实例的数据。



### 6.5:Java实现序列化和反序列化的过程



**1、实现序列化的必备要求：**



-  只有实现了Serializable或者Externalizable接口的类的对象才能被序列化为字节序列。（不是则会抛出异常）



**2、JDK中序列化和反序列化的API：**



①java.io.ObjectInputStream：对象输入流。

- 该类的readObject()方法从输入流中读取字节序列，然后将字节序列反序列化为一个对象并返回。

  ②java.io.ObjectOutputStream：对象输出流。

-  该类的writeObject(Object obj)方法将将传入的obj对象进行序列化，把得到的字节序列写入到目标输出流中进行输出。



**3、实现序列化和反序列化的三种实现：**



①若Student类仅仅实现了Serializable接口，则可以按照以下方式进行序列化和反序列化。

- ObjectOutputStream采用默认的序列化方式，对Student对象的非transient的实例变量进行序列化。
- ObjcetInputStream采用默认的反序列化方式，对Student对象的非transient的实例变量进行反序列化。



②若Student类仅仅实现了Serializable接口，并且还定义了readObject(ObjectInputStream in)和writeObject(ObjectOutputSteam out)，则采用以下方式进行序列化与反序列化。

-  ObjectOutputStream调用Student对象的writeObject(ObjectOutputStream out)的方法进行序列化。
-  ObjectInputStream会调用Student对象的readObject(ObjectInputStream in)的方法进行反序列化。



③若Student类实现了Externalnalizable接口，且Student类必须实现readExternal(ObjectInput in)和writeExternal(ObjectOutput out)方法，则按照以下方式进行序列化与反序列化。

- ObjectOutputStream调用Student对象的writeExternal(ObjectOutput out))的方法进行序列化。
- ObjectInputStream会调用Student对象的readExternal(ObjectInput in)的方法进行反序列化。



### 6.6:序列化和反序列化代码示例



```java
public class SerializableTest {
        public static void main(String[] args) throws IOException, ClassNotFoundException {
            //序列化
            FileOutputStream fos = new FileOutputStream("object.out");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            Student student1 = new Student("lihao", "wjwlh", "21");
            oos.writeObject(student1);
            oos.flush();
            oos.close();
            //反序列化
            FileInputStream fis = new FileInputStream("object.out");
            ObjectInputStream ois = new ObjectInputStream(fis);
            Student student2 = (Student) ois.readObject();
            System.out.println(student2.getUserName()+ " " +
                    student2.getPassword() + " " + student2.getYear());
    }
 
}
```



```java
public class Student implements Serializable{                             
                                                                          
    private static final long serialVersionUID = -6060343040263809614L;   
                                                                          
    private String userName;                                              
    private String password;                                              
    private String year;                                                  
                                                                          
    public String getUserName() {                                         
        return userName;                                                  
    }                                                                     
                                                                          
    public String getPassword() {                                         
        return password;                                                  
    }                                                                     
                                                                          
    public void setUserName(String userName) {                            
        this.userName = userName;                                         
    }                                                                     
                                                                          
    public void setPassword(String password) {                            
        this.password = password;                                         
    }                                                                     
                                                                          
    public String getYear() {                                             
        return year;                                                      
    }                                                                     
                                                                          
    public void setYear(String year) {                                    
        this.year = year;                                                 
    }                                                                     
                                                                          
    public Student(String userName, String password, String year) {       
        this.userName = userName;                                         
        this.password = password;                                         
        this.year = year;                                                 
    }                                                                     
}    
```



### 6.7：序列化图示



![image-20200920144034336](images\image-20200920144034336.png)

### 6.8：反序列图示



![image-20200920144100006](images\image-20200920144100006.png)



### 6.9：序列化和反序列化的注意点：



> ①序列化时，只对对象的状态进行保存，而不管对象的方法；
>
> ②当一个父类实现序列化，子类自动实现序列化，不需要显式实现Serializable接口；
>
> ③当一个对象的实例变量引用其他对象，序列化该对象时也把引用对象进行序列化；
>
> ④并非所有的对象都可以序列化，至于为什么不可以，有很多原因了，比如：
>
> - 安全方面的原因，比如一个对象拥有private，public等field，对于一个要传输的对象，比如写到文件，或者进行RMI传输等等，在序列化进行传输的过程中，这个对象的private等域是不受保护的；
> - 资源分配方面的原因，比如socket，thread类，如果可以序列化，进行传输或者保存，也无法对他们进行重新的资源分配，而且，也是没有必要这样实现；
>
> ⑤声明为static和transient类型的成员数据不能被序列化。因为static代表类的状态，transient代表对象的临时数据。
>
> ⑥序列化运行时使用一个称为 serialVersionUID 的版本号与每个可序列化类相关联，该序列号在反序列化过程中用于验证序列化对象的发送者和接收者是否为该对象加载了与序列化兼容的类。为它赋予明确的值。显式地定义serialVersionUID有两种用途：
>
> - 在某些场合，希望类的不同版本对序列化兼容，因此需要确保类的不同版本具有相同的serialVersionUID；
> - 在某些场合，不希望类的不同版本对序列化兼容，因此需要确保类的不同版本具有不同的serialVersionUID。
>
> ⑦Java有很多基础类已经实现了serializable接口，比如String,Vector等。但是也有一些没有实现serializable接口的；
>
> ⑧如果一个对象的成员变量是一个对象，那么这个对象的数据成员也会被保存！这是能用序列化解决深拷贝的重要原因；
>
> 注意：浅拷贝请使用Clone接口的原型模式。



## 7、对象流



### 7.1：序列化对象



```java
import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
public class TestDemo {
    public static void main(String[] args) throws Exception {
        ser();
    }
    public static void ser() throws Exception {
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(
                new File("D:" + File.separator + "book.ser")));
        oos.writeObject(new Book("Java开发实战经典", 79.8)); 	// 序列化对象
        oos.close();
    }
}
```



### 7.2：反序列化对象



```java
import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
public class TestDemo {
    public static void main(String[] args) throws Exception {
        dser();
    }
    public static void dser() throws Exception {
        ObjectInputStream ois = new ObjectInputStream(
                new FileInputStream(new File("D:" + File.separator + "book.ser")));
        Object obj = ois.readObject() ;					// 反序列化对象
        Book book = (Book) obj ;						// 转型
        System.out.println(book);
        ois.close();
    }
}
```



### 7.3：定义不想用列化的对象



```java
import java.io.Serializable;
@SuppressWarnings("serial")
class Book implements Serializable { 			// 此类对象可以被序列化
    private transient String title;			// 此属性无法被序列化
    private double price;
    public Book(String title, double price) {
        this.title = title;
        this.price = price;
    }
    @Override
    public String toString() {
        return "书名：" + this.title + "，价格：" + this.price;
    }
}
```



## 8、内存流



### 8.1：示例1



**实现一个小写字母转大写字母的操作**。

· 本程序不使用String类中提供的toUpperCase()方法，而是利用IO操作，将每一个字节进行大写字母转换；

· 为了方便地实现字母的转大写操作（避免不必要的字符也被转换）可以借助Character包装类的方法。

|- 转小写字母：public static char toLowerCase(char ch)；

|- 转小写字母（利用字母编码转换）：public static int toLowerCase(int codePoint)；

|- 转大写字母：public static char toUpperCase(char ch)；

|- 转大写字母（利用字母编码转换）：public static int toUpperCase(int codePoint)



```java
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
public class TestDemo {
    public static void main(String[] args) throws Exception { // 此处直接抛出
        String str = "www.yootk.com & www.MLDN.cn"; 	// 要求被转换的字符串
        // 本次将通过内存操作流实现转换，先将数据保存在内存流里面，再从里面取出每一个数据
        // 将所有要读取的数据设置到内存输入流中，本次利用向上转型为InputStream类实例化
        InputStream input = new ByteArrayInputStream(str.getBytes());
        // 为了能够将所有的内存流数据取出，可以使用ByteArrayOutputStream
        OutputStream output = new ByteArrayOutputStream();
        int temp = 0; 							// 读取每一个字节数据
        // 经过此次循环后，所有的数据都将保存在内存输出流对象中 
        while ((temp = input.read()) != -1) { 			// 每次读取一个数据
        // 将读取进来的数据转换为大写字母，利用Character.toUpperCase()可以保证只转换字母
            output.write(Character.toUpperCase(temp));	// 字节输出流
        }
        System.out.println(output); 					// 调用toString()方法
        input.close();								// 关闭输入流
        output.close();							// 关闭输出流
    }
}
```



### 8.3：示例2



**实现文件的合并读取**

```java
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
public class TestDemo {
    public static void main(String[] args) throws Exception { 			// 异常简化处理
        File fileA = new File("D:" + File.separator + "infoa.txt");			// 文件路径
        File fileB = new File("D:" + File.separator + "infob.txt");			// 文件路径
        InputStream inputA = new FileInputStream(fileA);				// 字节输入流
        InputStream inputB = new FileInputStream(fileB);				// 字节输入流
        ByteArrayOutputStream output = new ByteArrayOutputStream();	// 内存输出流
        int temp = 0; 										// 每次读取一个字节
        while ((temp = inputA.read()) != -1) {						// 循环读取数据
            output.write(temp);									// 将数据保存到输出流
        }
        while ((temp = inputB.read()) != -1) {						// 循环读取数据
            output.write(temp);									// 将数据保存到输出流
        }
        // 现在所有的内容都保存在了内存输出流里面，所有的内容变为字节数组取出
        byte data[] = output.toByteArray();						// 取出全部数据
        output.close();										// 关闭输出流
        inputA.close();										// 关闭输入流
        inputB.close();										// 关闭输入流
        System.out.println(new String(data));						// 字节转换为字符串输出
    }
}
```



## 9、打印流



### 9.1：定义一个打印工具类



```java
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
class PrintUtil {							// 实现专门的输出操作功能
    private OutputStream output ;			// 输出只能依靠OutputStream
    /**
     * 输出流的输出目标要通过构造方法传递
     * @param output
     */
    public PrintUtil(OutputStream output) {
        this.output = output ;
    }
    public void print(int x) {				// 输出int型数据
        this.print(String.valueOf(x));			// 调用本类字符串的输出方法
    }
    public void print(String x) {
        try {	// 采用OutputStream类中定义的方法，将字符串转变为字节数组后输出
            this.output.write(x.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void print(double x) {				// 输出double型数据
        this.print(String.valueOf(x));
    }
    public void println(int x) {				// 输出数据后换行
        this.println(String.valueOf(x));
    }
    public void println(String x) {			// 输出数据后换行
        this.print(x.concat("\n"));
    }
    public void println(double x) { 
        this.println(String.valueOf(x));
    }
    public void close() {					// 输出流关闭
        try {
            this.output.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
public class TestDemo {
    public static void main(String[] args) throws Exception { // 此处直接抛出
        PrintUtil pu = new PrintUtil(new FileOutputStream(new File("d:"
               + File.separator + "yootk.txt")));
        pu.print("你好啊：");
        pu.println("www.qweqwe.com");
        pu.println(1 + 1);
        pu.println(1.1 + 1.1);
        pu.close();
    }
}
```



### 9.2：使用PrintStream类实现输出



```java
import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintStream;
public class TestDemo {
    public static void main(String[] args) throws Exception { // 此处直接抛出
        // 实例化PrintStream类对象，本次利用FileOutputStream类实例化PrintStream类对象
        PrintStream pu = new PrintStream(new FileOutputStream(new File("d:"
                + File.separator + "yootk.txt")));
        pu.print("weqweqw：");
        pu.println("www.qweqweq.com");
        pu.println(1 + 1);
        pu.println(1.1 + 1.1);
        pu.close();
    }
}
```



### 9.3：格式输出



```java
package com.yootk.demo;
import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintStream;
public class TestDemo {
    public static void main(String[] args) throws Exception { // 此处直接抛出
        String name = "李兴华";
        int age = 19;
        double score = 59.95891023456;
        PrintStream pu = new PrintStream(new FileOutputStream(new File("d:"
                + File.separator + "yootk.txt")));
        pu.printf("姓名：%s，年龄：%d，成绩：%5.2f", name, age, score);
        pu.close();
    }
}
```



### 9.4：格式字符串



```java
public class TestDemo {
    public static void main(String[] args) throws Exception { 
        String name = "李兴华";
        int age = 19;
        double score = 59.95891023456;
        String str = String.format("姓名：%s，年龄：%d，成绩：%5.2f", name, age, score);
        System.out.println(str);
    }
}
```



## 10、Scanner类



### 10.1:helloworld



```java
import java.util.Scanner;
public class TestDemo {
    public static void main(String[] args) throws Exception { 	// 此处直接抛出
        Scanner scan = new Scanner(System.in); 			// 准备接收键盘输入数据
        System.out.print("请输入内容：");					// 提示信息
        if (scan.hasNext()) { 							// 是否有输入数据
           System.out.println("输入内容：" + scan.next());		// 存在内容则输出
        }
        scan.close();
    }
}
```



### 10.2:输入数字



```java
public class TestDemo {
    public static void main(String[] args) throws Exception { // 此处直接抛出
        Scanner scan = new Scanner(System.in); 		// 准备接收键盘输入数据
        System.out.print("请输入成绩：");
        if (scan.hasNextDouble()) { 					// 表示输入的是一个小数
           double score = scan.nextDouble(); 			// 省略了转型
           System.out.println("输入内容：" + score);
        } else { 								// 表示输入的不是一个小数
           System.out.println("输入的不是数字，错误！");
        }
        scan.close();
    }
}
```



### 10.3：正则校验



```java
import java.util.Scanner;
public class TestDemo {
    public static void main(String[] args) throws Exception { 	// 此处直接抛出
        Scanner scan = new Scanner(System.in) ;			// 准备接收键盘输入数据
        System.out.print("请输入生日：");					// 提示文字
        if (scan.hasNext("\\d{4}-\\d{2}-\\d{2}")) {			// 正则验证
           String bir = scan.next("\\d{4}-\\d{2}-\\d{2}") ;	// 接收数据
           System.out.println("输入内容：" + bir);
        } else {										// 数据格式错误
           System.out.println("输入的生日格式错误！");
        }
        scan.close(); 
    }
}
```



### 10.4：读取文件



```java
import java.io.File;
import java.io.FileInputStream;
import java.util.Scanner;
public class TestDemo {
    public static void main(String[] args) throws Exception { // 此处直接抛出
        Scanner scan = new Scanner(new FileInputStream(new File("D:"
                + File.separator + "yootk.txt")));		// 设置读取的文件输入流
        scan.useDelimiter("\n"); 					// 设置读取的分隔符
        while (scan.hasNext()) {						// 循环读取
            System.out.println(scan.next());			// 直接输出读取数据
        }
        scan.close();
    }
}
```



#  四、IO流三



## 4.1：实现图片的复制



> 我们来做一个图片的复制----图片自备



```java
@Test
	public void test01() {
		FileInputStream fis = null;
		FileOutputStream fos = null;
		try {
			fis = new FileInputStream("e:\\png-1475.png");
			fos = new FileOutputStream("e:\\png-1476.png");
			int b = -1;
			while ((b = fis.read()) != -1) {
				fos.write(b);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				fis.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				fos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
```



## 4.2：使用第三方IO包



### 1.引入jar



==maven坐标方式导入==

```xml
 <dependency>
   <groupId>commons-io</groupId>
   <artifactId>commons-io</artifactId>
   <version>2.8.0</version>
 </dependency>
```



### 2.代码



```java
public static void main(String[] args) {
    try {
         FileUtils.copyFile(
                new File("e:\\png-1475.png"),
                 new File("e:\\png-257a.png"));
      } catch (IOException e) {
         e.printStackTrace();
     }
     System.out.println("end");
}
```



## **4.3：properties资源文件**



### 1.简介



> java中的properties文件是一种配置文件，主要用于表达配置信息，文件类型为*.properties，格式为文本文件，文件的内容是格式是:`"键=值"`（推荐）或`"键:值"`的格式，在properties文件中，可以用**"#"（推荐）或者"//"**来作注释，properties文件在Java编程中用到的地方很多，操作很方便。<font color='red'>（注意：其实properties文件相当于只是定义了一种文件格式，在实际应用中后缀名可以改为其他任何符合后缀名规范的名称，比如.cfg，.ini等后缀名）</font>现在定义一个databaseInfo.properties文件，如下：



```properties
mysql.user=root
mysql.password=123456
mysql.driverClass=com.mysql.jdbc.Driver
mysql.jdbcUrl=jdbc:mysql://127.0.0.1:3306/mycar?serverTimezone=UTC&useUnicode=true&characterEncoding=utf-8
orcal.user=SCOTT
orcal.password=123456
orcal.driverClass=oracle.jdbc.OracleDriver
orcal.jdbcUrl=jdbc:oracle:thin:@127.0.0.1:1521:ORCL
jdbc.acquireIncrement=10 
jdbc.initialPoolSize=30 
jdbc.minPoolSize=5
jdbc.maxPoolSize=40
jdbc.maxStatements=1000
jdbc.maxStatementsPerConnection=100
```



### 2.注意事项



**我们使用开发工具编写properties文件的时候一定要注意文件的格式是什么编码：推荐使用UTF-8**



### **3.Properties类的重要方法**



> + Properties 类存在于包`Java.util`中，该类继承自 `Hashtable `(即：**public class java.util.Properties extends java.util.Hashtable{...}**)
>
> + 由于Properties 是Hashtable的子类，不允许key和value是null，并且它的key和value的类型都是String。



| 方法                                          | 简介                                                         |
| --------------------------------------------- | ------------------------------------------------------------ |
| **getProperty( String key)**                  | 用指定的键在此属性列表中搜索属性。也就是通过参数 key ，得到 key 所对应的 value。 |
| **load( InputStream inStream)**               | 从输入流中读取属性列表（键和元素对）。通过对指定的文件（比如说上面的 databaseInfo.properties 文件）进行装载来获取该文件中的所有键-值对。以供 getProperty ( String key) 来搜索。 |
| **setProperty( String key, String value)**    | 调用 Hashtable 的方法 put 。他通过调用基类的put方法来设置键-值对。 |
| **store( OutputStream out, String comments)** | 以适合使用 load 方法加载到 Properties 表中的格式，将此 Properties 表中的属性列表（键和元素对）写入输出流。与 load 方法相反，该方法将键 - 值对写入到指定的文件中去。 |
| **clear()**                                   | 清除所有装载的键-值对。该方法在基类中提供。                  |



### **4.Java对Properites类的常用操作**



```java
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Iterator;
import java.util.Properties;
import java.util.Set;

/**
 * @Description：Java对Properites类的常用操作
 */
public class PropDemo {

    // 根据key读取value
    public String readValue(String filePath, String key) {
        Properties props = new Properties();
        try {
            InputStream in = this.getClass().getResourceAsStream("/"+filePath);
            props.load(in);
            String value = props.getProperty(key);
            System.out.println(key + "=" + value);

            in.close();
            return value;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // 写入properties信息
    public void writeProperties(String filePath, String parameterName, String parameterValue) {
        Properties prop = new Properties();
        try {
            InputStream fis = this.getClass().getResourceAsStream("/"+filePath);
            // 从输入流中读取属性列表（键值对）
            prop.load(fis);

            OutputStream fos=new FileOutputStream(this.getClass().getResource("/"+filePath).getPath());
            // 设置新的键值对
            prop.setProperty(parameterName, parameterValue);
            // 将此 Properties 表中的属性列表（键和元素对）写入输出流
            prop.store(fos, "add key: '" + parameterName + "' value:" + parameterValue);

            fis.close();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 读取properties的全部信息
    public void readProperties(String filePath) {
        Properties props = new Properties();
        try {
            InputStream in = this.getClass().getResourceAsStream("/"+filePath);
            props.load(in);

            // 使用Set集合取得所有key值
            Set keyValue = props.keySet();
            // 使用while循环遍历
            Iterator it = keyValue.iterator();
            while (it.hasNext()){
                String key = (String) it.next();
                String Property = props.getProperty(key);
                System.out.println(key + "=" + Property);
            }
            in.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

        // 根据key读取value
        new PropDemo().readValue("databaseInfo.properties", "url");

        // 写入properties信息
        new PropDemo().writeProperties("databaseInfo.properties", "databaseType", "oracle");

        // 读取properties的全部信息
        new PropDemo().readProperties("databaseInfo.properties");
    }
}
```



### 5.自定义读取Properties文件工具类



> 工具类



```java
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
public class PropertiesUtil {
	private Map<String, String> map = new HashMap<>();
	private static Properties properties = new Properties();
	private static PropertiesUtil INstance = null;
	private static final InputStream IN = PropertiesUtil.class.getClassLoader().getResourceAsStream("jdbc.properties");
	static {
		try {
			properties.load(IN);
			
			for(Object obj : properties.keySet()) {
				String key = (String)obj;
				String value = properties.getProperty(key);
				PropertiesUtil.getINstance().addProperty(key, value);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	private PropertiesUtil() {}
	public static PropertiesUtil getINstance() {
		if(INstance==null) {
			INstance = new PropertiesUtil();
		}
		return INstance;
	}
	private void addProperty(String key,String value) {
		map.put(key, value);
	}
	public String getProperty(String key) {
		return map.get(key);
	}
}
```



> 测试



```java
public class TestProperties {
	public static void main(String[] args) throws Exception {
		Properties properties = new Properties();
		properties.load(TestProperties.class.getClassLoader().getResourceAsStream("jdbc.properties"));
		String id = properties.getProperty("id");
		String userName =properties.getProperty("userName");
		String passWord =properties.getProperty("passWord");
		String sex =properties.getProperty("sex");
		System.out.println("id:"+id+"\tuserName:"+userName+"\tpassWord:"+passWord+"\tsex:"+sex);
	}
}
```



> 资源文件



```properties
id=1
userName=admin
passWord=123
sex=\u7537
```



## 4.4：IO流使用规律总结



> + （1）明确要操作的数据是数据源还是数据目的(要读还是要写)
>
> 	+ 源：InputStream　　Reader
>       
>    > 	+ 目的：OutputStream　　Writer
>       
> + （2）明确要操作的设备上的数据是字节还是文本
>
>   + 源：
>          >
>          > 		+ 字节：InputStream
>                     >
>                     > 		+ 文本：Reader
>
>   + 目的：
>   	+ 字节：OutputStream
>
>          > 		+ 文本：Writer
>
> + （3）明确数据所在的具体设备
>
> 	+ 源设备：
>          > 		+ 硬盘：文件 File开头
>         	        > 		+ 内存：数组，字符串
> 		+ 键盘：System.in
> 		+ 网络：Socket
>
> 	+ 目的设备：
>
> 		+ 硬盘：文件 File开头
>
> 		+ 内存：数组，字符串
> 		+ 屏幕：System.out
>
> 		+ 网络：Socket
>
> + （4）明确是否需要额外功能？
> 	+ 需要转换——转换流 InputStreamReader OutputStreamWriter
>          > 	+ 需要高效——缓冲流Bufferedxxx
> 	+ 多个源——序列流 SequenceInputStream
> 	+ 对象序列化——ObjectInputStream,ObjectOutputStream
> 	+ 保证数据的输出形式——打印流PrintStream Printwriter
> 	+ 操作基本数据，保证字节原样性——DataOutputStream,DataInputStream



## 4.5：注意点



1. ==及时关闭流==
   我们在io操作完成后，需要及时关闭流释放资源，因为这样的对象不主动关闭，将会占用系统资源，会增大系统的开销。



2. ==文件中的分隔符使用类中常量==
   因为在window和linux中的分隔符等可能会不同，使用类中的分割常量将会根据系统自动生成。如File.pathSeparator、File.separator等