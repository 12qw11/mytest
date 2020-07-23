package com.demo.dao;

import com.demo.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
public interface UserDao extends JpaRepository<Users,Integer>
        , JpaSpecificationExecutor<Users> {
}
