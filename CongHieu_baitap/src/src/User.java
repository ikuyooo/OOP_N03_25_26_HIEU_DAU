package src;
public class User {
    public String name;
    public int age;
    public String phoneNumber;
    public String ID;
    public String email;

    public User(String name, int age, String phoneNumber, String ID, String email){
        this.name = name;
        this.age = age;
        this.phoneNumber = phoneNumber;
        this.ID = ID;
        this.email = email;
    }
    public String getName(){
        return this.name;
    }
    public void setName(String name){
        this.name = name;
    }
    public int getAge(){
        return this.age;
    }
    public void setAge(int age){
        this.age = age;
    }
    public String getPhoneNumber(){
        return this.phoneNumber;
    }
    public void setPhoneNumer(String phoneNumber){
        this.phoneNumber = phoneNumber;
    }
    public String getID(){
        return this.ID;
    }
    public void setID(String ID){
        this.ID = ID;
    }
    public String getEmail(){
        return this.email;
    }
    public void setEmail(String email){
        this.email = email;
    }
}

