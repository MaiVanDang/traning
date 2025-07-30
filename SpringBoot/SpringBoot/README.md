# Chương 3 – Annotation trong Spring Boot

## 🎯 Mục tiêu

Hiểu và thực hành các annotation cốt lõi dùng trong việc xử lý request và ánh xạ dữ liệu trong Spring/Spring Boot:

- `@RestController`, `@RequestMapping`
- `@GetMapping`, `@PostMapping`
- `@PathVariable`, `@RequestParam`, `@RequestBody`
- `@ModelAttribute`

---

## 🧱 Cấu trúc thư mục

```
com.practice.chapter3
├── DemoApplication.java
├── controller/
│   └── AnnotationDemoController.java
└── model/
    └── UserForm.java
```

---

## 📌 Các API Demo

| Endpoint          | Method | Annotation      | Mô tả                                  |
|-------------------|--------|-----------------|----------------------------------------|
| `/api/greet`      | GET    | `@RequestParam` | Nhận tham số từ URL: `?name=...`       |
| `/api/hello/{name}` | GET    | `@PathVariable` | Trích xuất biến từ URI                 |
| `/api/json`       | POST   | `@RequestBody`  | Nhận JSON trong phần thân request      |
| `/api/form`       | POST   | `@ModelAttribute` | Nhận dữ liệu từ form-urlencoded      |

---

## 📥 Test bằng Postman

### 🔹 1. GET `/api/greet?name=Đăng`

**Request:**
```
GET http://localhost:8080/api/greet?name=Đăng
```

**Kết quả:**
```
Xin chào Đăng!
```

---

### 🔹 2. GET `/api/hello/Đăng`

**Request:**
```
GET http://localhost:8080/api/hello/Đăng
```

**Kết quả:**
```
Hello Đăng from @PathVariable!
```

---

### 🔹 3. POST `/api/json`

**Request:**
```
POST http://localhost:8080/api/json
Content-Type: application/json
```

**Body (raw JSON):**
```json
{
  "username": "vanmai",
  "email": "vanmai@example.com"
}
```

**Kết quả:**
```
Received JSON: vanmai - vanmai@example.com
```

---

### 🔹 4. POST `/api/form`

**Request:**
```
POST http://localhost:8080/api/form
Content-Type: application/x-www-form-urlencoded
```

**Body (form-data hoặc x-www-form-urlencoded):**
```
username=vanmai&email=vanmai@example.com
```

**Kết quả:**
```
Form submit: vanmai - vanmai@example.com
```

---

## 💡 Chi tiết các Annotation

### `@RestController`
- Kết hợp `@Controller` + `@ResponseBody`
- Tự động serialize response thành JSON/XML

### `@RequestMapping`
- Annotation tổng quát để map request
- Có thể chỉ định method, path, headers, params

### `@GetMapping` / `@PostMapping`
- Shortcut cho `@RequestMapping(method = GET/POST)`
- Code ngắn gọn và rõ ràng hơn

### `@PathVariable`
- Trích xuất giá trị từ URI path
- Ví dụ: `/users/{id}` → `@PathVariable Long id`

### `@RequestParam`
- Lấy parameter từ query string
- Ví dụ: `/search?name=abc` → `@RequestParam String name`

### `@RequestBody`
- Deserialize JSON/XML từ request body thành object
- Thường dùng với POST/PUT requests

### `@ModelAttribute`
- Bind form data thành object
- Hỗ trợ form-urlencoded và multipart

---

## 🛠 Code Example

### Controller
```java
@RestController
@RequestMapping("/api")
public class AnnotationDemoController {
    
    @GetMapping("/greet")
    public String greet(@RequestParam String name) {
        return "Xin chào " + name + "!";
    }
    
    @GetMapping("/hello/{name}")
    public String hello(@PathVariable String name) {
        return "Hello " + name + " from @PathVariable!";
    }
    
    @PostMapping("/json")
    public String receiveJson(@RequestBody UserForm user) {
        return "Received JSON: " + user.getUsername() + " - " + user.getEmail();
    }
    
    @PostMapping("/form")
    public String receiveForm(@ModelAttribute UserForm user) {
        return "Form submit: " + user.getUsername() + " - " + user.getEmail();
    }
}
```

### Model
```java
public class UserForm {
    private String username;
    private String email;
    
    // Constructors, getters, setters
}
```

---

## 📚 Kiến thức đạt được

- **Annotation** giúp mã ngắn gọn, dễ bảo trì
- Tận dụng sức mạnh của **Convention over Configuration**
- Loại bỏ hầu hết cấu hình XML truyền thống
- Hiểu cách Spring Boot xử lý các loại request khác nhau
- Phân biệt giữa `@RequestParam`, `@PathVariable`, `@RequestBody`, `@ModelAttribute`

---

## 🔧 Tips

1. **@RequestParam** vs **@PathVariable**:
  - `@RequestParam`: Query parameters (`?name=value`)
  - `@PathVariable`: URI path variables (`/users/{id}`)

2. **@RequestBody** vs **@ModelAttribute**:
  - `@RequestBody`: JSON/XML data
  - `@ModelAttribute`: Form data

3. **Validation**: Có thể kết hợp với `@Valid` để validate input

4. **Optional Parameters**:
   ```java
   @RequestParam(required = false, defaultValue = "Guest") String name;
   ```