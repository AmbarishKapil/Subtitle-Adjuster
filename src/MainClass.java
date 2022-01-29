public class MainClass {
    public static void main(String[] args) {
        if(args.length != 2){
            throw new RuntimeException("Two arguments need to be provided.\n" +
                    "1. File name\n" +
                    "2. Time by which the subtitles need to be modified in the format hh:mm:ss,ms eg(06:06:06,066)");
        }

        Time.validateTime(args[1]);
    }
}
