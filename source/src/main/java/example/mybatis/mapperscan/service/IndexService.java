package example.mybatis.mapperscan.service;

import example.mybatis.mapperscan.dao.IndexDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @ClassName: IndexService
 * @Description: 服务层。
 * @Author: Uetec
 * @Date: 2020-11-19-10:19
 * @Version: 1.0
 **/
@Service
public class IndexService {

    //接口
    @Autowired
    IndexDao indexDao;

    public List<Map<String,Object>> select(String title) {
        List<Map<String,Object>> result = indexDao.select(title);
        for (Map<String, Object> map : result) {
            for(Map.Entry<String, Object> entry : map.entrySet()){
                String mapKey = entry.getKey();
                Object mapValue = entry.getValue();
                System.out.println(mapKey+":"+mapValue);
            }
        }
        return null;
    }

}
