package example.springdata.jpa.service.Impl;

import example.springdata.jpa.dao.UserDAO;
import example.springdata.jpa.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class UserDaoImpl implements UserDAO {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Transactional
    @Override
    public void save(UserEntity userEntity) {
        String sql = "INSERT INTO users (name) VALUES (?)";
        jdbcTemplate.update(sql, userEntity.getName());
    }

    @Override
    public List<UserEntity> getAll() {
        String sql = "SELECT * FROM users";
        return jdbcTemplate.query(sql, userRowMapper);
    }

    private RowMapper<UserEntity> userRowMapper = (rs, rowNum) -> new UserEntity(rs.getInt("id"), rs.getString("name"));

}
