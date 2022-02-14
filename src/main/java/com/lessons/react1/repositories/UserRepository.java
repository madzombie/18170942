package com.lessons.react1.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.lessons.react1.jsonEntity.User;

public interface UserRepository extends JpaRepository<User,Long>{
	
	@Query(value="select * from User where username =:username",nativeQuery=true)
	User getUser(String username);

	@Query(value="select count(*) from User where username =:username and password=:password",nativeQuery=true)
	int getCred(String username,String password);
}
