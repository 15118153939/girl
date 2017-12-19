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
