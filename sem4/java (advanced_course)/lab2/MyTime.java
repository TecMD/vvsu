import java.util.Calendar;

public class MyTime {

    private int hour;
    private int minute;
    private int second;

    public MyTime() {
        this(System.currentTimeMillis());
    }

    public MyTime(long elapseTime) {
        setTime(elapseTime);
    }

    public MyTime(int hour, int minute, int second) {
        this.hour = hour;
        this.minute = minute;
        this.second = second;
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

    public void setTime(long elapseTime) {

        long totalSeconds = elapseTime / 1000;
        this.second = (int) (totalSeconds % 60);

        long totalMinutes = totalSeconds / 60;
        this.minute = (int) (totalMinutes % 60);

        long totalHours = totalMinutes / 60;
        this.hour = (int) (totalHours % 24);
    }

    public String toString() {
        return String.format("%02d:%02d:%02d", hour, minute, second);
    }
}