package edu.upenn.cis.cis455.m1.server;
import java.util.*;

/**
 * Stub class for implementing the queue of HttpTasks
 */
public class HttpTaskQueue {
    private final Queue<HttpTask> internal = new LinkedList();
    private int size = 0;

    synchronized HttpTask dequeue() {
        if (!internal.isEmpty()) {
            size--;
            return internal.poll();
        } else {
            return new HttpTask();
        }
    }
    
    synchronized void enqueue(HttpTask task) {
        internal.offer(task);
        size ++;
    }
    
    public synchronized int size() {
        return size();
    }
}
