package io.github.leptothecatae.util;

import io.github.leptothecatae.util.RegexHelper;

import javax.naming.NameNotFoundException;
import java.util.ArrayList;

public class Format {

    private String formatCode;
    private String ext;
    private String resolution;
    private String fps;
    private String filesize;
    private String protocol;
    private String vcodec;
    private String vbitrate;
    private String acodec;
    private String abitrate;
    public Format(String s, RegexHelper reg){
        ArrayList<String> substrings = reg.getSubstrings(s);
        formatCode = substrings.get(0);
        ext = substrings.get(1);
        if(s.contains("video only")){
            resolution = substrings.get(2);
            fps = substrings.get(3);
            filesize = substrings.get(4);
            protocol = substrings.get(6);
            vcodec = substrings.get(7);
            vbitrate = substrings.get(8);
            return;
        }
        if(s.contains("audio only")){
            System.out.println("cannot parse audio only yet");
            return;
        }
        System.out.println("cannot parse string");
    }


    public String toString(){
        return formatCode + " " +
                ext + " " +
                resolution + " " +
                fps + " " +
                filesize + " " +
                protocol + " " +
                vcodec + " " +
                vbitrate + " " +
                acodec + " " +
                abitrate;
    }
    public String getFormatCode(){
        return formatCode;
    }
}
