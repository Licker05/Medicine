package com.Medicine.dao;


import com.Medicine.model.Drug;
import com.Medicine.model.Page;
import com.Medicine.model.Sale;
import org.apache.ibatis.annotations.*;

import java.util.List;
@Mapper
public interface SaleDAO {
    String TABLE_NAME = "sale";
    String INSET_FIELDS = " selldate, drugnumber, sellquantity,sellPrice,userid";
    String SELECT_FIELDS = "sellrecordnumber, selldate, drugnumber, sellquantity , sellPrice , userid ";
    @Update({"update ",TABLE_NAME," set selldate=#{selldate},drugnumber=#{drugnumber},sellquantity=#{sellquantity},sellquantity=#{sellquantity},sellPrice=#{sellPrice},userid=#{userid} where drugid=#{drugid}"})
    void updateInfo(Object object);

    @Select({"select ", SELECT_FIELDS, " from ", TABLE_NAME, " limit #{limit} offset #{offset}"})
    List<Sale> selectByLimitAndOffset(Page page);

    @Select({"select ", SELECT_FIELDS, " from ", TABLE_NAME, " where sellrecordnumber like CONCAT(CONCAT('%', #{likeValue}),'%') or drugnumber like CONCAT(CONCAT('%', #{likeValue}),'%') or userid like CONCAT(CONCAT('%', #{likeValue}),'%') limit #{limit} offset #{offset}"})
    List<Sale> selectByLikeValue(Page page);

    @Select({"select ",SELECT_FIELDS," from ",TABLE_NAME," where sellrecordnumber=#{id}"})
    Sale selectById(int id);

    @Select({"select count(*) from ",TABLE_NAME," where sellrecordnumber like CONCAT(CONCAT('%', #{likeValue}),'%') or drugnumber like CONCAT(CONCAT('%', #{likeValue}),'%') or userid like CONCAT(CONCAT('%', #{likeValue}),'%')"})
    int selectCountByValue(String likeValue);

    @Select({"select count(*) from ",TABLE_NAME})
    int selectCount();

    @Delete({"delete from ", TABLE_NAME, " where sellrecordnumber=#{id}"})
    void deleteById(int id);

    @Insert({"insert into ", TABLE_NAME, "(", INSET_FIELDS,
            ") values (#{selldate},#{drugnumber},#{sellquantity},#{sellPrice},#{userid})"})
    int addSale(Object object);
}
