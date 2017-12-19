# girl
多环境配置及启动启动：
application.yml
```
spring:
  profiles:
    active: prod
```
application-dev.yml
```
server:
  port: 8080
  context-path: /girl
girl:
  name: 王本地
  age: 15
  cupSize: F
```
```
server:
  port: 8082
  context-path: /girl
girl:
  name: 王生产
  age: 22
  cupSize: C
```
GirlProperties 属性配置需要添加注解：@ConfigurationProperties(prefix = "girl")，@Component让spring容器管理
```
package com.lv.girl;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @Author lvmingliang_glut@163.com
 * @Date 2017/12/19 14:11
 * @Description
 **/
@Component
@ConfigurationProperties(prefix = "girl")
public class GirlProperties {
    private String name;
    private String cupSize;
    private Integer age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCupSize() {
        return cupSize;
    }

    public void setCupSize(String cupSize) {
        this.cupSize = cupSize;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "GirlProperties{" +
                "name='" + name + '\'' +
                ", cupSize='" + cupSize + '\'' +
                ", age=" + age +
                '}';
    }
}

```
多环境启动：
一个是idea 直接启动 启动文件是prod
一个是：com命令行启动：
步骤如下：
![cmd启动步骤1：](https://github.com/15118153939/girl/blob/springBoot-more-configuration/img/pro/runstep1.png)
![cmd启动步骤2：](https://github.com/15118153939/girl/blob/springBoot-more-configuration/img/pro/runstep2.png)
运行结果：
![cmd启动步骤2：](https://github.com/15118153939/girl/blob/springBoot-more-configuration/img/pro/runresult-dev.png)
![cmd启动步骤2：](https://github.com/15118153939/girl/blob/springBoot-more-configuration/img/pro/runresult-prod.png)

