# xxx-project

## 项目结构

```text
├─generator
│  ├─lib
│  ├─src
│  │  ├─main
│  │  │  ├─java
│  │  │  │  └─top
│  │  │  │      └─tanmw
│  │  │  │          └─generator
│  │  │  │              └─db
│  │  │  └─resources
│  │  │      └─templates
├─xxx-api
│  ├─src
│  │  ├─main
│  │  │  ├─java
│  │  │  │  └─com
│  │  │  │      └─zenith
│  │  │  │          └─xxx
│  │  │  │              └─api
│  │  │  └─resources
│  │  └─test
│  │      └─java
├─xxx-dao
│  ├─src
│  │  ├─main
│  │  │  ├─java
│  │  │  │  └─com
│  │  │  │      └─zenith
│  │  │  │          └─xxx
│  │  │  │              └─dao
│  │  │  └─resources
│  │  │      └─mapper
│  │  └─test
│  │      └─java
├─xxx-model
│  ├─src
│  │  ├─main
│  │  │  ├─java
│  │  │  │  └─com
│  │  │  │      └─zenith
│  │  │  │          └─xxx
│  │  │  │              ├─constant
│  │  │  │              └─model
│  │  │  │                  ├─converter
│  │  │  │                  ├─dto
│  │  │  │                  ├─entity
│  │  │  │                  └─vo
│  │  │  └─resources
│  │  └─test
│  │      └─java
├─xxx-service
│  ├─src
│  │  ├─main
│  │  │  ├─java
│  │  │  │  └─com
│  │  │  │      └─zenith
│  │  │  │          └─xxx
│  │  │  │              ├─service
│  │  │  │              │  └─base
│  │  │  │              └─util
│  │  │  └─resources
│  │  └─test
│  │      └─java
├─xxx-web
│  ├─src
│  │  ├─main
│  │  │  ├─java
│  │  │  │  └─com
│  │  │  │      └─zenith
│  │  │  │          └─xxx
│  │  │  │              ├─config
│  │  │  │              ├─controller
│  │  │  │              │  └─base
│  │  │  │              └─interceptor
│  │  │  └─resources
│  │  │      ├─static
│  │  │      └─templates
│  │  │          ├─excel
│  │  │          └─word
│  │  └─test
│  │      └─java
├─logs
└─sjr-common
    ├─lib
    ├─src
    │  ├─main
    │  │  ├─java
    │  │  │  └─com
    │  │  │      └─sjr
    │  │  │          └─common
    │  │  │              ├─annotation
    │  │  │              ├─constant
    │  │  │              ├─entity
    │  │  │              ├─log
    │  │  │              ├─permission
    │  │  │              ├─util
    │  │  │              └─validate
    │  │  └─resources
    │  └─test
    │      └─java
```

```text
xxx-web      -- 启动类，配置文件，controller层
xxx-api      -- 接口层
xxx-service  -- 服务实现,工具类
xxx-dao      -- mapper层
xxx-model    -- 项目实体、DTO、VO、Converter
sjr-common          -- 通用常量，工具类，注解等（此模块如要变更必须先通知项目负责人）
```

## 版本说明
```text
springboot(2.6.6) + mybatis-plus(3.5.1) + ehcache(2.10.9.2)
+ pgsql(11.4) + swagger(2.9)
```

## 代码生成器说明
```text
    1.启动类：top.tanmw.generator.Generator
    2.配置文件说明（generator/src/main/resources/generator.txt）：
    1).数据库配置
    url=jdbc:postgresql://192.168.0.245:5432/xxx?useUnicode=true&characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B8&nullCatalogMeansCurrent=true
    driver=org.postgresql.Driver
    user=postgres
    password=20191809
    
    2).必填项说明，未说明的采用默认配置即可
    ## 包含表名，多个 英文逗号分隔
    includeSet=sys_log
    ## 不包含表名，多个 英文逗号分隔
    excludeSet=
    ## 去掉指定前缀，如sys,sys_等,多个逗号分割
    excludePrefix=
    ## 是否替换覆盖现有文件，默认不会覆盖
    replace=true
    ## 生成文件,1-model,2-dto,3-listDto,4-VO,5-converter,6-mapper,7-dao,8-service,9-serviceimpl,10-controller
    ## 1-4,生成表示包含中间连续的类型，英文逗号包括分隔
    fileType=
```

