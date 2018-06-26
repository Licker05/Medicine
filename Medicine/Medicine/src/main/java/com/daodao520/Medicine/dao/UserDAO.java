package com.daodao520.Medicine.dao;

import com.LResume.model.User;
import org.apache.ibatis.annotations.*;

@Mapper
public interface UserDAO {
    String TABLE_NAME = "user";
    String INSET_FIELDS = " name, password, salt, headurl , phone ,email";
    String SELECT_FIELDS = " id, name, password, salt, headurl,phone ,email";

    @Insert({"insert into ", TABLE_NAME, "(", INSET_FIELDS,
            ") values (#{name},#{password},#{salt},#{headurl},#{phone},#{email})"})
    int addUser(User user);

    @Select({"select ", SELECT_FIELDS, " from ", TABLE_NAME, " where id=#{id}"})
    User selectById(int id);
    @Select({"select salt from ", TABLE_NAME, " where id=#{id}"})
    User selectSaltById(int id);

    @Select({"select ", SELECT_FIELDS, " from ", TABLE_NAME, " where name=#{name}"})
    User selectByName(String name);

    @Update({"update ", TABLE_NAME, " set password=#{password} where id=#{id}"})
    void updatePassword(User user);

    @Delete({"delete from ", TABLE_NAME, " where id=#{id}"})
    void deleteById(int id);

    @Update({"update ",TABLE_NAME," set headurl=#{headurl},phone=#{phone},email=#{email} where name=#{name}"})
    void updateUser(User user);
}
