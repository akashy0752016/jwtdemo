package in.gov.indianrail.jwt.mapper;

import in.gov.indianrail.jwt.model.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRowMapper implements RowMapper<User> {
    @Override
    public User mapRow(ResultSet resultSet, int i) throws SQLException {
        User u = new User();
        u.setId(resultSet.getInt("id"));
        u.setName(resultSet.getString("name"));
        u.setPhone(resultSet.getString("phone"));
        return u;
    }
}
