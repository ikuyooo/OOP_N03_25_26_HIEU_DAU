
public static void main(String[] args) {
        OuterClassTest oct = new InnerClassTest();
        OuterClassTest.InnerClassTest ict = oct.new InnerClassTest();
        System.out.println(ict);
    }

