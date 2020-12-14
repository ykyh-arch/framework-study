package example.mybatis.mapperscan.dao;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

public interface IndexDao {

    @Select("select * from sys_flower_news where title like '%${title}%'")
    public List<Map<String,Object>>select(@Param("title") String title);
}
