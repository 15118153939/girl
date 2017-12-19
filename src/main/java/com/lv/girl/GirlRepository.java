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
