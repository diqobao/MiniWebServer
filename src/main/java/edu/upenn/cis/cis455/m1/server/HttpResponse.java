package edu.upenn.cis.cis455.m1.server;

import edu.upenn.cis.cis455.m1.server.interfaces.Response;

import java.util.Map;

public class HttpResponse extends Response {
    Map<String, String> headers;
    HttpResponse() {}
    @Override
    public String getHeaders() {
        return "";
    }
}
