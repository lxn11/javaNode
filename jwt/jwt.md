<H1>JWT</H1>



# 目录

[TOC]





# 一、简介



## 1.1：什么是jwt



**JWT 全称  JSON WEB TOKEN**

JSON Web令牌（JWT）是一个开放标准（RFC 7519），它定义了一种紧凑且自包含的方式，用于在各方之间安全地将信息作为JSON对象传输。由于此信息是经过数字签名的，因此可以被验证和信任。可以使用秘密（使用HMAC算法）或使用RSA或ECDSA的公钥/私钥对对JWT进行签名。

尽管可以对JWT进行加密以在各方之间提供保密性，但我们将重点关注已签名的令牌。签名的令牌可以验证其中包含的声明的完整性，而加密的令牌则将这些声明隐藏在其他方的面前。当使用公钥/私钥对对令牌进行签名时，签名还证明只有持有私钥的一方才是对其进行签名的一方。



## 1.2：官网



https://jwt.io/introduction



## 1.3：什么时候应该使用JSON Web令牌？



以下是JSON Web令牌有用的一些情况：

- **授权**：这是使用JWT的最常见方案。用户登录后，每个后续请求都将包含JWT，从而允许用户访问该令牌允许的路由，服务和资源。单一登录是当今广泛使用JWT的一项功能，因为它的开销很小并且可以在不同的域中轻松使用。
- **信息交换**：JSON Web令牌是在各方之间安全地传输信息的一种好方法。因为可以对JWT进行签名（例如，使用公钥/私钥对），所以您可以确保发件人是他们所说的人。此外，由于签名是使用标头和有效负载计算的，因此您还可以验证内容是否未被篡改。



# 二、传统认证



## 2.1：Session



我们知道，http协议本身是一种无状态的协议，而这就意味着如果用户向我们的应用提供了用户名和密码来进行用户认证，那么下一次请求时，用户还要再一次进行用户认证才行，因为根据http协议，我们并不能知道是哪个用户发出的请求，所以为了让我们的应用能识别是哪个用户发出的请求，我们只能在服务器存储一份用户登录的信息，这份登录信息会在响应时传递给浏览器，告诉其保存为cookie,以便下次请求时发送给我们的应用，这样我们的应用就能识别请求来自哪个用户了,这就是传统的基于session认证。

==但是这种基于session的认证使应用本身很难得到扩展，随着不同客户端用户的增加，独立的服务器已无法承载更多的用户，而这时候基于session认证应用的问题就会暴露出来.==



<img src="images/image-20210521173750133.png" alt="image-20210521173750133" style="zoom:80%;" />



**基于session认证所显露的问题**

+ Session: 每个用户经过我们的应用认证之后，我们的应用都要在服务端做一次记录，以方便用户下次请求的鉴别，通常而言session都是保存在内存中，而随着认证用户的增多，服务端的开销会明显增大。

+ 扩展性: 用户认证之后，服务端做认证记录，如果认证的记录被保存在内存中的话，这意味着用户下次请求还必须要请求在这台服务器上,这样才能拿到授权的资源，这样在分布式的应用上，相应的限制了负载均衡器的能力。这也意味着限制了应用的扩展能力。

+ CSRF: 因为是基于cookie来进行用户识别的, cookie如果被截获，用户就会很容易受到跨站请求伪造的攻击。





## 2.2：Token



Token是服务器签发的一串加密字符串，是为了给客户端重复访问的一个令牌，作用是为了证明请求者（客户端）的身份，保持用户长期保持登录状态。

基于token的鉴权机制类似于http协议也是无状态的，它不需要在服务端去保留用户的认证信息或者会话信息。这就意味着基于token认证机制的应用不需要去考虑用户在哪一台服务器登录了，这就为应用的扩展提供了便利。

流程上是这样的：

- 用户使用用户名密码来请求服务器
- 服务器进行验证用户的信息
- 服务器通过验证发送给用户一个token
- 客户端存储token，并在每次请求时附送上这个token值
- 服务端验证token值，并返回数据

这个token必须要在每次请求时传递给服务端，它应该保存在请求头里， 另外，服务端要支持`CORS(跨来源资源共享)`策略，一般我们在服务端这么做就可以了`Access-Control-Allow-Origin: *`。

那么我们现在回到JWT的主题上。

<img src="images/image-20210521174601991.png" alt="image-20210521174601991" style="zoom:80%;" />





# 三、JWT组成



## 3.1：JWT令牌的结构



JSON Web令牌以紧凑的形式由三部分组成，这些部分由点（`.`）分隔，分别是：

+ head（头部）
+ payload（载荷）
+ signature （签证）

第一部分我们称它为头部（header),第二部分我们称其为载荷（payload, 类似于飞机上承载的物品)，第三部分是签证（signature).



因此，JWT通常如下所示:



```java
xxxxx.yyyyy.zzzzz
```



JWT案例：



```basic
eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiYWRtaW4iOnRydWV9.TJVA95OrM7E2cBab30RMHrHDcEfxjoYZgeFONFh7HgQ
```





## 3.2：head（头部）



jwt的头部承载两部分信息：

- 声明类型，这里是jwt
- 声明加密的算法 通常直接使用 HMAC SHA256

完整的头部就像下面这样的JSON：



```json
{
  'typ': 'JWT',
  'alg': 'HS256'
}
```



然后将头部进行base64加密（该加密是可以对称解密的),构成了第一部分.



```java
eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9
```



## 3.3：payload（载荷）



载荷就是存放有效信息的地方。这个名字像是特指飞机上承载的货品，这些有效信息包含三个部分

- **标准中注册的声明**
- **公共的声明**
- **私有的声明**



<h4>标准中注册的声明 (建议但不强制使用) </h4>

