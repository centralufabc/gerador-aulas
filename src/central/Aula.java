package central;

import org.json.simple.JSONObject;

public class Aula {

    private final String day, start, finish, place, freq;

    public Aula(String day, String start, String finish, String place, String freq) {
        this.day = day;
        this.start = start;
        this.finish = finish;
        this.place = place.trim();
        this.freq = freq.trim();
    }

    public String getDay() {
        return day;
    }

    public String getStart() {
        return start;
    }

    public String getFinish() {
        return finish;
    }

    public String getPlace() {
        return place;
    }

    public String getFreq() {
        return freq;
    }

    public JSONObject getJSONObject() {
        JSONObject aula = new JSONObject();

        aula.put("day", this.day);
        aula.put("start", this.start);
        aula.put("finish", this.finish);
        aula.put("place", this.place);
        aula.put("freq", this.freq);

        return aula;
    }

}
