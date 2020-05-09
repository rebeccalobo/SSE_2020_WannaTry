package com.SSE2020.WannaTry.exceptions;

public class BlockedIPException extends Exception {
    public BlockedIPException(){
        super("This IP address has been blocked");
    }
}
