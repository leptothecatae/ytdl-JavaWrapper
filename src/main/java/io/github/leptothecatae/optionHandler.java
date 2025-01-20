package io.github.leptothecatae;

import io.github.leptothecatae.util.opt;

import java.util.ArrayList;

public class optionHandler {

    private ArrayList<String> params = new ArrayList<String>();
    public optionHandler(){
    }

    public void setoption(String option){
        params.add(String.format("--%s", option));
    }
    public void setoption(String option, String value){
        params.add(String.format("--%s", option));
        params.add(value);
    }

}
