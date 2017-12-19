package com.lv.girl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Author lvmingliang_glut@163.com
 * @Date 2017/12/19 16:24
 * @Description
 **/
@Service
public class GirlService {
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
}
