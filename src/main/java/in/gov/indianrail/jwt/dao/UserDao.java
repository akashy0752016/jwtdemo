package in.gov.indianrail.jwt.dao;

import in.gov.indianrail.jwt.model.User;

import java.util.List;

public interface UserDao {
    public boolean create(User u);
    public boolean update(User u);
    public User get(int id);
    public List<User> get();
    public boolean delete(int id);
}
