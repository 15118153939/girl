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
