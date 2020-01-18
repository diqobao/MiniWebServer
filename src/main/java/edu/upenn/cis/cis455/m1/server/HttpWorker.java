package edu.upenn.cis.cis455.m1.server;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.net.*;

/**
 * Stub class for a thread worker for
 * handling Web requests
 */
public class HttpWorker extends Thread{
    static final Logger logger = LogManager.getLogger(HttpWorker.class);
    private boolean running = true;
    
    public void run(HttpTaskQueue internal) {
        running = true;
        while (running) {
            HttpTask task = internal.dequeue();
            if (task == null) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    logger.info("wait failed");
                }
            } else {
                Socket socket = task.getSocket();
            }
        }
    }
    
    public void halt() {
        running = false;
        interrupt();
    }
}
