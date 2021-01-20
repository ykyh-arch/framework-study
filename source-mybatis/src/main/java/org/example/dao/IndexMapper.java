package org.example.dao;

import org.apache.ibatis.annotations.CacheNamespace;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

@Mapper
//开启MyBatis二级缓存
//@CacheNamespace
//MapperStatement 封裝了所有Mapper信息
public interface IndexMapper {

    @Select("select * from sys_flower_news where title like '%${title}%'")
    public List<Map<String,Object>> select(@Param("title") String title);

}
