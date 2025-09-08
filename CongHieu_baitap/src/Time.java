
public class Time {
    private int hour;
    private int minute;
    private int second;

    public Time() {
        setTime(0, 0, 0);
    }

    public Time(int h) {
        setTime(h, 0, 0);
    }

    public Time(int h, int m) {
        setTime(h, m, 0);
    }

    public Time(int h, int m, int s) {
        setTime(h, m, s);
    }

    public void setTime(int h, int m, int s) {
        setHour(h);
        setMinute(m);
        setSecond(s);
    }

    public int getHour() {
        return hour;
    }

    public int getMinute() {
        return minute;
    }

    public int getSecond() {
        return second;
    }

    public void setHour(int h) {
        if (h >= 0 && h < 24) {
            this.hour = h;
        } else {
            this.hour = 0;
        }
    }

    public void setMinute(int m) {
        if (m >= 0 && m < 60) {
            this.minute = m;
        } else {
            this.minute = 0;
        }
    }

    public void setSecond(int s) {
        if (s >= 0 && s < 60) {
            this.second = s;
        } else {
            this.second = 0;
        }
    }

    public String toString() {
        return String.format("%02d:%02d:%02d", hour, minute, second);
    }
}
