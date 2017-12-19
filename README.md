#SpringBoot事物操作

事务有四个特性：ACID

原子性（Atomicity）：事务是一个原子操作，由一系列动作组成。事务的原子性确保动作要么全部完成，要么完全不起作用。
一致性（Consistency）：一旦事务完成（不管成功还是失败），系统必须确保它所建模的业务处于一致的状态，而不会是部分完成部分失败。在现实中的数据不应该被破坏。
隔离性（Isolation）：可能有许多事务会同时处理相同的数据，因此每个事务都应该与其他事务隔离开来，防止数据损坏。
持久性（Durability）：一旦事务完成，无论发生什么系统错误，它的结果都不应该受到影响，这样就能从任何系统崩溃中恢复过来。通常情况下，事务的结果被写到持久化存储器中。

本案例是添加2个信息入库：
```
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
```
