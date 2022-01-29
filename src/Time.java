import java.util.regex.Pattern;

public class Time {
    private String hour;
    private String minute;
    private String second;
    private String milliSecond;

    private Time(String hour, String minute, String second, String milliSecond) {
        this.hour = hour;
        this.minute = minute;
        this.second = second;
        this.milliSecond = milliSecond;
    }

    /**
     * This method takes in a timeString in the format "hh:mm:ss,ms"
     * and returns a Time object
     *
     * @param timeString
     * @return
     */
    public static Time timeParser(String timeString){
        return new Time(timeString.split(",")[0].split(":")[0],
                timeString.split(",")[0].split(":")[1],
                timeString.split(",")[0].split(":")[2],
                timeString.split(",")[1]);
    }

    /**
     * This method validates the format and validity of the entered time
     *
     * @param time
     * @throws RuntimeException
     */
    public static void validateTime(String time) throws RuntimeException{
        if(!Pattern.matches("[0-2][0-3]:[0-5][0-9]:[0-5][0-9],[0-9][0-9][0-9]",time)){
            throw new RuntimeException("Entered time string is not in the proper hh:mm:ss,ms format");
        }
    }

    @Override
    public String toString() {
        return hour + ":" + minute + ":" + second + "," + milliSecond;
    }
}
