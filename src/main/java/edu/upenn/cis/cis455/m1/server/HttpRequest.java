package edu.upenn.cis.cis455.m1.server;
import edu.upenn.cis.cis455.m1.server.interfaces.Request;

import java.util.List;
import java.util.Map;
import java.util.Set;

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
    private Map<String, String> headers;
    private Map<String, List<String>> params;
    private boolean persistent;

    public HttpRequest(Map<String, String> headers, Map<String, List<String>> params, String uri) {
        super();
        this.headers = headers;
        this.params = params;
        this.method = headers.get("Method");
        this.uri = uri;
    }

    public HttpRequest() {

    }

    @Override
    public String requestMethod() {
        return method;
    }

    @Override
    public String host() {
        return host;
    }

    @Override
    public String userAgent() {
        return userAgent;
    }

    @Override
    public int port() {
        return port;
    }

    @Override
    public String pathInfo() {
        return pathInfo;
    }

    @Override
    public String url() {
        return url;
    }

    @Override
    public String uri() {
        return uri;
    }

    @Override
    public String protocol() {
        return protocol;
    }

    @Override
    public String contentType() {
        return contentType;
    }

    @Override
    public String ip() {
        return ip;
    }

    @Override
    public String body() {
        return body;
    }

    @Override
    public int contentLength() {
        return contentLength;
    }

    @Override
    public String headers(String name) {
        return headers.getOrDefault(name, "");
    }

    @Override
    public Set<String> headers() {
        return headers.keySet();
    }

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

    @Override
    public void uri(String uri) {
        this.uri = uri;
    }

    @Override
    public void requestMethod(String method) {
        this.method = method;
    }
}
