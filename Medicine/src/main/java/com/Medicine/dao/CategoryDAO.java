package com.Medicine.dao;

import com.Medicine.model.*;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CategoryDAO extends BaseDAO{
    String TABLE_NAME = "category";
    String INSET_FIELDS = " cname, createtime, description";
    String SELECT_FIELDS = "id, cname, createtime, description";
    @Select({"select ", SELECT_FIELDS, " from ", TABLE_NAME, " limit #{limit} offset #{offset}"})
    List<Category> selectByLimitAndOffset(Page page);

    @Update({"update ",TABLE_NAME," set cname=#{cname},description=#{description} where id=#{id}"})
    void updateInfo(Object object);

    @Delete({"delete from ", TABLE_NAME, " where id=#{id}"})
    void deleteById(int id);

    @Select({"select ", SELECT_FIELDS, " from ", TABLE_NAME, "where id like CONCAT(CONCAT('%', #{likeValue}),'%') or cname like CONCAT(CONCAT('%', #{likeValue}),'%') or description like CONCAT(CONCAT('%', #{likeValue}),'%') limit #{limit} offset #{offset}"})
    List<Category> selectByLikeValue(Page page);

    @Select({"select count(*) from ",TABLE_NAME})
    int selectCount();

    @Select({"select count(*) from ",TABLE_NAME," where id like CONCAT(CONCAT('%', #{likeValue}),'%') or cname like CONCAT(CONCAT('%', #{likeValue}),'%') or description like CONCAT(CONCAT('%', #{likeValue}),'%')"})
    int selectCountByValue(String likeValue);

    @Insert({"insert into ", TABLE_NAME, "(", INSET_FIELDS,
            ") values (#{cname},#{createtime},#{description})"})
    int addCategory(Object object);

    @Select({"select cname from ",TABLE_NAME})
    List<String> selectTypes();

    @Select({"select ",SELECT_FIELDS," from ",TABLE_NAME," where id=#{id}"})
    Category selectById(int id);

    @Select({"select ",SELECT_FIELDS," from ",TABLE_NAME," where cname=#{cname}"})
    Category selectByCname(String cname);

}