## 代码规约
### Web层
```text
    1.所有接口都需要添加参数校验；
    2.关于需要使用参数分组校验的使用此报下的注解：sjr-common/src/main/java/com/sjr/common/validate
    3.get请求方式使用注解@RequestParam接收参数，禁止使用@PathVariable；
    4.返回结果统一使用com.sjr.common.entity.Result;
    5.基础返回枚举com.sjr.common.entity.ResultEnum
    6.业务指定返回枚举com.zenith.xxx.model.constant.CustomResultEnum
    7.返回结果的封转如无必要不要放在service层，将其放到controller层；
    8.controller禁止直接调用dao层；
    9.方法命名必须以此开头，新增（save,add，insert）,修改（update,change）,查询（find,get），列表查询（list,page），统计（count）,删除（del,delete，remove）
```

### Api层
```text
    1.单纯的接口层，不涉及实现
```
### Service层
```text
    1.涉及两个及以上的多表修改，必须添加事务注解@Transactional(rollbackFor=Throwable.class)
    2.DTO,VO,Entity之间转换，如无特殊需求，统一使用Converter进行转换；
    3.Page<Entity> 转 Page<VO> 统一使用  com.zenith.xxx.util.PageVoUtil.toVo
```
### Mapper层
```text
    1.注意sql编写规范；
```

### Model
```text
    1.实体类，常量类，VO,DTO,Converter集合
    2.Converter配置参见com.zenith.xxx.model.converter.SysLogConverter
    3.Converter使用参见com.zenith.xxx.service.SysLogServiceImpl
```
### Common
```text
    1.原则上该模块代码不会发生变动，如不满足开发需求请先通知相关负责人；
```

## 日志记录
```text
    1.在controller层方法上配置com.sjr.common.log.Log注解，AOP进行日志记录，该注解三个参数都需填写；
    2.optType说明接口操作类型com.sjr.common.log.OptTypeEnum，如枚举类不能满足要求，联系负责进行添加；
    3.module，操作模块取com.zenith.xxx.model.constant.CustomMenuEnum.name；
    4.desc赋值操作内容
    5.join是否拼接日志；
    6.默认日志拼接：用户名+optType+desc；如果join为false，日志只会拼接：用户名+desc；
    7.需要详细记录用户操作内容的，如“张三修改了李四的考核信息为优秀”，需直接调用方法进行保存日志：com.zenith.xxx.api.SysLogService.saveLog，
        com.zenith.xxx.api.SysLogService.saveLogNotJoin;如果这两个方法不能满足要求，可自定义实现；
```

## 权限校验
```text
    1.需要Controller层的方法或者类上添加注解com.sjr.common.permission.Permission；
    2.value代表菜单Code，取值com.zenith.xxx.model.constant.CustomMenuEnum.code；
    3.MenuRelation代表多个菜单之间的校验关系，如and代表用户必须拥有所有注解上配置的菜单权限才能访问，or代表用户只要有其中一个权限就能访问；
    4.Permission注解校验规则：就近原则，如方法和类上都有权限注解，以方法上的权限注解为准；
```

## 代码规约
```text
    1.项目接口文档采用Swagger，所有类注解，字段注解都要添加完善；
    2.所有类、方法都需添加注解；
    3.方法体内部涉及逻辑判断的必要注解必须添加；
    4.测试类无特殊需求统一写在web层的测试包下，包命名与业务类相同；
    5.项目统一使用的lombok简化 getter/setter 方法;
    6.DTO,VO,Converter类名必须以此结尾；
    7.用户信息获取通过com.sjr.common.util.RequestHolder;
    8.线程使用必须用com.sjr.common.util.ThreadUtil.EXECUTOR_SERVICE开线程；
    9.谨慎处理 @Transactional 事务注解的使用，不要简单对 service 的方法添加个 @Transactional 注解。
      应当合并对数据库的操作，尽量减少添加了@Transactional方法内的业务逻辑，@Transactional 注解内的 rollbackFor 值必须使用异常的基类 Throwable.class
    10.注解 @Transactional 事务在类的内部方法调用是不会生效的
    11.其余规范请参照《阿里巴巴 Java 开发手册》索引规约
```


