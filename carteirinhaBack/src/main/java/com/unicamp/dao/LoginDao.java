package com.unicamp.dao;

import org.springframework.data.repository.CrudRepository;
import com.unicamp.entity.Login;

public interface LoginDao extends CrudRepository<Login, Integer> {
    
}
