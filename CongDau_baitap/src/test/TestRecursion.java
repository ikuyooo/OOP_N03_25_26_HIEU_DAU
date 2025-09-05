package test;

import src.User;
import src.Time;
import src.Recursion;
import java.util.*;
import java.util.ArrayList;
import java.util.List;

public class TestRecursion {
    public static void run() {
        List<User> users = new ArrayList<>();
        users.add(new User("Dau",20, "23010863"));
        users.add(new User("Hieu", 20,"23012020"));

        List<Time> times = new ArrayList<>();
        times.add(new Time(2, 30, 15));

        System.out.println("=== Test Recursion Print Users ===");
        Recursion.printUsers(users, 0);

        System.out.println("\n=== Test Recursion Print Times ===");
        Recursion.printTimes(times, 0);
    }
}
