package com.mapper;

import com.entity.Userboot;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserMapper {
    @Insert("INSERT INTO USERSBOOT ( firstname,lastname, email,password,age ,phone ) VALUES ( #{user.firstname}, #{user.lastname},#{user.email},#{user.password}, #{user.age},#{user.phone})")
    Integer insertUser(@Param("user") Userboot user) throws Exception;

    @Select("select * from USERSBOOT where email = #{email}")
    Userboot findByEmail(@Param("email") String email);

    @Select("select * from USERSBOOT where email = #{email} AND id!=#{id}")
    Userboot findByEmailNotUser(@Param("email") String email,@Param("id") Integer id);

    @Select("select * from USERSBOOT where id = #{id}")
    Userboot findById(@Param("id") Integer id);

    @Select("select * from USERSBOOT where email = #{email} AND password = #{password}")
    Userboot login(@Param("email") String email,@Param("password") String password);


    @Select("select * from USERSBOOT")
    List<Userboot> getUsers();

    @Update("UPDATE USERSBOOT SET firstname = #{user.firstname},lastname = #{user.lastname},email = #{user.email},phone = #{user.phone},age = #{user.age} WHERE id = #{user.id}")
    Integer updateUser(@Param("user") Userboot user) throws Exception;

    @Delete("DELETE from USERSBOOT where id = #{id}")
    Integer deleteById(@Param("id") Integer id);



}