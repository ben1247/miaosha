package com.miaoshaproject.service;

import com.miaoshaproject.error.BusinessException;
import com.miaoshaproject.service.model.UserModel;

public interface UserService {

    UserModel getUserById(Long id);

   /**
    * 〈一句话功能简述〉
    *  通过缓存获取用户对象
    *
    * @author zhangyue
    * @see [相关类/方法]（可选）
    * @since [产品/模块版本] （可选）
    * @param id
    * @Return com.miaoshaproject.service.model.UserModel
    * @date 2020/8/9 10:13 下午
    */
    UserModel getUserByIdInCache(Long id);


    void register(UserModel userModel) throws BusinessException;

    /**
    * 〈一句话功能简述〉<br>
    * telphone：用户登录手机
    * encryptPassword：加密后的密码
    * @author zhangyue
    * @see [相关类/方法]（可选）
    * @since [产品/模块版本] （可选）
    * @date 2020/3/29 2:56 下午
    */
    UserModel validateLogin(String telphone , String encryptPassword) throws BusinessException;
}
