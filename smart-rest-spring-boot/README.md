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

### 统一异常

### 统一日志

### 常用工具
- EnumCache
- BeanUtil
- IpUtils
- SmartDigestUtils

### 统一配置