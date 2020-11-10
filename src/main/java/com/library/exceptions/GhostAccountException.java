package com.library.exceptions;

public class GhostAccountException extends Exception {
    public GhostAccountException(){
        super("GHOST ACCOUNT - DATABASE LEAK");
    }
}
