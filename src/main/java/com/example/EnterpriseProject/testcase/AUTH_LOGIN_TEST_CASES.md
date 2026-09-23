# BẢNG TÀI LIỆU KIỂM THỬ CHỨC NĂNG ĐĂNG NHẬP (AUTHENTICATION TEST CASES)

- **Dự án:** EnterpriseProject Backend
- **Chức năng:** Xác thực & Phân quyền người dùng (User Authentication & Authorization)
- **API Endpoint:** `POST /api/auth/login`
- **Content-Type:** `application/json`
- **Môi trường thử nghiệm:** `http://localhost:8080`

---

## 1. Dữ liệu thử nghiệm có sẵn (Pre-seeded Test Accounts)

Các tài khoản dưới đây đã được khởi tạo tự động trong hệ thống thông qua `DataInitializer`:

| Mã thẻ (`cartId`) | Username   | Mật khẩu (`password`) | Vai trò (`Roles`) | Họ tên          | Phòng ban        |
| :---------------- | :--------- | :-------------------- | :---------------- | :-------------- | :--------------- |
| `CARD001`         | `admin`    | `12345678`            | `ROLE_ADMIN`      | Nguyễn Quản Trị | Ban Quản Trị     |
| `CARD002`         | `manager`  | `12345678`            | `ROLE_MANAGER`    | Trần Quản Lý    | Phòng Kỹ Thuật   |
| `CARD003`         | `employee` | `12345678`            | `ROLE_EMPLOYEE`   | Lê Nhân Viên    | Phòng Kinh Doanh |
| `CARD004`         | `user`     | `12345678`            | `ROLE_USER`       | Phạm Người Dùng | Khách Hàng       |

---

## 2. Bảng các trường hợp kiểm thử (Test Cases Matrix)

