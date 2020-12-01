package example.service.impl;

import example.dao.UserDao;
import example.service.UserService;

public class UserServiceImpl implements UserService {

    UserDao dao;
//    setter
//    public void setDao(UserDao dao) {
//        this.dao = dao;
//    }

    public UserServiceImpl(UserDao dao) {
        this.dao = dao;
    }

    @Override
    public void find() {
        System.out.println("service");
        dao.query();
    }

}
