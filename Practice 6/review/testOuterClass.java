public class testOuterClass {
        public static void main(String[] args) {
        OuterClass outer = new OuterClass();
        OuterClass.InnerClass inner = outer.new InnerClass();
        for (int i = 1; i <= 10; i++) {
            inner.executeCallback(i);
        }  

    }
}