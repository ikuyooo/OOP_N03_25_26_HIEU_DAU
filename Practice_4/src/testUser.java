public class testUser {
    public static void main(String[] args) {
        User u1 = new User("Admin");
        User u2 = new User("User");
        User u3 = new User("User");
        u1.setInfo("CongDau", "01/01/2005", "123456789");
        System.out.println("/n");
        u2.setInfo("CongHieu","01/02/2005", "987654321");
        System.out.println("/n");
        u3.setInfo("CongMinh","01/03/2005", "192837465");
        System.out.println("== Test User ==");
        System.out.println(u1.getInfo());
        System.out.println(u2.getInfo());
        System.out.println(u3.getInfo());
    }
}
