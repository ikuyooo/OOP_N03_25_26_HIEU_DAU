public class TestListOfUser {
    public static void main(String[] args) {
        ListOfUser list = new ListOfUser();
        User u1 = new User("Admin");
        User u2 = new User("User");
        User u3 = new User("User");
        u1.setInfo("CongDau", "01/01/2005", "123456789");
        
        u2.setInfo("CongHieu","01/02/2005", "987654321");
        u3.setInfo("CongMinh","01/03/2005", "192837465");

        list.addUser(u1);
        list.addUser(u2);
        list.addUser(u3);

        System.out.println("== Danh sách User ==");
        list.showAllUsers();

        System.out.println("\n== Tìm kiếm theo CMND 123456789 ==");
        User found = list.findUserByIdentity("123456789");
        if (found != null) {
            System.out.println(found.getInfo());
        } else {
            System.out.println("Không tìm thấy!");
        }
    }
}
