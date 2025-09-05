package src;

import java.util.List;
public class Recursion{
    public static void printUser(List<User> users, int index){
        if(index >= users.size()){
            return ;
        }
        System.out.println(users.get(index));
        printUser(users, index +1);
    }
public static Time sumTimes(List<Time> times , int index){
    if(index >= times.size()){
        return new Time(0,0,0);
    }
    System.out.println(times.get(index));
    return sumTimes(times, index + 1);
}
}