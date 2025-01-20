import io.github.leptothecatae.optionHandler;
import io.github.leptothecatae.ytdlWrapper;

import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {

        System.out.println("Hello Testing");
        optionHandler optionhandler = new optionHandler();
        ytdlWrapper wrapper = new ytdlWrapper(optionhandler);

        ProcessBuilder pb = new ProcessBuilder("ffmpeg");
        Process process = pb.start();

        String output = new String(process.getInputStream().readAllBytes());
        System.out.println(output);

//        InputStream is = process.getInputStream();
 //       InputStreamReader isr = new InputStreamReader(is);
  //      BufferedReader br = new BufferedReader(isr);
   //     String line;

//        while((line = br.readLine()) != null){
 //           System.out.println(line);
  //      }
    }
}
