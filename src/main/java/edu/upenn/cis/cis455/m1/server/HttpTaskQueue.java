package edu.upenn.cis.cis455.m1.server;
import java.util.*;

/**
 * Stub class for implementing the queue of HttpTasks
 */
public class HttpTaskQueue {
    private final Queue<HttpTask> internal = new LinkedList();
    
    public synchronized HttpTask dequeue() {
        if (!internal.isEmpty())
            return internal.poll();
        else
            return null;
    }
    
    public synchronized void enqueue(HttpTask task) {
        internal.offer(task);
    }
    
    public synchronized int size() {
        return internal.size();
    }
    
}
