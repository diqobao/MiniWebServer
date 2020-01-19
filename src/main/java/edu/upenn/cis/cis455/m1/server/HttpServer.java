package edu.upenn.cis.cis455.m1.server;
import java.util.*;
import java.net.*;
import java.io.*;

/**
 * Stub for your HTTP server, which
 * listens on a ServerSocket and handles
 * requests
 */
public class HttpServer implements ThreadManager {
    
    private HttpTaskQueue queue;
    private ArrayList<HttpWorker> workers;
    private int port;
    private String dir;
    private int maxThreads;
    private ServerSocket servSocket;
    private boolean status;
    
    public HttpServer(int port, String dir, int maxThreads) {
        this.port = port;
        this.dir = dir;
        this.maxThreads = maxThreads;
        this.status = true;
        this.workers = new ArrayList<HttpWorker>();
        this.queue = new HttpTaskQueue();
        
        for (int i = 0; i < maxThreads; i++) {
            workers.add(new HttpWorker(queue));
        }
        
        try{
            this.servSocket = new ServerSocket(port);
        } catch (IOException e) {
            System.out.println("Invalid socket");
        }
        this.start();

    }
    
    public void start() {
        for(HttpWorker worker: workers) {
            start(worker);
        }
        while (this.status == true) {
            try {
                System.out.println("Start working");
                Socket socket = servSocket.accept();
                HttpTask t = new HttpTask(socket);
                queue.enqueue(t);
            } catch (IOException e) {
                System.out.println("Accept failed");
            } 

        }
    }
    
    @Override
    public HttpTaskQueue getRequestQueue() {
        // TODO Auto-generated method stub
        return queue;
    }

    @Override
    public boolean isActive() {
        // TODO Auto-generated method stub
        return status;
    }

    @Override
    public void start(HttpWorker worker) {
        // TODO Auto-generated method stub
        Thread workerThread = new Thread(worker);
        workerThread.start();
    }

    @Override
    public void done(HttpWorker worker) {
        // TODO Auto-generated method stub
        worker.halt();

    }

    @Override
    public void error(HttpWorker worker) {
        // TODO Auto-generated method stub
        
    }
}
