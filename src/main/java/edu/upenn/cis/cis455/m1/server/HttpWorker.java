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
    private boolean isRunning = false;
    private final HttpTaskQueue internal;

    public HttpWorker(HttpTaskQueue internal) {
        this.internal = internal;
    }

    public void run() {
        isRunning = true;
        while (isRunning) {
            HttpTask task = internal.dequeue();
            taskHandler(task);
        }
    }

    public void taskHandler(HttpTask task) {
        if(task.isEmpty) return ;
        Socket socket = task.getSocket();
        Request request = new HttpRequest();
        Response response = new HttpResponse();
        try {
            boolean persistent = HttpIoHandler.sendResponse(socket, request, response);
            if(!persistent) {
                closeConnection(socket);
            }
        } catch (IOException e) {
            logger.info("ioexception");
        }
    }

    private void closeConnection(Socket socket) {
        try {
            socket.close();
        } catch (IOException e) {
            logger.info("Failed to close socket");
        }
    }

    void halt() {
        isRunning = false;
        interrupt();
    }


}
