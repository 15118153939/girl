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
