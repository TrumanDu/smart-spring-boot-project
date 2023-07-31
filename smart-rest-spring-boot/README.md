# smart rest spring boot

## Quickstart

首先在pom文件中添加：

```xml

<dependencies>
    <dependency>
        <groupId>top.trumandu</groupId>
        <artifactId>smart-rest-spring-boot</artifactId>
        <version>0.0.2-SNAPSHOT</version>
    </dependency>
</dependencies>
<repositories>
<repository>
    <id>github</id>
    <url>https://raw.githubusercontent.com/trumandu/maven-repository/main/repository</url>
    <snapshots>
        <enabled>true</enabled>
        <updatePolicy>always</updatePolicy>
    </snapshots>
</repository>
</repositories>
```

## Feature

### 统一结果

默认所有的返回值都会格式化为以下格式，data为方法返回数据

```
//正常结果
{
    "code": 200,
    "message": "OK",
    "data": "hello"
}
//异常结果
{
    "code": 500,
    "message": "Internal Server Error"
}
```

如果接口有特殊要求，可以使用注解`@IgnoreRestResult`取消强制转换。

### 统一异常

默认拦截部分常见异常：

- globalExceptionHandler 未知异常使用这个作为兜底方案
- MethodArgumentNotValidException 参数校验异常
- BusinessException 自定义业务异常
- NoHandlerFoundException
- HttpRequestMethodNotSupportedException
- HttpMediaTypeNotSupportedException
- HttpMessageNotReadableException

### 参数校验

使用参数`@Valid`

``` 
@PostMapping("/hello/info")
    public Response info(@RequestBody @Valid Hello hello) {
        return Response.ok().message("hello world!");
    }
    
public class Hello {
    @NotNull
    @Size(min = 1, max = 10, message = "info length must in 1-10")
    private String info;
    @NotEmpty(message = "name must not be empty.")
    private String name;

    @NotNull(message = "length 不能为null.")
    @Min(1)
    @Max(12)
    private int length;
}    
```

除此以外还可以通过`Assert`断言才对于一些参数校验

### 统一配置

#### 支持跨越

#### rest api增加前缀

RestController 类下的api默认增加`/api`前缀

### 日志

### 常用工具

- Assert
- EnumCache
- BeanUtil
- IpUtils
- SmartDigestUtils

