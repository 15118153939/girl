# girl
传统属性配置：
application.properties
```
#端口
server.port=8081    
#项目
server.context-path=/girl
```
SpringBoot 建议使用.yml文件
```
server:
  context-path: /girl
  port: 8082
cupSize: F
age: 24
name: 王小花
content: "name: ${name},age: ${age},cupSize: ${cupSize}"
```
HelloController使用yml属性的配置，通过注解@Value("${属性名}")
```
package com.lv.girl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author lvmingliang_glut@163.com
 * @Date 2017/12/19 13:07
 * @Description
 **/

@RestController
public class HelloController {

    @Value("${name}")
    private String name;
    @Value("${age}")
    private Integer age;
    @Value("${cupSize}")
    private String cupSize;

    @Value("${content}")
    private String content;
    @RequestMapping
    public String say(){
        return "my'name is "+name +" age is "+age +" cupZize:" +cupSize +"--"+content;
    }
}

```
运行结果：
![运行结果](https://github.com/15118153939/girl/blob/springBoot-configuration/img/properties/p1.png)
