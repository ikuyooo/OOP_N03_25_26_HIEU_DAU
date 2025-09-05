package src;

public class User {
    public String name;
    public int age;
    public String id;
    public String lophoc;
    public String diachi;
 public User(String name , int age , String id , String lophoc , String diachi){
    this.name =name;
    this.age =age;
    this.id=id;
    this.lophoc=lophoc;
    this.diachi=diachi;
 }
 public String getName(){
    return name;
 }   
 public void setName(String name){
    this.name=name;
 }
 public int getAge(){
    return age;
 }
 public void setAge(int age){
    this.age=age;
 }
 public String getId(){
    return id;
 }
 public void setId(String id){
    this.id=id;
 }
 public String lophoc(){
    return lophoc;
 }
 public void setLophoc(String lophoc){
     this.lophoc=lophoc; 
}
 public String getDiachi(){
        return diachi;
    }
 public void setDiachi(String diachi){
        this.diachi=diachi;
    }
    public String toString(){
         return "User{" +
                "Name : '" + name + '\'' +
                "Tuoi : '" + age + '\'' +
                "maSV : '" + id + '\'' +
                "Lop học : '" + lophoc + '\'' +
                "Dia Chi : '" + diachi + '\'' +
                '}';
    }
}
