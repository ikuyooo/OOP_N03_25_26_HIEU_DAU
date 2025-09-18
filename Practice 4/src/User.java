public class User {
    private String identity;
    private String fullName;
    private String dateOfBirth;
    private String userRole;

    public User() {}

    public User(String identity, String fullName, String dateOfBirth, String userRole) {
        this.identity = identity;
        this.fullName = fullName;
        this.dateOfBirth = dateOfBirth;
        this.userRole = userRole;
    }

    // --- Getter & Setter ---
    public String getIdentity() { return identity; }
    public void setIdentity(String identity) { this.identity = identity; }

    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }

    public String getDateOfBirth() { return dateOfBirth; }
    public void setDateOfBirth(String dateOfBirth) { this.dateOfBirth = dateOfBirth; }

    public String getUserRole() { return userRole; }
    public void setUserRole(String userRole) { this.userRole = userRole; }

    @Override
    public String toString() {
        return "User{" +
                "ID='" + identity + '\'' +
                ", Name='" + fullName + '\'' +
                ", DOB='" + dateOfBirth + '\'' +
                ", Role='" + userRole + '\'' +
                '}';
    }
}
