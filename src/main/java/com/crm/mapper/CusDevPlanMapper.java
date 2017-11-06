package com.crm.mapper;

import com.crm.pojo.CusDevPlan;
import com.crm.pojo.CusDevPlanExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CusDevPlanMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cus_dev_plan
     *
     * @mbggenerated Mon Oct 30 10:18:17 CST 2017
     */
    int countByExample(CusDevPlanExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cus_dev_plan
     *
     * @mbggenerated Mon Oct 30 10:18:17 CST 2017
     */
    int deleteByExample(CusDevPlanExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cus_dev_plan
     *
     * @mbggenerated Mon Oct 30 10:18:17 CST 2017
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cus_dev_plan
     *
     * @mbggenerated Mon Oct 30 10:18:17 CST 2017
     */
    int insert(CusDevPlan record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cus_dev_plan
     *
     * @mbggenerated Mon Oct 30 10:18:17 CST 2017
     */
    int insertSelective(CusDevPlan record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cus_dev_plan
     *
     * @mbggenerated Mon Oct 30 10:18:17 CST 2017
     */
    List<CusDevPlan> selectByExample(CusDevPlanExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cus_dev_plan
     *
     * @mbggenerated Mon Oct 30 10:18:17 CST 2017
     */
    CusDevPlan selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cus_dev_plan
     *
     * @mbggenerated Mon Oct 30 10:18:17 CST 2017
     */
    int updateByExampleSelective(@Param("record") CusDevPlan record, @Param("example") CusDevPlanExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cus_dev_plan
     *
     * @mbggenerated Mon Oct 30 10:18:17 CST 2017
     */
    int updateByExample(@Param("record") CusDevPlan record, @Param("example") CusDevPlanExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cus_dev_plan
     *
     * @mbggenerated Mon Oct 30 10:18:17 CST 2017
     */
    int updateByPrimaryKeySelective(CusDevPlan record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cus_dev_plan
     *
     * @mbggenerated Mon Oct 30 10:18:17 CST 2017
     */
    int updateByPrimaryKey(CusDevPlan record);
}