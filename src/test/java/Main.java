import io.github.leptothecatae.ytdlWrapper;

import java.io.*;
import io.github.leptothecatae.util.Exceptions.failedYTDLExecutionException;

public class Main {
    public static void main(String[] args) throws IOException, failedYTDLExecutionException {

        ytdlWrapper wrapper = new ytdlWrapper();
        wrapper.getAvailableFormats("https://www.youtube.com/watch?v=EYi54OyTNI8");
        //wrapper.fetch("asd");


    }
}
