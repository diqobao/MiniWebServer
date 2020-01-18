package edu.upenn.cis.cis455.m1.server;
import edu.upenn.cis.cis455.m1.server.interfaces.Request;

import java.util.Map;

public class HttpRequest extends Request {
        /**
     * Indicates we have a persistent HTTP 1.1 connection
     */
	private String method;
	private String host;
	private String userAgent;
	private int port;
	private String pathInfo;
	private String url;
	private String uri;
	private String protocol;
	private String contentType;
	private String ip;
	private String body;
	private int contentLength;
	private String currDir;
    public Map<String, String> headers;
    
    /**
     * The request method (GET, POST, ...)
     */
    public abstract String requestMethod();

    /**
     * @return The host
     */
    public abstract String host();  
    
    /**
     * @return The user-agent
     */
    public abstract String userAgent();
    
    /**
     * @return The server port
     */
    public abstract int port();
    
    /**
     * @return The path
     */
    public abstract String pathInfo();
    
    /**
     * @return The URL
     */
    public abstract String url();
    
    /**
     * @return The URI up to the query string
     */
    public abstract String uri();
    
    /**
     * @return The protocol name and version from the request
     */
    public abstract String protocol();

    /**
     * @return The MIME type of the body
     */
    public abstract String contentType();
    
    /**
     * @return The client's IP address
     */
    public abstract String ip();
    
    /**
     * @return The request body sent by the client
     */
    public abstract String body();
    
    /**
     * @return The length of the body
     */
    public abstract int contentLength();
    
    /**
     * @return Get the item from the header
     */
    public abstract String headers(String name);
    
    public abstract Set<String> headers();
    
    /**
     * Indicates we have a persistent HTTP 1.1 connection
     */
    public boolean persistentConnection() {
        return persistent;
    }
    
    /**
     * Sets whether we have a persistent HTTP 1.1 connection
     */
    public void persistentConnection(boolean persistent) {
        this.persistent = persistent;
    }
}
