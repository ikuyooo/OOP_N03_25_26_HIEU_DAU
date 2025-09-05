package src;
import test.TestUser;
import test.TestTime;
import test.TestRecursion;

public class App {
    public static void main(String[] args) {
        TestUser.run();
        System.out.println();
        TestTime.run();
        System.out.println();
        TestRecursion.run();
    }
}
