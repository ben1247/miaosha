package com.miaoshaproject.dao;

import com.miaoshaproject.dataobject.PromoDO;

public interface PromoDOMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table promo
     *
     * @mbg.generated Mon Apr 06 16:16:28 CST 2020
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table promo
     *
     * @mbg.generated Mon Apr 06 16:16:28 CST 2020
     */
    int insert(PromoDO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table promo
     *
     * @mbg.generated Mon Apr 06 16:16:28 CST 2020
     */
    int insertSelective(PromoDO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table promo
     *
     * @mbg.generated Mon Apr 06 16:16:28 CST 2020
     */
    PromoDO selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table promo
     *
     * @mbg.generated Mon Apr 06 16:16:28 CST 2020
     */
    int updateByPrimaryKeySelective(PromoDO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table promo
     *
     * @mbg.generated Mon Apr 06 16:16:28 CST 2020
     */
    int updateByPrimaryKey(PromoDO record);

    PromoDO selectByItemId(Long itemId);
}