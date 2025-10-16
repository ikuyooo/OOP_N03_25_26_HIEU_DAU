# OOP_N03_25_26_HIEU_DAU

Ứng dụng đặt phòng khách sạn

Thành viên :

1.Tạ Công Hiếu

2.Nguyễn Công Dậu

========================================================================

Project xây dựng ứng dụng quản lý khách sạn.

Yêu cầu chính: 

-Giao diện: Sử dụng Java Spring boot

-Có chưc năng quản lý khách hàng, phòng, nhân viên, doanh thu

-Và các chức năng khác.

Cụ thể:

-Quản lý khách hàng:

  +Thêm, xóa, sửa, tìm kiếm khách hàng

-Quản lý phòng:

  +Thông tin các loại phòng(VIP,STANDARD...)

  +Theo dõi trạng thái phòng(còn trống hay đã đầy).

  +Giá cả của các phòng.

-Quản lý nhân viên:

  +Thông tin nhân viên

  +Phân quyền(truy cập xóa, sửa, thêm, tìm kiếm phòng và khách hàng).

-Dữ liệu được lưu trữ dưới dạng hệ quản trị dữ liệu(MySQl).

========================================================================

### Chi Tiết Lớp Model

1.Customer

| Tên Thuộc Tính | Kiểu Dữ Liệu | Mô Tả | Ràng Buộc |
| :--- | :--- | :--- | :--- |
| `customerId` | `int` | Mã định danh duy nhất cho mỗi khách hàng (Khóa chính). | `PRIMARY KEY`, `AUTO_INCREMENT` |
| `Name` | `String` | Họ và tên đầy đủ của khách hàng. | `NOT NULL` |
| `phoneNumber` | `String` | Số điện thoại liên lạc. | `NOT NULL`, `UNIQUE` |
| `email` | `String` | Địa chỉ email của khách hàng. | `UNIQUE` |
| `idCardNumber` | `String` | Số CMND/CCCD. | `NOT NULL`, `UNIQUE` |

2.Room

| Tên Thuộc Tính | Kiểu Dữ Liệu | Mô Tả | Ràng Buộc |
| :--- | :--- | :--- | :--- |
| `roomNumber` | `int` | Mã định danh duy nhất cho mỗi phòng. | `PRIMARY KEY`, `AUTO_INCREMENT` |
| `roomType` | `String` | Loại phòng(Standard , VIP). | `NOT NULL` |
| `Price`| `double` | Giá tiền . | `NOT NULL`, `CHECK (pricePerNight >= 0)` |
| `status` | `String` | Trạng thái hiện tại của phòng. | `NOT NULL`, `DEFAULT 'Available'` |

3.Booking

| Tên Thuộc Tính | Kiểu Dữ Liệu | Mô Tả | Ràng Buộc |
| :--- | :--- | :--- | :--- |
| `bookingId` | `int` | Mã định danh duy nhất cho mỗi lần đặt phòng. | `PRIMARY KEY`, `AUTO_INCREMENT` |
| `customerId` | `int` | Mã khách hàng thực hiện đặt phòng. | `NOT NULL`, `FOREIGN KEY` |
| `roomNumber` | `int` | Mã phòng được đặt. | `NOT NULL`, `FOREIGN KEY` |
| `checkInDate` | `DATETIME` | Ngày và giờ khách hàng nhận phòng. | `NOT NULL` |
| `checkOutDate` | `DATETIME` | Ngày và giờ khách hàng dự kiến trả phòng. | `NOT NULL`, `CHECK (checkOutDate > checkInDate)` |
| `totalAmount` | `DECIMAL(10, 2)` | Tổng số tiền của hóa đơn. | `DEFAULT 0.00`, `CHECK (totalAmount >= 0)` |
| `status` | `String` | Trạng thái đặt phòng ("Checked-in", "Checked-out"). | `NOT NULL`, `DEFAULT 'Checked-in'` |
| `bookingDate` | `TIMESTAMP` | Thời điểm phiếu đặt phòng này được tạo. | `NOT NULL`, `DEFAULT CURRENT_TIMESTAMP`|


### Chi tiết lớp CRUD

1.CustomerDAO

| Phương thức (Method) | Kiểu trả về (Return Type) | Mô tả (Description) | Thao tác SQL |
| :--- | :--- | :--- | :--- |
| `addCustomer(Customer customer)` | `boolean` | Thêm một đối tượng khách hàng mới vào cơ sở dữ liệu. | `INSERT` |
| `getCustomerById(int customerId)` | `Customer` | Lấy một khách hàng duy nhất từ CSDL dựa vào `customerId`. | `SELECT` |
| `getAllCustomers()` | `List<Customer>` | Lấy toàn bộ danh sách khách hàng có trong CSDL. | `SELECT` |
| `updateCustomer(Customer customer)` | `boolean` | Cập nhật thông tin cho một khách hàng đã tồn tại. | `UPDATE` |
| `deleteCustomer(int customerId)` | `boolean` | Xóa một khách hàng khỏi CSDL dựa vào `customerId`. | `DELETE` |
| `findCustomerByPhone(String phone)`| `Customer`| Tìm kiếm khách hàng theo số điện thoại (hữu ích cho chức năng tìm kiếm). | `SELECT` |

2.RoomDAO

| Phương thức (Method) | Kiểu trả về (Return Type) | Mô tả (Description) | Thao tác SQL |
| :--- | :--- | :--- | :--- |
| `addRoom(Room room)` | `boolean` | Thêm một phòng mới vào hệ thống. | `INSERT` |
| `getRoomById(int roomId)` | `Room` | Lấy thông tin chi tiết của một phòng theo `roomId`. | `SELECT` |
| `getAllRooms()` | `List<Room>` | Lấy toàn bộ danh sách phòng trong khách sạn. | `SELECT` |
| `updateRoom(Room room)` | `boolean` | Cập nhật toàn bộ thông tin của một phòng. | `UPDATE` |
| `updateRoomStatus(int roomId, String status)` | `boolean` | Chỉ cập nhật trạng thái của phòng (ví dụ: "Available" -> "Occupied"). | `UPDATE` |
| `deleteRoom(int roomId)` | `boolean` | Xóa một phòng khỏi hệ thống dựa vào `roomId`. | `DELETE` |
| `getAvailableRooms()` | `List<Room>` | Lấy danh sách các phòng đang ở trạng thái "Available". | `SELECT` |

3.BookingDAO

| Phương thức (Method) | Kiểu trả về (Return Type) | Mô tả (Description) | Thao tác SQL |
| :--- | :--- | :--- | :--- |
| `createBooking(Booking booking)` | `boolean` | Tạo một phiếu đặt phòng mới (check-in). | `INSERT` |
| `getBookingById(int bookingId)` | `Booking` | Lấy thông tin một lần đặt phòng cụ thể theo `bookingId`. | `SELECT` |
| `getBookingsByCustomerId(int customerId)`| `List<Booking>` | Lấy lịch sử đặt phòng của một khách hàng. | `SELECT` |
| `getActiveBookings()` | `List<Booking>` | Lấy danh sách các phiếu đặt phòng đang hoạt động ("Checked-in"). | `SELECT` |
| `updateBooking(Booking booking)` | `boolean` | Cập nhật thông tin của một phiếu đặt phòng. | `UPDATE` |
| `checkout(int bookingId)` | `boolean` | Hoàn tất việc đặt phòng (check-out), cập nhật trạng thái và tổng tiền. | `UPDATE` |

