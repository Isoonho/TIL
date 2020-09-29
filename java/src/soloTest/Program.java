package soloTest;

public class Program {
    String name;
    String dayOfTheWeek;
    String pdName;
    String time;


    Program(String name, String dayOfTheWeek, String pdName, String time) {
        this.name = name;
        this.dayOfTheWeek = dayOfTheWeek;
        this.pdName = pdName;
        this.time = time;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDayOfTheWeek() {
        return dayOfTheWeek;
    }

    public void setDayOfTheWeek(String dayOfTheWeek) {
        this.dayOfTheWeek = dayOfTheWeek;
    }

    public String getPdName() {
        return pdName;
    }

    public void setPdName(String pdName) {
        this.pdName = pdName;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

}
