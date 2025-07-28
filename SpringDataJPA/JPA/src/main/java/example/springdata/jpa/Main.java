package example.springdata.jpa;

import example.springdata.jpa.dao.UserDAO;
import example.springdata.jpa.entity.UserEntity;
import example.springdata.jpa.repository.UserRepository;
import example.springdata.jpa.service.UserService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.util.List;

public class Main {
    public static void main(String[] args) {

        ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
        UserService userService = context.getBean(UserService.class);

        // --- Sắp xếp theo tên ---
        System.out.println("== Sort by name ==");
        Iterable<UserEntity> sortedUsers = userService.getAllUsersSorted(Sort.by("name").ascending());
        for (UserEntity user : sortedUsers) {
            System.out.println(user.getId() + " - " + user.getName());
        }

// --- Phân trang: trang 0, mỗi trang 2 bản ghi ---
        System.out.println("== Page 1 ==");
        Page<UserEntity> page = userService.getPagedUsers(PageRequest.of(1, 5));
        for (UserEntity user : page.getContent()) {
            System.out.println(user.getId() + " - " + user.getName());
        }

        UserRepository userRepo = context.getBean(UserRepository.class);
        // Dùng findByEmail (sẽ dùng CREATE hoặc fallback nếu là CREATE_IF_NOT_FOUND)
        System.out.println("Using findByEmail: " + userRepo.findByName("Mai Văn Đăng").getName());

        // Dùng findDeclaredByEmail (chỉ chạy được nếu strategy là USE_DECLARED_QUERY hoặc CREATE_IF_NOT_FOUND)
        System.out.println("Using findDeclaredByEmail: " + userRepo.findDeclaredByName("Nguyen Van A").getName());
    }
}