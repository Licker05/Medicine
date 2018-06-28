package com.Medicine.dao;

import com.Medicine.model.Category;
import com.Medicine.model.Drug;
import com.Medicine.model.Page;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface DrugDAO extends BaseDAO{
    String TABLE_NAME = "drug";
    String INSET_FIELDS = " drugnumber, drugname, drugPice,quantity,productdate,producer,categoryname";
    String SELECT_FIELDS = "drugid, drugnumber, drugname, drugPice , quantity , productdate , producer , categoryname";

    @Update({"update ",TABLE_NAME," set drugnumber=#{drugnumber},drugname=#{drugname},drugPice=#{drugPice},quantity=#{quantity},productdate=#{productdate},producer=#{producer},categoryname=#{categoryname} where drugid=#{drugid}"})
    void updateInfo(Object object);

    @Select({"select ", SELECT_FIELDS, " from ", TABLE_NAME, " limit #{limit} offset #{offset}"})
    List<Drug> selectByLimitAndOffset(Page page);

    @Select({"select ", SELECT_FIELDS, " from ", TABLE_NAME, " where drugid like CONCAT(CONCAT('%', #{likeValue}),'%') or drugname like CONCAT(CONCAT('%', #{likeValue}),'%') or categoryname like CONCAT(CONCAT('%', #{likeValue}),'%') limit #{limit} offset #{offset}"})
    List<Drug> selectByLikeValue(Page page);

    @Select({"select ",SELECT_FIELDS," from ",TABLE_NAME," where drugid=#{id}"})
    Drug selectById(int id);

    @Select({"select count(*) from ",TABLE_NAME," where drugid like CONCAT(CONCAT('%', #{likeValue}),'%') or drugname like CONCAT(CONCAT('%', #{likeValue}),'%') or categoryname like CONCAT(CONCAT('%', #{likeValue}),'%')"})
    int selectCountByValue(String likeValue);

    @Select({"select count(*) from ",TABLE_NAME})
    int selectCount();

    @Delete({"delete from ", TABLE_NAME, " where drugid=#{id}"})
    void deleteById(int id);

    @Insert({"insert into ", TABLE_NAME, "(", INSET_FIELDS,
            ") values (#{drugnumber},#{drugname},#{drugPice},#{quantity},#{productdate},#{producer},#{categoryname})"})
    int addDrug(Object object);

    @Select({"select quantity from ",TABLE_NAME," where drugid=#{id}"})
    int selectQtityById(int id);

    @Update({"update ",TABLE_NAME," set quantity=#{quantity} where drugid=#{drugid}"})
    void updateQtityById(@Param("quantity") int quantity,@Param("drugid") int drugid);
}
