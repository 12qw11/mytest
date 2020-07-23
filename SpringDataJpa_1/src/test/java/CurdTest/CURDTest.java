package CurdTest;

import com.demo.dao.CustomerDao;
import com.demo.entity.Customer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:applicationContext.xml") // 在执行某些方法之前加载当前的配置文件
public class CURDTest {

      @Autowired
      private CustomerDao customerDao;

    // 保存撒范德萨范德萨发达
    @Test
     public  void  testSave(){
         Customer customer  =  new Customer();
         customer.setCustName("张全蛋");

         //  保存
         customerDao.save(customer);

     }


     // 修改
    /**
     * 修改客户：调用save(obj)方法
     *      对于save方法的解释：如果执行此方法是对象中存在id属性，即为更新操作会先根据id查询，再更新
     *                      如果执行此方法中对象中不存在id属性，即为保存操作
     *
     */
    @Test
    public void testUpdata(){
           // 根据 id获取 对象
           Customer customer  =  customerDao.findOne(4L);
          //  修改对象的信息
          customer.setCustAddress("惠州xxx");
          customer.setCustLevel("12");
          // 修改
          customerDao.save(customer);

    }

    // 删除
    @Test
    public  void   testDelete(){
            customerDao.delete(2L);
    }


    // 查询全部
    @Test
    public  void  testAll(){

        List<Customer> list= customerDao.findAll();
        System.out.println(list);
    }

    @Test
    public  void testOne(){
          Customer  customer  = customerDao.findName("张全蛋");
          System.out.println(customer);
    }

     @Test
     @Transactional   // 做修改操作
    public void  testUpdate(){
         customerDao.updataC(3L,"sdfdsf");
    }

    @Test
    public void findSQL(){
         List<Customer> list= customerDao.findSql();
        System.out.println(list);
    }

    // 测试 方法规则查询
    @Test
    public  void  testffgz(){
        // Customer customer =  customerDao.findByCustName("sdfdsf");
        //System.out.println(customer);
        List<Customer> list  =  customerDao.findByCustLevelBetween("1","3");
        System.out.println(list);
    }

    //
    @Test
    public  void  testSpecifications(){

        //使用匿名内部类 创建一个Spectification 条件构造器
        Specification<Customer> spec = new Specification<Customer>() {
            /**
             *	root	：Root接口，代表查询的根对象，可以通过root获取实体中的属性
             *	criteriaQuery	：代表一个顶层查询对象，用来自定义查询
             *	criteriaBuilder		：用来构建查询，此对象里有很多条件方法
             **/
            public Predicate toPredicate(Root<Customer> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                // from Cusetomer where  custName like  ?
                return criteriaBuilder.like(root.get("custName").as(String.class),"w%");
            }
        };
         // 调用 查询
          List<Customer> customer= customerDao.findAll(spec);

          System.out.println(customer);

    }




}
