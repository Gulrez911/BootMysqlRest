package com.kgate.demo.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.kgate.demo.entity.User;

public interface UserDao extends JpaRepository<User, Long>{

	@Query(value = "select firstName from User where firstName =?1")
	List<User>  findByfirstName(String firstName);
}