| name | message                                                      |
| ---- | ------------------------------------------------------------ |
| iss  | jwt签发者                                                    |
| sub  | jwt所面向的用户                                              |
| aud  | 接收jwt的一方                                                |
| exp  | jwt的过期时间，这个过期时间必须要大于签发时间                |
| nbf  | 定义在什么时间之前，该jwt都是不可用的.                       |
| iat  | jwt的签发时间                                                |
| jti  | jwt的唯一身份标识，主要用来作为一次性token,从而回避重放攻击。 |



<h4>公共的声明 </h4>



> 公共的声明可以添加任何的信息，一般添加用户的相关信息或其他业务需要的必要信息.但不建议添加敏感信息，因为该部分在客户端可解密.



<h4>私有的声明 </h4>



> 私有声明是提供者和消费者所共同定义的声明，一般不建议存放敏感信息，因为base64是对称解密的，意味着该部分信息可以归类为明文信息。



<h4>定义一个payload</h4>



```json
{
  "sub": "1234567890",
  "name": "John Doe",
  "admin": true
}
```



然后将其进行base64加密，得到JWT的第二部分。



```javascript
eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiYWRtaW4iOnRydWV9
```



## 3.4：signature（签证）



JWT的第三部分是一个签证信息，这个签证信息由三部分组成：

- header (base64后的)
- payload (base64后的)
- secret

这个部分需要base64加密后的header和base64加密后的payload使用`.`连接组成的字符串，然后通过header中声明的加密方式进行加盐`secret`组合加密，然后就构成了jwt的第三部分。



```javascript
// javascript
var encodedString = base64UrlEncode(header) + '.' + base64UrlEncode(payload);

var signature = HMACSHA256(encodedString, 'secret'); // TJVA95OrM7E2cBab30RMHrHDcEfxjoYZgeFONFh7HgQ
```



将这三部分用`.`连接成一个完整的字符串,构成了最终的jwt:



```java
eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiYWRtaW4iOnRydWV9.TJVA95OrM7E2cBab30RMHrHDcEfxjoYZgeFONFh7HgQ
```



注意：<font color='red'>**secret是保存在服务器端的，jwt的签发生成也是在服务器端的，**</font>secret就是用来进行jwt的签发和jwt的验证，所以，它就是你**服务端的私钥**，在任何场景都不应该流露出去。

<font color='blue'>**一旦客户端得知这个secret, 那就意味着客户端是可以自我签发jwt了。**</font>



# 四、案例、基础

## 4.1：pom.xml



```xml
<!-- https://mvnrepository.com/artifact/com.auth0/java-jwt -->
<dependency>
    <groupId>com.auth0</groupId>
    <artifactId>java-jwt</artifactId>
    <version>3.16.0</version>
</dependency>
```



## 4.2：获取/解析JWT





```java
 @Test
    public void getJwt() {
        Calendar instance = Calendar.getInstance();
        instance.add(Calendar.SECOND, 20000);//200000秒失效
        Map<String, Object> header = new HashMap<>();
        header.put("typ","JWT");
        header.put("alg","HS256");
        String token = JWT.create()
                .withHeader(header)//header
                .withClaim("userId", "1111")//payload
                .withClaim("name", "admin")
                .withExpiresAt(instance.getTime())//指定令牌的过期时间
                .sign(Algorithm.HMAC256("!@#$%^&*"));//签名
        System.out.println(token);
    }


    @Test
    public void jwt(){
        JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256("!@#$%^&*")).build();
        DecodedJWT verify = jwtVerifier.verify("eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJuYW1lIjoiYWRtaW4iLCJleHAiOjE2MjE5NTIwMDcsInVzZXJJZCI6IjExMTEifQ.FgRk_ua5PdyOTQJZOjyiUkgSFcfq0onHvUvXw_YcDEw");
        String header = verify.getHeader();//头
        String payload = verify.getPayload();//payload
        String signature = verify.getSignature();//签名
        String token = verify.getToken();//token
        String userId = verify.getClaim("userId").asString();
        String name = verify.getClaim("name").asString();
        Date expiresAt = verify.getExpiresAt();//过期时间
        List<String> audience = verify.getAudience();
        System.out.println(header);
        System.out.println(payload);
        System.out.println(signature);
        System.out.println(token);
        System.out.println(userId);
        System.out.println(name);
        System.out.println(expiresAt);
        System.out.println(audience);
    }
```



## 4.3：常见异常信息



| name                           | message           |
| ------------------------------ | ----------------- |
| SignatureVerificationException | 签名不一致异常    |
| TokenExpiredException          | jwt过期异常       |
| AlgorithmMismatchException     | 算法不匹配异常    |
| InvalidClaimException          | 失效的payload异常 |



## 4.4：JWT封装工具类



```java
public class JWTUtils {
    private static String TOKEN = "token!Q@W3e4r";
    /**
     * 生成token
     *
     * @param map //传入payload
     * @return 返回token
     */
    public static String getToken(Map<String, String> map) {
        JWTCreator.Builder builder = JWT.create();
        map.forEach((k, v) -> {
            builder.withClaim(k, v);
        });
        Calendar instance = Calendar.getInstance();
        instance.add(Calendar.SECOND, 7);
        builder.withExpiresAt(instance.getTime());
        return builder.sign(Algorithm.HMAC256(TOKEN));
    }
    /**
     * 验证token
     *
     * @param token
     * @return
     */
    public static void verify(String token) {
        JWT.require(Algorithm.HMAC256(TOKEN)).build().verify(token);  // 如果验证通过，则不会把报错，否则会报错
    }
    /**
     * 获取token中payload
     *
     * @param token
     * @return
     */
    public static DecodedJWT getToken(String token) {
        return JWT.require(Algorithm.HMAC256(TOKEN)).build().verify(token);
    }
}
```



























