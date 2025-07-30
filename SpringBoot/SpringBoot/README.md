# Chương 1 – RESTful Web Services Demo

## 🎯 Mục tiêu

Xây dựng một ứng dụng Spring Boot đơn giản để thực hiện CRUD đối với thực thể `Product` thông qua các API RESTful.

---

## 🛠 Công nghệ sử dụng

- Java 17 trở lên
- Spring Boot 3.x
- Spring Web
- Spring Data JPA
- MySQL hoặc H2 (cho môi trường demo)
- Lombok
- Postman (gửi request REST)

---

## 🧱 Kiến trúc project

```
com.practice
├── DemoApplication.java
├── controller/
│   └── ProductController.java
├── entity/
│   ├── ProductEntity.java
│   └── CategoryEntity.java
├── repository/
│   └── ProductRepository.java
├── service/
│   └── ProductService.java
└── resources/
    └── application.properties
```

---

## 🧪 Các API đã triển khai

| Method | Endpoint                    | Mô tả                      |
|--------|-----------------------------|----------------------------|
| GET    | `/api/products`             | Lấy danh sách sản phẩm     |
| GET    | `/api/products/{id}`        | Lấy sản phẩm theo ID       |
| POST   | `/api/products`             | Tạo mới sản phẩm           |
| PUT    | `/api/products/{id}`        | Cập nhật sản phẩm          |
| DELETE | `/api/products/{id}`        | Xoá sản phẩm               |

---

## 📥 Cấu hình `application.properties`

### ✅ Dùng MySQL
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/demo_rest?useSSL=false&serverTimezone=UTC
spring.datasource.username=root
spring.datasource.password=yourpassword
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

spring.jpa.hibernate.ddl-auto=update
spring.jpa.database-platform=org.hibernate.dialect.MySQL8Dialect
spring.jpa.properties.hibernate.id.new_generator_mappings=false

spring.jpa.show-sql=true
```

### ✅ Dùng H2 (cho môi trường demo)
```properties
spring.datasource.url=jdbc:h2:mem:testdb
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=password

spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
spring.jpa.hibernate.ddl-auto=create-drop
spring.h2.console.enabled=true
```

---

## 🚀 Hướng dẫn chạy project

### 1. Clone hoặc tải project về máy

### 2. Cài đặt Maven dependency:
```bash
mvn clean install
```

### 3. Chạy project:
```bash
mvn spring-boot:run
```

### 4. Truy cập Postman để kiểm thử các API:
- **Base URL**: `http://localhost:8080/api/products`

---

## 🧪 Gửi request POST tạo sản phẩm (ví dụ trong Postman)

```json
{
  "name": "T-Shirt Nam",
  "importPrice": 100.0,
  "sellingPrice": 150.0,
  "stockQuantity": 20,
  "imageUrl": "https://example.com/tshirt.jpg",
  "isFeatured": true,
  "description": "Áo thun cotton 100%",
  "category": {
    "id": 1
  }
}
```

> ⚠️ **Lưu ý**: Đảm bảo category ID đã tồn tại trong DB!

---

## 📚 Kiến thức áp dụng

- **Kiến trúc REST**: Thiết kế API theo chuẩn RESTful
- **HTTP methods**: GET, POST, PUT, DELETE
- **Spring Annotations**:
    - `@RestController`, `@RequestMapping`
    - `@PathVariable`, `@RequestBody`
    - `@Autowired`
- **Truy xuất dữ liệu**: Spring Data JPA
- **Entity Mapping**: JPA annotations

---

## 🔧 Testing với Postman

### GET - Lấy danh sách sản phẩm
```
GET http://localhost:8080/api/products
```

### GET - Lấy sản phẩm theo ID
```
GET http://localhost:8080/api/products/1
```

### POST - Tạo sản phẩm mới
```
POST http://localhost:8080/api/products
Content-Type: application/json

{JSON payload như ví dụ trên}
```

### PUT - Cập nhật sản phẩm
```
PUT http://localhost:8080/api/products/1
Content-Type: application/json

{JSON payload đã cập nhật}
```

### DELETE - Xóa sản phẩm
```
DELETE http://localhost:8080/api/products/1
```