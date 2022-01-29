import java.io.*;

public class MainClass {
    public static void main(String[] args) {
        if (args.length != 2) {
            throw new RuntimeException("Two arguments need to be provided.\n" +
                    "1. Fully qualified .srt File path\n" +
                    "2. Time by which the subtitles need to be modified in the format hh:mm:ss,ms eg(06:06:06,066)");
        }

        Time.validateTime(args[1]);

        System.out.println(System.getProperty("user.dir"));

        File fileToBeModified = new File(args[0]);
        String oldContent = "";
        BufferedReader reader = null;
        FileWriter writer = null;

        try {
            reader = new BufferedReader(new FileReader(fileToBeModified));
            String line = reader.readLine();

            while(line != null){
                oldContent += line + System.lineSeparator();
                line = reader.readLine();
            }

        } catch (IOException e) {
            System.out.println("File not found!");
            e.printStackTrace();
        } finally {
            try {
                //Closing the resources
                reader.close();
                //writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        System.out.println(oldContent);

    }
}