# girl
![](https://github.com/15118153939/girl/blob/SpringBoot-web/img/srcs.png)
AOP处理请求，打印请求日志
```
package com.lv.girl.aspect;

import org.aopalliance.intercept.Joinpoint;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author lvmingliang_glut@163.com
 * @Date 2017/12/19 16:59
 * @Description
 **/
@Aspect
@Component
public class HttpAspect {

    private final static Logger logger = LoggerFactory.getLogger(HttpAspect.class);

    //    拦截所有
    @Pointcut("execution(public * com.lv.girl.controller.GirlController.*(..))")
    public void log() {
    }


    @Before("log()")
    public void doBefore(JoinPoint joinPoint) {
//        logger.info("---");

        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request =attributes.getRequest();
//        url
        logger.info("url={}",request.getRequestURL());
//        method
        logger.info("method={}",request.getMethod());
//        ip
        logger.info("ip={}",request.getRemoteAddr());
//        path
        logger.info("path={}",request.getServletPath());
        logger.info("LocalAddr={}",request.getLocalAddr());
//        类方法
        logger.info("class_method={}",joinPoint.getSignature().getDeclaringTypeName()+"."+joinPoint.getSignature().getName());
//    参数
        logger.info("args={}",joinPoint.getArgs());
    }

    @After("log()")
    public void doAfter() {

    }

    /**
     * 返回对象
     * @param object
     */
    @AfterReturning(returning = "object",pointcut = "log()")
    public void doAfterReturning(Object object){
        logger.info("response={}",object!=null?object.toString():object);
    }


}
```
```
package com.lv.girl.controller;

import com.lv.girl.aspect.HttpAspect;
import com.lv.girl.domain.Girl;
import com.lv.girl.domain.Result;
import com.lv.girl.repository.GirlRepository;
import com.lv.girl.service.GirlService;
import com.lv.girl.service.impl.IGirlService;
import com.lv.girl.utils.ResultUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @Author lvmingliang_glut@163.com
 * @Date 2017/12/19 15:33
 * @Description
 **/
@RestController
public class GirlController {

    private final static Logger logger = LoggerFactory.getLogger(GirlController.class);

    @Autowired
    private IGirlService girlService;


    @GetMapping(value = "girls")
    public Result<Girl> girlList() {
        logger.info("--girlList--");
        return ResultUtil.success(girlService.findAll());
    }


    @PostMapping(value = "/girls")
    public Result<Girl> girlAdd(@Valid Girl girl, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return ResultUtil.error(0, bindingResult.getFieldError().getDefaultMessage());
        }
        return ResultUtil.success(girlService.save(girl));
    }

    //    查询一个女生
    @GetMapping(value = "/girls/{id}")
    public Girl girlFindOne(@PathVariable("id") Integer id) throws Exception {

        return girlService.girlFindOne(id);

    }

    //    更新
    @PutMapping(value = "girls/{id}")
    public Girl girlUpdate(Girl girl) {
        System.out.println(girl.toString() + "-----------");
        return girlService.save(girl);
    }

    //    public
    @DeleteMapping(value = "/girls/{id}")
    public void girlDelete(@PathVariable("id") Integer id) {
        girlService.girlDelete(id);
    }

//    通过年龄查询

    @GetMapping(value = "/girls/age/{age}")
    public List<Girl> girlListByAge(@PathVariable("age") Integer age) {
        return girlService.findByAge(age);

    }

    @PostMapping(value = "/girls/two")
    public void girlTwo() {
        girlService.insertTwo();
    }

    @GetMapping(value = "/girls/getAge/{id}")
    public void getAge(@PathVariable("id") Integer id) throws Exception {

        girlService.getAge(id);
    }

}

```
domain包下：
Girl实体类
```
package com.lv.girl.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Min;

/**
 * @Author lvmingliang_glut@163.com
 * @Date 2017/12/19 15:25
 * @Description
 **/

@Entity
public class Girl {
    @Id
    @GeneratedValue
    private Integer id;
    private String name;
    private String cupSize;
    @Min(value = 18,message = "未成年少女不准如内")
    private Integer age;
    private double money;

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public Girl() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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
        return "Girl{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", cupSize='" + cupSize + '\'' +
                ", age=" + age +
                ", money=" + money +
                '}';
    }
}

```
Result：
```
package com.lv.girl.domain;

/**
 * @Author lvmingliang_glut@163.com
 * @Date 2017/12/19 20:15
 * @Description
 **/
public class Result<T> {
    private Integer code;
    private String msg;
    private T data;

    public Result() {
    }

    public Result(Integer code ,String msg) {
        this.code=code;
        this.msg=msg;
    }



    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Result{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }
}

```

enums包
```
package com.lv.girl.enums;

/**
 * @Author lvmingliang_glut@163.com
 * @Date 2017/12/19 21:54
 * @Description
 * 错误码全部用枚举来维护，比较方便
 **/
public enum ResultEnum {
    UNKNOW_ERROR(-1,"未知错误"),
    SUCCESS(0,"成功"),
    PRIMARY_SCHOOL(100,"你可能还在上小学！"),
    MIDDLE_SCHOOL(101,"你可能还在上初中！"),
    ;
    private Integer code;
    private String msg;

    ResultEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}

```
handle包：
```
package com.lv.girl.handle;

import com.lv.girl.domain.Result;
import com.lv.girl.utils.ResultUtil;
import exception.GirlException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author lvmingliang_glut@163.com
 * @Date 2017/12/19 21:36
 * @Description
 **/

@ControllerAdvice
public class ExceptionHandle {
    private final static Logger logger = LoggerFactory.getLogger(ExceptionHandle.class);


    @ExceptionHandler(value = Exception.class) //捕获exception
    @ResponseBody
    public Result handle(Exception e) {
//判断异常是不是自定义异常
        if (e instanceof GirlException) {
//        如果是的话
            GirlException girlException = (GirlException) e;
//        返回错误码的提示
            return ResultUtil.error(girlException.getCode(), girlException.getMessage());
        } else {
            logger.error("[系统异常]{}",e);
            return ResultUtil.error(-1, "未知错误！");
        }
    }
}

```

properties包
```
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

```
repository包
```
package com.lv.girl.repository;

import com.lv.girl.domain.Girl;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @Author lvmingliang_glut@163.com
 * @Date 2017/12/19 15:32
 * @Description
 **/
public interface GirlRepository extends JpaRepository<Girl,Integer> {

    //通过年龄来查询
    public List<Girl> findByAge(Integer age);
}

```
service.impl包
```
package com.lv.girl.service.impl;

import com.lv.girl.domain.Girl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author lvmingliang_glut@163.com
 * @Date 2017/12/19 23:07
 * @Description
 **/
@Service
public interface IGirlService {
    List<Girl> findByAge(Integer age);

    Girl save(Girl girl);

    List<Girl> findAll();

    void girlDelete(Integer id);

    Girl girlUpdate(Girl girl) throws Exception;

    Girl girlFindOne(Integer id) throws Exception;

    void getAge(Integer id)throws Exception;

    void insertTwo();
}

```
```
package com.lv.girl.service;

import com.lv.girl.controller.GirlController;
import com.lv.girl.enums.ResultEnum;
import com.lv.girl.repository.GirlRepository;
import com.lv.girl.domain.Girl;
import com.lv.girl.service.impl.IGirlService;
import exception.GirlException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author lvmingliang_glut@163.com
 * @Date 2017/12/19 16:24
 * @Description
 **/
@Service
public class GirlService implements IGirlService {
    private final static Logger logger = LoggerFactory.getLogger(GirlService.class);
    @Autowired
    private GirlRepository girlRepository;

    @Transactional
    public void insertTwo(){
        Girl girlA = new Girl();
        girlA.setName("王大花");
        girlA.setCupSize("F");
        girlA.setAge(15);
        girlA.setMoney(600);

        girlRepository.save(girlA);

        Girl girlB = new Girl();
        girlB.setName("王小花");
        girlB.setCupSize("FFFFFFF");//比如这个字段超出数据库的限制，这2个插入就都不执行
        girlB.setAge(15);
        girlB.setMoney(600);
        girlRepository.save(girlB);
    }

    /**
     * 单纯的测试 抛异常
     * @param id
     * @throws Exception
     */
    public void getAge(Integer id)throws Exception{
        Girl girl = girlRepository.findOne(id);

        Integer age = girl.getAge();
        if (age <10){
            throw  new GirlException(ResultEnum.PRIMARY_SCHOOL);
        }else if( age >10 && age<16){
            throw  new GirlException(ResultEnum.MIDDLE_SCHOOL );
        }else {

        }
    }

    /**
     *
     * @param id
     * @return
     * @throws Exception
     *
     * 案例：比如处理：根据ID查询，并且判断年龄大小，如果<16就抛异常
     */
    public Girl girlFindOne(Integer id) throws Exception{

//        可以這做逻辑处理咯

        Girl girl = girlRepository.findOne(id);
        Integer age = girl.getAge();
        if (age <10){
            throw  new GirlException(ResultEnum.PRIMARY_SCHOOL);
        }else if( age >10 && age<16){
            throw  new GirlException(ResultEnum.MIDDLE_SCHOOL );
        }
        return girl;
    }

    public Girl girlUpdate(Girl girl){
        logger.info("girlUpdate={}",girl.toString());
       return girlRepository.save(girl);
    }
    public void girlDelete(Integer id){
        logger.info("girldelete={}",id);
        girlRepository.delete(id);
    }

    public List<Girl> findAll(){

        return girlRepository.findAll();
    }

    public Girl save(Girl girl){
      return   girlRepository.save(girl);
    }

    public List<Girl> findByAge(Integer age){
        return  girlRepository.findByAge(age);
    }
}

```
utils包
```
package com.lv.girl.utils;

import com.lv.girl.domain.Result;

/**
 * @Author lvmingliang_glut@163.com
 * @Date 2017/12/19 20:19
 * @Description
 **/
public class ResultUtil {
    public static Result success(Object object) {
        Result result = new Result();
        result.setCode(0);
        result.setMsg("成功");
        result.setData(object);
        return result;
    }

    public static Result success(){
        return success(null);
    }

    public static Result error(Integer code, String msg) {
        Result result = new Result();
        result.setCode(0);
        result.setMsg(msg);
        return result;
    }
}

```

exception包
```
package exception;

import com.lv.girl.enums.ResultEnum;

/**
 * @Author lvmingliang_glut@163.com
 * @Date 2017/12/19 21:44
 * @Description
 **/
public class GirlException extends RuntimeException {
    private Integer code;


    public GirlException(ResultEnum resultEnum){
        super(resultEnum.getMsg());
        this.code=resultEnum.getCode();
    }

    public GirlException(Integer code,String message) {
        super(message);
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}

```
