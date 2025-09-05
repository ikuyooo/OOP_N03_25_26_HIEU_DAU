package test;
import src.User;
import src.Recursion;
import src.Time;
public class testTime {
    public static void run() {
        Time t1 = new Time();
        Time t2 = new Time(10);
        Time t3 = new Time(10, 30);
        Time t4 = new Time(10, 30, 45);

        System.out.println("t1: " + t1);
        System.out.println("t2: " + t2);
        System.out.println("t3: " + t3);
        System.out.println("t4: " + t4);
    }
}
