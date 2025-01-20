package io.github.leptothecatae;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class ytdlWrapper {

    optionHandler optHandler;
    public ytdlWrapper() {
    }
    public ytdlWrapper(optionHandler o){
        optHandler = o;
    }

    public void fetch(String url) throws IOException {
        ProcessBuilder pb = new ProcessBuilder("cmd.exe /c ffmpeg");
        Process process = pb.start();

        InputStream is = process.getInputStream();
        InputStreamReader isr = new InputStreamReader(is);
        BufferedReader br = new BufferedReader(isr);
        String line;

        while((line = br.readLine()) != null){
            System.out.println(line);
        }
    }
    public void fetch(String url, optionHandler ohandler){

    }
    public void setOptions(optionHandler o){
        optHandler = o;
    }
}
