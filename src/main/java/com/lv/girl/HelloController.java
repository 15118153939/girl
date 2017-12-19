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
