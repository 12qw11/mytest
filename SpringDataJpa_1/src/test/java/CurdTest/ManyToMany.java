package CurdTest;

import com.demo.dao.RoleDao;
import com.demo.dao.UserDao;
import com.demo.entity.Role;
import com.demo.entity.Users;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:applicationContext.xml")
public class ManyToMany {

       //自动注入
       @Autowired
       UserDao  userDao;
       @Autowired
       RoleDao roleDao;
/**
 * 需求：
 * 	保存用户和角色
 * 要求：
 * 	创建2个用户和3个角色
 * 	让1号用户具有1号和2号角色(双向的)
 * 	让2号用户具有2号和3号角色(双向的)
 *  保存用户和角色
 * 问题：
 *  在保存时，会出现主键重复的错误，因为都是要往中间表中保存数据造成的。
 * 解决办法：
 * 	让任意一方放弃维护关联关系的权利
 */
@Test
@Transactional     // 开启事务
@Rollback(false)  // 不回滚
 public   void  testAdd(){

    //  创建  2个用户  3 个角色
    Users  users1 =  new Users();
    users1.setUsername("张三1");

    Role   role  =  new Role();
    role.setRoleName("角色1");


    //  建立关联关系
    users1.getRoles().add(role);
    role.getUsers().add(users1);

    // 保存
    roleDao.save(role);
    userDao.save(users1);

 }


 @Test
 @Transactional
 @Rollback(false)
  public  void  testDelete(){
    // 删除ID 为 6 的数据
       userDao.delete(6);
  }




}
