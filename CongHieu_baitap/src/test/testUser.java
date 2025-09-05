package test;

public class testUser {
    public static void run() {
        User u1 = new User("Hieu", 20, "0394276719", "SV001", "hieu@gmail.com");
        User u2 = new User("Dau", 20, "0987654321", "SV002", "dau@gmail.com");

        System.out.println("===== TEST USER =====");
        System.out.println("Ten User 1: " + u1.getName());
        System.out.println("Ten User 2: " + u2.getName());
    }
}
