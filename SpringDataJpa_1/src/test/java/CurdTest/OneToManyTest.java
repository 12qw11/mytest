package CurdTest;

import com.demo.dao.CustomerDao;
import com.demo.dao.LinkManDao;
import com.demo.entity.Customer;
import com.demo.entity.LinkMan;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:applicationContext.xml")
public class OneToManyTest {

     @Autowired
     private CustomerDao  customerDao;
    @Autowired
     private LinkManDao linkManDao;

    /**
     * 保存操作
     * 需求:
     * 	保存一个客户和一个联系人
     * 要求：
     * 	创建一个客户对象和一个联系人对象
     *  建立客户和联系人之间关联关系（双向一对多的关联关系）
     *  先保存客户，再保存联系人
     * 问题：
     *		当我们建立了双向的关联关系之后，先保存主表，再保存从表时：
     *		会产生2条insert和1条update.
     * 		而实际开发中我们只需要2条insert。
     *
     */

     @Test
    @Transactional // 开启事务
    @Rollback(false) // 设置不回滚
    public void  testAdd(){

    //  第一个创建 客户信息
        Customer  c  =  new Customer();
        c.setCustName("TBD云集中心");
        c.setCustLevel("VIP客户");
        c.setCustSource("网络");
        c.setCustIndustry("商业办公");
        c.setCustAddress("昌平区北七家镇");
        c.setCustPhone("010-84389340");

   // 创建联系人
        LinkMan  l =  new LinkMan();
        l.setLkmName("TBD联系人");
        l.setLkmGender("male");
        l.setLkmMobile("13811111111");
        l.setLkmPhone("010-34785348");
        l.setLkmEmail("98354834@qq.com");
        l.setLkmPosition("老师");
        l.setLkmMemo("还行吧");
        // 实现添加
        // 在客户实体类中包涵了一个联系人
        c.getLinkmans().add(l);
        l.setCustomer(c);
        // 调用方法 实现 添加
         //  知道主从顺序
         customerDao.save(c);
         linkManDao.save(l);
    }


    //  级联删除  直接删除
    @Test
    @Transactional // 开启事务
    @Rollback(false) // 设置不回滚
    public void testDelete(){
          //linkManDao.delete(3L);
          customerDao.delete(96L);
    }


    //级联添加
    @Test
    @Transactional // 开启事务
    @Rollback(false) // 设置不回滚
    public void  testAdd1(){
        //  第一个创建 客户信息
        Customer  c  =  new Customer();
        c.setCustName("TBD云集中心");
        c.setCustLevel("VIP客户");
        c.setCustSource("网络");
        c.setCustIndustry("商业办公");
        c.setCustAddress("昌平区北七家镇");
        c.setCustPhone("010-84389340");
        // 创建联系人
        LinkMan  l =  new LinkMan();
        l.setLkmName("TBD联系人");
        l.setLkmGender("male");
        l.setLkmMobile("13811111111");
        l.setLkmPhone("010-34785348");
        l.setLkmEmail("98354834@qq.com");
        l.setLkmPosition("老师");
        l.setLkmMemo("还行吧");
        // 实现添加
        // 在客户实体类中包涵了一个联系人
        l.setCustomer(c);
        c.getLinkmans().add(l);
        // 调用方法 实现 添加
        customerDao.save(c);
    }

}
