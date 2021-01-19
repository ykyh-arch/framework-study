package org.example.service;

import org.apache.ibatis.annotations.Param;
import org.example.dao.IndexMapper;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Map;

/**
 * @ClassName: IndexService
 * @Description: 服务层。
 * @Author: Uetec
 * @Date: 2021-01-13-15:05
 * @Version: 1.0
 **/
@Service
public class IndexService implements InitializingBean {

    @Autowired
    IndexMapper indexMapper;

    @PostConstruct
    public void init(){
        System.out.println("init...");
    }

    public List<Map<String,Object>> select(String title) {
        return indexMapper.select(title);
    }

    public void afterPropertiesSet() throws Exception {
        System.out.println("afterPropertiesSet...");
    }
}
