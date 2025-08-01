# Chương 5 

# 📌 Spring Boot – Global Exception Handling Demo

## 🚀 Mục tiêu

Xây dựng một dự án Spring Boot REST API đơn giản để **xử lý ngoại lệ toàn cục (global exception)** bằng cách sử dụng các annotation như `@RestControllerAdvice`, `@ExceptionHandler`, và trả về JSON phản hồi lỗi tùy chỉnh.

---

## 🛠️ Kỹ thuật sử dụng

- Java 17+
- Spring Boot 3.x
- Spring Web
- Spring Data JPA
- H2 Database (in-memory)
- Lombok
- Exception Handling (custom exception, global handler)
- Validation (JSR 380)

---

## 📁 Cấu trúc thư mục

```bash
src/main/java/com/practice
│
├── controller/
│   └── UserController.java
│
├── entity/
│   └── User.java
│
├── repository/
│   └── UserRepository.java
│
├── service/
│   └── UserService.java
│
├── exception/
│   ├── UserNotFoundException.java
│   ├── ErrorResponse.java
│   └── GlobalExceptionHandler.java
│
└── Application.java
🧪 API Mẫu và Cách test với Postman
➕ Tạo user mới
bash
Sao chép
Chỉnh sửa
POST /api/users
Content-Type: application/json
json
Sao chép
Chỉnh sửa
{
  "name": "Mai Văn Đăng"
}
➡ Kết quả: Trả về user mới tạo.

🔍 Tìm user theo ID
bash
Sao chép
Chỉnh sửa
GET /api/users/{id}
Ví dụ:

bash
Sao chép
Chỉnh sửa
GET /api/users/100
➡ Nếu không tồn tại, API trả về lỗi 404:

json
Sao chép
Chỉnh sửa
{
  "timestamp": "2025-08-01 14:45:10",
  "status": 404,
  "error": "Not Found",
  "message": "Không tìm thấy người dùng có ID: 100",
  "path": "/api/users/100"
}
❌ Gửi sai định dạng ID
bash
Sao chép
Chỉnh sửa
GET /api/users/abc
➡ Spring sẽ ném lỗi 400 hoặc 500 tùy vào cấu hình (ví dụ: Failed to convert value of type 'String' to required type 'int')

🧯 Xử lý ngoại lệ
UserNotFoundException: Ngoại lệ tùy chỉnh, ném khi user không tồn tại.

MethodArgumentNotValidException: Dùng cho lỗi validation đầu vào.

HttpMessageNotReadableException: Khi body request sai định dạng JSON.

Tất cả các lỗi được xử lý ở lớp:

java
Sao chép
Chỉnh sửa
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(UserNotFoundException.class)
    ...
}
💡 Ghi chú thêm
Dự án sử dụng @RestControllerAdvice để tự động trả về JSON mà không cần thêm @ResponseBody.

@JsonFormat và @JsonInclude được dùng trong ErrorResponse để định dạng thời gian và loại bỏ field null.

Có thể mở rộng để xử lý các lỗi khác như 403, 500, lỗi quyền truy cập, v.v.

📌 Mục tiêu học tập
Nắm được cách thiết kế hệ thống xử lý lỗi REST API rõ ràng, thống nhất.

Biết cách tạo exception tùy chỉnh, và dùng @ExceptionHandler + @RestControllerAdvice để quản lý lỗi toàn cục.

Củng cố kỹ năng tổ chức project Spring theo hướng modular, dễ mở rộng.

✅ Chạy thử
bash
Sao chép
Chỉnh sửa
./mvnw spring-boot:run
API sẽ khởi động tại:

bash
Sao chép
Chỉnh sửa
http://localhost:8080/api/users
📚 Tài liệu tham khảo
Spring Boot Docs – Exception Handling

Baeldung – Exception Handling in Spring Boot

👨‍💻 Tác giả
Mai Văn Đăng   ```