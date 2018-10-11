package com.dwl.mindoc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

/**
 * @program: mindoc
 * @description: 参数配置
 * @author: daiwenlong
 * @create: 2018-10-09 20:39
 **/
@Component
public class MinConfig {

    @Autowired
    private Environment environment;


   /**
   * @Description: 获取参数
   * @Param: key
   * @return: value
   * @Date: 2018/10/11
   */ 
    public String getValue(String key){
        return  environment.getProperty(key);
    }


}


