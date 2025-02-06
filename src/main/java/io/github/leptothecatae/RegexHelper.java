package io.github.leptothecatae;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.ArrayList;
public class RegexHelper {

    private final String REGX_START_W_NUMBER = "^[0-9].*";

    private String applyPatternToString(String s, String pattern){
        Matcher m = Pattern.compile(pattern).matcher(s);
        if(m.find()){
            return m.group();
        }
        return null;
        // TODO mayber throw exception if no substring was found
    }
    private boolean containsNumbers(String s){
        return s.matches("\\d+\\w");
    }
    private ArrayList<String> getSubstrings(String s){
        ArrayList<String> substrings = new ArrayList<String>();
        Matcher m = Pattern.compile("\\b[.\\w]+").matcher(s);
        while(m.find()){
            substrings.add(m.group());
        }
        return substrings;
    }
    public void parseVideoFormat(String s){
        // string is not a valid format format
        if(!s.matches(REGX_START_W_NUMBER)){
            return;
        }
        //System.out.println(s);
        ArrayList<String> sstrings = getSubstrings(s);
        int formatCode = Integer.parseInt(sstrings.get(0));
        String formatExtension = sstrings.get(1);
        String resolution = sstrings.get(2);

        System.out.println(formatCode + " " + formatExtension);
    }

}
