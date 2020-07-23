package com.demo.dao;

import com.demo.entity.LinkMan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface LinkManDao extends JpaRepository<LinkMan,Long>
                      , JpaSpecificationExecutor<LinkMan> {
}
