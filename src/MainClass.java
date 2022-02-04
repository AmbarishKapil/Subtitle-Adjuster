import java.io.*;

public class MainClass {
    public static void main(String[] args) {
        if (args.length != 3) {
            throw new RuntimeException("Two arguments need to be provided.\n" +
                    "1. Fully qualified .srt File path\n" +
                    "2. Time by which the subtitles need to be modified in the format hh:mm:ss,ms eg(06:06:06,066)\n" +
                    "3. 1 for moving the time offset forward, 2 for moving the time offset backward");
        }

        System.out.println(args[2]);

        if(args[2] == "1" || args[2] == "2"){
            throw new RuntimeException("3rd argument needs to be either 1 or 2 depending on whether " +
                    "you want to move the time offset forward or backward respectively");
        }

        Time.validateTime(args[1]);

        File fileToBeModified = new File(args[0]);
        String oldContent = "";
        BufferedReader reader = null;
        FileWriter writer = null;

        try {
            reader = new BufferedReader(new FileReader(fileToBeModified));
            String line = reader.readLine();

            while(line != null){
                if(line.contains("-->")){
                    Time startTime, endTime;
                    switch (args[2]){
                        case "1":
                            startTime = ForwardBackwardOffset.forwardOffset(
                                    Time.timeParser(line.split(" --> ")[0]),
                                    Time.timeParser(args[1]));

                            endTime = ForwardBackwardOffset.forwardOffset(
                                    Time.timeParser(line.split(" --> ")[1]),
                                    Time.timeParser(args[1]));

                            line = startTime.toString() + " --> " + endTime.toString();
                            break;
                        case "2":
                            startTime = ForwardBackwardOffset.backwardOffset(
                                    Time.timeParser(line.split(" --> ")[0]),
                                    Time.timeParser(args[1]));

                            endTime = ForwardBackwardOffset.backwardOffset(
                                    Time.timeParser(line.split(" --> ")[1]),
                                    Time.timeParser(args[1]));

                            line = startTime.toString() + " --> " + endTime.toString();
                    }
                }
                oldContent += line + System.lineSeparator();
                line = reader.readLine();
            }

        } catch (IOException e) {
            System.out.println("File not found!");
            e.printStackTrace();
        } finally {
            try {
                reader.close();
                //writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        System.out.println(oldContent);

    }
}