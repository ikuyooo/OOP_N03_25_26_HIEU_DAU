public class App {
    public static void main(String[] args) {
        // --- User CRUD ---
        testUser testUser = new testUser();
        testUser.addUser(new User("U001", "Nguyen Van A", "2000-01-01", "Customer"));
        testUser.addUser(new User("U002", "Tran Van B", "1999-02-02", "Admin"));

        System.out.println("Users:");
        testUser.getAllUsers().forEach(System.out::println);

        // Update User
        testUser.updateUser("U001", "Nguyen Van A Updated", "2000-01-01", "VIP");
        System.out.println("After update:");
        testUser.getAllUsers().forEach(System.out::println);

        // --- Room CRUD ---
        testRoom testRoom = new testRoom();
        testRoom.addRoom(new Room("R101", "Single", 200.0));
        testRoom.addRoom(new Room("R102", "Double", 300.0));

        System.out.println("\nRooms:");
        testRoom.getAllRooms().forEach(System.out::println);

        // Update Room
        testRoom.updateRoom("R101", "Suite", 500.0);
        System.out.println("After update:");
        testRoom.getAllRooms().forEach(System.out::println);

        // --- Booking CRUD ---
        testBooking testBooking = new testBooking();
        testBooking.addBooking(new Booking("B001", "U001", "R101", "2025-09-15"));
        testBooking.addBooking(new Booking("B002", "U002", "R102", "2025-09-16"));

        System.out.println("\nBookings:");
        testBooking.getAllBookings().forEach(System.out::println);

        // Update Booking
        testBooking.updateBooking("B001", "U002", "R102", "2025-10-01");
        System.out.println("After update:");
        testBooking.getAllBookings().forEach(System.out::println);
    }
}
