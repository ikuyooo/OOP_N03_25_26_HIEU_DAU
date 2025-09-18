public class Booking {
    private String bookingId;
    private String userId;
    private String roomId;
    private String date;

    public Booking() {}

    public Booking(String bookingId, String userId, String roomId, String date) {
        this.bookingId = bookingId;
        this.userId = userId;
        this.roomId = roomId;
        this.date = date;
    }

    // Getter & Setter
    public String getBookingId() { return bookingId; }
    public void setBookingId(String bookingId) { this.bookingId = bookingId; }

    public String getUserId() { return userId; }
    public void setUserId(String userId) { this.userId = userId; }

    public String getRoomId() { return roomId; }
    public void setRoomId(String roomId) { this.roomId = roomId; }

    public String getDate() { return date; }
    public void setDate(String date) { this.date = date; }

    @Override
    public String toString() {
        return "Booking{" +
                "bookingId='" + bookingId + '\'' +
                ", userId='" + userId + '\'' +
                ", roomId='" + roomId + '\'' +
                ", date='" + date + '\'' +
                '}';
    }
}
