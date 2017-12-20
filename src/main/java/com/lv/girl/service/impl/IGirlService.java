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
