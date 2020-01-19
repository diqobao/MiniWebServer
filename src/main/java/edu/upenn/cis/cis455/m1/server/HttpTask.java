package edu.upenn.cis.cis455.m1.server;

import java.net.Socket;

public class HttpTask {
    boolean isEmpty;
    Socket requestSocket;

    public HttpTask() {
        isEmpty = true;
    }

    public HttpTask(Socket socket) {
        requestSocket = socket;
        isEmpty = false;
    }
    
    public Socket getSocket() {
        return requestSocket;
    }
}
