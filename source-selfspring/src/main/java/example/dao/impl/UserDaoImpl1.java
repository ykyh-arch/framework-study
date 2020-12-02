package example.dao.impl;

import example.dao.UserDao;

public class UserDaoImpl1 implements UserDao {
    @Override
    public void query() {
        System.out.println("dao1");
    }
}
