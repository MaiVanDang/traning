package example.springdata.jpa.dao;

import example.springdata.jpa.entity.UserEntity;

import java.util.List;

public interface UserDAO {
    void save(UserEntity userEntity);
    List<UserEntity> getAll();
}
