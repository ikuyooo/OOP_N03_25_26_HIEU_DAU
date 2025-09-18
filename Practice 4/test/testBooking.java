import java.util.ArrayList;
import java.util.List;

public class testBooking {
    private List<Booking> bookings = new ArrayList<>();

    // Create
    public void addBooking(Booking booking) {
        bookings.add(booking);
    }

    // Read
    public List<Booking> getAllBookings() {
        return bookings;
    }

    // Update
    public boolean updateBooking(String bookingId, String newUserId, String newRoomId, String newDate) {
        for (Booking b : bookings) {
            if (b.getBookingId().equals(bookingId)) {
                b.setUserId(newUserId);
                b.setRoomId(newRoomId);
                b.setDate(newDate);
                return true;
            }
        }
        return false;
    }

    // Delete
    public boolean deleteBooking(String bookingId) {
        return bookings.removeIf(b -> b.getBookingId().equals(bookingId));
    }
}
