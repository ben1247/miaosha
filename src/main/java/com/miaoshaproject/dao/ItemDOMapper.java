package com.miaoshaproject.dao;

import com.miaoshaproject.dataobject.ItemDO;

public interface ItemDOMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table item
     *
     * @mbg.generated Sun Mar 29 21:42:20 CST 2020
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table item
     *
     * @mbg.generated Sun Mar 29 21:42:20 CST 2020
     */
    int insert(ItemDO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table item
     *
     * @mbg.generated Sun Mar 29 21:42:20 CST 2020
     */
    int insertSelective(ItemDO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table item
     *
     * @mbg.generated Sun Mar 29 21:42:20 CST 2020
     */
    ItemDO selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table item
     *
     * @mbg.generated Sun Mar 29 21:42:20 CST 2020
     */
    int updateByPrimaryKeySelective(ItemDO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table item
     *
     * @mbg.generated Sun Mar 29 21:42:20 CST 2020
     */
    int updateByPrimaryKey(ItemDO record);
}