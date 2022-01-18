# 3D轮播插件vue-carousel-3d





## 插件介绍



vue-carousel-3d是一个效果非常好的3D轮播插件，可以解决一些非普通轮播特别是swiper比较难实现的场景。但是其官方的文档很难访问，导致遇到问题的时候比较难定位，所以在看了源码之后我归纳了一下，做了一套全面的文档方便其他人使用。


## 使用方法



### 1.安装



```java
npm install -S vue-carousel-3d
```



### 2.引入



在main.js全局引入：

```java
import Vue from 'vue';
import Carousel3d from 'vue-carousel-3d';
Vue.use(Carousel3d);
```

在需要使用的组件引入：

```java
import { Carousel3d, Slide } from 'vue-carousel-3d'
export default {
　　...
　　components: {
　　　　Carousel3d,
　　　　Slide
　　}
　　...
}
```



### 3.使用



#### -普通用法



```java
<div class="box">
  <carousel-3d>
    <slide v-for="(slide, i) in slides" :index="i" :key="i">
      <h1>{{ slide.title }}</h1>
      <p>{{ slide.desc }}</p>
    </slide>
  </carousel-3d>
</div>
```



#### - 通过插槽作用域获得信息



```java
<div class="box">
  <carousel-3d>
    <slide v-for="(item, i) in slides" :index="i" :key="i">
      <template slot-scope="obj">
        <img src="item.src" @click="imgClick(obj)" />
      </template>
    </slide>
  </carousel-3d>
</div>
```

obj内可以获取到的值有**index**, **isCurrent**, **leftIndex**, **rightIndex**。



## 文档



公开属性和方法：

### Property



| 属性名           | 类型             | 作用                                    | 默认值              | 可选值              |
| ---------------- | ---------------- | --------------------------------------- | ------------------- | ------------------- |
| autoplay         | Boolean          | 自动播放                                | true                |                     |
| perspective      | [Number, String] | 非主slide朝内旋转的角度                 | 35                  |                     |
| display          | [Number, String] | 显示的slide的个数                       | 5                   |                     |
| loop             | Boolean          | 是否循环轮播                            | true                |                     |
| animationSpeed   | [Number, String] | 切换动画的速度                          | 500                 |                     |
| dir              | String           | 轮播旋转的方向                          | 'rtl'               | 'rtl'、'ltr'        |
| width            | [Number, String] | slide的宽度                             | 360                 |                     |
| height           | [Number, String] | slide的高度                             | 270                 |                     |
| border           | [Number, String] | slide边框宽度                           | 1                   |                     |
| space            | [Number, String] | 非主slide的间隔宽度                     | 'auto'              | 任意数字或默认值    |
| startIndex       | [Number, String] | 主slide的index                          | 0                   | slide总数内的任意值 |
| clickable        | Boolean          | slide是否可点击                         | true                |                     |
| disable3d        | Boolean          | 是否取消3D效果                          | false               |                     |
| minSwipeDistance | Number           | 能使slide产生滑动效果的鼠标最小移动距离 | 10                  |                     |
| inverseScaling   | [Number, String] | 非主slide离屏幕的距离                   | 300                 |                     |
| controlsVisible  | Boolean          | 左右控制器是否显示                      | false               |                     |
| controlsPrevHtml | String           | 左控制器文本                            | '& lsaquo;'(无空格) |                     |
| controlsNextHtml | String           | 右控制器文本                            | '& rsaquo;'(无空格) |                     |
| controlsWidth    | [String, Number] | 控制器宽度                              | 50                  |                     |
| controlsHeight   | [String, Number] | 控制器高度                              | 50                  |                     |
| oneDirectional   | Boolean          | 只在左或右显示slide                     | false               |                     |

另外还有两个不知道有什么用的属性：



| 属性名 | 类型             | 默认值 |
| ------ | ---------------- | ------ |
| count  | [Number, String] | 0      |
| bias   | String           | 'left' |

（如有需要可以自己试一试）



### Methods



| 属性名           | 事件                    |
| ---------------- | ----------------------- |
| onLastSlide      | 滑动到最后一个slide触发 |
| onSlideChange    | slide改变时触发         |
| onMainSlideClick | 主slide被点击时触发     |



## 获取异步数据时的配置方法



### 通过v-if判断插件的显示



```java
<carousel-3d v-if="slides.length">
  <slide v-for="(slide, i) in slides" :index="i" :key="i">
    <h1>{{ slide.title }}</h1>
    <p>{{ slide.desc }}</p>
  </slide>
</carousel-3d>
export default {
  data () {
    return {
      slides: []
    }
  },
  methods: {
    getData () {
       ... //获取数据
       this.slides = res.data.list
    }
  }
}
```

获取数据后再展示。



## 修改源码的办法



通过查看package.json里面的：

```json
 "main": "./dist/carousel-3d.common.js"
```

可以知道我们引用的实际上是./dist/carousel-3d.common.js这个文件。所以直接修改项目中的源码是没有效果的。

------

有两种办法修改：

1.如果所需修改的功能不多，可先在源码文件中修改成想要的样子，然后去./dist/carousel-3d.common.js文件里搜索到相关位置修改。（我使用了这一种，因为只需要添加一个简单的功能）

2.如果需要添加或修改的功能较多，可先把仓库fork到自己的github上，经过大量修改后重新打包再上传到npm上供项目使用。