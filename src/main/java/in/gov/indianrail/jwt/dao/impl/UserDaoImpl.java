package in.gov.indianrail.jwt.dao.impl;

import in.gov.indianrail.jwt.dao.UserDao;
import in.gov.indianrail.jwt.mapper.UserRowMapper;
import in.gov.indianrail.jwt.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@PropertySource("classpath:query.properties")
@Service
public class UserDaoImpl implements UserDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private Environment environment;

    @Override
    public boolean create(User u) {
        int resp = jdbcTemplate.update(environment.getProperty("CREATE_USER"), u.getId(), u.getName(), u.getPhone());
        return (resp!=0);
    }

    @Override
    public boolean update(User u) {
        int resp = jdbcTemplate.update(environment.getProperty("UPDATE_USER"), u.getName(), u.getPhone(), u.getId());
        return (resp!=0);
    }

    @Override
    public User get(int id) {
        User user = null;
        try {
            user  = (User) jdbcTemplate.queryForObject(environment.getProperty("GET_USER_BY_ID"), new Object[] {id}, new UserRowMapper());
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            return null;
        }
        return user;
    }

    @Override
    public List<User> get() {
        return jdbcTemplate.query(environment.getProperty("GET_ALL_USER"), new UserRowMapper());
    }

    @Override
    public boolean delete(int id) {
        int resp = jdbcTemplate.update(environment.getProperty("DELETE_USER"), id);
        return (resp!=0);
    }
}
