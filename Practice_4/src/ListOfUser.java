
import java.util.ArrayList;

public class ListOfUser {
    private ArrayList<User> users;

    public ListOfUser() {
        users = new ArrayList<>();
    }

    public void addUser(User user) {
        users.add(user);
    }

    public void showAllUsers() {
        for (User u : users) {
            System.out.println(u.getInfo());
        }
    }

    public User findUserByIdentity(String identity) {
        for (User u : users) {
            if (u.getInfo().contains(identity)) {
                return u;
            }
        }
        return null;
    }
}
