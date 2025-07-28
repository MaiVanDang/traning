package example.springdata.jpa.repository;


import example.springdata.jpa.entity.UserEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.*;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;


//public interface UserRepository extends Repository<UserEntity, Integer> {
//    UserEntity save(UserEntity userEntity);
//
//    Optional<UserEntity> findById(long id);
//
//    List<UserEntity> findAll();
//}

public interface UserRepository extends PagingAndSortingRepository<UserEntity, Integer> {
    // Có thể thêm custom method nếu cần
    // Truy vấn tự động từ tên phương thức (CREATE hoặc fallback của CREATE_IF_NOT_FOUND)
    UserEntity findByName(String name);

    // Truy vấn khai báo rõ ràng (cho USE_DECLARED_QUERY hoặc fallback của CREATE_IF_NOT_FOUND)
    @Query
            ("SELECT u FROM UserEntity u WHERE u.name = :name")
    UserEntity findDeclaredByName(@Param("name") String name);
}
