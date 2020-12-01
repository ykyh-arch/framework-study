package example.dao.impl;

import example.dao.UserDao;

public class UserDaoImpl2 implements UserDao {
    @Override
    public void query() {
        System.out.println("dao2");
    }
}
