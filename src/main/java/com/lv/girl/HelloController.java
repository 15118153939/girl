package com.lv.girl;

import org.springframework.beans.factory.annotation.Autowired;
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


    @Autowired
    private GirlProperties girlProperties;

    @RequestMapping
    public String say(){
        return girlProperties.toString();
    }
}
