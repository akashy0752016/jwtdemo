package in.gov.indianrail.jwt.service;

import in.gov.indianrail.jwt.model.User;

import java.util.List;

public interface UserService {
    public boolean createUser(User user);
    public boolean updateUser(User user);
    public boolean deleteUser(int id);
    public User getUserById(int id);
    public List<User> getAllUser();
}