| Mã TC          | Tên ca kiểm thử                                     | Loại kiểm thử  | Dữ liệu đầu vào (Request Body)                       | Kết quả mong đợi (Expected Output)                                                                                              | Trạng thái    |
| :------------- | :-------------------------------------------------- | :------------- | :--------------------------------------------------- | :------------------------------------------------------------------------------------------------------------------------------ | :------------ |
| **TC_AUTH_01** | Đăng nhập thành công với tài khoản Admin            | **Positive**   | `{"cartId": "CARD001", "password": "12345678"}`      | - HTTP Status: `200 OK`<br>- Header có `Set-Cookie`<br>- Body chứa `id`, `jwtToken`, `username="admin"`, `roles=["ROLE_ADMIN"]` | Sẵn sàng test |
| **TC_AUTH_02** | Đăng nhập thành công với tài khoản Manager          | **Positive**   | `{"cartId": "CARD002", "password": "12345678"}`      | - HTTP Status: `200 OK`<br>- Header có `Set-Cookie`<br>- Body chứa `roles=["ROLE_MANAGER"]`                                     | Sẵn sàng test |
| **TC_AUTH_03** | Đăng nhập thành công với tài khoản Employee         | **Positive**   | `{"cartId": "CARD003", "password": "12345678"}`      | - HTTP Status: `200 OK`<br>- Header có `Set-Cookie`<br>- Body chứa `roles=["ROLE_EMPLOYEE"]`                                    | Sẵn sàng test |
| **TC_AUTH_04** | Đăng nhập thành công với tài khoản User             | **Positive**   | `{"cartId": "CARD004", "password": "12345678"}`      | - HTTP Status: `200 OK`<br>- Header có `Set-Cookie`<br>- Body chứa `roles=["ROLE_USER"]`                                        | Sẵn sàng test |
| **TC_AUTH_05** | Đăng nhập thất bại do sai mật khẩu                  | **Negative**   | `{"cartId": "CARD001", "password": "wrongpassword"}` | - HTTP Status: `404 Not Found`<br>- Body: `{"message": "Bad credentials", "status": false}`                                     | Sẵn sàng test |
| **TC_AUTH_06** | Đăng nhập thất bại do mã thẻ `cartId` không tồn tại | **Negative**   | `{"cartId": "CARD9999", "password": "12345678"}`     | - HTTP Status: `404 Not Found`<br>- Body: `{"message": "Bad credentials", "status": false}`                                     | Sẵn sàng test |
| **TC_AUTH_07** | Mật khẩu sai định dạng viết hoa/viết thường         | **Negative**   | `{"cartId": "CARD001", "password": "PASSWORD123"}`   | - HTTP Status: `404 Not Found`<br>- Body: `{"message": "Bad credentials", "status": false}`                                     | Sẵn sàng test |
| **TC_AUTH_08** | Để trống trường `cartId` (Validation)               | **Validation** | `{"cartId": "", "password": "12345678"}`             | - HTTP Status: `400 Bad Request`<br>- Báo lỗi `cartId cannot be null/blank`                                                     | Sẵn sàng test |
| **TC_AUTH_09** | Thiếu hẳn trường `cartId` trong JSON                | **Validation** | `{"password": "12345678"}`                           | - HTTP Status: `400 Bad Request`<br>- Báo lỗi `cartId cannot be null`                                                           | Sẵn sàng test |
| **TC_AUTH_10** | Để trống trường `password`                          | **Validation** | `{"cartId": "CARD001", "password": ""}`              | - HTTP Status: `400 Bad Request`                                                                                                | Sẵn sàng test |
| **TC_AUTH_11** | Mật khẩu quá ngắn (< 8 ký tự)                       | **Boundary**   | `{"cartId": "CARD001", "password": "12345"}`         | - HTTP Status: `400 Bad Request`<br>- Báo lỗi `Password must have at least 8 characters`                                        | Sẵn sàng test |
| **TC_AUTH_12** | Mật khẩu đúng 8 ký tự (Giá trị biên)                | **Boundary**   | `{"cartId": "CARD001", "password": "12345678"}`      | - HTTP Status: `200 OK`<br>- Đăng nhập thành công                                                                               | Sẵn sàng test |
| **TC_AUTH_13** | Gửi Body JSON hoàn toàn rỗng `{}`                   | **Validation** | `{}`                                                 | - HTTP Status: `400 Bad Request`                                                                                                | Sẵn sàng test |
| **TC_AUTH_14** | Kiểm tra tính hợp lệ của Cookie JWT                 | **Security**   | `{"cartId": "CARD001", "password": "12345678"}`      | - Cookie chứa token hợp lệ<br>- `Path=/api`<br>- Có thời hạn sống `maxAge`                                                      | Sẵn sàng test |

---

## 3. Chi tiết các bước thực hiện & Mẫu Request

### Trường hợp 1: Đăng nhập thành công (Happy Case)

- **cURL Request:**

```bash
curl -X POST http://localhost:8080/api/auth/login \
  -H "Content-Type: application/json" \
  -d '{
    "cartId": "CARD001",
    "password": "12345678"
  }'
```

- **Mẫu Response thành công (HTTP 200):**

```json
{
  "id": 1,
  "jwtToken": "springBootEcom=eyJhbGciOiJIUzI1NiJ9...; Path=/api; Max-Age=86400",
  "username": "admin",
  "roles": ["ROLE_ADMIN"]
}
```

---

### Trường hợp 2: Sai mật khẩu (Bad Credentials)

- **cURL Request:**

```bash
curl -X POST http://localhost:8080/api/auth/login \
  -H "Content-Type: application/json" \
  -d '{
    "cartId": "CARD001",
    "password": "sai_mat_khau"
  }'
```

- **Mẫu Response lỗi (HTTP 404):**

```json
{
  "message": "Bad credentials",
  "status": false
}
```

---

### Trường hợp 3: Vi phạm Validation (Password < 8 ký tự)

- **cURL Request:**

```bash
curl -X POST http://localhost:8080/api/auth/login \
  -H "Content-Type: application/json" \
  -d '{
    "cartId": "CARD001",
    "password": "123"
  }'
```

- **Mẫu Response lỗi Validation (HTTP 400):**

```json
{
  "timestamp": "2026-09-22T17:10:00.000+00:00",
  "status": 400,
  "error": "Bad Request",
  "path": "/api/auth/login"
}
```
