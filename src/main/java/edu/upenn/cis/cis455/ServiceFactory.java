package edu.upenn.cis.cis455;

import java.net.Socket;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;

import edu.upenn.cis.cis455.m1.server.HttpRequest;
import edu.upenn.cis.cis455.m1.server.HttpResponse;
import edu.upenn.cis.cis455.m1.server.implementations.ControlRequestHandler;
import edu.upenn.cis.cis455.m1.server.implementations.ShutdownRequestHandler;
import edu.upenn.cis.cis455.m1.server.implementations.StaticRequestHandler;
import edu.upenn.cis.cis455.m1.server.interfaces.WebService;
import edu.upenn.cis.cis455.m1.server.interfaces.HttpRequestHandler;
import edu.upenn.cis.cis455.m1.server.interfaces.Request;
import edu.upenn.cis.cis455.m1.server.interfaces.Response;
import edu.upenn.cis.cis455.m2.server.interfaces.Session;
import edu.upenn.cis.cis455.handlers.Route;
import edu.upenn.cis.cis455.m1.server.HttpServer;

public class ServiceFactory {
    static WService service;
    int port;
    int maxThreads;
    String dir;
    
    public static void up(String dir, String address, int port, int maxThreads) {
        service = new WService();
        service.port(port);
        service.staticFileLocation(dir);
        service.ipAddress(address);
        service.threadPool(maxThreads);
        service.start();
    }
    
    /**
     * Get the HTTP server associated with port 8080
     */
    public static WebService getServerInstance() {
        return service;
    }
    
    /**
     * Create an HTTP request given an incoming socket
     */
    public static Request createRequest(Socket socket,
                         String uri,
                         boolean keepAlive,
                         Map<String, String> headers,
                         Map<String, List<String>> parms) {
        Request request = new HttpRequest(headers, parms, uri);
        request.persistentConnection(keepAlive);
        request.uri(uri);
        request.requestMethod(headers.getOrDefault("Method", "text/plain"));
        return  request;
    }
    
    /**
     * Gets a request handler for files (i.e., static content) or dynamic content
     */
    public static HttpRequestHandler createRequestHandlerInstance(Path serverRoot) {
        if (serverRoot.toString().matches("/*shutdown/*")) {
            return new ShutdownRequestHandler(serverRoot);
        } else if (serverRoot.toString().matches("/*control/*")) {
            return new ControlRequestHandler(serverRoot);
        }
        return new StaticRequestHandler(serverRoot);
    }

    /**
     * Gets a new HTTP Response object
     */
    public static Response createResponse() {
        Response response = new HttpResponse();
        return response;
    }

    /**
     * Creates a blank session ID and registers a Session object for the request
     */
    public static String createSession() {
        return null;
    }
    
    /**
     * Looks up a session by ID and updates / returns it
     */
    public static Session getSession(String id) {
        
        return null;
    }
    static class WService extends WebService {
        private int port;
        private String dir;
        private int maxThread;
        private String ipAddress;
        
        public void start() {
            HttpServer HServer = new HttpServer(this.port, this.dir, this.maxThread);
            HServer.start();
        }

        @Override
        public void get(String path, Route route) {

        }

        @Override
        public void post(String path, Route route) {

        }

        @Override
        public void put(String path, Route route) {

        }

        @Override
        public void delete(String path, Route route) {

        }

        @Override
        public void head(String path, Route route) {

        }

        public void stop() {

        }

        public void staticFileLocation(String directory) {
            this.dir = directory;
        }

        public void ipAddress(String ipAddress) {
            this.ipAddress = ipAddress;
        }

        public void port(int port) {
            this.port = port;
        }

        public void threadPool(int threads) {
            this.maxThread = threads;
        }
    }
}