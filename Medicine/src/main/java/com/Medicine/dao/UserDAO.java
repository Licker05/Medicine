package com.Medicine.dao;

import com.Medicine.model.Drug;
import com.Medicine.model.Page;
import org.apache.ibatis.annotations.*;
import com.Medicine.model.User;

import java.util.List;

@Mapper
public interface UserDAO {
    String TABLE_NAME = "user";
    String INSET_FIELDS = " name, nickname,password, salt, headurl , phone ,email,level";
    String SELECT_FIELDS = " id, name, nickname , password, salt, headurl,phone ,email ,level";
    @Select({"select ", SELECT_FIELDS, " from ", TABLE_NAME, " limit #{limit} offset #{offset}"})
    List<User> selectByLimitAndOffset(Page page);

    @Select({"select ", SELECT_FIELDS, " from ", TABLE_NAME, " where name like CONCAT(CONCAT('%', #{likeValue}),'%') or nickname like CONCAT(CONCAT('%', #{likeValue}),'%') or phone like CONCAT(CONCAT('%', #{likeValue}),'%') limit #{limit} offset #{offset}"})
    List<User> selectByLikeValue(Page page);

    @Select({"select count(*) from ",TABLE_NAME," where name like CONCAT(CONCAT('%', #{likeValue}),'%') or nickname like CONCAT(CONCAT('%', #{likeValue}),'%') or phone like CONCAT(CONCAT('%', #{likeValue}),'%')"})
    int selectCountByValue(String likeValue);

    @Select({"select count(*) from ",TABLE_NAME})
    int selectCount();

    @Insert({"insert into ", TABLE_NAME, "(", INSET_FIELDS,
            ") values (#{name},#{nickname},#{password},#{salt},#{headurl},#{phone},#{email},#{level})"})
    int addUser(Object object);

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


    @Update({"update ",TABLE_NAME," set name=#{name},nickname=#{nickname},phone=#{phone},email=#{email},level=#{level}  where id=#{id}"})
    void updateInfo(Object object);
}
