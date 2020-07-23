package com.demo.dao;

import com.demo.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * JpaRepository<实体类类型，主键类型>：用来完成基本CRUD操作
 * JpaSpecificationExecutor<实体类类型>：用于复杂查询（分页等查询操作）
 */
public interface CustomerDao extends JpaRepository<Customer,Long>
                                   , JpaSpecificationExecutor<Customer> {
    // 使用 jpql 的方式查询
    // 查询全部
    @Query(value = "from  Customer ")
    public List<Customer> findAll();
    //根据条件查询  ?1 代表的仕占位符   1 代表参数的下标
    @Query(value = "from  Customer  where  custName=?1")
    public Customer  findName(String custName);
    @Modifying // 用 @Modifying 来将该操作标识为修改查询，这样框架最终会生成一个更新的操作，而非查询
    @Query(value = "update Customer set custName = ?2 where custId = ?1")
    public  void  updataC(Long custId,String custName);

   // 使用sql 进行 查询
    //                                          nativeQuery : 使用本地sql的方式查询
    @Query(value = "select * from cst_customer",nativeQuery = true)
    public List<Customer>   findSql();

    // 方法命名规则查询
    public Customer findByCustName(String name);

    public List<Customer> findByCustLevelBetween(String num1 ,String num2);

}
