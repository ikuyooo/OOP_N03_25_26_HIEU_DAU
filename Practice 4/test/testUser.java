import java.util.ArrayList;
import java.util.List;

public class testUser {
    private List<User> users = new ArrayList<>();

    // Create
    public void addUser(User user) {
        users.add(user);
    }

    // Read
    public List<User> getAllUsers() {
        return users;
    }

    // Update
    public boolean updateUser(String identity, String newName, String newDOB, String newRole) {
        for (User u : users) {
            if (u.getIdentity().equals(identity)) {
                u.setFullName(newName);
                u.setDateOfBirth(newDOB);
                u.setUserRole(newRole);
                return true;
            }
        }
        return false;
    }

    // Delete
    public boolean deleteUser(String identity) {
        return users.removeIf(u -> u.getIdentity().equals(identity));
    }
}
