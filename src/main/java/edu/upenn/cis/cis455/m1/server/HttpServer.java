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
        startAllWorkers();
        while (this.status) {
            acceptTaskOnSocket();
        }
    }

    private void startAllWorkers() {
        for(HttpWorker worker: workers) {
            start(worker);
        }
    }

    private void acceptTaskOnSocket() {
        try {
            Socket socket = servSocket.accept();
            clientHandler(socket);
        } catch (IOException e) {
            System.out.println("Accept failed");
        }
        }

    private void clientHandler(Socket socket) {
        HttpTask t = new HttpTask(socket);
        queue.enqueue(t);
    }
    
    @Override
    public HttpTaskQueue getRequestQueue() {
        return queue;
    }

    @Override
    public boolean isActive() {
        return status;
    }

    @Override
    public void start(HttpWorker worker) {
        Thread workerThread = new Thread(worker);
        workerThread.start();
    }

    @Override
    public void done(HttpWorker worker) {
        worker.halt();

    }

    @Override
    public void error(HttpWorker worker) {
        // TODO Auto-generated method stub

    }
}
