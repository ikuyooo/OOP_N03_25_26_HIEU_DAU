package test;

public class testRecursion {
    public static void run() {
        System.out.println("\n===== TEST RECURSION =====");

        User[] users = {
            new User("Hieu", 20, "0123456789", "SV001", "hieu@gmail.com"),
            new User("Lan", 21, "0987654321", "SV002", "lan@gmail.com"),
            new User("Minh", 19, "0911222333", "SV003", "minh@gmail.com")
        };

        System.out.println("Danh sách User:");
        Recursion.printUsers(users, 0);

        System.out.println("\nĐồng hồ tăng giây:");
        Time t = new Time(23, 59, 55);
        Recursion.tick(t, 10);
    }
}
