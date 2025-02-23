package io.github.leptothecatae;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


import io.github.leptothecatae.util.Exceptions.failedYTDLExecutionException;
import io.github.leptothecatae.util.*;
public class ytdlWrapper {

    private RegexHelper regexhelper = new RegexHelper();
    public ytdlWrapper() {
    }
    public List<Format> getAvailableFormats(URL url) throws failedYTDLExecutionException, IOException {
        List<String> stdout;
        //List<VideoFormat> availableFormats = new ArrayList<VideoFormat>();
        List<Format> availableFormats = new ArrayList<>();
        ArrayList<String> commandArgs = new ArrayList<String>();
        commandArgs.add("--list-formats");
        commandArgs.add(url.toString());
        stdout = executeCommand(commandArgs);
        for(int i = 0; i < stdout.size(); i++){
            if(regexhelper.startsWithNumber(stdout.get(i))){
                //System.out.println(out.get(i));
                //availableFormats.add(new VideoFormat(stdout.get(i), regexhelper));
                availableFormats.add(new Format(stdout.get(i), regexhelper));
            }
        }
        //out.forEach(System.out::println);
        return availableFormats;
    }

    public void fetchVideo(URL url, String videoFormatCode, String audioFormatCode) throws failedYTDLExecutionException, IOException {
        ArrayList<String> cmd = new ArrayList<String>();
        List<String> stdout;
        cmd.add("-f");
        cmd.add(videoFormatCode + "+" + audioFormatCode);
        cmd.add(url.toString());
        stdout = executeCommand(cmd);

        stdout.forEach(System.out::println);
    }
    public void fetchVideo(URL url, Format videoFormat, Format audioFormat) throws failedYTDLExecutionException, IOException {
        fetchVideo(url, videoFormat.getFormatCode(), audioFormat.getFormatCode());
    }
    private List<String> executeCommand(List<String> params) throws IOException, failedYTDLExecutionException {
        // adding executable path to start of params so it can be directly parsed into processbuilder
        //TODO maybe include win and unix binaries and dont hardcode it here ifykwim
        params.add(0, getClass().getResource("/yt-dlp.exe").getPath());
        ProcessBuilder pb = new ProcessBuilder(params);
        Process process = pb.start();

        InputStream is = process.getInputStream();
        InputStreamReader isr = new InputStreamReader(is);
        BufferedReader br = new BufferedReader(isr);

        String stdl;
        List<String> stdoutput = new ArrayList<String>();
        while((stdl = br.readLine()) != null){
            stdoutput.add(stdl);
        }

        if(process.exitValue() != 0){

            //feels like an extremely hacky solution, might have to fix later
            //TODO find more elegant solution
            InputStream errorstream = process.getErrorStream();
            InputStreamReader errorstreamreader = new InputStreamReader(errorstream);
            BufferedReader bufferederror = new BufferedReader(errorstreamreader);

            String errorl;
            ArrayList<String>stderr = new ArrayList<String>();
            while((errorl = bufferederror.readLine()) != null){
                stderr.add(errorl);
            }
            throw new failedYTDLExecutionException("unexedced Error executing YTDL executable", params, stderr);
        }

        return stdoutput;
    }
}
