package example.springdata.jpa.service;

import example.springdata.jpa.entity.UserEntity;
import example.springdata.jpa.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void createUser(UserEntity user) {
        userRepository.save(user);
    }

    public UserEntity getUser(int id) {
        return userRepository.findById(id).orElse(null);
    }

    public Iterable<UserEntity> getAllUsersSorted(Sort sort) {
        return userRepository.findAll(sort);
    }

    public Page<UserEntity> getPagedUsers(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    @Transactional
    public void registerUser(String name) {
        UserEntity userEntity = new UserEntity(name);
        userRepository.save(userEntity);
    }
}
