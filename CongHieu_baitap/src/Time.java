public class Time {
    int hour;
    int minute;
    int second;

    public Time(int hour, int minute, int second){
        this.hour = hour;
        this.minute = minute;
        this.second = second;
    }
    public int getHour(){
        return this.hour;
    }
    public void setHour(int hour){
        this.hour = hour;
    }
    public int getMinute(){
        return this.minute;
    }
    public void setMinute(int minute){
        this.minute = minute;
    }
    public int getSecond(){
        return this.second;
    }
    public void setSecond(int second){
        this.second = second;
    }
}
