package com.springboot.mybatis;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import com.springboot.bean.User;


@Mapper
public interface UserMapper {
	
	/*根据ID查询用户*/
	@Select("SELECT * FROM T_USER WHERE ID = #{id}")
	User findById(@Param("id")String id);
	
	/*新增用户*/
	@Insert("INSERT INTO T_USER(NAME,AGE,ADDRESS,PHONE) VALUES(#{name},#{age},#{address},#{phone})")
	int insert(@Param("name")String name,@Param("age")int age,
			@Param("address")String address,@Param("phone")String phone);;
	
	@Select("SELECT * FROM T_USER")
	List<User>	findAll();
}
