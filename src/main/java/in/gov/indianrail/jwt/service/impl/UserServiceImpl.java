package in.gov.indianrail.jwt.service.impl;

import in.gov.indianrail.jwt.dao.UserDao;
import in.gov.indianrail.jwt.model.User;
import in.gov.indianrail.jwt.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public boolean createUser(User user) {
        return userDao.create(user);
    }

    @Override
    public boolean updateUser(User user) {
        return userDao.update(user);
    }

    @Override
    public boolean deleteUser(int id) {
        return userDao.delete(id);
    }

    @Override
    public User getUserById(int id) {
        return userDao.get(id);
    }

    @Override
    public List<User> getAllUser() {
        return userDao.get();
    }
}
