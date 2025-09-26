public class OuterClass {
    public void callbackMethod(int value) {
        System.out.println("innerclass number: " + value);
    }
    public class InnerClass {
        public void executeCallback(int i) {
            callbackMethod(i);  
        }
    }
}