package com.example.address.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;

import com.example.address.entity.AddressData;
import com.example.address.entity.AddressDataExample;

public interface AddressDataMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table address_data
     *
     * @mbggenerated Thu Jul 19 09:23:25 JST 2018
     */
    int countByExample(AddressDataExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table address_data
     *
     * @mbggenerated Thu Jul 19 09:23:25 JST 2018
     */
    int deleteByExample(AddressDataExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table address_data
     *
     * @mbggenerated Thu Jul 19 09:23:25 JST 2018
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table address_data
     *
     * @mbggenerated Thu Jul 19 09:23:25 JST 2018
     */
    int insert(AddressData record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table address_data
     *
     * @mbggenerated Thu Jul 19 09:23:25 JST 2018
     */
    int insertSelective(AddressData record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table address_data
     *
     * @mbggenerated Thu Jul 19 09:23:25 JST 2018
     */
    List<AddressData> selectByExample(AddressDataExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table address_data
     *
     * @mbggenerated Thu Jul 19 09:23:25 JST 2018
     */
    AddressData selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table address_data
     *
     * @mbggenerated Thu Jul 19 09:23:25 JST 2018
     */
    int updateByExampleSelective(@Param("record") AddressData record, @Param("example") AddressDataExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table address_data
     *
     * @mbggenerated Thu Jul 19 09:23:25 JST 2018
     */
    int updateByExample(@Param("record") AddressData record, @Param("example") AddressDataExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table address_data
     *
     * @mbggenerated Thu Jul 19 09:23:25 JST 2018
     */
    int updateByPrimaryKeySelective(AddressData record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table address_data
     *
     * @mbggenerated Thu Jul 19 09:23:25 JST 2018
     */
    int updateByPrimaryKey(AddressData record);
}