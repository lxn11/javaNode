### Spring注解大全

官方文档查注解

https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/context/annotation/package-summary.html

可以通过package去查看官方文档里的具体说明，比如：  Annotation Types Summary部分就是该package下所有注解的说明。 

[Spring注解文档(比较老)](http://rcode.rbtree.cn/tool/spring.html)

### 一、Spring bean注解

#### 1、@SpringBootApplication

让spring boot自动给程序进行必要的配置，这个配置等同于：@Configuration ，@EnableAutoConfiguration 和 @ComponentScan 三个配置。

#### 2、@Component

泛指各种组件，就是说当我们的类不属于各种归类的时候（不属于@Controller、@Services等的时候），我们就可以使用@Component来标注这个类，把普通pojo实例化到spring容器中，相当于配置文件中的：。

所以可以理解为@Component可以细化为@Reposity，@Service,@Controller。

```
@Component("conversionImpl")
//其实默认的spring中的Bean id 为 conversionImpl(首字母小写)
public class ConversionImpl implements Conversion {
    @Autowired
    private RedisClient redisClient;
}
```

#### 3、@ComponentScan

@ComponentScan主要就是定义扫描的路径从中找出标识了需要装配的类自动装配到spring的bean容器中。前面说到过@Controller注解，@Service，@Repository注解，它们其实都是组件，属于@Component注解，而@ComponentScan注解默认会装配标识了@Controller，@Service，@Repository，@Component注解的类到spring容器中。

```
//扫描com.demo下的组件
@ComponentScan(value="com.demo")
@Configuration
public class myConfig {
}
```

#### 4、@Service

一般用于修饰service层的组件

```
@Service()
public class UserService{
    @Resource
    private UserDao userDao;
    public void add(){
        userDao.add();
    }
}
```

#### 5、@EnableAutoConfiguration

SpringBoot自动配置（auto-configuration）：尝试根据你添加的jar依赖自动配置你的Spring应用。

例如，如果你的classpath下存在HSQLDB，并且你没有手动配置任何数据库连接beans，那么我们将自动配置一个内存型（in-memory）数据库。

你可以将@EnableAutoConfiguration或者@SpringBootApplication注解添加到一个@Configuration类上来选择自动配置。如果发现应用了你不想要的特定自动配置类，你可以使用@EnableAutoConfiguration注解的`排除属性`来禁用它们。

禁用特定的自动配置属性

```
@Configuration
@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})
public class MyConfiguration{
    ...
}
```

 

#### 6、@Resource、@Autowired和@Inject

@Resource和@Autowired都是做`bean注入`时使用。

@Resource的作用相当于@Autowired，只不过`@Autowired按照byType`自动注入。

1、共同点

两者都可以写在字段和setter方法上。两者如果都写在字段上，那么就不需要再写setter方法。

2、不同点

##### 6.1、@Autowired

Spring 2.5 引入。@Autowired为Spring提供的注解，需要导入包org.springframework.beans.factory.annotation.Autowired，只按照byType注入。

@Autowired注解是按照类型（byType）装配依赖对象，默认情况下它要求依赖对象必须存在，`如果允许null值，可以设置它的required属性为false`。如果我们想使用按照名称（byName）来装配，可以结合@Qualifier注解一起使用。

```
public class TestServiceImpl {
    // 下面两种@Autowired只要使用一种即可
    @Autowired
    private UserDao userDao; // 用于字段上
    @Autowired
    public void setUserDao(UserDao userDao) { // 用于属性的方法上
        this.userDao = userDao;
    }

    @Autowired
    @Qualifier("userDao")
    private UserDao userDao;
}
```

##### 6.2、@Resource

@`Resource默认按照ByName`自动注入，由J2EE提供，是JSR250规范的实现，需要导入javax.annotation实现注入。

@Resource有两个重要的属性：name和type，而Spring将@Resource注解的name属性解析为bean的名字，而type属性则解析为bean的类型。所以，如果使用name属性，则使用byName的自动注入策略，而使用type属性时则使用byType自动注入策略。如果既不制定name也不制定type属性，这时将通过反射机制使用byName自动注入策略。

```
public class TestServiceImpl {
    // 下面两种@Resource只要使用一种即可
    @Resource(name="userDao")
    private UserDao userDao; // 用于字段上
    @Resource(name="userDao")
    public void setUserDao(UserDao userDao) {
        // 用于属性的setter方法上
        this.userDao = userDao;
    }
}
```

注：最好是将@Resource放在setter方法上，因为这样更符合面向对象的思想，通过set、get去操作属性，而不是直接去操作属性。

@Resource装配顺序：

- 如果同时指定了name和type，则从Spring上下文中找到唯一匹配的bean进行装配，找不到则抛出异常。
- 如果指定了name，则从上下文中查找名称（id）匹配的bean进行装配，找不到则抛出异常。
- 如果指定了type，则从上下文中找到类似匹配的唯一bean进行装配，找不到或是找到多个，都会抛出异常。
- 如果既没有指定name，又没有指定type，则自动按照byName方式进行装配；如果没有匹配，则回退为一个原始类型进行匹配，如果匹配则自动装配。

##### 6.3、@Inject

@Inject是JSR330 (Dependency Injection for Java)中的规范，需要导入javax.inject.Inject;实现注入。@Inject可以作用在变量、setter方法、构造函数上。根据类型进行自动装配的，如果需要按名称进行装配，则需要配合@Named。

@Named("XXX") 中的 XXX是 Bean 的名称，所以 @Inject和 @Named结合使用时，自动注入的策略就从 byType 转变成 byName 了。

```
public class User{
    private Person person;
    @Inject
    pbulic void setPerson(Person person){
        this.person = person;
    }
    @Inject
    pbulic void setPerson1(@Named("main")Person person)
    {
        this.person = person;
    }
}
```

##### 6.4、@Qualifier

当系统中存在同一类型的多个Bean时，@Autowired在进行依赖注入的时候就不知道该选择哪一个实现类进行注入。此时，我们可以使用@Qualifier注解来微调，帮助@Autowired选择正确的依赖项；

#### 7、@Repository

用于注解dao层，在daoImpl类上面注解。

#### 8、@Bean

产生一个Bean对象，然后这个Bean对象交给Spring管理。用@Bean标注方法等价于XML中配置的bean。

产生这个Bean对象的方法Spring只会调用一次，随后这个Spring将会将这个Bean对象放在自己的IOC容器中。SpringIOC 容器管理一个或者多个bean，这些bean都需要在@Configuration注解下进行创建，在一个方法上使用@Bean注解就表明这个方法需要交给Spring进行管理。

```
@Bean
public class UserTest(){
    public User getUser(){
        System.out.println("创建user实例");
        return new User("张三",26);
    }
}
```

@Bean 注解的属性有：value、name、autowire、initMethod、destroyMethod。

name 和 value 两个属性是相同的含义的， 在代码中定义了别名。为 bean 起一个名字，如果默认没有写该属性，那么就使用方法的名称为该 bean 的名称。

autowire指定 bean 的装配方式， 根据名称 和 根据类型 装配， 一般不设置，采用默认即可。autowire指定的装配方式 有三种Autowire.NO (默认设置)、Autowire.BY_NAME、Autowire.BY_TYPE。

initMethod和destroyMethod指定bean的初始化方法和销毁方法， 直接指定方法名称即可，不用带括号。

```
public class MyBean {
    public MyBean(){
        System.out.println("MyBean Initializing");
    }
    public void init(){
        System.out.println("Bean 初始化方法被调用");
    }
    public void destroy(){
        System.out.println("Bean 销毁方法被调用");
    }
}
@Configuration
public class AppConfig {
    @Bean(initMethod = "init", destroyMethod = "destroy")
    public MyBean myBean(){
        return new MyBean();
    }
}
```

##### 8.1、@Primary

当系统中需要配置多个具有相同类型的bean时，@Primary可以定义这些Bean的[优先级。

##### 8.2、@DependsOn

`@DependsOn`注解可以配置Spring IoC容器在初始化一个Bean之前，先初始化其他的Bean对象。

```
@Bean("firstBean")
@DependOn("value={"SecondBean","ThirdBean"}")
public FirstBean getFirstBean(){
    return new FirstBean();
}

public SecondBean getSecondBean(){
    return new SecondBean();
}

public ThirdBean getThirdBean(){
    return new ThirdBean();
}
```

 

#### 9、@Scope

@Scope注解默认的singleton单例模式。

@Scope注解是springIoc容器中的一个作用域，在 Spring IoC 容器中具有以下几种作用域：基本作用域singleton（单例）、prototype(多例)，Web 作用域（reqeust、session、globalsession），自定义作用域。

```
prototype原型模式：
@Scope(value=ConfigurableBeanFactory.SCOPE_PROTOTYPE)在每次注入的时候回自动创建一个新的bean实例

singleton单例模式：
@Scope(value=ConfigurableBeanFactory.SCOPE_SINGLETON)单例模式，在整个应用中只能创建一个实例

globalsession模式：
@Scope(value=WebApplicationContext.SCOPE_GLOBAL_SESSION)全局session中的一般不常用

@Scope(value=WebApplicationContext.SCOPE_APPLICATION)在一个web应用中只创建一个实例

request模式：
@Scope(value=WebApplicationContext.SCOPE_REQUEST)在一个请求中创建一个实例

session模式：
@Scope(value=WebApplicationContext.SCOPE_SESSION)每次创建一个会话中创建一个实例
```

 

```
@Configuration
public class myConfig {
    //默认是单例的。不需要特别说明
    @Bean("person")
    public Person person(){
        return new Person("binghe002", 18);
    }
}
@Configuration
public class myConfig {
    //Person对象的作用域修改成prototype,多例的
    @Scope("prototype")
    @Bean("person")
    public Person person(){
        return new Person("binghe002", 18);
    }
}
```

#### 10、@Value

@Value的作用是通过注解将常量、配置文件中的值、其他bean的属性值注入到变量中，作为变量的初始值。

(1)、普通注入

```
@Value("张三")
private String name; // 注入普通字符串
```

(2)、bean属性、系统属性、表达式注入，使用@Value("#{}")。bean属性注入需要注入者和被注入者属于同一个IOC容器，或者父子IOC容器关系，在同一个作用域内。

```
// 注入其他Bean属性：注入beanInject对象的属性another，类具体定义见下面
@Value("#{beanInject.another}")
private String fromAnotherBean;
// 注入操作系统属性
@Value("#{systemProperties['os.name']}")
private String systemPropertiesName;
//注入表达式结果
@Value("#{T(java.lang.Math).random() * 100.0 }")
private double randomNumber;
```

（3）、配置文件属性注入@Value("${}")

@Value("#{}")读取配置文件中的值，注入到变量中去。配置文件分为默认配置文件application.properties和自定义配置文件。

#### 11、@Conditional

@Conditional注解可以控制更为复杂的配置条件。在Spring内置的条件控制注解不满足应用需求的时候，可以使用此注解定义自定义的控制条件，以达到自定义的要求;

```
@Conditioanl(CustomConditioanl.class)
CustomProperties addCustomProperties(){
 //...
}
```

##### 11.1、@ConditionalOnClass & @ConditionalOnMissingClass

这两个注解属于类条件注解，它们根据是否存在某个类作为判断依据来决定是否要执行某些配置；

```
@Configuration
@ConditionalOnClass(DataSource.class)
class MySQLAutoConfiguration {
 //...
}
```

##### 11.2、@ConditionalOnBean & @ConditionalOnMissingBean

这两个注解属于对象条件注解，根据是否存在某个对象作为依据来决定是否要执行某些配置方法;

```
@Bean
@ConditionalOnBean(name="dataSource")
LocalContainerEntityManagerFactoryBean entityManagerFactory(){
 //...
}
@Bean
@ConditionalOnMissingBean
public MyBean myBean(){
 //...
}
```

##### 11.3、@ConditionalOnProperty

@ConditionalOnProperty注解会根据Spring配置文件中的配置项是否满足配置要求，从而决定是否要执行被其标注的方法；

```
@Bean
@ConditionalOnProperty(name="alipay",havingValue="on")
Alipay alipay(){
 return new Alipay();
}
```

##### 11.4、@ConditionalOnResource

用于检测当某个配置文件存在，存在则触发被其标注的方法；

```
@ConditionalOnResource(resources = "classpath:website.properties")
Properties addWebsiteProperties(){
 //...
}
```

##### 11.5、@ConditionalOnWebApplication & @ConditionalOnNotWebApplication

这两个注解用于判断当前的应用程序是否是Web应用程序。如果当前应用是Web应用程序，则使用Spring WebApplicationContext,并定义其会话的生命周期;

```
@ConditionalOnWebApplication
HealthCheckController healthCheckController(){
 //...
}
```

##### 11.6、@ConditionalExpression

此注解可以让我们控制更细粒度的基于表达式的配置条件限制。当表达式满足某个条件或者表达式为真的时候，将会执行被此注解标注的方法。

```
@Bean
@ConditionalException("${localstore} && ${local == 'true'}")
LocalFileStore store(){
 //...
}
```

#### 12、@PostConstruct & @PreDestroy

值得注意的是，这两个注解不属于Spring,它们是源于JSR-250中的两个注解，位于`common-annotations.jar`中。@PostConstruct注解用于标注在Bean被Spring初始化之前需要执行的方法。@PreDestroy注解用于标注Bean被销毁前需要执行的方法。

 

### 二、切面（AOP）相关注解

在运行时，动态地将代码切入到类的指定方法、指定位置上的编程思想就是面向切面的编程，简称AOP（aspect object programming）。AOP编程，可以将一些系统性相关的编程工作，独立提取出来，独立实现，然后通过切面切入进系统。从而避免了在业务逻辑的代码中混入很多的系统相关的逻辑——比如权限管理，事物管理，日志记录等等。这些系统性的编程工作都可以独立编码实现，然后通过AOP技术切入进系统即可。从而达到了 将不同的关注点分离出来的效果。

aop技术的功能是让关注点与业务逻辑代码进行分离；而重复的代码就是关注点；关注点形成的类，就是切面（类）

Spring支持AspectJ的注解式aop编程，需要在java的配置类中使用@EnableAspectJAutoProxy注解开启Spring对AspectJ代理的支持。下面介绍下aop编程的相关注解。

#### 1、@EnableAspectJAutoProxy

先说说@EnableAspectJAutoProxy注解，看看它的源码：

```
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(AspectJAutoProxyRegistrar.class)
public @interface EnableAspectJAutoProxy {
    /**
     * Indicate whether subclass-based (CGLIB) proxies are to be created as opposed
     * to standard Java interface-based proxies. The default is {@code false}.
     */
    boolean proxyTargetClass() default false;
    /**
     * Indicate that the proxy should be exposed by the AOP framework as a {@code ThreadLocal}
     * for retrieval via the {@link org.springframework.aop.framework.AopContext} class.
     * Off by default, i.e. no guarantees that {@code AopContext} access will work.
     * @since 4.3.1
     */
    boolean exposeProxy() default false;
}
```

这里有两个方法,一个是控制aop的具体实现方式,为true 的话使用cglib,为false的话使用java的Proxy,默认为false,第二个参数控制代理的暴露方式,解决内部调用不能使用代理的场景，默认为false。

#### 2、@Aspect

声明一个切面（类）上，作用是把当前类标识为一个切面供容器读取。在切面类中需要定义切面方法用于响应响应的目标方法，切面方法即为通知方法，通知方法需要用注解标识，AspectJ 支持 5 种类型的通知注解:

##### 2.1、@Before

前置通知, 在方法执行之前执行。

##### 2.2、@After

后置通知, 在方法执行之后执行。

##### 2.3、@AfterRunning

返回通知, 在方法返回结果之后执行。

##### 2.4、@AfterThrowing

异常通知, 在方法抛出异常之后。

##### 2.5、@Around

环绕通知, 围绕着方法执行。

#### 3、@PointCut

声明切点，是植入Advice（通知）的触发条件。每个Pointcut的定义包括2部分，一是表达式，二是方法签名。方法签名必须是 public及void型。可以将Pointcut中的方法看作是一个被Advice引用的助记符，因为表达式不直观，因此我们可以通过方法签名的方式为 此表达式命名。因此Pointcut中的方法只需要方法签名，而不需要在方法体内编写实际代码。

```
/**
 * 日志切面
 */
@Component
@Aspect
public class LoggingAspect {
    /**
     * 前置通知：目标方法执行之前执行以下方法体的内容
     */
    @Before("execution(* com.qcc.beans.aop.*.*(..))")
    public void beforeMethod(JoinPoint jp){
        String methodName = jp.getSignature().getName();
        System.out.println("【前置通知】the method 【" + methodName + "】 begins with " + Arrays.asList(jp.getArgs()));
    }
    /**
     * 返回通知：目标方法正常执行完毕时执行以下代码
     */
    @AfterReturning(value="execution(* com.qcc.beans.aop.*.*(..))",returning="result")
    public void afterReturningMethod(JoinPoint jp, Object result){
        String methodName = jp.getSignature().getName();
        System.out.println("【返回通知】the method 【" + methodName + "】 ends with 【" + result + "】");
    }
    /**
     * 后置通知：目标方法执行之后执行以下方法体的内容，不管是否发生异常。
     * @param jp
     */
    @After("execution(* com.qcc.beans.aop.*.*(..))")
    public void afterMethod(JoinPoint jp){
        System.out.println("【后置通知】this is a afterMethod advice...");
    }
    /**
     * 异常通知：目标方法发生异常的时候执行以下代码
     */
    @AfterThrowing(value="execution(* com.qcc.beans.aop.*.*(..))",throwing="e")
    public void afterThorwingMethod(JoinPoint jp, NullPointerException e){
        String methodName = jp.getSignature().getName();
        System.out.println("【异常通知】the method 【" + methodName + "】 occurs exception: " + e);
    }
}
```

### 三、全局异常处理

#### 1、@ControllerAdvice

包含@Component。可以被扫描到。统一处理异常。

#### 2、@ExceptionHandler（Exception.class）

用在方法上面表示遇到这个异常就执行以下方法。

如何使用呢？举个例子。如果方法参数不对的话就会抛出`MethodArgumentNotValidException`，我们来处理这个异常。

```
@ControllerAdvice
@ResponseBody
public class GlobalExceptionHandler {

    /**
     * 请求参数异常处理
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex, HttpServletRequest request) {
       ......
    }
}
```

 

### 四、JPA注解

#### 1、@Entity

#### 2、@Table(name=”“)

表明这是一个实体类。一般用于jpa，这两个注解一般一块使用，但是如果表名和实体类名相同的话，@Table可以省略

#### 3、@MappedSuperClass

用在确定是父类的entity上。父类的属性子类可以继承。

#### 4、@NoRepositoryBean

一般用作父类的repository，有这个注解，spring不会去实例化该repository。

#### 5、@Column

如果字段名与列名相同，则可以省略。

#### 6、@Id

表示该属性为主键。

#### 7、@GeneratedValue(strategy = GenerationType.SEQUENCE,generator = “repair_seq”)

表示主键生成策略是sequence（可以为Auto、IDENTITY、native等，Auto表示可在多个数据库间切换），指定sequence的名字是repair_seq。

```
@Id
@GeneratedValue(generator = "IdentityIdGenerator")
@GenericGenerator(name = "IdentityIdGenerator", strategy = "identity")
private Long id;
```

等价于：

```
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;
```

#### 8、@SequenceGeneretor(name = “repair_seq”, sequenceName = “seq_repair”, allocationSize = 1)

name为sequence的名称，以便使用，sequenceName为数据库的sequence名称，两个名称可以一致。

jpa 提供的主键生成策略有如下几种：

```
public class DefaultIdentifierGeneratorFactory
  implements MutableIdentifierGeneratorFactory, Serializable, ServiceRegistryAwareService {

 @SuppressWarnings("deprecation")
 public DefaultIdentifierGeneratorFactory() {
  register( "uuid2", UUIDGenerator.class );
  register( "guid", GUIDGenerator.class );   // can be done with UUIDGenerator + strategy
  register( "uuid", UUIDHexGenerator.class );   // "deprecated" for new use
  register( "uuid.hex", UUIDHexGenerator.class );  // uuid.hex is deprecated
  register( "assigned", Assigned.class );
  register( "identity", IdentityGenerator.class );
  register( "select", SelectGenerator.class );
  register( "sequence", SequenceStyleGenerator.class );
  register( "seqhilo", SequenceHiLoGenerator.class );
  register( "increment", IncrementGenerator.class );
  register( "foreign", ForeignGenerator.class );
  register( "sequence-identity", SequenceIdentityGenerator.class );
  register( "enhanced-sequence", SequenceStyleGenerator.class );
  register( "enhanced-table", TableGenerator.class );
 }

 public void register(String strategy, Class generatorClass) {
  LOG.debugf( "Registering IdentifierGenerator strategy [%s] -> [%s]", strategy, generatorClass.getName() );
  final Class previous = generatorStrategyToClassNameMap.put( strategy, generatorClass );
  if ( previous != null ) {
   LOG.debugf( "    - overriding [%s]", previous.getName() );
  }
 }

}
```

#### 9、@Transient

表示该属性并非一个到数据库表的字段的映射,ORM框架将忽略该属性。

如果一个属性并非数据库表的字段映射,就务必将其标示为@Transient,否则,ORM框架默认其注解为@Basic。@Basic(fetch=FetchType.LAZY)：标记可以指定实体属性的加载方式

如果我们想让`secrect` 这个字段不被持久化，可以使用 `@Transient`关键字声明。

```
Entity(name="USER")
public class User {

    ......
    @Transient
    private String secrect; // not persistent because of @Transient

}
```

除了 `@Transient`关键字声明， 还可以采用下面几种方法：

```
static String secrect; // not persistent because of static
final String secrect = “Satish”; // not persistent because of final
transient String secrect; // not persistent because of transient
```

一般使用注解的方式比较多。

 

#### 10、@JsonIgnore @JsonIgnoreProperties@JsonFormat

@JsonIgnore作用是json序列化时将Javabean中的一些属性忽略掉,序列化和反序列化都受影响。

`@JsonIgnoreProperties` 作用在类上用于过滤掉特定字段不返回或者不解析。

```
//生成json时将userRoles属性过滤
@JsonIgnoreProperties({"userRoles"})
public class User {

    private String userName;
    private String fullName;
    private String password;
    @JsonIgnore
    private List<UserRole> userRoles = new ArrayList<>();
}
```

`@JsonFormat`一般用来格式化 json 数据。：

比如：

```
@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", timezone="GMT")
private Date date;
```

 

#### 11、@JoinColumn（name=”loginId”）

一对一：本表中指向另一个表的外键。一对多：另一个表指向本表的外键。

#### 12、@OneToOne、@OneToMany、@ManyToOne

对应hibernate配置文件中的一对一，一对多，多对一。

#### 13、@Lob

声明某个字段为大字段。

```
@Lob
private String content;
```

更详细的声明：

```
@Lob
//指定 Lob 类型数据的获取策略， FetchType.EAGER 表示非延迟 加载，而 FetchType. LAZY 表示延迟加载 ；
@Basic(fetch = FetchType.EAGER)
//columnDefinition 属性指定数据表对应的 Lob 字段类型
@Column(name = "content", columnDefinition = "LONGTEXT NOT NULL")
private String content;
```

#### 14、@Enumerated

枚举字段要用此注解修饰。

```
public enum Gender {
    MALE("男性"),
    FEMALE("女性");

    private String value;
    Gender(String str){
        value=str;
    }
}

@Entity
@Table(name = "role")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    @Enumerated(EnumType.STRING)
    private Gender gender;
    省略getter/setter......
}
```

数据库里面对应存储的是 MAIL/FEMAIL。

#### 15、@Modifying

删除/修改数据，`@Modifying` 注解提示 JPA 该操作是修改操作,注意还要配合`@Transactional`注解使用。

```
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    @Modifying
    @Transactional(rollbackFor = Exception.class)
    void deleteByUserName(String userName);
}
```

 

#### 16、@EnableJpaAuditing

开启 JPA 审计功能。只要继承了 `AbstractAuditBase`的类都会默认加上下面四个字段。

```
@Data
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass
@EntityListeners(value = AuditingEntityListener.class)
public abstract class AbstractAuditBase {

    @CreatedDate
    @Column(updatable = false)
    @JsonIgnore
    private Instant createdAt;

    @LastModifiedDate
    @JsonIgnore
    private Instant updatedAt;

    @CreatedBy
    @Column(updatable = false)
    @JsonIgnore
    private String createdBy;

    @LastModifiedBy
    @JsonIgnore
    private String updatedBy;
}
```

我们对应的审计功能对应地配置类可能是下面这样的（Spring Security 项目）:

```
@Configuration
@EnableJpaAuditing
public class AuditSecurityConfiguration {
    @Bean
    AuditorAware<String> auditorAware() {
        return () -> Optional.ofNullable(SecurityContextHolder.getContext())
                .map(SecurityContext::getAuthentication)
                .filter(Authentication::isAuthenticated)
                .map(Authentication::getName);
    }
}
```

简单介绍一下上面设计到的一些注解：

1. `@CreatedDate`: 表示该字段为创建时间时间字段，在这个实体被 insert 的时候，会设置值
2. `@CreatedBy` :表示该字段为创建人，在这个实体被 insert 的时候，会设置值 `@LastModifiedDate`、`@LastModifiedBy`同理。

 

### 五、事务

#### 1、@Transactional

第一步： 在入口处增加 @EnableTransactionManagement 注解

```
package com.cm.aps;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class ApsApplication {
    public static void main(String[] args) {
        SpringApplication.run(ApsApplication.class, args);
    }
}
```

第二步就是在Service的类里写处理过程；注意事项请看注释

```
@Override
@Transactional(rollbackFor = Exception.class)  //这里回滚进行定义
public int update(Prdtv prdtv) throws RuntimeException{
    //注意在这里处理业务时，不要使用Try ...异常捕获，否则不回滚
    return prdtvMapper.update(prdtv);
}
```

我们知道 Exception 分为运行时异常 RuntimeException 和非运行时异常。在`@Transactional`注解中如果不配置`rollbackFor`属性,那么事物只会在遇到`RuntimeException`的时候才会回滚,加上`rollbackFor=Exception.class`,可以让事物在遇到非运行时异常时也回滚。

`@Transactional` 注解一般用在可以作用在`类`或者`方法`上。

- **作用于类**：当把`@Transactional 注解放在类上时，表示所有该类的`public 方法都配置相同的事务属性信息。
- **作用于方法**：当类配置了`@Transactional`，方法也配置了`@Transactional`，方法的事务会覆盖类的事务配置信息。

 

### 六、Lombok

#### 1、@Slf4j

自动生成该类的 log 静态常量，要打日志就可以直接打，不用再手动 new log 静态常量。

```
public class User{
    private static final Logger log = LoggerFactory.getLogger(User.class);
    public static void main(String[] args){
        log.info("hi");
    }
}
@Slf4j
public class User{
    public static void main(String[] args){
        log.info("hi");
    }
}
```

#### 2、@Setter

注解在属性上。为属性提供 setting 方法。

#### 3、@Getter

注解在属性上。为属性提供 getting 方法。

#### 4、@Data

注解在类上。等同于添加如下注解：

- @Getter/@Setter
- @ToString
- @EqualsAndHashCode
- @RequiredArgsConstructor

#### 5、@Log4j2

注解在类上。为类提供一个 属性名为log 的 log4j 日志对象，和@Log4j注解类似。

#### 6、@EqualsAndHashCode

默认情况下，会使用所有非瞬态(non-transient)和非静态(non-static)字段来生成equals和hascode方法，也可以指定具体使用哪些属性。

如果某些变量不想要加进判断，可以透过 exclude 排除，也可以使用 of 指定某些字段。

```
@EqualsAndHashCode(exclude = "name")
public class User{
    private String name;
    private Integer age;
}
```

#### 7、@ToString

生成toString方法，默认情况下，会输出类名、所有属性，属性会按照顺序输出，以逗号分割。

#### 8、@NoArgsConstructor, @RequiredArgsConstructor and @AllArgsConstructor

无参构造器、部分参数构造器、全参构造器，当我们需要重载多个构造器的时候，只能自己手写了。

##### 8.1、@NoArgsConstructor

注解在类上。为类提供一个无参的构造方法。

##### 8.2、@AllArgsConstructor

注解在类上。为类提供一个全参的构造方法。

##### 8.3、@RequiredArgsConstructor

生成一个包含 "特定参数" 的构造器，特定参数指的是那些有加上 final 修饰词的变量。

#### 9、@NonNull

注解在属性上，如果注解了，就必须不能为Null。

#### 10、@Nullable

注解在属性上，如果注解了，就必须可以为Null。

#### 11、@Value

也是整合包，但是他会把所有的变量都设成 final 的，其他的就跟 @Data 一样，等于同时加了以下注解：

- @Getter (注意没有setter)
- @ToString
- @EqualsAndHashCode
- @RequiredArgsConstructor

#### 12、@Builder

自动生成流式 set 值写法，从此之后再也不用写一堆 setter 了。

注意，虽然只要加上 @Builder 注解，我们就能够用流式写法快速设定对象的值，但是 setter 还是必须要写不能省略的，因为 Spring 或是其他框架有很多地方都会用到对象的 getter/setter 对他们取值/赋值。

所以通常是 @Data 和 @Builder 会一起用在同个类上，既方便我们流式写代码，也方便框架做事。

 

### 七、参数检验

数据的校验的重要性就不用说了，即使在前端对数据进行校验的情况下，我们还是要对传入后端的数据再进行一遍校验，避免用户绕过浏览器直接通过一些 HTTP 工具直接向后端请求一些违法数据。

**JSR(Java Specification Requests）** 是一套 JavaBean 参数校验的标准，它定义了很多常用的校验注解，我们可以直接将这些注解加在我们 JavaBean 的属性上面，这样就可以在需要校验的时候进行校验了，非常方便！

校验的时候我们实际用的是 **Hibernate Validator** 框架。Hibernate Validator 是 Hibernate 团队最初的数据校验框架，Hibernate Validator 4.x 是 Bean Validation 1.0（JSR 303）的参考实现，Hibernate Validator 5.x 是 Bean Validation 1.1（JSR 349）的参考实现，目前最新版的 Hibernate Validator 6.x 是 Bean Validation 2.0（JSR 380）的参考实现。

SpringBoot 项目的 spring-boot-starter-web 依赖中已经有 hibernate-validator包，不需要引用相关依赖

非 SpringBoot 项目需要自行引入相关依赖包，这里不多做讲解。

  需要注意的是： 所有的注解，推荐使用 JSR 注解，即`javax.validation.constraints`，而不是`org.hibernate.validator.constraints`

#### 1、 一些常用的字段验证的注解

- `@NotEmpty` 被注释的字符串的不能为 null 也不能为空
- `@NotBlank` 被注释的字符串非 null，并且必须包含一个非空白字符
- `@Null` 被注释的元素必须为 null
- `@NotNull` 被注释的元素必须不为 null
- `@AssertTrue` 被注释的元素必须为 true
- `@AssertFalse` 被注释的元素必须为 false
- `@Pattern(regex=,flag=)`被注释的元素必须符合指定的正则表达式
- `@Email` 被注释的元素必须是 Email 格式。
- `@Min(value)`被注释的元素必须是一个数字，其值必须大于等于指定的最小值
- `@Max(value)`被注释的元素必须是一个数字，其值必须小于等于指定的最大值
- `@DecimalMin(value)`被注释的元素必须是一个数字，其值必须大于等于指定的最小值
- `@DecimalMax(value)` 被注释的元素必须是一个数字，其值必须小于等于指定的最大值
- `@Size(max=, min=)`被注释的元素的大小必须在指定的范围内
- `@Digits (integer, fraction)`被注释的元素必须是一个数字，其值必须在可接受的范围内
- `@Past`被注释的元素必须是一个过去的日期
- `@Future` 被注释的元素必须是一个将来的日期
- ......

 

#### 2、 验证请求体(RequestBody)

```
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Person {

    @NotNull(message = "classId 不能为空")
    private String classId;

    @Size(max = 33)
    @NotNull(message = "name 不能为空")
    private String name;

    @Pattern(regexp = "((^Man$|^Woman$|^UGM$))", message = "sex 值不在可选范围")
    @NotNull(message = "sex 不能为空")
    private String sex;

    @Email(message = "email 格式不正确")
    @NotNull(message = "email 不能为空")
    private String email;

}
```

我们在需要验证的参数上加上了`@Valid`注解，如果验证失败，它将抛出`MethodArgumentNotValidException`。

```
@RestController
@RequestMapping("/api")
public class PersonController {

    @PostMapping("/person")
    public ResponseEntity<Person> getPerson(@RequestBody @Valid Person person) {
        return ResponseEntity.ok().body(person);
    }
}
```

#### 3、 验证请求参数(Path Variables 和 Request Parameters)

一定一定不要忘记在类上加上 `Validated` 注解了，这个参数可以告诉 Spring 去校验方法参数。

```
@RestController
@RequestMapping("/api")
@Validated
public class PersonController {

    @GetMapping("/person/{id}")
    public ResponseEntity<Integer> getPersonByID(@Valid @PathVariable("id") @Max(value = 5,message = "超过 id 的范围了") Integer id) {
        return ResponseEntity.ok().body(id);
    }
}
```

 

### 八、 **springMVC相关注解**

#### 1、@RestController

Spring4，注解是@Controller和@ResponseBody的合集,表示这是个控制器bean,并且是将函数的返回值直 接填入HTTP响应体中,是REST风格的控制器。

```
@RestController
@RequestMapping(“/demoInfo2”)
publicclass DemoController2 {
    @RequestMapping("/test")
    public String test(){
       return "ok";
    }
}
```

#### 2、@Controller

在SpringMVC 中，控制器Controller 负责处理由DispatcherServlet 分发的请求，它把用户请求的数据经过业务处理层处理之后封装成一个Model ，然后再把该Model 返回给对应的View 进行展示。在SpringMVC 中提供了一个非常简便的定义Controller 的方法，你无需继承特定的类或实现特定的接口，只需使用@Controller 标记一个类是Controller ，然后使用@RequestMapping 和@RequestParam 等一些注解用以定义URL 请求和Controller 方法之间的映射，这样的Controller 就能被外界访问到。此外Controller 不会直接依赖于HttpServletRequest 和HttpServletResponse 等HttpServlet 对象，它们可以通过Controller 的方法参数灵活的获取到。

用于定义控制器类，在spring 项目中由控制器负责将用户发来的URL请求转发到对应的服务接口（service层），一般这个注解在类中，通常方法需要配合注解@RequestMapping。示例代码：

```
@Controller
@RequestMapping(“/demoInfo”)
public class DemoController {
    @Autowired
    private DemoInfoService demoInfoService;

    @RequestMapping("/hello")
    public String hello(Map<String,Object> map){
       System.out.println("DemoController.hello()");
       map.put("hello","from TemplateController.helloHtml");
       //会使用hello.html或者hello.ftl模板进行渲染显示.
       return"/hello";
    }
}
```

#### 3、@RequestMapping

提供路由信息，是一个用来处理请求地址映射的注解，可用于类或方法上。用于类上，表示类中的所有响应请求的方法都是以该地址作为父路径。

RequestMapping注解有六个属性，下面我们把她分成三类进行说明（下面有相应示例）。

1、 value， method；

value：   指定请求的实际地址，指定的地址可以是URI Template 模式（后面将会说明）；

method：  指定请求的method类型， GET、POST、PUT、DELETE等；

2、consumes，produces

consumes： 指定处理请求的提交内容类型（Content-Type），例如application/json, text/html;

produces:   指定返回的内容类型，仅当request请求头中的(Accept)类型中包含该指定类型才返回；

3、params，headers

params： 指定request中必须包含某些参数值是，才让该方法处理。

headers： 指定request中必须包含某些指定的header值，才能让该方法处理请求。

```
@RestController
@RequestMapping("/home")
public class IndexController {
    /**
    * 将多个请求映射到一个方法上去
    */
    @RequestMapping(value = {
        “”,
        “/page”,
        "page*”,
        "view/*,**/msg"
    })
    String indexMultipleMapping() {
        return "Hello from index multiple mapping.”;
    }
    /**
    * 是否是必须传参
    * /home/name?person=xyz 或 /home/name
    */
    @RequestMapping(value = “/name”)
    String getName(@RequestParam(value = "person”, required = false) String personName) {
        return "Required element of request param”;
    }
    /**
    * 请求类型，请求参数，默认值
    */
    @RequestMapping(value = "/name", method = RequestMethod.GET)
    String getName(@RequestParam(value = "person", defaultValue = "John") String personName) {
        return "Required element of request param";
    }
    /**
    * 产生一个 JSON 响应
    */
    @RequestMapping(value = "/prod", produces = {
        "application/JSON"
    })
    @ResponseBody
    String getProduces() {
        return "Produces attribute";
    }
    /**
    * 可以同时处理请求中的 JSON 和 XML 内容
    */
    @RequestMapping(value = "/cons", consumes = {
        "application/JSON",
        "application/XML"
    })
    String getConsumes() {
        return "Consumes attribute";
    }
    /**
    * 根据请求中的消息头内容缩小请求映射的范围
    */
    @RequestMapping(value = “/head”, headers = {
        "content-type=text/plain”,
        "content-type=text/html"
    })
    String post() {
        return "Mapping applied along with headers”;
    }
    /**
    * 可以让多个处理方法处理到同一个URL 的请求, 而这些请求的参数是不一样的
    */
    @RequestMapping(value = “/fetch”, params = {
        "personId=10"
    })
    String getParams(@RequestParam(“personId”) String id) {
        return "Fetched parameter using params attribute = " + id;
    }
    /**
    * 使用正则表达式来只处理可以匹配到正则表达式的动态 URI
    */
    @RequestMapping(value = “/fetch/{id:[a-z]+}/{name}”, method = RequestMethod.GET)
    String getDynamicUriValueRegex(@PathVariable(“name”) String name) {
        System.out.println(“Name is " + name);
        return "Dynamic URI parameter fetched using regex”;
    }
    /**
    * 向 /home 发起的一个请求将会由 default() 来处理，因为注解并没有指定任何值
    */
    @RequestMapping()
    String
    default () {
        return "This is a default method for the class”;
    }
}
```

 

##### 3.1、@GetMapping

@GetMapping("users")`等价于`@RequestMapping(value="/users",method=RequestMethod.GET)

```
@GetMapping("/users")
public ResponseEntity<List<User>> getAllUsers() {
 return userRepository.findAll();
}
```

##### 3.2、@PostMapping

@PostMapping("users")`等价于`@RequestMapping(value="/users",method=RequestMethod.POST)

关于`@RequestBody`注解的使用，在下面的“前后端传值”这块会讲到。

```
@PostMapping("/users")
public ResponseEntity<User> createUser(@Valid @RequestBody UserCreateRequest userCreateRequest) {
 return userRespository.save(user);
}
```

##### 3.3、@PutMapping

@PutMapping("/users/{userId}")`等价于`@RequestMapping(value="/users/{userId}",method=RequestMethod.PUT)

```
@PutMapping("/users/{userId}")
public ResponseEntity<User> updateUser(@PathVariable(value = "userId") Long userId,
  @Valid @RequestBody UserUpdateRequest userUpdateRequest) {
  ......
}
```

##### 3.4、@DeleteMapping

@DeleteMapping("/users/{userId}")`等价于`@RequestMapping(value="/users/{userId}",method=RequestMethod.DELETE)

```
@DeleteMapping("/users/{userId}")
public ResponseEntity deleteUser(@PathVariable(value = "userId") Long userId){
  ......
}
```

##### 3.5、@PatchMapping

一般实际项目中，我们都是 PUT 不够用了之后才用 PATCH 请求去更新数据。

```
  @PatchMapping("/profile")
  public ResponseEntity updateStudent(@RequestBody StudentUpdateRequest studentUpdateRequest) {
        studentRepository.updateDetail(studentUpdateRequest);
        return ResponseEntity.ok().build();
    }
```

#### 4、@ModelAttribute和 @SessionAttributes

该Controller的所有方法在调用前，先执行此@ModelAttribute方法，可用于注解和方法参数中，可以把这个@ModelAttribute特性，应用在BaseController当中，所有的Controller继承BaseController，即可实现在调用Controller时，先执行@ModelAttribute方法。

##### 4.1、@SessionAttributes

@SessionAttributes即将值放到session作用域中，写在class上面。

具体示例参见下面：使用 @ModelAttribute 和 @SessionAttributes 传递和保存数据SpringMVC 支持使用 @ModelAttribute 和 @SessionAttributes 在不同的模型（model）和控制器之间共享数据。 @ModelAttribute 主要有两种使用方式，一种是标注在方法上，一种是标注在 Controller 方法参数上。

##### 4.2、@ModelAttribute

当 @ModelAttribute 标记在方法上的时候，`该方法将在处理器方法执行之前执行`，然后把返回的对象存放在 session 或模型属性中，属性名称可以使用 @ModelAttribute(“attributeName”) 在标记方法的时候指定，若未指定，则使用返回类型的类名称（首字母小写）作为属性名称。

#### 5、@PathVariable

用于将请求URL中的模板变量映射到功能处理方法的参数上，即取出uri模板中的变量作为参数。如：

```
@Controller
public class TestController {
    @RequestMapping(value="/user/{userId}/roles/{roleId}",method = RequestMethod.GET)
    public String getLogin(@PathVariable("userId") String userId,@PathVariable("roleId") String roleId){
        System.out.println("User Id : " + userId);
        System.out.println("Role Id : " + roleId);
        return "hello";
    }
    @RequestMapping(value="/product/{productId}",method = RequestMethod.GET)
    public String getProduct(@PathVariable("productId") String productId){
        System.out.println("Product Id : " + productId);
        return "hello";
    }
    <[By cnblogs.com/GoCircle]span class="hljs-meta">@RequestMapping(value="/javabeat/{regexp1:[a-z-]+}",
    method = RequestMethod.GET)
    public String getRegExp(@PathVariable("regexp1") String regexp1){
        System.out.println("URI Part 1 : " + regexp1);
        return "hello";
    }
}
```

#### 6、@RequestParam

@RequestParam主要用于在SpringMVC后台控制层获取参数，类似一种是request.getParameter("name")，它有三个常用参数：defaultValue = "0", required = false, value = "isApp"；defaultValue 表示设置默认值，required 通过boolean设置是否是必须要传入的参数，value 值表示接受的传入的参数类型。

```
public Resp test(@RequestParam(value="course_id") Integer id){
    return Resp.success(customerInfoService.fetch(id));
}
```

#### 7、@ResponseBody

Spring4后出现的注解。

作用： 该注解用于将Controller的方法返回的对象，用于构建RESTful的api，通过适当的HttpMessageConverter转换为指定格式后，写入到Response对象的body数据区。

使用时机：返回的数据不是html标签的页面，而是其他某种格式的数据时（如json、xml等）使用。

```
@RequestMapping(“/test”)
@ResponseBody
public String test(){
    return”ok”;
}
```

#### 8、@CrossOrigin

`@CrossOrigin`注解将为请求处理类或请求处理方法提供跨域调用支持。如果我们将此注解标注类，那么类中的所有方法都将获得支持跨域的能力。使用此注解的好处是可以微调跨域行为。

#### 9、@InitBinder

`@InitBinder`注解用于标注初始化WebDataBinider的方法，该方法用于对Http请求传递的表单数据进行处理，如时间格式化、字符串处理等。

```
@InitBinder
public void initBinder(WebDataBinder dataBinder){
    StringTrimmerEditor editor = new StringTrimmerEditor(true);
    dataBinder.registerCustomEditor(String.class,editor);
}
```

### 九、属性配置相关

#### 1、@Configuration

Spring3.0，相当于传统的xml配置文件，如果有些第三方库需要用到xml文件，建议仍然通过@Configuration类作为项目的配置主类可以使用@ImportResource注解加载xml配置文件。

`proxyBeanMethods`属性默认值是true,也就是说该配置类会被代理（CGLIB），在同一个配置文件中调用其它被@Bean注解标注的方法获取对象时会直接从IOC容器之中获取。

注意：@Configuration注解的配置类有如下要求：

1. @Configuration不可以是final类型；
2. @Configuration不可以是匿名类；
3. 嵌套的configuration必须是静态类。

```
@Configuration
public class AppConfig {
    // 未指定bean 的名称，默认采用的是 "方法名" + "首字母小写"的配置方式
    @Bean
    public MyBean myBean(){
        return new MyBean();
    }
}
```

#### 2、@ConfigurationProperties

- 前缀定义了哪些外部属性将绑定到类的字段上
- 根据 Spring Boot 宽松的绑定规则，类的属性名称必须与外部属性的名称匹配
- 我们可以简单地用一个值初始化一个字段来定义一个默认值
- 类本身可以是包私有的
- 类的字段必须有公共 setter 方法

激活 @ConfigurationProperties

- 只有当类所在的包被 Spring `@ComponentScan` 注解扫描到才会生效，默认情况下，该注解会扫描在主应用类下的所有包结构。

可以通过添加 @Component 注解让 Component Scan 扫描到。

```
@Component
@ConfigurationProperties(prefix = "demo")
class DemoProperties {
}
```

- 也可以通过 Spring 的 Java Configuration 特性实现同样的效果。

```
@Configuration
class PropertiesConfig {
    @Bean
    public DemoProperties demoProperties(){
        return new DemoProperties();
    }
}
```

- 使用 `@EnableConfigurationProperties` 注解让我们的类被 Spring Boot 所知道，在该注解中其实是用了`@Import(EnableConfigurationPropertiesImportSelector.class)` 实现

```
@Configuration
@EnableConfigurationProperties(DemoProperties.class)
class PropertiesConfig {
}
```

Spring Boot 内置支持从配置参数中解析 durations (持续时间)，[官网文档](https://docs.spring.io/spring-boot/docs/current/reference/html/boot-features-external-config.html#boot-features-external-config-conversion-duration) 给出了明确的说明。

配置 duration 不写单位，默认按照毫秒来指定，我们也可已通过 @DurationUnit 来指定单位。常用单位如下:

- ns for nanoseconds (纳秒)
- us for microseconds (微秒)
- ms for milliseconds (毫秒)
- s for seconds (秒)
- m for minutes (分)
- h for hours (时)
- d for days (天)

```
@Data
@ConfigurationProperties(prefix = "demo")
class DemoProperties {
    @DurarionUnit(ChronoUnit.SECONDS)
    private Duration timeout;
}
```

DataSize

与 Duration 的用法一毛一样，默认单位是 byte (字节)，可以通过 @DataSizeUnit 单位指定

但是，我测试的时候打印出来结果都是以 B (bytes) 来显示。常见单位如下

- `B` for bytes
- `KB` for kilobytes
- `MB` for megabytes
- `GB` for gigabytes
- `TB` for terabytes

 

#### 3、@Import

@Import注解是引入带有@Configuration的java类。

```
@Configuration
@Import({MyImportSelector.class, MyImportBeanDefinitionRegistrar.class, MyImportSelector1.class})
public static class Config {
    @Bean
    public BeanDemo0 beanDemo() {
        System.out.println("----beanDemo----");
        BeanDemo0 beanDemo0 = new BeanDemo0();
        beanDemo0.setId(1);
        beanDemo0.setName("123");
        return beanDemo0;
    }
}
```

#### 4、@ImportResource

@ImportResource是引入spring配置文件.xml

```
@Configuration
@ImportResource(locations = {"applicationContext.xml"})
public class BeanConfigTest</span> {
}
```

### 十、SpringCloud

#### 1、@EnableEurekaServer

用在springboot启动类上，表示这是一个eureka服务注册中心；

#### 2、@EnableDiscoveryClient

用在springboot启动类上，表示这是一个服务，可以被注册中心找到；

#### 3、@LoadBalanced

开启负载均衡能力；

#### 4、@EnableCircuitBreaker

用在启动类上，开启断路器功能；

#### 5、@HystrixCommand(fallbackMethod=”backMethod”)

用在方法上，fallbackMethod指定断路回调方法；

#### 6、@EnableConfigServer

用在启动类上，表示这是一个配置中心，开启Config Server；

#### 7、@EnableZuulProxy

开启zuul路由，用在启动类上；

#### 8、@SpringCloudApplication:

```
@SpringBootApplication`,`@EnableDiscovertyClient`,`@EnableCircuitBreaker
```

分别是SpringBoot注解、注册服务中心Eureka注解、断路器注解。对于SpringCloud来说，这是每一微服务必须应有的三个注解，所以才推出了@SpringCloudApplication这一注解集合。

### 十一、测试相关

#### 1、@ActiveProfiles

`@ActiveProfiles`一般作用于测试类上， 用于声明生效的 Spring 配置文件。

```
@SpringBootTest(webEnvironment = RANDOM_PORT)
@ActiveProfiles("test")
@Slf4j
public abstract class TestBase {
  ......
}
```

#### 2、@Test

`@Test`声明一个方法为测试方法

`@Transactional`被声明的测试方法的数据会回滚，避免污染测试数据。

#### 3、@WithMockUser

`@WithMockUser` Spring Security 提供的，用来模拟一个真实用户，并且可以赋予权限。

```
    @Test
    @Transactional
    @WithMockUser(username = "user-id-18163138155", authorities = "ROLE_TEACHER")
    void should_import_student_success() throws Exception {
        ......
    }
```