# smart admin

## 功能列表

- 日志管理
- 菜单管理
- 用户管理
- 角色管理
- 组织管理
- 权限管理

## 通用功能

### 权限校验

默认所有接口增加登录校验，如果需要忽略校验，使用注解`@AuthIgnore`

### 日志

使用注解，log会记录到数据库中，如果需要方法参数`params = true`

```
@SysLog(operation = "新增菜单", params = true)
```

数据库会记录操作人，操作方法，方法耗时，操作IP,方法参数等

### 集成mybatisPlus一键代码生成

//TODO
