package io.github.leptothecatae;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;


import io.github.leptothecatae.util.failedYTDLExecutionException;

public class ytdlWrapper {

    public ytdlWrapper() {
    }

    public void getAvailableFormats(String url) throws failedYTDLExecutionException, IOException {
        List<String> out;
        ArrayList<String> in = new ArrayList<String>();
        in.add("--list-formats");
        in.add(url);
        out = executeCommand(in);
        out.forEach(System.out::println);
    }
    private List<String> executeCommand(List<String> params) throws IOException, failedYTDLExecutionException {
        // adding executable path to start of params so it can be directly parsed into processbuilder
        //TODO maybe include win and unix binaries and dont hardcode it here ifykwim
        params.add(0, getClass().getResource("/youtube-dl.exe").getPath());
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
