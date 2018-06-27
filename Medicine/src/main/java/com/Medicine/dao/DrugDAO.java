package com.Medicine.dao;

import com.Medicine.model.Category;
import com.Medicine.model.Drug;
import com.Medicine.model.Page;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface DrugDAO {
    String TABLE_NAME = "drug";
    String INSET_FIELDS = " drugnumber, drugname, drugPice,quantity,productdate,producer,categoryname";
    String SELECT_FIELDS = "drugid, drugnumber, drugname, drugPice , quantity , productdate , producer , categoryname";

    @Select({"select ", SELECT_FIELDS, " from ", TABLE_NAME, " limit #{limit} offset #{offset}"})
    List<Drug> selectByLimitAndOffset(Page page);

    @Select({"select ", SELECT_FIELDS, " from ", TABLE_NAME, "where drugid like #{likeValue} or drugname like #{likeValue} limit #{limit} offset #{offset}"})
    List<Drug> selectByLikeValue(Page page);

    @Select({"select count(*) from ",TABLE_NAME," where drugid like #{likeValue} or drugname like #{likeValue}"})
    int selectCountByValue(String likeValue);

    @Select({"select count(*) from ",TABLE_NAME})
    int selectCount();

    @Delete({"delete from ", TABLE_NAME, " where id=#{id}"})
    void deleteById(int id);

    @Insert({"insert into ", TABLE_NAME, "(", INSET_FIELDS,
            ") values (#{drugnumber},#{drugname},#{drugPice},#{quantity},#{productdate},#{producer},#{categoryname})"})
    int addDrug(Object object);
}
