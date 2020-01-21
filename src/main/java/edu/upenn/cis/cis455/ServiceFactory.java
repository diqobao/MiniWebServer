package edu.upenn.cis.cis455;

import java.net.Socket;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;

import edu.upenn.cis.cis455.m1.server.HttpRequest;
import edu.upenn.cis.cis455.m1.server.HttpResponse;
import edu.upenn.cis.cis455.m1.server.implementations.ControlRequestHandler;
import edu.upenn.cis.cis455.m1.server.implementations.HttpWebService;
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
    private static WebService webService;
    int port;
    int maxThreads;
    String dir;
    
    public static void up(String dir, String address, int port, int maxThreads) {
        webService = new HttpWebService(port, dir, maxThreads, address);
        webService.start();
    }
    
    /**
     * Get the HTTP server associated with port 8080
     */
    public static WebService getServerInstance() {
        return webService;
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
}