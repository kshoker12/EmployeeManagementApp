package Model;

import Persistence.Writable;
import org.json.JSONObject;

public class Availability implements Writable {
    private int day;
    private int start;
    private int hours;

    public Availability(int day, int hours, int start) {
        this.day = day;
        this.hours = hours;
        this.start = start;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getHours() {
        return hours;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("day", day);
        json.put("hours", hours);
        json.put("start", start);
        return json;
    }
}
