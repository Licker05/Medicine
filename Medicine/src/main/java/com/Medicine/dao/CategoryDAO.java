package com.Medicine.dao;

import com.Medicine.model.Category;
import com.Medicine.model.LoginTicket;
import com.Medicine.model.Page;
import com.Medicine.model.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CategoryDAO {
    String TABLE_NAME = "category";
    String INSET_FIELDS = " cname, createtime, description";
    String SELECT_FIELDS = "id, cname, createtime, description";
    @Select({"select ", SELECT_FIELDS, " from ", TABLE_NAME, " limit #{limit} offset #{offset}"})
    List<Category> selectByLimitAndOffset(Page page);

    @Update({"update ",TABLE_NAME," set cname=#{cname},description=#{description} where id=#{id}"})
    void updateNameAndDesc(Category category);

    @Delete({"delete from ", TABLE_NAME, " where id=#{id}"})
    void deleteById(int id);

    @Select({"select ", SELECT_FIELDS, " from ", TABLE_NAME, "where id like #{likeValue} or cname like #{likeValue} or description like #{likeValue} limit #{limit} offset #{offset}"})
    List<Category> selectByLikeValue(Page page);

    @Insert({"insert into ", TABLE_NAME, "(", INSET_FIELDS,
            ") values (#{cname},#{createtime},#{description})"})
    int addCategory(Category category);
}
