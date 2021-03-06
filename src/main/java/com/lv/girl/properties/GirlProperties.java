package com.lv.girl.properties;

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
