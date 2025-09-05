package src;

public class Recursion {

    public static void printUsers(User[] users, int index) {
        if (index >= users.length) return;
        System.out.println("User " + (index + 1) + ": " + users[index].getName());
        printUsers(users, index + 1);
    }

    public static void tick(Time t, int n) {
        if (n == 0) return;

        int h = t.getHour();
        int m = t.getMinute();
        int s = t.getSecond() + 1;

        if (s >= 60) {
            s = 0;
            m++;
        }
        if (m >= 60) {
            m = 0;
            h++;
        }
        if (h >= 24) {
            h = 0;
        }

        t.setTime(h, m, s);
        System.out.println(t);

        tick(t, n - 1);
    }
}