package example.collection;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @ClassName: ListTest
 * @Description: 测试普通集合，查询的时候是否允许更新操作
 * @Author: Uetec
 * @Date: 2020-11-16-14:04
 * @Version: 1.0
 **/
public class ListTest {

    public static void main(String[] args) {

        List<User> list=new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            User user=new User(i,"User "+i);
            list.add(user);
        }

        Iterator<User> it=list.iterator();
        while(it.hasNext()){
            User user=it.next();
            System.out.println(user);
            if("User 6".equals(user.getName()))
                list.remove(user);
//            报错：ConcurrentModificationException
        }
    }
}

class User {
    private int id;
    private String name;

    public User(int id,String name){
        this.id=id;
        this.name=name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
