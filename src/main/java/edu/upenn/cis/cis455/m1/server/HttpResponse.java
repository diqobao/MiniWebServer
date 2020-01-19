package edu.upenn.cis.cis455.m1.server;

import edu.upenn.cis.cis455.m1.server.interfaces.Response;

import java.util.Map;

public class HttpResponse extends Response {
    Map<String, String> headers;
    HttpResponse() {
        super();
    }

    void addHeader(String name, String value) {
        headers.put(name, value);
    }

    void removeHeader(String name) {
        headers.remove(name);
    }

    @Override
    public String getHeaders() {
        StringBuilder sb = new StringBuilder();
        for(String k: headers.keySet()) {
            sb.append(k);
            sb.append(": ");
            sb.append(headers.get(k));
            sb.append("\n");
        }
        return sb.toString();
    }
}
