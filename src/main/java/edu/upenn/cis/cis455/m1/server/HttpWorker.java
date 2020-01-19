package edu.upenn.cis.cis455.m1.server;
import edu.upenn.cis.cis455.m1.server.interfaces.Request;
import edu.upenn.cis.cis455.m1.server.interfaces.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.net.*;

/**
 * Stub class for a thread worker for
 * handling Web requests
 */
public class HttpWorker extends Thread {
    static final Logger logger = LogManager.getLogger(HttpWorker.class);
    private boolean running = false;
    private final HttpTaskQueue internal;

    public HttpWorker(HttpTaskQueue internal) {
        this.internal = internal;
    }

    public void run() {
        running = true;
        while (running) {
            HttpTask task = internal.dequeue();
            if (task == null) {
//                try {
//                    wait();
//                } catch (InterruptedException e) {
//                    logger.info("wait failed");
//                }
            } else {
                Socket socket = task.getSocket();
                Request request = new HttpRequest();
                Response response = new HttpResponse();
                boolean persistent = true;
                try {
                    persistent = HttpIoHandler.sendResponse(socket, request, response);
                } catch (IOException e) {
                    logger.info("ioexception");
                }
                if(!persistent) {
                    try {
                        socket.close();
                    } catch (IOException e) {
                        logger.info("Failed to close socket");
                    }
                }
            }
        }
    }

    public void halt() {
        running = false;
        interrupt();
    }
}
