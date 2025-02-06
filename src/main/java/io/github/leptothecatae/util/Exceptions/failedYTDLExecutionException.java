package io.github.leptothecatae.util.Exceptions;

import java.util.List;
public class failedYTDLExecutionException extends Exception{

    public failedYTDLExecutionException(String msg, List<String> inputparams, List<String> erroutput){
        super(msg + " with input Parameters: \n" + inputparams.toString() + "\n error message: \n" + erroutput.toString());
    }
}
