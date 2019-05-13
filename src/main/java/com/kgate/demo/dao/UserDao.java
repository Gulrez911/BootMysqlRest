package com.kgate.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kgate.demo.entity.User;

public interface UserDao extends JpaRepository<User, Long>{

}
