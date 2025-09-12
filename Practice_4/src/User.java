public class User implements PeopleInterface {
    private String fullName;
    private String dateOfBirth;
    private String identity;
    private String userRole;

    public User(String userRole) {
        this.userRole = userRole;
    }

    @Override
    public void setInfo(String fullName, String dateOfBirth, String identity) {
        this.fullName = fullName;
        this.dateOfBirth = dateOfBirth;
        this.identity = identity;
    }

    @Override
    public String getInfo() {
        return "Tên: " + fullName + 
               ", Ngày sinh: " + dateOfBirth + 
               ", CMND/CCCD: " + identity + 
               ", Vai trò: " + userRole;
    }

    public String getUserRole() {
        return userRole;
    }
}

