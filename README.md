# SpringBoot -girl
springBoot 数据库操作：
配置mysql数据库属性：
datasource:数据库连接属性，jpa 
:ddl-auto有几种属性：
create：
每次加载hibernate时都会删除上一次的生成的表，然后根据你的model类再重新来生成新表，哪怕两次没有任何改变也要这样执行，这就是导致数据库表数据丢失的一个重要原因。
create-drop ：
每次加载hibernate时根据model类生成表，但是sessionFactory一关闭,表就自动删除。
update：
最常用的属性，第一次加载hibernate时根据model类会自动建立起表的结构（前提是先建立好数据库），以后加载hibernate时根据 model类自动更新表结构，即使表结构改变了但表中的行仍然存在不会删除以前的行。要注意的是当部署到服务器后，表结构是不会被马上建立起来的，是要等 应用第一次运行起来后才会。
validate ：
每次加载hibernate时，验证创建数据库表结构，只会和数据库中的表进行比较，不会创建新表，但是会插入新值。

再说点“废话”：
当我们把hibernate.hbm2ddl.auto=create时hibernate先用hbm2ddl来生成数据库schema。
当我们把hibernate.cfg.xml文件中hbm2ddl属性注释掉，这样我们就取消了在启动时用hbm2ddl来生成数据库schema。通常 只有在不断重复进行单元测试的时候才需要打开它，但再次运行hbm2ddl会把你保存的一切都删除掉（drop）---- create配置的含义是：“在创建SessionFactory的时候，从scema中drop掉所以的表，再重新创建它们”。
注意，很多Hibernate新手在这一步会失败，我们不时看到关于Table not found错误信息的提问。但是，只要你根据上面描述的步骤来执行，就不会有这个问题，因为hbm2ddl会在第一次运行的时候创建数据库schema， 后续的应用程序重启后还能继续使用这个schema。假若你修改了映射，或者修改了数据库schema,你必须把hbm2ddl重新打开一次。

```
spring:
  profiles:
    active: prod
  datasource:
    driver-class-name: com.mysql.jdbc.Driver
    url: jdbc:mysql://127.0.0.1:3306/dbgirl
    username: root
    password: Mingliang520
  jpa:
    hibernate:
      ddl-auto: update
    show-sql: true  


```
Java实体类：Girl  添加注解：@Entity 自动在数据库创建表等
```
package com.lv.girl;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

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
                '}';
    }
}

```

```
package com.lv.girl;

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
Controller业务比较简单，就不分层了，后续继续分层处理：
restful设计：
![](https://github.com/15118153939/girl/blob/jpa-restful-controller/img/restful/restful.png)
```
package com.lv.girl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author lvmingliang_glut@163.com
 * @Date 2017/12/19 15:33
 * @Description
 **/
@RestController
public class GirlController {

    @Autowired
    private GirlRepository girlRepository;
    @GetMapping(value = "girls")
    public List<Girl> girlList(){
        return girlRepository.findAll();
    }

    @PostMapping(value = "/girls")
    public Girl girlAdd(@RequestParam("cupSize") String cupSize,@RequestParam ("name") String name,
                          @RequestParam("age") Integer age){
        Girl girl = new Girl();
        girl.setName(name);
        girl.setCupSize(cupSize);
        girl.setAge(age);
        return girlRepository.save(girl);
    }
//    查询一个女生
    @GetMapping(value = "/girls/{id}")
    public Girl girlFindOne(@PathVariable("id") Integer id){
        return girlRepository.findOne(id);
    }
//    更新
    @PutMapping(value = "girls/{id}")
    public Girl girlUpdate(@PathVariable("id") @RequestParam("cupSize") String cupSize,@RequestParam ("name") String name,
                           @RequestParam("age") Integer age,@RequestParam("money") Double money){
       Girl girl = new Girl();
        girl.setName(name);
        girl.setCupSize(cupSize);
        girl.setAge(age);
        girl.setMoney(money);
        System.out.println(girl.toString()+"-----------");
      return   girlRepository.save(girl);
    }
//    public
    @DeleteMapping(value = "/girls/{id}")
    public void girlDelete(@PathVariable("id") Integer id){
        girlRepository.delete(id );
    }

//    通过年龄查询

    @GetMapping(value = "/girls/age/{age}")
    public List<Girl> girlListByAge(@PathVariable("age") Integer age){
        return girlRepository.findByAge(age);

    }
}

```

