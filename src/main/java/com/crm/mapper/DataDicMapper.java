package com.crm.mapper;

import com.crm.pojo.DataDic;
import com.crm.pojo.DataDicExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DataDicMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table data_dic
     *
     * @mbggenerated Mon Oct 30 10:18:17 CST 2017
     */
    int countByExample(DataDicExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table data_dic
     *
     * @mbggenerated Mon Oct 30 10:18:17 CST 2017
     */
    int deleteByExample(DataDicExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table data_dic
     *
     * @mbggenerated Mon Oct 30 10:18:17 CST 2017
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table data_dic
     *
     * @mbggenerated Mon Oct 30 10:18:17 CST 2017
     */
    int insert(DataDic record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table data_dic
     *
     * @mbggenerated Mon Oct 30 10:18:17 CST 2017
     */
    int insertSelective(DataDic record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table data_dic
     *
     * @mbggenerated Mon Oct 30 10:18:17 CST 2017
     */
    List<DataDic> selectByExample(DataDicExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table data_dic
     *
     * @mbggenerated Mon Oct 30 10:18:17 CST 2017
     */
    DataDic selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table data_dic
     *
     * @mbggenerated Mon Oct 30 10:18:17 CST 2017
     */
    int updateByExampleSelective(@Param("record") DataDic record, @Param("example") DataDicExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table data_dic
     *
     * @mbggenerated Mon Oct 30 10:18:17 CST 2017
     */
    int updateByExample(@Param("record") DataDic record, @Param("example") DataDicExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table data_dic
     *
     * @mbggenerated Mon Oct 30 10:18:17 CST 2017
     */
    int updateByPrimaryKeySelective(DataDic record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table data_dic
     *
     * @mbggenerated Mon Oct 30 10:18:17 CST 2017
     */
    int updateByPrimaryKey(DataDic record);

	List<DataDic> selectDistinct();

	List<String> findValueByName(String string);
}